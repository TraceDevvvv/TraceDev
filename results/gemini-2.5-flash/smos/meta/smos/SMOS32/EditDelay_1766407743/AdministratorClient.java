import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Simulates the administrator client application for editing delays.
 * This console application interacts with the SMOSServer to perform the "EditDelay" use case.
 */
public class AdministratorClient {
    private SMOSServer smosServer; // Instance of the simulated SMOS server
    private Scanner scanner;       // Scanner for reading user input from the console
    private boolean loggedInAsAdmin; // Simulates the administrator's login status

    /**
     * Constructor for AdministratorClient.
     * Initializes the SMOSServer and Scanner, and sets the initial login status to false.
     */
    public AdministratorClient() {
        this.smosServer = new SMOSServer();
        this.scanner = new Scanner(System.in);
        this.loggedInAsAdmin = false; // Administrator is not logged in initially
    }

    /**
     * Simulates the administrator login process.
     * This fulfills the precondition "The user must be logged in to the system as an administrator".
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean simulateAdminLogin() {
        System.out.println("--- Administrator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple hardcoded credentials for simulation purposes.
        // In a real application, this would involve authentication against a user management system.
        if ("admin".equals(username) && "adminpass".equals(password)) {
            System.out.println("Login successful. Welcome, Administrator!");
            loggedInAsAdmin = true;
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            loggedInAsAdmin = false;
            return false;
        }
    }

    /**
     * Attempts to connect to the simulated SMOS server.
     * If connection fails, the application exits as it cannot proceed without server access.
     */
    private void connectToServer() {
        try {
            smosServer.connect();
        } catch (SMOSServerException e) {
            System.err.println("Error connecting to SMOS server: " + e.getMessage());
            // If connection fails, the client cannot proceed with operations.
            System.out.println("Exiting application due to server connection failure.");
            System.exit(1); // Terminate the application
        }
    }

    /**
     * Attempts to disconnect from the simulated SMOS server.
     * Handles potential exceptions during disconnection.
     */
    private void disconnectFromServer() {
        try {
            if (smosServer.isConnected()) {
                smosServer.disconnect();
            }
        } catch (SMOSServerException e) {
            System.err.println("Error disconnecting from SMOS server: " + e.getMessage());
        }
    }

    /**
     * Starts the administrator client application.
     * This method orchestrates the login, server connection, and the main interaction loop.
     */
    public void start() {
        // Step 1: Simulate administrator login
        if (!simulateAdminLogin()) {
            System.out.println("Exiting application due to failed login.");
            scanner.close();
            return;
        }

        // Step 2: Connect to the SMOS server after successful login
        connectToServer();

        // Main application loop, simulating the "registry screen" as per postcondition.
        // The loop continues as long as the admin is logged in and the server is connected.
        while (loggedInAsAdmin && smosServer.isConnected()) {
            System.out.println("\n--- Edit Delay Registry Screen ---");
            System.out.println("1. Select Date to Edit Delay");
            System.out.println("2. Disconnect from Server (interrupts operation)");
            System.out.println("3. Exit Application");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    editDelayWorkflow(); // Initiates the delay editing process
                    break;
                case "2":
                    // Simulates "The administrator interrupts the operation" by disconnecting.
                    disconnectFromServer();
                    System.out.println("Disconnected from server. Please restart to reconnect.");
                    loggedInAsAdmin = false; // Force exit main loop as server connection is lost
                    break;
                case "3":
                    System.out.println("Exiting application.");
                    loggedInAsAdmin = false; // Force exit main loop
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        // Ensure disconnection if still connected when exiting the main loop.
        disconnectFromServer();
        scanner.close(); // Close the scanner to release resources
        System.out.println("Application terminated.");
    }

    /**
     * Manages the workflow for editing a delay for a specific date.
     * This method implements the event sequence: "Update the screen based on the selected date",
     * "The user edit the delay and click on \"Save\"", and "The system sends the data modified to the server."
     */
    private void editDelayWorkflow() {
        System.out.println(
                "\n--- Edit Delay Workflow ---\n(Format: YYYY-MM-DD. Example: 2023-10-26)"
        );
        System.out.print("Enter the date for which to edit the delay: ");
        String dateString = scanner.nextLine();

        // Validate date format
        LocalDate selectedDate;
        try {
            selectedDate = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        // Step 1: Update the screen based on the selected date (simulate by displaying current delay)
        try {
            Integer currentDelay = smosServer.getDelay(dateString);
            if (currentDelay != null) {
                System.out.println("Current delay for " + dateString + ": " + currentDelay + " minutes.");
            } else {
                System.out.println("No delay set for " + dateString + ". You can set a new one.");
            }

            // Step 2: The user edits the delay and clicks on "Save"
            System.out.print("Enter new delay in minutes (non-negative integer): ");
            int newDelay;
            try {
                newDelay = Integer.parseInt(scanner.nextLine());
                if (newDelay < 0) {
                    System.out.println("Delay cannot be negative. Please enter a non-negative value.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for delay.");
                return;
            }

            // Step 3: The system sends the data modified to the server.
            smosServer.updateDelay(dateString, newDelay);
            System.out.println("Delay for " + dateString + " successfully updated to " + newDelay + " minutes.");
            // Postcondition: The system remains on the registry screen (handled by the main loop)

        } catch (SMOSServerException e) {
            System.err.println("Server error during delay edit: " + e.getMessage());
            // Postcondition: Connection to the SMOS server interrupted
            // If a server error occurs, it implies connection interruption or a critical issue.
            // We set loggedInAsAdmin to false to exit the main loop, simulating interruption.
            loggedInAsAdmin = false;
        }
    }

    /**
     * Main method to run the AdministratorClient application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        AdministratorClient client = new AdministratorClient();
        client.start();
    }
}