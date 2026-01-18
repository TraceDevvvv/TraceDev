package com.example.repository;

import com.example.domain.Site;
import java.util.List;

/**
 * Repository interface for Site entities.
 */
public interface SiteRepository {
    /**
     * Finds sites by criteria.
     * @param criteria the search criteria
     * @return list of sites matching the criteria
     */
    List<Site> findByCriteria(String criteria);
}