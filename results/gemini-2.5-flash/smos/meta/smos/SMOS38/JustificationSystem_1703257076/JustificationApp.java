import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.HashMap; // Required for JustificationService constructor if it were nested, but not for external
import java.util.Collection; // Required for JustificationService.getAllJustifications() if it were nested, but not for external

/**
 * Main application class for the Justification System.
 * This class simulates the interaction of an Administrator with the system
 * to view, modify, or eliminate justification details, as per the use case.
 */
public class JustificationApp {

    private JustificationService justificationService;
    private Administrator loggedInAdmin;
    private Scanner scanner;

    /**
     * Constructor for JustificationApp.
     * Initializes the JustificationService and Scanner for user input.
     */
    public JustificationApp() {
        this.justificationService = new JustificationService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        JustificationApp app = new JustificationApp();
        app.run();
    }

    /**
     * Orchestrates the main flow of the application, simulating the use case.
     */
    public void run() {
        System.out.println("--- Justification System Administrator Portal ---");

        // Precondition: User must be logged in as an administrator.
        if (!loginAdministrator()) {
            System.out.println("Login failed. Exiting application.");
            return;
        }
        System.out.println("Administrator " + loggedInAdmin.getUsername() + " logged in successfully.");

        // Precondition: The user has held the case of use "ViewingLancogiustifies"
        // and clicks on one of the green absences.
        // Simulate displaying available absences that have justifications.
        displayAvailableAbsences();

        String selectedAbsenceId = promptForAbsenceSelection();
        if (selectedAbsenceId == null) {
            System.out.println("No valid absence selected. Exiting application.");
            return;
        }

        // Find the justification associated with the selected absence ID.
        // In a real system, an absence might have multiple justifications or none.
        // For this simulation, we assume a 1-to-1 or 1-to-many relationship where
        // we pick the first matching justification for simplicity.
        Optional<Justification> selectedJustification = justificationService.getJustificationByAbsenceId(selectedAbsenceId);

        if (selectedJustification.isEmpty()) {
            System.out.println("No justification found for absence ID: " + selectedAbsenceId + ". Exiting.");
            return;
        }

        // Event sequence 1: Show a form with the details of the justification
        // and the possibility of modifying or eliminating the justification.
        viewJustificationDetails(selectedJustification.get());

        // Postcondition: The system is viewing the details of a justice.
        System.out.println("\n--- End of Justification Details View ---");

        // Simulate the administrator interrupting the connection to the SMOS server.
        // This is represented by a graceful exit of the application.
        System.out.println("Administrator " + loggedInAdmin.getUsername() + " is interrupting the connection to the SMOS server.");
        System.out.println("Application session ended.");
        scanner.close(); // Close the scanner to release system resources.
    }

    /**
     * Simulates an administrator login process.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean loginAdministrator() {
        // For demonstration, a hardcoded admin user.
        Administrator admin = new Administrator("admin001", "admin", "password123");
        this.loggedInAdmin = admin; // Store the admin for later use.

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.authenticate(username, password)) {
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    /**
     * Displays a list of "green absences" (absences with associated justifications)
     * that the administrator can choose from.
     */
    private void displayAvailableAbsences() {
        System.out.println("\n--- Available Absences with Justifications (Green Absences) ---");
        // In a real system, this would query a list of absences and filter them.
        // For this simulation, we'll list the absence IDs that have justifications in our service.
        justificationService.getAllJustifications().forEach(justification ->
            System.out.println("Absence ID: " + justification.getAbsenceId() + " (Justification ID: " + justification.getId() + ")")
        );
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Prompts the administrator to select an absence ID and validates the input.
     *
     * @return The selected absence ID, or null if no valid selection is made.
     */
    private String promptForAbsenceSelection() {
        System.out.print("Please enter the Absence ID you wish to view justification details for: ");
        String absenceId = scanner.nextLine().trim();

        // Validate if a justification exists for this absence ID.
        if (!justificationService.isGreenAbsence(absenceId)) {
            System.out.println("Error: No justification found for absence ID '" + absenceId + "' or it's not a 'green absence'.");
            return null;
        }
        return absenceId;
    }

    /**
     * Displays the details of a given justification and offers options to modify or delete it.
     *
     * @param justification The Justification object to display and manage.
     */
    private void viewJustificationDetails(Justification justification) {
        System.out.println("\n--- Justification Details ---");
        System.out.println("Justification ID: " + justification.getId());
        System.out.println("Absence ID:       " + justification.getAbsenceId());
        System.out.println("Reason:           " + justification.getReason());
        System.out.println("Status:           " + justification.getStatus());
        System.out.println("Submission Date:  " + justification.getSubmissionDate());
        System.out.println("-----------------------------");

        boolean continueInteraction = true;
        while (continueInteraction) {
            System.out.println("\nOptions:");
            System.out.println("1. Modify Justification");
            System.out.println("2. Delete Justification");
            System.out.println("3. Back to Main Menu / Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        modifyJustification(justification);
                        // After modification, re-display details to show changes
                        System.out.println("\n--- Updated Justification Details ---");
                        System.out.println("Justification ID: " + justification.getId());
                        System.out.println("Absence ID:       " + justification.getAbsenceId());
                        System.out.println("Reason:           " + justification.getReason());
                        System.out.println("Status:           " + justification.getStatus());
                        System.out.println("Submission Date:  " + justification.getSubmissionDate());
                        System.out.println("-------------------------------------");
                        break;
                    case 2:
                        deleteJustification(justification.getId());
                        continueInteraction = false; // Exit after deletion
                        break;
                    case 3:
                        continueInteraction = false; // Exit to main menu or end app
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * Allows the administrator to modify the reason or status of a justification.
     *
     * @param justification The Justification object to be modified.
     */
    private void modifyJustification(Justification justification) {
        System.out.println("\n--- Modifying Justification (ID: " + justification.getId() + ") ---");
        System.out.println("Current Reason: " + justification.getReason());
        System.out.print("Enter new reason (or press Enter to keep current): ");
        String newReason = scanner.nextLine();
        if (!newReason.trim().isEmpty()) {
            justification.setReason(newReason.trim());
            System.out.println("Reason updated.");
        }

        System.out.println("Current Status: " + justification.getStatus());
        System.out.print("Enter new status (PENDING, APPROVED, REJECTED, or press Enter to keep current): ");
        String newStatusStr = scanner.nextLine().trim().toUpperCase();
        if (!newStatusStr.isEmpty()) {
            try {
                Justification.JustificationStatus newStatus = Justification.JustificationStatus.valueOf(newStatusStr);
                justification.setStatus(newStatus);
                System.out.println("Status updated.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status entered. Status remains unchanged.");
            }
        }

        // Attempt to update the justification in the service.
        Optional<Justification> updated = justificationService.updateJustification(justification);
        if (updated.isPresent()) {
            System.out.println("Justification " + justification.getId() + " successfully modified.");
        } else {
            System.out.println("Failed to modify justification " + justification.getId() + ".");
        }
    }

    /**
     * Allows the administrator to delete a justification.
     *
     * @param justificationId The ID of the justification to be deleted.
     */
    private void deleteJustification(String justificationId) {
        System.out.print("Are you sure you want to delete justification " + justificationId + "? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(confirmation)) {
            Optional<Justification> deleted = justificationService.deleteJustification(justificationId);
            if (deleted.isPresent()) {
                System.out.println("Justification " + justificationId + " successfully deleted.");
            } else {
                System.out.println("Failed to delete justification " + justificationId + ". It might not exist.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}