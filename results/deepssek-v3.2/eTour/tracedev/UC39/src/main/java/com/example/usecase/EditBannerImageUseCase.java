package com.example.usecase;

import com.example.domain.*;
import com.example.dto.BannerImageDTO;
import com.example.repository.IBannerRepository;
import com.example.service.IImageStorageService;
import java.util.List;

/**
 * Use case interactor for editing a banner image.
 */
public class EditBannerImageUseCase {
    private IBannerRepository bannerRepository;
    private IImageStorageService imageStorageService;

    public EditBannerImageUseCase(IBannerRepository bannerRepository, IImageStorageService imageStorageService) {
        this.bannerRepository = bannerRepository;
        this.imageStorageService = imageStorageService;
    }

    /**
     * Executes the banner image editing use case.
     * @param request the DTO containing image data and metadata
     * @return the updated Banner entity
     * @throws Exception if validation fails or connection interrupted
     */
    public Banner execute(BannerImageDTO request) throws Exception {
        // Step 1: Find the banner for the given point of restaurant
        Banner banner = findBannerForPointOfRestaurant(request.getPointOfRestaurantId(), request.getBannerId());
        if (banner == null) {
            throw new IllegalArgumentException("Banner not found");
        }

        // Step 2: Perform image validation
        ValidationResult validationResult = performValidation(request);
        if (!validationResult.isValid()) {
            // Trigger errored use case
            ErroredUseCase errored = new ErroredUseCase(
                "VALIDATION_ERROR",
                validationResult.getErrorMessage(),
                java.time.LocalDateTime.now()
            );
            throw new IllegalArgumentException(errored.getErrorDetails().toString());
        }

        // Step 3: Upload new image
        ImageMetadata metadata = imageStorageService.uploadImage(request);
        // Step 4: Delete old image if exists
        if (banner.getImageKey() != null && !banner.getImageKey().isEmpty()) {
            imageStorageService.deleteImage(banner.getImageKey());
        }
        // Step 5: Update banner with new image key
        banner.updateImage(metadata.getImageKey());
        // Step 6: Persist updated banner
        return bannerRepository.save(banner);
    }

    /**
     * Finds a banner by its ID for a specific point of restaurant.
     */
    private Banner findBannerForPointOfRestaurant(String pointOfRestaurantId, String bannerId) {
        List<Banner> banners = bannerRepository.findByPointOfRestaurant(pointOfRestaurantId);
        return banners.stream()
                .filter(b -> b.getId().equals(bannerId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Validates the image data using the storage service.
     */
    private ValidationResult performValidation(BannerImageDTO imageData) {
        return imageStorageService.validateImage(imageData);
    }
}