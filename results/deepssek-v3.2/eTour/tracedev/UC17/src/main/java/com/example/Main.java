package com.example;

import com.example.adapters.BannerRepositoryImpl;
import com.example.adapters.ImageValidatorImpl;
import com.example.adapters.RestPointRepositoryImpl;
import com.example.application.ChangeBannerImageUseCaseController;
import com.example.serv.ETourServerGateway;
import com.example.serv.ImageFileSystemService;
import com.example.ui.UIController;

/**
 * Main application class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Banner Image Change System...");
        
        // Initialize dependencies
        BannerRepositoryImpl bannerRepository = new BannerRepositoryImpl();
        RestPointRepositoryImpl restPointRepository = new RestPointRepositoryImpl();
        ImageValidatorImpl imageValidator = new ImageValidatorImpl();
        ImageFileSystemService imageFileSystemService = new ImageFileSystemService();
        ETourServerGateway etourServerGateway = new ETourServerGateway();
        
        // Create use case controller with ImageFileSystemService
        ChangeBannerImageUseCaseController useCaseController = 
            new ChangeBannerImageUseCaseController(bannerRepository, imageValidator, restPointRepository, imageFileSystemService);
        
        // Create UI controller
        UIController uiController = new UIController(useCaseController, imageFileSystemService, etourServerGateway);
        
        // Simulate the sequence diagram flow
        System.out.println("\n=== Simulating Banner Image Change Flow ===");
        
        // Step: Agency Operator accesses edit banner
        uiController.accessEditBanner("rest1", "banner1");
        
        // Step: Agency Operator selects an image (simulated)
        uiController.selectImage("/images/banner2.png");
        
        // The rest of the flow continues automatically through sendConfirmation()
        
        System.out.println("\n=== Flow Complete ===");
    }
}