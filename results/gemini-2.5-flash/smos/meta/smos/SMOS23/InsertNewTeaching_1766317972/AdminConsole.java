import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Main application logic for administrators to insert new teachings.
 * This class simulates the user interface and interaction flow described in the use case,
 * handling user input, validation, archiving, and potential SMOS connection issues.
 */
public class AdminConsole {

    private final TeachingArchive teachingArchive;
    private final SMOSConnection smosConnection;
    private final Scanner scanner;

    // Flag to simulate administrator login status
    private boolean isAdminLoggedIn = false;
    // Flag to simulate if the "viewing technology" case has been performed
    private boolean hasViewedTeachings = false;

    /**
     * Constructs an AdminConsole instance.
     * Initializes the teaching archive, SMOS connection, and scanner for input.
     */
    public AdminConsole() {
        this.teachingArchive = new TeachingArchive();
        this.smosConnection = new SMOSConnection();
        this.scanner = new Scanner(System.in);
        // Initialize with some dummy data for demonstration
        teachingArchive.addTeaching(new Teaching("Mathematics"));
        teachingArchive.addTeaching(new Teaching("Physics"));
    }

    /**
     * Simulates the administrator login process.
     * In a real system, this would involve authentication.
     */
    private void simulateAdminLogin() {
        System.out.println("--- Simulating Administrator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple mock login
        if ("admin".equals(username) && "password".equals(password)) {
            isAdminLoggedIn = true;
            System.out.println("Administrator logged in successfully.");
        } else {
            System.out.println("Login failed. Invalid credentials.");
            isAdminLoggedIn = false;
        }
        System.out.println("--------------------------------------");
    }

    /**
     * Simulates the "viewing technology" case, displaying the list of teachings.
     * This fulfills a precondition for inserting a new teaching.
     */
    private void simulateViewTeachings() {
        if (!isAdminLoggedIn) {
            System.out.println("Error: Must be logged in as an administrator to view teachings.");
            return;
        }
        System.out.println("\n--- Viewing Existing Teachings ---");
        Set<Teaching> teachings = teachingArchive.getAllTeachings();
        if (teachings.isEmpty()) {
            System.out.println("No teachings currently in the archive.");
        } else {
            System.out.println("Current Teachings:");
            teachings.forEach(t -> System.out.println("- " + t.getName()));
        }
        System.out.println("----------------------------------");
        hasViewedTeachings = true;
    }

    /**
     * Displays the form for entering a new teaching name and processes the input.
     * This method orchestrates the "InsertNewTeaching" use case.
     */
    public void insertNewTeachingFlow() {
        // Preconditions check
        if (!isAdminLoggedIn) {
            System.out.println("Operation aborted: User is not logged in as an administrator.");
            return;
        }
        if (!hasViewedTeachings) {
            System.out.println("Operation aborted: Please view existing teachings first (simulate 'viewing technology').");
            return;
        }

        System.out.println("\n--- Insert New Teaching ---");
        System.out.println("1. The system shows the form to be completed with: Teaching name.");
        System.out.println("   (Type 'cancel' at any point to interrupt the operation)");

        String teachingNameInput = null;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Enter Teaching name: ");
            teachingNameInput = scanner.nextLine();

            if ("cancel".equalsIgnoreCase(teachingNameInput.trim())) {
                simulateAdminInterruption();
                return; // Administrator interrupts the operation
            }

            // 4. Checks on the validity of the data entered
            if (TeachingValidator.isValidTeachingName(teachingNameInput)) {
                inputValid = true;
            } else {
                handleInvalidData(); // Activates the case of "Errodati" use
            }
        }

        // 3. Click on the "Save" button (simulated by pressing Enter after input)
        System.out.println("Simulating 'Save' button click...");

        try {
            // Attempt to connect to SMOS server (simulated)
            smosConnection.connect();

            // Create Teaching object
            Teaching newTeaching = new Teaching(teachingNameInput);

            // Check if teaching already exists
            if (teachingArchive.containsTeachingName(newTeaching.getName())) {
                System.out.println("Error: A teaching with the name '" + newTeaching.getName() + "' already exists.");
                // No SMOS interaction needed if it's a duplicate locally
                smosConnection.disconnect();
                return;
            }

            // Insert the new teaching into the archive
            boolean added = teachingArchive.addTeaching(newTeaching);

            if (added) {
                // Simulate sending data to SMOS server after successful local archive
                smosConnection.sendData("New Teaching Added: " + newTeaching.getName());
                notifyTeachingAdded(newTeaching.getName()); // Postcondition: User notified
            } else {
                // This case should ideally not be reached if containsTeachingName check is robust
                System.err.println("Unexpected error: Could not add teaching '" + newTeaching.getName() + "'.");
            }

        } catch (IllegalArgumentException e) {
            // This catch block handles cases where Teaching constructor might throw,
            // though isValidTeachingName should prevent it.
            System.err.println("Data validation error during object creation: " + e.getMessage());
            handleInvalidData(); // Re-activate "Errodati" if somehow an invalid name slipped through
        } catch (SMOSConnection.SMOSConnectionException e) {
            handleSMOSConnectionError(e.getMessage()); // Postcondition: error connection to SMOS server interrupted
        } finally {
            smosConnection.disconnect(); // Always attempt to disconnect
            System.out.println("--- End of Insert New Teaching Flow ---");
        }
    }

