package com.example;

import com.example.controller.ChangeCulturalObjectController;
import com.example.controller.ErroredUseCaseController;
import com.example.repository.CulturalObjectRepository;
import com.example.repository.JpaCulturalObjectRepository;
import com.example.service.AuthenticationService;
import com.example.service.CulturalObjectService;
import com.example.service.SearchService;
import com.example.service.SessionManager;
import com.example.validator.BasicCulturalObjectValidator;
import com.example.validator.CulturalObjectValidator;
import com.example.view.CulturalHeritageSearchView;
import com.example.view.CulturalObjectEditView;

/**
 * Main class to demonstrate the use case flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        CulturalObjectRepository repository = new JpaCulturalObjectRepository();
        CulturalObjectValidator validator = new BasicCulturalObjectValidator();
        CulturalObjectService service = new CulturalObjectService(repository, validator);
        AuthenticationService authService = new SessionManager();
        ErroredUseCaseController errorController = new ErroredUseCaseController();
        
        // Create edit view
        CulturalObjectEditView editView = new CulturalObjectEditView(null);
        // Create controller
        ChangeCulturalObjectController controller = new ChangeCulturalObjectController(editView, service, authService);
        // Set controller reference in view
        // Note: In real DI framework, this would be handled by dependency injection
        editView = new CulturalObjectEditView(controller);
        
        // Create search view
        SearchService searchService = new SearchService();
        CulturalHeritageSearchView searchView = new CulturalHeritageSearchView(controller, searchService);
        
        // Simulate the use case flow
        System.out.println("=== Starting Change Cultural Object Data Use Case ===");
        searchView.simulateUserInteraction();
        
        // Give some time for async operations
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n=== Use Case Demonstration Complete ===");
    }
}