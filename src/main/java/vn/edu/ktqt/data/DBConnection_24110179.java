package vn.edu.ktqt.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection_24110179 {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = DBConnection_24110179.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new ExceptionInInitializerError("Missing db.properties");
            }
            PROPERTIES.load(input);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private DBConnection_24110179() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PROPERTIES.getProperty("db.url"),
                PROPERTIES.getProperty("db.username"),
                PROPERTIES.getProperty("db.password")
        );
    }
}
