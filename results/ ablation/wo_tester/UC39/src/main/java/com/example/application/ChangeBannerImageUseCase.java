package com.example.application;

import com.example.domain.Banner;
import com.example.infrastructure.BannerRepository;
import com.example.infrastructure.ImageValidator;
import com.example.infrastructure.ImageData;
import com.example.infrastructure.ConnectionInterruptedException;

/**
 * Use case for changing a banner image.
 * Orchestrates the business logic.
 */
public class ChangeBannerImageUseCase {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    
    public ChangeBannerImageUseCase(BannerRepository repo, ImageValidator validator) {
        this.bannerRepository = repo;
        this.imageValidator = validator;
    }
    
    /**
     * Executes the command to change a banner image.
     * Implements the flow from the sequence diagram.
     * @param command the command containing change details
     * @throws ValidationException if image validation fails
     * @throws ConnectionInterruptedException if persistence fails due to connection issue
     */
    public void execute(ChangeBannerImageCommand command) throws ValidationException, ConnectionInterruptedException {
        String bannerId = command.getBannerId();
        String pointOfRestaurantId = command.getPointOfRestaurantId();
        ImageData newImageData = command.getNewImageData();
        
        Banner banner = bannerRepository.findBannerByIdAndPointOfRestaurantId(bannerId, pointOfRestaurantId);
        if (banner == null) {
            throw new IllegalArgumentException("Banner not found");
        }
        
        boolean isValid = imageValidator.validate(newImageData);
        if (!isValid) {
            throw new ValidationException("Invalid image");
        }
        
        String newImageUrl = newImageData.getFileName();
        banner.changeImage(newImageUrl);
        
        banner.bookmarkImage();
        
        try {
            bannerRepository.save(banner);
        } catch (ConnectionInterruptedException e) {
            throw e;
        }
    }
    
    /**
     * throw ValidationException
     * As per m27: return from UseCase to Controller
     */
    public void throwValidationException() throws ValidationException {
        throw new ValidationException("Validation failed");
    }
    
    /**
     * throw ConnectionInterruptedException
     * As per m42: return from UseCase to Controller
     */
    public void throwConnectionInterruptedException() throws ConnectionInterruptedException {
        throw new ConnectionInterruptedException("Connection interrupted");
    }
}