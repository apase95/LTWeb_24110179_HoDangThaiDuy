package vn.edu.ktqt.model;

import java.util.List;

public class AuthorBooks_24110179 {
    private final String authorName;
    private final List<Book_24110179> books;

    public AuthorBooks_24110179(String authorName, List<Book_24110179> books) {
        this.authorName = authorName;
        this.books = books;
    }

    public String getAuthorName() { return authorName; }
    public List<Book_24110179> getBooks() { return books; }
}
