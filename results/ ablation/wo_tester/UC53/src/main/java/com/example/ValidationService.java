package com.example;

/**
 * Service for validating feedback data.
 */
public class ValidationService {
    public boolean validateFeedback(FeedbackForm formData) {
        return isRatingValid(formData.getRating()) && isCommentValid(formData.getComment());
    }

    public boolean isRatingValid(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public boolean isCommentValid(String comment) {
        return comment != null && comment.trim().length() > 0 && comment.length() <= 500;
    }

    public boolean isDataSufficient(FeedbackForm formData) {
        return formData.getTouristId() != null && !formData.getTouristId().trim().isEmpty() &&
               formData.getSiteId() != null && !formData.getSiteId().trim().isEmpty() &&
               formData.getRating() > 0 && formData.getComment() != null;
    }
}