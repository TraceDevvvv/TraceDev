package com.system.validation;

import com.system.Image;
import java.util.Arrays;
import java.util.List;

/**
 * Validates that an image has an allowed format.
 */
public class ImageFormatValidator extends ValidationRule {
    private List<String> allowedFormats;

    public ImageFormatValidator() {
        this.allowedFormats = Arrays.asList("JPEG", "PNG", "GIF");
    }

    public List<String> getAllowedFormats() {
        return allowedFormats;
    }

    public void setAllowedFormats(List<String> allowedFormats) {
        this.allowedFormats = allowedFormats;
    }

    @Override
    public ValidationResult validate(Object input) {
        if (!(input instanceof Image)) {
            return new ValidationResult(false, "Input must be an Image.");
        }
        Image image = (Image) input;
        if (image.getFormat() == null) {
            return new ValidationResult(false, "Image format is null.");
        }
        boolean valid = allowedFormats.stream()
                .anyMatch(format -> format.equalsIgnoreCase(image.getFormat()));
        String message = valid ? "Format is valid." : "Image format not allowed. Allowed: " + allowedFormats;
        return new ValidationResult(valid, message);
    }

    @Override
    public String getErrorMessage() {
        return "Image format validation failed.";
    }
}