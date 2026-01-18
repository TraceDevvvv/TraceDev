package com.example.infrastructure;

import com.example.domain.ConnectionInterruptedException;
import com.example.domain.ImageMetadata;
import com.example.domain.ValidationResult;
import com.example.dto.BannerImageDTO;
import com.example.service.IImageStorageService;
import java.time.LocalDateTime;

/**
 * Concrete implementation of IImageStorageService.
 */
public class ImageStorageServiceImpl implements IImageStorageService {
    private CloudStorage cloudStorageClient;

    public ImageStorageServiceImpl(CloudStorage cloudStorageClient) {
        this.cloudStorageClient = cloudStorageClient;
    }

    @Override
    public ValidationResult validateImage(BannerImageDTO imageData) {
        // Check image format
        boolean validFormat = checkImageFormat(imageData.getContentType());
        if (!validFormat) {
            return new ValidationResult(false, "Invalid image format");
        }
        // Check image size
        boolean validSize = checkImageSize(imageData.getImageData().length);
        if (!validSize) {
            return new ValidationResult(false, "Image size too large");
        }
        return new ValidationResult(true, null);
    }

    @Override
    public ImageMetadata uploadImage(BannerImageDTO imageData) throws Exception {
        // Simulate possible connection interruption
        if (Math.random() < 0.1) { // 10% chance
            throw new ConnectionInterruptedException(
                "Upload failed: connection interrupted",
                "Network issue",
                LocalDateTime.now()
            );
        }
        // Upload to cloud storage
        String imageKey = cloudStorageClient.upload(imageData.getImageData(), imageData.getFileName());
        // Return metadata
        return new ImageMetadata(
            imageKey,
            "https://storage.example.com/" + imageKey,
            imageData.getImageData().length,
            imageData.getContentType()
        );
    }

    @Override
    public void deleteImage(String oldImageKey) {
        cloudStorageClient.delete(oldImageKey);
    }

    boolean checkImageFormat(String contentType) {
        // Accept jpeg, png, gif, webp
        return contentType != null && (
            contentType.equals("image/jpeg") ||
            contentType.equals("image/png") ||
            contentType.equals("image/gif") ||
            contentType.equals("image/webp")
        );
    }

    boolean checkImageSize(long size) {
        // Max 5 MB
        return size <= 5 * 1024 * 1024;
    }
}