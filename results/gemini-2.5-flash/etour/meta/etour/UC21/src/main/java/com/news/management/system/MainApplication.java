package com.news.management.system;

import com.news.management.system.cli.NewsInsertionCLI;
import com.news.management.system.dao.NewsArticleDAO;
import com.news.management.system.dao.NewsArticleDAOImpl;
import com.news.management.system.service.NewsArticleService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main application class to initialize and start the News Insertion CLI.
 * This class acts as the entry point for the application, setting up
 * dependencies and orchestrating the flow.
 */
public class MainApplication {

    public static void main(String[] args) {
        // In a real application, database connection details would come from a config file.
        // For this in-memory simulation, the Connection object is a placeholder.
        Connection connection = null;
        try {
            // Simulate a database connection. In a real scenario, this would connect to a DB.
            // For an in-memory H2 database:
            // connection = DriverManager.getConnection("jdbc:h2:mem:newsdb;DB_CLOSE_DELAY=-1", "sa", "");
            // For this example, we just pass null as NewsArticleDAOImpl uses an in-memory map.
            // If a real DB were used, you'd also need to create tables here or via a migration tool.
            System.out.println("Simulating database connection setup...");
            // connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", ""); // Example for H2 in-memory DB
            // For this specific in-memory DAO, the connection object isn't strictly used,
            // but we pass it to adhere to the DAO's constructor signature.
            // In a real app, you'd handle this connection properly.

            // Initialize DAO layer
            NewsArticleDAO newsArticleDAO = new NewsArticleDAOImpl(connection);

            // Initialize Service layer
            NewsArticleService newsArticleService = new NewsArticleService(newsArticleDAO);

            // Initialize CLI layer
            NewsInsertionCLI newsInsertionCLI = new NewsInsertionCLI(newsArticleService);

            // Start the CLI application
            newsInsertionCLI.start();

        } catch (Exception e) { // Catching generic Exception for broader error handling at application startup
            System.err.println("Application failed to start due to an error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // In a real application, ensure the connection is closed.
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }
}