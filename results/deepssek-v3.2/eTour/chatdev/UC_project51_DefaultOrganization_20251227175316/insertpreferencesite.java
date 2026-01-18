/**
 * Main class implementing the InsertPreferenceSite use case.
 * Allows tourists to bookmark sites with server connection handling.
 * Follows the use case flow: activate feature, prompt inclusion,
 * confirm input, insert site, handle server interruptions.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class InsertPreferenceSite {
    private ServerConnection serverConnection;
    private List<String> bookmarks;
    private Scanner scanner;
    /**
     * Constructor initializes server connection and bookmarks list.
     */
    public InsertPreferenceSite() {
        serverConnection = new ServerConnection("ETOUR");
        bookmarks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    /**
     * Gets the server connection instance.
     * @return The server connection.
     */
    public ServerConnection getServerConnection() {
        return serverConnection;
    }
    /**
     * Activates the bookmark insertion feature.
     * Implements the full use case flow from activation to completion.
     */
    public void activateBookmarkFeature() {
        System.out.println("=== Bookmark Insertion Feature Activated ===");
        // Step 2: Prompt for site inclusion
        String siteName = promptSiteInclusion();
        if (siteName == null) return;
        // Check server connection before proceeding
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to server " + 
                serverConnection.getServerName() + " interrupted. Operation aborted.");
            return;
        }
        // Step 3: Confirm the input
        if (!confirmInput(siteName)) {
            System.out.println("Bookmark cancelled.");
            return;
        }
        // Final server connection check before insertion
        if (!checkServerConnection()) {
            System.out.println("Error: Connection lost during operation. Please try again.");
            return;
        }
        // Step 4: Insert the selected site into bookmarks
        insertSiteToBookmarks(siteName);
    }
    /**
     * Prompts user for site name input.
     * @return The site name or null if invalid.
     */
    private String promptSiteInclusion() {
        System.out.print("Enter the site name to bookmark: ");
        String siteName = scanner.nextLine().trim();
        if (siteName.isEmpty()) {
            System.out.println("Error: Site name cannot be empty.");
            return null;
        }
        return siteName;
    }
    /**
     * Confirms the bookmarking action with user.
     * @param siteName The site to be bookmarked.
     * @return true if confirmed, false otherwise.
     */
    private boolean confirmInput(String siteName) {
        System.out.print("Confirm bookmarking '" + siteName + "'? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes");
    }
    /**
     * Checks server connection status.
     * @return true if connected, false otherwise.
     */
    private boolean checkServerConnection() {
        return serverConnection.isConnected();
    }
    /**
     * Inserts a site into the bookmarks list.
     * @param siteName The name of the site to bookmark.
     */
    private void insertSiteToBookmarks(String siteName) {
        // Check for duplicate entry
        if (bookmarks.contains(siteName)) {
            System.out.println("Site '" + siteName + "' is already bookmarked.");
            return;
        }
        // Add to bookmarks
        bookmarks.add(siteName);
        // Exit condition: Notification
        System.out.println("Success: Site '" + siteName + "' has been added to your bookmarks!");
        System.out.println("Total bookmarks: " + bookmarks.size());
    }
    /**
     * Displays all bookmarked sites.
     */
    public void displayBookmarks() {
        if (bookmarks.isEmpty()) {
            System.out.println("No bookmarks yet.");
        } else {
            System.out.println("=== Your Bookmarked Sites ===");
            for (int i = 0; i < bookmarks.size(); i++) {
                System.out.println((i + 1) + ". " + bookmarks.get(i));
            }
        }
    }
    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void closeResources() {
        scanner.close();
    }
    /**
     * Main method to run the program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        InsertPreferenceSite app = new InsertPreferenceSite();
        try {
            System.out.println("Tourist card is at a particular site.");
            System.out.println("Server: " + app.getServerConnection().getServerName());
            // Activate the bookmark feature
            app.activateBookmarkFeature();
            // Display updated bookmarks
            app.displayBookmarks();
        } finally {
            app.closeResources();
        }
    }
}