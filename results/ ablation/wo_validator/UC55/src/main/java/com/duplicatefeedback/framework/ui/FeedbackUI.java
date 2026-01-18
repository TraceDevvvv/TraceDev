package com.duplicatefeedback.framework.ui;

import com.duplicatefeedback.application.controller.FeedbackUseCaseController;
import com.duplicatefeedback.adapter.web.UserSession;
import com.duplicatefeedback.application.controller.UseCaseResult;

/**
 * UI component that interacts with the user and delegates to the controller.
 * Part of the frameworks & drivers layer.
 */
public class FeedbackUI {
    private final FeedbackUseCaseController feedbackUseCaseController;
    private final UserSession userSession;

    public FeedbackUI(FeedbackUseCaseController feedbackUseCaseController, UserSession userSession) {
        this.feedbackUseCaseController = feedbackUseCaseController;
        this.userSession = userSession;
    }

    /**
     * Submits feedback on behalf of the user.
     * @param userId the user identifier
     * @param siteId the site identifier
     * @param content the feedback content
     */
    public void submitFeedback(String userId, String siteId, String content) {
        // Delegate to the use case controller
        UseCaseResult result = feedbackUseCaseController.execute(userId, siteId, content);

        if (result.isSuccess()) {
            showNotification("Feedback submitted successfully");
        } else {
            showError(result.getMessage());
            // For duplicate case, also show a notification as per sequence diagram
            if ("Feedback exists".equals(result.getMessage())) {
                showNotification("Feedback already issued for this site");
            }
        }
    }

    public void showNotification(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }

    public void showError(String message) {
        System.err.println("[ERROR] " + message);
    }
}