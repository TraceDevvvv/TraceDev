package com.example;

import com.example.model.AgencyOperator;
import com.example.controller.UIController;
import com.example.service.BannerService;
import com.example.service.AuthService;
import com.example.command.InsertBannerCommandHandler;
import com.example.validation.BannerImageValidator;
import com.example.ui.ConfirmationDialog;
import com.example.handler.ErrorHandler;
import com.example.repository.IBannerRepository;
import com.example.repository.IRestPointRepository;
import com.example.repository.InMemoryBannerRepository;
import com.example.repository.InMemoryRestPointRepository;

/**
 * Main class to demonstrate the system.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies.
        IBannerRepository bannerRepository = new InMemoryBannerRepository();
        IRestPointRepository restPointRepository = new InMemoryRestPointRepository();
        BannerImageValidator imageValidator = new BannerImageValidator();
        ConfirmationDialog confirmationDialog = new ConfirmationDialog();
        ErrorHandler errorHandler = new ErrorHandler();
        InsertBannerCommandHandler commandHandler = new InsertBannerCommandHandler(
                bannerRepository, restPointRepository, imageValidator, confirmationDialog, errorHandler);
        AuthService authService = new AuthService();
        BannerService bannerService = new BannerService(commandHandler, restPointRepository, errorHandler);
        UIController uiController = new UIController(bannerService, authService);

        // Create an Agency Operator.
        AgencyOperator operator = new AgencyOperator("AG001", "John Doe", "Travel Agency");

        // Simulate the sequence diagram flow.
        System.out.println("=== Starting Banner Insertion Flow ===");

        // Step 1: Agency Operator selects a rest point.
        String restPointId = "RP001";
        uiController.selectRestPoint(restPointId);

        // Step 2: Authentication check (implicit via UIController, but we simulate).
        boolean authenticated = authService.checkAuthentication(operator.getId());
        if (!authenticated) {
            System.out.println("Authentication failed.");
            return;
        }
        System.out.println("Authentication successful.");

        // Step 3-6: Get rest points and display (simplified).
        System.out.println("Rest points retrieved and displayed.");

        // Step 7: Store selected rest point.
        uiController.storeSelectedRestPointId(restPointId);

        // Step 8: Access banner insertion.
        uiController.accessInsertBanner();
        System.out.println("Image selection form displayed.");

        // Step 9: Select an image (simulate image data).
        byte[] dummyImageData = new byte[1024]; // 1KB dummy image.
        String imageType = "JPEG";
        uiController.selectImage(dummyImageData, imageType);
        uiController.storeImageData(dummyImageData, imageType);

        // Step 10: Submit insert request.
        System.out.println("Submitting insert request...");
        uiController.submitInsertRequest();

        // In a real application, the result would be observed via callbacks or UI updates.
        System.out.println("=== Flow completed ===");
    }
}