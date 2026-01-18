/**
 * Represents a Justification entity with details such as ID, employee name,
 * absence date, reason, and status. It's a simple POJO (Plain Old Java Object).
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Justification {
    private String id;
    private String employeeName;
    private LocalDate absenceDate;
    private String reason;
    private String status; // e.g., "Pending", "Approved", "Rejected"
    /**
     * Constructs a new Justification instance.
     *
     * @param id The unique identifier for the justification.
     * @param employeeName The name of the employee.
     * @param absenceDate The date of absence.
     * @param reason The reason for the absence/justification.
     * @param status The current status of the justification.
     */
    public Justification(String id, String employeeName, LocalDate absenceDate, String reason, String status) {
        this.id = id;
        this.employeeName = employeeName;
        this.absenceDate = absenceDate;
        this.reason = reason;
        this.status = status;
    }
    /**
     * Gets the ID of the justification.
     * @return The justification ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the justification.
     * @param id The new ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the employee's name.
     * @return The employee's name.
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /**
     * Sets the employee's name.
     * @param employeeName The new employee name.
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    /**
     * Gets the absence date.
     * @return The absence date.
     */
    public LocalDate getAbsenceDate() {
        return absenceDate;
    }
    /**
     * Sets the absence date.
     * @param absenceDate The new absence date.
     */
    public void setAbsenceDate(LocalDate absenceDate) {
        this.absenceDate = absenceDate;
    }
    /**
     * Gets the reason for the justification.
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
     * Gets the status of the justification.
     * @return The status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status of the justification.
     * @param status The new status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Returns a string representation of the Justification object.
     * @return A string containing all justification details.
     */
    @Override
    public String toString() {
        // Handle null absenceDate gracefully to prevent NullPointerException
        String formattedAbsenceDate = (absenceDate != null) ? absenceDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "N/A";
        return "Justification{" +
               "id='" + id + '\'' +
               ", employeeName='" + employeeName + '\'' +
               ", absenceDate=" + formattedAbsenceDate +
               ", reason='" + reason + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}