package com.example;

/**
 * Use case for updating banner images with validation and error handling.
 */
public class UpdateBannerImageUseCase {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private ErrorHandler errorHandler;

    public UpdateBannerImageUseCase(BannerRepository bannerRepository, ImageValidator imageValidator, ErrorHandler errorHandler) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.errorHandler = errorHandler;
    }

    public UpdateResult execute(BannerUpdateRequest request) {
        try {
            // Validate image data
            ValidationResult validationResult = imageValidator.validate(request.getImageData());
            
            if (!validationResult.isValid()) {
                errorHandler.handleError(validationResult.getErrorMessage());
                return new UpdateResult(false, validationResult.getErrorMessage(), null);
            }
            
            // Find and update banner
            Banner banner = bannerRepository.findById(request.getBannerId());
            if (banner == null) {
                String errorMsg = "Banner not found with ID: " + request.getBannerId();
                errorHandler.handleError(errorMsg);
                return new UpdateResult(false, errorMsg, null);
            }
            
            // Generate new image URL (in real implementation, this would upload and get URL)
            String newImageUrl = "http://example.com/uploaded/" + request.getImageData().getFileName();
            banner.setImageUrl(newImageUrl);
            
            // Save updated banner
            Banner updatedBanner = bannerRepository.save(banner);
            
            return new UpdateResult(true, "Banner image updated successfully", updatedBanner);
            
        } catch (Exception e) {
            errorHandler.handleError("Error updating banner image: " + e.getMessage());
            errorHandler.logError(e);
            return new UpdateResult(false, "Internal error: " + e.getMessage(), null);
        }
    }
}