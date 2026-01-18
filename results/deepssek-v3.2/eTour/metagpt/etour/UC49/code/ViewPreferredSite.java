import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the ViewPreferredSite use case.
 * This program allows an authenticated tourist to view their list of personal favorites/bookmarks.
 * Handles server interruption (ETOUR) edge cases.
 */
public class ViewPreferredSite {
    
    /**
     * Represents a bookmark/favorite site with name and description.
     */
    static class Bookmark {
        private String siteName;
        private String description;
        
        public Bookmark(String siteName, String description) {
            this.siteName = siteName;
            this.description = description;
        }
        
        public String getSiteName() {
            return siteName;
        }
        
        public String getDescription() {
            return description;
        }
        
        @Override
        public String toString() {
            return "Site: " + siteName + " | Description: " + description;
        }
    }
    
    /**
     * Simulates the ETOUR server connection for retrieving bookmarks.
     * In a real application, this would connect to an actual server.
     */
    static class ETourServer {
        private boolean serverAvailable = true;
        
        /**
         * Simulates server interruption by randomly setting availability.
         * In production, this would detect actual network/server issues.
         */
        public void simulateServerInterruption() {
            // Simulate 20% chance of server interruption for demonstration
            if (Math.random() < 0.2) {
                serverAvailable = false;
            } else {
                serverAvailable = true;
            }
        }
        
        /**
         * Checks if the server is available.
         * @return true if server is available, false otherwise
         */
        public boolean isServerAvailable() {
            return serverAvailable;
        }
        
        /**
         * Retrieves bookmarks from the server.
         * Simulates server call with potential interruption.
         * @param touristId the ID of the authenticated tourist
         * @return list of bookmarks or empty list if server unavailable
         * @throws ServerInterruptedException if server connection is lost
         */
        public List<Bookmark> getBookmarks(String touristId) throws ServerInterruptedException {
            if (!serverAvailable) {
                throw new ServerInterruptedException("ETOUR server connection interrupted");
            }
            
            // Simulate server delay
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Simulated data - in real application, this would come from database/API
            List<Bookmark> bookmarks = new ArrayList<>();
            bookmarks.add(new Bookmark("Eiffel Tower", "Iconic iron tower in Paris, France"));
            bookmarks.add(new Bookmark("Statue of Liberty", "Colossal neoclassical sculpture in New York Harbor"));
            bookmarks.add(new Bookmark("Great Wall of China", "Series of fortifications in northern China"));
            bookmarks.add(new Bookmark("Taj Mahal", "White marble mausoleum in Agra, India"));
            bookmarks.add(new Bookmark("Machu Picchu", "15th-century Inca citadel in Peru"));
            
            return bookmarks;
        }
    }
    
    /**
     * Custom exception for server interruption.
     */
    static class ServerInterruptedException extends Exception {
        public ServerInterruptedException(String message) {
            super(message);
        }
    }
    
    /**
     * Manages tourist authentication.
     */
    static class AuthenticationManager {
        private boolean isAuthenticated = false;
        private String currentTouristId;
        
        /**
         * Simulates tourist authentication.
         * In real application, this would validate credentials against a database.
         * @param touristId the tourist identifier
         * @param password the password
         * @return true if authentication successful, false otherwise
         */
        public boolean authenticate(String touristId, String password) {
            // Simulated authentication - in production, use secure authentication
            if (touristId != null && !touristId.trim().isEmpty() && 
                password != null && !password.trim().isEmpty()) {
                isAuthenticated = true;
                currentTouristId = touristId;
                return true;
            }
            return false;
        }
        
        /**
         * Checks if a tourist is authenticated.
         * @return true if authenticated, false otherwise
         */
        public boolean isAuthenticated() {
            return isAuthenticated;
        }
        
        /**
         * Gets the current authenticated tourist ID.
         * @return tourist ID or null if not authenticated
         */
        public String getCurrentTouristId() {
            return currentTouristId;
        }
        
        /**
         * Logs out the current tourist.
         */
        public void logout() {
            isAuthenticated = false;
            currentTouristId = null;
        }
    }
    
    /**
     * Main method - entry point of the program.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthenticationManager authManager = new AuthenticationManager();
        ETourServer server = new ETourServer();
        
        System.out.println("=== ViewPreferredSite - Personal Favorites System ===");
        
        // Step 1: Authentication (Entry condition: Tourist has successfully authenticated)
        System.out.println("\n--- Authentication Required ---");
        System.out.print("Enter Tourist ID: ");
        String touristId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        
        if (!authManager.authenticate(touristId, password)) {
            System.out.println("Authentication failed. Please check your credentials.");
            scanner.close();
            return;
        }
        
        System.out.println("Authentication successful! Welcome, " + touristId);
        
        // Main program loop
        boolean running = true;
        while (running && authManager.isAuthenticated()) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Personal Favorites (Bookmarks)");
            System.out.println("2. Exit");
            System.out.print("Select option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    // Flow of events: User selects feature to display personal favorites
                    displayPersonalFavorites(authManager, server);
                    break;
                case "2":
                    System.out.println("Thank you for using ViewPreferredSite. Goodbye!");
                    authManager.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the list of personal favorites/bookmarks.
     * Handles the main flow: upload list of bookmarks and display them.
     * @param authManager the authentication manager
     * @param server the ETOUR server connection
     */
    private static void displayPersonalFavorites(AuthenticationManager authManager, ETourServer server) {
        System.out.println("\n--- Loading Personal Favorites ---");
        
        // Simulate potential server interruption
        server.simulateServerInterruption();
        
        try {
            // Flow of events: System uploads the list of bookmarks
            System.out.println("Connecting to ETOUR server...");
            List<Bookmark> bookmarks = server.getBookmarks(authManager.getCurrentTouristId());
            
            // Exit condition: System displays the list of bookmarks
            System.out.println("\n=== Your Personal Favorites ===");
            
            if (bookmarks.isEmpty()) {
                System.out.println("No bookmarks found. Start adding your favorite sites!");
            } else {
                for (int i = 0; i < bookmarks.size(); i++) {
                    System.out.println((i + 1) + ". " + bookmarks.get(i));
                }
                System.out.println("\nTotal bookmarks: " + bookmarks.size());
            }
            
            System.out.println("===============================");
            
        } catch (ServerInterruptedException e) {
            // Handle interruption of connection to server ETOUR
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Please check your internet connection and try again.");
            System.out.println("You can try again later when the server is available.");
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.out.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Please contact system administrator.");
        }
    }
}