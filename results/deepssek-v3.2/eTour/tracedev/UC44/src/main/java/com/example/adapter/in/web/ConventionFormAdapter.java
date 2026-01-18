package com.example.adapter.in.web;

import com.example.application.service.SubmitConventionService;
import com.example.application.service.ConfirmConventionService;
import com.example.application.service.SubmitConventionService.ValidationFailedException;
import com.example.domain.ConventionData;
import com.example.domain.ConventionRequest;
import com.example.domain.FormData;
import com.example.domain.ConventionFormData;
import java.util.List;

public class ConventionFormAdapter {
    private final SubmitConventionService submitService;
    private final ConfirmConventionService confirmService;
    private final ErrorHandler errorHandler;

    public ConventionFormAdapter(SubmitConventionService submitService,
                                 ConfirmConventionService confirmService,
                                 ErrorHandler errorHandler) {
        this.submitService = submitService;
        this.confirmService = confirmService;
        this.errorHandler = errorHandler;
    }

    public ConventionFormData displayForm() {
        return displayConventionForm();
    }

    public void enableConventionRequest() {
        System.out.println("Convention request functionality enabled.");
    }

    public ConventionFormData displayConventionForm() {
        ConventionFormData formData = new ConventionFormData();
        System.out.println("Displaying convention form.");
        return formData;
    }

    public ConventionRequest handleFormSubmission(FormData formData) {
        try {
            ConventionData conventionData = formData.toConventionData();
            ConventionRequest request = submitService.submitConvention(conventionData);
            showConfirmationDialog(request.getRequestId());
            return request;
        } catch (ValidationFailedException e) {
            List<String> errors = List.of(e.getMessage());
            errorHandler.handleValidationError(errors);
            showValidationErrors(errors);
            throw e;
        } catch (Exception e) {
            errorHandler.logError("Error submitting form", e);
            throw new RuntimeException("Submission failed", e);
        }
    }

    public void handleConfirmation(String requestId) {
        try {
            confirmService.confirmConvention(requestId);
            showSuccessNotification();
        } catch (IllegalArgumentException e) {
            errorHandler.logError("Invalid request ID: " + requestId, e);
            throw e;
        } catch (Exception e) {
            errorHandler.logError("Error confirming request", e);
            throw e;
        }
    }

    public void cancelOperation(String requestId) {
        // In a real implementation, would fetch request and call cancel()
        // For now, simulate cancellation
        System.out.println("Cancelling request: " + requestId);
        showCancellationMessage();
    }

    public void showConfirmationDialog(String requestId) {
        System.out.println("Showing confirmation dialog for request: " + requestId);
    }

    public void showSuccessNotification() {
        System.out.println("Success! Convention request sent to agency.");
    }

    public void showCancellationMessage() {
        System.out.println("Operation cancelled.");
    }

    public void showAuthenticationError() {
        System.out.println("Authentication error. Please log in.");
    }

    public void showConnectionError() {
        System.out.println("Connection error. Please check your network.");
    }

    public void showValidationErrors(List<String> errors) {
        System.out.println("Validation errors: " + errors);
    }
}