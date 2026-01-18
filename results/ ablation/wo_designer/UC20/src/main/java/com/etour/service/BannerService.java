package com.etour.service;

import com.etour.exception.ImageValidationException;
import com.etour.exception.MaxBannersExceededException;
import com.etour.exception.ServerConnectionException;
import com.etour.model.Banner;
import com.etour.model.RefreshmentPoint;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service layer for Banner operations.
 */
public class BannerService {
    private static final int MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5 MB
    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 600;
    private static final List<String> ALLOWED_FORMATS = List.of("jpg", "jpeg", "png", "gif");

    /**
     * Validates image characteristics.
     *
     * @param imagePath Path to the image.
     * @param imageSize Size of the image in bytes.
     * @param width     Width of the image in pixels.
     * @param height    Height of the image in pixels.
     * @param format    Format of the image.
     * @throws ImageValidationException if validation fails.
     */
    public void validateImage(String imagePath, long imageSize, int width, int height, String format)
            throws ImageValidationException {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new ImageValidationException("Image path is required.");
        }
        if (imageSize > MAX_IMAGE_SIZE) {
            throw new ImageValidationException("Image size exceeds maximum allowed (5 MB).");
        }
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new ImageValidationException("Image dimensions must be at least 800x600 pixels.");
        }
        if (!ALLOWED_FORMATS.contains(format.toLowerCase())) {
            throw new ImageValidationException("Image format not allowed. Allowed formats: " + ALLOWED_FORMATS);
        }
    }

    /**
     * Checks if the refreshment point can accept a new banner.
     *
     * @param point         The refreshment point.
     * @param currentCount  Current number of banners at the point.
     * @throws MaxBannersExceededException if max banners reached.
     */
    public void checkBannerLimit(RefreshmentPoint point, int currentCount) throws MaxBannersExceededException {
        if (currentCount >= point.getMaxBanners()) {
            throw new MaxBannersExceededException(
                    "Refreshment point '" + point.getName() + "' has reached the maximum allowed banners (" +
                            point.getMaxBanners() + ").");
        }
    }

    /**
     * Simulates storing the banner (in a real scenario this would involve database persistence).
     *
     * @param banner The banner to store.
     * @throws ServerConnectionException if connection to server fails.
     */
    public void storeBanner(Banner banner) throws ServerConnectionException {
        // Simulate potential server connection issue
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ServerConnectionException("Connection to server ETOUR interrupted.");
        }
        // In a real application, you would persist the banner to a database here.
        System.out.println("Banner stored successfully: " + banner.getImagePath() +
                " for refreshment point ID " + banner.getRefreshmentPointId());
    }

    /**
     * Creates a Banner object from given parameters.
     */
    public Banner createBanner(int refreshmentPointId, String imagePath, long imageSize,
                               int width, int height, String format) {
        return new Banner(0, refreshmentPointId, imagePath, LocalDateTime.now(),
                format, imageSize, width, height);
    }
}