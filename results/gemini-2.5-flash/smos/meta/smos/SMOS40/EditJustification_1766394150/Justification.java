import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Justification entity in the system.
 * This class holds details about a justification, primarily its unique identifier
 * and the date associated with it.
 */
public class Justification {
    private String id; // Unique identifier for the justification
    private LocalDate justificationDate; // The date of the justification

    /**
     * Constructs a new Justification instance.
     *
     * @param id The unique identifier for this justification. Must not be null or empty.
     * @param justificationDate The date associated with this justification. Must not be null.
     * @throws IllegalArgumentException if id or justificationDate is null, or if id is empty.
     */
    public Justification(String id, LocalDate justificationDate) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification ID cannot be null or empty.");
        }
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }
        this.id = id;
        this.justificationDate = justificationDate;
    }

    /**
     * Returns the unique identifier of the justification.
     *
     * @return The justification's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the justification.
     *
     * @param id The new ID for the justification. Must not be null or empty.
     * @throws IllegalArgumentException if id is null or empty.
     */
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification ID cannot be null or empty.");
        }
        this.id = id;
    }

    /**
     * Returns the date of the justification.
     *
     * @return The justification's date.
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }

    /**
     * Sets the date of the justification.
     *
     * @param justificationDate The new date for the justification. Must not be null.
     * @throws IllegalArgumentException if justificationDate is null.
     */
    public void setJustificationDate(LocalDate justificationDate) {
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }
        this.justificationDate = justificationDate;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Justification objects are considered equal if they have the same ID.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the justification's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the Justification object.
     *
     * @return A string containing the ID and justification date.
     */
    @Override
    public String toString() {
        return "Justification{" +
               "id='" + id + '\'' +
               ", justificationDate=" + justificationDate +
               '}';
    }
}