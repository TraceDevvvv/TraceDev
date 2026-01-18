package com.example.controller;

import com.example.model.Site;
import com.example.model.TouristCard;
import com.example.service.AuthenticationService;
import com.example.service.BookmarkService;
import java.util.Scanner;

/**
 * Controller for handling site bookmark removal operations.
 */
public class SiteBookmarkController {
    
    private AuthenticationService authService;
    private BookmarkService bookmarkService;
    private TouristCard touristCard;
    
    public SiteBookmarkController(AuthenticationService authService, BookmarkService bookmarkService, TouristCard touristCard) {
        this.authService = authService;
        this.bookmarkService = bookmarkService;
        this.touristCard = touristCard;
    }
    
    /**
     * Main method to run the bookmark removal use case.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        // Entry Conditions check.
        if (!authService.isAuthenticated()) {
            System.out.println("Tourist is not authenticated. Operation aborted.");
            return;
        }
        
        Site currentSite = touristCard.getCurrentSite();
        if (currentSite == null) {
            System.out.println("Tourist card is not at a particular site. Operation aborted.");
            return;
        }
        
        System.out.println("Current site: " + currentSite.getName());
        System.out.println("Bookmarked sites: " + bookmarkService.getBookmarkedSites().size());
        
        // Step 1: Tourist chooses to remove a site from bookmarks.
        System.out.println("Do you want to remove the current site from bookmarks? (yes/no)");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (!choice.equals("yes")) {
            System.out.println("Operation cancelled by Tourist.");
            return; // Exit Condition: Tourist cancels the operation.
        }
        
        // Step 2: System prompts for removal confirmation.
        System.out.println("Are you sure you want to remove '" + currentSite.getName() + "' from bookmarks? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        // Step 3: Tourist confirms the removal.
        boolean confirm = confirmation.equals("yes");
        
        // Steps 4 and 5 handled in BookmarkService.
        boolean success = bookmarkService.removeSiteFromBookmarks(currentSite, confirm);
        
        if (success) {
            System.out.println("Site successfully removed from bookmarks.");
            System.out.println("Notifications updated.");
        } else {
            System.out.println("Failed to remove site from bookmarks.");
        }
        
        scanner.close();
    }
}