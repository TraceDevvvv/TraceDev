import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class demonstrating the SearchRefreshmentPoint use case.
 * Simulates the flow:
 * 1. Activate search functionality
 * 2. Show the search form
 * 3. Fill in the form and submit
 * 4. Process the request and display results
 * 
 * Handles connection interruption to ETOUR server and enforces 15-second timeout.
 */
public class Main {
    
    /**
     * Main entry point of the program.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== SearchRefreshmentPoint Use Case Demonstration ===");
        System.out.println();
        
        // Step 1: Activate search functionality
        System.out.println("Step 1: Activating search functionality...");
        SearchService searchService = new SearchService();
        
        // Step 2: Show the search form
        System.out.println("Step 2: Displaying search form...");
        displaySearchFormInstructions();
        
        // Step 3: Fill in the search form
        System.out.println("Step 3: Filling search form...");
        SearchForm searchForm = createSearchFormFromUserInput();
        
        // Step 4: Process the request
        System.out.println("Step 4: Processing search request...");
        System.out.println("Search criteria: " + searchForm);
        System.out.println();
        
        // Start timing to ensure 15-second maximum
        long startTime = System.currentTimeMillis();
        
        try {
            // Perform search with timeout handling
            List<PointOfRest> results = searchService.performSearch(searchForm);
            
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            
            // Step 5: Display results
            displayResults(results, elapsedTime);
            
            // Check if operation completed within 15 seconds (quality requirement)
            if (elapsedTime <= 15000) {
                System.out.println("✓ Quality requirement met: Operation completed in " + elapsedTime + "ms (within 15 seconds)");
            } else {
                System.out.println("⚠ Warning: Operation took " + elapsedTime + "ms (exceeds 15 seconds)");
            }
            
        } catch (RuntimeException e) {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            
            System.out.println("❌ Error during search: " + e.getMessage());
            System.out.println("Operation took " + elapsedTime + "ms");
            
            if (e.getMessage().contains("ETOUR")) {
                System.out.println("Handled: Connection interruption to ETOUR server");
            }
        }
        
        System.out.println("\n=== End of SearchRefreshmentPoint Use Case ===");
    }
    
    /**
     * Displays instructions for the search form.
     * In a real application, this would be a GUI or web form.
     */
    private static void displaySearchFormInstructions() {
        System.out.println("---------------------------------------------------");
        System.out.println("SEARCH FORM - Please enter your search criteria:");
        System.out.println("(Enter values or press Enter for defaults)");
        System.out.println("---------------------------------------------------");
    }
    
    /**
     * Creates a SearchForm from simulated user input.
     * In a real application, this would read from actual user input.
     * 
     * @return SearchForm with user-provided criteria
     */
    private static SearchForm createSearchFormFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        SearchForm form = new SearchForm();
        
        // For demonstration, we'll use a mix of hardcoded values and simulated input
        // In a real application, you would read all values from the user
        
        System.out.print("Enter location to search near (default: 'City Center'): ");
        String locationInput = scanner.nextLine();
        if (!locationInput.isEmpty()) {
            form.setLocation(locationInput);
        } else {
            form.setLocation("City Center");
        }
        
        System.out.print("Enter minimum rating (0.0-5.0, default: 3.0): ");
        String ratingInput = scanner.nextLine();
        if (!ratingInput.isEmpty()) {
            try {
                form.setMinRating(Double.parseDouble(ratingInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating, using default 3.0");
                form.setMinRating(3.0);
            }
        } else {
            form.setMinRating(3.0);
        }
        
        System.out.print("Search only open points? (yes/no, default: yes): ");
        String openInput = scanner.nextLine();
        if (!openInput.isEmpty() && openInput.equalsIgnoreCase("no")) {
            form.setOpenOnly(false);
        } else {
            form.setOpenOnly(true);
        }
        
        System.out.print("Enter amenities to filter by (comma-separated, default: 'WiFi,Restroom'): ");
        String amenitiesInput = scanner.nextLine();
        if (!amenitiesInput.isEmpty()) {
            List<String> amenities = Arrays.asList(amenitiesInput.split("\\s*,\\s*"));
            form.setAmenities(amenities);
        } else {
            form.setAmenities(Arrays.asList("WiFi", "Restroom"));
        }
        
        System.out.print("Enter maximum distance in km (default: 10.0): ");
        String distanceInput = scanner.nextLine();
        if (!distanceInput.isEmpty()) {
            try {
                form.setMaxDistance(Double.parseDouble(distanceInput));
            } catch (NumberFormatException e) {
                System.out.println("Invalid distance, using default 10.0");
                form.setMaxDistance(10.0);
            }
        } else {
            form.setMaxDistance(10.0);
        }
        
        scanner.close();
        return form;
    }
    
    /**
     * Displays the search results.
     * 
     * @param results list of PointOfRest objects matching the search criteria
     * @param elapsedTime time taken for the search operation in milliseconds
     */
    private static void displayResults(List<PointOfRest> results, long elapsedTime) {
        System.out.println("===================================================");
        System.out.println("SEARCH RESULTS");
        System.out.println("===================================================");
        System.out.println("Found " + results.size() + " point(s) of rest:");
        System.out.println("Search completed in " + elapsedTime + "ms");
        System.out.println();
        
        if (results.isEmpty()) {
            System.out.println("No points of rest match your search criteria.");
            System.out.println("Try broadening your search parameters.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                PointOfRest point = results.get(i);
                System.out.println((i + 1) + ". " + point.getName());
                System.out.println("   Location: " + point.getLocation());
                System.out.println("   Rating: " + point.getRating() + "/5.0");
                System.out.println("   Amenities: " + point.getAmenities());
                System.out.println("   Status: " + (point.isOpen() ? "Open" : "Closed"));
                System.out.println();
            }
        }
    }
}