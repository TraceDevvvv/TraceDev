// Service to retrieve sites for which a tourist has issued feedback
package com.etour.service;

import com.etour.model.Feedback;
import java.util.ArrayList;
import java.util.List;

public class SiteService {
    // Simulating database or backend call to fetch feedbacks for a tourist
    public List<Feedback> getSitesWithFeedbackByTourist(int touristId) {
        List<Feedback> feedbacks = new ArrayList<>();
        // Mock data - in real scenario, fetch from database
        // For touristId = 1, return 3 sample sites
        feedbacks.add(new Feedback(101, touristId, 1, "Eiffel Tower", "Great view from top"));
        feedbacks.add(new Feedback(102, touristId, 2, "Colosseum", "Rich history"));
        feedbacks.add(new Feedback(103, touristId, 3, "Statue of Liberty", "Iconic landmark"));
        // For other touristId values, return empty list to simulate no feedbacks
        if (touristId != 1) {
            feedbacks.clear();
        }
        return feedbacks;
    }
}