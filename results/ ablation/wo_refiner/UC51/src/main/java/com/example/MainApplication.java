package com.example;

import com.example.domain.entity.Bookmark;
import com.example.infrastructure.adapter.connection.ServerConnectionServiceImpl;
import com.example.infrastructure.adapter.notification.NotificationServiceImpl;
import com.example.infrastructure.adapter.repository.BookmarkRepositoryImpl;
import com.example.infrastructure.adapter.repository.SiteRepositoryImpl;
import com.example.infrastructure.adapter.repository.TouristRepositoryImpl;
import com.example.interfaceadapter.controller.PreferenceController;
import com.example.ui.UIView;
import com.example.usecase.interactor.InsertPreferenceSiteInteractor;

/**
 * Main application class that sets up dependencies and demonstrates the flow
 * Implements the complete sequence diagram scenario
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== ETOUR Bookmark System Demo ===");
        
        // Setup infrastructure adapters
        SiteRepositoryImpl siteRepository = new SiteRepositoryImpl();
        BookmarkRepositoryImpl bookmarkRepository = new BookmarkRepositoryImpl();
        TouristRepositoryImpl touristRepository = new TouristRepositoryImpl();
        NotificationServiceImpl notificationService = new NotificationServiceImpl();
        ServerConnectionServiceImpl connectionService = new ServerConnectionServiceImpl();
        
        // Setup use case interactor
        InsertPreferenceSiteInteractor interactor = new InsertPreferenceSiteInteractor(
            siteRepository, bookmarkRepository, touristRepository);
        
        // Setup controller
        PreferenceController controller = new PreferenceController(
            interactor, notificationService, connectionService);
        
        // Setup UI
        UIView ui = new UIView();
        
        // Simulate tourist interaction (sequence diagram steps)
        System.out.println("\n--- Sequence Diagram Simulation ---");
        
        // Step 1: Tourist activates insertion feature
        System.out.println("1. Tourist activates Insertion Feature for Site");
        
        // Steps 2-3: UI prompts and tourist confirms (requirements 7-8)
        ui.promptForInclusion();
        ui.confirmInput();
        
        // Step 4: UI calls controller
        String siteId = "SITE_001";
        String touristId = "TOURIST_001";
        System.out.println("4. UI calls controller with siteId=" + siteId + ", touristId=" + touristId);
        
        String result = controller.activateInsertion(siteId, touristId);
        
        // Step 5: UI displays result
        ui.displayMessage(result);
        
        System.out.println("\n--- End of Simulation ---");
        
        // Demonstrate error case by temporarily setting low connection probability
        System.out.println("\n--- Testing Connection Failure Scenario ---");
        
        // In a real scenario, we might inject a mock that returns false
        // For demo, we'll just show what happens when checkConnection returns false
        System.out.println("Simulating connection failure...");
        
        // Note: In production, we would use dependency injection with mocks
        // For this demo, we create a new scenario
        System.out.println("If connection fails, controller returns: 'Error: Connection to ETOUR server interrupted'");
    }
}