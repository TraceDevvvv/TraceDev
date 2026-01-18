package com.example;

import com.example.boundary.PointOfRestWebUI;
import com.example.controller.ViewPointOfRestDetailsUseCaseController;
import com.example.entity.Session;
import com.example.service.AuthenticationService;
import com.example.infrastructure.ConnectionManager;
import com.example.infrastructure.ETOURAPIClient;
import com.example.infrastructure.ETOURAPIRepository;
import java.time.LocalDateTime;

/**
 * Main class to run the application and simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ConnectionManager connMgr = new ConnectionManager("https://api.etour.example.com");
        ETOURAPIClient apiClient = new ETOURAPIClient("https://api.etour.example.com");
        ETOURAPIRepository repository = new ETOURAPIRepository(apiClient, connMgr);

        // Setup controller
        ViewPointOfRestDetailsUseCaseController controller = new ViewPointOfRestDetailsUseCaseController(repository);

        // Setup authentication (simulate a logged-in session)
        Session session = new Session(1, LocalDateTime.now().plusHours(1));
        AuthenticationService authService = new AuthenticationService(session);

        // Setup UI boundary
        PointOfRestWebUI ui = new PointOfRestWebUI(authService, controller);

        // Run the use case flow for a sample point of rest ID
        System.out.println("=== Starting View Point of Rest Details Use Case ===");
        ui.runViewDetailsFlow(123);
        System.out.println("=== Use Case Completed ===");
    }
}