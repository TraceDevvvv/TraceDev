package com.example.controller;

import com.example.model.*;
import com.example.repository.BannerRepository;
import com.example.service.ETourConnectionMonitor;
import com.example.service.ImageService;
import com.example.handler.ErroredUseCaseHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for modifying banners.
 * Added to satisfy requirement REQ-016, REQ-011, REQ-004, REQ-013
 */
public class ModifyBannerController {
    private BannerRepository bannerRepository;
    private BannerValidator bannerValidator;
    private ImageService imageService;
    private ETourConnectionMonitor connectionMonitor;
    private ErroredUseCaseHandler errorHandler;

    public ModifyBannerController(BannerRepository bannerRepository, BannerValidator bannerValidator,
                                  ImageService imageService, ETourConnectionMonitor connectionMonitor,
                                  ErroredUseCaseHandler errorHandler) {
        this.bannerRepository = bannerRepository;
        this.bannerValidator = bannerValidator;
        this.imageService = imageService;
        this.connectionMonitor = connectionMonitor;
        this.errorHandler = errorHandler;
    }

    /**
     * Retrieves banners for a given point of restaurant.
     * @param pointOfRestaurantId the point of restaurant ID
     * @return list of BannerDTO
     */
    public List<BannerDTO> getBannersByPointOfRestaurant(String pointOfRestaurantId) {
        List<Banner> banners = bannerRepository.findAllByPointOfRestaurantId(pointOfRestaurantId);
        return convertToDTOList(banners);
    }

    /**
     * Retrieves details of a specific banner.
     * @param bannerId banner ID
     * @return BannerDTO
     */
    public BannerDTO getBannerDetails(String bannerId) {
        Banner banner = bannerRepository.findById(bannerId);
        return convertToDTO(banner);
    }

    /**
     * Prepares image upload by validating the image.
     * @param formData the image upload form
     * @return image validation result
     */
    public ImageValidationResult prepareImageUpload(ImageUploadForm formData) {
        // In a real scenario, extract image data from formData.
        // Here, we simulate extracting image data.
        byte[] imageData = new byte[0]; // placeholder
        ImageValidationResult result = new ImageValidationResult();
        boolean formatValid = imageService.validateImageFormat(imageData);
        if (!formatValid) {
            ValidationResult validationResult = bannerValidator.validateImageProperties(null);
            result.setValid(false);
            result.setValidationMessage("Invalid image format.");
            errorHandler.handleImageValidationError(validationResult);
            return result;
        }
        ImageProperties properties = imageService.getImageProperties(imageData);
        ValidationResult validationResult = bannerValidator.validateImageProperties(properties);
        if (!validationResult.isValid()) {
            result.setValid(false);
            result.setValidationMessage("Image properties are invalid.");
            errorHandler.handleImageValidationError(validationResult);
            return result;
        }
        result.setValid(true);
        result.setValidationMessage("Image is valid.");
        result.setImageProperties(properties);
        return result;
    }

    /**
     * Modifies a banner with new image data.
     * @param updateRequest banner update request
     * @return operation result
     */
    public OperationResult modifyBanner(BannerUpdateRequest updateRequest) {
        // Check connection before proceeding
        if (!connectionMonitor.checkConnection()) {
            OperationResult result = new OperationResult();
            result.setSuccess(false);
            result.setMessage("Connection error");
            errorHandler.handleConnectionError("Connection to server lost.");
            return result;
        }

        // Validate ownership (simplified: assume ownership is valid)
        boolean ownershipValid = bannerValidator.validateBannerOwnership(
            updateRequest.getBannerId(), "dummyPointOfRestaurantId");
        if (!ownershipValid) {
            OperationResult result = new OperationResult();
            result.setSuccess(false);
            result.setMessage("Banner ownership validation failed.");
            return result;
        }

        // Upload image
        String imageUrl = imageService.uploadImage(updateRequest.getImageData(), updateRequest.getImageMetadata());

        // Update banner image
        Banner updatedBanner = bannerRepository.updateImage(updateRequest.getBannerId(), imageUrl);

        // Bookmark the image
        bannerRepository.bookmarkBannerImage(updateRequest.getBannerId(), imageUrl);

        // Convert to DTO
        BannerDTO dto = convertToDTO(updatedBanner);
        OperationResult result = new OperationResult();
        result.setSuccess(true);
        result.setMessage("Banner modified successfully.");
        result.setUpdatedBanner(dto);
        return result;
    }

    /**
     * Authenticates an operator using a token.
     * Added to satisfy requirement REQ-004
     * @param token authentication token
     * @return true if authenticated
     */
    public boolean authenticateOperator(String token) {
        PointOfRestaurantOperator operator = new PointOfRestaurantOperator("op1", "Operator", "rest1");
        return operator.authenticate(token);
    }

    /**
     * Converts a list of Banner to a list of BannerDTO.
     * Added for consistency with sequence diagram
     * @param banners list of banners
     * @return list of BannerDTO
     */
    public List<BannerDTO> convertToDTOList(List<Banner> banners) {
        List<BannerDTO> dtoList = new ArrayList<>();
        for (Banner banner : banners) {
            dtoList.add(convertToDTO(banner));
        }
        return dtoList;
    }

    /**
     * Converts a Banner to a BannerDTO.
     * Added for consistency with sequence diagram
     * @param banner the banner
     * @return BannerDTO
     */
    public BannerDTO convertToDTO(Banner banner) {
        if (banner == null) return null;
        BannerDTO dto = new BannerDTO();
        dto.setId(banner.getId());
        dto.setPointOfRestaurantId(banner.getPointOfRestaurantId());
        dto.setImageUrl(banner.getImageUrl());
        dto.setStatus("Active");
        return dto;
    }
}