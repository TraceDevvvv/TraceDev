import java.util.Scanner;

/**
 * Main class to run the DeleteOfOnePage program.
 * This class orchestrates the simulation of an administrator's actions:
 * logging in, displaying report cards, and deleting a specific report card
 * with confirmation, as per the use case.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize SMOS Server
        SMOSServer smosServer = new SMOSServer();
        // Initialize Scanner for main program's user input (e.g., report card ID to delete)
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("--- Starting DeleteOfOnePage Simulation ---");

        // 1. Simulate SMOS Server connection
        System.out.println("\nAttempting to connect to SMOS Server...");
        if (!smosServer.connect()) {
            System.err.println("Failed to connect to SMOS Server. Exiting.");
            mainScanner.close();
            return;
        }

        // 2. Populate some initial report card data
        System.out.println("\nPopulating initial report card data...");
        smosServer.addReportCard(new ReportCard("RC001", "S001", "Alice Smith", "Math", 85.5));
        smosServer.addReportCard(new ReportCard("RC002", "S001", "Alice Smith", "Physics", 92.0));
        smosServer.addReportCard(new ReportCard("RC003", "S002", "Bob Johnson", "Chemistry", 78.0));
        smosServer.addReportCard(new ReportCard("RC004", "S003", "Charlie Brown", "History", 65.0));
        smosServer.addReportCard(new ReportCard("RC005", "S004", "Diana Prince", "English", 95.0));

        // 3. Initialize Administrator
        Administrator admin = new Administrator("admin", smosServer);

        // Precondition: The user has been logged in to the system as an administrator
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Enter administrator password: ");
        String password = mainScanner.nextLine();
        if (!admin.login(password)) {
            System.err.println("Administrator login failed. Exiting.");
            admin.closeScanner(); // Close scanner used by Administrator
            smosServer.disconnect(); // Disconnect server
            mainScanner.close();
            return;
        }

        // Precondition: The user took the case of use "Displaying a report card"
        System.out.println("\n--- Displaying All Report Cards (Pre-deletion) ---");
        admin.displayAllReportCards();

        // Precondition: The user clicked on the "Delete" button
        System.out.print("\nEnter the Report Card ID to delete (e.g., RC003): ");
        String reportCardIdToDelete = mainScanner.nextLine();

        // Simulate the deletion process
        // This method handles the confirmation prompt and actual deletion
        boolean deletionSuccessful = admin.deleteReportCard(reportCardIdToDelete);

        if (deletionSuccessful) {
            System.out.println("\nDeletion process completed successfully for Report Card ID: " + reportCardIdToDelete);
        } else {
            System.out.println("\nDeletion process was not successful or was cancelled for Report Card ID: " + reportCardIdToDelete);
        }

        // Postcondition: Connection to the interrupted SMOS server
        // This means the server connection should be closed after the operation.
        System.out.println("\n--- Ending Simulation ---");
        admin.logout(); // Administrator logs out
        smosServer.disconnect(); // Disconnect from SMOS server
        admin.closeScanner(); // Close the scanner used by the Administrator object
        mainScanner.close(); // Close the main scanner

        System.out.println("Simulation finished.");
    }
}