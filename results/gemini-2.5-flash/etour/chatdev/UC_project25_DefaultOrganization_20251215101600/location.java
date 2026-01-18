/**
 * Represents a geographical location.
 */
public class Location {
    private int id;
    private String name;
    /**
     * Constructs a new Location object.
     * @param id The unique identifier for the location.
     * @param name The name of the location.
     */
    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Gets the ID of the location.
     * @return The location ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the name of the location.
     * @return The location name.
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the toString method to return the location name,
     * which is suitable for display in UI components like JComboBox.
     * @return The name of the location.
     */
    @Override
    public String toString() {
        return name;
    }
}