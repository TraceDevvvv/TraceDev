/**
 * Represents an absence record that may or may not be justified.
 * This class holds details about a specific absence, including its date,
 * justification status, and the employee it belongs to.
 */
import java.time.LocalDate;
public class Absence {
    private String id; // Unique identifier for the absence
    private LocalDate date;
    private boolean isJustified;
    private String employeeName;
    /**
     * Constructs a new Absence object.
     * @param id A unique ID for the absence.
     * @param date The date of the absence.
     * @param isJustified The current justification status (true if justified, false otherwise).
     * @param employeeName The name of the employee associated with this absence.
     */
    public Absence(String id, LocalDate date, boolean isJustified, String employeeName) {
        this.id = id;
        this.date = date;
        this.isJustified = isJustified;
        this.employeeName = employeeName;
    }
    /**
     * Returns the unique ID of the absence.
     * @return The absence ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the date of the absence.
     * @return The LocalDate object representing the absence date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Checks if the absence is justified.
     * @return True if the absence is justified, false otherwise.
     */
    public boolean isJustified() {
        return isJustified;
    }
    /**
     * Sets the justification status of the absence.
     * @param justified True to mark as justified, false to mark as unjustified.
     */
    public void setJustified(boolean justified) {
        isJustified = justified;
    }
    /**
     * Returns the name of the employee associated with this absence.
     * @return The employee's name.
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /**
     * Provides a string representation of the Absence object.
     * Useful for displaying in lists or tables.
     * @return A formatted string showing absence details.
     */
    @Override
    public String toString() {
        return String.format("Employee: %s | Date: %s | Justified: %s",
                             employeeName, date.toString(), isJustified ? "Yes" : "No (Red)");
    }
}