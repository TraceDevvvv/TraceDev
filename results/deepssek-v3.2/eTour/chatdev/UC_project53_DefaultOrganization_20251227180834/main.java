/**
 * Main class for the InsertFeedback use case simulation.
 * This program demonstrates the feedback insertion process for a tourist at a site.
 * Simulates connection to ETOUR server and handles interruptions.
 */
import java.util.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {
    // Simulate ETOUR server connection check
    private static boolean isServerConnected = true;
    public static boolean checkServerConnection() {
        // In a real implementation, this would ping the ETOUR server
        // For simulation, we'll use a simple flag that can be toggled
        return isServerConnected;
    }
    public static void simulateServerInterruption() {
        isServerConnected = false;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        // Simulate entry conditions: Tourist is at a particular site
        Site currentSite = new Site(1, "Eiffel Tower");
        Tourist currentTourist = new Tourist(101, "John Doe");
        System.out.println("Welcome to the Feedback System!");
        System.out.println("Tourist: " + currentTourist.getName());
        System.out.println("Current Site: " + currentSite.getName());
        System.out.println();
        // Check server connection before proceeding
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to ETOUR server interrupted.");
            System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
            scanner.close();
            return;
        }
        // Step 1: Activate the feature on the issue of feedback
        System.out.println("Activating feedback feature...");
        System.out.print("Do you want to proceed? (yes/no): ");
        String proceed = scanner.nextLine().trim().toLowerCase();
        if (!proceed.equals("yes")) {
            System.out.println("Exit condition: The Tourist cancelled the operation.");
            scanner.close();
            return;
        }
        // Check server connection again
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to ETOUR server interrupted.");
            System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
            scanner.close();
            return;
        }
        // Step 2: Verify if feedback already exists
        if (dbManager.hasFeedbackForSite(currentTourist.getId(), currentSite.getId())) {
            System.out.println("Error: Feedback already released for this site.");
            System.out.println("Activating use case: FeedbackAlreadyReleased.");
            scanner.close();
            return;
        }
        // Step 3: Display form and collect input
        System.out.println("\n=== Feedback Form for " + currentSite.getName() + " ===");
        // Get vote with validation
        int vote = 0;
        boolean validVote = false;
        while (!validVote) {
            System.out.print("Enter vote (1-5, where 5 is best): ");
            String voteInput = scanner.nextLine().trim();
            // Check for cancellation
            if (voteInput.equalsIgnoreCase("cancel")) {
                System.out.println("Exit condition: The Tourist cancelled the operation.");
                scanner.close();
                return;
            }
            try {
                vote = Integer.parseInt(voteInput);
                if (vote >= 1 && vote <= 5) {
                    validVote = true;
                } else {
                    System.out.println("Error: Vote must be between 1 and 5. Enter 'cancel' to exit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number between 1-5. Enter 'cancel' to exit.");
            }
            // Check server connection
            if (!checkServerConnection()) {
                System.out.println("Error: Connection to ETOUR server interrupted.");
                System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
                scanner.close();
                return;
            }
        }
        // Get comment with validation
        String comment = "";
        boolean validComment = false;
        while (!validComment) {
            System.out.print("Enter comment (minimum 10 characters, maximum 500): ");
            comment = scanner.nextLine().trim();
            // Check for cancellation
            if (comment.equalsIgnoreCase("cancel")) {
                System.out.println("Exit condition: The Tourist cancelled the operation.");
                scanner.close();
                return;
            }
            if (comment.length() < 10) {
                System.out.println("Error: Comment must be at least 10 characters. Enter 'cancel' to exit.");
            } else if (comment.length() > 500) {
                System.out.println("Error: Comment cannot exceed 500 characters. Enter 'cancel' to exit.");
            } else {
                validComment = true;
            }
            // Check server connection
            if (!checkServerConnection()) {
                System.out.println("Error: Connection to ETOUR server interrupted.");
                System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
                scanner.close();
                return;
            }
        }
        // Step 4: Verify data (already done during input collection)
        // Step 5: Confirm issue of feedback
        System.out.println("\n=== Feedback Summary ===");
        System.out.println("Site: " + currentSite.getName());
        System.out.println("Vote: " + vote + "/5");
        System.out.println("Comment: " + comment);
        System.out.print("\nConfirm submission? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        // Check server connection
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to ETOUR server interrupted.");
            System.out.println("Exit condition: Interruption of the connection to the server ETOUR.");
            scanner.close();
            return;
        }
        if (!confirm.equals("yes")) {
            System.out.println("Exit condition: The Tourist cancelled the operation.");
            scanner.close();
            return;
        }
        // Step 6: Save feedback and update visited sites
        try {
            int newFeedbackId = dbManager.getAllFeedbacksSize() + 1;
            Feedback newFeedback = new Feedback(
                newFeedbackId,
                currentTourist.getId(),
                currentSite.getId(),
                vote,
                comment
            );
            dbManager.insertFeedback(newFeedback);
            dbManager.addVisitedSite(currentTourist.getId(), currentSite.getId());
            // Exit condition: Notify success
            System.out.println("\n✓ Success: Feedback submitted for " + currentSite.getName());
            System.out.println("Exit condition: The system shall notify the successful combination of feedback to the site");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Activating use case: Errored.");
        }
        scanner.close();
        // Uncomment to test server interruption scenario:
        // simulateServerInterruption();
    }
}