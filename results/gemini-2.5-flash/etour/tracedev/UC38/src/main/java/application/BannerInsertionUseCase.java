package application;

import domain.Banner;
import domain.IBannerValidator;
import domain.PointOfRestaurant;
import infrastructure.IBannerRepository;
import infrastructure.IPointOfRestaurantRepository;
import infrastructure.NetworkUnavailableException;

import java.util.Optional;

/**
 * Use case responsible for handling the logic related to banner insertion.
 * It coordinates interactions between repositories and validators to fulfill banner insertion requests.
 */
public class BannerInsertionUseCase {
    private final IBannerRepository bannerRepository;
    private final IPointOfRestaurantRepository pointOfRestaurantRepository;
    private final IBannerValidator bannerValidator;

    /**
     * Constructs a BannerInsertionUseCase with necessary dependencies.
     *
     * @param bannerRepository The repository for managing Banner entities.
     * @param pointOfRestaurantRepository The repository for managing PointOfRestaurant entities.
     * @param bannerValidator The validator for banner data (e.g., image URLs).
     */
    public BannerInsertionUseCase(IBannerRepository bannerRepository,
                                  IPointOfRestaurantRepository pointOfRestaurantRepository,
                                  IBannerValidator bannerValidator) {
        this.bannerRepository = bannerRepository;
        this.pointOfRestaurantRepository = pointOfRestaurantRepository;
        this.bannerValidator = bannerValidator;
    }

    /**
     * Processes a request to tentatively insert a new banner.
     * This method performs initial validations and checks, and may require further confirmation.
     *
     * @param request The DTO containing banner creation details.
     * @return A DTO indicating the outcome of the request, potentially requiring confirmation.
     */
    public BannerInsertionResponseDTO requestBannerInsertion(BannerCreationRequestDTO request) {
        // 1. Validate image URL
        if (!bannerValidator.validate(request.getImageUrl())) {
            return new BannerInsertionResponseDTO(false, "Invalid image characteristics.", null, false);
        }

        PointOfRestaurant foundPoR;
        try {
            // 2. Find PointOfRestaurant
            foundPoR = pointOfRestaurantRepository.findById(request.getPointOfRestaurantId());
            if (foundPoR == null) {
                return new BannerInsertionResponseDTO(false, "Point of Restaurant not found.", null, false);
            }
        } catch (NetworkUnavailableException e) {
            // Handle network interruption during PoR lookup
            return new BannerInsertionResponseDTO(false, "Connection to ETOUR server interrupted during PoR lookup.", null, false);
        }

        // 3. Check if banner can be added
        if (!foundPoR.canAddBanner()) {
            return new BannerInsertionResponseDTO(false, "Maximum number of banners exceeded.", null, false);
        }

        // 4. Tentatively create banner entity for ID generation/tracking
        // The banner's ID is generated here so it can be passed for confirmation tracking.
        Banner pendingBanner = new Banner(request.getImageUrl(), request.getPointOfRestaurantId());

        // We don't save the banner fully yet, just hold onto its ID for the confirmation step.
        // For simplicity, we can temporarily save it as a "pending" banner,
        // or just rely on its ID being passed around.
        // The sequence diagram shows `save` only after confirmation.
        // Let's assume for now that `findById` later works with this ID, so a temporary save is needed.
        // This is an assumption to make `findById` in confirmInsertion work as per sequence diagram
        try {
            bannerRepository.save(pendingBanner); // Temporarily save to track by ID
        } catch (NetworkUnavailableException e) {
            return new BannerInsertionResponseDTO(false, "Connection to ETOUR server interrupted while preparing banner.", null, false);
        }


        return new BannerInsertionResponseDTO(false, "Confirm banner insertion?", pendingBanner.getId(), true);
    }

    /**
     * Confirms the insertion of a banner that was previously requested.
     * This method finalizes the banner creation and updates the PointOfRestaurant.
     *
     * @param bannerId The ID of the banner to confirm.
     * @param pointOfRestaurantId The ID of the PointOfRestaurant associated with the banner.
     * @return A DTO indicating the final outcome of the confirmation.
     */
    public BannerInsertionResponseDTO confirmInsertion(String bannerId, String pointOfRestaurantId) {
        PointOfRestaurant currentPoR;
        Banner confirmedBanner;

        try {
            // Retrieve PoR to update its banner count
            currentPoR = pointOfRestaurantRepository.findById(pointOfRestaurantId);
            if (currentPoR == null) {
                return new BannerInsertionResponseDTO(false, "Point of Restaurant not found during confirmation.", null, false);
            }

            // Retrieve the banner that was tentatively created
            confirmedBanner = bannerRepository.findById(bannerId);
            if (confirmedBanner == null) {
                return new BannerInsertionResponseDTO(false, "Banner not found for confirmation. It might have been cancelled or never created.", null, false);
            }

            // Increment banner count and update PoR
            currentPoR.incrementBannerCount();
            pointOfRestaurantRepository.update(currentPoR);

            // Save the banner (it's already 'saved' tentatively in requestBannerInsertion,
            // this step conceptually makes it 'active' or finalizes it, or just re-saves).
            // The sequence diagram shows saving after PoR update.
            bannerRepository.save(confirmedBanner); // Re-saving, ensures its 'final' state if any changes were made.

        } catch (NetworkUnavailableException e) {
            // Handle network interruption during confirmation
            // If PoR update failed, try to revert banner save if it was already saved
            // This is a complex transactional scenario, for simplicity, we report failure.
            return new BannerInsertionResponseDTO(false, "Connection to ETOUR server interrupted during confirmation.", null, false);
        }

        return new BannerInsertionResponseDTO(true, "Banner inserted successfully.", bannerId, false);
    }

    /**
     * Cancels a previously requested banner insertion.
     * This typically involves removing any temporary banner data.
     *
     * @param bannerId The ID of the banner to cancel.
     */
    public void cancelInsertion(String bannerId) {
        // Attempt to delete the pending banner if it was temporarily saved.
        // In a real system, this might involve more sophisticated state management.
        try {
            Banner bannerToDelete = bannerRepository.findById(bannerId);
            if (bannerToDelete != null) {
                bannerRepository.delete(bannerId);
            } else {
                System.out.println("No pending banner with ID " + bannerId + " found to delete.");
            }
        } catch (NetworkUnavailableException e) {
            System.err.println("Error: Connection to ETOUR server interrupted during banner cancellation: " + e.getMessage());
            // Log the error but proceed, as cancellation is best effort.
        }
    }
}