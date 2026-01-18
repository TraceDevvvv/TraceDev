/**
 * JusticeData class represents a justification/justice entity.
 * This class holds the details of a justice such as ID, employee name, date, reason, and status.
 * It provides getters and setters for each attribute.
 */
public class JusticeData {
    private String id;
    private String employeeName;
    private String date;
    private String reason;
    private String status;
    /**
     * Constructor to initialize a JusticeData object with all attributes.
     *
     * @param id           Unique identifier for the justice.
     * @param employeeName Name of the employee associated with the justice.
     * @param date         Date of the justice (format: YYYY-MM-DD).
     * @param reason       Reason for the justification.
     * @param status       Current status (e.g., Approved, Pending, Rejected).
     */
    public JusticeData(String id, String employeeName, String date, String reason, String status) {
        this.id = id;
        this.employeeName = employeeName;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }
    // Getter and Setter methods for each attribute
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.ployeeName = employeeName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Returns a string representation of the justice data.
     *
     * @return String containing all justice details.
     */
    @Override
    public String toString() {
        return "Justice ID: " + id + "\nEmployee: " + employeeName + "\nDate: " + date + "\nReason: " + reason + "\nStatus: " + status;
    }
}