package com.example.infrastructure;

/**
 * Client for interacting with cloud storage.
 */
public class CloudStorage {
    public String upload(byte[] data, String fileName) {
        // Simulate upload and return a fake key
        return "banners/" + System.currentTimeMillis() + "_" + fileName;
    }
    
    public void delete(String imageKey) {
        // Simulate deletion
        System.out.println("Deleted image: " + imageKey);
    }
}