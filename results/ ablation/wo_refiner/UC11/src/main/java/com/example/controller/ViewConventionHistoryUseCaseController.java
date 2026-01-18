
package com.example.controller;

import com.example.dto.ViewConventionHistoryRequest;
import com.example.dto.ViewConventionHistoryResponse;
import com.example.model.Convention;
import com.example.repository.ConventionHistoryRepository;
import com.example.repository.ServerConnectionException;
import java.util.List;

/**
 * Controller for the View Convention History use case.
 * Orchestrates the flow between request, response, and repository.
 */
public class ViewConventionHistoryUseCaseController {
    private ConventionHistoryRepository repository;

    public ViewConventionHistoryUseCaseController(ConventionHistoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case.
     * @param request the request containing point of rest ID and agency ID.
     * @return response with conventions or error.
     */
    public ViewConventionHistoryResponse execute(ViewConventionHistoryRequest request) {
        // Step 4: Validate request (simplified validation).
        if (request.getPointOfRestId() == null || request.getPointOfRestId().isEmpty()) {
            ViewConventionHistoryResponse response = new ViewConventionHistoryResponse(false, null);
            response.setErrorMessage("Invalid request: point of rest ID is missing");
            return response;
        }
        
        try {
            // Step 5: Retrieve conventions from repository.
            List<Convention> conventions = repository.findByPointOfRestId(request.getPointOfRestId());
            
            // Steps from sequence diagram: access convention data and timestamp.
            if (!conventions.isEmpty()) {
                Convention firstConvention = conventions.get(0);
                String data = firstConvention.getConventionData();
                java.time.LocalDateTime timestamp = firstConvention.getTimestamp();
                // In a real scenario, we might log or process this data.
            }
            
            // Create success response.
            ViewConventionHistoryResponse response = new ViewConventionHistoryResponse(true, conventions);
            return response;
        } catch (ServerConnectionException e) {
            // Handle server connection error.
            ViewConventionHistoryResponse response = new ViewConventionHistoryResponse(false, null);
            response.setErrorMessage("Server connection failed");
            return response;
        }
    }
}
