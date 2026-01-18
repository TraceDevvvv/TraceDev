package com.example.repository;

import com.example.model.ConventionHistory;
import com.example.model.PointOfRest;
import java.util.List;

/**
 * Repository interface for ConventionHistory data access.
 */
public interface ConventionHistoryRepository {
    List<ConventionHistory> findByPointOfRest(PointOfRest pointOfRest);
}