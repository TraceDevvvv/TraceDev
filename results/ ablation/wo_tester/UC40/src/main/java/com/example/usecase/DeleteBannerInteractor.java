package com.example.usecase;

import com.example.repository.BannerRepository;
import com.example.entity.Banner;
import com.example.response.DeleteBannerResponse;
import java.util.List;

/**
 * Interactor implementing the DeleteBannerUseCase.
 * Contains the business logic for banner deletion.
 */
public class DeleteBannerInteractor implements DeleteBannerUseCase {
    private BannerRepository bannerRepository;

    public DeleteBannerInteractor(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public DeleteBannerResponse execute(String operatorId, String bannerId) {
        try {
            // If bannerId is null, this is a retrieval request (for displaying banners)
            if (bannerId == null) {
                // In a real system, we would get the pointOfRestId from operatorId
                String pointOfRestId = getPointOfRestIdFromOperatorId(operatorId);
                List<Banner> banners = bannerRepository.findAllByPointOfRest(pointOfRestId);
                return new DeleteBannerResponse(true, "Banners retrieved successfully", null, banners);
            }
            
            // This is a deletion request
            boolean deleted = bannerRepository.deleteById(bannerId);
            if (deleted) {
                return new DeleteBannerResponse(true, "Banner deleted successfully", bannerId);
            } else {
                return new DeleteBannerResponse(false, "Banner not found or could not be deleted", bannerId);
            }
        } catch (Exception e) {
            // Handle connection errors and other exceptions
            System.err.println("Error in DeleteBannerInteractor: " + e.getMessage());
            return new DeleteBannerResponse(false, "Server error occurred", bannerId);
        }
    }

    /**
     * Helper method to get point of restaurant ID from operator ID.
     * In a real implementation, this would query a user-service or database.
     */
    private String getPointOfRestIdFromOperatorId(String operatorId) {
        // Simplified: assume operatorId maps to a pointOfRestId
        // In reality, this would be a database lookup
        return "pointOfRest_" + operatorId;
    }
}