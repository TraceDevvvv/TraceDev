package com.system;

import com.system.validation.ValidationResult;
import com.system.validation.ValidationRule;
import com.system.validation.ImageFormatValidator;
import com.system.validation.ImageSizeValidator;
import com.system.validation.BannerCountValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages banner-related operations including validation.
 */
public class BannerManager {
    private List<ValidationRule> imageValidators;

    public BannerManager() {
        imageValidators = new ArrayList<>();
        imageValidators.add(new ImageFormatValidator());
        imageValidators.add(new ImageSizeValidator(1_048_576L)); // 1 MB
    }

    /**
     * Validates an image using all registered image validators.
     * @param image the image to validate
     * @return a list of validation results
     */
    public List<ValidationResult> validateImage(Image image) {
        List<ValidationResult> results = new ArrayList<>();
        for (ValidationRule validator : imageValidators) {
            results.add(validator.validate(image));
        }
        return results;
    }

    /**
     * Validates that a refreshment point has not reached its banner limit.
     * @param point the refreshment point to check
     * @return a validation result
     */
    public ValidationResult validateBannerCount(RefreshmentPoint point) {
        BannerCountValidator validator = new BannerCountValidator();
        return validator.validate(point);
    }

    /**
     * Adds a banner to a refreshment point after validation.
     * @param point the refreshment point
     * @param banner the banner to add
     * @return true if banner was added successfully
     */
    public boolean addBannerToPoint(RefreshmentPoint point, Banner banner) {
        ValidationResult countValidation = validateBannerCount(point);
        if (!countValidation.isValid()) {
            System.out.println("Cannot add banner: " + countValidation.getErrorMessage());
            return false;
        }
        List<ValidationResult> imageValidations = validateImage(banner.getImage());
        boolean allImageValid = imageValidations.stream().allMatch(ValidationResult::isValid);
        if (!allImageValid) {
            System.out.println("Cannot add banner: image validation failed.");
            return false;
        }
        return point.addBanner(banner);
    }
}