package com.example;

public class ImageValidator {
    // Validates image characteristics (simplified example)
    public static boolean validateImage(String imagePath) {
        // Check if imagePath is not null and ends with a valid image extension
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return false;
        }
        String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        String lowerPath = imagePath.toLowerCase();
        for (String ext : validExtensions) {
            if (lowerPath.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}