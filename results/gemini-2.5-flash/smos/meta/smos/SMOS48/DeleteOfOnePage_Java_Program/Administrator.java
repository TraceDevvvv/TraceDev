import java.util.List;
import java.util.Scanner;

/**
 * Simulates an administrator's interaction with the system to manage report cards.
 * This class handles administrator login, displaying report cards, and the
 * specific use case of deleting a report card, including user confirmation.
 */
public class Administrator {
    private String username;
    private boolean loggedIn;
    private SMOSServer smosServer;
    private Scanner scanner; // Used for capturing user input for confirmation

    /**
     * Constructs a new Administrator instance.
     *
     * @param username The username of the administrator.
     * @param smosServer The SMOSServer instance to interact with.
     */
    public Administrator(String username, SMOSServer smosServer) {
        this.username = username;
        this.smosServer = smosServer;
        this.loggedIn = false; // Administrator is not logged in initially
        this.scanner = new Scanner(System.in); // Initialize scanner for user input
    }

    /**
     * Simulates the administrator logging into the system.
     * For simplicity, a hardcoded password check is used.
     *
     * @param password The password entered by the administrator.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String password) {
        // In a real application, this would involve secure authentication.
        // For this simulation, a simple hardcoded password check suff.
        if ("adminpass".equals(password)) {
            this.loggedIn = true;
            System.out.println(username + " logged in as Administrator.");
            return true;
        }
        System.out.println("Login failed for " + username + ": Incorrect password.");
        return false;
    }

    /**
     * Simulates the administrator logging out of the system.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println(username + " logged out.");
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Displays all report cards currently stored in the SMOS server.
     * This method simulates the "Displaying a report card" use case,
     * showing a list of all available report cards.
     */
    public void displayAllReportCards() {
        if (!loggedIn) {
            System.out.println("Error: Administrator not logged in. Cannot display report cards.");
            return;
        }
        if (!smosServer.isConnected()) {
            System.out.println("Error: SMOS server not connected. Cannot display report cards.");
            return;
        }

        System.out.println("\n--- Current Report Cards in System ---");
        List<ReportCard> reportCards = smosServer.getAllReportCards();
        if (reportCards.isEmpty()) {
            System.out.println("No report cards found in the system.");
        } else {
            for (ReportCard rc : reportCards) {
                System.out.println(rc);
            }
        }
        System.out.println("--------------------------------------");
    }

    /**
     * Handles the process of deleting a specific report card from the system.
     * This method implements the "DeleteOfOnePage" use case, including:
     * 1. Displaying a confirmation prompt to the administrator.
     * 2. Waiting for administrator confirmation.
     * 3. Attempting to delete the report card via the SMOS server.
     * 4. Displaying the result of the deletion and the updated list of report cards.
     *
     * Preconditions:
     * - The administrator is logged in.
     * - The SMOS server is connected.
     * - The administrator has previously viewed the report card and clicked "Delete".
     *
     * Postconditions:
     * - The specified report card is canceled (if confirmed and found).
     * - The connection to the SMOS server is conceptually "interrupted" for this specific operation,
     *   meaning the transaction is complete. The server itself remains connected for further operations
     *   like displaying the updated list. The final server disconnect is handled by the main application flow.
     *
     * @param reportCardIdToDelete The unique ID of the report card to be deleted.
     * @return true if the report card was successfully deleted and confirmed, false otherwise.
     */
    public boolean deleteReportCard(String reportCardIdToDelete) {
        // Precondition check: Administrator must be logged in
        if (!loggedIn) {
            System.out.println("Error: Administrator not logged in. Cannot perform deletion.");
            return false;
        }
        // Precondition check: SMOS server must be connected
        if (!smosServer.isConnected()) {
            System.out.println("Error: SMOS server not connected. Cannot perform deletion.");
            return false;
        }

        // Simulate "System 1: The system displays a form to confirm the cancellation"
        System.out.println("\n--- Confirm Report Card Deletion ---");
        ReportCard rcToDelete = smosServer.getReportCard(reportCardIdToDelete);

        if (rcToDelete == null) {
            System.out.println("Error: Report card with ID '" + reportCardIdToDelete + "' not found. Deletion aborted.");
            return false;
        }

        System.out.println("You have selected the following report card for deletion:");
        System.out.println(rcToDelete);
        System.out.print("Are you sure you want to permanently delete this report card? (Type 'yes' to confirm): ");

        // Simulate "User 2: The user accepts the cancellation by pressing the confirmation key"
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(confirmation)) {
            // Attempt to delete the report card from the SMOS server
            boolean deleted = smosServer.deleteReportCard(reportCardIdToDelete);

            // Simulate "System 3: Displays a message of correct deletion and displays the form with the list of classes"
            if (deleted) {
                System.out.println("Success: Report card '" + reportCardIdToDelete + "' has been successfully canceled.");
                // Postcondition: A report relative to a student is canceled. (Handled by SMOSServer)
                // Postcondition: Connection to the interrupted SMOS server.
                // This implies the specific operation's transaction is complete.
                // The server remains connected to allow displaying the updated list.
                displayAllReportCards(); // Display the updated list of classes
                return true;
            } else {
                // This case should ideally not be reached if rcToDelete was not null,
                // but it's a safeguard for potential race conditions or server issues.
                System.out.println("Error: Failed to delete report card '" + reportCardIdToDelete + "'. Please try again.");
                return false;
            }
        } else {
            System.out.println("Deletion canceled by administrator. Report card was not deleted.");
            return false;
        }
    }

    /**
     * Closes the scanner resource used for user input.
     * It's important to call this method when the Administrator object is no longer needed
     * to prevent resource leaks.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            scanner = null; // Set to null to indicate it's closed
        }
    }
}