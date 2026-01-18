package com.example;

import com.example.model.Tourist;
import com.example.controller.ViewVisitedSitesController;
import com.example.interactor.ViewVisitedSitesInteractor;
import com.example.repository.SiteRepositoryImpl;
import com.example.service.AuthenticationService;
import com.example.service.ETOURService;
import com.example.dto.VisitedSitesResponseDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Sample application to demonstrate the flow.
 */
public class Application {
    public static void main(String[] args) {
        // Setup serv with dummy implementations
        AuthenticationService authService = new AuthenticationService() {
            @Override
            public boolean isAuthenticated(String userId) {
                // Simulate authentication check
                return "tourist123".equals(userId);
            }
        };

        ETOURService eTourService = new ETOURService() {
            @Override
            public List<Map<String, Object>> findSitesWithFeedback(String touristId) {
                // Simulate external service call with reliability requirement: response time < 2s
                // For demo, return dummy data
                List<Map<String, Object>> result = new ArrayList<>();
                Map<String, Object> site1 = new HashMap<>();
                site1.put("siteId", "S001");
                site1.put("name", "Eiffel Tower");
                site1.put("location", "Paris");
                List<Map<String, Object>> feedbacks1 = new ArrayList<>();
                Map<String, Object> f1 = new HashMap<>();
                f1.put("feedbackId", "F001");
                f1.put("rating", 5);
                f1.put("comment", "Amazing!");
                f1.put("date", new Date());
                feedbacks1.add(f1);
                site1.put("feedbackList", feedbacks1);
                result.add(site1);
                // Simulate connection interruption scenario if needed
                if ("fail".equals(touristId)) {
                    throw new com.example.exception.ConnectionLostException("Connection to server ETOUR interrupted");
                }
                return result;
            }
        };

        // Setup repository and interactor
        SiteRepositoryImpl siteRepository = new SiteRepositoryImpl(eTourService);
        ViewVisitedSitesInteractor interactor = new ViewVisitedSitesInteractor(siteRepository);
        ViewVisitedSitesController controller = new ViewVisitedSitesController(interactor, authService);

        // Simulate Tourist
        Tourist tourist = new Tourist("tourist123", "John Doe");
        String authToken = "someToken";

        try {
            VisitedSitesResponseDTO response = controller.handleRequest(tourist.getUserId(), authToken);
            System.out.println("Success! Number of sites: " + response.getSites().size());
            // R4-Exit Condition: System displays list of visited sites with feedback history
        } catch (SecurityException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        } catch (com.example.exception.ConnectionLostException e) {
            // R5-Exit Conditions: Connection to the server ETOUR is interrupted
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}