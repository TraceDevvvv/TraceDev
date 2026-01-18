package com.example.touristapp;

import com.example.touristapp.application.SiteService;
import com.example.touristapp.dataaccess.FeedbackRepository;
import com.example.touristapp.dataaccess.SiteRepository;
import com.example.touristapp.presentation.SiteController;
import com.example.touristapp.presentation.TouristAppUI;

/**
 * Main class to demonstrate the Tourist App functionality, simulating the interaction
 * of the Tourist actor with the system.
 * This class sets up the dependency injection and orchestrates the use case scenario
 * described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Tourist Application Simulation ---");

        // 1. Initialize Data Access Layer (Repositories)
        SiteRepository siteRepository = new SiteRepository();
        FeedbackRepository feedbackRepository = new FeedbackRepository();

        // 2. Initialize Application Layer (Service)
        SiteService siteService = new SiteService(siteRepository, feedbackRepository);

        // 3. Initialize Presentation Layer (UI and Controller)
        TouristAppUI touristAppUI = new TouristAppUI();
        SiteController siteController = new SiteController(siteService, touristAppUI);

        String touristId1 = "T001"; // Tourist with visited sites and feedback
        String touristId2 = "T002"; // Tourist with visited sites, some without feedback
        String touristId3 = "T003"; // Tourist with fewer visited sites
        String touristId4 = "T004"; // Tourist who hasn't visited any sites

        // --- Scenario 1: Successful retrieval for T001 ---
        System.out.println("\n=== Scenario 1: Successful retrieval for Tourist " + touristId1 + " ===");
        siteRepository.setSimulateNetworkError(false); // Ensure no network errors
        feedbackRepository.setSimulateNetworkError(false);
        touristAppUI.selectViewVisitedSites(touristId1, siteController);

        // --- Scenario 2: Successful retrieval for T002 (some feedback missing) ---
        System.out.println("\n=== Scenario 2: Successful retrieval for Tourist " + touristId2 + " (some feedback missing) ===");
        siteRepository.setSimulateNetworkError(false);
        feedbackRepository.setSimulateNetworkError(false);
        touristAppUI.selectViewVisitedSites(touristId2, siteController);

        // --- Scenario 3: Tourist who hasn't visited any sites (T004) ---
        System.out.println("\n=== Scenario 3: No visited sites for Tourist " + touristId4 + " ===");
        siteRepository.setSimulateNetworkError(false);
        feedbackRepository.setSimulateNetworkError(false);
        touristAppUI.selectViewVisitedSites(touristId4, siteController);

        // --- Scenario 4: Simulate Network Interruption during SiteRepository call ---
        // This simulates a network error during "SiteRepo -> Database: query"
        System.out.println("\n=== Scenario 4: Network Error during Site Lookup (Tourist " + touristId1 + ") ===");
        siteRepository.setSimulateNetworkError(true); // Enable network errors for SiteRepository
        feedbackRepository.setSimulateNetworkError(false); // Keep feedback repo stable
        touristAppUI.selectViewVisitedSites(touristId1, siteController);
        siteRepository.setSimulateNetworkError(false); // Disable for next scenarios

        // --- Scenario 5: Simulate Network Interruption during FeedbackRepository call ---
        // This simulates a network error during "FeedbackRepo -> Database: query" within the loop
        System.out.println("\n=== Scenario 5: Network Error during Feedback Lookup (Tourist " + touristId3 + ") ===");
        siteRepository.setSimulateNetworkError(false); // Keep site repo stable
        feedbackRepository.setSimulateNetworkError(true); // Enable network errors for FeedbackRepository
        touristAppUI.selectViewVisitedSites(touristId3, siteController);
        feedbackRepository.setSimulateNetworkError(false); // Disable for next scenarios

        System.out.println("\n--- Tourist Application Simulation Finished ---");
    }
}