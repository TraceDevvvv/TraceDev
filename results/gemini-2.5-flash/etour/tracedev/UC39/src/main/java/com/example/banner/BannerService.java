package com.example.banner;

import java.util.List;
import java.util.Map;

/**
 * Service layer for Banner operations, handling business logic and coordinating
 * with repository and validation serv.
 */
public class BannerService {
    private IBannerRepository bannerRepository;
    private ImageValidationService imageValidationService;

    /**
     * Constructs a new BannerService.
     * @param bannerRepository The repository for banner data access.
     * @param imageValidationService The service for image validation.
     */
    public BannerService(IBannerRepository bannerRepository, ImageValidationService imageValidationService) {
        this.bannerRepository = bannerRepository;
        this.imageValidationService = imageValidationService;
    }

    /**
     * Retrieves a list of banners for a given restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A list of Banner objects.
     * @throws NetworkException If a network error occurs during data retrieval.
     */
    public List<Banner> getBannersByRestaurant(String restaurantId) throws NetworkException {
        System.out.println("[BannerService] Getting banners for restaurant: " + restaurantId);
        // Comment: Delegates to repository to fetch data. Propagates NetworkException.
        return bannerRepository.findAllByRestaurant(restaurantId);
    }

    /**
     * Retrieves a specific banner by its ID.
     * @param bannerId The ID of the banner.
     * @return The Banner object.
     * @throws NetworkException If a network error occurs during data retrieval.
     */
    public Banner getBannerById(String bannerId) throws NetworkException {
        System.out.println("[BannerService] Getting banner by ID: " + bannerId);
        // Comment: Delegates to repository to fetch data. Propagates NetworkException.
        return bannerRepository.findById(bannerId);
    }

    /**
     * Validates image characteristics and prepares for a banner image change.
     * This method does not persist the change, only validates and indicates readiness for confirmation.
     * @param bannerId The ID of the banner to change.
     * @param newImageUrl The new image URL proposed for the banner.
     * @param imageCharacteristics Map of characteristics for the new image.
     * @return True if the image is valid and the change can proceed to confirmation, false otherwise.
     */
    public boolean validateAndPrepareImageChange(String bannerId, String newImageUrl, Map<String, String> imageCharacteristics) {
        System.out.println("[BannerService] Validating and preparing image change for banner " + bannerId);
        // Comment: Delegates to ImageValidationService for validation.
        boolean isValid = imageValidationService.validateImage(imageCharacteristics);
        if (!isValid) {
            System.out.println("[BannerService] Image validation failed for banner " + bannerId);
        } else {
            System.out.println("[BannerService] Image validated successfully for banner " + bannerId + ". Ready for confirmation.");
        }
        return isValid;
    }

    /**
     * Finalizes the banner image change by updating the banner and persisting it.
     * @param bannerId The ID of the banner to update.
     * @param newImageUrl The new image URL to set for the banner.
     * @throws NetworkException If a network error occurs during data retrieval or persistence.
     */
    public void finalizeImageChange(String bannerId, String newImageUrl) throws NetworkException {
        System.out.println("[BannerService] Finalizing image change for banner " + bannerId + " to URL: " + newImageUrl);
        // Comment: Retrieve the banner, update its image, and then save it.
        Banner banner = bannerRepository.findById(bannerId);
        if (banner == null) {
            // Comment: Assuming the banner exists at this point after previous validation,
            // but handling null defensively.
            throw new IllegalArgumentException("Banner with ID " + bannerId + " not found for image change.");
        }
        // Comment: Call the banner's own method to update its state.
        banner.updateImageUrl(newImageUrl);
        bannerRepository.save(banner);
        System.out.println("[BannerService] Banner image change finalized successfully for banner " + bannerId);
    }
}