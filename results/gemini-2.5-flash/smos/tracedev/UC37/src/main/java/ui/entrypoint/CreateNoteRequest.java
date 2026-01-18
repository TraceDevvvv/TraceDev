package ui.entrypoint;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) for encapsulating the request data
 * when creating a new disciplinary note.
 */
public class CreateNoteRequest {
    private String studentId;
    private LocalDate date;
    private String teacherId;
    private String description;

    /**
     * Constructs a new CreateNoteRequest.
     *
     * @param studentId The ID of the student involved.
     * @param date The date the disciplinary action occurred.
     * @param teacherId The ID of the teacher issuing the note.
     * @param description A detailed description of the disciplinary incident.
     */
    public CreateNoteRequest(String studentId, LocalDate date, String teacherId, String description) {
        this.studentId = studentId;
        this.date = date;
        this.teacherId = teacherId;
        this.description = description;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getDescription() {
        return description;
    }

    // Setters (optional, but often included for DTOs if they are mutable)
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateNoteRequest{" +
               "studentId='" + studentId + '\'' +
               ", date=" + date +
               ", teacherId='" + teacherId + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}