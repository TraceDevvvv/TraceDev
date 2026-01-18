// File: AdministratorUI.java
import java.util.Scanner; // For simulating user input in a console environment

/**
 * Represents the User Interface (UI) for the Administrator.
 * It handles displaying forms, getting user input, presenting results,
 * and interacting with the SearchService.
 */
public class AdministratorUI {
    // - searchService : SearchService
    private SearchService searchService;
    // Private field to simulate input text
    private String currentSearchInput = "";
    // Flag to simulate cancellation
    private boolean searchCancelled = false;

    /**
     * Constructor for AdministratorUI, injecting the SearchService dependency.
     *
     * @param searchService The service responsible for performing search operations.
     */
    public AdministratorUI(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Displays the search form to the administrator.
     * In a console application, this typically means printing a prompt.
     */
    public void displaySearchForm() {
        System.out.println("--- Administrator Search Form ---");
        System.out.println("Enter keywords to search for Classes, Teachings, Addresses, and Users.");
        System.out.print("Search: ");
    }

    /**
     * Simulates getting search input from the user and wraps it in a SearchCriteriaDTO.
     * For a console application, this would read from stdin.
     *
     * @return A SearchCriteriaDTO containing the user's input keywords.
     */
    public SearchCriteriaDTO getSearchInput() {
        // In a real UI, this would retrieve text from a text field.
        // Here, we use the internally stored currentSearchInput.
        System.out.println("[UI] Getting search input: '" + currentSearchInput + "'");
        return new SearchCriteriaDTO(currentSearchInput);
    }

    /**
     * Displays the search results to the administrator.
     *
     * @param results The SearchResultDTO containing the found entities.
     */
    public void displaySearchResults(SearchResultDTO results) {
        System.out.println("\\n--- Search Results ---");
        if (results.getAllFoundEntities().isEmpty()) {
            System.out.println("No entities found for your query.");
        } else {
            System.out.println("Found Classes (" + results.foundClasses.size() + "):");
            results.foundClasses.forEach(System.out::println);
            System.out.println("Found Teachings (" + results.foundTeachings.size() + "):");
            results.foundTeachings.forEach(System.out::println);
            System.out.println("Found Addresses (" + results.foundAddresses.size() + "):");
            results.foundAddresses.forEach(System.out::println);
            System.out.println("Found Users (" + results.foundUsers.size() + "):");
            results.foundUsers.forEach(System.out::println);
        }
        System.out.println("----------------------\\n");
    }

    /**
     * Displays an error message to the administrator.
     * Added to support error display from SMOSServerException.
     *
     * @param errorMessage The error message to display.
     */
    public void displayError(String errorMessage) {
        System.err.println("\\n[UI ERROR]: " + errorMessage);
        System.err.println("Please try again or check the server status.");
        System.out.println("----------------------\\n");
    }

    /**
     * Simulates the administrator entering text into the search field.
     * This method is used to set the internal state `currentSearchInput`.
     *
     * @param text The text entered by the administrator.
     */
    public void insertsText(String text) {
        this.currentSearchInput = text;
        System.out.println("[Admin -> UI] Administrator inserts text: '" + text + "'");
    }

    /**
     * Handles the event when the search button is clicked by the administrator.
     * This method implements the core logic of the search use case as described in the sequence diagram.
     * // Added to satisfy requirement {'Flow of Events': '2. Administrator clicks the "Search" button.'} and resolve SC-2.
     * // Renamed from onSearchButtonClicked() to clicksSearchButton() to match sequence diagram message m4.
     */
    public void clicksSearchButton() {
        System.out.println("[Admin -> UI] Administrator clicks 'Search' button.");
        searchCancelled = false; // Reset cancellation flag for a new search

        // Precondition check: Administrator is logged in. (Assumed by main caller)
        System.out.println("[UI] Administrator is logged in. (Precondition met)");

        // 1. Administrator enters search query. (Simulated by insertsText call before this method)
        // Note: The sequence diagram shows `Admin -> UI : insertsText(searchText : String)`
        // occurring before `clicksSearchButton()`.

        // Simulate alternative flow for user stopping operation
        if (searchCancelled) {
            System.out.println("[UI] Operation cancelled before search service call.");
            // deactivate UI #LightGrey // Deactivate clicksSearchButton operation
            // deactivate UI // Deactivate initial UI activation
            return; // Operation cancelled
        }

        // UI -> UI : searchCriteria = getSearchInput()
        // Modified to resolve Inconsistency 2 and align with CD
        SearchCriteriaDTO searchCriteria = getSearchInput();
        System.out.println("[UI] Calling existing method to get search criteria as DTO: " + searchCriteria);

        SearchResultDTO searchResult = null;
        try {
            // UI -> Service : searchEntities(searchCriteria : SearchCriteriaDTO)
            System.out.println("[UI -> Service] Calling searchEntities with " + searchCriteria);
            searchResult = searchService.searchEntities(searchCriteria);
            System.out.println("[Service --> UI] Received searchResult from SearchService.");

            // UI -> UI : displaySearchResults(searchResult)
            System.out.println("[UI] Formatting and presenting the aggregated search results.");
            displaySearchResults(searchResult);

            // UI --> Admin : displaySearchResults(searchResult)
            // (Implicitly done by printing to console)
        } catch (SMOSServerException e) {
            // Service -> UI : displayError("Server connection lost during ...")
            System.out.println("[Service -> UI] Propagated SMOSServerException to UI.");
            displayError(e.getMessage()); // UI displays error to Admin
            // UI --> Admin : displayError("Server connection lost during ...")
            // (Implicitly done by printing to console)
        } finally {
            // This block represents the deactivation of the UI activation in the sequence diagram
            // deactivate UI #LightGrey // Deactivate clicksSearchButton operation
            // deactivate UI // Deactivate initial UI activation
            System.out.println("[UI] Search operation completed or aborted.");
        }
    }

    /**
     * Handles the event when the administrator cancels the search operation.
     * This method sets a flag to indicate cancellation.
     * // Added to satisfy requirement {'Exit Conditions': 'The user stops the operation.'} and resolve CD-T1/SC-3.
     * // Renamed from cancelSearch() to cancelsSearch() to match sequence diagram message m5.
     */
    public void cancelsSearch() {
        System.out.println("[Admin -> UI] Administrator cancels search operation.");
        this.searchCancelled = true;
        System.out.println("[UI] Search operation has been cancelled.");
    }
}