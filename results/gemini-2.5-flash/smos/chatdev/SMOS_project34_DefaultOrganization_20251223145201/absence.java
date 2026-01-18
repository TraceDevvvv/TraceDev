package com.chatdev.smos.model;
import java.time.LocalDate;
/**
 * Represents a student absence record.
 * This class holds information about a specific absence, including its date,
 * a reason, and a boolean indicating whether it has been justified.
 */
public class Absence {
    private LocalDate date;
    private String reason;
    private boolean isJustified;
    /**
     * Constructs a new Absence object.
     *
     * @param date The date of the absence.
     * @param reason The reason for the absence (can be null or empty).
     * @param isJustified True if the absence has been justified, false otherwise.
     */
    public Absence(LocalDate date, String reason, boolean isJustified) {
        this.date = date;
        this.reason = reason;
        this.isJustified = isJustified;
    }
    /**
     * Returns the date of the absence.
     * @return The LocalDate object representing the absence date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Returns the reason for the absence.
     * @return A String containing the reason.
     */
    public String getReason() {
        return reason;
    }
    /**
     * Checks if the absence has been justified.
     * @return True if justified, false otherwise.
     */
    public boolean isJustified() {
        return isJustified;
    }
    /**
     * Sets the justification status of the absence.
     * While this specific use case is for viewing, providing a setter is good practice
     * for a complete model.
     * @param justified The new justification status.
     */
    public void setJustified(boolean justified) {
        isJustified = justified;
    }
    /**
     * Provides a string representation of the Absence object for debugging/logging.
     * @return A formatted string with absence details.
     */
    @Override
    public String toString() {
        return "Absence{" +
               "date=" + date +
               ", reason='" + reason + '\'' +
               ", isJustified=" + isJustified +
               '}';
    }
}