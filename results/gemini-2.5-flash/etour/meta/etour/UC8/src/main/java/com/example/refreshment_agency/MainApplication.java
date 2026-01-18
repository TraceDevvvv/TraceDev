package com.example.refreshment_agency;

import com.example.refreshment_agency.controller.RefreshmentPointController;
import com.example.refreshment_agency.repository.RefreshmentPointRepository;
import com.example.refreshment_agency.service.RefreshmentPointService;
import com.example.refreshment_agency.service.ValidationService;
import com.example.refreshment_agency.util.InputReader;

/**
 * Main application class to start the program for modifying refreshment point data.
 * This class initializes the necessary components and orchestrates the application flow.
 */
public class MainApplication {

    public static void main(String[] args) {
        // Initialize utility and service components
        InputReader inputReader = new InputReader();
        RefreshmentPointRepository refreshmentPointRepository = new RefreshmentPointRepository();
        ValidationService validationService = new ValidationService();
        RefreshmentPointService refreshmentPointService = new RefreshmentPointService(refreshmentPointRepository, validationService);

        // Initialize the controller with its dependencies
        RefreshmentPointController refreshmentPointController = new RefreshmentPointController(refreshmentPointService, inputReader);

        System.out.println("Welcome to the Refreshment Agency Data Modification System!");
        System.out.println("Agency Operator logged in.");

        // Start the modification process
        refreshmentPointController.modifyRefreshmentPointData();

        System.out.println("Thank you for using the system. Goodbye!");

        // Close resources
        inputReader.close();
    }
}