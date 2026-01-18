package com.example.controller;

import com.example.dto.FeedbackDTO;
import com.example.exception.ConnectionException;
import com.example.service.FeedbackService;
import java.util.List;

/**
 * Controller that handles the view feedback use case.
 * Depends on FeedbackService to retrieve feedback data.
 */
public class ViewFeedbackController {
    private FeedbackService feedbackService;

    public ViewFeedbackController(FeedbackService service) {
        this.feedbackService = service;
    }

    /**
     * Retrieves feedback for a given site ID.
     * Calls the service and returns a list of FeedbackDTO.
     * Handles connection exceptions as per the sequence diagram.
     */
    public List<FeedbackDTO> viewFeedback(String siteId) {
        try {
            return feedbackService.getFeedbackForSite(siteId);
        } catch (ConnectionException e) {
            String errorMsg = handleError(e);
            System.err.println("Controller handled connection error: " + errorMsg);
            return null; // or could return empty list, depending on requirement
        }
    }

    /**
     * Handles connection exceptions as per Exit Conditions requirement.
     * Returns a user-friendly error message.
     */
    public String handleError(ConnectionException exception) {
        return "Connection error occurred: " + exception.getMessage() + " (Error code: " + exception.getErrorCode() + ")";
    }
}