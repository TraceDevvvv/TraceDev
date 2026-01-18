import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This program simulates the "EliminateJustification" use case for an administrator.
 * It includes login, viewing justification details, and deletion with error handling.
 */

// Class representing a Justification with an ID and details
class Justification {
    private String id;
    private String details;

    public Justification(String id, String details) {
        this.id = id;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Justification ID: " + id + ", Details: " + details;
    }
}

// Class representing the System that manages justifications and server connections
class JustificationSystem {
    private Map<String, Justification> justifications; // Simulated database of justifications
    private boolean serverConnected; // Simulate server connection status

    public JustificationSystem() {
        justifications = new HashMap<>();
        serverConnected = true; // Assume server is initially connected
        // Initialize with some sample justifications for demonstration
        justifications.put("J001", new Justification("J001", "Sample justification details for testing."));
        justifications.put("J002", new Justification("J002", "Another justification for deletion example."));
    }

    // Method to simulate server connection interruption
    public void interruptServerConnection() {
        serverConnected = false;
        System.out.println("Alert: Connection to SMOS server interrupted.");
    }

    // Method to check server connection
    public boolean isServerConnected() {
        return serverConnected;
    }

    // Method to view details of a justification by ID
    public Justification viewJustificationDetails(String id) {
        if (!serverConnected) {
            System.out.println("Error: Cannot view details due to server interruption.");
            return null;
        }
        return justifications.get(id);
    }

    // Method to eliminate (delete) a justification by ID
    public boolean eliminateJustification(String id) {
        if (!serverConnected) {
            System.out.println("Error: Cannot delete justification due to server interruption.");
            return false;
        }
        if (justifications.containsKey(id)) {
            justifications.remove(id);
            System.out.println("Justification with ID " + id + " has been successfully eliminated.");
            return true;
        } else {
            System.out.println("Error: Justification with ID " + id + " not found.");
            return false;
        }
    }

    // Method to return to registry screen (simulated by listing all justifications)
    public void showRegistryScreen() {
        if (!serverConnected) {
            System.out.println("Cannot display registry due to server interruption.");
            return;
        }
        System.out.println("Registry Screen - Current Justifications:");
        if (justifications.isEmpty()) {
            System.out.println("No justifications available.");
        } else {
            for (Justification j : justifications.values()) {
                System.out.println(j);
            }
        }
    }
}

// Class representing an Administrator user
class Administrator {
    private String username;
    private boolean loggedIn;

    public Administrator(String username) {
        this.username = username;
        this.loggedIn = false;
    }

    // Simulate login process
    public boolean login(String password) {
        // Simple login simulation: password "admin123" for demonstration
        if ("admin123".equals(password)) {
            loggedIn = true;
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Login failed. Incorrect password.");
            return false;
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    // Simulate logout or session end
    public void logout() {
        loggedIn = false;
        System.out.println("Logged out.");
    }
}

// Main class to run the program
public class EliminateJustificationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JustificationSystem system = new JustificationSystem();
        Administrator admin = new Administrator("admin");

        System.out.println("=== Eliminate Justification Use Case Simulation ===");

        // Precondition: Administrator must be logged in
        System.out.print("Enter password to login as administrator: ");
        String password = scanner.nextLine();
        if (!admin.login(password)) {
            System.out.println("Precondition failed: Administrator login required. Exiting.");
            scanner.close();
            return;
        }

        // Simulate viewing justification details (precondition: user has viewed details)
        System.out.print("Enter Justification ID to view details (e.g., J001): ");
        String viewId = scanner.nextLine();
        Justification justification = system.viewJustificationDetails(viewId);
        if (justification == null) {
            System.out.println("Justification not found or server error. Exiting.");
            admin.logout();
            scanner.close();
            return;
        }
        System.out.println("Viewing Details: " + justification);

        // Event sequence: User clicks "Delete"
        System.out.print("Do you want to delete this justification? (yes/no): ");
        String choice = scanner.nextLine();
        if ("yes".equalsIgnoreCase(choice)) {
            // Simulate potential server interruption (edge case handling)
            System.out.print("Simulate server interruption? (yes/no): ");
            String interruptChoice = scanner.nextLine();
            if ("yes".equalsIgnoreCase(interruptChoice)) {
                system.interruptServerConnection();
            }

            // Attempt to eliminate the justification
            boolean success = system.eliminateJustification(viewId);
            if (success) {
                // Postcondition: Justification eliminated, return to registry screen
                System.out.println("Returning to registry screen...");
                system.showRegistryScreen();
            } else {
                System.out.println("Deletion failed. Returning to registry screen...");
                system.showRegistryScreen();
            }
        } else {
            System.out.println("Deletion cancelled. Returning to registry screen...");
            system.showRegistryScreen();
        }

        // Clean up
        admin.logout();
        scanner.close();
        System.out.println("Simulation ended.");
    }
}
