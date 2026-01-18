package com.system;

import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 * This class demonstrates the entire interaction.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create an administrator and log in (Login Flow prior to main sequence)
        Administrator admin = new Administrator("ADM001", "SuperAdmin", "U999", "admin", "admin@system.com");
        admin.login();

        // 2. Create boundary with admin reference
        UserManagerBoundary boundary = new UserManagerBoundary(admin);

        // 3. Simulate button click (main sequence)
        System.out.println("\n--- Simulating User Manager button click ---");
        boundary.handleUserManagerButtonClick();

        // 4. Simulate connection interruption scenario
        System.out.println("\n--- Simulating connection interruption ---");
        Archive archive = new Archive();
        archive.setConnectionStatus("INTERRUPTED");
        // Re-run with interrupted connection
        DisplayUserListController controller = new DisplayUserListController();
        List<User> users = controller.execute();
        if (users == null || users.isEmpty()) {
            System.out.println("No users retrieved due to connection issue.");
        }

        // 5. Logout
        admin.logout();
        boundary.setButtonEnabled(false);
        System.out.println("\n--- After logout ---");
        boundary.handleUserManagerButtonClick();
    }
}