package com.etour.feedback;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository to manage feedback data. Uses in-memory storage for simplicity.
 * In a real application, this would interact with a database.
 */
public class FeedbackRepository {
    private final Map<String, Set<Feedback>> feedbackByTourist = new ConcurrentHashMap<>();
    private final Map<String, Set<Feedback>> feedbackBySite = new ConcurrentHashMap<>();
    private final List<String> visitedSites = Collections.synchronizedList(new ArrayList<>());

    /**
     * Checks if the tourist has already issued feedback for the given site.
     */
    public boolean hasTouristFeedbackForSite(String touristId, String siteId) {
        Set<Feedback> feedbacks = feedbackByTourist.get(touristId);
        if (feedbacks == null) return false;
        return feedbacks.stream().anyMatch(f -> f.getSiteId().equals(siteId));
    }

    /**
     * Saves feedback and records the site as visited.
     * Returns true if successful, false if duplicate feedback exists.
     */
    public boolean saveFeedback(Feedback feedback) {
        String touristId = feedback.getTouristId();
        String siteId = feedback.getSiteId();

        // Quality requirement: verify tourist hasn't already issued feedback for the site
        if (hasTouristFeedbackForSite(touristId, siteId)) {
            return false;
        }

        // Save feedback
        feedbackByTourist.computeIfAbsent(touristId, k -> ConcurrentHashMap.newKeySet()).add(feedback);
        feedbackBySite.computeIfAbsent(siteId, k -> ConcurrentHashMap.newKeySet()).add(feedback);

        // Record site as visited (if not already)
        synchronized (visitedSites) {
            if (!visitedSites.contains(siteId)) {
                visitedSites.add(siteId);
            }
        }

        return true;
    }

    /**
     * Gets all feedbacks for a tourist.
     */
    public List<Feedback> getFeedbacksByTourist(String touristId) {
        Set<Feedback> feedbacks = feedbackByTourist.get(touristId);
        return feedbacks == null ? Collections.emptyList() : new ArrayList<>(feedbacks);
    }

    /**
     * Gets all feedbacks for a site.
     */
    public List<Feedback> getFeedbacksBySite(String siteId) {
        Set<Feedback> feedbacks = feedbackBySite.get(siteId);
        return feedbacks == null ? Collections.emptyList() : new ArrayList<>(feedbacks);
    }

    /**
     * Gets all visited sites.
     */
    public List<String> getVisitedSites() {
        return new ArrayList<>(visitedSites);
    }
}