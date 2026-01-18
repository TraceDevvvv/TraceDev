import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/*
 * Represents a single student's record for a specific date, including attendance,
 * justifications, and disciplinary notes.
 */
public class RegisterEntry {
    private Student student;
    private AttendanceStatus status;
    private Justification justification; // Can be null if no justification
    private List<DisciplinaryNote> disciplinaryNotes; // List as a student can have multiple notes
    /**
     * Constructs a new RegisterEntry object.
     * @param student The student for this entry.
     * @param status The attendance status for the student on this date.
     */
    public RegisterEntry(Student student, AttendanceStatus status) {
        this.student = student;
        this.status = status;
        this.disciplinaryNotes = new ArrayList<>();
    }
    /**
     * Returns the student associated with this register entry.
     * @return The student object.
     */
    public Student getStudent() {
        return student;
    }
    /**
     * Sets the student for this register entry.
     * @param student The new student object.
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    /**
     * Returns the attendance status of the student.
     * @return The attendance status.
     */
    public AttendanceStatus getStatus() {
        return status;
    }
    /**
     * Sets the attendance status of the student.
     * @param status The new attendance status.
     */
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    /**
     * Returns the justification for the student's absence/delay.
     * Can be null if no justification exists.
     * @return The justification object or null.
     */
    public Justification getJustification() {
        return justification;
    }
    /**
     * Sets the justification for the student's absence/delay.
     * @param justification The new justification object.
     */
    public void setJustification(Justification justification) {
        this.justification = justification;
    }
    /**
     * Returns the list of disciplinary notes for the student.
     * @return A list of DisciplinaryNote objects.
     */
    public List<DisciplinaryNote> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }
    /**
     * Adds a disciplinary note to the student's record.
     * @param note The DisciplinaryNote to add.
     */
    public void addDisciplinaryNote(DisciplinaryNote note) {
        if (note != null && student.getId().equals(note.getStudentId())) {
            this.disciplinaryNotes.add(note);
        }
    }
    /**
     * Removes a disciplinary note from the student's record.
     * @param note The DisciplinaryNote to remove.
     * @return True if the note was removed, false otherwise.
     */
    public boolean removeDisciplinaryNote(DisciplinaryNote note) {
        return this.disciplinaryNotes.remove(note);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterEntry that = (RegisterEntry) o;
        return student.equals(that.student); // Only student object defines uniqueness for an entry per date
    }
    @Override
    public int hashCode() {
        return Objects.hash(student);
    }
}