package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.AuthService_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyOtpController_24110179 extends HttpServlet {
    private final AuthService_24110179 authService = new AuthService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/verify-otp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String expectedOtp = (String) session.getAttribute("registerOtp");
        if (expectedOtp == null || !expectedOtp.equals(request.getParameter("otp"))) {
            request.setAttribute("error", "OTP không đúng");
            doGet(request, response);
            return;
        }

        try {
            authService.createUser(
                    (String) session.getAttribute("registerEmail"),
                    (String) session.getAttribute("registerFullname"),
                    (String) session.getAttribute("registerPhone"),
                    (String) session.getAttribute("registerPassword")
            );
            session.removeAttribute("registerEmail");
            session.removeAttribute("registerFullname");
            session.removeAttribute("registerPhone");
            session.removeAttribute("registerPassword");
            session.removeAttribute("registerOtp");
            response.sendRedirect(request.getContextPath() + "/login?registered=1");
        } catch (Exception e) {
            request.setAttribute("error", "Không thể tạo tài khoản");
            doGet(request, response);
        }
    }
}
