package vn.edu.ktqt.business;

import vn.edu.ktqt.data.OrderDAO_24110179;
import vn.edu.ktqt.model.CartItem_24110179;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;

public class CheckoutService_24110179 {
    private final OrderDAO_24110179 orderDAO = new OrderDAO_24110179();

    public int checkoutCod(Integer userId, String name, String phone, String address,
                           BigDecimal total, Collection<CartItem_24110179> items) throws SQLException {
        if (items.isEmpty()) throw new IllegalArgumentException("Giỏ hàng trống");
        return orderDAO.createCodOrder(userId, name, phone, address, total, items);
    }
}
