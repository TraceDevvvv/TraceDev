/**
 * Represents a single record of child information including absences, notes, delays, and justification.
 * Corresponds to the data displayed in the summary table as per use case requirements.
 */
class ChildRecord {
    private String date;
    private int absences;
    private String disciplinaryNotes;
    private String delays;
    private String justification;
    /**
     * Constructor to create a child record with all required fields.
     * @param date Date of the record in YYYY-MM-DD format
     * @param absences Number of absences on that date (non-negative)
     * @param disciplinaryNotes Disciplinary notes/comments about behavior
     * @param delays Any delays (e.g., "15 minutes", "None")
     * @param justification Justification for absences/delays
     */
    public ChildRecord(String date, int absences, String disciplinaryNotes, String delays, String justification) {
        // Validate inputs
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (absences < 0) {
            throw new IllegalArgumentException("Absences cannot be negative");
        }
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes != null ? disciplinaryNotes : "";
        this.delays = delays != null ? delays : "None";
        this.justification = justification != null ? justification : "N/A";
    }
    // Getters for all fields
    public String getDate() { 
        return date; 
    }
    public int getAbsences() { 
        return absences; 
    }
    public String getDisciplinaryNotes() { 
        return disciplinaryNotes; 
    }
    public String getDelays() { 
        return delays; 
    }
    public String getJustification() { 
        return justification; 
    }
    /**
     * Returns formatted string representation of the record for table display.
     * Ensures consistent formatting as specified in the use case.
     * @return Formatted record string suitable for table output
     */
    @Override
    public String toString() {
        return String.format("| %-12s | %-9d | %-25s | %-15s | %-20s |", 
            date, absences, 
            truncate(disciplinaryNotes, 25), 
            truncate(delays, 15), 
            truncate(justification, 20));
    }
    /**
     * Helper method to truncate long strings for table display.
     * @param text The text to truncate
     * @param maxLength Maximum allowed length
     * @return Truncated text with ellipsis if needed
     */
    private String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
    /**
     * Returns a more detailed string representation for debugging.
     * @return Detailed description of the record
     */
    public String toDetailedString() {
        return String.format("ChildRecord{date='%s', absences=%d, disciplinaryNotes='%s', delays='%s', justification='%s'}",
            date, absences, disciplinaryNotes, delays, justification);
    }
}