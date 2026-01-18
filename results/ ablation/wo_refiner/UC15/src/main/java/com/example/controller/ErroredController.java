package com.example.controller;

import com.example.ui.ErroredUI;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for errored use case.
 * Added to satisfy requirement REQ-009.
 */
public class ErroredController {
    private ErroredUI erroredUI;
    
    public ErroredController() {
        this.erroredUI = new ErroredUI();
    }
    
    public ErroredController(ErroredUI erroredUI) {
        this.erroredUI = erroredUI;
    }
    
    /**
     * Activates the errored use case.
     * Added to satisfy requirement REQ-009.
     */
    public void activate() {
        System.out.println("Errored use case activated");
        
        // Simulate some errors
        List<String> errors = Arrays.asList(
            "Validation failed: Name is required",
            "Email format is invalid",
            "Phone number contains invalid characters"
        );
        
        erroredUI.displayErrorDetails(errors);
    }
    
    /**
     * Show specific error details to agency operator as per sequence diagram.
     */
    public void showSpecificErrorDetails() {
        System.out.println("Showing specific error details to Agency Operator");
        activate(); // Using existing activation logic
    }
    
    public ErroredUI getErroredUI() {
        return erroredUI;
    }
    
    public void setErroredUI(ErroredUI erroredUI) {
        this.erroredUI = erroredUI;
    }
}