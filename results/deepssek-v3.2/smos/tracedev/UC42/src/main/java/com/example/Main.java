
package com.example;

import com.example.presentation.Presentation;
import com.example.controller.JustificationDeleteController;
import com.example.justification.*;
import com.example.audit.AuditLogger;
import com.example.session.SessionManager;
import com.example.session.UserSession;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main class to demonstrate the flow.
 * Creates all necessary components and runs a demo scenario.
 */
public class Main {
    public static void main(String[] args) {
        // Setup data source (in-memory H2 for demonstration)
        DataSource dataSource = createDataSource();

        // Create repository
        JustificationRepository repository = new JustificationRepositoryImpl(dataSource);
        // Create audit logger
        AuditLogger auditLogger = new AuditLogger("audit.log");
        // Create session manager and simulate login
        SessionManager sessionManager = new SessionManager();
        UserSession adminSession = new UserSession(1, "admin", "Administrator");
        sessionManager.setCurrentUser(adminSession);

        // Create service
        JustificationService service = new JustificationServiceImpl(repository, auditLogger);
        // Create controller
        JustificationDeleteController controller = new JustificationDeleteController(service, auditLogger, sessionManager);
    }

    private static DataSource createDataSource() {
        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", username, password);
            }

            @Override
            public java.io.PrintWriter getLogWriter() throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLogWriter(java.io.PrintWriter out) throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public int getLoginTimeout() throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public java.util.logging.Logger getParentLogger() {
                throw new UnsupportedOperationException();
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                throw new UnsupportedOperationException();
            }
        };
    }
}
