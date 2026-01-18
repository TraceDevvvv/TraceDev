package com.restaurant.statistics.ui;

import com.restaurant.statistics.service.StatisticsService;
import com.restaurant.statistics.model.RestaurantPointStatistics;

/**
 * UI class for displaying personal statistics form.
 * This class handles the display of statistics data as per the use case requirements.
 * It shows a form with statistics associated with the refreshment point.
 */
public class StatisticsUI {
    
    private final StatisticsService statisticsService;
    
    /**
     * Constructor for StatisticsUI.
     * 
     * @param statisticsService the statistics service to use for data retrieval
     */
    public StatisticsUI(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    
    /**
     * Displays the statistics form as per the use case flow of events.
     * Step 2: "Displays a form that shows data for the statistics associated refreshment point."
     * This method handles the entire display process including error handling.
     */
    public void displayStatisticsForm() {
        System.out.println("\n=== Personal Statistics Form ===");
        System.out.println("Loading statistics data...\n");
        
        try {
            // Check connection status before attempting to fetch data
            if (!isConnectionAvailable()) {
                displayConnectionError();
                return;
            }
            
            // Fetch statistics data from the service
            RestaurantPointStatistics statistics = fetchStatisticsData();
            
            // Display the statistics in a formatted form
            displayStatistics(statistics);
            
            // Display additional insights based on the statistics
            displayInsights(statistics);
            
        } catch (Exception e) {
            displayError("Failed to display statistics: " + e.getMessage());
        }
    }
    
    /**
     * Fetches statistics data from the service.
     * Handles potential connection interruptions as per quality requirements.
     * 
     * @return RestaurantPointStatistics object containing the statistics
     * @throws RuntimeException if data cannot be retrieved
     */
    private RestaurantPointStatistics fetchStatisticsData() {
        try {
            // Attempt to retrieve current point statistics
            return statisticsService.getCurrentPointStatistics();
            
        } catch (RuntimeException e) {
            // Handle connection interruption or other retrieval errors
            System.out.println("Warning: " + e.getMessage());
            System.out.println("Attempting to use cached data or fallback...");
            
            // In a real system, this might load cached data or use fallback values
            // For demonstration, we'll generate minimal fallback statistics
            return generateFallbackStatistics();
        }
    }
    
    /**
     * Displays statistics in a formatted form.
     * This creates the visual representation of the statistics as required by the use case.
     * 
     * @param statistics the statistics to display
     */
    private void displayStatistics(RestaurantPointStatistics statistics) {
        // Display form header
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                PERSONAL STATISTICS FORM                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        // Display basic point information
        System.out.printf("║ Point ID: %-45s ║\n", statistics.getPointId());
        System.out.printf("║ Point Name: %-43s ║\n", statistics.getPointName());
        System.out.printf("║ Operator: %-45s ║\n", statistics.getOperatorName());
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        // Display core statistics
        System.out.printf("║ Total Customers Served: %-33d ║\n", statistics.getTotalCustomersServed());
        System.out.printf("║ Total Revenue: $%-38d ║\n", statistics.getTotalRevenue());
        System.out.printf("║ Average Customer Rating: %-5.1f/5.0%22s ║\n", 
                         statistics.getAverageCustomerRating(), "");
        System.out.printf("║ Avg Revenue per Customer: $%-31.2f ║\n", 
                         statistics.getAverageRevenuePerCustomer());
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        // Display additional metrics
        System.out.printf("║ Most Popular Item: %-40s ║\n", statistics.getMostPopularItem());
        System.out.printf("║ Peak Hours: %-46s ║\n", statistics.getPeakHours());
        System.out.printf("║ Customer Satisfaction: %-37s ║\n", statistics.getSatisfactionLevel());
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        // Display metadata
        System.out.printf("║ Last Updated: %-44s ║\n", statistics.getLastUpdated().toString());
        System.out.printf("║ Data Status: %-45s ║\n", 
                         statistics.isValid() ? "Valid" : "Partial Data");
        
        // Display connection status
        System.out.printf("║ Connection Status: %-41s ║\n", 
                         isConnectionAvailable() ? "Connected" : "Disconnected");
        
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Displays additional insights based on the statistics.
     * Provides actionable information for the operator.
     * 
     * @param statistics the statistics to analyze
     */
    private void displayInsights(RestaurantPointStatistics statistics) {
        System.out.println("\n=== Insights & Recommendations ===");
        
        // Revenue insights
        double revenuePerCustomer = statistics.calculateRevenuePerCustomer();
        if (revenuePerCustomer < 5.0) {
            System.out.println("• Consider promoting higher-value items to increase average transaction.");
        } else if (revenuePerCustomer > 15.0) {
            System.out.println("• Excellent average revenue per customer! Keep up the good work.");
        }
        
        // Customer rating insights
        double rating = statistics.getAverageCustomerRating();
        if (rating < 3.0) {
            System.out.println("• Customer satisfaction is low. Consider gathering feedback.");
        } else if (rating >= 4.5) {
            System.out.println("• Outstanding customer ratings! Customers are very satisfied.");
        }
        
        // Popular item insights
        String popularItem = statistics.getMostPopularItem();
        if (popularItem != null && !popularItem.isEmpty()) {
            System.out.println("• " + popularItem + " is your best seller. Ensure adequate stock.");
        }
        
        // Peak hours insights
        if (statistics.getPeakHours() != null && statistics.getPeakHours().contains(":")) {
            System.out.println("• Plan staffing according to peak hours: " + statistics.getPeakHours());
        }
        
        System.out.println("\n=== End of Statistics Report ===");
    }
    
    /**
     * Checks if the connection to the server is available.
     * This handles the "Interruption of the connection to the server" quality requirement.
     * 
     * @return true if connection is available, false otherwise
     */
    private boolean isConnectionAvailable() {
        try {
            return statisticsService.checkConnectionStatus();
        } catch (Exception e) {
            System.out.println("Connection check failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Displays a connection error message when server is unreachable.
     */
    private void displayConnectionError() {
        System.out.println("\n⚠️  CONNECTION ERROR ⚠️");
        System.out.println("═════════════════════════");
        System.out.println("Unable to connect to the statistics server.");
        System.out.println("Possible causes:");
        System.out.println("• Network connection interrupted");
        System.out.println("• Server is temporarily unavailable");
        System.out.println("• Maintenance in progress");
        System.out.println("\nActions:");
        System.out.println("1. Check your internet connection");
        System.out.println("2. Try again in a few minutes");
        System.out.println("3. Contact technical support if issue persists");
        System.out.println("\nDisplaying last available data...\n");
        
        // Display fallback data when connection fails
        RestaurantPointStatistics fallbackStats = generateFallbackStatistics();
        displayStatistics(fallbackStats);
        
        System.out.println("\n⚠️  NOTE: Data may be outdated due to connection issues.");
    }
    
    /**
     * Generates fallback statistics when server connection is unavailable.
     * This provides basic information even during network interruptions.
     * 
     * @return minimal RestaurantPointStatistics object
     */
    private RestaurantPointStatistics generateFallbackStatistics() {
        RestaurantPointStatistics fallback = new RestaurantPointStatistics();
        fallback.setPointId("REST-POINT-OFFLINE");
        fallback.setPointName("Main Refreshment Point (Offline Mode)");
        fallback.setOperatorName("Operator (Data Cached)");
        fallback.setTotalCustomersServed(0);
        fallback.setTotalRevenue(0);
        fallback.setAverageCustomerRating(0.0);
        fallback.setMostPopularItem("N/A - Offline Mode");
        fallback.setPeakHours("N/A - Offline Mode");
        fallback.setAverageRevenuePerCustomer(0.0);
        fallback.setSatisfactionLevel("Unknown - Offline Mode");
        fallback.updateTimestamp();
        
        return fallback;
    }
    
    /**
     * Displays an error message with the specified details.
     * 
     * @param message the error message to display
     */
    private void displayError(String message) {
        System.out.println("\n❌ ERROR ❌");
        System.out.println("═════════════");
        System.out.println(message);
        System.out.println("\nPlease try again or contact support.");
    }
    
    /**
     * Refreshes the statistics display.
     * This method can be called to update the displayed statistics.
     */
    public void refreshStatistics() {
        System.out.println("\nRefreshing statistics...");
        displayStatisticsForm();
    }
    
    /**
     * Displays a simplified version of statistics for quick viewing.
     * This provides key metrics at a glance.
     */
    public void displayQuickStats() {
        try {
            RestaurantPointStatistics stats = statisticsService.getCurrentPointStatistics();
            
            System.out.println("\n=== Quick Statistics ===");
            System.out.printf("Customers: %d | Revenue: $%d | Rating: %.1f/5.0\n",
                             stats.getTotalCustomersServed(),
                             stats.getTotalRevenue(),
                             stats.getAverageCustomerRating());
            System.out.printf("Popular: %s | Satisfaction: %s\n",
                             stats.getMostPopularItem(),
                             stats.getSatisfactionLevel());
            
        } catch (Exception e) {
            System.out.println("Quick stats unavailable: " + e.getMessage());
        }
    }
}