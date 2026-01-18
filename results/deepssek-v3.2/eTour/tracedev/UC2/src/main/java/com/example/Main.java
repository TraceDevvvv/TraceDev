package com.example;

import com.example.application.InsertCulturalGoodCommandHandler;
import com.example.authentication.AuthenticationService;
import com.example.authentication.SessionAuthenticationService;
import com.example.controller.CulturalGoodController;
import com.example.domain.CulturalGoodType;
import com.example.domain.GeoPoint;
import com.example.domain.Location;
import com.example.infrastructure.CulturalGoodRepository;
import com.example.infrastructure.CulturalGoodRepositoryImpl;
import com.example.notification.EmailNotificationService;
import com.example.notification.NotificationService;
import com.example.presentation.FormData;
import com.example.presentation.LocationData;
import com.example.presentation.ViewModel;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        CulturalGoodRepository repository = new CulturalGoodRepositoryImpl(null, null);
        NotificationService notificationService = new EmailNotificationService();
        AuthenticationService authService = new SessionAuthenticationService();
        
        // Setup application layer
        InsertCulturalGoodCommandHandler handler = new InsertCulturalGoodCommandHandler(repository, notificationService);
        CulturalGoodController controller = new CulturalGoodController(handler, authService);
        
        // Simulate the main success scenario
        System.out.println("=== Main Success Scenario ===");
        LocationData locData = new LocationData("123 Main St", 40.7128, -74.0060);
        FormData formData = new FormData("Ancient Vase", "A beautiful ancient vase", CulturalGoodType.ARTIFACT, locData);
        ViewModel result = controller.submitInsertForm(formData);
        System.out.println("Result: " + result.getMessage());
        
        // Simulate duplicate entry scenario
        System.out.println("\n=== Duplicate Entry Scenario ===");
        FormData duplicateData = new FormData("duplicate artifact", "This is a duplicate", CulturalGoodType.MONUMENT, locData);
        ViewModel duplicateResult = controller.submitInsertForm(duplicateData);
        System.out.println("Result: " + duplicateResult.getMessage());
        
        // Simulate connection error scenario
        System.out.println("\n=== Connection Error Scenario ===");
        FormData errorData = new FormData("connectionerror artifact", "Triggers connection error", CulturalGoodType.DOCUMENT, locData);
        ViewModel errorResult = controller.submitInsertForm(errorData);
        System.out.println("Result: " + errorResult.getMessage());
    }
}