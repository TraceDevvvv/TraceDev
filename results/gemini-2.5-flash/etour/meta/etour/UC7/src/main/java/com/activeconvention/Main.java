package com.activeconvention;

import com.activeconvention.ui.ConventionController;
import com.activeconvention.ui.ConventionView;
import com.activeconvention.service.ConventionService;
import com.activeconvention.data.ConventionRepository;
import com.activeconvention.service.EtourService;
import com.activeconvention.util.Logger;

import javax.swing.*;

/**
 * Main class to start the ActiveConvention application.
 * This class initializes the core components of the application (Model, View, Controller, Serv, Repositories)
 * and sets up the application window.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize Logger
        Logger.logInfo("Starting ActiveConvention application...");

        // Create instances of data access and service layers
        ConventionRepository conventionRepository = new ConventionRepository();
        EtourService etourService = new EtourService();
        ConventionService conventionService = new ConventionService(conventionRepository, etourService);

        // Use SwingUtilities.invokeLater to ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main view (JFrame)
            ConventionView conventionView = new ConventionView();
            // Create the controller, injecting the service and view
            ConventionController conventionController = new ConventionController(conventionService, conventionView);
            // Initialize the controller, which sets up the view and its listeners
            conventionController.init();

            // Set up the main application window
            JFrame frame = new JFrame("Active Convention System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(conventionView); // Add the ConventionView panel to the frame
            frame.pack(); // Pack the frame to size its components
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true); // Make the frame visible

            Logger.logInfo("ActiveConvention application started successfully.");
        });
    }
}