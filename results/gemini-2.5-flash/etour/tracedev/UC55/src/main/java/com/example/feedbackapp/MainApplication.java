package com.example.feedbackapp;

import com.example.feedbackapp.controller.FeedbackController;
import com.example.feedbackapp.repository.FeedbackRepository;
import com.example.feedbackapp.repository.IFeedbackRepository;
import com.example.feedbackapp.service.FeedbackApplicationService;
import com.example.feedbackapp.view.FeedbackView;

/**
 * Main application class to demonstrate the feedback submission process.
 * This class sets up the dependencies and orchestrates the user interaction flow
 * as described in the sequence diagram.
 */
public class MainApplication {
    public static void main(String[] args) {
        // 1. Instantiate concrete implementations of repositories and views
        IFeedbackRepository feedbackRepository = new FeedbackRepository();
        FeedbackView feedbackView = new FeedbackView();

        // 2. Instantiate application serv, injecting their dependencies
        FeedbackApplicationService feedbackApplicationService = new FeedbackApplicationService(feedbackRepository);

        // 3. Instantiate controllers, injecting their dependencies
        FeedbackController feedbackController = new FeedbackController(feedbackApplicationService, feedbackView);

        System.out.println("--- Starting Feedback Application Demo ---");

        // Scenario 1: Attempt to submit feedback for a site that ALREADY HAS feedback
        System.out.println("\nScenario 1: Attempting to submit feedback for 'site123' (which already has feedback)");
        feedbackController.submitFeedbackAttempt("site123");

        System.out.println("\n--- End of Scenario 1 ---");
        System.out.println("\n--------------------------------------------------------------\n");

        // Scenario 2: Attempt to submit feedback for a site that DOES NOT have feedback
        System.out.println("Scenario 2: Attempting to submit feedback for 'site789' (which does NOT have feedback)");
        feedbackController.submitFeedbackAttempt("site789");

        System.out.println("\n--- End of Scenario 2 ---");
        System.out.println("\n--- Feedback Application Demo Finished ---");
    }
}