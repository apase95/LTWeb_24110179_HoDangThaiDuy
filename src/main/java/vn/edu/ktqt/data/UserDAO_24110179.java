package vn.edu.ktqt.data;

import vn.edu.ktqt.model.User_24110179;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO_24110179 {
    public User_24110179 findByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT id, email, fullname, is_admin FROM users WHERE email = ? AND passwd = ?";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User_24110179(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("fullname"),
                            resultSet.getBoolean("is_admin")
                    );
                }
            }
        }
        return null;
    }

    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public void createUser(String email, String fullname, String phone, String password) throws SQLException {
        String sql = "INSERT INTO users (email, fullname, phone, passwd, is_admin) VALUES (?, ?, ?, ?, 0)";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, fullname);
            statement.setString(3, phone == null || phone.isBlank() ? null : phone);
            statement.setString(4, password);
            statement.executeUpdate();
        }
    }

    public void updateLastLogin(int id) throws SQLException {
        String sql = "UPDATE users SET last_login = NOW() WHERE id = ?";
        try (Connection connection = DBConnection_24110179.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
