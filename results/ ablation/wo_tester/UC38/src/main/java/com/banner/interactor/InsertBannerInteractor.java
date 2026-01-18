package com.banner.interactor;

import com.banner.dto.InsertBannerRequest;
import com.banner.dto.InsertBannerResponse;
import com.banner.model.Banner;
import com.banner.repository.BannerRepository;
import com.banner.validation.ImageValidator;
import com.banner.handler.ErrorHandler;

/**
 * Core business logic for inserting a banner.
 * Implements sequence diagram interactions: validation, counting, persistence, notification.
 */
public class InsertBannerInteractor {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private ErrorHandler errorHandler;
    private int maxBannersPerRestaurant;

    public InsertBannerInteractor(BannerRepository bannerRepository,
                                  ImageValidator imageValidator,
                                  ErrorHandler errorHandler,
                                  int maxBannersPerRestaurant) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.errorHandler = errorHandler;
        this.maxBannersPerRestaurant = maxBannersPerRestaurant;
    }

    /**
     * Main execution method as per sequence diagram.
     */
    public InsertBannerResponse execute(InsertBannerRequest request) {
        // 1. Validate image
        if (!imageValidator.validateAll(request.getImageData(), request.getImageFormat())) {
            return errorHandler.handleImageValidationError();
        }

        // 2. Check banner count limit
        if (!validateBannerCount(request.getPointOfRestId())) {
            return errorHandler.handleMaxBannersExceededError();
        }

        // 3. Create and save banner
        Banner banner = createBannerEntity(request);
        try {
            Banner savedBanner = bannerRepository.save(banner);
            // 4. Send notification (exit condition)
            // NotificationService is invoked from controller as per diagram,
            // but we simulate it here for completeness.
            // In real flow controller would call notification.
            // We'll assume success and return response.
            return createSuccessResponse(savedBanner);
        } catch (Exception e) {
            // Handle server connection errors (opt block in sequence diagram)
            return errorHandler.handleGenericError(e);
        }
    }

    /**
     * Validate that the restaurant hasn't reached its banner limit.
     */
    private boolean validateBannerCount(String pointOfRestId) {
        int currentCount = bannerRepository.countByPointOfRestId(pointOfRestId);
        return currentCount < maxBannersPerRestaurant;
    }

    // Implement sequence diagram message: create Banner entity
    private Banner createBannerEntity(InsertBannerRequest request) {
        // This corresponds to message m18 and m19
        return new Banner(request.getPointOfRestId(), request.getImageData(), request.getImageFormat());
    }

    // Implement sequence diagram message: create success response
    private InsertBannerResponse createSuccessResponse(Banner savedBanner) {
        // This corresponds to message m23
        return new InsertBannerResponse(true, savedBanner.getId(), "Banner inserted successfully.");
    }
}