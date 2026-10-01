package vn.edu.ktqt.business;

import vn.edu.ktqt.model.BookDetail_24110179;
import vn.edu.ktqt.model.CartItem_24110179;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartService_24110179 {
    private final HomeService_24110179 homeService = new HomeService_24110179();

    public Map<Integer, CartItem_24110179> getCart(HttpSession session) {
        Map<Integer, CartItem_24110179> cart = (Map<Integer, CartItem_24110179>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public void add(HttpSession session, int bookId, int quantity) throws SQLException {
        BookDetail_24110179 detail = homeService.getBookDetail(bookId);
        if (detail == null) return;
        Map<Integer, CartItem_24110179> cart = getCart(session);
        CartItem_24110179 item = cart.get(bookId);
        int current = item == null ? 0 : item.getQuantity();
        int next = Math.min(detail.getBook().getQuantity(), Math.max(1, current + quantity));
        if (item == null) cart.put(bookId, new CartItem_24110179(detail.getBook(), next));
        else item.setQuantity(next);
    }

    public void update(HttpSession session, int bookId, int quantity) {
        CartItem_24110179 item = getCart(session).get(bookId);
        if (item == null) return;
        item.setQuantity(Math.min(item.getBook().getQuantity(), Math.max(1, quantity)));
    }

    public void remove(HttpSession session, int bookId) {
        getCart(session).remove(bookId);
    }

    public BigDecimal total(Collection<CartItem_24110179> items) {
        return items.stream().map(CartItem_24110179::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
