package vn.edu.ktqt.presentation;

import vn.edu.ktqt.business.HomeService_24110179;
import vn.edu.ktqt.model.BookDetail_24110179;
import vn.edu.ktqt.model.Book_24110179;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class AdminBookController_24110179 extends HttpServlet {
    private final HomeService_24110179 homeService = new HomeService_24110179();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action) || "edit".equals(action)) {
                request.setAttribute("authors", homeService.getAuthors());
                if ("edit".equals(action)) {
                    BookDetail_24110179 detail = homeService.getBookDetail(parseInt(request, "id", 0));
                    request.setAttribute("book", detail == null ? null : detail.getBook());
                }
                request.getRequestDispatcher("/WEB-INF/views/admin/book-form.jsp").forward(request, response);
                return;
            }
            int page = parseInt(request, "page", 1);
            int pageSize = 5;
            request.setAttribute("books", homeService.getAdminBooks(page, pageSize));
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", homeService.getAdminBookPages(pageSize));
            request.getRequestDispatcher("/WEB-INF/views/admin/books.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("delete".equals(action)) {
                homeService.deleteBook(parseInt(request, "id", 0));
            } else {
                Book_24110179 book = readBook(request);
                int authorId = parseInt(request, "authorId", 0);
                if ("edit".equals(action)) {
                    homeService.updateBook(book, authorId);
                } else {
                    homeService.createBook(book, authorId);
                }
            }
            response.sendRedirect(request.getContextPath() + "/admin/books");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private Book_24110179 readBook(HttpServletRequest request) {
        return new Book_24110179(
                parseInt(request, "bookId", 0),
                parseInt(request, "isbn", 0),
                request.getParameter("title"),
                "",
                request.getParameter("publisher"),
                new BigDecimal(request.getParameter("price")),
                Date.valueOf(request.getParameter("publishDate")),
                request.getParameter("coverImage"),
                parseInt(request, "quantity", 0),
                0
        );
    }

    private int parseInt(HttpServletRequest request, String name, int defaultValue) {
        try {
            return Integer.parseInt(request.getParameter(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
