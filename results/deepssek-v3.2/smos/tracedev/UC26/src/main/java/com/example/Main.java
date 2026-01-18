
package com.example;

import java.util.List;

/**
 * Main class to demonstrate the flow as per the sequence diagram.
 * Simulates the administrator actions and sequence of calls.
 */
public class Main {
    public static void main(String[] args) {
        // Setup components
        TeachingRepository repository = new TeachingRepositoryImpl(null);
        AuditLogger logger = new ConsoleAuditLogger();
        SMOSConnection smos = new SMOSConnectionImpl("http://smos.example.com");
        DeleteTeachingController controller = new DeleteTeachingController(repository, logger, smos);

        // Populate some teachings
        Teaching t1 = new Teaching("T001", "Math 101", "Basic Mathematics");
        Teaching t2 = new Teaching("T002", "Physics 201", "Advanced Physics");
        repository.save(t1);
        repository.save(t2);

        // Simulate: User HAS executed-displaydeddailsignment
        System.out.println("=== Display Teaching Details ===");
        TeachingDetails details = controller.displayTeachingDetails("T001");
        System.out.println(details.getDetails());

        // Simulate: User IS logged-in-as-administrator
        Administrator admin = new Administrator("A001", "admin", "ADMIN");
        admin.setLoggedIn(true);
        System.out.println("\nAdministrator logged in: " + admin.isLoggedIn());

        // Create delete command
        DeleteTeachingCommand command = new DeleteTeachingCommand("T001", admin.getId());

        // Handle delete command
        System.out.println("\n=== Handle Delete Command ===");
        try {
            List<Teaching> remaining = controller.handleDeleteCommand(command);
            System.out.println("Teaching deleted successfully.");
            System.out.println("Remaining teachings: " + remaining.size());
            for (Teaching t : remaining) {
                System.out.println(" - " + t.getTitle());
            }
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        // Simulate connection interruption (optional)
        System.out.println("\n=== Simulate SMOS Connection Interruption ===");
        if (!controller.checkSMOSConnection()) {
            System.out.println("Connection to SMOS server interrupted. Operation may be incomplete.");
            try {
                smos.checkConnection();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
