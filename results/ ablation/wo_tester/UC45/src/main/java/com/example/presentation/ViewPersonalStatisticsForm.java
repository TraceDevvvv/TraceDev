package com.example.presentation;

import com.example.application.ViewPersonalStatisticsUseCaseController;
import com.example.application.ViewPersonalStatisticsPresenter;
import com.example.domain.ConnectionException;
import com.example.infrastructure.Network;

/**
 * Presentation layer form for displaying personal statistics.
 * Handles user interactions and connection error scenarios.
 */
public class ViewPersonalStatisticsForm {
    private ViewPersonalStatisticsPresenter presenter;
    private Network network;

    public ViewPersonalStatisticsForm() {
        // In a real scenario, dependencies would be injected (e.g., via constructor or DI framework)
        this.presenter = new ViewPersonalStatisticsPresenter();
        this.network = new Network();
        // Simulate network connection monitoring
        network.setConnectionListener(this::handleConnectionError);
    }

    // Called when operator selects the feature to view personal statistics
    public void onViewStatisticsRequested() {
        // Sequence diagram message m1: "1. Selects feature to display personal statistics"
        // This method corresponds to operator's action
        String operatorId = "operator123";
        ViewPersonalStatisticsUseCaseController controller = new ViewPersonalStatisticsUseCaseController(presenter);
        try {
            StatisticsViewModel viewModel = controller.execute(operatorId);
            displayStatistics(viewModel);
        } catch (ConnectionException e) {
            handleConnectionError(e.getMessage());
        } catch (Exception e) {
            handleConnectionError("Unexpected error: " + e.getMessage());
        }
    }

    // Sequence diagram message m3: "2. execute(operatorId)" - This is sent to UseCaseController
    // The execute method is called in onViewStatisticsRequested above

    public void displayStatistics(StatisticsViewModel viewModel) {
        // Sequence diagram message m15: "5. displayStatistics(viewModel)"
        // In a real application, this would update UI components
        System.out.println("=== Personal Statistics ===");
        System.out.println("Period: " + viewModel.periodDisplay);
        System.out.println("Total Sales: " + viewModel.formattedTotalSales);
        System.out.println("Avg Order Value: " + viewModel.formattedAverageOrderValue);
        System.out.println("Popular Items: " + viewModel.popularItemsList);
        System.out.println("=========================");
        
        // Sequence diagram message m16: "6. Display statistics on screen"
        // This method call represents the display to the operator
        // The note m17: "System displays the data on the screen." is implemented by this method
    }

    // Added to satisfy requirement: Exit Condition "The connection to the server is interrupted."
    public void handleConnectionError(String errorMessage) {
        System.err.println("Connection Error: " + errorMessage);
        // In a real application, show an error dialog or message to the operator
        // This handles sequence diagram message m19: "Connection lost" and note m20
    }
}