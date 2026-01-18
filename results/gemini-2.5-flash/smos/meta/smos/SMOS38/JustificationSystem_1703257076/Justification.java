import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Justification entity in the system.
 * This class holds details about a specific justification, including its unique identifier,
 * the reason provided, its current status, and the ID of the absence it's related to.
 */
public class Justification {

    private String id; // Unique identifier for the justification
    private String absenceId; // Identifier of the absence this justification is for
    private String reason; // The reason provided for the absence
    private JustificationStatus status; // Current status of the justification (e.g., PENDING, APPROVED, REJECTED)
    private LocalDateTime submissionDate; // Date and time when the justification was submitted

    /**
     * Enum to define the possible statuses of a justification.
     */
    public enum JustificationStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    /**
     * Constructor for creating a new Justification object.
     *
     * @param id The unique ID of the justification.
     * @param absenceId The ID of the absence associated with this justification.
     * @param reason The detailed reason for the justification.
     * @param status The initial status of the justification.
     * @param submissionDate The date and time the justification was submitted.
     */
    public Justification(String id, String absenceId, String reason, JustificationStatus status, LocalDateTime submissionDate) {
        this.id = id;
        this.absenceId = absenceId;
        this.reason = reason;
        this.status = status;
        this.submissionDate = submissionDate;
    }

    /**
     * Gets the unique identifier of the justification.
     *
     * @return The justification ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the justification.
     *
     * @param id The new justification ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the ID of the absence associated with this justification.
     *
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }

    /**
     * Sets the ID of the absence associated with this justification.
     *
     * @param absenceId The new absence ID.
     */
    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    /**
     * Gets the reason provided for the justification.
     *
     * @return The justification reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for the justification.
     *
     * @param reason The new justification reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gets the current status of the justification.
     *
     * @return The justification status.
     */
    public JustificationStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the justification.
     *
     * @param status The new justification status.
     */
    public void setStatus(JustificationStatus status) {
        this.status = status;
    }

    /**
     * Gets the submission date and time of the justification.
     *
     * @return The submission date.
     */
    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Sets the submission date and time of the justification.
     *
     * @param submissionDate The new submission date.
     */
    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     * Provides a string representation of the Justification object.
     *
     * @return A formatted string containing the justification's details.
     */
    @Override
    public String toString() {
        return "Justification{" +
               "id='" + id + '\'' +
               ", absenceId='" + absenceId + '\'' +
               ", reason='" + reason + '\'' +
               ", status=" + status +
               ", submissionDate=" + submissionDate +
               '}';
    }

    /**
     * Compares this Justification object to the specified object. The result is true if and only if
     * the argument is not null and is a Justification object that has the same id.
     *
     * @param o The object to compare this Justification against.
     * @return true if the given object represents a Justification equivalent to this justification, false otherwise.
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
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}