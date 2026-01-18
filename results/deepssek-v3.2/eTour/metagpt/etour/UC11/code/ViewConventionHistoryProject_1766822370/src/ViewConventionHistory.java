import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Main class for the ViewConventionHistory application.
 * This program allows an Agency Operator to view the history of conventions
 * derived from a selected point of rest (restaurant).
 */
public class ViewConventionHistory {
    
    /**
     * Represents a Convention with details about an event.
     */
    static class Convention {
        private String id;
        private String name;
        private LocalDateTime dateTime;
        private String location;
        private String description;
        
        public Convention(String id, String name, LocalDateTime dateTime, String location, String description) {
            this.id = id;
            this.name = name;
            this.dateTime = dateTime;
            this.location = location;
            this.description = description;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public LocalDateTime getDateTime() { return dateTime; }
        public String getLocation() { return location; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return String.format("Convention ID: %s\nName: %s\nDate/Time: %s\nLocation: %s\nDescription: %s\n",
                    id, name, dateTime.format(formatter), location, description);
        }
    }
    
    /**
     * Represents a Restaurant (point of rest) with conventions history.
     */
    static class Restaurant {
        private String id;
        private String name;
        private String address;
        private List<Convention> conventions;
        
        public Restaurant(String id, String name, String address) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.conventions = new ArrayList<>();
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getAddress() { return address; }
        public List<Convention> getConventions() { return conventions; }
        
        public void addConvention(Convention convention) {
            conventions.add(convention);
        }
        
        @Override
        public String toString() {
            return String.format("Restaurant ID: %s\nName: %s\nAddress: %s\nTotal Conventions: %d",
                    id, name, address, conventions.size());
        }
    }
    
    /**
     * Service class that handles convention history operations.
     * Simulates connection to ETOUR server and handles interruptions.
     */
    static class ConventionHistoryService {
        private static final String SERVER_NAME = "ETOUR";
        private boolean serverConnected = true;
        
        /**
         * Simulates uploading convention data from a selected restaurant.
         * Handles potential server connection interruptions.
         * 
         * @param restaurant The restaurant to upload data from
         * @return List of conventions or empty list if connection fails
         */
        public List<Convention> uploadConventionData(Restaurant restaurant) {
            System.out.println("Connecting to " + SERVER_NAME + " server...");
            
            // Simulate server connection with potential interruption
            if (!checkServerConnection()) {
                System.out.println("ERROR: Connection to " + SERVER_NAME + " server interrupted!");
                return new ArrayList<>();
            }
            
            System.out.println("Successfully connected to " + SERVER_NAME + " server.");
            System.out.println("Uploading convention data for restaurant: " + restaurant.getName());
            
            // Simulate data processing delay
            simulateProcessingDelay();
            
            if (!checkServerConnection()) {
                System.out.println("ERROR: Connection lost during data upload!");
                return new ArrayList<>();
            }
            
            System.out.println("Convention data uploaded successfully.");
            return restaurant.getConventions();
        }
        
        /**
         * Checks if the server connection is still active.
         * Simulates random connection failures for demonstration.
         * 
         * @return true if connected, false if interrupted
         */
        private boolean checkServerConnection() {
            // Simulate occasional connection interruptions (10% chance)
            if (Math.random() < 0.1) {
                serverConnected = false;
                return false;
            }
            serverConnected = true;
            return true;
        }
        
        /**
         * Simulates processing delay for data upload.
         */
        private void simulateProcessingDelay() {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Processing interrupted.");
            }
        }
        
        /**
         * Displays the history of conventions for a restaurant.
         * 
         * @param conventions List of conventions to display
         */
        public void displayConventionHistory(List<Convention> conventions) {
            if (conventions == null || conventions.isEmpty()) {
                System.out.println("No convention history available.");
                return;
            }
            
            System.out.println("\n=== CONVENTION HISTORY ===");
            System.out.println("Total conventions found: " + conventions.size());
            System.out.println("===========================\n");
            
            for (int i = 0; i < conventions.size(); i++) {
                System.out.println("Convention #" + (i + 1) + ":");
                System.out.println(conventions.get(i));
                if (i < conventions.size() - 1) {
                    System.out.println("---");
                }
            }
        }
    }
    
