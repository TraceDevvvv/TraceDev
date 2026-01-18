package com.example;

import com.example.presentation.ViewPersonalStatisticsForm;

/**
 * Main class to simulate the runnable system.
 * This demonstrates the full flow from operator action to statistics display.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Personal Statistics System...");

        // Simulate an operator opening the statistics form
        ViewPersonalStatisticsForm form = new ViewPersonalStatisticsForm();

        // Operator selects feature to view personal statistics (sequence diagram step 1)
        System.out.println("\nOperator requests personal statistics...");
        form.onViewStatisticsRequested();

        // Optionally simulate a connection interruption
        System.out.println("\n--- Simulating connection interruption ---");
        // In a real scenario, this would be triggered by a network event
        form.handleConnectionError("The connection to the server is interrupted.");

        System.out.println("\nSystem simulation complete.");
    }
}