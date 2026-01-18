package ViewClassListTeacher_1766400150;

import java.util.Objects;

/**
 * Represents a Professor (teacher) in the system.
 * Each professor has a unique identifier and a name.
 */
public class Professor {
    private final String id;
    private final String name;

    /**
     * Constructs a new Professor.
     *
     * @param id   The unique identifier for the professor (e.g., "P001").
     * @param name The full name of the professor (e.g., "Dr. Alice Smith").
     */
    public Professor(String id, String name) {
        // Validate input to ensure id and name are not null or empty
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Professor ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Professor name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the professor.
     *
     * @return The ID of the professor.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the professor.
     *
     * @return The name of the professor.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this Professor object with another object for equality.
     * Two Professor objects are considered equal if their IDs are the same.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(id, professor.id);
    }

    /**
     * Returns a hash code value for the Professor object.
     * The hash code is based on the ID of the professor.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the Professor object.
     *
     * @return A string in the format "Professor{id='...', name='...'}".
     */
    @Override
    public String toString() {
        return "Professor{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}