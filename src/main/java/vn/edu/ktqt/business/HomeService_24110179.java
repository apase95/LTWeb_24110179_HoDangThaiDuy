package vn.edu.ktqt.business;

import vn.edu.ktqt.data.BookDAO_24110179;
import vn.edu.ktqt.model.AuthorBooks_24110179;
import vn.edu.ktqt.model.Author_24110179;
import vn.edu.ktqt.model.BookDetail_24110179;
import vn.edu.ktqt.model.Book_24110179;

import java.sql.SQLException;
import java.util.List;

public class HomeService_24110179 {
    private final BookDAO_24110179 bookDAO = new BookDAO_24110179();

    public String getWelcomeMessage() {
        return "Chào mừng đến với Book Store";
    }

    public List<AuthorBooks_24110179> getBooksByAuthor(int page, int pageSize) throws SQLException {
        return bookDAO.findBooksGroupedByAuthor(page, pageSize);
    }

    public int getTotalPages(int pageSize) throws SQLException {
        return bookDAO.countAuthors();
    }

    public BookDetail_24110179 getBookDetail(int bookId) throws SQLException {
        return bookDAO.findDetail(bookId);
    }

    public void addReview(int userId, int bookId, String reviewText) throws SQLException {
        bookDAO.addReview(userId, bookId, reviewText);
    }

    public List<Book_24110179> getAdminBooks(int page, int pageSize) throws SQLException {
        return bookDAO.findAdminBooks(page, pageSize);
    }

    public int getAdminBookPages(int pageSize) throws SQLException {
        return (int) Math.ceil(bookDAO.countBooks() / (double) pageSize);
    }

    public List<Author_24110179> getAuthors() throws SQLException {
        return bookDAO.findAuthors();
    }

    public void createBook(Book_24110179 book, int authorId) throws SQLException {
        bookDAO.createBook(book, authorId);
    }

    public void updateBook(Book_24110179 book, int authorId) throws SQLException {
        bookDAO.updateBook(book, authorId);
    }

    public void deleteBook(int bookId) throws SQLException {
        bookDAO.deleteBook(bookId);
    }
}
