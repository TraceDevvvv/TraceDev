package com.example.modifybanner.service;

import com.example.modifybanner.exception.BannerNotFoundException;
import com.example.modifybanner.exception.InvalidImageException;
import com.example.modifybanner.model.Banner;
import com.example.modifybanner.repository.BannerRepository;
import com.example.modifybanner.util.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for banner management.
 * Handles business logic related to banners, including image modification.
 */
@Service
public class BannerService {

    private final BannerRepository bannerRepository;
    private final ImageStorageService imageStorageService;
    private final ImageValidator imageValidator;

    /**
     * Constructor for BannerService, injecting dependencies.
     *
     * @param bannerRepository    Repository for Banner entities.
     * @param imageStorageService Service for storing and retrieving images.
     * @param imageValidator      Utility for validating image files.
     */
    @Autowired
    public BannerService(BannerRepository bannerRepository, ImageStorageService imageStorageService, ImageValidator imageValidator) {
        this.bannerRepository = bannerRepository;
        this.imageStorageService = imageStorageService;
        this.imageValidator = imageValidator;
    }

    /**
     * Retrieves a list of all banners associated with a specific restaurant.
     *
     * @param restaurantId The ID of the restaurant.
     * @return A list of Banner objects.
     */
    public List<Banner> getBannersByRestaurantId(Long restaurantId) {
        return bannerRepository.findByRestaurantId(restaurantId);
    }

    /**
     * Retrieves a banner by its ID.
     *
     * @param bannerId The ID of the banner to retrieve.
     * @return An Optional containing the Banner if found, or empty otherwise.
     */
    public Optional<Banner> getBannerById(Long bannerId) {
        return bannerRepository.findById(bannerId);
    }

    /**
     * Modifies the image of an existing banner.
     * This method performs image validation, stores the new image, deletes the old one,
     * and updates the banner's image path and URL in the database.
     *
     * @param bannerId  The ID of the banner to modify.
     * @param imageFile The new image file to associate with the banner.
     * @return The updated Banner object.
     * @throws BannerNotFoundException If the banner with the given ID does not exist.
     * @throws InvalidImageException   If the provided image file is invalid.
     * @throws IOException             If an error occurs during image storage or deletion.
     */
    @Transactional
    public Banner modifyBannerImage(Long bannerId, MultipartFile imageFile) throws IOException {
        // 1. Retrieve the banner
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new BannerNotFoundException("Banner with ID " + bannerId + " not found."));

        // 2. Validate the new image
        imageValidator.validateImage(imageFile);

        // Store the old image path for deletion
        String oldImagePath = banner.getImagePath();

        // 3. Store the new image
        String newImagePath = imageStorageService.storeImage(imageFile);
        String newImageUrl = imageStorageService.getImageUrl(newImagePath);

        // 4. Update banner details
        banner.setImagePath(newImagePath);
        banner.setImageUrl(newImageUrl);
        // The @PreUpdate annotation in Banner entity will handle updatedAt timestamp

        // 5. Save the updated banner
        Banner updatedBanner = bannerRepository.save(banner);

        // 6. Delete the old image (if it exists and is different from the new one)
        if (oldImagePath != null && !oldImagePath.isEmpty() && !oldImagePath.equals(newImagePath)) {
            try {
                imageStorageService.deleteImage(oldImagePath);
            } catch (IOException e) {
                // Log the error but don't rethrow, as the banner update was successful.
                // This ensures the main transaction commits even if old image deletion fails.
                System.err.println("Failed to delete old image at path: " + oldImagePath + ". Error: " + e.getMessage());
            }
        }

        return updatedBanner;
    }
}