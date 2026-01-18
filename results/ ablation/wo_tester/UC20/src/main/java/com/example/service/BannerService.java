package com.example.service;

import com.example.entity.Banner;
import com.example.dto.OperationResultDTO;
import com.example.dto.ValidationResult;

/**
 * Interface for banner-related operations.
 */
public interface BannerService {
    /**
     * Inserts a banner for a refreshment point.
     */
    OperationResultDTO insertBanner(Banner banner, String refreshmentPointId);
    
    /**
     * Validates banner image characteristics.
     */
    ValidationResult validateBannerImage(Banner banner);
    
    /**
     * Gets the current banner count for a point.
     */
    int getBannerCountForPoint(String pointId);
}