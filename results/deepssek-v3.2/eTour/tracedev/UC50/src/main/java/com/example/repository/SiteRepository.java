package com.example.repository;

import com.example.entity.Site;
import java.util.List;

/**
 * Repository interface for Site entities.
 */
public interface SiteRepository {
    List<Site> findSitesWithFeedbackByTouristId(String touristId);
}