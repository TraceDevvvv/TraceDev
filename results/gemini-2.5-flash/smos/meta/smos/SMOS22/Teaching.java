import java.util.Objects;

/**
 * Represents a single teaching item in the archive.
 * This class holds details about a teaching, such as its unique identifier,
 * name, and the teacher associated with it.
 */
public class Teaching {
    private final String id;
    private final String name;
    private final String teacher;

    /**
     * Constructs a new Teaching instance.
     *
     * @param id The unique identifier for the teaching. Must not be null or empty.
     * @param name The name or title of the teaching. Must not be null or empty.
     * @param teacher The name of the teacher for this teaching. Must not be null or empty.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Teaching(String id, String name, String teacher) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
        if (teacher == null || teacher.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * Returns the unique identifier of the teaching.
     *
     * @return The teaching ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name or title of the teaching.
     *
     * @return The teaching name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the teacher for this teaching.
     *
     * @return The teacher's name.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Provides a string representation of the Teaching object,
     * useful for displaying teaching details.
     *
     * @return A formatted string containing the teaching's ID, name, and teacher.
     */
    @Override
    public String toString() {
        return "Teaching [ID: " + id + ", Name: '" + name + "', Teacher: '" + teacher + "']";
    }

    /**
     * Compares this Teaching object with another object for equality.
     * Two Teaching objects are considered equal if they have the same ID.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(id, teaching.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the teaching's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}