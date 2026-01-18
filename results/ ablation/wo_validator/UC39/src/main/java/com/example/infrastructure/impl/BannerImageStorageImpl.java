package com.example.infrastructure.impl;

import com.example.infrastructure.BannerImageStorage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * Adapter that stores banner images on the local file system.
 * In a real application, this would be replaced with cloud storage (e.g., S3).
 */
public class BannerImageStorageImpl implements BannerImageStorage {
    private final String storageBasePath = "/tmp/banner-images/"; // Example path

    public BannerImageStorageImpl() {
        try {
            Files.createDirectories(Path.of(storageBasePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String storeImage(byte[] imageData, String contentType) {
        String extension = getExtensionFromContentType(contentType);
        String fileName = UUID.randomUUID().toString() + extension;
        Path filePath = Path.of(storageBasePath + fileName);
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(imageData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        // Return a URL‑like identifier (in reality this would be a full URL)
        return "/images/" + fileName;
    }

    private String getExtensionFromContentType(String contentType) {
        if (contentType == null) return ".bin";
        switch (contentType) {
            case "image/jpeg": return ".jpg";
            case "image/png": return ".png";
            case "image/gif": return ".gif";
            default: return ".img";
        }
    }
}