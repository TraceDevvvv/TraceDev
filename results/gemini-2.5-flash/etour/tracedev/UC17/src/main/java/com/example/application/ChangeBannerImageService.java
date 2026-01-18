package com.example.application;

import com.example.domain.Banner;
import com.example.domain.Image;
import com.example.domain.ImageValidator;
import com.example.domain.TurningPoint;
import com.example.infrastructure.IBannerRepository;
import com.example.infrastructure.IETourService;
import com.example.infrastructure.IImageStorageService;
import com.example.infrastructure.ITurningPointRepository;
import com.example.presentation.AgencyOperatorController; // Assuming AOC is aware of this service for callbacks

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Application service for managing banner image changes.
 * This service orchestrates domain logic and infrastructure interactions
 * to fulfill the "Change Banner Image" use case.
 */
public class ChangeBannerImageService {

    // Dependencies on infrastructure and domain serv
    private final ITurningPointRepository turningPointRepository;
    private final IBannerRepository bannerRepository;
    private final ImageValidator imageValidator;
    private final IImageStorageService imageStorageService;
    private final IETourService etourService;
    private final AgencyOperatorController agencyOperatorController; // For displaying messages back to UI

    /**
     * Constructor for Dependency Injection.
     * @param turningPointRepository Repository for TurningPoint entities.
     * @param bannerRepository Repository for Banner entities.
     * @param imageValidator Validator for image characteristics.
     * @param imageStorageService Service for storing and previewing images.
     * @param etourService Service for ETOUR system integration.
     * @param agencyOperatorController The presentation layer controller for callbacks.
     */
    public ChangeBannerImageService(ITurningPointRepository turningPointRepository,
                                    IBannerRepository bannerRepository,
                                    ImageValidator imageValidator,
                                    IImageStorageService imageStorageService,
                                    IETourService etourService,
                                    AgencyOperatorController agencyOperatorController) {
        this.turningPointRepository = turningPointRepository;
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.imageStorageService = imageStorageService;
        this.etourService = etourService;
        this.agencyOperatorController = agencyOperatorController;
    }

    /**
     * Retrieves all available turning points.
     * Corresponds to Step 1 in the Sequence Diagram.
     * @return A list of TurningPoint objects.
     */
    public List<TurningPoint> getAvailableTurningPoints() {
        System.out.println("CBIS: Getting available turning points.");
        return turningPointRepository.findAll();
    }

    /**
     * Retrieves banners associated with a specific turning point.
     * Corresponds to Step 2 in the Sequence Diagram.
     * @param turningPointId The ID of the turning point.
     * @return A list of Banner objects.
     */
    public List<Banner> getBannersForTurningPoint(String turningPointId) {
        System.out.println("CBIS: Getting banners for turning point ID: " + turningPointId);
        TurningPoint selectedTurningPoint = turningPointRepository.findById(turningPointId);
        if (selectedTurningPoint != null) {
            System.out.println("CBIS: Found turning point: " + selectedTurningPoint.getName());
            return selectedTurningPoint.getBanners();
        }
        agencyOperatorController.displayError("Turning Point with ID " + turningPointId + " not found.");
        return List.of(); // Return empty list if turning point not found
    }

    /**
     * Retrieves details of a specific banner.
     * Corresponds to REQ-CD-007 and Step 5 in the Sequence Diagram (message m16).
     * @param bannerId The ID of the banner.
     * @return The Banner object, or null if not found.
     */
    public Banner getBannerDetails(String bannerId) { // Renamed from selectBanner to match class diagram and SD message m16
        System.out.println("CBIS: Getting banner details for ID: " + bannerId);
        return bannerRepository.findById(bannerId);
    }

