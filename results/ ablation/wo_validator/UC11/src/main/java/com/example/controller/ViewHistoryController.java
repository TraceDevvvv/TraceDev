package com.example.controller;

import com.example.entities.Convention;
import com.example.repository.ConventionRepository;
import java.util.List;

/**
 * Controller class as per class diagram and sequence diagram.
 * Receives requests from UI and retrieves data via repository.
 */
public class ViewHistoryController {
    private ConventionRepository repository;

    /**
     * Constructor with dependency injection.
     */
    public ViewHistoryController(ConventionRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves convention history for a given restaurant ID.
     * Implements sequence diagram flow: UI -> VC : retrieveConventionHistory
     */
    public List<Convention> retrieveConventionHistory(String restaurantId) {
        // As per sequence diagram, call repository
        List<Convention> results = repository.findByRestaurantId(restaurantId);
        // Simulate storing results as per sequence diagram (though not used further)
        // store results step is implicit in returning the list
        return results;
    }
}