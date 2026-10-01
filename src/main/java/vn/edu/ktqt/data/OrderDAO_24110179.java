package vn.edu.ktqt.data;

import vn.edu.ktqt.model.CartItem_24110179;
import vn.edu.ktqt.model.Order_24110179;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

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

    public List<Order_24110179> findOrders(Integer userId, String status) throws SQLException {
        boolean hasStatus = status != null && !status.isBlank();
        String sql = "SELECT order_id, customer_name, customer_phone, customer_address, payment_method, total_amount, status, created_at "
                + "FROM orders WHERE (? IS NULL OR user_id = ?) "
                + (hasStatus ? "AND status = ? " : "")
                + "ORDER BY created_at DESC";
        List<Order_24110179> orders = new ArrayList<>();
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (userId == null) {
                statement.setNull(1, java.sql.Types.INTEGER);
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(1, userId);
                statement.setInt(2, userId);
            }
            if (hasStatus) statement.setString(3, status);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order_24110179(
                            rs.getInt("order_id"), rs.getString("customer_name"), rs.getString("customer_phone"),
                            rs.getString("customer_address"), rs.getString("payment_method"), rs.getBigDecimal("total_amount"),
                            rs.getString("status"), rs.getTimestamp("created_at")
                    ));
                }
            }
        }
        return orders;
    }
}
