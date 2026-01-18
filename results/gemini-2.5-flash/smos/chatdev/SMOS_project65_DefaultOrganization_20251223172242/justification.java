import java.time.LocalDate;
import java.util.Objects;
/*
 * Represents a justification submitted for a student's absence or delay.
 */
public class Justification {
    private String id;
    private String studentId;
    private LocalDate date;
    private String reason;
    private boolean approved;
    /**
     * Constructs a new Justification object.
     * @param id The unique identifier for the justification.
     * @param studentId The ID of the student the justification is for.
     * @param date The date the justification applies to.
     * @param reason The reason for the absence/delay.
     * @param approved The approval status of the justification.
     */
    public Justification(String id, String studentId, LocalDate date, String reason, boolean approved) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.reason = reason;
        this.approved = approved;
    }
    /**
     * Returns the justification ID.
     * @return The justification ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the justification ID.
     * @param id The new justification ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the ID of the student associated with this justification.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Sets the ID of the student associated with this justification.
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * Returns the date the justification applies to.
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Sets the date the justification applies to.
     * @param date The new date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * Returns the reason for the justification.
     * @return The reason.
     */
    public String getReason() {
        return reason;
    }
    /**
     * Sets the reason for the justification.
     * @param reason The new reason.
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * Checks if the justification is approved.
     * @return True if approved, false otherwise.
     */
    public boolean isApproved() {
        return approved;
    }
    /**
     * Sets the approval status of the justification.
     * @param approved The new approval status.
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    /**
     * Approves the justification.
     */
    public void approve() {
        this.approved = true;
    }
    /**
     * Rejects the justification.
     */
    public void reject() {
        this.approved = false;
    }
    @Override
    public String toString() {
        return reason + (approved ? " (Approved)" : " (Pending)");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}