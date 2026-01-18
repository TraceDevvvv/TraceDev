/**
 * ModifyComment: Complete runnable program implementing the comment modification use case.
 * This consolidated file contains all necessary classes to ensure it's fully runnable.
 */
import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDateTime;
public class ModifyComment {
    // Simulated in-memory database of comments by tourist ID and site ID
    private static HashMap<String, String> commentDatabase = new HashMap<>();
    private static boolean serverConnected = true;
    /**
     * ErroredUseCase handles error scenarios in the comment modification process.
     */
    static class ErroredUseCase {
        /**
         * Handles an error by logging it.
         * @param errorMessage Description of the error that occurred.
         */
        public static void handleError(String errorMessage) {
            System.err.println("Errored use case triggered: " + errorMessage);
        }
    }
    /**
     * Represents a Tourist who can modify comments.
     */
    static class Tourist {
        String touristId;
        String currentSiteId;
        Tourist(String id, String siteId) {
            this.touristId = id;
            this.currentSiteId = siteId;
        }
        String getCombinedKey() {
            return touristId + "_" + currentSiteId;
        }
    }
    /**
     * Comment class represents a comment entity with its properties.
     */
    static class Comment {
        private String touristId;
        private String siteId;
        private String text;
        private LocalDateTime timestamp;
        public Comment(String touristId, String siteId, String text) {
            this.touristId = touristId;
            this.siteId = siteId;
            this.text = text;
            this.timestamp = LocalDateTime.now();
        }
        // Getters and setters
        public String getTouristId() { return touristId; }
        public String getSiteId() { return siteId; }
        public String getText() { return text; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setText(String newText) {
            this.text = newText;
            this.timestamp = LocalDateTime.now();
        }
        @Override
        public String toString() {
            return "Comment{touristId='" + touristId + "', siteId='" + siteId + 
                   "', text='" + text + "', timestamp=" + timestamp + "}";
        }
    }
    /**
     * Site class represents a tourist site (included for completeness).
     */
    static class Site {
        private String siteId;
        private String name;
        private String description;
        public Site(String siteId, String name, String description) {
            this.siteId = siteId;
            this.name = name;
            this.description = description;
        }
        // Getters
        public String getSiteId() { return siteId; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        @Override
        public String toString() {
            return "Site{id='" + siteId + "', name='" + name + 
                   "', description='" + description + "'}";
        }
    }
    /**
     * Validates the new comment text.
     * @param newComment The comment text to validate.
     * @return true if valid, false otherwise.
     */
    private static boolean validateComment(String newComment) {
        if (newComment == null || newComment.trim().isEmpty()) {
            ErroredUseCase.handleError("Comment cannot be empty");
            return false;
        }
        if (newComment.length() > 500) {
            ErroredUseCase.handleError("Comment exceeds 500 characters");
            return false;
        }
        return true;
    }
    /**
     * Simulates checking server connection.
     * @return true if connected to ETOUR server, false otherwise.
     */
    private static boolean checkServerConnection() {
        return serverConnected;
    }
    /**
     * Edits the comment in the database.
     * @param tourist The tourist performing the edit.
     * @param newComment The validated new comment text.
     */
    private static void editComment(Tourist tourist, String newComment) {
        String key = tourist.getCombinedKey();
        commentDatabase.put(key, newComment);
        System.out.println("Comment successfully updated in the database.");
    }
    /**
     * Notifies about the comment alteration.
     * @param tourist The tourist who made the change.
     * @param oldComment The previous comment (if any).
     * @param newComment The updated comment.
     */
    private static void notifyAlteration(Tourist tourist, String oldComment, String newComment) {
        System.out.println("=== Notification ===");
        System.out.println("Tourist ID: " + tourist.touristId);
        System.out.println("Site ID: " + tourist.currentSiteId);
        System.out.println("Previous comment: " + (oldComment != null ? oldComment : "[No previous comment]"));
        System.out.println("Updated comment: " + newComment);
        System.out.println("Timestamp: " + LocalDateTime.now());
        System.out.println("===================");
    }
    /**
     * Main method: entry point of the runnable program.
     * Simulates the complete flow of the ModifyComment use case.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize a tourist with a site (simulating entry condition)
        Tourist tourist = new Tourist("TOURIST_001", "SITE_456");
        // Pre-populate with an existing comment
        commentDatabase.put(tourist.getCombinedKey(), "Original comment: Great site!");
        System.out.println("=== Modify Comment Use Case ===");
        System.out.println("Tourist is viewing details of site: " + tourist.currentSiteId);
        // 1. Choose to change the comment
        System.out.println("\n1. Choose to change the comment");
        System.out.print("Enter new comment: ");
        String newComment = scanner.nextLine();
        // 2. Verify data entered
        System.out.println("\n2. Verifying data...");
        if (!validateComment(newComment)) {
            System.out.println("Validation failed. Exiting.");
            scanner.close();
            return;
        }
        // Check server connection with retry logic to handle interruption
        System.out.println("Checking connection to ETOUR server...");
        int maxRetries = 3;
        int retryCount = 0;
        boolean connectionEstablished = false;
        // Retry loop for server connection
        while (retryCount < maxRetries && !connectionEstablished) {
            if (checkServerConnection()) {
                connectionEstablished = true;
                System.out.println("Successfully connected to ETOUR server.");
            } else {
                retryCount++;
                System.out.println("Connection to ETOUR server failed. Attempt " + 
                                  retryCount + " of " + maxRetries);
                if (retryCount < maxRetries) {
                    System.out.println("Retrying in 2 seconds...");
                    try {
                        Thread.sleep(2000); // Wait before retry
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        // Handle server connection failure
        if (!connectionEstablished) {
            ErroredUseCase.handleError("Connection to server ETOUR interrupted after " + 
                                      maxRetries + " attempts");
            System.out.println("Cannot proceed without server connection. Please try again later.");
            scanner.close();
            return;
        }
        // Ask for confirmation
        System.out.println("\nNew comment will be: " + newComment);
        System.out.print("3. Confirm change? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (!confirmation.equals("yes")) {
            System.out.println("Change cancelled by user.");
            scanner.close();
            return;
        }
        // 4. Edit commentary on selected feedback
        System.out.println("\n4. Editing commentary...");
        String oldComment = commentDatabase.get(tourist.getCombinedKey());
        editComment(tourist, newComment);
        // Exit condition: Notify alterations
        System.out.println("\nExit condition: System notifies alterations");
        notifyAlteration(tourist, oldComment, newComment);
        System.out.println("\nUse case completed successfully.");
        scanner.close();
    }
    // Methods to simulate server connection changes (for testing)
    static void disconnectServer() {
        serverConnected = false;
    }
    static void reconnectServer() {
        serverConnected = true;
    }
}