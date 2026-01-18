package com.etour.touristaccount;

/**
 * Represents a tourist account with properties like ID, name, and active status.
 * This class encapsulates the data for a single tourist.
 */
public class Tourist {
    private String touristId;
    private String name;
    private boolean isActive;

    /**
     * Constructs a new Tourist object.
     *
     * @param touristId The unique identifier for the tourist.
     * @param name The name of the tourist.
     * @param isActive The initial active status of the tourist account.
     */
    public Tourist(String touristId, String name, boolean isActive) {
        // Validate touristId to ensure it's not null or empty
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty.");
        }
        // Validate name to ensure it's not null or empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist name cannot be null or empty.");
        }
        this.touristId = touristId;
        this.name = name;
        this.isActive = isActive;
    }

    /**
     * Returns the unique identifier of the tourist.
     *
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }

    /**
     * Returns the name of the tourist.
     *
     * @return The tourist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the tourist account is currently active.
     *
     * @return true if the account is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the tourist account.
     *
     * @param active The new active status (true for active, false for inactive).
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Provides a string representation of the Tourist object.
     *
     * @return A string containing the tourist's ID, name, and active status.
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "touristId='" + touristId + '\'' +
               ", name='" + name + '\'' +
               ", isActive=" + isActive +
               '}';
    }
}