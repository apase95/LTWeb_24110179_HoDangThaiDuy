package com.example.controller.admin;

import com.example.entity.Category;
import com.example.service.ICategoryService;
import com.example.service.CategoryServiceImpl;
import com.example.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category/insert",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
@MultipartConfig
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/categories")) {
            List<Category> list = categoryService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        } else if (uri.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/category/insert")) {
            insertCategory(req, resp);
        } else if (uri.contains("/admin/category/update")) {
            updateCategory(req, resp);
        }
    }

    private void insertCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("categoryname");
        int status = Integer.parseInt(req.getParameter("status"));
        String imagesLink = req.getParameter("images");

        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setStatus(status);

        Part filePart = req.getPart("images1");
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
        } else if (imagesLink != null && !imagesLink.isEmpty()) {
            fileName = imagesLink;
        } else {
            fileName = "default.png";
        }
        category.setImages(fileName);

        categoryService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryid"));
        String categoryName = req.getParameter("categoryname");
        int status = Integer.parseInt(req.getParameter("status"));
        String imagesLink = req.getParameter("images");

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String oldFile = category.getImages();
        category.setCategoryName(categoryName);
        category.setStatus(status);

        Part filePart = req.getPart("images1");
        if (filePart != null && filePart.getSize() > 0) {
            if (oldFile != null && !oldFile.startsWith("http")) {
                File old = new File(Constant.UPLOAD_DIR + File.separator + oldFile);
                if (old.exists()) old.delete();
            }
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            int idx = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(idx + 1);
            String newFile = System.currentTimeMillis() + "." + ext;
            String uploadPath = Constant.UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            filePart.write(uploadPath + File.separator + newFile);
            category.setImages(newFile);
        } else if (imagesLink != null && !imagesLink.isEmpty()) {
            category.setImages(imagesLink);
        } else {
            category.setImages(oldFile);
        }

        categoryService.update(category);
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}