    /**
     * Main method - entry point of the application.
     * Implements the use case flow:
     * 1. Access features to display historical conventions
     * 2. Upload data on conventions from the selected restaurant
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConventionHistoryService service = new ConventionHistoryService();
        
        System.out.println("=== VIEW CONVENTION HISTORY SYSTEM ===");
        System.out.println("Participating Actor: Agency Operator");
        System.out.println("=======================================\n");
        
        // Step 1: Access the features on the display of historical conventions
        System.out.println("Step 1: Accessing historical conventions features...");
        
        // Create sample restaurants with conventions for demonstration
        List<Restaurant> restaurants = createSampleRestaurants();
        
        // Display available restaurants
        System.out.println("\nAvailable Restaurants (Points of Rest):");
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant r = restaurants.get(i);
            System.out.println((i + 1) + ". " + r.getName() + " - " + r.getAddress());
        }
        
        // Step 2: Upload data on conventions from the restaurant selected
        System.out.print("\nSelect a restaurant (1-" + restaurants.size() + "): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice < 1 || choice > restaurants.size()) {
                System.out.println("Invalid selection. Exiting.");
                return;
            }
            
            Restaurant selectedRestaurant = restaurants.get(choice - 1);
            System.out.println("\nSelected Restaurant: " + selectedRestaurant.getName());
            
            // Upload convention data from selected restaurant
            List<Convention> conventions = service.uploadConventionData(selectedRestaurant);
            
            // Exit condition: Display the history of conventions
            if (!conventions.isEmpty()) {
                service.displayConventionHistory(conventions);
                System.out.println("\n=== SYSTEM STATUS: Convention history displayed successfully ===");
            } else {
                System.out.println("\n=== SYSTEM STATUS: Failed to retrieve convention history ===");
                System.out.println("Possible reasons:");
                System.out.println("1. Connection to " + ConventionHistoryService.SERVER_NAME + " server interrupted");
                System.out.println("2. No conventions found for the selected restaurant");
                System.out.println("3. Server timeout or network issues");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("\nSystem shutdown complete.");
        }
    }
    
    /**
     * Creates sample restaurants with convention data for demonstration.
     * 
     * @return List of sample restaurants
     */
    private static List<Restaurant> createSampleRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        
        // Restaurant 1
        Restaurant r1 = new Restaurant("R001", "Gourmet Haven", "123 Main St, Cityville");
        r1.addConvention(new Convention("C101", "Annual Food Safety Conference", 
            LocalDateTime.of(2024, 3, 15, 9, 0), "Main Hall", 
            "Annual conference focusing on food safety standards and best pract"));
        r1.addConvention(new Convention("C102", "Culinary Innovation Summit", 
            LocalDateTime.of(2024, 6, 22, 10, 30), "Conference Room A", 
            "Exploring new trends and innovations in culinary arts"));
        
        // Restaurant 2
        Restaurant r2 = new Restaurant("R002", "Urban Bistro", "456 Oak Ave, Townsville");
        r2.addConvention(new Convention("C201", "Sustainable Restaurant Pract", 
            LocalDateTime.of(2024, 2, 10, 14, 0), "Green Room", 
            "Workshop on implementing sustainable pract in restaurant operations"));
        r2.addConvention(new Convention("C202", "Wine Pairing Masterclass", 
            LocalDateTime.of(2024, 4, 5, 18, 30), "Wine Cellar", 
            "Masterclass on wine selection and food pairing techniques"));
        r2.addConvention(new Convention("C203", "Staff Training Seminar", 
            LocalDateTime.of(2024, 5, 20, 9, 0), "Training Center", 
            "Quarterly staff training on customer service excellence"));
        
        // Restaurant 3
        Restaurant r3 = new Restaurant("R003", "Seaside Grill", "789 Beach Blvd, Coastal City");
        r3.addConvention(new Convention("C301", "Seafood Safety Symposium", 
            LocalDateTime.of(2024, 1, 18, 11, 0), "Ocean View Room", 
            "Symposium on seafood handling and safety protocols"));
        
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        
        return restaurants;
    }
}