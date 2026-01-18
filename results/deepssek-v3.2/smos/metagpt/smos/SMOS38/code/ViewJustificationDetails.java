import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

/**
 * Main program for ViewJustificationDetails use case.
 * This application allows administrators to view and manage justification details.
 * The system handles administrator authentication and viewing justification details.
 */
public class ViewJustificationDetails {
    
    /**
     * Main method - entry point of the application.
     * Creates an instance of the application and starts it.
     */
    public static void main(String[] args) {
        JustificationViewerApp app = new JustificationViewerApp();
        app.start();
    }
}

/**
 * Represents a justification/justice with its details.
 * This class models the data structure for a justice instance.
 */
class Justification {
    private String id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private String status;
    private String administratorId;
    
    /**
     * Constructor to create a new justification with provided details.
     * 
     * @param id Unique identifier for the justification
     * @param title Title of the justification
     * @param description Detailed description
     * @param creationDate When the justification was created
     * @param status Current status (e.g., "Pending", "Approved", "Rejected")
     * @param administratorId ID of the administrator who created/modified it
     */
    public Justification(String id, String title, String description, 
                        LocalDateTime creationDate, String status, String administratorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
        this.administratorId = administratorId;
    }
    
    // Getters and setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getAdministratorId() { return administratorId; }
    public void setAdministratorId(String administratorId) { this.administratorId = administratorId; }
    
    /**
     * Returns a formatted string representation of the justification details.
     * 
     * @return Formatted string with all justification details
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Justification Details:\n" +
               "ID: " + id + "\n" +
               "Title: " + title + "\n" +
               "Description: " + description + "\n" +
               "Creation Date: " + creationDate.format(formatter) + "\n" +
               "Status: " + status + "\n" +
               "Administrator ID: " + administratorId;
    }
}

/**
 * Handles administrator authentication and session management.
 * Validates administrator credentials before allowing access to justification details.
 */
class AdministratorAuthenticator {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    
    /**
     * Authenticates an administrator with provided credentials.
     * 
     * @param username Administrator username
     * @param password Administrator password
     * @return true if authentication succeeds, false otherwise
     */
    public boolean authenticate(String username, String password) {
        // In a real application, this would check against a secure database
        // For this example, we use hardcoded credentials
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
    
    /**
     * Checks if the current user is authenticated as an administrator.
     * This is a placeholder that would normally check session state.
     * 
     * @return Always returns true for this example implementation
     */
    public boolean isAuthenticated() {
        // In a real application, this would check session cookies or tokens
        return true;
    }
}

/**
 * Handles connection to SMOS server.
 * Manages server connectivity and handles connection interruptions.
 */
class SMOSServerConnection {
    private boolean connected = false;
    
    /**
     * Establishes connection to SMOS server.
     * 
     * @return true if connection is successful, false otherwise
     */
    public boolean connect() {
        System.out.println("Connecting to SMOS server...");
        // Simulate connection attempt
        try {
            // Simulate network delay
            Thread.sleep(500);
            connected = true;
            System.out.println("Successfully connected to SMOS server.");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Connection to SMOS server was interrupted.");
            connected = false;
            return false;
        }
    }
    
    /**
     * Disconnects from SMOS server.
     */
    public void disconnect() {
        if (connected) {
            System.out.println("Disconnecting from SMOS server...");
            connected = false;
            System.out.println("Disconnected from SMOS server.");
        }
    }
    
    /**
     * Checks if currently connected to SMOS server.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Simulates interruption of SMOS server connection.
     * Handles the postcondition where connection may be interrupted.
     */
    public void simulateInterruption() {
        if (connected) {
            System.out.println("SMOS server connection interrupted!");
            connected = false;
        }
    }
}

/**
 * Form for displaying justification details and providing modification options.
 * This class handles the user interface for viewing and managing justifications.
 */
class JustificationDetailForm {
    private Justification justification;
    private Scanner scanner;
    
