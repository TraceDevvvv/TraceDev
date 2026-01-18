package com.example.adapters;

import com.example.application.InsertFeedbackResult;

/**
 * Presenter to format success/error responses for the UI.
 */
public class FeedbackPresenter {
    public FeedbackResponse presentSuccess() {
        return new FeedbackResponse("SUCCESS", "Feedback submitted successfully.");
    }

    public FeedbackResponse presentError(InsertFeedbackResult error) {
        String message;
        switch (error) {
            case ERROR_INVALID_DATA:
                message = "Invalid vote or comment. Vote must be 1-5, comment cannot be empty.";
                break;
            case FEEDBACK_ALREADY_EXISTS:
                message = "You have already submitted feedback for this site.";
                break;
            case SERVER_INTERRUPTION:
                message = "Server connection lost. Please try again.";
                break;
            case OPERATION_CANCELLED:
                message = "Operation cancelled.";
                break;
            default:
                message = "An unexpected error occurred.";
        }
        return new FeedbackResponse(error.name(), message);
    }

    /**
     * Create success response (for sequence diagram message m34).
     */
    public FeedbackResponse createSuccessResponse() {
        return presentSuccess();
    }
}