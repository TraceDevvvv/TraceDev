/**
 * Represents a teaching entity with an ID, name, and description.
 * This is a simple POJO (Plain Old Java Object).
 */
public class Teaching {
    private String id;
    private String name;
    private String description;
    /**
     * Constructs a new Teaching object.
     *
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param description A brief description of the teaching.
     */
    public Teaching(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * Gets the description of the teaching.
     * @return The teaching description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns a string representation of the teaching, primarily its name,
     * useful for displaying in lists.
     * @return The name of the teaching.
     */
    @Override
    public String toString() {
        return name;
    }
}