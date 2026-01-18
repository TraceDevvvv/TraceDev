package presentation;

import application.BannerCreationRequestDTO;
import application.BannerInsertionResponseDTO;
import application.BannerInsertionUseCase;

/**
 * Controller class for handling banner-related requests from the UI.
 * It acts as an entry point to the application layer (use cases).
 */
public class BannerController {
    private final BannerInsertionUseCase bannerInsertionUseCase;

    /**
     * Constructs a BannerController with a dependency on BannerInsertionUseCase.
     *
     * @param bannerInsertionUseCase The use case responsible for banner insertion logic.
     */
    public BannerController(BannerInsertionUseCase bannerInsertionUseCase) {
        this.bannerInsertionUseCase = bannerInsertionUseCase;
    }

    /**
     * Handles the request to insert a new banner.
     * Delegates the processing to the BannerInsertionUseCase.
     *
     * @param request The DTO containing banner creation details.
     * @return A DTO indicating the outcome of the request.
     */
    public BannerInsertionResponseDTO requestBannerInsertion(BannerCreationRequestDTO request) {
        System.out.println("[Controller] Received banner insertion request: " + request);
        return bannerInsertionUseCase.requestBannerInsertion(request);
    }

    /**
     * Handles the confirmation of a previously requested banner insertion.
     * Delegates the processing to the BannerInsertionUseCase.
     *
     * @param bannerId The ID of the banner to confirm.
     * @param pointOfRestaurantId The ID of the PointOfRestaurant associated with the banner.
     * @return A DTO indicating the outcome of the confirmation.
     */
    public BannerInsertionResponseDTO confirmInsertion(String bannerId, String pointOfRestaurantId) {
        System.out.println("[Controller] Received banner confirmation for bannerId: " + bannerId);
        return bannerInsertionUseCase.confirmInsertion(bannerId, pointOfRestaurantId);
    }

    /**
     * Handles the cancellation of a previously requested banner insertion.
     * Delegates the processing to the BannerInsertionUseCase.
     *
     * @param bannerId The ID of the banner to cancel.
     */
    public void cancelInsertion(String bannerId) {
        System.out.println("[Controller] Received banner cancellation for bannerId: " + bannerId);
        bannerInsertionUseCase.cancelInsertion(bannerId);
    }
}