package infrastructure.adapters;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) for encapsulating notification details
 * to be sent via the message broker.
 */
public class NotificationMessage {
    private String noteId;
    private String studentId;
    private String studentName;
    private String parentEmail;
    private String teacherName;
    private String description;
    private LocalDateTime timestamp;

    /**
     * Constructs a new NotificationMessage.
     *
     * @param noteId The ID of the disciplinary note.
     * @param studentId The ID of the student.
     * @param studentName The name of the student.
     * @param parentEmail The email of the student's parent.
     * @param teacherName The name of the teacher.
     * @param description The description of the disciplinary incident.
     * @param timestamp The time the notification message was created.
     */
    public NotificationMessage(String noteId, String studentId, String studentName, String parentEmail,
                               String teacherName, String description, LocalDateTime timestamp) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.parentEmail = parentEmail;
        this.teacherName = teacherName;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters
    public String getNoteId() {
        return noteId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters (optional, if mutable DTOs are preferred)
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
               "noteId='" + noteId + '\'' +
               ", studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               ", teacherName='" + teacherName + '\'' +
               ", description='" + description + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}