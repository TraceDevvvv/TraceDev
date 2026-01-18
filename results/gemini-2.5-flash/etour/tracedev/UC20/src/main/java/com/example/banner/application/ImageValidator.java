package com.example.banner.application;

import com.example.banner.infrastructure.IImageValidationStrategy;
import java.util.List;

/**
 * Orchestrates multiple {@link IImageValidationStrategy} implementations
 * to validate image data.
 */
public class ImageValidator {
    private final List<IImageValidationStrategy> strategies;

    /**
     * Constructs an ImageValidator with a list of validation strategies.
     *
     * @param strategies A list of {@link IImageValidationStrategy} to apply.
     */
    public ImageValidator(List<IImageValidationStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Validates the given image data by applying all configured strategies.
     * The image is considered valid only if it passes ALL strategies.
     *
     * @param imageData The raw binary data of the image.
     * @return True if the image passes all validations, false otherwise.
     */
    public boolean validateImage(byte[] imageData) { // SD: m26
        System.out.println("[ImageValidator] Validating image with " + strategies.size() + " strategies.");
        for (IImageValidationStrategy strategy : strategies) {
            if (!strategy.validate(imageData)) {
                System.out.println("[ImageValidator] Image failed validation by strategy: " + strategy.getClass().getSimpleName());
                return false; // Fail fast if any strategy fails
            }
        }
        System.out.println("[ImageValidator] Image passed all validations.");
        return true;
    }
}