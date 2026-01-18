package com.example.feedback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeedbackService {
    private List<Site> sites;
    private List<Feedback> feedbacks;

    public FeedbackService() {
        this.sites = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        initializeDummyData();
    }

    private void initializeDummyData() {
        sites.add(new Site("1", "Site Alpha", "Location A"));
        sites.add(new Site("2", "Site Beta", "Location B"));
        sites.add(new Site("3", "Site Gamma", "Location C"));

        feedbacks.add(new Feedback("101", "1", "Initial comment for Site Alpha"));
        feedbacks.add(new Feedback("102", "2", "Initial comment for Site Beta"));
        feedbacks.add(new Feedback("103", "3", "Initial comment for Site Gamma"));
    }

    public List<Site> searchSites(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>(sites);
        }
        List<Site> result = new ArrayList<>();
        for (Site site : sites) {
            if (site.getName().toLowerCase().contains(query.toLowerCase()) ||
                site.getLocation().toLowerCase().contains(query.toLowerCase())) {
                result.add(site);
            }
        }
        return result;
    }

    public List<Feedback> getFeedbacksBySite(String siteId) {
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            if (feedback.getSiteId().equals(siteId)) {
                result.add(feedback);
            }
        }
        return result;
    }

    public Optional<Feedback> getFeedbackById(String feedbackId) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId().equals(feedbackId)) {
                return Optional.of(feedback);
            }
        }
        return Optional.empty();
    }

    public boolean updateFeedbackComment(String feedbackId, String newComment) {
        // Validate comment: cannot be empty
        if (newComment == null || newComment.trim().isEmpty()) {
            return false;
        }
        for (Feedback feedback : feedbacks) {
            if (feedback.getId().equals(feedbackId)) {
                feedback.setComment(newComment);
                return true;
            }
        }
        return false;
    }
}