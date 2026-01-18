/*
 * Represents an academic year.
 * This class is a simple data structure to hold the year information
 * and overrides toString for proper display in GUI components.
 */
class AcademicYear {
    private String year;
    /**
     * Constructs a new AcademicYear object.
     * @param year The string representation of the academic year (e.g., "2023-2024").
     */
    public AcademicYear(String year) {
        this.year = year;
    }
    /**
     * Returns the academic year string.
     * @return The academic year string.
     */
    public String getYear() {
        return year;
    }
    /**
     * Provides a string representation of the academic year, suitable for display.
     * @return The academic year string.
     */
    @Override
    public String toString() {
        return year;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return year.equals(that.year);
    }
    @Override
    public int hashCode() {
        return year.hashCode();
    }
}