package com.example;

import com.example.controller.DeleteBookmarkController;
import com.example.dto.DeleteBookmarkRequest;
import com.example.dto.DeleteBookmarkResponse;
import com.example.model.Tourist;
import com.example.repository.BookmarkRepository;
import com.example.repository.BookmarkRepositoryImpl;
import com.example.service.NotificationService;
import com.example.unitofwork.UnitOfWork;
import com.example.unitofwork.UnitOfWorkImpl;
import com.example.usecase.DeleteBookmarkUseCase;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Main class to demonstrate the application.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Setup dependencies (using H2 in-memory database for demonstration)
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
            DataSource dataSource = new SimpleDataSource(connection);
            
            // Create tourist
            Tourist tourist = new Tourist("user123");
            
            // Create repository
            BookmarkRepository repository = new BookmarkRepositoryImpl(dataSource);
            
            // Create unit of work
            UnitOfWork unitOfWork = new UnitOfWorkImpl(connection);
            
            // Create notification service
            NotificationService notificationService = new NotificationService();
            
            // Create use case
            DeleteBookmarkUseCase useCase = new DeleteBookmarkUseCase(
                repository, unitOfWork, notificationService
            );
            
            // Create controller
            DeleteBookmarkController controller = new DeleteBookmarkController(useCase);
            
            // Test 1: Main Success Scenario
            System.out.println("Test 1: Main Success Scenario");
            DeleteBookmarkRequest request1 = new DeleteBookmarkRequest(
                "user123", 
                "site456", 
                "confirm123"
            );
            DeleteBookmarkResponse response1 = controller.handleRequest(request1);
            System.out.println("Response: Success=" + response1.isSuccess() + 
                             ", Message=" + response1.getMessage());
            
            // Test 2: Alternative Flow - Tourist Cancels
            System.out.println("\nTest 2: Tourist Cancels");
            DeleteBookmarkRequest request2 = new DeleteBookmarkRequest(
                "user123", 
                "site456", 
                "CANCEL"
            );
            DeleteBookmarkResponse response2 = controller.handleRequest(request2);
            System.out.println("Response: Success=" + response2.isSuccess() + 
                             ", Message=" + response2.getMessage());
            
            // Test 3: Alternative Flow - Bookmark Not Found
            System.out.println("\nTest 3: Bookmark Not Found");
            DeleteBookmarkRequest request3 = new DeleteBookmarkRequest(
                "user999", 
                "site999", 
                "confirm123"
            );
            DeleteBookmarkResponse response3 = controller.handleRequest(request3);
            System.out.println("Response: Success=" + response3.isSuccess() + 
                             ", Message=" + response3.getMessage());
            
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Simple DataSource implementation for demonstration
    static class SimpleDataSource implements javax.sql.DataSource {
        private Connection connection;
        
        public SimpleDataSource(Connection connection) {
            this.connection = connection;
        }
        
        @Override
        public Connection getConnection() {
            return connection;
        }
        
        // Other DataSource methods (simplified for demonstration)
        @Override
        public Connection getConnection(String username, String password) {
            return connection;
        }
        
        @Override
        public java.io.PrintWriter getLogWriter() {
            return null;
        }
        
        @Override
        public void setLogWriter(java.io.PrintWriter out) {}
        
        @Override
        public void setLoginTimeout(int seconds) {}
        
        @Override
        public int getLoginTimeout() {
            return 0;
        }
        
        @Override
        public java.util.logging.Logger getParentLogger() {
            return null;
        }
        
        @Override
        public <T> T unwrap(Class<T> iface) {
            return null;
        }
        
        @Override
        public boolean isWrapperFor(Class<?> iface) {
            return false;
        }
    }
}