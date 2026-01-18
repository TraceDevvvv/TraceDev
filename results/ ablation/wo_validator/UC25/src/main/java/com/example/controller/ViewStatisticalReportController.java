package com.example.controller;

import com.example.model.StatisticalReportDTO;
import com.example.model.SiteFeedbackDTO;
import com.example.model.LocationDTO;
import com.example.repository.LocationRepository;
import com.example.request.ViewStatisticalReportRequest;
import com.example.response.ViewStatisticalReportResponse;
import com.example.usecase.SearchSiteUseCase;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for viewing statistical reports
 * Implements the sequence diagram interactions
 */
public class ViewStatisticalReportController {
    private SearchSiteUseCase searchSiteUseCase;
    private LocationRepository locationRepository;
    
    public ViewStatisticalReportController(SearchSiteUseCase searchSiteUseCase, 
                                         LocationRepository locationRepository) {
        this.searchSiteUseCase = searchSiteUseCase;
        this.locationRepository = locationRepository;
    }
    
    public ViewStatisticalReportResponse execute(ViewStatisticalReportRequest request) {
        ViewStatisticalReportResponse response = new ViewStatisticalReportResponse();
        
        try {
            // Step 2-3: Fetch location list (from sequence diagram)
            List<LocationDTO> locations = locationRepository.findAllLocations();
            
            // Step 4-5: Validate request (Operator selects and submits)
            validateRequest(request);
            
            // Execute search use case
            List<SiteFeedbackDTO> siteFeedback = searchSiteUseCase.execute(request.getLocationId());
            
            // Prepare statistical report
            StatisticalReportDTO report = prepareReport(siteFeedback);
            
            // Set successful response
            response.setReport(report);
            response.setSuccess(true);
            response.setMessage("Statistical report generated successfully");
            
        } catch (Exception e) {
            // Handle connection errors and other exceptions
            response.setSuccess(false);
            response.setMessage("Error generating report: " + e.getMessage());
            
            // Exit Condition 2: Connection interrupted
            if (e instanceof RuntimeException) {
                throw new RuntimeException("ConnectionErrorException: Connection to server is interrupted", e);
            }
        }
        
        return response;
    }
    
    private void validateRequest(ViewStatisticalReportRequest request) {
        if (request == null || request.getLocationId() == null || request.getLocationId().trim().isEmpty()) {
            throw new IllegalArgumentException("Location ID is required");
        }
    }
    
    StatisticalReportDTO prepareReport(List<SiteFeedbackDTO> siteFeedback) {
        StatisticalReportDTO report = new StatisticalReportDTO();
        
        if (siteFeedback == null || siteFeedback.isEmpty()) {
            report.setLocationName("Unknown Location");
            report.setTotalFeedback(0);
            report.setAverageRating(0.0);
            report.setFeedbackByCategory(new HashMap<>());
            return report;
        }
        
        // Calculate statistics
        int totalFeedback = siteFeedback.size();
        double totalRating = 0.0;
        Map<String, Integer> feedbackByCategory = new HashMap<>();
        
        for (SiteFeedbackDTO feedback : siteFeedback) {
            totalRating += feedback.getRating();
            
            String category = feedback.getCategory();
            feedbackByCategory.put(category, 
                feedbackByCategory.getOrDefault(category, 0) + 1);
        }
        
        // Set report values
        report.setLocationName("Selected Location"); // In real implementation, get from LocationDTO
        report.setTotalFeedback(totalFeedback);
        report.setAverageRating(totalFeedback > 0 ? totalRating / totalFeedback : 0.0);
        report.setFeedbackByCategory(feedbackByCategory);
        
        return report;
    }
}