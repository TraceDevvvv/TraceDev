/**
 * Represents an attendance record for a specific student on a specific date.
 * Includes status, justification, and disciplinary notes.
 */
import java.util.Date;
public class AttendanceRecord {
    private Student student;
    private Date date;
    private String status; // "Present", "Absent", or "Delayed"
    private String justification;
    private String disciplinaryNotes;
    public AttendanceRecord(Student student, Date date) {
        this.student = student;
        this.date = date;
        this.status = "Present"; // Default status
        this.justification = "";
        this.disciplinaryNotes = "";
    }
    // Getters and setters
    public Student getStudent() { return student; }
    public Date getDate() { return date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        if (status.equals("Present") || status.equals("Absent") || status.equals("Delayed")) {
            this.status = status; 
        }
    }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }
    public String getDisciplinaryNotes() { return disciplinaryNotes; }
    public void setDisciplinaryNotes(String disciplinaryNotes) { this.disciplinaryNotes = disciplinaryNotes; }
    @Override
    public String toString() {
        return student + " on " + date + ": " + status;
    }
}