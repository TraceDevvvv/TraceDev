package com.newsagency.ui;

import com.newsagency.controller.NewsController;
import com.newsagency.dto.NewsDTO;
import com.newsagency.model.AgencyOperator;
import com.newsagency.model.Operator;
import java.util.Map;

/**
 * View component for news insertion functionality.
 * Implements the boundary in the MVC pattern.
 */
public class NewsInsertionView {
    private Map<String, Object> formData;
    private NewsController controller;
    private AgencyOperator currentOperator;
    
    public NewsInsertionView() {
        this.formData = new java.util.HashMap<>();
    }
    
    public void setController(NewsController controller) {
        this.controller = controller;
    }
    
    public void setCurrentOperator(AgencyOperator operator) {
        this.currentOperator = operator;
    }
    
    /**
     * Validates login status as per entry conditions.
     * @return true if operator is logged in, false otherwise
     */
    public boolean validateLoginStatus() {
        if (currentOperator == null) {
            return false;
        }
        return controller != null && controller.validateLogin();
    }
    
    /**
     * Activates the insert news feature (Flow of Events 1).
     */
    public void activateInsertNewsFeature() {
        // Entry condition: check login status
        if (!validateLoginStatus()) {
            System.out.println("ERROR: Agency Operator must be logged in to insert news.");
            return;
        }
        
        System.out.println("News insertion feature activated for operator: " + 
                          (currentOperator != null ? currentOperator.getOperatorId() : "Unknown"));
        displayForm();
    }
    
    /**
     * Displays the news insertion form.
     */
    public void displayForm() {
        System.out.println("Displaying news insertion form...");
        System.out.println("Form fields: Title, Content, Author, Category");
    }
    
    /**
     * Collects form data from UI.
     * @return Map containing form data
     */
    public Map<String, Object> collectFormData() {
        // In a real implementation, this would collect data from UI components
        // For simulation, we'll use data from the current formData
        System.out.println("Collecting form data...");
        return formData;
    }
    
    /**
     * Shows confirmation dialog to user.
     * @return true if user confirms, false if cancels
     */
    public boolean showConfirmationDialog() {
        System.out.println("Showing confirmation dialog: 'Do you want to insert this news?'");
        // In real implementation, this would show a dialog and wait for user response
        // For simulation, assume user confirms
        return true;
    }
    
    /**
     * Displays success message after successful operation.
     */
    public void displaySuccessMessage() {
        System.out.println("SUCCESS: News has been successfully inserted!");
    }
    
    /**
     * Displays error message when operation fails.
     */
    public void displayErrorMessage() {
        System.out.println("ERROR: News insertion failed!");
    }
    
    /**
     * Requests form submission from controller (Flow of Events 4).
     */
    public void requestFormSubmission() {
        if (controller != null) {
            System.out.println("Requesting form submission from controller...");
            controller.requestFormSubmission();
        }
    }
    
    /**
     * Cleans up form data after operation completion or cancellation.
     */
    public void cleanupForm() {
        formData.clear();
        System.out.println("Form cleaned up.");
    }
    
    /**
     * Handles connection loss (Exit Conditions).
     */
    public void handleConnectionLoss() {
        System.out.println("Connection to database lost. Please try again later.");
        cleanupForm();
    }
    
    /**
     * Requests confirmation dialog from Use Case Controller.
     * @return user confirmation result
     */
    public boolean requestConfirmationDialog() {
        return showConfirmationDialog();
    }
    
    /**
     * Simulates form filling by operator.
     * @param data form data provided by operator
     */
    public void fillForm(Map<String, Object> data) {
        if (data != null) {
            this.formData.putAll(data);
            System.out.println("Form filled with data: " + data);
        }
    }
    
    /**
     * Simulates form submission by operator.
     */
    public void submitForm() {
        if (controller != null) {
            System.out.println("Form submitted by operator.");
            controller.processFormSubmission();
        }
    }
    
    /**
     * Creates NewsDTO from form data.
     * @return NewsDTO instance
     */
    public NewsDTO createNewsDTO() {
        NewsDTO dto = new NewsDTO();
        if (formData.containsKey("title")) {
            dto.setTitle((String) formData.get("title"));
        }
        if (formData.containsKey("content")) {
            dto.setContent((String) formData.get("content"));
        }
        if (formData.containsKey("author")) {
            dto.setAuthor((String) formData.get("author"));
        }
        if (formData.containsKey("category")) {
            dto.setCategory((String) formData.get("category"));
        }
        return dto;
    }
}