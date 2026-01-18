'''
Represents a "point of rest" (e.g., a restaurant or hotel)
for which convention histories are viewed.
This class serves as a data model for points of rest.
'''
package com.chatdev.viscon; // Using a package for better organization
import java.util.Objects;
/**
 * Represents a "point of rest" (e.g., a restaurant, hotel, or venue)
 * for which convention historical data can be retrieved.
 */
public class PointOfRest {
    private String id;
    private String name;
    /**
     * Constructs a new PointOfRest object.
     *
     * @param id The unique identifier for the point of rest.
     * @param name The display name of the point of rest.
     */
    public PointOfRest(String id, String name) {
        this.id = id;
        this.name = name;
    }
    // --- Getters for PointOfRest properties ---
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    /**
     * Provides a string representation of the PointOfRest object.
     * This is useful for displaying in UI components like JComboBox.
     *
     * @return The name of the point of rest.
     */
    @Override
    public String toString() {
        return name; // Display name directly in UI components
    }
    /**
     * Compares this PointOfRest object with another for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfRest that = (PointOfRest) o;
        return Objects.equals(id, that.id);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code based on the point of rest's ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}