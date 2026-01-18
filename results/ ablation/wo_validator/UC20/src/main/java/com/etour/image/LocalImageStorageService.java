package com.etour.image;

import java.io.File;

/**
 * LocalImageStorageService - simulates image storage locally.
 */
public class LocalImageStorageService implements ImageStorageService {
    @Override
    public String uploadImage(File imageFile) {
        // Simulate uploading and returning a path.
        String path = "/uploads/" + imageFile.getName();
        System.out.println("ImageStorageService: uploaded image to " + path);
        return path;
    }

    @Override
    public boolean validateImageFormat(File imageFile) {
        // Simulate format validation (e.g., JPEG, PNG).
        String name = imageFile.getName().toLowerCase();
        boolean valid = name.endsWith(".jpg") || name.endsWith(".png");
        System.out.println("ImageStorageService: format validation result = " + valid);
        return valid;
    }

    @Override
    public boolean validateImageSize(File imageFile) {
        // Simulate size validation (max 5MB).
        long size = imageFile.length(); // in bytes
        boolean valid = size <= 5 * 1024 * 1024;
        System.out.println("ImageStorageService: size validation result = " + valid);
        return valid;
    }
}