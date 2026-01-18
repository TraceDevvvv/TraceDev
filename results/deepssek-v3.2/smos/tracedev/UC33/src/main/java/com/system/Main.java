package com.system;

import java.util.Date;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Delay Elimination System Demo ===\n");

        // Initialize components.
        ArchiveRepository archiveRepo = new ArchiveRepository();
        StudentRepository studentRepo = new StudentRepository();
        SMOSServer smosServer = new SMOSServer();
        DelayEliminationController controller = new DelayEliminationController(
                archiveRepo, studentRepo, smosServer);
        Administrator admin = new Administrator("ADM001", "John Doe");

        // Simulate sequence diagram.
        try {
            // 1. Administrator logs in.
            admin.login();

            // 2. Entry condition: select case.
            controller.selectCase("SplitTaTtAlloreGloregistration");

            // 3. Handle date selection.
            Date today = new Date();
            controller.handleDateSelection(today);

            // 4. Remove late input for a student.
            controller.removeLateInput("S001");

            // 5. Save changes (may throw SaveFailedException).
            boolean saved = controller.saveChanges();
            if (saved) {
                System.out.println("Controller: Save successful.");
            }

            // 6. (Optional) Simulate operation interruption.
            // Uncomment to test cancellation.
            // controller.cancelOperation();

            // 7. Administrator logs out.
            admin.logout();

        } catch (SaveFailedException e) {
            System.out.println("Error: " + e.getMessage() + " at " + e.getTimestamp());
            // Handle exception (e.g., retry, notify admin).
        }

        System.out.println("\n=== Demo Complete ===");
    }
}