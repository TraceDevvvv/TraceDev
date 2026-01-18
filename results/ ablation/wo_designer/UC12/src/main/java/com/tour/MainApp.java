package com.tour;

import com.tour.controllers.TouristController;

/**
 * Main application entry point.
 * Simulates the Agency Operator being logged in and starting the use case.
 */
public class MainApp {
    public static void main(String[] args) {
        TouristController controller = new TouristController();
        controller.runUseCase();
        controller.close();
    }
}