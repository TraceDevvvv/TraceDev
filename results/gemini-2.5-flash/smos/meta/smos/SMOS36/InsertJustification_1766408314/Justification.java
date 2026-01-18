import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a justification for an absence in the system.
 * This class holds the details of a justification, primarily its date
 * and the ID of the absence it is justifying.
 */
public class Justification {
    private String justificationId; // Unique identifier for the justification
    private String absenceId;       // ID of the absence this justification is for
    private LocalDate justificationDate; // The date of the justification

    /**
     * Constructs a new Justification instance.
     *
     * @param justificationId   A unique identifier for this justification.
     * @param absenceId         The ID of the absence this justification is associated with.
     * @param justificationDate The date provided for the justification.
     */
    public Justification(String justificationId, String absenceId, LocalDate justificationDate) {
        if (justificationId == null || justificationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification ID cannot be null or empty.");
        }
        if (absenceId == null || absenceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Absence ID cannot be null or empty.");
        }
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }
        this.justificationId = justificationId;
        this.absenceId = absenceId;
        this.justificationDate = justificationDate;
    }

    /**
     * Gets the unique identifier of the justification.
     *
     * @return The justification ID.
     */
    public String getJustificationId() {
        return justificationId;
    }

    /**
     * Gets the ID of the absence this justification is for.
     *
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }

    /**
     * Gets the date of the justification.
     *
     * @return The justification date.
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }

    /**
     * Sets the justification date.
     *
     * @param justificationDate The new justification date.
     */
    public void setJustificationDate(LocalDate justificationDate) {
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }
        this.justificationDate = justificationDate;
    }

    @Override
    public String toString() {
        return "Justification{" +
               "justificationId='" + justificationId + '\'' +
               ", absenceId='" + absenceId + '\'' +
               ", justificationDate=" + justificationDate +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return Objects.equals(justificationId, that.justificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(justificationId);
    }
}