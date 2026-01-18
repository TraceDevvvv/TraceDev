package com.example.application;

import com.example.domain.Location;
import com.example.domain.SiteFeedback;
import com.example.infrastructure.SiteFeedbackRepository;
import java.util.List;

/**
 * Service implementation for searching site feedback.
 */
public class SearchSiteServiceImpl implements SearchSiteService {
    private SiteFeedbackRepository feedbackRepository;

    public SearchSiteServiceImpl(SiteFeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<SiteFeedback> searchFeedbackForLocation(Location location) {
        return feedbackRepository.findByLocation(location);
    }
}