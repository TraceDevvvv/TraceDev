package usecase;

import dto.DeleteBannerRequest;
import dto.DeleteBannerResponse;

/**
 * Input port (interface) for the Delete Banner use case.
 * Defines the contract for the interactor.
 */
public interface DeleteBannerInputPort {
    DeleteBannerResponse execute(DeleteBannerRequest request);
}