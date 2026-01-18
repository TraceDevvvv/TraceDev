'''
Data model class representing a single entry in a class register for a student on a specific date.
It includes details like absences, disciplinary notes, delays, and their justifications.
'''
public class StudentRegisterEntry {
    private String date;               // The date of the register entry (e.g., "2023-10-26").
    private String studentName;        // The name of the student.
    private boolean absence;           // True if the student was absent.
    private String disciplinaryNote;   // Any disciplinary notes for the student on this date.
    private boolean delay;             // True if the student was delayed.
    private String justification;      // Justification for absence or delay.
    /**
     * Constructs a new StudentRegisterEntry object.
     * @param date The date of the entry.
     * @param studentName The name of the student.
     * @param absence True if the student was absent.
     * @param disciplinaryNote Any disciplinary notes.
     * @param delay True if the student was delayed.
     * @param justification Justification for absence/delay.
     */
    public StudentRegisterEntry(String date, String studentName, boolean absence, 
                                 String disciplinaryNote, boolean delay, String justification) {
        this.date = date;
        this.studentName = studentName;
        this.absence = absence;
        this.disciplinaryNote = disciplinaryNote;
        this.delay = delay;
        this.justification = justification;
    }
    // --- Getters for all fields ---
    public String getDate() {
        return date;
    }
    public String getStudentName() {
        return studentName;
    }
    public boolean isAbsence() {
        return absence;
    }
    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }
    public boolean isDelay() {
        return delay;
    }
    public String getJustification() {
        return justification;
    }
}