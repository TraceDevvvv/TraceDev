import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * SEARCH Use Case Implementation
 * Description: Research and cultural heritage for all.
 * This program implements a console-based search system for cultural heritage sites.
 * It simulates the complete flow from user login to search results display,
 * including handling server interruptions.
 */
public class SEARCHUseCase {

    /**
     * Represents a cultural heritage site with basic properties.
     */
    static class CulturalHeritageSite {
        private String name;
        private String location;
        private String type; // e.g., Museum, Archaeological Site, Monument
        private String description;
        
        public CulturalHeritageSite(String name, String location, String type, String description) {
            this.name = name;
            this.location = location;
            this.type = type;
            this.description = description;
        }
        
        public String getName() { return name; }
        public String getLocation() { return location; }
        public String getType() { return type; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return "Site: " + name + 
                   "\n  Location: " + location + 
                   "\n  Type: " + type + 
                   "\n  Description: " + description + "\n";
        }
    }
    
    /**
     * Represents a search form with various criteria.
     */
    static class SearchForm {
        private String location;
        private String siteType;
        private String keyword;
        
        // Getters and setters
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        
        public String getSiteType() { return siteType; }
        public void setSiteType(String siteType) { this.siteType = siteType; }
        
        public String getKeyword() { return keyword; }
        public void setKeyword(String keyword) { this.keyword = keyword; }
        
        /**
         * Validates if the form has at least one search criterion filled.
         * @return true if valid, false otherwise
         */
        public boolean isValid() {
            return (location != null && !location.trim().isEmpty()) ||
                   (siteType != null && !siteType.trim().isEmpty()) ||
                   (keyword != null && !keyword.trim().isEmpty());
        }
        
        @Override
        public String toString() {
            return "Search Criteria:\n" +
                   "  Location: " + (location != null ? location : "Not specified") + "\n" +
                   "  Site Type: " + (siteType != null ? siteType : "Not specified") + "\n" +
                   "  Keyword: " + (keyword != null ? keyword : "Not specified");
        }
    }
    
    /**
     * Mock database of cultural heritage sites for demonstration.
     */
    private static List<CulturalHeritageSite> initializeMockDatabase() {
        List<CulturalHeritageSite> sites = new ArrayList<>();
        
        // Adding sample cultural heritage sites
        sites.add(new CulturalHeritageSite(
            "Louvre Museum",
            "Paris, France",
            "Museum",
            "World's largest art museum and a historic monument."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Colosseum",
            "Rome, Italy",
            "Archaeological Site",
            "Ancient amphitheater and iconic symbol of Imperial Rome."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Great Wall of China",
            "Beijing, China",
            "Monument",
            "Series of fortifications made of stone, brick, and other materials."
        ));
        
        sites.add(new CulturalHeritageSite(
            "British Museum",
            "London, UK",
            "Museum",
            "Dedicated to human history, art and culture with over 8 million works."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Machu Picchu",
            "Cusco Region, Peru",
            "Archaeological Site",
            "15th-century Inca citadel located on a mountain ridge."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Taj Mahal",
            "Agra, India",
            "Monument",
            "Ivory-white marble mausoleum commissioned in 1632."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Metropolitan Museum of Art",
            "New York, USA",
            "Museum",
            "Largest art museum in the United States."
        ));
        
        sites.add(new CulturalHeritageSite(
            "Pyramids of Giza",
            "Giza, Egypt",
            "Archaeological Site",
            "Ancient pyramid complex including the Great Pyramid."
        ));
        
        return sites;
    }
    
    /**
     * Simulates connection to the ETOUR server.
     * @return true if connection is successful, false if interrupted
     */
    private static boolean connectToETOURServer() {
        Random random = new Random();
        // Simulate 80% chance of successful connection, 20% chance of interruption
        boolean connectionSuccessful = random.nextInt(100) < 80;
        
        if (!connectionSuccessful) {
            System.out.println("\n[ERROR] Connection to server ETOUR interrupted!");
            System.out.println("Please try again later or check your network connection.");
        }
        
        return connectionSuccessful;
    }
    
    /**
     * Performs search based on form criteria.
     * @param form The search form with criteria
     * @param database The mock database of sites
     * @return List of matching sites
     */
    private static List<CulturalHeritageSite> performSearch(SearchForm form, List<CulturalHeritageSite> database) {
        List<CulturalHeritageSite> results = new ArrayList<>();
        
        for (CulturalHeritageSite site : database) {
            boolean matches = true;
            
            // Check location match (case-insensitive partial match)
            if (form.getLocation() != null && !form.getLocation().trim().isEmpty()) {
                if (!site.getLocation().toLowerCase().contains(form.getLocation().toLowerCase())) {
                    matches = false;
                }
            }
            
            // Check site type match (case-insensitive exact match)
            if (matches && form.getSiteType() != null && !form.getSiteType().trim().isEmpty()) {
                if (!site.getType().equalsIgnoreCase(form.getSiteType())) {
                    matches = false;
                }
            }
            
            // Check keyword match in name or description (case-insensitive)
            if (matches && form.getKeyword() != null && !form.getKeyword().trim().isEmpty()) {
                String keyword = form.getKeyword().toLowerCase();
                if (!site.getName().toLowerCase().contains(keyword) && 
                    !site.getDescription().toLowerCase().contains(keyword)) {
                    matches = false;
                }
            }
            
            if (matches) {
                results.add(site);
            }
        }
        
        return results;
    }
    
