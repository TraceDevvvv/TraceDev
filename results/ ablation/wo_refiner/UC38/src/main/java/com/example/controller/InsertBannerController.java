package com.example.controller;

import com.example.service.BannerService;
import com.example.validation.ImageValidator;
import com.example.service.AuthenticationService;
import com.example.web.InsertBannerRequestDTO;
import com.example.web.InsertBannerResponseDTO;
import com.example.web.BannerDTO;
import com.example.domain.PointOfRestaurant;
import com.example.repository.PointOfRestRepository;
import java.util.Optional;

/**
 * Controller for Insert Banner use case.
 * Links to requirement REQ-5 (Flow of events).
 */
public class InsertBannerController {
    private BannerService bannerService;
    private ImageValidator imageValidator;
    private AuthenticationService authService;
    private ErrorHandler errorHandler;
    private PointOfRestRepository pointOfRestRepository; // Assumed dependency for limit check

    public InsertBannerController(BannerService bannerService,
                                  ImageValidator imageValidator,
                                  AuthenticationService authService,
                                  ErrorHandler errorHandler,
                                  PointOfRestRepository pointOfRestRepository) {
        this.bannerService = bannerService;
        this.imageValidator = imageValidator;
        this.authService = authService;
        this.errorHandler = errorHandler;
        this.pointOfRestRepository = pointOfRestRepository;
    }

    /**
     * Handles the insertion request as per the sequence diagram.
     */
    public InsertBannerResponseDTO handleInsertionRequest(InsertBannerRequestDTO request) {
        // Step 1: Validate image
        var validationResult = imageValidator.validate(request.getImageData(), request.getImageFormat());
        if (!validationResult.getIsValid()) {
            errorHandler.triggerErroredUseCase(validationResult);
            return new InsertBannerResponseDTO(false, validationResult.getErrorMessage(), null);
        }

        // Step 2: Check banner count limit (REQ-14)
        int currentCount = bannerService.getBannerCountForPointOfRest(request.getPointOfRestId());
        Optional<PointOfRestaurant> pointOpt = pointOfRestRepository.findById(request.getPointOfRestId());
        if (pointOpt.isEmpty()) {
            return new InsertBannerResponseDTO(false, "Point of restaurant not found", null);
        }
        PointOfRestaurant point = pointOpt.get();
        if (!point.canAcceptNewBanner(currentCount)) {
            return new InsertBannerResponseDTO(false, "Maximum banners already entered", null);
        }

        // Step 3: Insert banner (after user confirmation, simulated here)
        try {
            BannerDTO bannerDTO = new BannerDTO(
                    null, // id will be generated
                    request.getPointOfRestId(),
                    request.getImageData(),
                    request.getImageFormat()
            );
            BannerDTO inserted = bannerService.insertBanner(bannerDTO);
            return new InsertBannerResponseDTO(true, "Banner inserted successfully", inserted.getId());
        } catch (Exception e) {
            // Handle connection interruption (REQ-15)
            if (e instanceof com.example.exceptions.ConnectionException) {
                errorHandler.handleConnectionError((com.example.exceptions.ConnectionException) e);
                return new InsertBannerResponseDTO(false, "Connection lost", null);
            }
            return new InsertBannerResponseDTO(false, e.getMessage(), null);
        }
    }

    /**
     * Checks if the user is authenticated.
     */
    public boolean isUserAuthenticated(String userId) {
        return authService.isUserAuthenticated(userId);
    }

    // New method for sequence diagram messages
    public InsertBannerResponseDTO handleInsertionRequest(String requestDTO) {
        // This is a placeholder to match sequence diagram message name "handleInsertionRequest(requestDTO)"
        // The actual implementation is the method above which takes InsertBannerRequestDTO
        // We'll create an overload that accepts String (for compatibility) but delegate to the existing one
        // Since we can't parse String to DTO without context, we'll throw or return error
        return new InsertBannerResponseDTO(false, "Invalid request format - expected InsertBannerRequestDTO", null);
    }
}