package com.example.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SiteFeedbackRepository class
 * Implements ISiteFeedbackRepository and provides data access for SiteFeedback objects.
 * Uses in-memory data for demonstration purposes.
 */
public class SiteFeedbackRepository implements ISiteFeedbackRepository {
    private List<SiteFeedback> feedbacks = new ArrayList<>();

    /**
     * Constructor for SiteFeedbackRepository.
     * Initializes with some dummy data.
     */
    public SiteFeedbackRepository() {
        // Dummy data for demonstration
        feedbacks.add(new SiteFeedback("fb1", "loc1", 4, "Great park, loved the atmosphere.", new Date()));
        feedbacks.add(new SiteFeedback("fb2", "loc1", 5, "Beautiful and well-maintained.", new Date()));
        feedbacks.add(new SiteFeedback("fb3", "loc1", 3, "A bit crowded, but nice.", new Date()));
        feedbacks.add(new SiteFeedback("fb4", "loc2", 5, "Absolutely breathtaking view!", new Date()));
        feedbacks.add(new SiteFeedback("fb5", "loc2", 4, "Iconic, worth the visit.", new Date()));
        feedbacks.add(new SiteFeedback("fb6", "loc3", 4, "Amazing views of the city.", new Date()));
        feedbacks.add(new SiteFeedback("fb7", "loc3", 5, "Fantastic experience, highly recommend.", new Date()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SiteFeedback> findByLocationId(String locationId) {
        // Simulate database access delay
        try {
            Thread.sleep(50); // Simulate network/DB latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[SFR] Fetching feedback for location ID: " + locationId + " from DB (simulated).");
        // SD: m18, m19
        return feedbacks.stream()
                .filter(fb -> fb.getLocationId().equals(locationId))
                .collect(Collectors.toList());
    }
}