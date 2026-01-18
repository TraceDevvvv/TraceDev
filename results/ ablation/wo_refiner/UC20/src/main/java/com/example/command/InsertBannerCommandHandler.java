package com.example.command;

import com.example.model.Banner;
import com.example.model.RestPoint;
import com.example.repository.IBannerRepository;
import com.example.repository.IRestPointRepository;
import com.example.validation.BannerImageValidator;
import com.example.validation.ValidationResult;
import com.example.ui.ConfirmationDialog;
import com.example.handler.ErrorHandler;

/**
 * Handles the InsertBannerCommand.
 * Coordinates validation, business rules, and persistence.
 */
public class InsertBannerCommandHandler {
    private IBannerRepository bannerRepository;
    private IRestPointRepository restPointRepository;
    private BannerImageValidator imageValidator;
    private ConfirmationDialog confirmationDialog;
    private ErrorHandler errorHandler;

    public InsertBannerCommandHandler(IBannerRepository bannerRepository,
                                      IRestPointRepository restPointRepository,
                                      BannerImageValidator imageValidator,
                                      ConfirmationDialog confirmationDialog,
                                      ErrorHandler errorHandler) {
        this.bannerRepository = bannerRepository;
        this.restPointRepository = restPointRepository;
        this.imageValidator = imageValidator;
        this.confirmationDialog = confirmationDialog;
        this.errorHandler = errorHandler;
    }

    /**
     * Handles the insert banner command.
     * Sequence diagram step 14.
     */
    public CommandResult handle(InsertBannerCommand command) {
        // Step 15: Find rest point.
        RestPoint restPoint = restPointRepository.findById(command.getRestPointId());

        // Step 16-17: Connection interruption handling (simulated).
        if (restPoint == null) {
            // Simulate connection error.
            errorHandler.handleConnectionError();
            return new CommandResult(false, "Connection to ETOUR server lost", null);
        }

        // Step 18-20: Check banner limit (REQ-014).
        if (!restPoint.canAddBanner()) {
            return new CommandResult(false, "Max banners reached", null);
        }

        // Step 21: Create banner object (with restPointId per REQ-011).
        Banner banner = new Banner(command.getImageData(), command.getImageType(), command.getRestPointId());

        // Step 23-26: Validate image (REQ-009).
        ValidationResult validationResult = imageValidator.validateImage(banner);
        if (!validationResult.isValid()) {
            errorHandler.handleInvalidImage(validationResult.getErrorMessage());
            return new CommandResult(false, "Image validation failed: " + validationResult.getErrorMessage(), null);
        }

        // Step 27: Show confirmation dialog (REQ-013).
        boolean userConfirms = confirmationDialog.show("Confirm Insertion", "Insert banner to rest point?");
        if (!userConfirms) {
            // User cancellation (sequence diagram opt "User Cancels").
            return new CommandResult(false, "Cancelled", null);
        }

        // Step 28-31: Add banner and save.
        restPoint.addBanner(banner);
        bannerRepository.save(banner);
        restPointRepository.save(restPoint);

        return new CommandResult(true, "Banner inserted successfully", banner.getId());
    }

    /**
     * Validates image (deprecated - moved to BannerImageValidator).
     * Kept for backward compatibility but not used.
     */
    private ValidationResult validateImage(Banner banner) {
        // Delegated to BannerImageValidator.
        return imageValidator.validateImage(banner);
    }

    /**
     * Checks banner limit (deprecated - now part of RestPoint.canAddBanner()).
     */
    private boolean checkBannerLimit(RestPoint restPoint) {
        return restPoint.canAddBanner();
    }
}