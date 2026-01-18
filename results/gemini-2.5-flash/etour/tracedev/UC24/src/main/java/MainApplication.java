package com.example;

import com.example.controller.FeedbackViewController;
import com.example.repository.FeedbackRepository;
import com.example.repository.SiteRepository;
import com.example.service.AuthenticationService;
import com.example.service.CacheService;
import com.example.service.FeedbackService;
import com.example.view.FeedbackView;
import com.example.view.SiteListView;

import java.util.Scanner;

/**
 * Main application class to demonstrate the interaction flow described in the UML diagrams.
 * This class simulates the 'Agency Operator' (AO) and orchestrates the system.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting Feedback Application Simulation ---");

        // 1. Initialize core components
        // Dummy dataSource for repositories
        Object dummyDataSource = new Object();
        
        SiteRepository siteRepository = new SiteRepository(dummyDataSource);
        FeedbackRepository feedbackRepository = new FeedbackRepository(dummyDataSource);
        
        CacheService cacheService = new CacheService();
        AuthenticationService authenticationService = new AuthenticationService();
        
        FeedbackService feedbackService = new FeedbackService(siteRepository, feedbackRepository, cacheService);
        
        SiteListView siteListView = new SiteListView();
        FeedbackView feedbackView = new FeedbackView();
        
        // The controller manages interactions between service and views
        FeedbackViewController feedbackViewController = new FeedbackViewController(
            feedbackService, siteListView, feedbackView, authenticationService
        );

        Scanner scanner = new Scanner(System.in);

        // --- Simulate Sequence Diagram: View Feedback for a Site ---

        System.out.println("\n*** Scenario 1: Unauthenticated User ***");
        // AO -> FVC : requestSiteList()
        feedbackViewController.requestSiteList(); // Should result in an error message

        System.out.println("\n*** Scenario 2: Authenticate User ***");
        // Simulate authentication
        System.out.println("Attempting to authenticate 'agency' with 'password'...");
        if (!authenticationService.authenticate("agency", "password")) {
            System.err.println("Authentication failed. Exiting simulation.");
            return;
        }

        System.out.println("\n*** Scenario 3: Authenticated User - Request Site List (Cache Miss, then Cache Hit) ***");
        // AO -> FVC : requestSiteList()
        // FVC -> AuthService : isAuthenticated() (returns true)
        // FVC -> FS : getAllSites() (first call will be cache miss)
        feedbackViewController.requestSiteList(); // This will populate the cache

        System.out.println("Simulating another request for site list (should be served from cache)...");
        feedbackViewController.requestSiteList(); // This should be a cache hit


        System.out.println("\n*** Scenario 4: Authenticated User - Select Site and View Feedback (Cache Miss, then Cache Hit) ***");
        String selectedSiteId = "S001"; // Let's pick 'Eiffel Tower'

        // AO -> SLV : selectSite(siteId)
        siteListView.simulateSiteSelection(selectedSiteId); // This calls FVC.selectSite internally

        // AO -> FVC : viewFeedbackForSelectedSite(siteId)
        feedbackViewController.viewFeedbackForSelectedSite(selectedSiteId); // This will populate feedback cache

        System.out.println("Simulating another request for feedback for site " + selectedSiteId + " (should be served from cache)...");
        feedbackViewController.viewFeedbackForSelectedSite(selectedSiteId); // This should be a cache hit

        String selectedSiteId2 = "S002"; // Let's pick 'Statue of Liberty'
        System.out.println("\n*** Scenario 5: View Feedback for a different site (S002) ***");
        siteListView.simulateSiteSelection(selectedSiteId2);
        feedbackViewController.viewFeedbackForSelectedSite(selectedSiteId2);


        System.out.println("\n*** Scenario 6: Simulate Network Connection Error for Sites ***");
        System.out.println("Setting SiteRepository to simulate network error...");
        siteRepository.setSimulateNetworkError(true);
        cacheService.clearCache(); // Clear cache to force repository access

        // AO -> FVC : requestSiteList()
        System.out.println("Requesting site list with simulated network error...");
        feedbackViewController.requestSiteList();


        System.out.println("\n*** Scenario 7: Simulate Network Connection Error for Feedback ***");
        System.out.println("Setting FeedbackRepository to simulate network error...");
        feedbackRepository.setSimulateNetworkError(true);
        siteRepository.setSimulateNetworkError(false); // Reset site repo error
        cacheService.clearCache(); // Clear cache to force repository access

        // First, get sites successfully so we can select one
        feedbackViewController.requestSiteList();

        // AO -> FVC : viewFeedbackForSelectedSite(siteId)
        System.out.println("Requesting feedback for site " + selectedSiteId + " with simulated network error...");
        siteListView.simulateSiteSelection(selectedSiteId); // select the site again
        feedbackViewController.viewFeedbackForSelectedSite(selectedSiteId);


        System.out.println("\n--- Simulation Complete ---");
        scanner.close();
    }
}