package com.restaurant.refreshment;

/**
 * Represents a refreshment point with its associated data.
 * This class holds information such as the point's ID, name, and location.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String type; // e.g., "Cafe", "Bar", "Kiosk"
    private String contactInfo; // e.g., phone number, email

    /**
     * Constructs a new RefreshmentPoint.
     *
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param location The physical location of the refreshment point.
     * @param type The type of refreshment point (e.g., Cafe, Bar).
     * @param contactInfo Contact information for the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String location, String type, String contactInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.contactInfo = contactInfo;
    }

    /**
     * Gets the unique identifier of the refreshment point.
     * @return The ID of the refreshment point.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the refreshment point.
     * @param id The new ID for the refreshment point.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the refreshment point.
     * @return The name of the refreshment point.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the refreshment point.
     * @param name The new name for the refreshment point.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the refreshment point.
     * @return The location of the refreshment point.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the refreshment point.
     * @param location The new location for the refreshment point.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of the refreshment point.
     * @return The type of the refreshment point.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the refreshment point.
     * @param type The new type for the refreshment point.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the contact information of the refreshment point.
     * @return The contact information of the refreshment point.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the contact information of the refreshment point.
     * @param contactInfo The new contact information for the refreshment point.
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               ", type='" + type + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               '}';
    }

    /**
     * Creates a deep copy of this RefreshmentPoint object.
     * @return A new RefreshmentPoint object with the same data.
     */
    public RefreshmentPoint deepCopy() {
        return new RefreshmentPoint(this.id, this.name, this.location, this.type, this.contactInfo);
    }
}