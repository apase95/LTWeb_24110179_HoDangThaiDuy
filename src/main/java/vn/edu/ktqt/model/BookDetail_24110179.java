package vn.edu.ktqt.model;

import java.util.List;

public class BookDetail_24110179 {
    private final Book_24110179 book;
    private final List<Review_24110179> reviews;

    public BookDetail_24110179(Book_24110179 book, List<Review_24110179> reviews) {
        this.book = book;
        this.reviews = reviews;
    }

    public Book_24110179 getBook() { return book; }
    public List<Review_24110179> getReviews() { return reviews; }
}
