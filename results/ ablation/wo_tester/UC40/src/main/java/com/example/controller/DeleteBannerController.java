package com.example.controller;

import com.example.usecase.DeleteBannerUseCase;
import com.example.repository.BannerRepository;
import com.example.service.AuthenticationService;
import com.example.response.DeleteBannerResponse;

/**
 * Controller for handling banner deletion requests.
 * Orchestrates authentication, validation, and use case execution.
 * Non-functional requirement: Ensures reliable deletion with proper error handling and authentication validation.
 */
public class DeleteBannerController {
    private BannerRepository bannerRepository;
    private AuthenticationService authService;
    private DeleteBannerUseCase deleteBannerUseCase;

    public DeleteBannerController(BannerRepository bannerRepository, AuthenticationService authService) {
        this.bannerRepository = bannerRepository;
        this.authService = authService;
        this.deleteBannerUseCase = new DeleteBannerInteractor(bannerRepository);
    }

    /**
     * Constructor with explicit use case for dependency injection flexibility.
     */
    public DeleteBannerController(BannerRepository bannerRepository, AuthenticationService authService, 
                                  DeleteBannerUseCase deleteBannerUseCase) {
        this.bannerRepository = bannerRepository;
        this.authService = authService;
        this.deleteBannerUseCase = deleteBannerUseCase;
    }

    /**
     * Executes the delete use case for a specific banner.
     * @param operatorId the ID of the operator
     * @param bannerId the ID of the banner to delete
     * @return response with deletion result
     */
    public DeleteBannerResponse executeDeleteUseCase(String operatorId, String bannerId) {
        // Validate authentication
        if (!validateRequest(operatorId)) {
            return new DeleteBannerResponse(false, "Authentication failed", bannerId);
        }
        
        // Orchestrate deletion
        return deleteBannerUseCase.execute(operatorId, bannerId);
    }

    /**
     * Retrieves banners for an operator (for display before deletion).
     * @param operatorId the ID of the operator
     * @return response with list of banners
     */
    public DeleteBannerResponse retrieveBannersForOperator(String operatorId) {
        // Validate authentication
        if (!validateRequest(operatorId)) {
            return new DeleteBannerResponse(false, "Authentication failed", null);
        }
        
        // Execute use case with null bannerId to indicate retrieval only
        return deleteBannerUseCase.execute(operatorId, null);
    }

    /**
     * Validates the request by checking authentication.
     * @param operatorId the operator ID to validate
     * @return true if request is valid, false otherwise
     */
    private boolean validateRequest(String operatorId) {
        // Simplified validation - in reality would check session token
        return authService.validateSession("session_token_" + operatorId);
    }

    /**
     * Orchestrates the deletion process.
     * @param bannerId the ID of the banner to delete
     * @return true if deletion was successful, false otherwise
     */
    private boolean orchestrateDeletion(String bannerId) {
        DeleteBannerResponse response = deleteBannerUseCase.execute(null, bannerId);
        return response.isSuccess();
    }

    /**
     * Handles operation cancellation from UI.
     */
    public void operationCancelled() {
        System.out.println("Operation cancelled by user");
        // Cleanup or other cancellation logic would go here
    }
}