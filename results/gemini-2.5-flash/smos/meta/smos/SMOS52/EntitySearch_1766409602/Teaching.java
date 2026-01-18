package EntitySearch_1766409602;

/**
 * Represents a Teaching (or Lecture/Section) entity in the system.
 * Implements the Entity interface to allow it to be searched.
 */
public class Teaching implements Entity {
    private String id;
    private String name;
    private String instructor;
    private String schedule;

    /**
     * Constructs a new Teaching instance.
     *
     * @param id A unique identifier for the teaching instance (e.g., T-CS101-F23).
     * @param name The name or title of the teaching instance (e.g., "Fall 2023 - CS101").
     * @param instructor The name of the instructor teaching this course.
     * @param schedule The schedule details (e.g., "Monday, Wednesday, Friday 10:00 AM").
     */
    public Teaching(String id, String name, String instructor, String schedule) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.schedule = schedule;
    }

    /**
     * Returns the unique identifier of the teaching instance.
     *
     * @return The teaching ID.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns a string containing all searchable content for the teaching instance.
     * This includes its ID, name, instructor, and schedule.
     *
     * @return A concatenated string of searchable attributes.
     */
    @Override
    public String getSearchableContent() {
        // Convert to lowercase for case-insensitive searching
        return (id + " " + name + " " + instructor + " " + schedule).toLowerCase();
    }

    /**
     * Returns the display name of the teaching instance, which is its name.
     *
     * @return The name of the teaching instance.
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Returns the type of the entity, which is "Teaching".
     *
     * @return The string "Teaching".
     */
    @Override
    public String getType() {
        return "Teaching";
    }

    /**
     * Provides a string representation of the Teaching object for debugging and display.
     *
     * @return A formatted string including the teaching type, ID, name, instructor, and schedule.
     */
    @Override
    public String toString() {
        return String.format("Type: %s, ID: %s, Name: %s, Instructor: %s, Schedule: %s",
                             getType(), id, name, instructor, schedule);
    }
}