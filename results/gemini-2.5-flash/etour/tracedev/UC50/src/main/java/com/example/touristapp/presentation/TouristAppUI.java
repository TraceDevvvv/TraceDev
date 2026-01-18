package com.example.touristapp.presentation;

import com.example.touristapp.dto.SiteFeedbackDto;

import java.util.List;

/**
 * The User Interface (UI) component of the Tourist Application.
 * Responsible for displaying information to the user and receiving user input (simulated here).
 */
public class TouristAppUI {

    /**
     * Displays a list of visited sites along with their feedback to the tourist.
     * Fulfills REQ-001: Goal of displaying visited sites.
     * Satisfies REQ-006: System displays a list of sites visited.
     *
     * @param siteFeedbackDtos A list of {@link SiteFeedbackDto} objects to be displayed.
     */
    public void displayVisitedSites(List<SiteFeedbackDto> siteFeedbackDtos) {
        System.out.println("\n--- Visited Sites with Feedback ---");
        if (siteFeedbackDtos == null || siteFeedbackDtos.isEmpty()) {
            System.out.println("You have not visited any sites yet or no data is available.");
            return;
        }

        for (SiteFeedbackDto dto : siteFeedbackDtos) {
            System.out.println(dto.toString());
        }
        System.out.println("-----------------------------------");
    }

    /**
     * Displays an error message to the tourist, typically due to system issues.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     *
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("An error occurred: " + message);
        System.err.println("Please try again later.");
        System.err.println("-------------");
    }

    /**
     * Simulates the Tourist selecting the "View Visited Sites" feature.
     * This method would typically be triggered by a button click or menu selection in a real UI.
     * // Traceability: Sequence Diagram: m1: Tourist selects "View Visited Sites"
     *
     * @param touristId The ID of the currently authenticated tourist.
     * @param controller The {@link SiteController} to handle the request.
     */
    public void selectViewVisitedSites(String touristId, SiteController controller) {
        System.out.println("\nTourist (ID: " + touristId + ") selects 'View Visited Sites' option.");
        // Depicts REQ-004: Tourist selects feature.
        // User is already authenticated (Satisfies REQ-003).
        controller.handleDisplayVisitedSitesRequest(touristId);
    }
}