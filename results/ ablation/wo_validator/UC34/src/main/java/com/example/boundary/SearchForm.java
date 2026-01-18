package com.example.boundary;

import com.example.controller.SiteSearchController;
import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import java.util.List;
import java.util.Scanner;

/**
 * Presentation Layer boundary class.
 * Handles user interface for searching cultural heritage sites.
 */
public class SearchForm {
    private SiteSearchController controller;

    public SearchForm() {
        this.controller = new SiteSearchController();
    }

    /**
     * Simulates activation of search functionality.
     * Typically called when the user opens the search page.
     */
    public void activateSearch() {
        displayForm();
    }

    /**
     * Displays the search form to the user.
     */
    public void displayForm() {
        // In a real app this would be a GUI; here we simulate with console output.
        System.out.println("=== Cultural Heritage Site Search ===");
        System.out.println("Please fill the search criteria:");
    }

    /**
     * Collects search criteria from user input and creates a DTO.
     * @return SearchCriteriaDTO populated with user input
     */
    public SearchCriteriaDTO collectSearchCriteria() {
        Scanner scanner = new Scanner(System.in);
        SearchCriteriaDTO dto = new SearchCriteriaDTO();

        System.out.print("Site name (or leave blank): ");
        dto.setSiteName(scanner.nextLine());

        System.out.print("Cultural period (or leave blank): ");
        dto.setCulturalPeriod(scanner.nextLine());

        System.out.print("Maximum distance in km (0 for no limit): ");
        double maxDist = scanner.nextDouble();
        dto.setMaxDistance(maxDist);

        scanner.close(); // Note: In real application, better to manage scanner lifecycle appropriately.
        return dto;
    }

    /**
     * Called by the controller with search results for display.
     * @param results List of SiteDTO objects to display
     */
    public void showResults(List<SiteDTO> results) {
        System.out.println("\n=== Search Results ===");
        if (results.isEmpty()) {
            System.out.println("No sites found matching your criteria.");
        } else {
            for (SiteDTO site : results) {
                System.out.printf("- %s (ID: %s)%n", site.getName(), site.getId());
                System.out.printf("  Description: %s%n", site.getDescription());
                System.out.printf("  Period: %s, Location: (%.4f, %.4f)%n",
                        site.getCulturalPeriod(), site.getLatitude(), site.getLongitude());
            }
        }
    }

    /**
     * Displays an error message to the user.
     * @param message Error message to display
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Simulates the user filling and submitting the form.
     * This method orchestrates the sequence diagram steps:
     * displayForm, collectSearchCriteria, call controller, showResults.
     */
    public void fillAndSubmitForm() {
        // Step: displayForm() called earlier by activateSearch().
        // Step: collectSearchCriteria()
        SearchCriteriaDTO criteriaDTO = collectSearchCriteria();
        // Step: call controller.searchSites
        List<SiteDTO> results = controller.searchSites(criteriaDTO);
        // Step: showResults
        showResults(results);
    }
}