package com.example.presentation.ui;

import java.util.List;
import java.util.UUID;

/**
 * Boundary class representing the UI form for justification.
 */
public class JustificationForm {
    private String dateInput;
    private String reasonInput;
    private UUID absenceIdInput;

    public boolean validateInputs() {
        // Simple validation: fields must not be empty
        return dateInput != null && !dateInput.trim().isEmpty() &&
               reasonInput != null && !reasonInput.trim().isEmpty() &&
               absenceIdInput != null;
    }

    public void showValidationErrors(List<String> errors) {
        System.out.println("Validation errors: " + errors);
    }

    public void showSuccessMessage(String message) {
        System.out.println("Success: " + message);
    }

    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    // Getters and setters for the input fields
    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public String getReasonInput() {
        return reasonInput;
    }

    public void setReasonInput(String reasonInput) {
        this.reasonInput = reasonInput;
    }

    public UUID getAbsenceIdInput() {
        return absenceIdInput;
    }

    public void setAbsenceIdInput(UUID absenceIdInput) {
        this.absenceIdInput = absenceIdInput;
    }
}