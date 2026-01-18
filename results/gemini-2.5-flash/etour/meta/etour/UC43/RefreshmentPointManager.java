package com.restaurant.manager;

import com.restaurant.refreshment.RefreshmentPoint;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Manages RefreshmentPoint data, including uploading, modifying, and storing.
 * Handles data validation and simulates database interactions.
 */
public class RefreshmentPointManager {
    // Simulates a database or data store for refreshment points
    private Map<String, RefreshmentPoint> refreshmentPoints;

    public RefreshmentPointManager() {
        this.refreshmentPoints = new HashMap<>();
        // Initialize with some dummy data for demonstration
        refreshmentPoints.put("RP001", new RefreshmentPoint("RP001", "Central Cafe", "Main Street 101", "Cafe", "info@centralcafe.com"));
        refreshmentPoints.put("RP002", new RefreshmentPoint("RP002", "Park Bar", "Park Avenue 202", "Bar", "contact@parkbar.net"));
    }

    /**
     * Retrieves a RefreshmentPoint by its ID.
     * Simulates "uploading data point Refreshments and displays them in a form."
     *
     * @param id The ID of the refreshment point to retrieve.
     * @return The RefreshmentPoint object if found, null otherwise.
     */
    public RefreshmentPoint getRefreshmentPoint(String id) {
        System.out.println("System: Attempting to retrieve Refreshment Point with ID: " + id);
        RefreshmentPoint point = refreshmentPoints.get(id);
        if (point != null) {
            System.out.println("System: Found Refreshment Point: " + point.getName());
            // Return a deep copy to prevent direct modification of stored object
            return point.deepCopy();
        } else {
            System.out.println("System: Refreshment Point with ID " + id + " not found.");
            return null;
        }
    }

    /**
     * Validates the data for a RefreshmentPoint.
     * This method implements the "Verify the data entered" step.
     *
     * @param point The RefreshmentPoint object with potentially new data.
     * @return A validation message if data is invalid, null if valid.
     */
    public String validateRefreshmentPointData(RefreshmentPoint point) {
        if (point == null) {
            return "Refreshment Point data cannot be null.";
        }
        if (point.getId() == null || point.getId().trim().isEmpty()) {
            return "Refreshment Point ID cannot be empty.";
        }
        if (point.getName() == null || point.getName().trim().isEmpty()) {
            return "Refreshment Point Name cannot be empty.";
        }
        if (point.getLocation() == null || point.getLocation().trim().isEmpty()) {
            return "Refreshment Point Location cannot be empty.";
        }
        if (point.getType() == null || point.getType().trim().isEmpty()) {
            return "Refreshment Point Type cannot be empty.";
        }
        // Basic email validation for contact info if it looks like an email
        if (point.getContactInfo() != null && !point.getContactInfo().trim().isEmpty()) {
            if (point.getContactInfo().contains("@")) { // Simple check for email
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);
                if (!pattern.matcher(point.getContactInfo()).matches()) {
                    return "Invalid Contact Info format (email expected).";
                }
            }
            // Could add phone number validation here too
        } else {
            return "Refreshment Point Contact Info cannot be empty.";
        }

        return null; // Data is valid
    }

    /**
     * Stores the modified data of the refreshment point.
     * This method implements the "Stores the modified data of the point of rest." step.
     *
     * @param modifiedPoint The RefreshmentPoint object with the updated data.
     * @return true if the data was successfully stored, false otherwise (e.g., ID not found).
     */
    public boolean storeModifiedRefreshmentPoint(RefreshmentPoint modifiedPoint) {
        if (modifiedPoint == null || modifiedPoint.getId() == null) {
            System.err.println("Error: Cannot store null or ID-less refreshment point.");
            return false;
        }

        if (refreshmentPoints.containsKey(modifiedPoint.getId())) {
            refreshmentPoints.put(modifiedPoint.getId(), modifiedPoint);
            System.out.println("System: Successfully stored modified data for Refreshment Point ID: " + modifiedPoint.getId());
            return true;
        } else {
            System.err.println("Error: Refreshment Point with ID " + modifiedPoint.getId() + " does not exist. Cannot store.");
            return false;
        }
    }

    /**
     * Simulates a server connection interruption.
     * @throws RuntimeException always throws to simulate ETOUR.
     */
    public void simulateConnectionInterruption() {
        throw new RuntimeException("ETOUR: Connection to server interrupted.");
    }
}