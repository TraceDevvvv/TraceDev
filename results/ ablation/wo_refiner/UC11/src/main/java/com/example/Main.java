package com.example;

import com.example.controller.ViewConventionHistoryUseCaseController;
import com.example.repository.ETOURServerRepository;
import com.example.ui.UILayer;

/**
 * Main class to run the application.
 * This simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies.
        ETOURServerRepository repository = new ETOURServerRepository();
        ViewConventionHistoryUseCaseController controller = new ViewConventionHistoryUseCaseController(repository);
        UILayer uiLayer = new UILayer(controller);
        
        // Simulate agency operator accessing the feature.
        uiLayer.accessHistoricalConventionsFeature();
    }
}