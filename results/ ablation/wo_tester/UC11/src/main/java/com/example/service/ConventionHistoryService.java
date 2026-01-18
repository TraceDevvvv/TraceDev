package com.example.service;

import com.example.model.ConventionHistory;
import com.example.model.PointOfRest;
import java.util.List;

/**
 * Service interface for convention history operations.
 */
public interface ConventionHistoryService {
    List<ConventionHistory> getConventionHistoryForPointOfRest(PointOfRest pointOfRest);
}