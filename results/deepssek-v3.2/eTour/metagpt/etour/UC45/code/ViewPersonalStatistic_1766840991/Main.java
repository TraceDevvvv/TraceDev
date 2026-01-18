package com.restaurant.statistics;

import com.restaurant.statistics.ui.StatisticsUI;
import com.restaurant.statistics.service.StatisticsService;
import com.restaurant.statistics.auth.AuthenticationService;

/**
 * Main class for the ViewPersonalStatistic application.
 * This program allows a Point of Restaurant Operator to view personal statistics
 * after successful authentication.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting ViewPersonalStatistic application...");
        
        // Initialize serv
        AuthenticationService authService = new AuthenticationService();
        StatisticsService statsService = new StatisticsService();
        
        // Simulate authentication (in a real system, this would come from login)
        // For demonstration, we'll assume the operator is already authenticated
        // as per entry conditions in the use case
        boolean isAuthenticated = authService.authenticateOperator();
        
        if (!isAuthenticated) {
            System.out.println("Error: Operator authentication failed. Exiting application.");
            return;
        }
        
        System.out.println("Operator successfully authenticated.");
        
        try {
            // Create and display the statistics UI
            StatisticsUI ui = new StatisticsUI(statsService);
            ui.displayStatisticsForm();
            
            System.out.println("Statistics displayed successfully.");
        } catch (Exception e) {
            System.out.println("Error displaying statistics: " + e.getMessage());
            System.out.println("Application terminated due to error.");
        }
    }
}