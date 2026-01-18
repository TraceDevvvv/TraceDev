/**
 * Main entry point for the InsertTag application.
 * This program simulates the InsertTag use case for an agency operator.
 * It provides a console-based interface for entering a new tag,
 * validates the input, checks for duplicates, and handles server interruptions.
 */
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Main {
    // Simulate a database of existing tags (in a real application, this would be a database connection)
    private static Set<String> existingTags = new HashSet<>();
    // Simulate server connection status (true = connected, false = disconnected)
    private static boolean serverConnected = true;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Simulate initial database connection
        System.out.println("Connecting to server...");
        simulateServerConnection();
        if (!serverConnected) {
            System.out.println("Error: Server connection interrupted (ETOUR). Exiting.");
            return;
        }
        System.out.println("Welcome, Agency Operator!");
        // Step 1: Access the functionality of inserting new tag search
        System.out.println("\n--- Insert New Tag Search ---");
        // Step 2: Show the form for entering a tag
        System.out.print("Enter tag name: ");
        String tagName = scanner.nextLine().trim();
        // Step 3: Form submitted implicitly via console input
        // Step 4: Verify data and check for duplicates
        try {
            if (!serverConnected) {
                throw new Exception("Server connection interrupted (ETOUR).");
            }
            // Validate the tag name
            if (isValidTag(tagName)) {
                // Check if tag already exists
                if (existingTags.contains(tagName)) {
                    // Activate use case ExistingErrorTag
                    System.out.println("Error: Tag '" + tagName + "' already exists in the system.");
                } else {
                    // Simulate database insertion
                    existingTags.add(tagName);
                    // Exit condition: notification about inclusion
                    System.out.println("Success: Tag '" + tagName + "' has been added to the system.");
                }
            } else {
                // Activate use case Errored for invalid/in sufficient data
                System.out.println("Error: Invalid tag name. Tag must be 1-100 characters and contain only letters, numbers, and underscores.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        scanner.close();
    }
    /**
     * Validates the tag name.
     * A valid tag is non-empty, 1-100 characters, and contains only alphanumeric characters and underscores.
     * @param tag The tag name to validate
     * @return true if valid, false otherwise
     */
    private static boolean isValidTag(String tag) {
        if (tag == null || tag.isEmpty() || tag.length() > 100) {
            return false;
        }
        // Regex: allows letters, numbers, and underscores
        return tag.matches("^[a-zA-Z0-9_]+$");
    }
    /**
     * Simulates server connection with potential interruption (ETOUR).
     * In a real application, this would involve actual network/database connectivity checks.
     */
    private static void simulateServerConnection() {
        // For simulation, we'll assume 90% chance of connection success
        double random = Math.random();
        if (random < 0.1) { // 10% chance of interruption
            serverConnected = false;
        }
    }
}