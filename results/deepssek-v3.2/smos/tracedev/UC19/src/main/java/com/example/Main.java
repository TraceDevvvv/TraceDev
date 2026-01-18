package com.example;

import com.example.controller.ViewAddressDetailsController;
import com.example.repository.AddressRepository;
import com.example.view.AddressDetailView;
import com.example.session.SessionManager;
import com.example.model.User;

/**
 * Main class to simulate the sequence diagram flow.
 * This demonstrates the runnable system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting View Address Details Use Case ===\n");

        // Create components
        AddressRepository repository = new AddressRepository();
        AddressDetailView view = new AddressDetailView();
        SessionManager sessionManager = new SessionManager();
        ViewAddressDetailsController controller = new ViewAddressDetailsController(repository, view, sessionManager);
        view.setController(controller);

        // Simulate user login and previous use case
        User admin = new User(1L, "admin");
        sessionManager.login(admin);
        sessionManager.setPreviousUseCase("ViewingLenchIndirizzi"); // Set entry condition

        // Simulate Administrator clicking ShowAddressDetailsButton (addressId = 101)
        // This corresponds to message m1: clicks ShowAddressDetailsButton from Admin to Screen
        System.out.println("Administrator clicks ShowAddressDetailsButton for addressId=101\n");
        view.clicksShowAddressDetailsButton(101L);

        // Test error scenario: connection interrupted (simulated via random chance in SMOSServer)
        System.out.println("\n--- Testing connection error scenario ---\n");
        view.clicksShowAddressDetailsButton(102L); // May trigger connection error

        // Test session expired scenario
        System.out.println("\n--- Testing session expired scenario ---\n");
        sessionManager.setPreviousUseCase("SomeOtherUseCase"); // Violate entry condition
        view.clicksShowAddressDetailsButton(103L);

        // Test not logged in scenario
        System.out.println("\n--- Testing not logged in scenario ---\n");
        sessionManager.logout();
        view.clicksShowAddressDetailsButton(104L);

        System.out.println("\n=== End of simulation ===");
    }
}