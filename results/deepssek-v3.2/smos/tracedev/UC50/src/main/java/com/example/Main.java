package com.example;

import java.util.Date;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup repositories
        UserRepository userRepo = new UserRepositoryImpl();
        RegistrationRepository regRepo = new RegistrationRepositoryImpl();

        // Create a pending user
        User user = new User("user123", "john_doe", UserStatus.PENDING);
        userRepo.save(user);

        // Create a pending registration for that user
        Registration reg = new Registration("req001", "user123", new Date());
        ((RegistrationRepositoryImpl) regRepo).addRegistration(reg);

        // Create serv and views
        RegistrationService regService = new RegistrationService(userRepo, regRepo);
        RegistrationListView listView = new RegistrationListViewImpl();
        RegistrationListViewController listViewController = new RegistrationListViewController(regService, listView);
        SMOSServerConnection serverConnection = new SMOSServerConnection();
        RegistrationController controller = new RegistrationController(regService, listViewController, serverConnection);
        Administrator admin = new Administrator(controller, serverConnection);

        System.out.println("Initial state:");
        listViewController.refreshView();

        // Simulate accepting the registration
        System.out.println("\n--- Accepting registration req001 ---");
        admin.triggerAcceptRequest("req001");

        System.out.println("\nAfter acceptance:");
        listViewController.refreshView();

        // Simulate interruption scenario
        System.out.println("\n--- Simulating connection interruption ---");
        admin.interruptConnection();
        // Try another operation (should fail due to connection loss)
        admin.triggerAcceptRequest("req002"); // nonexistent request
    }
}