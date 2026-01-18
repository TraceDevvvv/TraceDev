package com.agency.touristmanager;
/**
 * Represents a tourist with an account that can be active or inactive.
 */
public class Tourist {
    private String id;
    private String name;
    private boolean isActive;
    /**
     * Constructs a Tourist object.
     * @param id The unique identifier of the tourist.
     * @param name The full name of the tourist.
     * @param isActive The initial active status of the account.
     */
    public Tourist(String id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
    /**
     * Returns the tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the tourist's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the active status of the account.
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * Sets the active status of the account.
     * @param isActive The new active status.
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    /**
     * Returns a string representation of the tourist.
     */
    @Override
    public String toString() {
        return name + " (" + id + ") - " + (isActive ? "Active" : "Inactive");
    }
}