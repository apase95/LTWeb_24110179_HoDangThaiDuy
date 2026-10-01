package vn.edu.ktqt.data;

import vn.edu.ktqt.model.AuthorBooks_24110179;
import vn.edu.ktqt.model.Author_24110179;
import vn.edu.ktqt.model.BookDetail_24110179;
import vn.edu.ktqt.model.Book_24110179;
import vn.edu.ktqt.model.Review_24110179;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAO_24110179 {
    public List<AuthorBooks_24110179> findBooksGroupedByAuthor(int page, int pageSize) throws SQLException {
        String authorName = findAuthorByPage(page);
        if (authorName == null) {
            return Collections.emptyList();
        }

        String sql = "SELECT b.bookid, b.isbn, b.title, a.author_name, b.publisher, b.price, b.publish_date, "
                + "b.cover_image, b.quantity, COUNT(r.userid) review_count "
                + "FROM books b "
                + "JOIN book_author ba ON ba.bookid = b.bookid "
                + "JOIN author a ON a.author_id = ba.author_id "
                + "LEFT JOIN rating r ON r.bookid = b.bookid "
                + "WHERE a.author_name = ? "
                + "GROUP BY b.bookid, b.isbn, b.title, a.author_name, b.publisher, b.price, b.publish_date, b.cover_image, b.quantity "
                + "ORDER BY b.bookid LIMIT ?";
        List<Book_24110179> books = new ArrayList<>();
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, authorName);
            statement.setInt(2, pageSize);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book_24110179(
                            rs.getInt("bookid"),
                            rs.getInt("isbn"),
                            rs.getString("title"),
                            rs.getString("author_name"),
                            rs.getString("publisher"),
                            rs.getBigDecimal("price"),
                            rs.getDate("publish_date"),
                            rs.getString("cover_image"),
                            rs.getInt("quantity"),
                            rs.getInt("review_count")
                    ));
                }
            }
        }
        return Collections.singletonList(new AuthorBooks_24110179(authorName, books));
    }

    public int countAuthors() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT a.author_id) FROM author a JOIN book_author ba ON ba.author_id = a.author_id";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public BookDetail_24110179 findDetail(int bookId) throws SQLException {
        String sql = "SELECT b.bookid, b.isbn, b.title, a.author_name, b.publisher, b.price, b.publish_date, "
                + "b.cover_image, b.quantity, COUNT(r.userid) review_count "
                + "FROM books b "
                + "JOIN book_author ba ON ba.bookid = b.bookid "
                + "JOIN author a ON a.author_id = ba.author_id "
                + "LEFT JOIN rating r ON r.bookid = b.bookid "
                + "WHERE b.bookid = ? "
                + "GROUP BY b.bookid, b.isbn, b.title, a.author_name, b.publisher, b.price, b.publish_date, b.cover_image, b.quantity";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                Book_24110179 book = new Book_24110179(
                        rs.getInt("bookid"), rs.getInt("isbn"), rs.getString("title"), rs.getString("author_name"),
                        rs.getString("publisher"), rs.getBigDecimal("price"), rs.getDate("publish_date"),
                        rs.getString("cover_image"), rs.getInt("quantity"), rs.getInt("review_count")
                );
                return new BookDetail_24110179(book, findReviews(bookId));
            }
        }
    }

    public void addReview(int userId, int bookId, String reviewText) throws SQLException {
        String sql = "INSERT INTO rating (userid, bookid, rating, review_text) VALUES (?, ?, 5, ?) "
                + "ON DUPLICATE KEY UPDATE review_text = VALUES(review_text), rating = VALUES(rating)";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            statement.setString(3, reviewText);
            statement.executeUpdate();
        }
    }

    private List<Review_24110179> findReviews(int bookId) throws SQLException {
        String sql = "SELECT u.fullname, r.review_text FROM rating r JOIN users u ON u.id = r.userid "
                + "WHERE r.bookid = ? AND r.review_text IS NOT NULL ORDER BY u.fullname";
        List<Review_24110179> reviews = new ArrayList<>();
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    reviews.add(new Review_24110179(rs.getString("fullname"), rs.getString("review_text")));
                }
            }
        }
        return reviews;
    }

    private String findAuthorByPage(int page) throws SQLException {
        String sql = "SELECT a.author_name FROM author a JOIN book_author ba ON ba.author_id = a.author_id "
                + "GROUP BY a.author_id, a.author_name ORDER BY a.author_name LIMIT 1 OFFSET ?";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, page - 1);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getString("author_name") : null;
            }
        }
    }

    public List<Book_24110179> findAdminBooks(int page, int pageSize) throws SQLException {
        String sql = "SELECT b.bookid, b.isbn, b.title, COALESCE(a.author_name, '') author_name, b.publisher, b.price, "
                + "b.publish_date, b.cover_image, b.quantity, COUNT(r.userid) review_count "
                + "FROM books b LEFT JOIN book_author ba ON ba.bookid = b.bookid "
                + "LEFT JOIN author a ON a.author_id = ba.author_id LEFT JOIN rating r ON r.bookid = b.bookid "
                + "GROUP BY b.bookid, b.isbn, b.title, a.author_name, b.publisher, b.price, b.publish_date, b.cover_image, b.quantity "
                + "ORDER BY b.bookid DESC LIMIT ? OFFSET ?";
        List<Book_24110179> books = new ArrayList<>();
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, (page - 1) * pageSize);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book_24110179(rs.getInt("bookid"), rs.getInt("isbn"), rs.getString("title"),
                            rs.getString("author_name"), rs.getString("publisher"), rs.getBigDecimal("price"),
                            rs.getDate("publish_date"), rs.getString("cover_image"), rs.getInt("quantity"), rs.getInt("review_count")));
                }
            }
        }
        return books;
    }

    public int countBooks() throws SQLException {
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM books");
             ResultSet rs = statement.executeQuery()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public List<Author_24110179> findAuthors() throws SQLException {
        List<Author_24110179> authors = new ArrayList<>();
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT author_id, author_name FROM author ORDER BY author_name");
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                authors.add(new Author_24110179(rs.getInt("author_id"), rs.getString("author_name")));
            }
        }
        return authors;
    }

    public int createBook(Book_24110179 book, int authorId) throws SQLException {
        String sql = "INSERT INTO books (isbn, title, publisher, price, publish_date, cover_image, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            fillBookStatement(statement, book);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                int bookId = keys.getInt(1);
                setBookAuthor(connection, bookId, authorId);
                return bookId;
            }
        }
    }

    public void updateBook(Book_24110179 book, int authorId) throws SQLException {
        String sql = "UPDATE books SET isbn=?, title=?, publisher=?, price=?, publish_date=?, cover_image=?, quantity=? WHERE bookid=?";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            fillBookStatement(statement, book);
            statement.setInt(8, book.getBookId());
            statement.executeUpdate();
            setBookAuthor(connection, book.getBookId(), authorId);
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        try (Connection connection = DBConnection_24110179.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM rating WHERE bookid=?")) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM book_author WHERE bookid=?")) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE bookid=?")) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
        }
    }

    private void fillBookStatement(PreparedStatement statement, Book_24110179 book) throws SQLException {
        statement.setInt(1, book.getIsbn());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getPublisher());
        statement.setBigDecimal(4, book.getPrice());
        statement.setDate(5, book.getPublishDate());
        statement.setString(6, book.getCoverImage());
        statement.setInt(7, book.getQuantity());
    }

    private void setBookAuthor(Connection connection, int bookId, int authorId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM book_author WHERE bookid=?")) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        }
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO book_author (bookid, author_id) VALUES (?, ?)")) {
            statement.setInt(1, bookId);
            statement.setInt(2, authorId);
            statement.executeUpdate();
        }
    }
}
