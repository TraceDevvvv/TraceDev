
package com.example.interfaceadapters.controllers;

import com.example.domain.RefreshmentPoint;
import com.example.domain.repository.RefreshmentPointRepository;
import java.util.List;

/**
 * REST controller for refreshment point operations.
 * Implements REQ-006 requirement.
 */
public class RefreshmentPointController {
    
    private final RefreshmentPointRepository refreshmentPointRepository;
    
    public RefreshmentPointController(RefreshmentPointRepository repository) {
        this.refreshmentPointRepository = repository;
    }
    
    public List<RefreshmentPoint> searchRefreshmentPoints(String searchCriteria) {
        // Implementation for REQ-006
        List<RefreshmentPoint> points = refreshmentPointRepository.searchByName(searchCriteria);
        return points;
    }
}
