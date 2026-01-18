package ViewClassListTeacher_1766400150;

import java.util.Objects;

/**
 * Represents an academic year in the system.
 * Each academic year has a unique identifier and a display name.
 */
public class AcademicYear {
    private final String id;
    private final String name;

    /**
     * Constructs a new AcademicYear.
     *
     * @param id   The unique identifier for the academic year (e.g., "2023-2024").
     * @param name The display name for the academic year (e.g., "Academic Year 2023-2024").
     */
    public AcademicYear(String id, String name) {
        // Validate input to ensure id and name are not null or empty
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("AcademicYear ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("AcademicYear name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the academic year.
     *
     * @return The ID of the academic year.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the display name of the academic year.
     *
     * @return The name of the academic year.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this AcademicYear object with another object for equality.
     * Two AcademicYear objects are considered equal if their IDs are the same.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the AcademicYear object.
     * The hash code is based on the ID of the academic year.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the AcademicYear object.
     *
     * @return A string in the format "AcademicYear{id='...', name='...'}".
     */
    @Override
    public String toString() {
        return "AcademicYear{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}