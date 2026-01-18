/**
 * Represents an academic year with a start and end year.
 * This class provides methods to access the start and end years of an academic period.
 */
public class AcademicYear {
    private final int startYear;
    private final int endYear;

    /**
     * Constructs a new AcademicYear object.
     *
     * @param startYear The starting year of the academic period.
     * @param endYear   The ending year of the academic period.
     * @throws IllegalArgumentException if startYear is greater than or equal to endYear.
     */
    public AcademicYear(int startYear, int endYear) {
        if (startYear >= endYear) {
            throw new IllegalArgumentException("Start year must be less than end year.");
        }
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Returns the starting year of the academic period.
     *
     * @return The start year.
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * Returns the ending year of the academic period.
     *
     * @return The end year.
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * Returns a string representation of the academic year in the format "YYYY-YYYY".
     *
     * @return A string representing the academic year.
     */
    @Override
    public String toString() {
        return startYear + "-" + endYear;
    }

    /**
     * Compares this AcademicYear object to the specified object. The result is true if and only if
     * the argument is not null and is an AcademicYear object that represents the same start and end year.
     *
     * @param o The object to compare this AcademicYear against.
     * @return true if the given object represents an AcademicYear equivalent to this academic year, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return startYear == that.startYear && endYear == that.endYear;
    }

    /**
     * Returns a hash code for this AcademicYear object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = startYear;
        result = 31 * result + endYear;
        return result;
    }
}