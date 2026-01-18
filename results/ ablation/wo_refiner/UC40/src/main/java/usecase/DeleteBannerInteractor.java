package usecase;

import dto.DeleteBannerRequest;
import dto.DeleteBannerResponse;
import entity.Banner;
import repository.BannerRepository;
import exceptions.DataSourceAccessException;

/**
 * Interactor implementing the delete banner business logic.
 * Follows a two‑phase delete (deactivate then remove) for data integrity.
 */
public class DeleteBannerInteractor implements DeleteBannerInputPort {
    private BannerRepository bannerRepository;

    public DeleteBannerInteractor(BannerRepository repository) {
        this.bannerRepository = repository;
    }

    @Override
    public DeleteBannerResponse execute(DeleteBannerRequest request) {
        // Validate request data (as per sequence diagram step 7)
        if (request.getBannerId() == null || request.getBannerId().isEmpty()) {
            return new DeleteBannerResponse(false, "Invalid banner ID", false);
        }

        try {
            // Step 8: Find the banner
            Banner banner = bannerRepository.findById(request.getBannerId());
            if (banner == null) {
                return new DeleteBannerResponse(false, "Banner not found", false);
            }

            // Check that banner belongs to the correct point of rest
            if (!banner.getPointOfRestId().equals(request.getPointOfRestId())) {
                return new DeleteBannerResponse(false, "Access denied: banner does not belong to this point of rest", false);
            }

            // Two‑phase delete: first deactivate
            banner.deactivate();
            bannerRepository.save(banner);

            // Then remove permanently
            bannerRepository.deleteById(banner.getBannerId());

            return new DeleteBannerResponse(true, "Successful elimination of banner", false);

        } catch (DataSourceAccessException e) {
            // Handle server connection loss (REQ-015)
            return new DeleteBannerResponse(false, "Connection to server lost: " + e.getMessage(), false);
        } catch (Exception e) {
            return new DeleteBannerResponse(false, "Deletion failed: " + e.getMessage(), false);
        }
    }

    /**
     * Handles cancellation of the operation (Alternative Flow 1).
     * This method is called by the controller when cancellation is requested.
     */
    public DeleteBannerResponse cancelOperation(String requestId) {
        // In a real implementation, we might track the ongoing request by requestId
        // and abort it. Here we just return a cancelled response.
        return new DeleteBannerResponse(false, "Operation cancelled", true);
    }

    // New method to simulate step 9: Validate request data
    private void validateRequestData(DeleteBannerRequest request) {
        System.out.println("[Interactor] Validate request data");
        // Validation logic is already in execute method.
    }

    // New method to simulate step 27: Create success response
    private DeleteBannerResponse createSuccessResponse() {
        System.out.println("[Interactor] Create success response");
        return new DeleteBannerResponse(true, "Successful elimination of banner", false);
    }

    // New method to simulate step 34: Create error response
    private DeleteBannerResponse createErrorResponse(String message) {
        System.out.println("[Interactor] Create error response");
        return new DeleteBannerResponse(false, message, false);
    }

    // New method to simulate step 45: Create error response (for connection lost)
    private DeleteBannerResponse createConnectionErrorResponse(String message) {
        System.out.println("[Interactor] Create error response for connection lost");
        return new DeleteBannerResponse(false, message, false);
    }
}