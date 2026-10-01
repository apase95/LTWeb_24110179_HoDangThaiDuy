package vn.edu.ktqt.data;

import vn.edu.ktqt.model.CartItem_24110179;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

public class OrderDAO_24110179 {
    public int createCodOrder(Integer userId, String name, String phone, String address,
                              BigDecimal total, Collection<CartItem_24110179> items) throws SQLException {
        try (Connection connection = DBConnection_24110179.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int orderId;
                String orderSql = "INSERT INTO orders (user_id, customer_name, customer_phone, customer_address, payment_method, total_amount) VALUES (?, ?, ?, ?, 'COD', ?)";
                try (PreparedStatement statement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                    if (userId == null) statement.setNull(1, java.sql.Types.INTEGER); else statement.setInt(1, userId);
                    statement.setString(2, name);
                    statement.setString(3, phone);
                    statement.setString(4, address);
                    statement.setBigDecimal(5, total);
                    statement.executeUpdate();
                    try (ResultSet keys = statement.getGeneratedKeys()) {
                        keys.next();
                        orderId = keys.getInt(1);
                    }
                }
                String itemSql = "INSERT INTO order_items (order_id, bookid, quantity, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(itemSql)) {
                    for (CartItem_24110179 item : items) {
                        statement.setInt(1, orderId);
                        statement.setInt(2, item.getBook().getBookId());
                        statement.setInt(3, item.getQuantity());
                        statement.setBigDecimal(4, item.getBook().getPrice());
                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
                connection.commit();
                return orderId;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }
}
