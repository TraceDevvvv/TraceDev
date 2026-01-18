import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner; // For simulating user input

/**
 * Represents the view component for searching tourist accounts.
 * It's responsible for displaying forms, results, and error messages,
 * as well as capturing user input.
 */
public class TouristSearchView {

    // Dependency on controller to initiate search actions
    private TouristSearchController touristSearchController;
    private final Scanner scanner = new Scanner(System.in); // For simulated user input

    /**
     * Default constructor for TouristSearchView.
     * Needs to be initialized with a controller later or via a setter if not using constructor injection.
     */
    public TouristSearchView() {
        // Default constructor, controller set via setter
    }

    /**
     * Sets the controller dependency.
     *
     * @param touristSearchController The controller to be used for search actions.
     */
    public void setTouristSearchController(TouristSearchController touristSearchController) {
        this.touristSearchController = touristSearchController;
    }

    /**
     * Displays the search form to the agency operator.
     * This method simulates rendering a UI form.
     */
    public void displaySearchForm() {
        System.out.println("\n--- Tourist Account Search Form ---");
        System.out.println("Please enter search criteria (leave blank for any):");
        // In a real application, this would render HTML/GUI elements.
    }

    /**
     * Displays the search results to the agency operator.
     * Traceability: m26 - Displays the list of matching tourist accounts.
     *
     * @param accounts A list of TouristAccountDTOs to display.
     */
    public void displaySearchResults(List<TouristAccountDTO> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("\n--- Search Results ---");
            System.out.println("No tourist accounts found matching your criteria.");
        } else {
            System.out.println("\n--- Search Results ---");
            accounts.forEach(System.out::println);
        }
    }

    /**
     * Prompts the user for search criteria and returns them as a DTO.
     * This method simulates capturing user input from a form.
     *
     * @return A TouristSearchCriteriaDTO populated with user input.
     */
    public TouristSearchCriteriaDTO getSearchCriteria() {
        System.out.print("Name (e.g., John): ");
        String name = scanner.nextLine().trim();
        System.out.print("Nationality (e.g., US): ");
        String nationality = scanner.nextLine().trim();
        System.out.print("Minimum Booking Count (e.g., 0): ");
        int minBookingCount;
        try {
            minBookingCount = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number for minimum booking count. Using 0.");
            minBookingCount = 0;
        }

        TouristSearchCriteriaDTO criteria = new TouristSearchCriteriaDTO();
        criteria.name = name.isEmpty() ? null : name;
        criteria.nationality = nationality.isEmpty() ? null : nationality;
        criteria.minBookingCount = minBookingCount;
        return criteria;
    }

    /**
     * Helper method to convert a raw map of search data (e.g., from a web form)
     * into a TouristSearchCriteriaDTO.
     *
     * @param searchData A map where keys are field names and values are user inputs.
     * @return A populated TouristSearchCriteriaDTO.
     */
    public TouristSearchCriteriaDTO getSearchCriteria(Map<String, String> searchData) {
        TouristSearchCriteriaDTO criteria = new TouristSearchCriteriaDTO();
        criteria.name = searchData.getOrDefault("name", "").isEmpty() ? null : searchData.get("name");
        criteria.nationality = searchData.getOrDefault("nationality", "").isEmpty() ? null : searchData.get("nationality");
        try {
            criteria.minBookingCount = Integer.parseInt(searchData.getOrDefault("minBookingCount", "0"));
        } catch (NumberFormatException e) {
            System.err.println("View: Invalid number format for minBookingCount, defaulting to 0.");
            criteria.minBookingCount = 0;
        }
        return criteria;
    }


    /**
     * Handles the initiation of a search by the agency operator.
     * This method orchestrates the user interaction to get criteria and trigger the search.
     * Traceability: m4 - User fills out form and submits data (part 1: filling form simulation).
     */
    public void handleSearchInitiation() {
        // Precondition: Agency Operator is logged in. (Handled in Main simulation)
        System.out.println("View: Agency Operator requests search form.");
        displaySearchForm(); // Display the form

        // Simulate user input for search criteria
        Map<String, String> searchData = new HashMap<>();
        System.out.print("Enter Name (e.g., 'John', leave blank for all): ");
        searchData.put("name", scanner.nextLine());
        System.out.print("Enter Nationality (e.g., 'US', leave blank for all): ");
        searchData.put("nationality", scanner.nextLine());
        System.out.print("Enter Minimum Booking Count (e.g., '2', leave blank for 0): ");
        searchData.put("minBookingCount", scanner.nextLine());

        submitSearchCriteria(searchData); // Submit the criteria to the controller
    }

    /**
     * Submits the search criteria entered by the agency operator to the controller.
     * Traceability: m4 - User fills out form and submits data (part 2: submitting data simulation).
     *
     * @param searchData A map containing the raw search input from the form.
     */
    public void submitSearchCriteria(Map<String, String> searchData) {
        System.out.println("View: Agency Operator submits the form with data: " + searchData);
        // The View converts the raw map data into a DTO and passes it to the controller.
        // The sequence diagram shows the controller receiving the DTO directly,
        // but the note suggests the view might do the conversion, or the controller maps raw data.
        // For this implementation, the view provides the raw map, and the controller
        // uses a helper to convert it. (Updated design for clarify as per sequence diagram note).
        touristSearchController.handleSearchRequest(searchData);
    }

    /**
     * Displays an error message to the agency operator.
     * Traceability: m20 - Search aborted due to server error (AO receives error message).
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!");
    }
}