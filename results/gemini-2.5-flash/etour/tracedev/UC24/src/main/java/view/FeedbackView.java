package com.example.view;

import com.example.model.Feedback;

import java.util.List;

/**
 * View component responsible for displaying feedback information and error messages.
 */
public class FeedbackView {

    /**
     * Displays a list of feedback entries.
     * @param feedback The list of Feedback objects to display.
     */
    public void displayFeedback(List<Feedback> feedback) {
        System.out.println("\n--- Feedback for Selected Site ---");
        if (feedback == null || feedback.isEmpty()) {
            System.out.println("No feedback available for this site.");
            return;
        }
        feedback.forEach(f -> System.out.println(" - " + f.getDetails()));
        System.out.println("----------------------------------\n");
    }

    /**
     * Displays an error message to the operator.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!\n");
    }
}