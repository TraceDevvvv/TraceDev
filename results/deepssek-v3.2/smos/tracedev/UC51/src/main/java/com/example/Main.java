package com.example;

import com.example.controller.RegistrationController;
import com.example.handler.RejectRegistrationHandler;
import com.example.model.RegistrationRequest;
import com.example.repository.RegistrationRepository;
import com.example.repository.RegistrationRepositoryImpl;
import com.example.service.RegistrationService;
import com.example.view.AdminDashboard;
import java.time.LocalDateTime;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup repository with sample data
        RegistrationRepository repository = new RegistrationRepositoryImpl();
        repository.save(new RegistrationRequest("req1", "Alice", LocalDateTime.now()));
        repository.save(new RegistrationRequest("req2", "Bob", LocalDateTime.now()));
        repository.save(new RegistrationRequest("req3", "Charlie", LocalDateTime.now()));

        // Setup handler and service
        RejectRegistrationHandler handler = new RejectRegistrationHandler(repository);
        RegistrationService service = new RegistrationService(handler, repository);

        // Setup dashboard and controller
        AdminDashboard dashboard = new AdminDashboard();
        RegistrationController controller = new RegistrationController(service, dashboard);

        // Create administrator
        Administrator admin = new Administrator("admin123", "John Doe");

        // Display initial pending registrations
        System.out.println("Initial state:");
        controller.getPendingRegistrations();

        // Administrator clicks reject button for a valid request
        System.out.println("\n--- Rejecting request 'req2' ---");
        admin.clickRejectButton(dashboard, "req2");
        controller.clickRejectButton("req2");

        // Display pending registrations after rejection
        System.out.println("\nState after rejection:");
        controller.getPendingRegistrations();

        // Attempt to reject with invalid request ID
        System.out.println("\n--- Attempting to reject with invalid request ID ---");
        controller.clickRejectButton("");

        // Administrator interrupts connection
        System.out.println("\n--- Interrupting connection ---");
        admin.interruptConnection(service);
    }
}