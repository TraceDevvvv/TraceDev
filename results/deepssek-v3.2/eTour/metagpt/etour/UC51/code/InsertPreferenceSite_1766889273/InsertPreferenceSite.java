import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * InsertPreferenceSite - A program that allows tourists to bookmark favorite sites
 * while handling server connectivity and edge cases.
 * 
 * This program demonstrates the "InsertPreferenceSite" use case where:
 * 1. A tourist can select a site from available sites
 * 2. The system prompts for confirmation
 * 3. The site is added to the tourist's bookmarks
 * 4. Handles server connectivity issues with proper error handling
 */
public class InsertPreferenceSite {
    
    /**
     * Represents a tourist with a card and bookmark list
     */
    static class Tourist {
        private final String name;
        private Site currentSite; // Current site where tourist card is located
        private final List<Site> bookmarks; // List of bookmarked sites
        
        public Tourist(String name) {
            this.name = name;
            this.bookmarks = new ArrayList<>();
        }
        
        public String getName() {
            return name;
        }
        
        public Site getCurrentSite() {
            return currentSite;
        }
        
        public void setCurrentSite(Site site) {
            this.currentSite = site;
        }
        
        public List<Site> getBookmarks() {
            return new ArrayList<>(bookmarks); // Return copy for encapsulation
        }
        
        /**
         * Adds a site to bookmarks if not already present
         * @param site The site to bookmark
         * @return true if added successfully, false if already bookmarked
         */
        public boolean addBookmark(Site site) {
            if (site == null) {
                System.out.println("Error: Cannot bookmark a null site");
                return false;
            }
            
            // Check if site is already bookmarked
            for (Site bookmark : bookmarks) {
                if (bookmark.getId() == site.getId()) {
                    System.out.println("Site '" + site.getName() + "' is already in your bookmarks!");
                    return false;
                }
            }
            
            bookmarks.add(site);
            return true;
        }
        
        /**
         * Displays all bookmarked sites
         */
        public void displayBookmarks() {
            if (bookmarks.isEmpty()) {
                System.out.println("You have no bookmarked sites yet.");
                return;
            }
            
            System.out.println("\n=== Your Bookmarked Sites ===");
            for (int i = 0; i < bookmarks.size(); i++) {
                System.out.println((i + 1) + ". " + bookmarks.get(i));
            }
            System.out.println("============================\n");
        }
    }
    
    /**
     * Represents a tourist site with ID, name, and description
     */
    static class Site {
        private final int id;
        private final String name;
        private final String description;
        
        public Site(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        
        public int getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public String getDescription() {
            return description;
        }
        
        @Override
        public String toString() {
            return name + " (ID: " + id + ") - " + description;
        }
    }
    
    /**
     * Manages bookmark operations and server connectivity
     */
    static class BookmarkManager {
        private final Tourist tourist;
        private final List<Site> availableSites;
        private boolean serverConnected;
        
        public BookmarkManager(Tourist tourist) {
            this.tourist = tourist;
            this.availableSites = new ArrayList<>();
            this.serverConnected = true; // Assume server is initially connected
            
            // Initialize with some sample sites
            initializeSampleSites();
        }
        
        private void initializeSampleSites() {
            availableSites.add(new Site(1, "Eiffel Tower", "Iconic iron tower in Paris, France"));
            availableSites.add(new Site(2, "Colosseum", "Ancient amphitheater in Rome, Italy"));
            availableSites.add(new Site(3, "Statue of Liberty", "Symbol of freedom in New York, USA"));
            availableSites.add(new Site(4, "Great Wall of China", "Historic fortification in China"));
            availableSites.add(new Site(5, "Taj Mahal", "White marble mausoleum in India"));
        }
        
        /**
         * Simulates server connection check
         * @return true if server is connected, false otherwise
         */
        public boolean checkServerConnection() {
            // Simulate occasional server disconnection (1 in 5 chance)
            if (Math.random() < 0.2) {
                serverConnected = false;
                System.out.println("Warning: Lost connection to ETOUR server!");
            } else {
                serverConnected = true;
            }
            return serverConnected;
        }
        
        /**
         * Attempts to reconnect to server with timeout
         * @return true if reconnected successfully, false otherwise
         */
        public boolean reconnectToServer() {
            System.out.println("Attempting to reconnect to ETOUR server...");
            
            try {
                // Simulate reconnection delay (2 seconds max)
                TimeUnit.MILLISECONDS.sleep(1500);
                serverConnected = true;
                System.out.println("Successfully reconnected to ETOUR server!");
                return true;
            } catch (InterruptedException e) {
                System.out.println("Reconnection interrupted: " + e.getMessage());
                return false;
            }
        }
        
