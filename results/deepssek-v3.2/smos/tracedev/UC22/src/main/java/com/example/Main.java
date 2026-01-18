
package com.example;

import com.example.model.Administrator;
import com.example.ui.ManagementUI;
import com.example.controller.TeachingManagementController;
import com.example.repository.TeachingRepository;
import com.example.repository.TeachingRepositoryImpl;
import com.example.service.SMOSService;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Main class to simulate the runnable system.
 * Creates necessary components and runs the sequence diagram scenario.
 */
public class Main {
    public static void main(String[] args) {
        // Setup an in-memory H2 database for demonstration.
        DataSource dataSource = createDataSource();
        initializeDatabase(dataSource);

        // Create components.
        TeachingRepository teachingRepository = new TeachingRepositoryImpl(dataSource);
        SMOSService smosService = new SMOSService();
        TeachingManagementController controller = new TeachingManagementController(teachingRepository, smosService);
        ManagementUI ui = new ManagementUI(controller);
        Administrator admin = new Administrator("admin1", "Administrator");

        // Simulate the sequence diagram flow.
        ui.navigateToManagementScreen();
        ui.requestManagementView(admin);
    }

    /**
     * Creates an H2 in-memory DataSource for demonstration.
     * @return Configured DataSource.
     */
    private static DataSource createDataSource() {
        return new javax.sql.DataSource() {
            @Override
            public Connection getConnection() throws java.sql.SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            }

            @Override
            public Connection getConnection(String username, String password) throws java.sql.SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", username, password);
            }

            @Override
            public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) throws java.sql.SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public int getLoginTimeout() throws java.sql.SQLException {
                return 0;
            }

            @Override
            public void setLoginTimeout(int seconds) throws java.sql.SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws java.sql.SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws java.sql.SQLException {
                return false;
            }
        };
    }

    /**
     * Initializes the database with sample teaching records.
     * @param dataSource The DataSource to use for connection.
     */
    private static void initializeDatabase(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE teachings (" +
                    "id BIGINT PRIMARY KEY, " +
                    "title VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "status VARCHAR(50))");
            stmt.execute("INSERT INTO teachings (id, title, description, status) VALUES " +
                    "(1, 'Mathematics 101', 'Introductory mathematics course', 'Active'), " +
                    "(2, 'Physics 201', 'Advanced physics course', 'Inactive'), " +
                    "(3, 'Chemistry 150', 'General chemistry', 'Active')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
