package com.example.feedbackapp.view;

import java.util.Scanner;

/**
 * Represents the user interface component for feedback-related operations.
 * It's responsible for displaying information to the user and capturing user input.
 */
public class FeedbackView {

    private final Scanner scanner;

    /**
     * Constructor for FeedbackView.
     * Initializes a Scanner for simulating user input.
     */
    public FeedbackView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a notification message to the user.
     *
     * @param message The message to be displayed.
     */
    public void displayNotification(String message) {
        System.out.println("\n--- Feedback Notification ---");
        System.out.println(message);
        System.out.println("---------------------------");
        System.out.println("[FeedbackView] Clear and promptly displayed notification (Quality Req.)");
    }

    /**
     * Requests user confirmation after displaying a notification.
     * Renamed from 'requestUserConfirmation()' to 'confirmsReading()' as per audit report.
     *
     * @return true if the user confirms, false otherwise.
     */
    public boolean confirmsReading() {
        System.out.println("[FeedbackView] Please confirm you have read the notification (Type 'y' for yes):");
        // Simulate user input for confirmation
        String input = scanner.nextLine(); // A real UI would have a button click
        return input.trim().equalsIgnoreCase("y");
    }

    /**
     * Displays a confirmation message that the operation was cancelled.
     */
    public void displayOperationCancelledConfirmation() {
        System.out.println("\n[FeedbackView] Operation cancelled successfully and state recovered.");
        // Further UI updates might happen here, e.g., enabling form elements again.
    }
}