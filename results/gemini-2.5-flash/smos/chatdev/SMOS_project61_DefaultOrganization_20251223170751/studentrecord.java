'''
Represents a single record entry for a student on a specific date,
including absences, disciplinary notes, delays, and justification.
'''
package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a single record entry for a student on a specific date,
 * including absences, disciplinary notes, delays, and justification.
 */
public class StudentRecord {
    private LocalDate date;
    private boolean absent;
    private String disciplinaryNotes;
    private int delays; // Number of delays
    private String justification; // Justification for absence/delays
    /**
     * Constructs a new StudentRecord.
     * @param date The date of the record.
     * @param absent True if the student was absent, false otherwise.
     * @param disciplinaryNotes Any disciplinary notes for the day. Can be empty.
     * @param delays The number of delays for the day.
     * @param justification Justification for absence or delays. Can be empty.
     */
    public StudentRecord(LocalDate date, boolean absent, String disciplinaryNotes, int delays, String justification) {
        this.date = date;
        this.absent = absent;
        this.disciplinaryNotes = disciplinaryNotes != null ? disciplinaryNotes : "";
        this.delays = delays;
        this.justification = justification != null ? justification : "";
    }
    /**
     * Gets the date of this record.
     * @return The record date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Checks if the student was absent on this date.
     * @return True if absent, false otherwise.
     */
    public boolean isAbsent() {
        return absent;
    }
    /**
     * Gets disciplinary notes for this date.
     * @return Disciplinary notes string.
     */
    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }
    /**
     * Gets the number of delays for this date.
     * @return Number of delays.
     */
    public int getDelays() {
        return delays;
    }
    /**
     * Gets the justification for absence or delays.
     * @return Justification string.
     */
    public String getJustification() {
        return justification;
    }
    /**
     * Returns a formatted date string for display.
     * @return Formatted date string (e.g., "YYYY-MM-DD").
     */
    public String getFormattedDate() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}