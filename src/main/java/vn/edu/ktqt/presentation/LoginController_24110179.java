package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.AuthService_24110179;
import vn.edu.ktqt.model.User_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController_24110179 extends HttpServlet {
    private final AuthService_24110179 authService = new AuthService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            User_24110179 user = authService.login(request.getParameter("email"), request.getParameter("password"));
            if (user == null) {
                request.setAttribute("error", "Email hoặc mật khẩu không đúng");
                doGet(request, response);
                return;
            }
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + (user.isAdmin() ? "/admin" : "/home"));
        } catch (Exception e) {
            request.setAttribute("error", "Không thể đăng nhập");
            doGet(request, response);
        }
    }
}
