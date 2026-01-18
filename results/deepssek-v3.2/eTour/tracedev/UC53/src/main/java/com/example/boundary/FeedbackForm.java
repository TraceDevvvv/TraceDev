package com.example.boundary;

import com.example.model.Site;
import com.example.dto.FeedbackDTO;

/**
 * Concrete implementation of IFeedbackForm.
 */
public class FeedbackForm implements IFeedbackForm {

    @Override
    public void displayForm(Site site) {
        System.out.println("Displaying feedback form for site: " + site.getName());
    }

    @Override
    public FeedbackDTO getFeedbackData() {
        // Simulate retrieving data from form fields
        return new FeedbackDTO("T001", "S001", 5, "Excellent site!");
    }

    @Override
    public void showSuccess() {
        System.out.println("Success! Feedback submitted.");
    }

    @Override
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Additional sequence diagram method: activateFeedback()
     */
    public void activateFeedback() {
        System.out.println("Feedback form activated.");
    }

    /**
     * Additional sequence diagram method: closeForm()
     */
    public void closeForm() {
        System.out.println("Form closed.");
    }

    /**
     * Additional sequence diagram method: cancel()
     */
    public void cancel() {
        System.out.println("Cancel operation.");
    }
}