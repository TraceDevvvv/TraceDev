package com.etour.banner;

import com.etour.command.*;
import com.etour.image.*;
import com.etour.restpoint.*;

/**
 * BannerInsertionValidator - validates an InsertBannerCommand.
 */
public class BannerInsertionValidator {
    private BannerRepository bannerRepository;
    private ImageStorageService imageStorageService;

    public BannerInsertionValidator(BannerRepository bannerRepository, 
                                    ImageStorageService imageStorageService) {
        this.bannerRepository = bannerRepository;
        this.imageStorageService = imageStorageService;
    }

    // Performs validation: image format, size, and banner limit.
    public ValidationResult validate(InsertBannerCommand command) {
        System.out.println("BannerInsertionValidator: validating command...");

        // Validate image format
        if (!imageStorageService.validateImageFormat(command.getImageFile())) {
            System.out.println("BannerInsertionValidator: image format invalid");
            return ValidationResult.IMAGE_INVALID;
        }

        // Validate image size
        if (!imageStorageService.validateImageSize(command.getImageFile())) {
            System.out.println("BannerInsertionValidator: image size invalid");
            return ValidationResult.IMAGE_INVALID;
        }

        // Check banner limit
        int currentCount = bannerRepository.countByRestPointId(command.getRestPointId());
        // For simplicity, assume max banners = 5 (should be fetched from RestPoint).
        boolean limitReached = currentCount >= 5;
        if (limitReached) {
            System.out.println("BannerInsertionValidator: banner limit exceeded");
            return ValidationResult.LIMIT_EXCEEDED;
        }

        System.out.println("BannerInsertionValidator: validation SUCCESS");
        return ValidationResult.SUCCESS;
    }
}