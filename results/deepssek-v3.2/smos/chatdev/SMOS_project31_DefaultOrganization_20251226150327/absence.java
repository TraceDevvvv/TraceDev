import java.time.LocalDate;
/**
 * Represents an absence record for a student on a specific date.
 */
public class Absence {
    private Student student;
    private LocalDate date;
    private boolean present; // true if present, false if absent
    public Absence(Student student, LocalDate date, boolean present) {
        this.student = student;
        this.date = date;
        this.present = present;
    }
    public Student getStudent() { return student; }
    public LocalDate getDate() { return date; }
    public boolean isPresent() { return present; }
    public void setPresent(boolean present) {
        this.present = present;
    }
}