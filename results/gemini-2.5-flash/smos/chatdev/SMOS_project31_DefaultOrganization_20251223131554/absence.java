/*
 * DOCSTRING: Defines the Absence data model class.
 * This class represents a single absence record for a student on a specific date.
 */
package model;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
public class Absence {
    private final UUID id; // Unique identifier for the absence record
    private int studentId;
    private String studentName;
    private LocalDate date;
    private String reason;
    private String originalParentEmail; // Stored to send notifications
    // For tracking changes in the UI before saving to the "server"
    public enum Status {
        EXISTING,
        NEW,
        DELETED
    }
    private Status status;
    /**
     * Constructor for a new Absence.
     *
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param date The date of the absence.
     * @param reason The reason for the absence.
     * @param originalParentEmail The email of the student's parent at the time of creation/retrieval.
     */
    public Absence(int studentId, String studentName, LocalDate date, String reason, String originalParentEmail) {
        this(UUID.randomUUID(), studentId, studentName, date, reason, originalParentEmail, Status.NEW);
    }
    /**
     * Constructor for an existing Absence.
     *
     * @param id The unique ID of the absence.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param date The date of the absence.
     * @param reason The reason for the absence.
     * @param originalParentEmail The email of the student's parent.
     * @param status The current status of the absence (e.g., EXISTING, NEW, DELETED).
     */
    public Absence(UUID id, int studentId, String studentName, LocalDate date, String reason, String originalParentEmail, Status status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.date = date;
        this.reason = reason;
        this.originalParentEmail = originalParentEmail;
        this.status = status;
    }
    // --- Getters ---
    public UUID getId() {
        return id;
    }
    public int getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getReason() {
        return reason;
    }
    public String getOriginalParentEmail() {
        return originalParentEmail;
    }
    public Status getStatus() {
        return status;
    }
    // --- Setters ---
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setOriginalParentEmail(String originalParentEmail) {
        this.originalParentEmail = originalParentEmail;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Helper method to check if this is a newly created absence.
     * @return true if the absence is new, false otherwise.
     */
    public boolean isNew() {
        return status == Status.NEW;
    }
    /**
     * Helper method to check if this absence is marked for deletion.
     * @return true if the absence is marked for deletion, false otherwise.
     */
    public boolean isDeleted() {
        return status == Status.DELETED;
    }
    @Override
    public String toString() {
        return "Absence{" +
               "id=" + id +
               ", studentId=" + studentId +
               ", studentName='" + studentName + '\'' +
               ", date=" + date +
               ", reason='" + reason + '\'' +
               ", parentEmail='" + originalParentEmail + '\'' +
               ", status=" + status +
               '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return studentId == absence.studentId &&
               Objects.equals(id, absence.id) &&
               Objects.equals(studentName, absence.studentName) &&
               Objects.equals(date, absence.date) &&
               Objects.equals(reason, absence.reason) &&
               Objects.equals(originalParentEmail, absence.originalParentEmail);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, studentName, date, reason, originalParentEmail);
    }
}