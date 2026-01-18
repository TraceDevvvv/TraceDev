import java.time.Year;
import java.util.Objects;

/**
 * Data model class representing an academic year.
 * An academic year typically spans two calendar years (e.g., 2023-2024).
 */
public class AcademicYear {
    private final String yearId;
    private final String displayName;
    private final Year startYear;
    private final Year endYear;

    /**
     * Constructor for AcademicYear.
     * @param yearId Unique identifier for the academic year.
     * @param startYear The starting year of the academic year.
     * @param endYear The ending year of the academic year.
     * @throws IllegalArgumentException if startYear is not exactly one year before endYear.
     */
    public AcademicYear(String yearId, Year startYear, Year endYear) {
        this.yearId = Objects.requireNonNull(yearId, "Year ID cannot be null");
        this.startYear = Objects.requireNonNull(startYear, "Start year cannot be null");
        this.endYear = Objects.requireNonNull(endYear, "End year cannot be null");
        
        // Validate that endYear is exactly one year after startYear
        if (!endYear.equals(startYear.plusYears(1))) {
            throw new IllegalArgumentException(
                "Academic year must span exactly one year: startYear=" + startYear + ", endYear=" + endYear
            );
        }
        
        this.displayName = startYear + "-" + endYear;
    }

    /**
     * Gets the academic year's unique ID.
     * @return Year ID.
     */
    public String getYearId() {
        return yearId;
    }

    /**
     * Gets the display name of the academic year (e.g., "2023-2024").
     * @return Display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the starting year of the academic year.
     * @return Start year.
     */
    public Year getStartYear() {
        return startYear;
    }

    /**
     * Gets the ending year of the academic year.
     * @return End year.
     */
    public Year getEndYear() {
        return endYear;
    }

    /**
     * Returns a string representation of the academic year.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "AcademicYear{" +
                "yearId='" + yearId + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }

    /**
     * Checks equality based on yearId.
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return yearId.equals(that.yearId);
    }

    /**
     * Hash code based on yearId.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(yearId);
    }
}