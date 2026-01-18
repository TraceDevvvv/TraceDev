package com.example.search;

import com.example.search.application.SearchService;
import com.example.search.application.SearchUseCaseController;
import com.example.search.infrastructure.ETOURClient;
import com.example.search.infrastructure.ETOURCulturalObjectRepository;
import com.example.search.presentation.PresentationController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class to demonstrate the application flow as described in the sequence diagram.
 * It sets up the dependencies and simulates user interaction.
 */
public class Main {
    public static void main(String[] args) {
        // --- Dependency Injection / Wiring up the layers ---\n        System.out.println("Initializing application components...");

        // Infrastructure Layer
        ETOURClient etourClient = new ETOURClient();
        ETOURCulturalObjectRepository etourRepo = new ETOURCulturalObjectRepository(etourClient);

        // Application Layer
        SearchService searchService = new SearchService(etourRepo);
        SearchUseCaseController searchUseCaseController = new SearchUseCaseController(searchService);

        // Presentation Layer
        PresentationController presentationController = new PresentationController(searchUseCaseController);

        System.out.println("Application initialized. Ready for user interaction.");

        // --- Simulate User Interaction (Success Scenario) ---\n        System.out.println("\n--- Scenario 1: Successful Search ---");
        presentationController.activateSearch(); // User activates search (R3, R4)

        // Simulate user submitting form data
        Map<String, String> formDataSuccess = new HashMap<>();
        formDataSuccess.put("keyword", "painting");
        formDataSuccess.put("typeFilter", "");
        formDataSuccess.put("yearRangeStart", "1800");
        formDataSuccess.put("yearRangeEnd", "1900");
        presentationController.submitSearchForm(formDataSuccess); // User submits form

        // Simulate another successful search
        System.out.println("\n--- Scenario 2: Another Successful Search (Sculptures before 1000 AD) ---");
        Map<String, String> formDataSuccess2 = new HashMap<>();
        formDataSuccess2.put("keyword", "ancient");
        formDataSuccess2.put("typeFilter", "sculpture");
        formDataSuccess2.put("yearRangeStart", "");
        formDataSuccess2.put("yearRangeEnd", "1000"); // Before 1000 AD
        presentationController.submitSearchForm(formDataSuccess2);

        // --- Simulate User Interaction (Failure Scenario: ETOUR Connection Error) ---\n        System.out.println("\n--- Scenario 3: ETOUR Connection Failure ---");
        // Deactivate ETOUR client connection to simulate failure
        etourClient.setConnectionActive(false);

        presentationController.activateSearch(); // User activates search again

        Map<String, String> formDataFailure = new HashMap<>();
        formDataFailure.put("keyword", "history");
        formDataFailure.put("typeFilter", "");
        formDataFailure.put("yearRangeStart", "");
        formDataFailure.put("yearRangeEnd", "");
        presentationController.submitSearchForm(formDataFailure); // User submits form, expects error

        // Re-activate connection for any potential further interactions (not explicitly in SD, but good practice)
        etourClient.setConnectionActive(true);

        System.out.println("\n--- End of demonstration ---");
    }
}