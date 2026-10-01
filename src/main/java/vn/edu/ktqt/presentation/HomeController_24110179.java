package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.HomeService_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController_24110179 extends HttpServlet {
    private final HomeService_24110179 homeService = new HomeService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        try {
            page = Math.max(1, Integer.parseInt(request.getParameter("page")));
        } catch (NumberFormatException ignored) {
        }

        try {
            int pageSize = 3;
            request.setAttribute("message", homeService.getWelcomeMessage());
            request.setAttribute("authorBooks", homeService.getBooksByAuthor(page, pageSize));
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", homeService.getTotalPages(pageSize));
        } catch (Exception e) {
            request.setAttribute("error", "Không thể tải danh sách sách");
        }
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }
}
