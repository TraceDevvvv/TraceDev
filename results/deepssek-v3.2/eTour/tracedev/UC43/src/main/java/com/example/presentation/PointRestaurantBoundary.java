package com.example.presentation;

import com.example.dto.RefreshmentDTO;

/**
 * Boundary class representing the user interface for the Point Restaurant operator.
 */
public class PointRestaurantBoundary {
    
    /**
     * Displays refreshment data in the UI form.
     * @param refreshmentData the DTO containing refreshment data
     */
    public void displayRefreshmentData(RefreshmentDTO refreshmentData) {
        System.out.println("Displaying refreshment data in form: " + refreshmentData.getName());
    }

    /**
     * Simulates retrieving user input from the form.
     * @return a RefreshmentDTO populated with user input
     */
    public RefreshmentDTO getRefreshmentFormInput() {
        // For demonstration, returning a dummy DTO.
        RefreshmentDTO dto = new RefreshmentDTO();
        dto.setId(1L);
        dto.setName("Updated Refreshment");
        dto.setDescription("Updated description");
        dto.setPrice(new java.math.BigDecimal("5.99"));
        dto.setAvailable(true);
        return dto;
    }

    /**
     * Shows a confirmation prompt to the user.
     * @return true if the user confirms, false if cancels
     */
    public boolean showConfirmationPrompt() {
        // In a real UI, this would show a dialog and wait for user response.
        // For this simulation, we assume the user confirms.
        System.out.println("Showing confirmation dialog...");
        return true;
    }

    /**
     * Shows a success notification to the user.
     */
    public void showSuccessNotification() {
        System.out.println("SUCCESS: Refreshment updated successfully.");
    }

    /**
     * Shows an error notification to the user.
     * @param errorMessage the error message to display
     */
    public void showErrorNotification(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    /**
     * Simulates login process (added from sequence diagram).
     * @param credentials the user credentials
     */
    public void login(com.example.authentication.Credentials credentials) {
        System.out.println("Logging in with username: " + credentials.getUsername());
    }

    /**
     * Simulates enabling information functionality after successful login.
     */
    public void enablesInformationFunctionality() {
        System.out.println("Information functionality enabled.");
    }

    /**
     * Simulates displaying a form with data (from sequence diagram).
     */
    public void displayFormWithData() {
        System.out.println("Displaying form with data.");
    }

    /**
     * Simulates showing confirmation dialog (from sequence diagram).
     */
    public void showConfirmationDialog() {
        System.out.println("Showing confirmation dialog.");
    }

    /**
     * Simulates displaying success message (from sequence diagram).
     */
    public void displaySuccessMessage() {
        showSuccessNotification();
    }

    /**
     * Simulates displaying validation errors (from sequence diagram).
     * @param errors the validation error messages
     */
    public void displayValidationErrors(String errors) {
        showErrorNotification(errors);
    }

    /**
     * Simulates changes data in form (from sequence diagram).
     */
    public void changesDataInForm() {
        System.out.println("User changes data in form.");
    }

    /**
     * Simulates submits form (from sequence diagram).
     */
    public void submitsForm() {
        System.out.println("Form submitted.");
    }
}