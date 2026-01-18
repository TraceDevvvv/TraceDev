'''
/**
 * Represents a single teaching (e.g., a course or subject) with a unique identifier and a name.
 * This is a simple Plain Old Java Object (POJO) used throughout the application to manage
 * educational offerings and their assignments to physical or virtual addresses.
 */
'''
public class Teaching {
    private String id;
    private String name;
    /**
     * Constructs a new Teaching object.
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     */
    public Teaching(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Gets the ID of the teaching.
     * @return The teaching ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the name of the teaching.
     * @return The teaching name.
     */
    public String getName() {
        return name;
    }
    /**
     * Overrides the toString method to provide a friendly representation (the name)
     * for display in GUI components like JList.
     * @return The name of the teaching.
     */
    @Override
    public String toString() {
        return name;
    }
    /**
     * Overrides the equals method to compare Teaching objects based on their ID.
     * This is crucial for correctly handling Set operations and list comparisons.
     * @param obj The object to compare with.
     * @return true if the IDs are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teaching teaching = (Teaching) obj;
        return id != null ? id.equals(teaching.id) : teaching.id == null;
    }
    /**
     * Overrides the hashCode method, consistent with equals, based on the ID.
     * @return The hash code for this Teaching object.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}