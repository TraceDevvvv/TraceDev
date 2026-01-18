package com.etour;

import com.etour.connection.ETOURServerConnection;
import com.etour.repository.TouristRepositoryImpl;
import com.etour.service.TouristService;
import com.etour.controller.AgencyOperatorController;
import com.etour.ui.TouristSearchUI;

/**
 * Main class to demonstrate the system.
 * This simulates the use case "View Tourist Account Details".
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        ETOURServerConnection connection = new ETOURServerConnection();
        connection.connect();

        TouristRepositoryImpl repository = new TouristRepositoryImpl(connection);
        TouristService service = new TouristService(repository);
        AgencyOperatorController controller = new AgencyOperatorController(service);
        TouristSearchUI ui = new TouristSearchUI(controller);

        // Simulate the use case
        System.out.println("=== Starting Use Case: View Tourist Account Details ===");
        ui.activateSearchTourist();

        // Simulate selecting a tourist
        System.out.println("\n=== Selecting a tourist ===");
        ui.selectTourist("T3");

        // Simulate a connection error scenario
        System.out.println("\n=== Simulating connection error ===");
        connection.disconnect(); // cause connection loss
        ui.selectTourist("T5");
    }
}