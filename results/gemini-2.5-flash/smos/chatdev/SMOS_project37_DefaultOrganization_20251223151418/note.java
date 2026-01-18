'''
Represents a disciplinary note with details such as student, date, teacher, description, and parent email.
'''
import java.time.LocalDate;
import java.util.Objects;
public class Note {
    private String studentName;
    private LocalDate date;
    private String teacherName;
    private String description;
    private String parentEmail;
    /**
     * Constructor for creating a new Note object.
     *
     * @param studentName The name of the student.
     * @param date The date the note was issued.
     * @param teacherName The name of the teacher who issued the note.
     * @param description A detailed description of the disciplinary action.
     * @param parentEmail The email address of the parent/guardian to notify.
     */
    public Note(String studentName, LocalDate date, String teacherName, String description, String parentEmail) {
        this.studentName = studentName;
        this.date = date;
        this.teacherName = teacherName;
        this.description = description;
        this.parentEmail = parentEmail;
    }
    // --- Getters and Setters ---
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getParentEmail() {
        return parentEmail;
    }
    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
    /**
     * Provides a string representation of the Note object.
     *
     * @return A string containing all the note's details.
     */
    @Override
    public String toString() {
        return "Note{" +
               "studentName='" + studentName + '\'' +
               ", date=" + date +
               ", teacherName='" + teacherName + '\'' +
               ", description='" + description + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               '}';
    }
    /**
     * Compares this Note object to the specified object. The result is true if and only if
     * the argument is not null and is a Note object that represents the same student, date,
     * teacher, description, and parent email as this object.
     *
     * @param o The object to compare this Note against.
     * @return true if the given object represents a Note equivalent to this note, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(studentName, note.studentName) &&
               Objects.equals(date, note.date) &&
               Objects.equals(teacherName, note.teacherName) &&
               Objects.equals(description, note.description) &&
               Objects.equals(parentEmail, note.parentEmail);
    }
    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentName, date, teacherName, description, parentEmail);
    }
}