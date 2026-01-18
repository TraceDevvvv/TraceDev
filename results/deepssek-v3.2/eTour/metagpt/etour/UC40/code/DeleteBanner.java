import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Main class for the DeleteBanner use case.
 * This program simulates the deletion of a banner ad associated with a point of rest (restaurant).
 * It includes authentication, banner listing, selection, confirmation, and deletion.
 */
public class DeleteBanner {
    
    /**
     * Represents a Banner with an ID and description.
     */
    static class Banner {
        private final int id;
        private final String description;
        
        public Banner(int id, String description) {
            this.id = id;
            this.description = description;
        }
        
        public int getId() {
            return id;
        }
        
        public String getDescription() {
            return description;
        }
        
        @Override
        public String toString() {
            return "Banner ID: " + id + " - " + description;
        }
    }
    
    /**
     * Manages banners for a point of rest (restaurant).
     */
    static class BannerManager {
        private final List<Banner> banners;
        
        public BannerManager() {
            banners = new ArrayList<>();
            // Initialize with some sample banners
            banners.add(new Banner(1, "Summer Special Offer"));
            banners.add(new Banner(2, "New Menu Launch"));
            banners.add(new Banner(3, "Weekend Discount"));
        }
        
        /**
         * Returns a list of all banners.
         * @return List of Banner objects
         */
        public List<Banner> getBanners() {
            return new ArrayList<>(banners); // Return a copy to prevent external modification
        }
        
        /**
         * Deletes a banner by ID.
         * @param bannerId The ID of the banner to delete
         * @return true if deletion was successful, false otherwise
         */
        public boolean deleteBanner(int bannerId) {
            for (int i = 0; i < banners.size(); i++) {
                if (banners.get(i).getId() == bannerId) {
                    banners.remove(i);
                    return true;
                }
            }
            return false; // Banner not found
        }
        
