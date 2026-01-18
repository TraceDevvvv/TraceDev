package com.example.etour;

import com.example.etour.controller.BookmarkController;
import com.example.etour.model.Site;
import com.example.etour.model.Tourist;
import com.example.etour.service.EtourService;

/**
 * Main class to simulate the use case:
 * Tourist inserts a site into bookmarks.
 */
public class Main {
    public static void main(String[] args) {
        // Setup
        EtourService etourService = new EtourService();
        etourService.connect(); // start connected

        Tourist tourist = new Tourist("T123", "John Doe");
        Site site = new Site("S456", "Eiffel Tower");
        tourist.setCurrentSite(site); // Tourist card is in a particular site

        BookmarkController controller = new BookmarkController(etourService);
        controller.setTourist(tourist);

        System.out.println("Initial tourist state: " + tourist);
        System.out.println("--- Starting bookmark insertion flow ---");

        // Simulate tourist confirmation (true for yes, false for no)
        boolean touristConfirmation = true;
        boolean success = controller.insertSiteToBookmarks(touristConfirmation);

        System.out.println("Result: " + (success ? "SUCCESS" : "FAILED"));
        System.out.println("Final tourist state: " + tourist);
        System.out.println("ETOUR service connected? " + etourService.isConnected());
    }
}