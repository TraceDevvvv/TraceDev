'''
Represents a refreshment point (punto di ristoro) in the system.
Each refreshment point has a unique ID and a name.
'''
package model;
public class RefreshmentPoint {
    private int id;
    private String name;
    /**
     * Constructs a new RefreshmentPoint.
     *
     * @param id   The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     */
    public RefreshmentPoint(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Gets the ID of the refreshment point.
     *
     * @return The refreshment point's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the name of the refreshment point.
     *
     * @return The refreshment point's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns a string representation of the RefreshmentPoint,
     * which is its name. This is useful for displaying objects
     * directly in Swing components like JComboBox.
     *
     * @return The name of the refreshment point.
     */
    @Override
    public String toString() {
        return name;
    }
}