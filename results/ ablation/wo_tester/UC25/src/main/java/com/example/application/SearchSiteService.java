package com.example.application;

import com.example.domain.Location;
import com.example.domain.SiteFeedback;
import java.util.List;

/**
 * Interface for searching site feedback.
 */
public interface SearchSiteService {
    List<SiteFeedback> searchFeedbackForLocation(Location location);
}