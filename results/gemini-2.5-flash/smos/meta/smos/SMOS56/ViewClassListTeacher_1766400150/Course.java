package ViewClassListTeacher_1766400150;

import java.util.Objects;

/**
 * Represents a course or class offered in a specific academic year.
 * Each course has a unique identifier, a name, and is linked to an AcademicYear.
 */
public class Course {
    private final String id;
    private final String name;
    private final AcademicYear academicYear;

    /**
     * Constructs a new Course.
     *
     * @param id           The unique identifier for the course (e.g., "CS101").
     * @param name         The display name for the course (e.g., "Introduction to Computer Science").
     * @param academicYear The AcademicYear object to which this course belongs.
     */
    public Course(String id, String name, AcademicYear academicYear) {
        // Validate input to ensure id, name, and academicYear are not null or empty/invalid
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null for a Course.");
        }

        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
    }

    /**
     * Returns the unique identifier of the course.
     *
     * @return The ID of the course.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the display name of the course.
     *
     * @return The name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the academic year to which this course belongs.
     *
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Compares this Course object with another object for equality.
     * Two Course objects are considered equal if their IDs and academic years are the same.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        // A course is uniquely identified by its ID within a specific academic year.
        return Objects.equals(id, course.id) &&
               Objects.equals(academicYear, course.academicYear);
    }

    /**
     * Returns a hash code value for the Course object.
     * The hash code is based on the ID and academic year of the course.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, academicYear);
    }

    /**
     * Returns a string representation of the Course object.
     *
     * @return A string in the format "Course{id='...', name='...', academicYear=...}".
     */
    @Override
    public String toString() {
        return "Course{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", academicYear=" + academicYear.getName() + // Display academic year name for readability
               '}';
    }
}