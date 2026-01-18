package com.example.infrastructure.presentation;

import com.example.application.ports.input.UpdateAccountStatusInputPort;
import com.example.application.ports.output.UpdateAccountStatusOutputPort;
import com.example.application.dtos.UpdateAccountStatusRequest;
import com.example.application.dtos.UpdateAccountStatusResponse;
import com.example.domain.TouristAccount;
import com.example.domain.enums.AccountOperation;
import com.example.domain.enums.AccountStatus;

/**
 * Controller handling Agency Operator interactions.
 * Implements both input port delegation and output port presentation.
 */
public class AgencyOperatorController implements UpdateAccountStatusOutputPort {
    private UpdateAccountStatusInputPort inputPort;
    private ActivationDeactivationSheet currentSheet;

    public AgencyOperatorController(UpdateAccountStatusInputPort inputPort) {
        this.inputPort = inputPort;
    }

    // Entry point for activation/deactivation request
    public void handleActivationRequest(String accountId, AccountOperation operation) {
        System.out.println("Controller: Received activation request for account " + accountId + 
                          ", operation: " + operation);
        
        // Create and check activation sheet
        currentSheet = new ActivationDeactivationSheet(accountId, operation);
        
        // Step 1a: Validate entry conditions
        if (!currentSheet.isAvailable()) {
            showError("Activation sheet not available");
            return;
        }
        
        // Step 2: Show confirmation
        askForConfirmation(accountId, operation);
        showConfirmation(accountId, operation);
        
        // In a real application, this would wait for user confirmation
        // For simulation, we'll automatically proceed
        receiveConfirmation();
    }

    // Added to satisfy requirement Flow of Events 2
    public void askForConfirmation(String accountId, AccountOperation operation) {
        System.out.println("Controller: Asking for confirmation to " + 
                          (operation == AccountOperation.ACTIVATE ? "activate" : "deactivate") + 
                          " account " + accountId);
    }

    public void showConfirmation(String accountId, AccountOperation operation) {
        TouristAccount accountDetails = currentSheet.getAccountDetails();
        System.out.println("=== CONFIRMATION REQUIRED ===");
        System.out.println("Account ID: " + accountId);
        System.out.println("Account Name: " + accountDetails.getName());
        System.out.println("Current Status: " + accountDetails.getStatus());
        System.out.println("Requested Operation: " + 
                          (operation == AccountOperation.ACTIVATE ? "ACTIVATE" : "DEACTIVATE"));
        System.out.println("Please confirm the operation.");
        System.out.println("=============================");
    }

    // Added to satisfy requirement Flow of Events 3
    public void receiveConfirmation() {
        System.out.println("Controller: Confirmation received from Agency Operator");
        
        // Create request and execute use case
        UpdateAccountStatusRequest request = new UpdateAccountStatusRequest(
                currentSheet.getAccountId(), 
                currentSheet.getOperationType()
        );
        
        // Execute the use case
        UpdateAccountStatusResponse response = inputPort.execute(request);
        
        // The response will be presented via the present() method
        // since we implement UpdateAccountStatusOutputPort
    }

    public void confirmOperation() {
        System.out.println("Controller: confirmOperation() called");
        receiveConfirmation();
    }

    // Implementation of UpdateAccountStatusOutputPort
    @Override
    public void present(UpdateAccountStatusResponse response) {
        if (response.isSuccess()) {
            showResult(response.getMessage(), response.getUpdatedStatus());
            showNotification(response.getMessage(), response.getUpdatedStatus());
        } else {
            showError(response.getMessage());
        }
    }

    // Added to satisfy requirement Exit Conditions
    public void showNotification(String message, AccountStatus status) {
        System.out.println("[NOTIFICATION] " + message);
        System.out.println("Updated Status: " + status);
    }

    public void showResult(String message, AccountStatus status) {
        System.out.println("=== OPERATION RESULT ===");
        System.out.println("Result: " + message);
        System.out.println("New Status: " + status);
        System.out.println("========================");
    }

    // Added to satisfy requirement Alternative Flow
    public void showError(String message) {
        System.out.println("!!! ERROR: " + message);
    }

    // Getter for testing
    public ActivationDeactivationSheet getCurrentSheet() {
        return currentSheet;
    }
}