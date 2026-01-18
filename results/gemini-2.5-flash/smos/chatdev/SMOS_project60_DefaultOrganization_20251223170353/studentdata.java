'''
Represents a single record of a student's daily data
including date, absences, disciplinary notes, delays, and justification.
'''
public class StudentData {
    private String date;
    private int absences;
    private String disciplinaryNotes;
    private int delays; // in minutes or count, depends on interpretation, let's use count for simplicity
    private String justification;
    /**
     * Constructs a new StudentData record.
     *
     * @param date The date for which the record applies.
     * @param absences The number of absences on this date.
     * @param disciplinaryNotes Any disciplinary notes for this date.
     * @param delays The number of delays on this date.
     * @param justification Any justification for absences or delays.
     */
    public StudentData(String date, int absences, String disciplinaryNotes, int delays, String justification) {
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }
    /**
     * @return The date of the record.
     */
    public String getDate() {
        return date;
    }
    /**
     * @return The number of absences.
     */
    public int getAbsences() {
        return absences;
    }
    /**
     * @return Disciplinary notes.
     */
    public String getDisciplinaryNotes() {
        return disciplinaryNotes;
    }
    /**
     * @return The number of delays.
     */
    public int getDelays() {
        return delays;
    }
    /**
     * @return Justification for absences/delays.
     */
    public String getJustification() {
        return justification;
    }
}