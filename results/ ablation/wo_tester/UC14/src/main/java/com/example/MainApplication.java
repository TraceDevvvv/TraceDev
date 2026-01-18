package com.example;

import com.example.application.SearchCriteria;
import com.example.application.SearchTouristsController;
import com.example.domain.Tourist;
import com.example.domain.agency.AgencyOperator;
import com.example.infrastructure.TouristRepositoryImpl;
import com.example.presentation.TouristSearchForm;
import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting Tourist Search Simulation ===");

        // 1. Create Agency Operator and authenticate (Entry Condition)
        AgencyOperator operator = new AgencyOperator("OP001", "Alice");
        operator.getAuthenticationService().login("OP001", "password");
        boolean loggedIn = operator.verifyLoginStatus();
        System.out.println("Operator logged in: " + loggedIn);
        if (!loggedIn) {
            System.out.println("Entry condition not met. Exiting.");
            return;
        }

        // 2. Create form and display
        TouristSearchForm form = new TouristSearchForm();
        operator.verifyLoginStatus(); // dummy call as per sequence diagram
        form.display();

        // 3. Fill out form (simulate setting criteria)
        SearchCriteria criteria = new SearchCriteria();
        criteria.setNameFilter("John");
        criteria.setEmailFilter("");
        criteria.setOtherFilter("");
        form.setCriteria(criteria);
        System.out.println("Form filled with criteria: " + criteria);

        // 4. Submit form -> get input
        SearchCriteria submittedCriteria = form.getFormInput();

        // 5. Create controller and repository, then search
        TouristRepositoryImpl repositoryImpl = new TouristRepositoryImpl();
        SearchTouristsController controller = new SearchTouristsController(repositoryImpl);
        List<Tourist> results = controller.searchTourists(submittedCriteria);

        // 6. Show results
        form.showResults(results);
        System.out.println("=== Main Success Flow Complete ===");

        // Simulate alternative flow: connection failure
        System.out.println("\n=== Simulating Alternative Flow (Connection Failure) ===");
        com.example.infrastructure.DatabaseConnection brokenConn = new com.example.infrastructure.DatabaseConnection("");
        brokenConn.disconnect();
        TouristRepositoryImpl brokenRepo = new TouristRepositoryImpl(brokenConn);
        SearchTouristsController altController = new SearchTouristsController(brokenRepo);
        List<Tourist> emptyResults = altController.searchTourists(submittedCriteria);
        form.showResults(emptyResults);
    }
}