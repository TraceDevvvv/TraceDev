package com.example.application;

import com.example.domain.Banner;
import com.example.domain.ImageMetadata;
import com.example.dto.ChangeBannerImageRequest;
import com.example.dto.ChangeBannerImageResponse;
import com.example.exceptions.ConnectionException;
import com.example.ports.BannerRepository;
import com.example.ports.ImageValidator;
import com.example.ports.RestPointRepository;
import com.example.ports.ValidationResult;
import com.example.serv.ImageFileSystemService;
import java.util.List;

/**
 * Controller implementing the ChangeBannerImageUseCase.
 * Orchestrates the flow of changing a banner image.
 */
public class ChangeBannerImageUseCaseController implements ChangeBannerImageUseCase {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private RestPointRepository restPointRepository;
    private ImageFileSystemService imageFileSystemService;

    public ChangeBannerImageUseCaseController(BannerRepository bannerRepository,
                                              ImageValidator imageValidator,
                                              RestPointRepository restPointRepository,
                                              ImageFileSystemService imageFileSystemService) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.restPointRepository = restPointRepository;
        this.imageFileSystemService = imageFileSystemService;
    }

    @Override
    public ChangeBannerImageResponse execute(ChangeBannerImageRequest request) {
        try {
            // Step: Get image metadata from file system (as shown in sequence diagram)
            ImageMetadata imageMetadata = imageFileSystemService.getImageMetadata(request.getSelectedImagePath());
            
            // Step: Validate the image (as shown in sequence diagram)
            ValidationResult validationResult = imageValidator.validate(imageMetadata);
            if (!validationResult.isValid()) {
                return createErrorResponse(validationResult.getErrorMessages());
            }
            
            // Step: Retrieve the banner
            Banner banner = bannerRepository.findById(request.getBannerId());
            if (banner == null) {
                return createErrorResponse(List.of("Banner not found"));
            }
            
            // Step: Bookmark image (as shown in sequence diagram)
            String newImageUrl = imageFileSystemService.bookmarkImage(request.getSelectedImagePath(), request.getBannerId());
            
            // Step: Update banner image with actual metadata
            banner.updateImage(newImageUrl, imageMetadata.getWidth(), imageMetadata.getHeight());
            
            // Step: Save the updated banner
            Banner updatedBanner = bannerRepository.save(banner);
            
            return createSuccessResponse(updatedBanner.getBannerId());
            
        } catch (ConnectionException e) {
            return handleConnectionError();
        }
    }

    public ChangeBannerImageResponse createErrorResponse(List<String> errorMessages) {
        String message = String.join("; ", errorMessages);
        return new ChangeBannerImageResponse(false, message, null);
    }

    public ChangeBannerImageResponse createSuccessResponse(String bannerId) {
        return new ChangeBannerImageResponse(true, "Banner image changed successfully", bannerId);
    }

    public ChangeBannerImageResponse handleConnectionError() {
        return new ChangeBannerImageResponse(false, "Connection lost during operation", null);
    }
}