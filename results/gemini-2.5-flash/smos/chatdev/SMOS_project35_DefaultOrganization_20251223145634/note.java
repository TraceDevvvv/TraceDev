/**
 * Represents a single note associated with a student.
 */
public class Note {
    private String studentId;
    private String content;
    private String date; // Using String for simplicity in this example
    /**
     * Constructs a new Note object.
     * @param studentId The ID of the student to whom the note belongs.
     * @param content The actual content/text of the note.
     * @param date The date the note was recorded (e.g., "YYYY-MM-DD").
     */
    public Note(String studentId, String content, String date) {
        this.studentId = studentId;
        this.content = content;
        this.date = date;
    }
    /**
     * Returns the ID of the student associated with this note.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Returns the content of the note.
     * @return The note's content.
     */
    public String getContent() {
        return content;
    }
    /**
     * Returns the date the note was recorded.
     * @return The note's date.
     */
    public String getDate() {
        return date;
    }
    /**
     * Provides a formatted string representation of the note.
     * @return A string containing the note's date and content.
     */
    @Override
    public String toString() {
        return "Date: " + date + "\n" + "  Note: " + content + "\n";
    }
}