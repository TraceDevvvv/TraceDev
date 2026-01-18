// Controller handling requests for displaying visited sites
package com.etour.controller;

import com.etour.model.Feedback;
import com.etour.service.SiteService;
import java.util.List;

public class SiteController {
    private SiteService siteService;
    private int currentTouristId; // Simulating authenticated tourist

    public SiteController(int touristId) {
        this.siteService = new SiteService();
        this.currentTouristId = touristId;
    }

    // Display list of sites visited (with feedback)
    public void displayVisitedSites() {
        System.out.println("Loading sites for Tourist ID: " + currentTouristId + "...");
        try {
            List<Feedback> sites = siteService.getSitesWithFeedbackByTourist(currentTouristId);
            if (sites.isEmpty()) {
                System.out.println("No sites found with feedback issued by you.");
            } else {
                System.out.println("=== Sites You Have Visited (with feedback) ===");
                for (int i = 0; i < sites.size(); i++) {
                    System.out.println((i + 1) + ". " + sites.get(i));
                }
                System.out.println("Total: " + sites.size() + " site(s)");
            }
        } catch (Exception e) {
            System.err.println("Error: Connection to server ETOUR interrupted.");
            System.err.println("Details: " + e.getMessage());
        }
    }
}