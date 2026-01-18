package com.example.tourist;

/**
 * View component that displays tourist card.
 * Interacts with AgencyOperator and uses Controller.
 * Traceability: R3 - Agency Operator interacts with this view, R5 - Flow starts from view.
 */
public class TouristCardView {
    private TouristController controller;
    private AgencyOperator operator;

    public TouristCardView(TouristController controller) {
        this.controller = controller;
        this.operator = new AgencyOperator();
    }

    /**
     * Validates if the operator is authenticated per REQ-004.
     * @return true if authenticated, false otherwise
     */
    public boolean validateAuthentication() {
        return operator.isLoggedIn();
    }

    /**
     * Shows tourist card for a given tourist ID.
     * @param touristId the tourist ID
     */
    public void showTouristCard(String touristId) {
        // First validate authentication
        if (!validateAuthentication()) {
            System.out.println("Authentication required. Please login.");
            return;
        }

        // Get tourist details from controller
        TouristDTO touristDTO = controller.displayTouristCard(touristId);
        
        if (touristDTO != null) {
            displayTouristDetails(touristDTO);
        } else {
            System.out.println("Tourist with ID " + touristId + " not found.");
        }
    }

    /**
     * Displays tourist details.
     * @param touristDTO the tourist data to display
     */
    public void displayTouristDetails(TouristDTO touristDTO) {
        System.out.println("=== Tourist Card ===");
        System.out.println("ID: " + touristDTO.getId());
        System.out.println("Name: " + touristDTO.getName());
        System.out.println("Contact: " + touristDTO.getContactInfo());
        System.out.println("====================");
    }
}