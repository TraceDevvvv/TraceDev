import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student with their attendance details, justifications, and disciplinary notes.
 * This class encapsulates all information pertinent to a student's record for a specific day
 * in the class register.
 */
public class Student {

    /**
     * Enum to represent the attendance status of a student.
     */
    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        DELAYED
    }

    private String studentId;
    private String name;
    private AttendanceStatus attendanceStatus;
    private String justification; // Reason for absence or delay
    private List<String> disciplinaryNotes; // List of disciplinary notes for the student

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.studentId = studentId;
        this.name = name;
        this.attendanceStatus = AttendanceStatus.PRESENT; // Default to present
        this.justification = "";
        this.disciplinaryNotes = new ArrayList<>();
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the attendance status of the student for a specific date.
     *
     * @return The attendance status (PRESENT, ABSENT, DELAYED).
     */
    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    /**
     * Sets the attendance status of the student for a specific date.
     *
     * @param attendanceStatus The new attendance status.
     */
    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        if (attendanceStatus == null) {
            throw new IllegalArgumentException("Attendance status cannot be null.");
        }
        this.attendanceStatus = attendanceStatus;
    }

    /**
     * Gets the justification for the student's absence or delay.
     *
     * @return The justification string.
     */
    public String getJustification() {
        return justification;
    }

    /**
     * Sets the justification for the student's absence or delay.
     *
     * @param justification The justification string. Can be empty but not null.
     */
    public void setJustification(String justification) {
        if (justification == null) {
            throw new IllegalArgumentException("Justification cannot be null.");
        }
        this.justification = justification;
    }

    /**
     * Gets the list of disciplinary notes for the student.
     *
     * @return A list of disciplinary notes. This list is mutable.
     */
    public List<String> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    /**
     * Adds a disciplinary note for the student.
     *
     * @param note The disciplinary note to add.
     */
    public void addDisciplinaryNote(String note) {
        if (note == null || note.trim().isEmpty()) {
            throw new IllegalArgumentException("Disciplinary note cannot be null or empty.");
        }
        this.disciplinaryNotes.add(note);
    }

    /**
     * Removes a specific disciplinary note from the student's record.
     *
     * @param note The disciplinary note to remove.
     * @return true if the note was found and removed, false otherwise.
     */
    public boolean removeDisciplinaryNote(String note) {
        return this.disciplinaryNotes.remove(note);
    }

    /**
     * Provides a string representation of the Student object.
     *
     * @return A formatted string containing student details.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", attendanceStatus=" + attendanceStatus +
               ", justification='" + justification + '\'' +
               ", disciplinaryNotes=" + disciplinaryNotes +
               '}';
    }

    /**
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same studentId as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}