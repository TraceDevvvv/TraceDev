package com.example;

import com.example.auth.AuthenticationService;
import com.example.connection.ConnectionManager;
import com.example.controller.RegistrationRequestListController;
import com.example.repository.IRegistrationRequestRepository;
import com.example.repository.RegistrationRequestRepositoryImpl;
import com.example.service.RegistrationRequestService;
import com.example.service.RegistrationRequestServiceImpl;
import com.example.view.RegistrationRequestListView;
import com.example.dto.RegistrationRequestDTO;
import java.util.List;

/**
 * Main application class to demonstrate the system.
 * This class sets up dependencies and simulates the administrator interaction.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Initialize components
        IRegistrationRequestRepository repository = new RegistrationRequestRepositoryImpl();
        RegistrationRequestService service = new RegistrationRequestServiceImpl(repository);
        RegistrationRequestListView view = new RegistrationRequestListView();
        AuthenticationService authService = new AuthenticationService();
        ConnectionManager connectionManager = new ConnectionManager();
        
        // Create controller with dependencies
        RegistrationRequestListController controller = new RegistrationRequestListController(
            service, view, authService, connectionManager
        );

        System.out.println("=== Administrator Views Registration Request List ===\n");
        
        // Simulate administrator clicking "View Request List" button
        System.out.println("1. Administrator clicks 'View Request List' button...");
        controller.handleViewRequestListClick();
        
        System.out.println("\n--- Simulating periodic refresh for accurate display ---");
        // Demonstrate quality requirement: accurate display through refresh
        List<RegistrationRequestDTO> refreshedList = service.refreshRequestList();
        System.out.println("Refreshed request list contains " + refreshedList.size() + " requests.");
        
        System.out.println("\n--- Simulating connection interruption (exit condition) ---");
        // Simulate user interrupting connection
        controller.cancelOrDisconnect();
        
        System.out.println("\n=== Application Demo Complete ===");
    }
}