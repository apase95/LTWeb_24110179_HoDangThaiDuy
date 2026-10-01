package vn.edu.ktqt.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Book_24110179 {
    private final int bookId;
    private final int isbn;
    private final String title;
    private final String authorName;
    private final String publisher;
    private final BigDecimal price;
    private final Date publishDate;
    private final String coverImage;
    private final int quantity;
    private final int reviewCount;

    public Book_24110179(int bookId, int isbn, String title, String authorName, String publisher,
                         BigDecimal price, Date publishDate, String coverImage, int quantity, int reviewCount) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.authorName = authorName;
        this.publisher = publisher;
        this.price = price;
        this.publishDate = publishDate;
        this.coverImage = coverImage;
        this.quantity = quantity;
        this.reviewCount = reviewCount;
    }

    public int getBookId() { return bookId; }
    public int getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthorName() { return authorName; }
    public String getPublisher() { return publisher; }
    public BigDecimal getPrice() { return price; }
    public Date getPublishDate() { return publishDate; }
    public String getCoverImage() { return coverImage; }
    public int getQuantity() { return quantity; }
    public int getReviewCount() { return reviewCount; }
}
