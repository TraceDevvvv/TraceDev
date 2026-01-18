/**
 * This class represents an Academic Year.
 * It stores the year identifier which can be used to filter classes.
 */
package model;
import java.util.Objects; // Add this import
public class AcademicYear {
    private String id; // e.g., "2023-2024"
    private String displayLabel; // e.g., "Academic Year 2023-2024"
    /**
     * Constructs a new AcademicYear object.
     * @param id The unique identifier for the academic year (e.g., "2023-2024").
     * @param displayLabel A more descriptive label for display purposes.
     */
    public AcademicYear(String id, String displayLabel) {
        this.id = id;
        this.displayLabel = displayLabel;
    }
    /**
     * Returns the ID of the academic year.
     * @return The academic year's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the academic year.
     * @param id The new ID for the academic year.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the display label of the academic year.
     * @return The academic year's display label.
     */
    public String getDisplayLabel() {
        return displayLabel;
    }
    /**
     * Sets the display label of the academic year.
     * @param displayLabel The new display label for the academic year.
     */
    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }
    /**
     * Overrides the toString method to display the label in GUI components.
     * @return The display label of the academic year.
     */
    @Override
    public String toString() {
        return displayLabel;
    }
    /**
     * Compares this AcademicYear object to another object for equality.
     * Two AcademicYear objects are considered equal if their 'id' fields are identical.
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
     * The hash code is based on the 'id' field.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}