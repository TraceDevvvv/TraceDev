package com.example.ui;

import com.example.agency.AgencyOperator;
import com.example.ui.model.FormData;
import com.example.ui.model.OperationResult;
import com.example.ui.model.ValidationResult;
import com.example.controller.InsertCulturalHeritageController;
import com.example.session.SessionManager;
import com.example.heritage.CulturalGood;

/**
 * Boundary class for the Insert Cultural Heritage UI.
 * Added to satisfy requirement REQ-005.
 */
public class InsertCulturalHeritageUI {
    private FormData formData; // current form data.
    private InsertCulturalHeritageController controller;
    private SessionManager sessionManager;
    
    public InsertCulturalHeritageUI(InsertCulturalHeritageController controller, SessionManager sessionManager) {
        this.controller = controller;
        this.sessionManager = sessionManager;
    }
    
    public void activateInsertNewCulturalGoodFeature() {
        // UI displays the insertion form.
        displayInsertionForm();
    }
    
    public void displayInsertionForm() {
        // Implementation to display the form.
        // In a real UI, this would show the form for the operator.
    }
    
    public OperationResult fillFormAndSubmit(String name, String description, String type, String location) {
        formData = new FormData(name, description, type, location);
        return controller.handleInsertRequest(formData);
    }
    
    public void displayValidationErrors(ValidationResult validationResult) {
        // Display validation errors to the operator.
    }
    
    public void displayDuplicateError() {
        // Display duplicate error to the operator.
    }
    
    public void displayConfirmationRequest() {
        // Display confirmation request to the operator.
    }
    
    public void verifyDataAndAskForConfirmation() {
        // Verify data and ask the operator for confirmation.
    }
    
    public OperationResult confirmOperation() {
        return controller.confirmationReceived();
    }
    
    public void displaySuccessNotification(OperationResult operationResult) {
        // Display success notification.
    }
    
    public OperationResult cancelOperation() {
        return controller.cancelRequest();
    }
    
    public void displayCancellationMessage() {
        // Display cancellation message.
    }
    
    public void displayConnectionErrorMessage() {
        // Display connection error message.
    }
}