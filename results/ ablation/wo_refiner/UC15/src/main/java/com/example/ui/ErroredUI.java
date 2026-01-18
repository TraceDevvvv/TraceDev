package com.example.ui;

import java.util.List;

/**
 * UI for displaying error details in errored use case.
 */
public class ErroredUI {
    
    public void displayErrorDetails(List<String> errors) {
        // Implementation to display error details
        System.err.println("Errored UI - Displaying error details:");
        if (errors != null) {
            for (String error : errors) {
                System.err.println("  - " + error);
            }
        }
    }
    
    /**
     * Show specific error details to agency operator.
     */
    public void showSpecificErrorDetails() {
        System.out.println("Errored UI: Showing specific error details to Agency Operator");
    }
    
    /**
     * Redirect to login page as per sequence diagram.
     */
    public void redirectToLogin() {
        System.out.println("Errored UI: Redirecting to login page");
    }
}