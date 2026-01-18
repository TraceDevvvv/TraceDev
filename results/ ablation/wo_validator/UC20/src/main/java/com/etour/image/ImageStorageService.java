package com.etour.image;

import java.io.File;

/**
 * ImageStorageService - interface for image storage operations.
 */
public interface ImageStorageService {
    String uploadImage(File imageFile);
    boolean validateImageFormat(File imageFile);
    boolean validateImageSize(File imageFile);
}