package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.HomeService_24110179;
import vn.edu.ktqt.model.BookDetail_24110179;
import vn.edu.ktqt.model.User_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookDetailController_24110179 extends HttpServlet {
    private final HomeService_24110179 homeService = new HomeService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = parseBookId(request);
        try {
            BookDetail_24110179 detail = homeService.getBookDetail(bookId);
            if (detail == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            request.setAttribute("detail", detail);
            request.getRequestDispatcher("/WEB-INF/views/book-detail.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        int bookId = parseBookId(request);
        User_24110179 user = (User_24110179) request.getSession().getAttribute("currentUser");
        int userId = user == null ? 2 : user.getId();
        try {
            homeService.addReview(userId, bookId, request.getParameter("reviewText"));
            response.sendRedirect(request.getContextPath() + "/book-detail?id=" + bookId);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private int parseBookId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
