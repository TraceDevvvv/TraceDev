package com.example;

import com.example.repository.CulturalObjectRepository;
import com.example.service.CulturalObjectService;
import com.example.system.ETourServerSimulator;
import com.example.ui.AgencyOperatorUI;

/**
 * Main class to run the application.
 * Simulates the Agency Operator logged in and performing insertion.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate entry condition: Agency HAS logged
        System.out.println("Agency Operator logged in.");

        // Initialize components
        CulturalObjectRepository repository = new CulturalObjectRepository();
        CulturalObjectService service = new CulturalObjectService(repository);
        ETourServerSimulator serverSimulator = new ETourServerSimulator();
        AgencyOperatorUI ui = new AgencyOperatorUI(service);

        // Simulate flow of events
        try {
            // Check connection to server (exit condition)
            if (!serverSimulator.isConnected()) {
                System.out.println("Exit Condition: Connection to the server ETOUR is interrupted.");
                return;
            }

            // Activate insertion feature
            ui.activateInsertion();
        } finally {
            ui.close();
        }
    }
}