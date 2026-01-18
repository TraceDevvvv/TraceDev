package com.example.application;

import com.example.domain.Banner;
import com.example.infrastructure.BannerRepository;
import com.example.infrastructure.ImageValidator;
import com.example.infrastructure.BannerImageStorage;
import com.example.application.commands.UpdateBannerImageCommand;
import com.example.application.results.UpdateBannerImageResult;
import com.example.application.results.ValidationResult;

/**
 * Use case for updating a banner's image.
 * Coordinates the flow: fetch banner, validate image, store image, update banner.
 */
public class UpdateBannerImageUseCase {
    private final BannerRepository bannerRepository;
    private final ImageValidator imageValidator;
    private final BannerImageStorage bannerImageStorage;

    public UpdateBannerImageUseCase(BannerRepository bannerRepository,
                                    ImageValidator imageValidator,
                                    BannerImageStorage bannerImageStorage) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.bannerImageStorage = bannerImageStorage;
    }

    /**
     * Executes the banner image update process as per the sequence diagram.
     * @param command the command containing bannerId, operatorId, image data, and content type.
     * @return the result of the operation.
     */
    public UpdateBannerImageResult execute(UpdateBannerImageCommand command) {
        // Find the banner
        Banner banner = bannerRepository.findById(command.getBannerId());
        if (banner == null) {
            return new UpdateBannerImageResult(false, "Banner not found.");
        }

        // Validate the image
        ValidationResult validationResult = imageValidator.validate(command.getNewImageData(), command.getContentType());
        if (!validationResult.isValid()) {
            return new UpdateBannerImageResult(false, validationResult.getErrorMessage());
        }

        // Store the image and get new URL
        String newImageUrl = bannerImageStorage.storeImage(command.getNewImageData(), command.getContentType());
        if (newImageUrl == null || newImageUrl.isEmpty()) {
            return new UpdateBannerImageResult(false, "Failed to store the image.");
        }

        // Update the banner with the new image URL
        banner.updateImage(newImageUrl);
        bannerRepository.save(banner);

        return new UpdateBannerImageResult(true, "Banner updated successfully.");
    }
}