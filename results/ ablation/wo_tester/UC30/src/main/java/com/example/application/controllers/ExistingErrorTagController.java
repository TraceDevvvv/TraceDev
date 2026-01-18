package com.example.application.controllers;

/**
 * Controller for handling existing tag error scenario.
 */
public class ExistingErrorTagController {
    public void handle(String tagName) {
        // Implementation could include logging, alerting, or other error handling.
        // For now, we just log to console as per the sequence diagram.
        System.out.println("ExistingErrorTagController: Tag '" + tagName + "' already exists.");
    }
}