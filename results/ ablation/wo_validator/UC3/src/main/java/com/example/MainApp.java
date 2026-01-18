package com.example;

import com.example.controller.ModifyCulturalHeritageController;
import com.example.datasource.DataSource;
import com.example.model.CulturalHeritage;
import com.example.repository.CulturalHeritageRepository;
import com.example.usecase.ModifyCulturalHeritageUseCase;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class to demonstrate the runnable code.
 * Not part of the UML, but included to show the system in action.
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Cultural Heritage Management System Starting...");

        // Setup data source (using in-memory H2 URL for demonstration)
        DataSource dataSource = new DataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", 30);

        // Initialize repository
        CulturalHeritageRepository repository = new CulturalHeritageRepository(dataSource);

        // Initialize use case
        ModifyCulturalHeritageUseCase useCase = new ModifyCulturalHeritageUseCase(repository);

        // Initialize controller
        ModifyCulturalHeritageController controller = new ModifyCulturalHeritageController(useCase);

        // Simulate creating a test cultural heritage (not in sequence diagram, but for demonstration)
        CulturalHeritage testItem = new CulturalHeritage("CH001", "Ancient Temple",
                "A historical temple from 500 BC", new Date(), "Athens, Greece", "Archaeological Site");
        repository.save(testItem);

        // Simulate the load request from Agency Operator as per sequence diagram
        System.out.println("\n--- Simulating Load Request ---");
        CulturalHeritage loaded = controller.handleLoadRequest("CH001");
        if (loaded != null) {
            System.out.println("Loaded Cultural Heritage: " + loaded.getTitle());
        }

        // Simulate the update request from Agency Operator as per sequence diagram
        System.out.println("\n--- Simulating Update Request ---");
    }
}