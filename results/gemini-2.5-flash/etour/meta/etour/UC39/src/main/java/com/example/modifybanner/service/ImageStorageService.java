package com.example.modifybanner.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Interface for image storage operations.
 * Defines methods for storing, retrieving, and deleting image files.
 */
public interface ImageStorageService {

    /**
     * Stores an image file and returns its unique path.
     *
     * @param file The MultipartFile representing the image to store.
     * @return The unique path where the image is stored.
     * @throws IOException If an I/O error occurs during storage.
     */
    String storeImage(MultipartFile file) throws IOException;

    /**
     * Loads an image as a Path object given its stored path.
     *
     * @param imagePath The path where the image is stored.
     * @return A Path object representing the loaded image.
     */
    Path loadImage(String imagePath);

    /**
     * Deletes an image given its stored path.
     *
     * @param imagePath The path of the image to delete.
     * @throws IOException If an I/O error occurs during deletion.
     */
    void deleteImage(String imagePath) throws IOException;

    /**
     * Resolves the public URL for a given image path.
     *
     * @param imagePath The internal path of the image.
     * @return The publicly accessible URL for the image.
     */
    String getImageUrl(String imagePath);
}