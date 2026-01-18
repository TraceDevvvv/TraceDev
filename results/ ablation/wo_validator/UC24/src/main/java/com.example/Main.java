package com.example;

import com.example.presentation.AgencyOperator;
import com.example.presentation.FeedbackViewController;
import com.example.business.SiteService;
import com.example.business.FeedbackService;
import com.example.data.DatabaseSiteRepository;
import com.example.data.DatabaseFeedbackRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main class to demonstrate the runnable system.
 * It sets up the components and simulates the interactions described in the Sequence Diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup a simple in‑memory H2 database for demonstration.
        DataSource dataSource = new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", username, password);
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                throw new SQLException("Not a wrapper");
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public java.io.PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) throws SQLException {
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
                throw new java.sql.SQLFeatureNotSupportedException();
            }
        };

        // Create repositories with the data source.
        DatabaseSiteRepository siteRepository = new DatabaseSiteRepository(dataSource);
        DatabaseFeedbackRepository feedbackRepository = new DatabaseFeedbackRepository(dataSource);

        // Create serv (business logic layer).
        SiteService siteService = new SiteService(siteRepository);
        FeedbackService feedbackService = new FeedbackService(feedbackRepository);

        // Create the agency operator (assume already authenticated).
        AgencyOperator operator = new AgencyOperator("john_doe");

        // Create the view controller (presentation layer).
        FeedbackViewController controller = new FeedbackViewController(siteService, feedbackService, operator);

        // Simulate the sequence diagram flow.
        System.out.println("=== Starting Feedback Viewing Use Case ===");
        controller.activateSiteView();                     // Step 1
        controller.handleSiteSelection(1);                 // Steps 2 & 3 (select site ID 1)
        System.out.println("=== Use Case Complete ===");
    }
}