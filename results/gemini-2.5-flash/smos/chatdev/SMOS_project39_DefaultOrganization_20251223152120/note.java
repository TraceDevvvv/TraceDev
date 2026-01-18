/*
 * Represents a 'Note' entity with student, description, teacher, and date details.
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Note {
    private final String student;
    private final String description;
    private final String teacher;
    private final LocalDate date; // Using LocalDate for proper date handling
    /**
     * Constructs a new Note object.
     *
     * @param student The name of the student associated with the note.
     * @param description The content/description of the note.
     * @param teacher The teacher who created/is associated with the note.
     * @param date The date the note was created or last updated.
     */
    public Note(String student, String description, String teacher, LocalDate date) {
        // Basic validation to ensure essential fields are not null or empty
        if (student == null || student.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        if (teacher == null || teacher.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }
    /**
     * Returns the student's name.
     * @return The student's name.
     */
    public String getStudent() {
        return student;
    }
    /**
     * Returns the description of the note.
     * @return The note's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns the teacher's name.
     * @return The teacher's name.
     */
    public String getTeacher() {
        return teacher;
    }
    /**
     * Returns the date of the note.
     * @return The note's date.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Provides a string representation of the Note object.
     * @return A formatted string showing all note details.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Note{" +
               "student='" + student + '\'' +
               ", description='" + description + '\'' +
               ", teacher='" + teacher + '\'' +
               ", date=" + date.format(formatter) +
               '}';
    }
}