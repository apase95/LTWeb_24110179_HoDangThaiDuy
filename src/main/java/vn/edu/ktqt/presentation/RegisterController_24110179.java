package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.AuthService_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController_24110179 extends HttpServlet {
    private final AuthService_24110179 authService = new AuthService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        try {
            String otp = authService.startRegister(email);
            HttpSession session = request.getSession();
            session.setAttribute("registerEmail", email);
            session.setAttribute("registerFullname", request.getParameter("fullname"));
            session.setAttribute("registerPhone", request.getParameter("phone"));
            session.setAttribute("registerPassword", request.getParameter("password"));
            session.setAttribute("registerOtp", otp);
            response.sendRedirect(request.getContextPath() + "/verify-otp");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}
