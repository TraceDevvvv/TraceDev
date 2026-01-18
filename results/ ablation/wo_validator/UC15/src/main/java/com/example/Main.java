package com.example;

import com.example.application.controller.ModifyTouristUseCaseController;
import com.example.application.repository.TouristRepository;
import com.example.infrastructure.database.DatabaseConnection;
import com.example.infrastructure.repository.TouristRepositoryImpl;
import com.example.presentation.TouristForm;

/**
 * Main class to run the Modify Tourist Account use case.
 * Sets up the dependencies and starts the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:h2:mem:test", "sa", "");
        try {
            dbConnection.connect();
        } catch (Exception e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            return;
        }

        TouristRepository repository = new TouristRepositoryImpl(dbConnection);
        ModifyTouristUseCaseController controller = new ModifyTouristUseCaseController(repository);
        TouristForm form = new TouristForm(controller);

        // Run the modify tourist flow
        form.runModifyFlow();

        // Cleanup
        try {
            dbConnection.disconnect();
        } catch (Exception e) {
            System.err.println("Error disconnecting from database: " + e.getMessage());
        }
    }
}