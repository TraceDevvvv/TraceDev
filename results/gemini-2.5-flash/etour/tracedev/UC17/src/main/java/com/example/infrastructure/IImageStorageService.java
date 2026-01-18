package com.example.infrastructure;

import com.example.domain.Image;

/**
 * Interface for serv that handle image storage and retrieval.
 * Part of the Infrastructure Layer.
 */
public interface IImageStorageService {
    /**
     * Stores the raw image data and returns a corresponding Image object
     * with details like ID, URL, and metadata.
     * @param imageData The raw byte array of the image to store.
     * @return An Image object representing the stored image, or null if storage failed.
     */
    Image storeImage(byte[] imageData);

    /**
     * Generates a preview (e.g., a thumbnail or compressed version) of the image data.
     * @param imageData The raw byte array of the image to preview.
     * @return A byte array representing the preview image.
     */
    byte[] previewImage(byte[] imageData);
}