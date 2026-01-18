package com.example.service;

import com.example.model.Feedback;
import com.example.model.Site;
import com.example.repository.FeedbackRepository;
import java.util.List;

/**
 * Service for feedback-related operations.
 */
public class FeedbackService {
    private FeedbackRepository feedbackRepo;

    public FeedbackService(FeedbackRepository feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    public List<Feedback> getFeedbackBySite(Site site) {
        System.out.println("FeedbackService: getFeedbackBySite called for site " + site.getSiteId());
        return feedbackRepo.findBySite(site);
    }

    public Feedback getFeedbackById(int id) {
        System.out.println("FeedbackService: getFeedbackById called for id " + id);
        return feedbackRepo.findById(id);
    }
}