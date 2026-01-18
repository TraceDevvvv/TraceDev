package com.system.validation;

import com.system.RefreshmentPoint;

/**
 * Validates that a refreshment point has not reached its maximum banner count.
 */
public class BannerCountValidator extends ValidationRule {

    @Override
    public ValidationResult validate(Object input) {
        if (!(input instanceof RefreshmentPoint)) {
            return new ValidationResult(false, "Input must be a RefreshmentPoint.");
        }
        RefreshmentPoint point = (RefreshmentPoint) input;
        boolean valid = !point.hasReachedMaxBanners();
        String message = valid ? "Banner count is within limit." : "Refreshment point has reached maximum banners.";
        return new ValidationResult(valid, message);
    }

    @Override
    public String getErrorMessage() {
        return "Banner count validation failed.";
    }
}