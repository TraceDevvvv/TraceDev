package com.example;

/**
 * Service interface for submitting feedback.
 */
public interface SubmitFeedbackService {
    ResultDTO submitFeedback(FeedbackForm formData);
    boolean validateTouristCard(String touristId);
    boolean validateSitePresence(String siteId);
}