package com.example.controller;

import com.example.model.Site;
import com.example.repository.SiteRepository;
import com.example.repository.SiteRepositoryImpl;
import java.util.List;

/**
 * Controller for handling site list requests from tourists.
 */
public class SiteListController {
    private SiteRepository touristRepository;
    
    public SiteListController() {
        // In a real application, this would be injected. Here we instantiate the implementation.
        this.touristRepository = new SiteRepositoryImpl();
    }
    
    /**
     * Retrieves visited sites for a given tourist ID.
     * @param touristId the tourist's ID
     * @return list of Site objects visited by the tourist
     */
    public List<Site> getVisitedSites(int touristId) {
        validateAuthentication(); // assumed authentication check
        List<Site> sites = touristRepository.findSitesWithFeedback(touristId);
        
        // Simulate display logic as per sequence diagram
        if (sites.isEmpty()) {
            System.out.println("No visited sites");
        } else {
            System.out.println("Display site list: " + sites);
        }
        return sites;
    }
    
    /**
     * Validates authentication. For simplicity, just prints a message.
     */
    private void validateAuthentication() {
        System.out.println("Authentication validated for request.");
    }
}