package com.example.boundary;

import com.example.model.Site;
import com.example.dto.FeedbackDTO;

/**
 * Interface for FeedbackForm.
 */
public interface IFeedbackForm {
    void displayForm(Site site);
    FeedbackDTO getFeedbackData();
    void showSuccess();
    void showError(String message);
}