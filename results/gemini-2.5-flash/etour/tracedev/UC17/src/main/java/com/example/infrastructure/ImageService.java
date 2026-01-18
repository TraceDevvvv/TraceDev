package com.example.infrastructure;

import com.example.domain.Image;

import java.util.Arrays;
import java.util.UUID;

/**
 * Concrete implementation of IImageStorageService, simulating an image storage system.
 * REQ-CD-001: Renamed from LocalStorageImageService.
 * Part of the Infrastructure Layer.
 */
public class ImageService implements IImageStorageService { // REQ-CD-001 (Renamed)

    /**
     * Stores the raw image data and returns a corresponding Image object.
     * (Mock implementation)
     * @param imageData The raw byte array of the image to store.
     * @return An Image object representing the stored image, or null if storage failed.
     */
    @Override
    public Image storeImage(byte[] imageData) {
        System.out.println("ISS: Storing image data (size: " + imageData.length + " bytes).");
        if (imageData == null || imageData.length == 0) {
            System.err.println("ISS: Cannot store empty image data.");
            return null;
        }

        // Simulate storage by generating an ID and a dummy URL
        String id = "IMG-" + UUID.randomUUID().toString();
        String url = "http://mockstorage.com/images/" + id + ".jpg"; // Assume JPEG for mock
        String format = "jpeg"; // Mock format
        int width = 1920; // Mock dimensions
        int height = 1080;

        System.out.println("ISS: Image stored. ID: " + id + ", URL: " + url);
        return new Image(id, url, format, width, height, imageData);
    }

    /**
     * Generates a preview (e.g., a thumbnail or compressed version) of the image data.
     * (Mock implementation: returns a small portion of the original data as "preview")
     * @param imageData The raw byte array of the image to preview.
     * @return A byte array representing the preview image.
     */
    @Override
    public byte[] previewImage(byte[] imageData) {
        System.out.println("ISS: Generating preview for image data (size: " + imageData.length + " bytes).");
        if (imageData == null || imageData.length == 0) {
            return new byte[0];
        }
        // Simulate a preview by returning a smaller portion or a modified version
        // In a real scenario, this would involve image processing libraries.
        int previewSize = Math.min(imageData.length, 100); // Take first 100 bytes as mock preview
        byte[] previewData = Arrays.copyOf(imageData, previewSize);
        System.out.println("ISS: Preview generated (size: " + previewData.length + " bytes).");
        return previewData;
    }
}