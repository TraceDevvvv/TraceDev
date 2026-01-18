package com.example.dto;

import java.util.List;

/**
 * DTO for image selection form.
 */
public class ImageSelectionForm implements DataTransferObject {
    private List<String> availableImages;

    public ImageSelectionForm(List<String> availableImages) {
        this.availableImages = availableImages;
    }

    public List<String> getAvailableImages() {
        return availableImages;
    }

    public void setAvailableImages(List<String> availableImages) {
        this.availableImages = availableImages;
    }
}