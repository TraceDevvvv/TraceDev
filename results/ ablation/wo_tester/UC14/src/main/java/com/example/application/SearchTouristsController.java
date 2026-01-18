package com.example.application;

import com.example.domain.Tourist;
import java.util.List;

/**
 * Controller for searching tourists.
 * Quality Requirement: The system must reliably process search requests for tourist accounts.
 */
public class SearchTouristsController {
    private ITouristRepository touristRepository;

    public SearchTouristsController(ITouristRepository repo) {
        this.touristRepository = repo;
    }

    public List<Tourist> searchTourists(SearchCriteria criteria) {
        // Delegate to repository
        return touristRepository.findByCriteria(criteria);
    }
}