        /**
         * Checks if a banner with the given ID exists.
         * @param bannerId The ID to check
         * @return true if exists, false otherwise
         */
        public boolean bannerExists(int bannerId) {
            for (Banner banner : banners) {
                if (banner.getId() == bannerId) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * Handles authentication for the Point of Restaurant Operator.
     */
    static class AuthenticationService {
        private final String validUsername = "operator";
        private final String validPassword = "password123";
        private boolean isAuthenticated = false;
        
        /**
         * Authenticates the user with username and password.
         * @param username The username
         * @param password The password
         * @return true if authentication successful, false otherwise
         */
        public boolean authenticate(String username, String password) {
            isAuthenticated = validUsername.equals(username) && validPassword.equals(password);
            return isAuthenticated;
        }
        
        /**
         * Checks if the user is currently authenticated.
         * @return true if authenticated, false otherwise
         */
        public boolean isAuthenticated() {
            return isAuthenticated;
        }
        
        /**
         * Logs out the user.
         */
        public void logout() {
            isAuthenticated = false;
        }
    }
    
    /**
     * Simulates server connection to ETOUR.
     */
    static class ServerConnection {
        private final AtomicBoolean isConnected = new AtomicBoolean(true);
        
        /**
         * Checks if connected to the server.
         * @return true if connected, false otherwise
         */
        public boolean isConnected() {
            return isConnected.get();
        }
        
        /**
         * Simulates a server connection interruption.
         */
        public void simulateConnectionInterruption() {
            isConnected.set(false);
        }
        
        /**
         * Re-establishes server connection.
         */
        public void reconnect() {
            isConnected.set(true);
        }
    }
    
    /**
     * Main application class that orchestrates the banner deletion flow.
     */
    static class BannerDeletionApp {
        private final BannerManager bannerManager;
        private final AuthenticationService authService;
        private final ServerConnection serverConnection;
        private final Scanner scanner;
        
        public BannerDeletionApp() {
            bannerManager = new BannerManager();
            authService = new AuthenticationService();
            serverConnection = new ServerConnection();
            scanner = new Scanner(System.in);
        }
        
        /**
         * Main entry point for the banner deletion application.
         */
        public void run() {
            System.out.println("=== Banner Deletion System ===");
            
            // Step 0: Authentication (Entry condition)
            if (!authenticateUser()) {
                System.out.println("Authentication failed. Exiting application.");
                return;
            }
            
            // Main application loop
            boolean running = true;
            while (running) {
                displayMainMenu();
                int choice = getIntInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        deleteBannerFlow();
                        break;
                    case 2:
                        viewBanners();
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        authService.logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
            scanner.close();
            System.out.println("Application terminated.");
        }
        
        /**
         * Handles user authentication.
         * @return true if authentication successful, false otherwise
         */
        private boolean authenticateUser() {
            System.out.println("\n=== Authentication ===");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            boolean authenticated = authService.authenticate(username, password);
            if (authenticated) {
                System.out.println("Authentication successful!");
            } else {
                System.out.println("Authentication failed!");
            }
            return authenticated;
        }
        
        /**
         * Displays the main menu.
         */
        private void displayMainMenu() {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Delete a banner");
            System.out.println("2. View all banners");
            System.out.println("3. Logout and exit");
        }
        
        /**
         * Implements the complete banner deletion flow.
         */
        private void deleteBannerFlow() {
            System.out.println("\n=== Banner Deletion Flow ===");
            
            // Check server connection
            if (!serverConnection.isConnected()) {
                System.out.println("Error: Connection to ETOUR server interrupted.");
                System.out.println("Please try again later.");
                return;
            }
            
            // Step 1: Select the feature for removal of the banner
            System.out.println("Step 1: Selected banner deletion feature.");
            
            // Step 2: View the list of banners associated with the point of rest
            System.out.println("\nStep 2: Viewing banners associated with the point of rest:");
            viewBanners();
            
            if (bannerManager.getBanners().isEmpty()) {
                System.out.println("No banners available for deletion.");
                return;
            }
            
            // Step 3: Select a banner from the list and enter the function of elimination
            System.out.print("\nStep 3: Enter the ID of the banner to delete: ");
            int bannerId;
            try {
                bannerId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric ID.");
                return;
            }
            
            // Validate banner exists
            if (!bannerManager.bannerExists(bannerId)) {
                System.out.println("Error: Banner with ID " + bannerId + " not found.");
                return;
            }
            
            // Step 4: Displays a message confirming the deletion
            System.out.println("\nStep 4: Confirmation Message");
            System.out.println("You are about to delete banner ID: " + bannerId);
            System.out.println("This action cannot be undone.");
            
            // Step 5: Confirm the operation
            System.out.print("\nStep 5: Confirm deletion? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("yes")) {
                System.out.println("Deletion cancelled by operator.");
                return; // Exit condition: Operator cancels the operation
            }
            
            // Step 6: Removes the banner
            System.out.println("\nStep 6: Removing banner...");
            boolean success = bannerManager.deleteBanner(bannerId);
            
            if (success) {
                System.out.println("SUCCESS: Banner with ID " + bannerId + " has been deleted.");
                // Exit condition: Successful elimination notification
            } else {
                System.out.println("ERROR: Failed to delete banner with ID " + bannerId);
            }
        }
        
        /**
         * Displays all banners.
         */
        private void viewBanners() {
            List<Banner> banners = bannerManager.getBanners();
            if (banners.isEmpty()) {
                System.out.println("No banners available.");
            } else {
                for (Banner banner : banners) {
                    System.out.println(banner);
                }
            }
        }
        
        /**
         * Helper method to get integer input with validation.
         * @param prompt The prompt to display
         * @return The integer input
         */
        private int getIntInput(String prompt) {
            while (true) {
                System.out.print(prompt);
                try {
                    return Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
    }
    
    /**
     * Main method to start the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        BannerDeletionApp app = new BannerDeletionApp();
        app.run();
    }
}