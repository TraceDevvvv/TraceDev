package domain;

/**
 * Represents an academic year in the domain model.
 */
public class AcademicYear {
    private String id;
    private int yearNumber;

    /**
     * Constructs a new AcademicYear.
     *
     * @param id The unique identifier for the academic year.
     * @param yearNumber The numerical representation of the academic year (e.g., 2023 for 2023-2024).
     */
    public AcademicYear(String id, int yearNumber) {
        this.id = id;
        this.yearNumber = yearNumber;
    }

    /**
     * Gets the unique identifier of the academic year.
     *
     * @return The academic year's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the numerical representation of the academic year.
     *
     * @return The academic year's number.
     */
    public int getYearNumber() {
        return yearNumber;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
               "id='" + id + '\'' +
               ", yearNumber=" + yearNumber +
               '}';
    }
}