    /**
     * Handles the "Errodati" use case when data entered is not valid.
     */
    private void handleInvalidData() {
        System.err.println("!!! Errodati: Invalid data entered. Teaching name cannot be empty or contain only whitespace. Please try again. !!!");
    }

    /**
     * Notifies the user that a teaching has been successfully entered.
     *
     * @param teachingName The name of the teaching that was added.
     */
    private void notifyTeachingAdded(String teachingName) {
        System.out.println("\nSuccess: Teaching '" + teachingName + "' has been successfully added to the archive.");
    }

    /**
     * Handles the scenario where the connection to the SMOS server is interrupted.
     *
     * @param errorMessage The error message from the SMOS connection.
     */
    private void handleSMOSConnectionError(String errorMessage) {
        System.err.println("\n!!! Error: Connection to the SMOS server interrupted. " + errorMessage + " !!!");
        System.err.println("Postcondition: The error connection to the SMOS server interrupted.");
    }

    /**
     * Simulates the administrator interrupting the operation.
     */
    private void simulateAdminInterruption() {
        System.out.println("\nOperation interrupted by administrator.");
        System.out.println("Postcondition: The administrator interrupts the operation.");
    }

    /**
     * Main method to run the AdminConsole application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        AdminConsole console = new AdminConsole();

        // Simulate the preconditions
        console.simulateAdminLogin();
        if (!console.isAdminLoggedIn) {
            System.out.println("Exiting application due to failed login.");
            console.scanner.close();
            return;
        }

        console.simulateViewTeachings();
        if (!console.hasViewedTeachings) {
            System.out.println("Exiting application as preconditions for viewing teachings were not met.");
            console.scanner.close();
            return;
        }

        // Main loop for the administrator to perform actions
        while (true) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. Insert New Teaching");
            System.out.println("2. Simulate SMOS Connection Interruption (for testing)");
            System.out.println("3. Simulate SMOS Connection Restoration (for testing)");
            System.out.println("4. View All Teachings");
            System.out.println("5. Logout and Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = console.scanner.nextInt();
                console.scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        console.insertNewTeachingFlow();
                        break;
                    case 2:
                        SMOSConnection.setConnectionInterrupted(true);
                        break;
                    case 3:
                        SMOSConnection.setConnectionInterrupted(false);
                        break;
                    case 4:
                        console.simulateViewTeachings();
                        break;
                    case 5:
                        System.out.println("Logging out. Goodbye!");
                        console.isAdminLoggedIn = false; // Simulate logout
                        console.scanner.close();
                        return; // Exit the application
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                console.scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}