package com.etour.command;

import com.etour.banner.*;
import com.etour.image.*;
import com.etour.restpoint.*;

/**
 * BannerCommandHandler - processes InsertBannerCommand.
 */
public class BannerCommandHandler {
    private BannerRepository bannerRepository;
    private ImageStorageService imageStorageService;

    public BannerCommandHandler(BannerRepository bannerRepository, 
                               ImageStorageService imageStorageService) {
        this.bannerRepository = bannerRepository;
        this.imageStorageService = imageStorageService;
    }

    // Handles the command: validates, uploads image, creates banner, saves it.
    public Banner handle(InsertBannerCommand command) {
        System.out.println("BannerCommandHandler: processing command for rest point " 
            + command.getRestPointId());

        BannerInsertionValidator validator = new BannerInsertionValidator(bannerRepository, imageStorageService);
        ValidationResult result = validator.validate(command);

        if (result != ValidationResult.SUCCESS) {
            throw new RuntimeException("Validation failed: " + result);
        }

        // Upload image
        String imagePath = imageStorageService.uploadImage(command.getImageFile());

        // Create banner
        Banner banner = createBanner(command.getRestPointId(), imagePath);

        // Save banner
        bannerRepository.save(banner);

        // Increment banner count (in a real system, this would update RestPoint).
        System.out.println("BannerCommandHandler: incrementing banner count (simulated)");

        return banner;
    }

    private Banner createBanner(String restPointId, String imagePath) {
        String bannerId = "BANNER-" + System.currentTimeMillis();
        return new Banner(bannerId, imagePath, restPointId);
    }
}