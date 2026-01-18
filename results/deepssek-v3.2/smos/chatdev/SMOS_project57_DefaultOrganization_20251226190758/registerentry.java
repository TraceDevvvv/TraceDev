'''
RegisterEntry.java
Data class representing a single entry in a class register.
Holds information about date, absences, disciplinary notes, delays, and justifications.
'''
public class RegisterEntry {
    private String date;
    private String absences;
    private String disciplinaryNotes;
    private String delays;
    private String justification;
    /**
     * Constructor for a register entry.
     * @param date Date of the entry.
     * @param absences Number of absences.
     * @param disciplinaryNotes Disciplinary notes.
     * @param delays Number of delays.
     * @param justification Justification for absences/delays.
     */
    public RegisterEntry(String date, String absences, String disciplinaryNotes, String delays, String justification) {
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }
    /**
     * Converts the entry to a formatted string for display.
     * @return Formatted string representing the entry.
     */
    public String toDisplayString() {
        return String.format("Date: %s | Absences: %s | Disciplinary Notes: %s | Delays: %s | Justification: %s",
                date, absences, disciplinaryNotes, delays, justification);
    }
    // Getters for potential future use
    public String getDate() { return date; }
    public String getAbsences() { return absences; }
    public String getDisciplinaryNotes() { return disciplinaryNotes; }
    public String getDelays() { return delays; }
    public String getJustification() { return justification; }
}