        /**
         * Displays available sites for selection
         */
        public void displayAvailableSites() {
            System.out.println("\n=== Available Tourist Sites ===");
            for (int i = 0; i < availableSites.size(); i++) {
                System.out.println((i + 1) + ". " + availableSites.get(i));
            }
            System.out.println("===============================\n");
        }
        
        /**
         * Main method to handle the bookmark insertion process
         * Follows the use case flow: activate, prompt, confirm, insert
         */
        public void insertPreferenceSite() {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("=== Insert Preference Site Feature ===");
            System.out.println("Tourist: " + tourist.getName());
            
            // Check entry condition: tourist must be at a site
            if (tourist.getCurrentSite() == null) {
                System.out.println("Error: Tourist card is not at any site. Please set current site first.");
                return;
            }
            
            System.out.println("Current Location: " + tourist.getCurrentSite());
            
            // 1. Activate the feature for insertion
            System.out.println("\n1. Feature activated: Insert selected site to bookmarks");
            
            // Check server connection before proceeding
            if (!checkServerConnection()) {
                System.out.println("Error: Cannot proceed without server connection.");
                
                // Try to reconnect
                if (!reconnectToServer()) {
                    System.out.println("Failed to reconnect. Operation aborted.");
                    return;
                }
            }
            
            // 2. Prompt for site selection
            System.out.println("\n2. Prompting for site selection...");
            displayAvailableSites();
            
            Site selectedSite = null;
            while (selectedSite == null) {
                try {
                    System.out.print("Select a site number to bookmark (1-" + availableSites.size() + "): ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    if (choice < 1 || choice > availableSites.size()) {
                        System.out.println("Invalid choice. Please select a number between 1 and " + availableSites.size());
                        continue;
                    }
                    
                    selectedSite = availableSites.get(choice - 1);
                    
                    // Check if selected site is the current site (as per entry condition)
                    if (selectedSite.getId() == tourist.getCurrentSite().getId()) {
                        System.out.println("You're already at this site! Setting as selected.");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            
            // 3. Confirm the input
            System.out.println("\n3. Please confirm your selection:");
            System.out.println("You selected: " + selectedSite);
            System.out.print("Confirm bookmarking this site? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("yes") && !confirmation.equals("y")) {
                System.out.println("Bookmarking cancelled by user.");
                return;
            }
            
            // Double-check server connection before final insertion
            if (!checkServerConnection()) {
                System.out.println("Error: Server connection lost during confirmation!");
                if (!reconnectToServer()) {
                    System.out.println("Failed to reconnect. Operation aborted.");
                    return;
                }
            }
            
            // 4. Insert the selected site in the list of bookmarks
            System.out.println("\n4. Inserting selected site to bookmarks...");
            
            if (tourist.addBookmark(selectedSite)) {
                System.out.println("✓ Successfully bookmarked: " + selectedSite.getName());
                
                // Exit condition: Show notification
                System.out.println("\n=== Exit Condition: Notification ===");
                System.out.println("Notification: Site '" + selectedSite.getName() + 
                                 "' has been added to your favorites!");
                
                // Display updated bookmarks
                tourist.displayBookmarks();
            } else {
                System.out.println("Failed to bookmark the site.");
            }
        }
    }
    
    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Tourist Bookmark System ===\n");
        
        // Create a tourist
        System.out.print("Enter tourist name: ");
        String touristName = scanner.nextLine().trim();
        Tourist tourist = new Tourist(touristName.isEmpty() ? "Default Tourist" : touristName);
        
        // Create bookmark manager
        BookmarkManager manager = new BookmarkManager(tourist);
        
        // Set tourist's current site (entry condition)
        System.out.println("\nSetting up tourist's current location...");
        manager.displayAvailableSites();
        
        Site currentSite = null;
        while (currentSite == null) {
            try {
                System.out.print("Select current site number (1-5): ");
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please select between 1 and 5.");
                    continue;
                }
                
                currentSite = manager.availableSites.get(choice - 1);
                tourist.setCurrentSite(currentSite);
                System.out.println("✓ Current location set to: " + currentSite.getName());
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        // Main program loop
        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Insert preference site (bookmark)");
            System.out.println("2. View my bookmarks");
            System.out.println("3. View available sites");
            System.out.println("4. Test server connection");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manager.insertPreferenceSite();
                        break;
                    case 2:
                        tourist.displayBookmarks();
                        break;
                    case 3:
                        manager.displayAvailableSites();
                        break;
                    case 4:
                        boolean connected = manager.checkServerConnection();
                        System.out.println("Server connection status: " + 
                                         (connected ? "CONNECTED" : "DISCONNECTED"));
                        break;
                    case 5:
                        running = false;
                        System.out.println("Thank you for using Tourist Bookmark System!");
                        break;
                    default:
                        System.out.println("Invalid option. Please select 1-5.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        
        scanner.close();
    }
}