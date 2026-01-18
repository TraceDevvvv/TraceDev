package com.example.refreshmentpoint;

import java.util.List;

/**
 * Main class to demonstrate the "ViewRefreshmentPointCard" use case.
 * This class orchestrates the interaction between the AgencyOperator and the RefreshmentPointService.
 */
public class ViewRefreshmentPointCard {

    public static void main(String[] args) {
        // 1. Initialize the RefreshmentPointService
        RefreshmentPointService service = new RefreshmentPointService();

        // 2. Initialize the AgencyOperator with the service
        AgencyOperator operator = new AgencyOperator(service);

        // Entry Condition: The agency has logged in.
        operator.login();

        // Check if the operator is logged in before proceeding
        if (operator.isLoggedIn()) {
            // Flow of events User System:
            // 1. View the list of points of rest as a result of the use case SearchRefreshmentPoint,
            //    it selects and activates a function to view the card.
            List<RefreshmentPoint> availablePoints = operator.viewRefreshmentPointList();

            // Simulate user selection and activation of the view function
            // This method encapsulates steps 1 and 2 of the flow:
            // - User selects a point.
            // - System uploads data to a selected restaurant (via getRefreshmentPointDetails).
            operator.selectAndDisplayRefreshmentPointCard(availablePoints);
        } else {
            System.out.println("Error: Operator failed to log in. Cannot proceed with viewing refreshment point cards.");
        }

        // Clean up resources
        operator.closeScanner();
        System.out.println("\nApplication finished.");
    }
}