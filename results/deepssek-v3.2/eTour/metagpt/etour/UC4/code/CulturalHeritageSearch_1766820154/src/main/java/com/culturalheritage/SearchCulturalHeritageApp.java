package com.culturalheritage;

import java.time.Year;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the SearchCulturalHeritage use case.
 * This class demonstrates the complete flow of the use case:
 * 1. Activate the search functionality of a cultural object
 * 2. Show the form for research
 * 3. Fill in the search form and submit
 * 4. Processing the request
 * 5. Display results (or handle errors)
 */
public class SearchCulturalHeritageApp {
    
    private final SearchService searchService;
    private final Scanner scanner;
    
    /**
     * Constructor initializes the search service and scanner for user input.
     */
    public SearchCulturalHeritageApp() {
        this.searchService = new SearchService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method - entry point of the application.
     * 
     * @param args command line arguments (optional search criteria)
     */
    public static void main(String[] args) {
        System.out.println("=== Cultural Heritage Search System ===");
        System.out.println("Use case: SearchCulturalHeritage");
        
        SearchCulturalHeritageApp app = new SearchCulturalHeritageApp();
        
        try {
            // Check if command line arguments were provided
            if (args.length > 0) {
                app.processCommandLineSearch(args);
            } else {
                app.runInteractiveMode();
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            app.cleanup();
        }
    }
    
    /**
     * Processes search from command line arguments.
     * Demonstrates how the system can be used non-interactively.
     * 
     * @param args command line arguments with search criteria
     */
    private void processCommandLineSearch(String[] args) {
        System.out.println("\nProcessing command line search...");
        
        // Create search form from command line arguments
        SearchForm searchForm = SearchForm.fromCommandLineArgs(args);
        System.out.println(searchForm.getCriteriaSummary());
        
        // Process the search request
        processSearchRequest(searchForm);
    }
    
    /**
     * Runs the application in interactive mode.
     * Simulates the complete use case flow step by step.
     */
    private void runInteractiveMode() {
        System.out.println("\n--- Starting Interactive Search ---");
        
        // Step 1: Activate the search functionality
        activateSearchFunctionality();
        
        // Step 2: Show the form for research
        System.out.println("\n--- Step 2: Search Form ---");
        System.out.println("Please fill in the search criteria (press Enter to skip any field):");
        
        // Step 3: Fill in the search form
        SearchForm searchForm = showAndFillSearchForm();
        
        // Step 4: Submit and process the request
        System.out.println("\n--- Step 3: Submitting Search Request ---");
        System.out.println("Search criteria submitted:");
        System.out.println(searchForm.getCriteriaSummary());
        
        // Step 5: Processing the request and showing results
        processSearchRequest(searchForm);
        
        System.out.println("\n--- Search Complete ---");
        
        // Ask if user wants to perform another search
        askForAnotherSearch();
    }
    
    /**
     * Step 1: Activate the search functionality.
     * This simulates the user initiating a search.
     */
    private void activateSearchFunctionality() {
        System.out.println("\n--- Step 1: Activating Search Functionality ---");
        System.out.println("Search functionality activated.");
        System.out.println("Welcome to the Cultural Heritage Search System.");
        System.out.println("This system allows you to search for cultural objects based on various criteria.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Wait for user confirmation
    }
    
    /**
     * Step 2 & 3: Show the search form and collect user input.
     * 
     * @return the filled search form
     */
    private SearchForm showAndFillSearchForm() {
        SearchForm form = new SearchForm();
        
        // Get name search criteria
        System.out.print("Name (or part of name): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            form.setName(name);
        }
        
        // Get type search criteria
        System.out.print("Type (e.g., Painting, Monument, Sculpture, Architecture, Artifact): ");
        String type = scanner.nextLine().trim();
        if (!type.isEmpty()) {
            form.setType(type);
        }
        
        // Get era search criteria
        System.out.print("Era/Historical Period (e.g., Renaissance, Ancient, Modern): ");
        String era = scanner.nextLine().trim();
        if (!era.isEmpty()) {
            form.setEra(era);
        }
        
        // Get location search criteria
        System.out.print("Location (museum, city, or country): ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            form.setLocation(location);
        }
        
        // Get country of origin
        System.out.print("Country of Origin: ");
        String country = scanner.nextLine().trim();
        if (!country.isEmpty()) {
            form.setCountryOfOrigin(country);
        }
        
        // Get year range criteria
        System.out.print("Minimum Year (e.g., 1500, leave empty for no minimum): ");
        String minYearStr = scanner.nextLine().trim();
        if (!minYearStr.isEmpty()) {
            try {
                form.setMinYear(Year.of(Integer.parseInt(minYearStr)));
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Minimum year will be ignored.");
            }
        }
        
        System.out.print("Maximum Year (e.g., 2000, leave empty for no maximum): ");
        String maxYearStr = scanner.nextLine().trim();
        if (!maxYearStr.isEmpty()) {
            try {
                form.setMaxYear(Year.of(Integer.parseInt(maxYearStr)));
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Maximum year will be ignored.");
            }
        }
        
        // Get protected status criteria
        System.out.print("Protected Heritage Only? (yes/no/any): ");
        String protectedStr = scanner.nextLine().trim().toLowerCase();
        if (protectedStr.equals("yes")) {
            form.setIsProtected(true);
        } else if (protectedStr.equals("no")) {
            form.setIsProtected(false);
        }
        // "any" or empty leaves it as null (no filter)
        
        return form;
    }
    
    /**
     * Step 4: Process the search request and handle the results.
     * This method demonstrates the core search functionality with timeout handling
     * and error management for server connection interruptions (ETOUR).
     * 
     * @param searchForm the filled search form
     */
    private void processSearchRequest(SearchForm searchForm) {
        System.out.println("\n--- Step 4: Processing Search Request ---");
        
        // Validate the form before processing
        if (!searchForm.isValid()) {
            System.out.println("Error: Invalid search criteria. Please check your input.");
            if (searchForm.getMinYear() != null && searchForm.getMaxYear() != null &&
                searchForm.getMinYear().isAfter(searchForm.getMaxYear())) {
                System.out.println("Minimum year cannot be after maximum year.");
            }
            return;
        }
        
        try {
            // Show processing message
            System.out.println("Processing search request...");
            System.out.println("(Note: There's a 10% chance of simulated server delay to demonstrate ETOUR scenario)");
            
            // Perform the search with a timeout
            long startTime = System.currentTimeMillis();
            
            // Quality requirement: return results within set time (5 seconds timeout)
            List<CulturalObject> results = searchService.search(searchForm);
            
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            
            // Display results
            System.out.println("\n--- Search Results ---");
            System.out.println("Search processed in " + processingTime + "ms");
            
            if (results.isEmpty()) {
                System.out.println("No cultural objects found matching your criteria.");
                
                // Suggest trying a broader search
                if (!searchForm.isEmpty()) {
                    System.out.println("Tip: Try broadening your search criteria for better results.");
                    System.out.println("Available cultural object types in the system:");
                    searchService.getAllCulturalObjects().stream()
                        .map(CulturalObject::getType)
                        .distinct()
                        .forEach(type -> System.out.println("  - " + type));
                }
            } else {
                System.out.println("Found " + results.size() + " cultural object(s):");
                System.out.println("=".repeat(80));
                
                for (int i = 0; i < results.size(); i++) {
                    CulturalObject obj = results.get(i);
                    System.out.println((i + 1) + ". " + obj.getName());
                    System.out.println("   Type: " + obj.getType());
                    System.out.println("   Era: " + obj.getEra());
                    System.out.println("   Location: " + obj.getLocation());
                    if (obj.getYearCreated() != null) {
                        System.out.println("   Year Created: " + obj.getYearCreated());
                    }
                    System.out.println("   Country of Origin: " + 
                        (obj.getCountryOfOrigin().isEmpty() ? "Unknown" : obj.getCountryOfOrigin()));
                    System.out.println("   Protected Heritage: " + (obj.isProtected() ? "Yes" : "No"));
                    if (!obj.getDescription().isEmpty()) {
                        System.out.println("   Description: " + 
                            (obj.getDescription().length() > 100 ? 
                             obj.getDescription().substring(0, 100) + "..." : 
                             obj.getDescription()));
                    }
                    System.out.println("-".repeat(80));
                }
            }
            
            // Check if processing time meets quality requirements
            if (processingTime > SearchService.DEFAULT_TIMEOUT_MS) {
                System.out.println("\nWarning: Search took longer than the expected timeout period.");
                System.out.println("This indicates a performance issue that should be addressed.");
            }
            
        } catch (SearchService.SearchException e) {
            // Handle search-specific exceptions
            System.out.println("\n--- Search Error ---");
            System.out.println("Error: " + e.getMessage());
            
            // Special handling for timeout (simulating ETOUR server interruption)
            if (e.getMessage().contains("timed out")) {
                System.out.println("This may be due to a server connection issue (ETOUR interruption).");
                System.out.println("Please try again or contact system administrator if the problem persists.");
            }
            
            // Show cause if available
            if (e.getCause() != null) {
                System.out.println("Root cause: " + e.getCause().getMessage());
            }
            
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("\n--- Unexpected Error ---");
            System.out.println("An unexpected error occurred during search: " + e.getMessage());
            System.out.println("Please try again or contact system administrator.");
        }
    }
    
    /**
     * Asks the user if they want to perform another search.
     * Continues the interactive mode if yes.
     */
    private void askForAnotherSearch() {
        System.out.print("\nWould you like to perform another search? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            System.out.println("\n" + "=".repeat(60));
            runInteractiveMode();
        } else {
            System.out.println("Thank you for using the Cultural Heritage Search System. Goodbye!");
        }
    }
    
    /**
     * Demonstrates example searches to show system capabilities.
     * This can be called from main for demonstration purposes.
     */
    private void demonstrateExampleSearches() {
        System.out.println("\n=== Example Searches Demonstration ===\n");
        
        // Example 1: Search by name
        System.out.println("Example 1: Searching for objects containing 'Lisa' in name");
        SearchForm form1 = new SearchForm("Lisa", null, null, null);
        processSearchRequest(form1);
        
        // Example 2: Search by type and location
        System.out.println("\n\nExample 2: Searching for Paintings in Paris");
        SearchForm form2 = new SearchForm(null, "Painting", null, "Paris");
        processSearchRequest(form2);
        
        // Example 3: Search by era with year range
        System.out.println("\n\nExample 3: Searching for Ancient objects created before year 0");
        SearchForm form3 = new SearchForm(null, null, "Ancient", null);
        form3.setMaxYear(Year.of(0));
        processSearchRequest(form3);
        
        // Example 4: Empty search (returns all)
        System.out.println("\n\nExample 4: Empty search (returns all cultural objects)");
        SearchForm form4 = new SearchForm();
        processSearchRequest(form4);
    }
    
    /**
     * Cleanup resources before exiting.
     */
    private void cleanup() {
        scanner.close();
        searchService.shutdown();
        System.out.println("\nSystem resources cleaned up.");
    }
}