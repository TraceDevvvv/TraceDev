package com.example.banner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class to demonstrate the Banner management application flow.
 * This class instantiates all components and simulates user interactions
 * as per the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Banner Management Application Demo ---");

        // 1. Initialize components
        // Dummy data for the repository
        List<Banner> initialBanners = Arrays.asList(
                new Banner("b001", "Summer Sale", "http://example.com/summer-sale.jpg", "restA"),
                new Banner("b002", "New Arrivals", "http://example.com/new-arrivals.jpg", "restA"),
                new Banner("b003", "Happy Hour", "http://example.com/happy-hour.jpg", "restB")
        );
        BannerRepository bannerRepository = new BannerRepository(initialBanners);
        ImageValidationService imageValidationService = new ImageValidationService();
        BannerService bannerService = new BannerService(bannerRepository, imageValidationService);

        MockBannerView mockBannerView = new MockBannerView();
        AuthenticationService authenticationService = new AuthenticationService();
        // Simulate a logged-in operator session (R3)
        UserSession userSession = new UserSession(true, "operator123");

        BannerController bannerController = new BannerController(
                bannerService, mockBannerView, authenticationService, userSession
        );
        mockBannerView.setController(bannerController); // Set controller for mock view callbacks

        System.out.println("\n--- Use Case Simulation: Change Banner Image ---");

        // Precondition: Operator is authenticated (handled by UserSession and AuthenticationService)

        // Step 1: Operator selects banner editing for restaurant "restA"
        System.out.println("\nScenario 1: Successful Image Change");
        String restaurantId = "restA";
        bannerController.selectBannerEditing(restaurantId);

        // Step 2: Operator selects a specific banner "b001"
        String bannerIdToEdit = "b001";
        bannerController.selectBanner(bannerIdToEdit);

        // Step 3: Operator selects a new picture (simulated by MockBannerView calling controller.changeBannerImage)
        String newImageUrlValid = "http://example.com/new-banner-image-valid.jpg";
        Map<String, String> validImageCharacteristics = new HashMap<>();
        validImageCharacteristics.put("size", "1000000"); // 1MB, which is valid
        validImageCharacteristics.put("format", "jpeg");
        mockBannerView.onImageSelected(newImageUrlValid, validImageCharacteristics);

        // Step 4: Operator confirms the change (calls confirmBannerImageChange)
        // The mockBannerView stores the last banner it prompted for, which the operator would use
        bannerController.confirmBannerImageChange(mockBannerView.getLastDisplayedConfirmationBannerId(), mockBannerView.getLastDisplayedConfirmationImageUrl());

        // Verify the change
        System.out.println("\n--- Verification after successful change ---");
        try {
            Banner updatedBanner = bannerService.getBannerById(bannerIdToEdit);
            System.out.println("Updated banner 'b001' image URL: " + updatedBanner.getCurrentImageUrl());
        } catch (NetworkException e) {
            System.err.println("Verification failed due to NetworkException: " + e.getMessage());
        }


        System.out.println("\n--- Scenario 2: Invalid Image Selection (Errored Use Case) ---");
        // Step 1: Operator selects banner editing for restaurant "restA"
        bannerController.selectBannerEditing(restaurantId);

        // Step 2: Operator selects a specific banner "b002"
        String bannerIdToEditInvalid = "b002";
        bannerController.selectBanner(bannerIdToEditInvalid);

        // Step 3: Operator selects an INVALID new picture (simulated by MockBannerView calling controller.changeBannerImage)
        String newImageUrlInvalid = "http://example.com/too-large-image.jpg";
        Map<String, String> invalidImageCharacteristics = new HashMap<>();
        invalidImageCharacteristics.put("size", "6000000"); // 6MB, which is invalid
        invalidImageCharacteristics.put("format", "png");
        mockBannerView.onImageSelected(newImageUrlInvalid, invalidImageCharacteristics);
        // No confirmation prompt, instead an error is displayed by the view.
        // No further action from operator for this branch.


        System.out.println("\n--- Scenario 3: Operator Cancels Operation ---");
        // Step 1: Operator selects banner editing for restaurant "restA"
        bannerController.selectBannerEditing(restaurantId);

        // Step 2: Operator selects a specific banner "b003"
        String bannerIdToCancel = "b003";
        bannerController.selectBanner(bannerIdToCancel);

        // Step 3: Operator selects a new picture (valid, to get to confirmation step)
        String newImageUrlToCancel = "http://example.com/cancel-this-image.jpg";
        Map<String, String> characteristicsForCancel = new HashMap<>();
        characteristicsForCancel.put("size", "500000");
        characteristicsForCancel.put("format", "gif");
        mockBannerView.onImageSelected(newImageUrlToCancel, characteristicsForCancel);

        // Step 4: Operator cancels the operation before confirming
        bannerController.cancelOperation();


        System.out.println("\n--- Scenario 4: Network Error during banner retrieval (R17) ---");
        bannerRepository.setSimulateNetworkError(true); // Enable network error simulation
        String nonExistentRestaurant = "restC";
        bannerController.selectBannerEditing(nonExistentRestaurant); // This should result in a network error message


        System.out.println("\n--- Scenario 5: Network Error during image change finalization (R17) ---");
        // Reset network error for initial steps
        bannerRepository.setSimulateNetworkError(false);
        bannerController.selectBanner("b001");
        String anotherImageUrl = "http://example.com/another-image.jpg";
        Map<String, String> anotherImageCharacteristics = new HashMap<>();
        anotherImageCharacteristics.put("size", "200000");
        mockBannerView.onImageSelected(anotherImageUrl, anotherImageCharacteristics);

        bannerRepository.setSimulateNetworkError(true); // Enable network error for the save operation
        bannerController.confirmBannerImageChange(mockBannerView.getLastDisplayedConfirmationBannerId(), mockBannerView.getLastDisplayedConfirmationImageUrl());


        System.out.println("\n--- Demo Complete ---");
    }
}