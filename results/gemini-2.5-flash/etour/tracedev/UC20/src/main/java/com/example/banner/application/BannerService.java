
package com.example.banner.application;

import com.example.banner.domain.Banner;
import com.example.banner.domain.BannerStatus;
import com.example.banner.domain.PointOfRest;
import com.example.banner.infrastructure.IBannerRepository;
import com.example.banner.infrastructure.IPointOfRestRepository;
import com.example.banner.infrastructure.ETOURConnectionException;

import java.util.Date;
import java.util.List;

/**
 * The BannerService is the application layer component responsible for managing banner operations.
 * It orchestrates interactions between the UI, domain models, and infrastructure repositories.
 */
public class BannerService {
    private final IBannerRepository bannerRepository;
    private final IPointOfRestRepository pointOfRestRepository;
    private final ImageValidator imageValidator;

    /**
     * Constructs a BannerService with the necessary repositories and image validator.
     *
     * @param bannerRepository The repository for banner persistence.
     * @param pointOfRestRepository The repository for point of rest persistence.
     * @param imageValidator The image validator for checking image characteristics.
     */
    public BannerService(IBannerRepository bannerRepository,
                         IPointOfRestRepository pointOfRestRepository,
                         ImageValidator imageValidator) {
        this.bannerRepository = bannerRepository;
        this.pointOfRestRepository = pointOfRestRepository;
        this.imageValidator = imageValidator;
    }

    /**
     * Retrieves a list of all available Points of Rest from the repository.
     *
     * @return A list of {@link PointOfRest} objects.
     */
    public List<PointOfRest> getAvailablePointsOfRest() {
        System.out.println("[BannerService] Retrieving available points of rest.");
        return pointOfRestRepository.findAllPointsOfRest();
    }

    /**
     * Attempts to insert a new banner. This method performs validation and checks capacity
     * before indicating if the operation can proceed or if confirmation is needed.
     *
     * @param pointOfRestId The ID of the point of rest where the banner will be inserted.
     * @param imageData The raw binary data of the banner image.
     * @return A {@link BannerInsertionResult} indicating the outcome of the operation.
     */
    public BannerInsertionResult insertBanner(String pointOfRestId, byte[] imageData) { // SD: m27, m37, m41
        System.out.println("[BannerService] Attempting to insert banner for PointOfRest ID: " + pointOfRestId);

        // Step 7: Check image characteristics
        if (!imageValidator.validateImage(imageData)) {
            System.out.println("[BannerService] Image validation failed.");
            return BannerInsertionResult.INVALID_IMAGE;
        }

        // Step 8: Check max banners for point of rest
        PointOfRest pointOfRest = pointOfRestRepository.findById(pointOfRestId);
        if (pointOfRest == null) {
            System.err.println("[BannerService] PointOfRest with ID " + pointOfRestId + " not found.");
            // Assuming this is an internal error or invalid input not handled by UI pre-selection
            return BannerInsertionResult.INVALID_IMAGE; // Or a more specific error like POINT_OF_REST_NOT_FOUND
        }

        if (!pointOfRest.canAddBanner()) {
            System.out.println("[BannerService] Max banners exceeded for PointOfRest: " + pointOfRest.getName());
            return BannerInsertionResult.MAX_BANNERS_EXCEEDED;
        }

        // Step 9: Asks for confirmation
        // According to the sequence diagram, the service signals the UI to ask for confirmation
        System.out.println("[BannerService] Banner insertion awaiting confirmation for PointOfRest: " + pointOfRest.getName());
        return BannerInsertionResult.AWAITING_CONFIRMATION;
    }

    /**
     * Confirms the insertion of a banner after user approval.
     * This method proceeds with saving the banner and updating the point of rest.
     *
     * @param pointOfRestId The ID of the point of rest.
     * @param imageData The raw binary data of the banner image.
     * @return The newly created and saved Banner if successful, null if canceled or error.
     * @throws ETOURConnectionException if there's a problem communicating with the ETOUR system.
     */
    public Banner confirmBannerInsertion(String pointOfRestId, byte[] imageData) throws ETOURConnectionException { // SD: m49, m54, m56
        System.out.println("[BannerService] Confirming banner insertion for PointOfRest ID: " + pointOfRestId);

        PointOfRest pointOfRest = pointOfRestRepository.findById(pointOfRestId);
        if (pointOfRest == null) {
            System.err.println("[BannerService] PointOfRest with ID " + pointOfRestId + " not found during confirmation. This should not happen.");
            throw new IllegalStateException("PointOfRest not found during confirmation.");
        }

        if (!pointOfRest.canAddBanner()) {
            System.err.println("[BannerService] Max banners exceeded during confirmation. This indicates a race condition or incorrect state management.");
            throw new IllegalStateException("Max banners exceeded for PointOfRest: " + pointOfRest.getName() + " during confirmation.");
        }

        // Create the banner object
        Banner newBanner = createBanner(pointOfRestId, imageData);

        // Save the banner (can throw ETOURConnectionException)
        Banner savedBanner = bannerRepository.save(newBanner);
        System.out.println("[BannerService] Banner saved: " + savedBanner.getId());

        // Increment banner count for PointOfRest and update (can throw ETOURConnectionException)
        pointOfRest.incrementBannerCount();
        // pointOfRest.setLastModified(new Date()); // Update last modified - Removed as PointOfRest class doesn't have this method.
        pointOfRestRepository.update(pointOfRest);
        System.out.println("[BannerService] PointOfRest banner count updated for: " + pointOfRest.getName());

        System.out.println("[BannerService] Banner inserted successfully: " + savedBanner.getId());
        return savedBanner;
    }

    /**
     * Creates a new Banner instance with a generated image URL and pending status.
     *
     * @param pointOfRestId The ID of the point of rest.
     * @param imageData The raw binary data of the image (used to generate a dummy URL).
     * @return A new {@link Banner} object.
     */
    private Banner createBanner(String pointOfRestId, byte[] imageData) {
        // In a real application, imageData would be uploaded to a storage service
        // and a real URL would be returned. Here, we generate a dummy one.
        String dummyImageUrl = "http://cdn.example.com/banners/" + pointOfRestId + "-" + System.currentTimeMillis() + ".jpg";
        return new Banner(dummyImageUrl, pointOfRestId, BannerStatus.PENDING);
    }
}
