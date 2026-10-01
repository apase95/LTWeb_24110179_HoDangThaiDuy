package vn.edu.ktqt.model;

import java.math.BigDecimal;

public class CartItem_24110179 {
    private final Book_24110179 book;
    private int quantity;

    public CartItem_24110179(Book_24110179 book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book_24110179 getBook() { return book; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getSubTotal() { return book.getPrice().multiply(BigDecimal.valueOf(quantity)); }
}
