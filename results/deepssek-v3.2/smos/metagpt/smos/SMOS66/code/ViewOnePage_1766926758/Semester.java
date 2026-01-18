import java.util.Objects;

/**
 * Data model class representing a semester (quadrimestre).
 * A semester is part of an academic year (e.g., "First Quadrimestre", "Second Quadrimestre").
 */
public class Semester {
    private final String semesterId;
    private final String name;
    private final AcademicYear academicYear;
    private final int order; // 1 for first semester, 2 for second, etc.

    /**
     * Constructor for Semester.
     * @param semesterId Unique identifier for the semester.
     * @param name Name of the semester (e.g., "First Quadrimestre", "Second Quadrimestre").
     * @param academicYear The academic year this semester belongs to.
     * @param order Numerical order of the semester within the academic year (starting from 1).
     * @throws IllegalArgumentException if order is less than 1.
     */
    public Semester(String semesterId, String name, AcademicYear academicYear, int order) {
        this.semesterId = Objects.requireNonNull(semesterId, "Semester ID cannot be null");
        this.name = Objects.requireNonNull(name, "Semester name cannot be null");
        this.academicYear = Objects.requireNonNull(academicYear, "Academic year cannot be null");
        
        if (order < 1) {
            throw new IllegalArgumentException("Semester order must be at least 1, got: " + order);
        }
        this.order = order;
    }

    /**
     * Gets the semester's unique ID.
     * @return Semester ID.
     */
    public String getSemesterId() {
        return semesterId;
    }

    /**
     * Gets the name of the semester.
     * @return Semester name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the academic year this semester belongs to.
     * @return AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Gets the numerical order of the semester within the academic year.
     * @return Order (starting from 1).
     */
    public int getOrder() {
        return order;
    }

    /**
     * Returns a string representation of the semester.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Semester{" +
                "semesterId='" + semesterId + '\'' +
                ", name='" + name + '\'' +
                ", academicYear=" + academicYear.getDisplayName() +
                ", order=" + order +
                '}';
    }

    /**
     * Checks equality based on semesterId.
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterId.equals(semester.semesterId);
    }

    /**
     * Hash code based on semesterId.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(semesterId);
    }
}