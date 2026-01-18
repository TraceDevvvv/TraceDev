'''
Represents a Tourist account in the system.
This class holds basic information about a tourist,
such as their unique ID and name.
'''
public class Tourist {
    private String id;
    private String name;
    /**
     * Constructs a new Tourist object.
     * @param id The unique identifier for the tourist.
     * @param name The name of the tourist.
     */
    public Tourist(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Gets the unique ID of the tourist.
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the name of the tourist.
     * @return The tourist's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the toString method to provide a human-readable representation
     * of the Tourist object, which is useful for displaying in UI components like JList.
     * @return A string representation of the tourist (ID - Name).
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
    /**
     * Overrides the equals method to compare Tourist objects based on their ID.
     * This is crucial for collections that rely on equality checks, such as HashMaps or HashSet.
     * @param o The object to compare with.
     * @return true if the objects are equal (have the same ID), false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return id.equals(tourist.id);
    }
    /**
     * Overrides the hashCode method, consistent with the overridden equals method.
     * This is necessary for correct functionality in hash-based collections.
     * @return A hash code based on the tourist's ID.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}