package com.example.infrastructure;

/**
 * Port interface for storing banner images and returning the stored URL.
 */
public interface BannerImageStorage {
    String storeImage(byte[] imageData, String contentType);
}