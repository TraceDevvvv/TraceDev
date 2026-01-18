package com.example.ui;

import com.example.dto.RefreshmentDTO;
import com.example.service.RefreshmentPointManagementService;

/**
 * Form for editing refreshment point data.
 */
public class RefreshmentPointEditForm {
    private RefreshmentDTO refreshmentData;
    private RefreshmentPointManagementService managementService;
    private String operatorId;

    public RefreshmentPointEditForm(RefreshmentPointManagementService managementService, String operatorId) {
        this.managementService = managementService;
        this.operatorId = operatorId;
    }

    /**
     * Displays the refreshment data in the form.
     */
    public void displayData(RefreshmentDTO refreshmentData) {
        this.refreshmentData = refreshmentData;
        System.out.println("Displaying form with data for refreshment: " + refreshmentData.getName());
        // In a real UI, this would update form fields
    }

    /**
     * Returns the currently modified DTO from the form.
     */
    public RefreshmentDTO getModifiedData() {
        // Assumption: form fields have been updated in refreshmentData
        return refreshmentData;
    }

    /**
     * Submits the form for processing.
     * Follows sequence diagram flow.
     */
    public void submitForm() {
        if (refreshmentData == null) {
            System.out.println("No data to submit");
            return;
        }

        try {
            // Call service to update refreshment point
            managementService.updateRefreshmentPoint(refreshmentData, operatorId);
            System.out.println("Update successful");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Cancels the form operation.
     */
    public void cancelForm() {
        System.out.println("Form cancelled, data unchanged");
        resetForm();
    }

    /**
     * Resets the form to initial state.
     */
    private void resetForm() {
        // Clear form data
        refreshmentData = null;
    }
}