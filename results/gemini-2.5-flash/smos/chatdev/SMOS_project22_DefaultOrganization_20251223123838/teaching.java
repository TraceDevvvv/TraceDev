/**
 * This class represents a Teaching object with properties like id, title, and description.
 * It serves as a simple Plain Old Java Object (POJO) for data modeling.
 */
public class Teaching {
    private int id;
    private String title;
    private String description;
    /**
     * Constructs a new Teaching object.
     * @param id The unique identifier for the teaching.
     * @param title The title of the teaching.
     * @param description A brief description of the teaching.
     */
    public Teaching(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    /**
     * Returns the ID of the teaching.
     * @return The teaching ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the title of the teaching.
     * @return The teaching title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Returns the description of the teaching.
     * @return The teaching description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Provides a string representation of the Teaching object.
     * @return A string containing the teaching's ID, title, and description.
     */
    @Override
    public String toString() {
        return "Teaching{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}