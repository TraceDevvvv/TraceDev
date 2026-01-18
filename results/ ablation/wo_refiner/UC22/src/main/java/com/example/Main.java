package com.example;

import com.example.controller.NewsDeletionController;
import com.example.exception.ExceptionHandler;
import com.example.model.AgencyOperator;
import com.example.repository.NewsRepository;
import com.example.repository.NewsRepositoryImpl;
import com.example.security.SessionManager;
import com.example.service.NewsService;

/**
 * Main class to demonstrate the news deletion flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting News Deletion System ===\n");
        
        // Initialize dependencies
        AgencyOperator operator = new AgencyOperator("OP001", "John Doe");
        SessionManager sessionManager = new SessionManager();
        NewsRepository newsRepository = new NewsRepositoryImpl();
        NewsService newsService = new NewsService(newsRepository);
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        
        // Create controller
        NewsDeletionController controller = new NewsDeletionController(
            sessionManager, newsService, exceptionHandler, operator
        );
        
        // Demonstrate normal flow
        System.out.println("\n=== Normal Flow Demonstration ===");
        controller.activateDeleteNewsFunction();
        
        // Demonstrate exception flow (REQ-015 robustness demonstration)
        System.out.println("\n=== Exception Flow Demonstration ===");
        System.out.println("Testing connection interruption scenario...");
        
        // Create a new controller with a modified repository that throws exception
        NewsRepository failingRepository = new NewsRepositoryImpl() {
            @Override
            public void delete(int newsId) {
                // Always throw exception to simulate connection loss
                throw new RuntimeException("DatabaseException: Connection Lost");
            }
        };
        
        NewsService failingService = new NewsService(failingRepository);
        NewsDeletionController exceptionController = new NewsDeletionController(
            sessionManager, failingService, exceptionHandler, operator
        );
        
        // This will trigger the exception handling flow
        exceptionController.submitDeleteForm(999);
        
        System.out.println("\n=== System Demonstration Complete ===");
    }
}