    /**
     * Displays the search form and collects user input.
     * @param scanner Scanner for reading user input
     * @return Filled search form
     */
    private static SearchForm displayAndFillForm(Scanner scanner) {
        SearchForm form = new SearchForm();
        
        System.out.println("\n=== SEARCH FORM ===");
        System.out.println("Please enter your search criteria (press Enter to skip any field):");
        
        // Get location
        System.out.print("Location (e.g., Paris, Rome, etc.): ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            form.setLocation(location);
        }
        
        // Get site type
        System.out.print("Site Type (Museum, Archaeological Site, Monument): ");
        String siteType = scanner.nextLine().trim();
        if (!siteType.isEmpty()) {
            form.setSiteType(siteType);
        }
        
        // Get keyword
        System.out.print("Keyword (e.g., 'ancient', 'art', etc.): ");
        String keyword = scanner.nextLine().trim();
        if (!keyword.isEmpty()) {
            form.setKeyword(keyword);
        }
        
        return form;
    }
    
    /**
     * Main method implementing the SEARCH use case flow.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=========================================");
        System.out.println("  CULTURAL HERITAGE SEARCH SYSTEM");
        System.out.println("  Research and cultural heritage for all");
        System.out.println("=========================================");
        
        // Entry condition: Guest User logs on
        System.out.print("\nPlease enter your name (Guest User): ");
        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) {
            userName = "Guest";
        }
        System.out.println("Welcome, " + userName + "!");
        
        // Flow of events:
        boolean continueSearching = true;
        
        while (continueSearching) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Activate Search Functionality");
            System.out.println("2. Exit");
            System.out.print("Enter your choice (1-2): ");
            
            String choice = scanner.nextLine().trim();
            
            if (choice.equals("1")) {
                // Step 1: Activate the search functionality
                System.out.println("\n[INFO] Search functionality activated.");
                
                // Step 2: Display the corresponding form
                // Step 3: Fill out the form and submit
                SearchForm form = displayAndFillForm(scanner);
                
                // Validate form
                if (!form.isValid()) {
                    System.out.println("\n[ERROR] Please enter at least one search criterion.");
                    continue;
                }
                
                System.out.println("\n" + form.toString());
                System.out.print("\nSubmit search? (yes/no): ");
                String submitChoice = scanner.nextLine().trim().toLowerCase();
                
                if (!submitChoice.equals("yes") && !submitChoice.equals("y")) {
                    System.out.println("Search cancelled.");
                    continue;
                }
                
                // Simulate connection to ETOUR server
                System.out.println("\n[INFO] Connecting to ETOUR server...");
                boolean connected = connectToETOURServer();
                
                if (!connected) {
                    // Handle server interruption
                    System.out.println("[INFO] Search cannot proceed due to server interruption.");
                    continue;
                }
                
                // Step 4: Process research (perform search)
                System.out.println("[INFO] Processing search...");
                
                // Initialize mock database
                List<CulturalHeritageSite> database = initializeMockDatabase();
                
                // Perform search
                List<CulturalHeritageSite> results = performSearch(form, database);
                
                // Exit condition: Display list of sites meeting search criteria
                System.out.println("\n=== SEARCH RESULTS ===");
                System.out.println("Found " + results.size() + " site(s) matching your criteria:");
                
                if (results.isEmpty()) {
                    System.out.println("No sites found matching your search criteria.");
                    System.out.println("Suggestions:");
                    System.out.println("  - Try broader search terms");
                    System.out.println("  - Check spelling");
                    System.out.println("  - Try different location or site type");
                } else {
                    for (int i = 0; i < results.size(); i++) {
                        System.out.println("Result " + (i + 1) + ":");
                        System.out.println(results.get(i));
                    }
                }
                
            } else if (choice.equals("2")) {
                // Exit the program
                System.out.println("\nThank you for using the Cultural Heritage Search System!");
                continueSearching = false;
            } else {
                System.out.println("\n[ERROR] Invalid choice. Please enter 1 or 2.");
            }
        }
        
        scanner.close();
        System.out.println("Program terminated.");
    }
}