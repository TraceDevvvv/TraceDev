package com.example.repository;

import com.example.domain.CulturalObject;
import com.example.domain.DateRange;
import java.util.List;

/**
 * Repository interface for CulturalObject data access.
 */
public interface CulturalObjectRepository {
    /**
     * Finds cultural objects based on search criteria.
     * @param criteria The search string (e.g., keyword).
     * @param filterType The type of filter (e.g., by period, location).
     * @param dateRange Optional date range for creation date.
     * @return A list of matching CulturalObject entities.
     */
    List<CulturalObject> findByCriteria(String criteria, String filterType, DateRange dateRange);
}