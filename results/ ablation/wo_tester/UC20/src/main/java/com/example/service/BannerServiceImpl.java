package com.example.service;

import com.example.entity.Banner;
import com.example.dto.OperationResultDTO;
import com.example.dto.ValidationResult;
import com.example.repository.BannerRepository;
import com.example.validator.ImageValidator;

/**
 * Implementation of banner service.
 */
public class BannerServiceImpl implements BannerService {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private BackupService backupService;

    public BannerServiceImpl(BannerRepository bannerRepository, 
                           ImageValidator imageValidator,
                           BackupService backupService) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.backupService = backupService;
    }

    @Override
    public OperationResultDTO insertBanner(Banner banner, String refreshmentPointId) {
        System.out.println("Inserting banner for point: " + refreshmentPointId);
        
        try {
            // Validate image
            ValidationResult validation = validateBannerImage(banner);
            if (!validation.isValid()) {
                return new OperationResultDTO(false, "Invalid banner: " + validation.getErrors(), "VALIDATION_FAILED", 0);
            }
            
            // Save banner
            Banner savedBanner = bannerRepository.save(banner);
            
            // Store association
            bannerRepository.storeBannerAssociation(refreshmentPointId, savedBanner.getBannerId());
            
            // Backup data
            backupService.backupBannerData(savedBanner);
            
            return new OperationResultDTO(true, "Banner inserted successfully", "SUCCESS", 0);
        } catch (Exception e) {
            return new OperationResultDTO(false, "Failed to insert banner: " + e.getMessage(), "INSERT_ERROR", 3);
        }
    }

    @Override
    public ValidationResult validateBannerImage(Banner banner) {
        return imageValidator.validate(
            banner.getImageData(),
            banner.getImageFormat(),
            banner.getSizeInKB()
        );
    }

    @Override
    public int getBannerCountForPoint(String pointId) {
        return bannerRepository.countByPointId(pointId);
    }
}