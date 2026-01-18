
package com.example.banner.interfaceadapters.controller;

import com.example.banner.application.command.DeleteBannerCommand;
import com.example.banner.application.usecase.DeleteBannerUseCase;

/**
 * REST controller for banner operations.
 */
public class BannerController implements BannerControllerPort {
    private DeleteBannerUseCase deleteBannerUseCase;

    public BannerController(DeleteBannerUseCase deleteBannerUseCase) {
        this.deleteBannerUseCase = deleteBannerUseCase;
    }

    @Override
    public Object deleteBanner(String bannerId, String pointOfRestId, String operatorId) {
        try {
            DeleteBannerCommand command = new DeleteBannerCommand(bannerId, pointOfRestId, operatorId);
            deleteBannerUseCase.execute(command);
            // Step: Display success message
            return "Banner deleted successfully";
        } catch (RuntimeException e) {
            // Step: Display error message
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Simulates cancellation endpoint (as in sequence diagram alternative flow).
     */
    public Object cancelOperation() {
        if (deleteBannerUseCase instanceof com.example.banner.application.usecase.DeleteBannerInteractor) {
            ((com.example.banner.application.usecase.DeleteBannerInteractor) deleteBannerUseCase).cancelOperation();
        }
        return "Operation cancelled";
    }
}
