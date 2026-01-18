package com.banner.service;

import com.banner.model.Dimensions;
import com.banner.model.ImageFile;

/**
 * Service for validating image files.
 */
public class ImageValidatorService {
    public boolean validate(ImageFile imageFile) {
        // Simple validation: check if file is not null, size > 0, dimensions are positive.
        if (imageFile == null) return false;
        if (imageFile.getSize() <= 0) return false;
        Dimensions dim = imageFile.getDimensions();
        if (dim == null || dim.getWidth() <= 0 || dim.getHeight() <= 0) return false;
        // Additional validation could be added (e.g., content type, max size).
        return true;
    }

    public Dimensions getImageDimensions(ImageFile imageFile) {
        if (imageFile == null) return new Dimensions(0, 0);
        return imageFile.getDimensions();
    }

    public long getImageSize(ImageFile imageFile) {
        if (imageFile == null) return 0L;
        return imageFile.getSize();
    }
}