package com.example.newsapp;

import com.example.newsapp.application.usecase.DeleteNewsUseCase;
import com.example.newsapp.data.repository.INewsRepository;
import com.example.newsapp.data.repository.NewsRepository;
import com.example.newsapp.infrastructure.service.SystemNotificationService;
import com.example.newsapp.presentation.NewsController;

import java.util.Scanner;

/**
 * Main application class to demonstrate the News Deletion use case.
 * This class sets up the dependencies and simulates user interaction.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Infrastructure/Cross-Cutting Concerns
        SystemNotificationService systemNotificationService = new SystemNotificationService();

        // 2. Data Access Layer
        // Using Object as a placeholder for DatabaseConnection, as specified in the Class Diagram
        Object dummyDataSource = new Object() {
            @Override
            public String toString() {
                return "MockDatabaseConnection";
            }
        };
        NewsRepository newsRepository = new NewsRepository(dummyDataSource); // Concrete implementation
        INewsRepository iNewsRepository = newsRepository; // Use the interface

        // 3. Application Layer
        DeleteNewsUseCase deleteNewsUseCase = new DeleteNewsUseCase(iNewsRepository);

        // 4. Presentation Layer
        NewsController newsController = new NewsController(deleteNewsUseCase, systemNotificationService);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the News Management System (Delete News Module)");

        // --- Scenario 1: Successful Deletion ---
        System.out.println("\n--- Running Scenario 1: Successful Deletion ---");
        // Reset network failure simulation for this scenario
        newsRepository.setSimulateNetworkFailure(false);
        newsController.simulateUserInteraction(scanner, false); // No network failure

        // --- Scenario 2: Network Interruption ---
        System.out.println("\n--- Running Scenario 2: Deletion with Network Interruption ---");
        // Ensure there's still news to delete for this scenario
        // If news1 was deleted, let's try to delete news2
        newsRepository = new NewsRepository(dummyDataSource); // Re-initialize repo to restore data for this scenario
        iNewsRepository = newsRepository;
        deleteNewsUseCase = new DeleteNewsUseCase(iNewsRepository);
        newsController = new NewsController(deleteNewsUseCase, systemNotificationService); // Re-create controller with new use case

        newsController.simulateUserInteraction(scanner, true); // Simulate network failure

        // --- Scenario 3: Cancellation ---
        System.out.println("\n--- Running Scenario 3: Deletion Cancelled ---");
        // Re-initialize repo to restore data for this scenario
        newsRepository = new NewsRepository(dummyDataSource);
        iNewsRepository = newsRepository;
        deleteNewsUseCase = new DeleteNewsUseCase(iNewsRepository);
        newsController = new NewsController(deleteNewsUseCase, systemNotificationService); // Re-create controller with new use case
        newsController.simulateUserInteraction(scanner, false); // No network failure, but user cancels

        scanner.close();
        System.out.println("\nApplication demonstration finished.");
    }
}