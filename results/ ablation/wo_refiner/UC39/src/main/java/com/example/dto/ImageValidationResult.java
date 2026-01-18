package com.example.dto;

import com.example.model.ImageProperties;

/**
 * Result of image validation.
 */
public class ImageValidationResult {
    private boolean isValid;
    private String validationMessage;
    private ImageProperties imageProperties;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public ImageProperties getImageProperties() {
        return imageProperties;
    }

    public void setImageProperties(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }
}