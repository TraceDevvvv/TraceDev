
package com.example;

import com.example.datasource.ServerDataSource;
import com.example.repository.StatisticsRepository;
import com.example.repository.StatisticsRepositoryImpl;
import com.example.service.StatisticsService;
import com.example.view.StatisticsView;
import com.example.viewmodel.StatisticsViewModel;

/**
 * Main class to simulate the sequence diagram flow.
 * Acts as the entry point and simulates operator interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Simulating Use Case: View Personal Statistics ===");
        
        // Setup dependencies (IoC simulation)
        ServerDataSource dataSource = new ServerDataSource();
        StatisticsRepository repository = new StatisticsRepositoryImpl(dataSource);
        StatisticsService service = new StatisticsService(repository);
        String operatorId = "OP-001"; // Authenticated operator per REQ-004
        StatisticsViewModel viewModel = new StatisticsViewModel(operatorId, service);
        StatisticsView view = new StatisticsView(viewModel);

        // Simulate actor (Operator) navigating to view
        System.out.println("\n1. Operator navigates to statistics view:");
        view.navigateToStatisticsView();

        // Simulate operator selecting personal statistics feature
        System.out.println("\n2. Operator selects personal statistics feature:");
        viewModel.loadPersonalStatistics();

        // Wait for async operation (simulate)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Simulate connection failure scenario
        System.out.println("\n--- Simulating Connection Failure ---");
        dataSource.setConnectionHealthy(false);
        viewModel.loadPersonalStatistics();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== Simulation Complete ===");
    }
}
