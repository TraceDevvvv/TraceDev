package com.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Tourist.
 */
public class Tourist {
    private String touristId;
    private String name;
    private TouristCard touristCard;
    private List<Feedback> feedbacks = new ArrayList<>();
    private List<VisitedSite> visitedSites = new ArrayList<>();

    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the tourist has a tourist card (Entry Condition #1).
     */
    public boolean hasTouristCard() {
        return touristCard != null;
    }

    public TouristCard getTouristCard() {
        return touristCard;
    }

    public void setTouristCard(TouristCard touristCard) {
        this.touristCard = touristCard;
    }

    /**
     * Gets the current site the tourist is at (assumed to be the last visited site).
     * This is a simplified implementation.
     */
    public Site getCurrentSite() {
        if (visitedSites.isEmpty()) {
            return null;
        }
        // Assuming the last visited site is the current site
        VisitedSite last = visitedSites.get(visitedSites.size() - 1);
        // In a real implementation, we would fetch the Site object using siteId.
        return null; // Placeholder
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public List<VisitedSite> getVisitedSites() {
        return visitedSites;
    }

    public void addVisitedSite(VisitedSite visitedSite) {
        visitedSites.add(visitedSite);
    }
}