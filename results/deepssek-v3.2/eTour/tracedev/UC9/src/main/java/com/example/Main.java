package com.example;

import com.example.controller.RefreshmentPointSearchController;
import com.example.dto.SearchCriteriaDTO;
import com.example.repository.IRefreshmentPointRepository;
import com.example.repository.RefreshmentPointRepositoryImpl;
import com.example.service.RefreshmentPointSearchService;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        IRefreshmentPointRepository repository = new RefreshmentPointRepositoryImpl();
        RefreshmentPointSearchService service = new RefreshmentPointSearchService(repository);
        RefreshmentPointSearchController controller = new RefreshmentPointSearchController(service);

        // Simulate User actor activating search functionality
        controller.activateSearchFunctionality();
        controller.showSearchForm();

        // Simulate User submitting a valid search form
        SearchCriteriaDTO criteriaDTO = new SearchCriteriaDTO();
        criteriaDTO.setLocationFilter("Downtown");
        criteriaDTO.setTypeFilter("CAFE");
        criteriaDTO.setMaxDistance(10);

        System.out.println("\n--- Submitting valid search criteria ---");
        var results = controller.handleSearchFormSubmission(criteriaDTO);
        System.out.println("Search returned " + results.size() + " results.");

        // Simulate User submitting invalid criteria
        SearchCriteriaDTO invalidDTO = new SearchCriteriaDTO();
        invalidDTO.setLocationFilter("");
        invalidDTO.setMaxDistance(-5);

        System.out.println("\n--- Submitting invalid search criteria ---");
        var emptyResults = controller.handleSearchFormSubmission(invalidDTO);
        System.out.println("Invalid criteria returned " + emptyResults.size() + " results.");

        // Note: The alternative flow with connection error can be simulated by
        // causing an exception in the ETourServer.
        System.out.println("\n--- End of demonstration ---");
    }
}