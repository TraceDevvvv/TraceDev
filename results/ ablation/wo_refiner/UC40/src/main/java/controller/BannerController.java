
package controller;

import dto.DeleteBannerRequest;
import dto.DeleteBannerResponse;
import usecase.DeleteBannerInputPort;
import usecase.DeleteBannerInteractor;

/**
 * Controller handling delete banner requests from the UI.
 * Delegates to the use case and returns the response.
 */
public class BannerController {
    private DeleteBannerInputPort deleteBannerUseCase;

    public BannerController(DeleteBannerInputPort useCase) {
        this.deleteBannerUseCase = useCase;
    }

    public DeleteBannerResponse handleDeleteBanner(DeleteBannerRequest request) {
        // Step 6: Create request DTO and delegate to use case
        return deleteBannerUseCase.execute(request);
    }

    /**
     * Cancels an ongoing delete operation (REQ‑013).
     * @param requestId identifier of the request to cancel
     */
    public void cancelOperation(String requestId) {
        // If the interactor supports cancellation, we call it.
        // For simplicity, we assume the interactor is the same instance.
        if (deleteBannerUseCase instanceof DeleteBannerInteractor) {
            DeleteBannerInteractor interactor = (DeleteBannerInteractor) deleteBannerUseCase;
            interactor.cancelOperation(requestId);
        }
        // In a real scenario we might also notify other components.
    }
}
