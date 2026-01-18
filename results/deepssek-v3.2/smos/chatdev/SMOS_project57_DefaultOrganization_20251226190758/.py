'''
RegisterEntry.java
Data class representing a single entry in the class register.
'''
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * Converts the entry to an array for table row insertion.
     * @return Array of strings representing the entry.
     */
    public String[] toRow() {
        return new String[]{date, absences, disciplinaryNotes, delays, justification};
    }
}