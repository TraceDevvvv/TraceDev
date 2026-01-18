/**
 * ModifySearchPreferenceService class implementing the business logic for the ModifySearchPreference use case.
 * This service handles the complete workflow: displaying preferences, editing, confirmation,
 * validation, and persistence. It also handles edge cases like cancellation and server interruption.
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ModifySearchPreferenceService {
    private Tourist currentTourist;
    private Scanner scanner;
    private Map<String, SearchPreferences> preferenceStorage; // Simulated database storage
    
    /**
     * Constructor for the ModifySearchPreferenceService
     * @param tourist the authenticated tourist to modify preferences for
     */
    public ModifySearchPreferenceService(Tourist tourist) {
        this.currentTourist = tourist;
        this.scanner = new Scanner(System.in);
        this.preferenceStorage = new HashMap<>();
        
        // Initialize simulated storage with some existing preferences if available
        if (tourist.hasSearchPreferences()) {
            preferenceStorage.put(tourist.getUserId(), tourist.getSearchPreferences());
        }
    }
    
    /**
     * Main method to execute the ModifySearchPreference use case flow.
     * This implements the complete flow of events from the use case specification.
     * @return true if preferences were successfully modified, false otherwise
     */
    public boolean executeModifyPreferenceFlow() {
        System.out.println("\n=== Modify Search Preferences Flow ===");
        
        // Entry condition check: Tourist must be authenticated
        if (!currentTourist.isAuthenticated()) {
            System.out.println("Error: Tourist is not authenticated. Please authenticate first.");
            return false;
        }
        
        System.out.println("Tourist " + currentTourist.getUsername() + " is authenticated.");
        
        try {
            // Step 1: Access to functionality for modification of personal search preferences
            System.out.println("\n1. Accessing search preference modification functionality...");
            
            // Step 2: Upload search preferences and display them in a form
            SearchPreferences currentPreferences = getCurrentPreferences();
            System.out.println("\n2. Current search preferences:");
            displayPreferencesForm(currentPreferences);
            
            // Step 3: Edit the fields in the form and submit
            System.out.println("\n3. Editing search preferences...");
            SearchPreferences editedPreferences = editPreferencesForm(currentPreferences);
            
            // Validate the edited preferences
            if (!editedPreferences.validate()) {
                System.out.println("Error: Invalid search preferences. Please check your inputs.");
                return false;
            }
            
            // Step 4: Ask for confirmation
            System.out.println("\n4. Please confirm the changes:");
            displayPreferencesForm(editedPreferences);
            System.out.println("\nAre these changes correct? (yes/no/cancel): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            // Handle cancellation
            if (confirmation.equals("cancel")) {
                System.out.println("Operation cancelled by user.");
                return false;
            }
            
            // Step 5: Confirmation for changing search preferences
            if (confirmation.equals("yes") || confirmation.equals("y")) {
                System.out.println("\n5. Confirmed changes. Proceeding with update...");
                
                // Step 6: Memorize search preferences changed (persist)
                boolean success = savePreferences(editedPreferences);
                
                if (success) {
                    // Exit condition: Successful modification notification
                    System.out.println("\n6. Search preferences have been successfully updated!");
                    System.out.println("New preferences have been memorized in the system.");
                    return true;
                } else {
                    // Handle persistence failure (simulated server interruption)
                    System.out.println("Error: Failed to save preferences. Server may be unavailable.");
                    return false;
                }
            } else {
                System.out.println("Changes not confirmed. Operation aborted.");
                return false;
            }
            
        } catch (Exception e) {
            // Handle unexpected exceptions (simulating server interruption)
            System.out.println("Error: Unexpected interruption occurred. " + e.getMessage());
            System.out.println("Please try again later.");
            return false;
        }
    }
    
    /**
     * Retrieves current preferences for the tourist.
     * If no preferences exist, creates default preferences.
     * @return current or default SearchPreferences object
     */
    private SearchPreferences getCurrentPreferences() {
        if (currentTourist.hasSearchPreferences()) {
            return currentTourist.getSearchPreferences();
        } else {
            System.out.println("No existing preferences found. Creating default preferences.");
            SearchPreferences defaultPrefs = new SearchPreferences();
            defaultPrefs.setDestination("Paris");
            defaultPrefs.setMinBudget(1000.0);
            defaultPrefs.setMaxBudget(3000.0);
            defaultPrefs.setStartDate("2024-07-01");
            defaultPrefs.setEndDate("2024-07-10");
            defaultPrefs.setAccommodationType("Hotel");
            defaultPrefs.setNumberOfTravelers(2);
            defaultPrefs.setIncludeFlights(true);
            defaultPrefs.setIncludeMeals(false);
            defaultPrefs.setTravelStyle("Standard");
            return defaultPrefs;
        }
    }
    
    /**
     * Displays the search preferences in a form-like format.
     * @param preferences the SearchPreferences object to display
     */
    private void displayPreferencesForm(SearchPreferences preferences) {
        System.out.println("========================================");
        System.out.println("SEARCH PREFERENCES FORM");
        System.out.println("========================================");
        System.out.println("1. Destination: " + preferences.getDestination());
        System.out.println("2. Min Budget: $" + preferences.getMinBudget());
        System.out.println("3. Max Budget: $" + preferences.getMaxBudget());
        System.out.println("4. Start Date: " + preferences.getStartDate());
        System.out.println("5. End Date: " + preferences.getEndDate());
        System.out.println("6. Accommodation Type: " + preferences.getAccommodationType());
        System.out.println("7. Number of Travelers: " + preferences.getNumberOfTravelers());
        System.out.println("8. Include Flights: " + (preferences.isIncludeFlights() ? "Yes" : "No"));
        System.out.println("9. Include Meals: " + (preferences.isIncludeMeals() ? "Yes" : "No"));
        System.out.println("10. Travel Style: " + preferences.getTravelStyle());
        System.out.println("========================================");
        System.out.println("Summary: " + preferences.getSummary());
    }
    
    /**
     * Allows the user to edit each field of the search preferences.
     * @param currentPreferences the current preferences to edit
     * @return edited SearchPreferences object
     */
    private SearchPreferences editPreferencesForm(SearchPreferences currentPreferences) {
        // Create a copy to avoid modifying the original until confirmed
        SearchPreferences edited = new SearchPreferences(currentPreferences);
        
        System.out.println("Enter new values (press Enter to keep current value):");
        
        // Edit destination
        System.out.print("1. Destination [" + edited.getDestination() + "]: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            edited.setDestination(input);
        }
        
        // Edit min budget
        System.out.print("2. Min Budget [$" + edited.getMinBudget() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                double minBudget = Double.parseDouble(input);
                if (minBudget >= 0) {
                    edited.setMinBudget(minBudget);
                } else {
                    System.out.println("Invalid input. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Keeping current value.");
            }
        }
        
        // Edit max budget
        System.out.print("3. Max Budget [$" + edited.getMaxBudget() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                double maxBudget = Double.parseDouble(input);
                if (maxBudget >= 0) {
                    edited.setMaxBudget(maxBudget);
                } else {
                    System.out.println("Invalid input. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Keeping current value.");
            }
        }
        
        // Edit start date
        System.out.print("4. Start Date (YYYY-MM-DD) [" + edited.getStartDate() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            edited.setStartDate(input);
        }
        
        // Edit end date
        System.out.print("5. End Date (YYYY-MM-DD) [" + edited.getEndDate() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            edited.setEndDate(input);
        }
        
        // Edit accommodation type
        System.out.print("6. Accommodation Type [" + edited.getAccommodationType() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            edited.setAccommodationType(input);
        }
        
        // Edit number of travelers
        System.out.print("7. Number of Travelers [" + edited.getNumberOfTravelers() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int travelers = Integer.parseInt(input);
                if (travelers > 0 && travelers <= 20) {
                    edited.setNumberOfTravelers(travelers);
                } else {
                    System.out.println("Must be between 1 and 20. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Keeping current value.");
            }
        }
        
        // Edit include flights
        System.out.print("8. Include Flights? (yes/no) [" + (edited.isIncludeFlights() ? "yes" : "no") + "]: ");
        input = scanner.nextLine().trim().toLowerCase();
        if (!input.isEmpty()) {
            edited.setIncludeFlights(input.equals("yes") || input.equals("y"));
        }
        
        // Edit include meals
        System.out.print("9. Include Meals? (yes/no) [" + (edited.isIncludeMeals() ? "yes" : "no") + "]: ");
        input = scanner.nextLine().trim().toLowerCase();
        if (!input.isEmpty()) {
            edited.setIncludeMeals(input.equals("yes") || input.equals("y"));
        }
        
        // Edit travel style
        System.out.print("10. Travel Style [" + edited.getTravelStyle() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            edited.setTravelStyle(input);
        }
        
        return edited;
    }
    
    /**
     * Saves the preferences to simulated storage.
     * @param preferences the SearchPreferences object to save
     * @return true if saved successfully, false otherwise (simulating server interruption)
     */
    private boolean savePreferences(SearchPreferences preferences) {
        try {
            // Simulate server communication with potential interruption
            double random = Math.random();
            if (random < 0.1) { // 10% chance of simulated server interruption
                throw new RuntimeException("Server connection interrupted");
            }
            
            // Save to simulated storage
            preferenceStorage.put(currentTourist.getUserId(), preferences);
            
            // Update the tourist object
            currentTourist.setSearchPreferences(preferences);
            
            // Simulate persistence delay
            Thread.sleep(500);
            
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            // Log the error in a real system
            System.err.println("Error saving preferences: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Gets the current preference storage (for testing purposes).
     * @return the preference storage map
     */
    protected Map<String, SearchPreferences> getPreferenceStorage() {
        return preferenceStorage;
    }
    
    /**
     * Closes the scanner resource.
     * Should be called when the service is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Alternative flow: Allows cancellation at any point during editing.
     * This implements the cancellation exit condition from the use case.
     * @return true if user wants to cancel, false otherwise
     */
    private boolean checkForCancellation() {
        System.out.print("Type 'cancel' at any time to abort, or press Enter to continue: ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("cancel");
    }
    
    /**
     * Simulates server interruption handling.
     * This method demonstrates how server interruptions could be handled.
     * @return true if operation should retry, false if should abort
     */
    private boolean handleServerInterruption() {
        System.out.println("\nServer connection was interrupted.");
        System.out.print("Would you like to retry? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }
}