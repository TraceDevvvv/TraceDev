import java.time.LocalDate; // Import for handling dates

/**
 * Represents a note with details such as student, description, teacher, and date.
 * This class is immutable once created, ensuring data integrity.
 */
public class Note {
    private final String student;
    private final String description;
    private final String teacher;
    private final LocalDate date; // Using LocalDate for date to represent just the date without time

    /**
     * Constructs a new Note object.
     *
     * @param student The name of the student associated with the note. Cannot be null or empty.
     * @param description The detailed description of the note. Cannot be null or empty.
     * @param teacher The name of the teacher who created or is associated with the note. Cannot be null or empty.
     * @param date The date when the note was created or is relevant. Cannot be null.
     * @throws IllegalArgumentException if any of the string parameters are null or empty, or if date is null.
     */
    public Note(String student, String description, String teacher, LocalDate date) {
        // Validate input parameters to ensure data integrity
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
     * Returns the name of the student associated with this note.
     * @return The student's name.
     */
    public String getStudent() {
        return student;
    }

    /**
     * Returns the description of this note.
     * @return The note's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the teacher associated with this note.
     * @return The teacher's name.
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * Returns the date of this note.
     * @return The note's date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Provides a string representation of the Note object, useful for debugging and logging.
     * @return A formatted string containing all details of the note.
     */
    @Override
    public String toString() {
        return "Note Details:\n" +
               "  Student: " + student + "\n" +
               "  Description: " + description + "\n" +
               "  Teacher: " + teacher + "\n" +
               "  Date: " + date;
    }
}