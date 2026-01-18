/**
 * Represents a disciplinary note with student information, date, teacher, and description
 */
import java.time.LocalDate;
public class Note {
    private String student;
    private LocalDate date;
    private String teacher;
    private String description;
    /**
     * Constructor for creating a new disciplinary note
     * @param student Student's name
     * @param date Date of the incident
     * @param teacher Teacher's name
     * @param description Description of the incident
     */
    public Note(String student, LocalDate date, String teacher, String description) {
        this.student = student;
        this.date = date;
        this.teacher = teacher;
        this.description = description;
    }
    // Getters
    public String getStudent() { return student; }
    public LocalDate getDate() { return date; }
    public String getTeacher() { return teacher; }
    public String getDescription() { return description; }
    /**
     * Gets the parent email associated with the student
     * In a real system, this would be retrieved from a database
     * @return Parent's email address
     */
    public String getParentEmail() {
        // Simulate getting parent email from student name
        // In real implementation, this would query a database
        return student.toLowerCase().replace(" ", ".") + ".parent@school.edu";
    }
    @Override
    public String toString() {
        return "Note{" +
                "student='" + student + '\'' +
                ", date=" + date +
                ", teacher='" + teacher + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}