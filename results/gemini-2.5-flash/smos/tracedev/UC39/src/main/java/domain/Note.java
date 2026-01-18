package domain;

import java.util.Date;

/**
 * Represents a Note entity in the domain layer.
 */
public class Note {
    // Attributes as specified in the Class Diagram
    private String id;
    private String description;
    private Date date;
    private String studentId;
    private String teacherId;

    /**
     * Constructs a new Note object.
     * @param id The unique identifier for the note.
     * @param description The textual content of the note.
     * @param date The date the note was created or last updated.
     * @param studentId The ID of the student associated with this note.
     * @param teacherId The ID of the teacher who created this note.
     */
    public Note(String id, String description, Date date, String studentId, String teacherId) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    // Getters for the attributes
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public String toString() {
        return "Note{" +
               "id='" + id + '\'' +
               ", description='" + description + '\'' +
               ", date=" + date +
               ", studentId='" + studentId + '\'' +
               ", teacherId='" + teacherId + '\'' +
               '}';
    }
}