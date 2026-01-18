package com.example.controller;

import com.example.dto.PersonalStatisticsDTO;
import com.example.exception.ServiceException;
import com.example.service.AuthenticationService;
import com.example.service.StatisticsService;

/**
 * Controller class handling the view personal statistics use case.
 * Coordinates between view, authentication, and statistics service.
 */
public class ViewPersonalStatisticsController {

    private AuthenticationService authService;
    private StatisticsService statisticsService;

    /**
     * Constructor with dependency injection.
     */
    public ViewPersonalStatisticsController(AuthenticationService authService, StatisticsService statisticsService) {
        this.authService = authService;
        this.statisticsService = statisticsService;
    }

    /**
     * Handles the request to view statistics for an operator.
     * Sequence diagram step: handleViewStatisticsRequest(operatorId)
     * Precondition: operator must be authenticated (as per class diagram note).
     */
    public PersonalStatisticsDTO handleViewStatisticsRequest(String operatorId) {
        try {
            // Authentication check (sequence diagram alt branch)
            // Precondition: authService.isAuthenticated(operatorId)
            if (!authService.isAuthenticated(operatorId)) {
                // Not authenticated, return null and let view show error
                // In the sequence diagram, view calls showErrorMessage directly after receiving null
                return null;
            }

            // Main success flow: get statistics
            return statisticsService.getPersonalStatistics(operatorId);
        } catch (ServiceException e) {
            // Handle service-level errors
            handleError(e);
            return null;
        }
    }

    /**
     * Centralized error handling.
     * Sequence diagram step: handleError(error)
     */
    public void handleError(ServiceException error) {
        // Log the error, send to monitoring, etc.
        System.err.println("Controller handled service exception: " + error.getMessage());
        if (error.getCause() != null) {
            error.getCause().printStackTrace();
        }
    }
}