'''
Main.java
Entry point of the program. Simulates the complete EditJustification use case.
Includes all steps: login, view details, edit, save, disconnect, and return to registry.
'''
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.LogManager;
public class Main {
    public static void main(String[] args) {
        try {
            // Configure logging
            LogManager.getLogManager().readConfiguration(
                Main.class.getResourceAsStream("/logging.properties"));
        } catch (Exception e) {
            System.err.println("Warning: Could not configure logging: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        SMOSSystem smosSystem = new SMOSSystem();
        EditJustificationHandler editHandler = new EditJustificationHandler(smosSystem);
        RegistryFrame registry = new RegistryFrame();
        System.out.println("=== EDIT JUSTIFICATION SYSTEM ===");
        System.out.println("System started at: " + 
                          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // Precondition: Login as administrator
        System.out.println("\n=== ADMINISTRATOR LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (!smosSystem.loginAsAdmin(username, password)) {
            System.out.println("\n*** LOGIN FAILED ***");
            System.out.println("The system requires administrator privileges to continue.");
            scanner.close();
            return;
        }
        // Connect to SMOS server (implied by the use case)
        try {
            smosSystem.connectToSMOS();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
            smosSystem.logout();
            scanner.close();
            return;
        }
        // Display registry first
        registry.displayRegistry();
        // Select justification to edit
        int justificationId = 0;
        while (true) {
            try {
                System.out.print("Enter Justification ID to edit (or 0 to exit): ");
                justificationId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (justificationId == 0) {
                    System.out.println("Exiting program.");
                    smosSystem.disconnectFromSMOS();
                    smosSystem.logout();
                    scanner.close();
                    return;
                }
                if (DatabaseSimulator.containsJustification(justificationId)) {
                    break;
                } else {
                    System.out.println("Error: Justification ID " + justificationId + " not found.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
        // Simulate that user has carried out "viewdetalticaustifica"
        Justification justification;
        try {
            justification = editHandler.viewJustificationDetails(justificationId);
            if (justification == null) {
                throw new IllegalStateException("Failed to load justification details");
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
            smosSystem.disconnectFromSMOS();
            smosSystem.logout();
            scanner.close();
            return;
        }
        // Display current details
        registry.displayJustificationDetails(justificationId);
        // Step 1: Change one or more fields
        System.out.println("\n=== EDIT JUSTIFICATION FIELDS ===");
        System.out.println("Current date: " + justification.getDate());
        System.out.print("Enter new date (YYYY-MM-DD) or press Enter to keep current: ");
        String newDate = scanner.nextLine().trim();
        System.out.println("\nCurrent description: " + justification.getDescription());
        System.out.print("Enter new description or press Enter to keep current: ");
        String newDescription = scanner.nextLine().trim();
        // Step 2: Click on "Save" -> edit fields and save
        boolean editSuccess = editHandler.editJustificationFields(newDate, newDescription);
        if (editSuccess) {
            System.out.print("\nSave changes? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.equals("yes") || confirm.equals("y")) {
                boolean saveSuccess = editHandler.saveJustification();
                if (saveSuccess) {
                    // Postconditions:
                    System.out.println("\n" + "=".repeat(50));
                    System.out.println("OPERATION COMPLETE - POSTCONDITIONS");
                    System.out.println("=".repeat(50));
                    System.out.println("1. ✓ The justification has been modified.");
                    // 2. The system returns to the registry screen.
                    System.out.println("2. ✓ Returning to registry screen...");
                    registry.returnToRegistry();
                    // 3. The administrator interrupts the connection to the SMOS server.
                    smosSystem.disconnectFromSMOS();
                    System.out.println("3. ✓ SMOS server connection interrupted.");
                    // Display updated details
                    System.out.println("\nUpdated justification details:");
                    registry.displayJustificationDetails(justificationId);
                } else {
                    System.out.println("\n*** SAVE FAILED ***");
                    System.out.println("Changes were not saved to the database.");
                }
            } else {
                System.out.println("\nChanges discarded. No modifications were saved.");
            }
        } else {
            System.out.println("\n*** EDIT FAILED ***");
            System.out.println("No changes were made to the justification.");
        }
        // Cleanup
        smosSystem.logout();
        scanner.close();
        System.out.println("\n=== SYSTEM SHUTDOWN ===");
        System.out.println("Thank you for using the Edit Justification System.");
    }
}