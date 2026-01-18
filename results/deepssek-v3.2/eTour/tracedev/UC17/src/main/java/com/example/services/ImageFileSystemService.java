package com.example.serv;

import com.example.domain.ImageMetadata;
import com.example.exceptions.ConnectionException;
import java.util.Arrays;
import java.util.List;

/**
 * Service for interacting with the image file system.
 */
public class ImageFileSystemService {
    public ImageMetadata getImageMetadata(String imagePath) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while getting image metadata");
        }
        
        // Return mock metadata based on image path
        return new ImageMetadata(imagePath, 800, 600, 1024 * 1024L, "jpg");
    }

    public String bookmarkImage(String sourcePath, String bannerId) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while bookmarking image");
        }
        
        // Simulate bookmarking by creating a new path
        return "bookmarked_" + bannerId + "_" + sourcePath.substring(sourcePath.lastIndexOf('/') + 1);
    }

    public List<String> getAvailableImages() {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while getting available images");
        }
        
        // Return mock list of available images
        return Arrays.asList(
            "/images/banner1.jpg",
            "/images/banner2.png",
            "/images/banner3.gif",
            "/images/advert1.jpg",
            "/images/advert2.png"
        );
    }
}