package EntitySearch_1766409602;

/**
 * Represents a Course (or Class) entity in the system.
 * Implements the Entity interface to allow it to be searched.
 */
public class Course implements Entity {
    private String id;
    private String name;
    private String description;

    /**
     * Constructs a new Course instance.
     *
     * @param id A unique identifier for the course.
     * @param name The name of the course.
     * @param description A brief description of the course content.
     */
    public Course(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the unique identifier of the course.
     *
     * @return The course ID.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns a string containing all searchable content for the course.
     * This includes its ID, name, and description.
     *
     * @return A concatenated string of searchable attributes.
     */
    @Override
    public String getSearchableContent() {
        // Convert to lowercase for case-insensitive searching
        return (id + " " + name + " " + description).toLowerCase();
    }

    /**
     * Returns the display name of the course, which is its name.
     *
     * @return The name of the course.
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns the type of the entity, which is "Course".
     *
     * @return The string "Course".
     */
    @Override
    public String getType() {
        return "Course";
    }

    /**
     * Provides a string representation of the Course object for debugging and display.
     *
     * @return A formatted string including the course type, ID, name, and description.
     */
    @Override
    public String toString() {
        return String.format("Type: %s, ID: %s, Name: %s, Description: %s",
                             getType(), id, name, description);
    }
}