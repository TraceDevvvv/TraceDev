package com.example;

import com.example.authentication.AuthenticationService;
import com.example.authentication.Credentials;
import com.example.application.UpdateRefreshmentController;
import com.example.data.RefreshmentRepositoryImpl;
import com.example.infrastructure.ETOURServerConnectionImpl;
import com.example.presentation.PointRestaurantBoundary;
import com.example.service.RefreshmentService;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Update Refreshment Use Case ===");
        
        // Setup infrastructure
        ETOURServerConnectionImpl connection = new ETOURServerConnectionImpl();
        RefreshmentRepositoryImpl repository = new RefreshmentRepositoryImpl(connection);
        RefreshmentService service = new RefreshmentService(repository, connection);
        AuthenticationService authService = new AuthenticationService();
        
        // Create controller
        UpdateRefreshmentController controller = new UpdateRefreshmentController(repository, service, authService);
        
        // Simulate login (from sequence diagram)
        PointRestaurantBoundary boundary = new PointRestaurantBoundary();
        boundary.login(new Credentials("operator", "password"));
        boundary.enablesInformationFunctionality();
        
        // Execute the update flow
        controller.execute();
        
        System.out.println("=== Use Case Completed ===");
    }
}