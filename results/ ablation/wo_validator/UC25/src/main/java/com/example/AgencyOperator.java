package com.example;

import com.example.controller.ViewStatisticalReportController;
import com.example.repository.LocationRepository;
import com.example.repository.LocationRepositoryImpl;
import com.example.repository.SiteFeedbackRepository;
import com.example.repository.SiteFeedbackRepositoryImpl;
import com.example.request.ViewStatisticalReportRequest;
import com.example.response.ViewStatisticalReportResponse;
import com.example.usecase.SearchSiteUseCase;

/**
 * Main class representing the Agency Operator actor
 * Demonstrates the complete flow from the sequence diagram
 */
public class AgencyOperator {
    
    public static void main(String[] args) {
        // Initialize dependencies
        LocationRepository locationRepository = new LocationRepositoryImpl();
        SiteFeedbackRepository siteFeedbackRepository = new SiteFeedbackRepositoryImpl();
        SearchSiteUseCase searchSiteUseCase = new SearchSiteUseCase(siteFeedbackRepository);
        ViewStatisticalReportController controller = new ViewStatisticalReportController(
            searchSiteUseCase, locationRepository);
        
        // Create request
        ViewStatisticalReportRequest request = new ViewStatisticalReportRequest();
        request.setLocationId("LOC001"); // Simulating operator selection
        
        // Execute the use case
        ViewStatisticalReportResponse response = controller.execute(request);
        
        // Display results
        if (response.isSuccess()) {
            System.out.println("=== Statistical Report ===");
            System.out.println("Location: " + response.getReport().getLocationName());
            System.out.println("Total Feedback: " + response.getReport().getTotalFeedback());
            System.out.println("Average Rating: " + response.getReport().getAverageRating());
            System.out.println("Feedback by Category: " + response.getReport().getFeedbackByCategory());
        } else {
            System.out.println("Error: " + response.getMessage());
        }
    }
}