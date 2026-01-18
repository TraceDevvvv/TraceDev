package com.example.banner;

import com.example.banner.application.BannerService;
import com.example.banner.application.ImageValidator;
import com.example.banner.infrastructure.BannerRepositoryImpl;
import com.example.banner.infrastructure.IImageValidationStrategy;
import com.example.banner.infrastructure.ImageFormatValidationStrategy;
import com.example.banner.infrastructure.ImageSizeValidationStrategy;
import com.example.banner.infrastructure.PointOfRestRepositoryImpl;
import com.example.banner.presentation.BannerManagementUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to set up the application context and run the Banner Management UI demo.
 * This acts as the entry point for the application, initializing all components
 * and demonstrating the use case flow as described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // --- Infrastructure Layer Initialization ---
        // Repositories
        BannerRepositoryImpl bannerRepository = new BannerRepositoryImpl();
        PointOfRestRepositoryImpl pointOfRestRepository = new PointOfRestRepositoryImpl();

        // Initialize dummy data for testing the repositories
        pointOfRestRepository.initDummyData();
        bannerRepository.initDummyData(); // Banners will be associated with pointOfRest IDs

        // Image Validation Strategies
        List<IImageValidationStrategy> validationStrategies = new ArrayList<>();
        validationStrategies.add(new ImageSizeValidationStrategy());
        validationStrategies.add(new ImageFormatValidationStrategy());

        // Image Validator
        ImageValidator imageValidator = new ImageValidator(validationStrategies);

        // --- Application Layer Initialization ---
        BannerService bannerService = new BannerService(bannerRepository, pointOfRestRepository, imageValidator);

        // --- Presentation Layer Initialization ---
        BannerManagementUI ui = new BannerManagementUI(bannerService);

        // Run the UI demo to simulate the use case
        ui.runDemo();
    }
}