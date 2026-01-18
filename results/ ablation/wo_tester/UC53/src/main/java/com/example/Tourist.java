package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tourist.
 */
public class Tourist {
    private String touristId;
    private String touristCardId;
    private List<Site> visitedSites;

    public Tourist(String touristId, String touristCardId) {
        this.touristId = touristId;
        this.touristCardId = touristCardId;
        this.visitedSites = new ArrayList<>();
    }

    public String getTouristId() {
        return touristId;
    }

    public String getTouristCardId() {
        return touristCardId;
    }

    public boolean validateTouristCard() {
        // Simplified validation: assume card is valid if not empty
        return touristCardId != null && !touristCardId.trim().isEmpty();
    }

    public List<Site> getVisitedSites() {
        return visitedSites;
    }

    public void addVisitedSite(Site site) {
        visitedSites.add(site);
    }

    // Sequence diagram messages
    public void showFeedbackAlreadyReleased() {
        System.out.println("Tourist: Displaying 'FeedbackAlreadyReleased' notification.");
    }

    public void showFeedbackForm() {
        System.out.println("Tourist: Displaying feedback form.");
    }

    public void fillForm(int rating, String comment) {
        System.out.println("Tourist: Filling form with rating=" + rating + ", comment=" + comment);
    }

    public void submitForm(FeedbackForm formData) {
        System.out.println("Tourist: Submitting form for site " + formData.getSiteId());
    }

    public void showErrored() {
        System.out.println("Tourist: Displaying 'Errored' notification.");
    }

    public void showSuccessNotification() {
        System.out.println("Tourist: Displaying success notification.");
    }
}