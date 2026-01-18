package com.culturalheritage;

import com.culturalheritage.controller.CulturalHeritageController;
import com.culturalheritage.repository.CulturalHeritageRepository;
import com.culturalheritage.repository.InMemoryCulturalHeritageRepository;
import com.culturalheritage.service.CulturalHeritageService;
import com.culturalheritage.service.CulturalHeritageValidator;

/**
 * Main application class to initialize and start the Cultural Heritage Management System.
 * This class sets up the dependency injection for the application components
 * and starts the main controller.
 */
public class MainApplication {

    /**
     * The main method, entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize repository
        CulturalHeritageRepository repository = new InMemoryCulturalHeritageRepository();

        // Initialize validator
        CulturalHeritageValidator validator = new CulturalHeritageValidator();

        // Initialize service with repository and validator
        CulturalHeritageService service = new CulturalHeritageService(repository, validator);

        // Initialize controller with service
        CulturalHeritageController controller = new CulturalHeritageController(service);

        // Start the application
        controller.start();
    }
}