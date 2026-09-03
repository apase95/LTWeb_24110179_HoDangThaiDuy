package com.example.controller.web;

import com.example.entity.UserEntity;
import com.example.model.User;
import com.example.service.IUserServiceJpa;
import com.example.service.UserServiceJpaImpl;
import com.example.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                 maxFileSize = 1024 * 1024 * 5,
                 maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileController extends HttpServlet {
    private IUserServiceJpa userService = new UserServiceJpaImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        UserEntity userEntity = userService.findByUsername(user.getUserName());
        if (userEntity != null) {
            req.setAttribute("user", userEntity);
        } else {
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        UserEntity userEntity = userService.findByUsername(user.getUserName());
        if (userEntity == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        userEntity.setFullname(fullname);
        userEntity.setPhone(phone);

        Part filePart = req.getPart("avatar");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int idx = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(idx + 1);
            fileName = System.currentTimeMillis() + "." + ext;
            String uploadPath = Constant.UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + fileName);

            String oldAvatar = userEntity.getAvatar();
            if (oldAvatar != null && !oldAvatar.startsWith("http")) {
                File oldFile = new File(Constant.UPLOAD_DIR + File.separator + oldAvatar);
                if (oldFile.exists()) oldFile.delete();
            }
            userEntity.setAvatar(fileName);
        }

        userService.update(userEntity);

        User updatedUser = userEntity.toModelUser();
        session.setAttribute("account", updatedUser);

        req.setAttribute("message", "Cập nhật thành công!");
        req.setAttribute("user", userEntity);
        req.getRequestDispatcher("/views/web/profile.jsp").forward(req, resp);
    }
}