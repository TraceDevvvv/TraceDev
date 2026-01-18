package com.example.agencyapp;

import com.example.agencyapp.controller.PasswordChangeController;
import com.example.agencyapp.entity.User;
import com.example.agencyapp.repository.InMemoryUserRepository;
import com.example.agencyapp.repository.UserRepository;
import com.example.agencyapp.service.PasswordChangeService;
import java.util.Scanner;

/**
 * Main class to simulate the password change use case.
 * This is a console-based simulation for demonstration.
 */
public class Main {
    public static void main(String[] args) {
        // Setup repository and service
        UserRepository userRepository = new InMemoryUserRepository();
        PasswordChangeService service = new PasswordChangeService(userRepository);

        // Simulate logged-in Agency Operator (Entry Condition: Agency Operator IS logged)
        User loggedInUser = ((InMemoryUserRepository) userRepository).findByUsername("operator1");
        System.out.println("Logged in as: " + loggedInUser.getUsername() + " (Role: " + loggedInUser.getRole() + ")");
        System.out.println("Current password is: oldPass123");

        // Create controller
        PasswordChangeController controller = new PasswordChangeController(service, loggedInUser);

        // Simulate the flow of events
        Scanner scanner = new Scanner(System.in);

        // Step 1 & 2: Operator chooses to change password and presses button
        System.out.println("\n--- Password Change Simulation ---");
        System.out.println("Step 1-2: Operator chooses to change password and presses button.");

        // Step 3: System uploads form (simulated by prompting for inputs)
        System.out.println("Step 3: System displays password change form.");

        // Step 4: Operator enters new password
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();

        // Step 5 & 6: Operator confirms and saves changes (processed in controller)
        System.out.println("Step 5-6: Operator confirms and saves changes.");
        String result = controller.processPasswordChange(currentPassword, newPassword, confirmPassword);

        // Exit Conditions
        System.out.println("\n--- Result ---");
        System.out.println(result);
        if (result.contains("successfully")) {
            System.out.println("Exit Condition: The system confirms the success of Operation.");
        } else if (result.contains("connection interruption") || result.contains("system error")) {
            System.out.println("Exit Condition: The connection to the server is interrupted.");
        }

        // Verify password update
        System.out.println("\nVerification: Current password in system is now: " + loggedInUser.getPassword());
        scanner.close();
    }
}