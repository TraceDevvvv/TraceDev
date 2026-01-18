package com.example;

import com.example.controller.SiteBookmarkController;
import com.example.model.Site;
import com.example.model.TouristCard;
import com.example.service.AuthenticationService;
import com.example.service.BookmarkService;

/**
 * Main class to run the Tourist Bookmark Removal use case.
 */
public class Main {
    
    public static void main(String[] args) {
        // Setup serv and data.
        AuthenticationService authService = new AuthenticationService();
        BookmarkService bookmarkService = new BookmarkService();
        
        // Simulating authentication.
        authService.authenticate("tourist123");
        
        // Create a sample site and tourist card.
        Site site = new Site("001", "Eiffel Tower", "A famous landmark in Paris.");
        TouristCard touristCard = new TouristCard("tourist123", site);
        
        // Add some sample bookmarks.
        bookmarkService.addSiteToBookmarks(site);
        bookmarkService.addSiteToBookmarks(new Site("002", "Colosseum", "Ancient amphitheatre in Rome."));
        
        // Create controller and run the use case.
        SiteBookmarkController controller = new SiteBookmarkController(authService, bookmarkService, touristCard);
        
        try {
            controller.run();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            // Exit Condition: There is an interruption of the connection to the server ETOUR.
            // For simplicity, we simulate a connection interruption here.
            System.err.println("Simulating connection interruption to server ETOUR...");
        } finally {
            bookmarkService.shutdown();
        }
    }
}