    /**
     * Validates and processes a new image for a banner, preparing for preview.
     * Corresponds to REQ-CD-006 and Step 10 in the Sequence Diagram (message m22).
     * @param bannerId The ID of the banner.
     * @param imageData The byte array of the new image data.
     * @return The result of the image validation.
     */
    public ImageValidationResult processNewImageForBanner(String bannerId, byte[] imageData) { // Renamed from uploadImage to match class diagram and SD message m22
        System.out.println("CBIS: Processing new image for banner ID: " + bannerId);

        // Step 10: System checks the characteristics of the inserted image.
        // REQ-SD-003: validateImageSize
        boolean sizeIsValid = imageValidator.validateImageSize(imageData);
        System.out.println("CBIS: Image size validation result: " + (sizeIsValid ? "VALID" : "INVALID"));

        // REQ-SD-004: validateImageFormat
        boolean formatIsValid = imageValidator.validateImageFormat(imageData);
        System.out.println("CBIS: Image format validation result: " + (formatIsValid ? "VALID" : "INVALID"));

        // Original validate call, now potentially encompassing format/size or checking other characteristics
        ImageValidationResult validationResult = imageValidator.validate(imageData);
        System.out.println("CBIS: Overall image validation result: " + validationResult);

        if (validationResult == ImageValidationResult.VALID) {
            System.out.println("CBIS: Image characteristics are valid. Preparing preview. (m25)");
            // Generate preview - Sequence Diagram shows CBIS -> ISS: previewImage(newImageData)
            byte[] imagePreviewData = imageStorageService.previewImage(imageData); // m27
            System.out.println("CBIS: Image preview generated (size: " + imagePreviewData.length + " bytes).");
            // Call AOC to display confirmation prompt (m28)
            agencyOperatorController.displayConfirmationPrompt(bannerId, imagePreviewData);
        } else {
            System.out.println("CBIS: Image is not valid. Notifying error. (m50)");
            // Call AOC to display error (m51)
            agencyOperatorController.displayError("Image is invalid (characteristics). Please select a different image.");
        }
        return validationResult;
    }

    /**
     * Applies the image change to the banner, stores the image, and notifies ETOUR.
     * Corresponds to Step 14 and subsequent steps in the Sequence Diagram.
     * @param bannerId The ID of the banner to update.
     * @param imageData The byte array of the new image data.
     * @return True if the image change was successfully applied and ETOUR notified (or local update succeeded), false otherwise.
     */
    public boolean applyImageChange(String bannerId, byte[] imageData) {
        System.out.println("CBIS: Applying image change for banner ID: " + bannerId);

        Banner existingBanner = bannerRepository.findById(bannerId); // m33
        if (existingBanner == null) {
            agencyOperatorController.displayError("Banner with ID " + bannerId + " not found. Cannot apply image change.");
            return false;
        }

        // Store the new image
        Image newImage = imageStorageService.storeImage(imageData); // m35
        if (newImage == null) {
            agencyOperatorController.displayError("Failed to store the new image for banner ID: " + bannerId);
            return false;
        }
        System.out.println("CBIS: New image stored with ID: " + newImage.getId() + ", URL: " + newImage.getUrl());

        // Update the banner with the new image and timestamp
        Date currentTimestamp = new Date(); // currentTimestamp = now()
        existingBanner.updateImage(newImage, currentTimestamp); // REQ-CD-008, REQ-SD-005
        System.out.println("CBIS: Banner " + bannerId + " updated with new image and timestamp: " + currentTimestamp);

        // Save the updated banner
        bannerRepository.save(existingBanner);
        System.out.println("CBIS: Updated banner " + bannerId + " saved to repository.");

        // Check connection to ETOUR server
        boolean connectionStatus = etourService.checkConnection(); // m40
        System.out.println("CBIS: ETOUR connection status: " + (connectionStatus ? "Successful" : "Interrupted"));

        if (connectionStatus) {
            // ETOUR connection successful, notify ETOUR
            boolean notificationSuccess = etourService.notifyBannerChange(existingBanner); // m42
            if (notificationSuccess) {
                agencyOperatorController.displaySuccess("Banner updated successfully and ETOUR notified.");
                return true;
            } else {
                agencyOperatorController.displayWarning("Banner updated, but ETOUR notification failed."); // m46 implied
                return true; // Local update still successful
            }
        } else {
            // ETOUR connection interrupted (m45 implied)
            agencyOperatorController.displayWarning("Banner updated, but connection to ETOUR server interrupted. Notification failed."); // m46 implied
            return true; // Local update still successful
        }
    }
}