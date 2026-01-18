'''
Service class responsible for business logic related to banners and points of rest.
It manages data access (simulated in-memory), image validation, and banner insertion.
'''
package com.chatdev.bannerapp.service;
import com.chatdev.bannerapp.model.Banner;
import com.chatdev.bannerapp.model.PointOfRest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
public class BannerService {
    // Simulates a data store for PointsOfRest
    private Map<String, PointOfRest> dataStore;
    // Counter for generating unique banner IDs
    private AtomicLong bannerIdCounter = new AtomicLong(1);
    /**
     * Constructs a new BannerService and initializes a mock data store with some PointOfRest objects.
     */
    public BannerService() {
        dataStore = new HashMap<>();
        // Populate with some mock data
        PointOfRest p1 = new PointOfRest("POR001", "Central Cafe", 3);
        p1.addBanner(new Banner("B001", "path/to/img1.jpg", true));
        p1.addBanner(new Banner("B002", "path/to/img2.png", true));
        dataStore.put(p1.getId(), p1);
        PointOfRest p2 = new PointOfRest("POR002", "Riverside Restaurant", 2);
        p2.addBanner(new Banner("B003", "path/to/img3.gif", true));
        dataStore.put(p2.getId(), p2);
        PointOfRest p3 = new PointOfRest("POR003", "Mountain View Diner", 5);
        dataStore.put(p3.getId(), p3); // No banners initially
    }
    /**
     * Retrieves a list of all Points of Rest from the data store.
     * @return A list of PointOfRest objects.
     * @throws RuntimeException if a simulated connection error occurs.
     */
    public List<PointOfRest> getPointsOfRest() {
        // Simulate a potential connection error (ETOUR) for demonstration
        if (Math.random() < 0.05) { // 5% chance of connection error
            throw new RuntimeException("Interruption of the connection to the server (ETOUR).");
        }
        return new ArrayList<>(dataStore.values());
    }
    /**
     * Simulates the validation of an image based on its path.
     * In a real application, this would involve checking file type, dimensions, content, etc.
     * For this example, it checks if the path contains "invalid" or "too_large".
     *
     * @param imagePath The file path of the image to validate.
     * @return True if the image is considered valid, false otherwise.
     */
    public boolean validateImage(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            return false;
        }
        String lowerCasePath = imagePath.toLowerCase();
        // Simulate some validation rules
        if (lowerCasePath.contains("invalid") || lowerCasePath.contains("too_large")) {
            return false; // Example of invalid image characteristic
        }
        // Assume all other images are valid for this simulation
        return true;
    }
    /**
     * Attempts to insert a new banner for a given PointOfRest.
     * Performs checks for maximum banner limit and image validity.
     *
     * @param selectedPoint The PointOfRest to which the banner should be added.
     * @param imagePath The file path of the image to be used for the banner.
     * @return A BannerInsertionResult enum indicating the outcome of the insertion attempt.
     * @throws RuntimeException if a simulated connection error occurs.
     */
    public BannerInsertionResult insertBanner(PointOfRest selectedPoint, String imagePath) {
        // Simulate a potential connection error (ETOUR) for demonstration
        if (Math.random() < 0.05) { // 5% chance of connection error
            throw new RuntimeException("Interruption of the connection to the server (ETOUR).");
        }
        if (selectedPoint == null) {
            return BannerInsertionResult.NO_POINT_SELECTED;
        }
        // 4. Check the characteristics of the inserted
        boolean isValid = validateImage(imagePath);
        if (!isValid) {
            // In the event that the inserted image is not valid, enable the use case Errored.
            return BannerInsertionResult.INVALID_IMAGE;
        }
        // Check that the number of banners did not exceed the maximum point of the restaurant
        if (!selectedPoint.canAddBanner()) {
            // The point of eating has already entered the maximum number of banners allowed.
            return BannerInsertionResult.MAX_BANNERS_REACHED;
        }
        // If all checks pass, proceed with insertion
        String newBannerId = "B" + bannerIdCounter.getAndIncrement();
        Banner newBanner = new Banner(newBannerId, imagePath, true); // Image is valid as checked above
        if (selectedPoint.addBanner(newBanner)) {
            // Remember the banners associated with the selected point of rest (dataStore implicitly updated)
            return BannerInsertionResult.SUCCESS;
        } else {
            // This case should ideally not be reached if canAddBanner() was checked, but as a safeguard.
            return BannerInsertionResult.UNKNOWN_ERROR;
        }
    }
}