    /**
     * Constructor that initializes the form with a justification.
     * 
     * @param justification The justification to display and manage
     */
    public JustificationDetailForm(Justification justification) {
        this.justification = justification;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the justification details form with options.
     * This method implements the main event sequence from the use case.
     */
    public void display() {
        System.out.println("\n=== Justification Details Form ===");
        System.out.println(justification.toString());
        System.out.println("\nOptions:");
        System.out.println("1. Modify justification");
        System.out.println("2. Eliminate (delete) justification");
        System.out.println("3. Return to main menu");
        System.out.print("Enter your choice (1-3): ");
        
        String choice = scanner.nextLine();
        handleUserChoice(choice);
    }
    
    /**
     * Handles user choice from the options menu.
     * 
     * @param choice User's selected option
     */
    private void handleUserChoice(String choice) {
        switch (choice) {
            case "1":
                modifyJustification();
                break;
            case "2":
                eliminateJustification();
                break;
            case "3":
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                display(); // Show form again
        }
    }
    
    /**
     * Handles modification of the justification details.
     * Allows administrator to update title, description, and status.
     */
    private void modifyJustification() {
        System.out.println("\n=== Modify Justification ===");
        
        System.out.print("Enter new title (current: '" + justification.getTitle() + "'): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.trim().isEmpty()) {
            justification.setTitle(newTitle);
        }
        
        System.out.print("Enter new description (current: '" + justification.getDescription() + "'): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.trim().isEmpty()) {
            justification.setDescription(newDescription);
        }
        
        System.out.print("Enter new status (Pending/Approved/Rejected) (current: '" + justification.getStatus() + "'): ");
        String newStatus = scanner.nextLine();
        if (!newStatus.trim().isEmpty()) {
            justification.setStatus(newStatus);
        }
        
        System.out.println("Justification modified successfully!");
        System.out.println("\nUpdated details:");
        System.out.println(justification.toString());
        
        // Return to the form to show updated details
        display();
    }
    
    /**
     * Handles elimination (deletion) of the justification.
     * Asks for confirmation before deleting.
     */
    private void eliminateJustification() {
        System.out.println("\n=== Eliminate Justification ===");
        System.out.print("Are you sure you want to delete this justification? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            justification = null;
            System.out.println("Justification has been eliminated successfully.");
        } else {
            System.out.println("Deletion cancelled.");
            display(); // Return to form
        }
    }
    
    /**
     * Returns the current justification.
     * 
     * @return The justification object, or null if eliminated
     */
    public Justification getJustification() {
        return justification;
    }
}

/**
 * Main application class that orchestrates the ViewJustificationDetails use case.
 * Handles the complete flow from authentication to viewing details.
 */
class JustificationViewerApp {
    private AdministratorAuthenticator authenticator;
    private SMOSServerConnection serverConnection;
    private Scanner scanner;
    
    /**
     * Constructor initializes the application components.
     */
    public JustificationViewerApp() {
        this.authenticator = new AdministratorAuthenticator();
        this.serverConnection = new SMOSServerConnection();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Starts the application and handles the main flow.
     * Implements the complete use case including preconditions and postconditions.
     */
    public void start() {
        System.out.println("=== Justification Details Viewer ===");
        
        // Precondition: User must be logged in as administrator
        if (!authenticateAdministrator()) {
            System.out.println("Authentication failed. Exiting application.");
            return;
        }
        
        // Connect to SMOS server
        if (!serverConnection.connect()) {
            System.out.println("Failed to connect to SMOS server. Exiting application.");
            return;
        }
        
        try {
            // Main application loop
            runApplication();
        } finally {
            // Postcondition: Handle SMOS server connection interruption
            serverConnection.disconnect();
        }
    }
    
    /**
     * Handles administrator authentication.
     * 
     * @return true if authentication successful, false otherwise
     */
    private boolean authenticateAdministrator() {
        System.out.println("\n=== Administrator Authentication ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        boolean authenticated = authenticator.authenticate(username, password);
        
        if (authenticated) {
            System.out.println("Authentication successful! Welcome, Administrator.");
        } else {
            System.out.println("Authentication failed. Invalid credentials.");
        }
        
        return authenticated;
    }
    
    /**
     * Main application logic after authentication.
     * Handles viewing justification details and user interactions.
     */
    private void runApplication() {
        // Simulate fetching a justification (in real app, this would come from database)
        Justification justification = createSampleJustification();
        
        // Create and display the justification details form
        JustificationDetailForm form = new JustificationDetailForm(justification);
        
        // Event sequence: Show form with details and modification options
        form.display();
        
        // Postcondition: Simulate possible connection interruption
        simulateRandomConnectionInterruption();
        
        System.out.println("\n=== Application Completed ===");
        System.out.println("Thank you for using the Justification Details Viewer.");
    }
    
    /**
     * Creates a sample justification for demonstration.
     * In a real application, this would be fetched from a database.
     * 
     * @return A sample justification object
     */
    private Justification createSampleJustification() {
        return new Justification(
            UUID.randomUUID().toString(),
            "Unauthorized Absence Justification",
            "Employee was absent due to family emergency. Medical certificate provided.",
            LocalDateTime.now().minusDays(2),
            "Pending",
            "ADM001"
        );
    }
    
    /**
     * Simulates random interruption of SMOS server connection.
     * This handles the postcondition where connection may be interrupted.
     */
    private void simulateRandomConnectionInterruption() {
        // 30% chance to simulate connection interruption
        if (Math.random() < 0.3) {
            System.out.println("\n[System Notice] Simulating SMOS server connection interruption...");
            serverConnection.simulateInterruption();
        }
    }
}