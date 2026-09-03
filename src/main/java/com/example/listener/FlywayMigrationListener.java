package com.example.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

@WebListener
public class FlywayMigrationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== FlywayMigrationListener started ===");
        try {
            Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/servlet_db?useSSL=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true", "root", "123")
                .load();
            flyway.migrate();
            System.out.println("Flyway migration completed!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Flyway migration failed: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}