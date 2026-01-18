package com.example;

import com.example.model.Tourist;
import com.example.initialization.SystemInitialization;
import com.example.system.SystemBoundary;
import com.example.controller.UseCaseController;
import com.example.repository.GPSHardwareRepository;
import com.example.repository.GPSRepository;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create tourist
        Tourist tourist = new Tourist("user123", "John Doe");

        // Create GPS repository
        GPSRepository repo = new GPSHardwareRepository(5000, 3);

        // Create controller
        UseCaseController controller = new UseCaseController(repo);

        // Create system initialization
        SystemInitialization init = new SystemInitialization(controller);

        // Create system boundary
        SystemBoundary boundary = new SystemBoundary(init);

        // Simulate tourist starting a search (could be beginSearch or beginAdvancedSearch)
        tourist.beginSearch();

        // Boundary handles request and triggers initialization
        boundary.handleTouristRequest(tourist);

        // Alternatively, simulate an advanced search
        // tourist.beginAdvancedSearch();
        // boundary.handleTouristRequest(tourist);
    }
}