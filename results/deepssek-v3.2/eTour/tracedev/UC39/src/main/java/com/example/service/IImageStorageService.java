package com.example.service;

import com.example.domain.ImageMetadata;
import com.example.domain.ValidationResult;
import com.example.dto.BannerImageDTO;

/**
 * Interface for image storage operations.
 */
public interface IImageStorageService {
    ValidationResult validateImage(BannerImageDTO imageData);
    ImageMetadata uploadImage(BannerImageDTO imageData) throws Exception;
    void deleteImage(String oldImageKey);
}