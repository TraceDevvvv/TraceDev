'''
Represents a single point of rest in the system.
This is a simple model class (POJO) that holds data about a rest point.
'''
package com.chatdev.ricercapuntidiristoro.model;
/**
 * Represents a single point of rest in the system.
 * This is a simple model class (POJO) that holds data about a rest point.
 */
public class RestPoint {
    private String id;
    private String name;
    private String location;
    private String type; // e.g., "Restaurant", "Cafe", "Hotel", "Picnic Area", "Service Station"
    /**
     * Constructs a new RestPoint with the specified details.
     *
     * @param id       Unique identifier for the rest point.
     * @param name     The name of the rest point.
     * @param location The geographical location or address of the rest point.
     * @param type     The category or type of the rest point.
     */
    public RestPoint(String id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }
    /**
     * Gets the unique identifier of the rest point.
     * @return The ID of the rest point.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the name of the rest point.
     * @return The name of the rest point.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the location of the rest point.
     * @return The location string.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Gets the type of the rest point.
     * @return The type string.
     */
    public String getType() {
        return type;
    }
    /**
     * Returns a string representation of the RestPoint, which is useful for displaying
     * the object directly in UI components like JList or JComboBox.
     *
     * @return A formatted string showing the rest point's name, type, and location.
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, type, location);
    }
}