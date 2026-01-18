import java.time.LocalDate;
import java.util.Objects;
/*
 * Represents a disciplinary note issued for a student.
 */
public class DisciplinaryNote {
    private String id;
    private String studentId;
    private LocalDate date;
    private String description;
    /**
     * Constructs a new DisciplinaryNote object.
     * @param id The unique identifier for the disciplinary note.
     * @param studentId The ID of the student the note is for.
     * @param date The date the disciplinary action occurred.
     * @param description A detailed description of the disciplinary note.
     */
    public DisciplinaryNote(String id, String studentId, LocalDate date, String description) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.description = description;
    }
    /**
     * Returns the disciplinary note ID.
     * @return The disciplinary note ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the disciplinary note ID.
     * @param id The new disciplinary note ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the ID of the student associated with this disciplinary note.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Sets the ID of the student associated with this disciplinary note.
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    /**
     * Returns the date the disciplinary note was issued.
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Sets the date the disciplinary note was issued.
     * @param date The new date.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * Returns the description of the disciplinary note.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the disciplinary note.
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisciplinaryNote that = (DisciplinaryNote) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}