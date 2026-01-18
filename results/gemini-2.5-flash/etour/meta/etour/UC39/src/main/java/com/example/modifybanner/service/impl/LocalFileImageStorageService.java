package com.example.modifybanner.service.impl;

import com.example.modifybanner.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Local file system implementation of ImageStorageService.
 * Stores images in a designated directory on the server's file system.
 */
@Service
public class LocalFileImageStorageService implements ImageStorageService {

    @Value("${app.image.upload-dir}")
    private String uploadDir; // Directory where images will be stored

    @Value("${app.image.base-url}")
    private String baseUrl; // Base URL for accessing stored images

    /**
     * Stores an image file to the local file system.
     * Generates a unique file name to prevent collisions.
     *
     * @param file The MultipartFile representing the image to store.
     * @return The unique path (filename) where the image is stored relative to the upload directory.
     * @throws IOException If an I/O error occurs during storage.
     */
    @Override
    public String storeImage(MultipartFile file) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        // Generate a unique file name
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        Path filePath = uploadPath.resolve(uniqueFileName);

        // Copy file to the target location
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return uniqueFileName; // Return only the file name, not the full path
    }

    /**
     * Loads an image as a Path object from the local file system.
     *
     * @param imagePath The relative path (filename) of the image.
     * @return A Path object representing the loaded image's absolute path.
     */
    @Override
    public Path loadImage(String imagePath) {
        return Paths.get(uploadDir).toAbsolutePath().normalize().resolve(imagePath).normalize();
    }

    /**
     * Deletes an image from the local file system.
     *
     * @param imagePath The relative path (filename) of the image to delete.
     * @throws IOException If an I/O error occurs during deletion.
     */
    @Override
    public void deleteImage(String imagePath) throws IOException {
        Path filePath = loadImage(imagePath);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    /**
     * Resolves the public URL for a given image path.
     * Assumes images are served from a web server configured to serve files from the upload directory.
     *
     * @param imagePath The relative path (filename) of the image.
     * @return The publicly accessible URL for the image.
     */
    @Override
    public String getImageUrl(String imagePath) {
        // Ensure baseUrl ends with a slash
        String formattedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        return formattedBaseUrl + imagePath;
    }
}