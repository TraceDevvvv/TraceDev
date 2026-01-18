/**
 * student.java
 * Model class representing a student with their class register data.
 * This class encapsulates all data points mentioned in the use case:
 * date, absences, disciplinary notes, delays and justification.
 */
import java.util.Date;
public class Student {
    private int id;
    private String name;
    private String className;
    private Date date;
    private int absences;
    private String disciplinaryNotes;
    private int delays;
    private String justification;
    public Student(int id, String name, String className, Date date, 
                   int absences, String disciplinaryNotes, int delays, String justification) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.date = date;
        this.absences = absences;
        this.disciplinaryNotes = disciplinaryNotes;
        this.delays = delays;
        this.justification = justification;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public int getAbsences() { return absences; }
    public void setAbsences(int absences) { this.absences = absences; }
    public String getDisciplinaryNotes() { return disciplinaryNotes; }
    public void setDisciplinaryNotes(String disciplinaryNotes) { 
        this.disciplinaryNotes = disciplinaryNotes; 
    }
    public int getDelays() { return delays; }
    public void setDelays(int delays) { this.delays = delays; }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { 
        this.justification = justification; 
    }
    @Override
    public String toString() {
        return String.format("Student: %s (Class: %s) - Absences: %d, Delays: %d", 
                name, className, absences, delays);
    }
}