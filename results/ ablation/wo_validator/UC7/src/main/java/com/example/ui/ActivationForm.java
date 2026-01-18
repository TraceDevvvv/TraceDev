package com.example.ui;

import com.example.domain.ConventionDataDTO;

/**
 * UI form for displaying convention data and capturing operator confirmation.
 */
public class ActivationForm {

    private ConventionDataDTO conventionData;
    private boolean confirmed = false;

    /**
     * Displays the form with the given convention data.
     * @param conventionData the data to display
     */
    public void displayForm(ConventionDataDTO conventionData) {
        this.conventionData = conventionData;
        System.out.println("Displaying form with agreement data: " + conventionData.getAgreementData());
    }

    /**
     * Simulates the operator checking the data.
     * @return the agreement data for display
     */
    public String checkData() {
        return conventionData.getAgreementData();
    }

    /**
     * Simulates the operator confirming activation.
     * In a real UI, this would trigger a confirmation dialog.
     */
    public void confirmActivation() {
        System.out.println("Operator requested activation confirmation.");
    }

    /**
     * Gets the confirmation status from the operator.
     * This simulates the operator's decision after seeing the confirmation dialog.
     * @return true if operator confirmed, false otherwise
     */
    public boolean getConfirmation() {
        // Simulate user interaction: for demonstration, assume operator confirms.
        // In a real application, this would be set by a UI event.
        this.confirmed = true; // Assume confirmed for sequence flow
        return confirmed;
    }

    /**
     * Submits the confirmation to the controller (called by UI after operator confirms).
     */
    public void submitConfirmation() {
        // This method would be called by the UI layer when operator confirms.
        // In this simplified example, the controller handles confirmation directly.
    }
}