'''
Represents a Point of Interest (e.g., a restaurant, shop, museum)
which can have associated banner ads.
'''
package models;
/**
 * Represents a Point of Interest (e.g., a restaurant, shop, museum)
 * which can have associated banner ads.
 */
public class PointOfInterest {
    private int id;       // Unique identifier for the Point of Interest
    private String name;  // Name of the Point of Interest
    /**
     * Constructs a new PointOfInterest.
     *
     * @param id   The unique ID of the point of interest.
     * @param name The name of the point of interest.
     */
    public PointOfInterest(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the ID of the point of interest.
     *
     * @return The integer ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the name of the point of interest.
     *
     * @return The string name.
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the toString method to provide a user-friendly representation
     * for display in GUI components like JComboBox.
     *
     * @return The name of the point of interest.
     */
    @Override
    public String toString() {
        return name;
    }
}