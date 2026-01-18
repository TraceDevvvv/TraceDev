package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of FeedbackRepository.
 * For simplicity, uses an in‑memory list.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private List<FeedbackDTO> feedbackList = new ArrayList<>();

    @Override
    public boolean save(FeedbackDTO feedback) {
        // Simulate save operation; assume always successful unless forced error.
        // For demonstration: if feedback ID contains "ERROR", return false.
        if (feedback.getFeedbackId().contains("ERROR")) {
            return false;
        }
        feedbackList.add(feedback);
        return true;
    }

    @Override
    public boolean existsByTouristAndSite(String touristId, String siteId) {
        return feedbackList.stream()
                .anyMatch(f -> f.getTouristId().equals(touristId) && f.getSiteId().equals(siteId));
    }

    @Override
    public List<FeedbackDTO> findByTouristId(String touristId) {
        List<FeedbackDTO> result = new ArrayList<>();
        for (FeedbackDTO dto : feedbackList) {
            if (dto.getTouristId().equals(touristId)) {
                result.add(dto);
            }
        }
        return result;
    }

    // Helper to create a FeedbackDTO from a form
    public FeedbackDTO createFeedbackDTO(FeedbackForm form) {
        String id = UUID.randomUUID().toString();
        return new FeedbackDTO(id, form.getTouristId(), form.getSiteId(),
                form.getRating(), form.getComment(), new Date());
    }
}