package com.example.ui;

import com.example.controller.CulturalHeritageCardController;
import com.example.dto.CulturalGoodDTO;
import com.example.exception.ServiceException;
import java.util.List;

/**
 * User interface for the agency operator.
 * Implements the interactions described in the sequence diagram.
 */
public class AgencyOperatorUI {
    private CulturalHeritageCardController controller;

    public AgencyOperatorUI(CulturalHeritageCardController controller) {
        this.controller = controller;
    }

    /**
     * Displays search results as per sequence diagram pre-conditions.
     * Called after SearchCulturalHeritage use case.
     * @param results list of CulturalGoodDTO objects
     */
    public void displaySearchResults(List<CulturalGoodDTO> results) {
        System.out.println("=== Search Results ===");
        for (CulturalGoodDTO dto : results) {
            System.out.println(dto.getId() + ": " + dto.getTitle());
        }
        System.out.println("======================");
    }

    /**
     * Called when the operator selects a cultural good from the list.
     * Corresponds to step 1 in the sequence diagram.
     * @param culturalGoodId the selected cultural good's ID
     */
    public void onCardSelected(String culturalGoodId) {
        System.out.println("Selected cultural good ID: " + culturalGoodId);
        try {
            // Step 2: call controller to view card details
            CulturalGoodDTO details = controller.viewCard(culturalGoodId);
            // Step 9: display the details
            displayCardDetails(details);
        } catch (ServiceException e) {
            // Error path: display error message to the operator
            displayError(e.getMessage());
        }
    }

    /**
     * Displays the details of a cultural good card.
     * Corresponds to step 9 in the sequence diagram (success path).
     * @param details the CulturalGoodDTO containing details
     */
    public void displayCardDetails(CulturalGoodDTO details) {
        System.out.println("=== Cultural Heritage Card Details ===");
        System.out.println("ID: " + details.getId());
        System.out.println("Title: " + details.getTitle());
        System.out.println("Description: " + details.getDescription());
        System.out.println("Location: " + details.getLocation());
        System.out.println("Period: " + details.getPeriod());
        System.out.println("=====================================");
    }

    /**
     * Displays an error message to the operator.
     * As per class diagram and sequence diagram error path (step 8).
     * @param message the error message to display
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    // Additional method to correspond with sequence diagram message "Displays list"
    public void displaysList(List<CulturalGoodDTO> items) {
        displaySearchResults(items);
    }

    // Additional method to correspond with sequence diagram message "Shows error message"
    public void showsErrorMessage(String message) {
        displayError(message);
    }
}