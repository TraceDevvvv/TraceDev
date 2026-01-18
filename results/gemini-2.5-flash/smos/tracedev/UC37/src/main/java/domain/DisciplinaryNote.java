package domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity representing a Disciplinary Note.
 * This is the core domain object, holding all details about a disciplinary incident.
 */
public class DisciplinaryNote {
    private String id;
    private String studentId;
    private LocalDate date;
    private String teacherId;
    private String description;
    private String parentEmail; // Added to the entity as per sequence diagram flow where it's looked up and stored.

    /**
     * Constructor for creating a new DisciplinaryNote with a generated ID.
     * This is typically used when the note is first created in the system.
     *
     * @param studentId The ID of the student involved.
     * @param date The date the disciplinary action occurred.
     * @param teacherId The ID of the teacher issuing the note.
     * @param description A detailed description of the disciplinary incident.
     * @param parentEmail The email address of the student's parent, looked up during creation.
     */
    public DisciplinaryNote(String studentId, LocalDate date, String teacherId, String description, String parentEmail) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the new note
        this.studentId = studentId;
        this.date = date;
        this.teacherId = teacherId;
        this.description = description;
        this.parentEmail = parentEmail;
    }

    /**
     * Constructor for creating a DisciplinaryNote when its ID is already known
     * (e.g., when retrieving from a database).
     *
     * @param id The unique ID of the disciplinary note.
     * @param studentId The ID of the student involved.
     * @param date The date the disciplinary action occurred.
     * @param teacherId The ID of the teacher issuing the note.
     * @param description A detailed description of the disciplinary incident.
     * @param parentEmail The email address of the student's parent.
     */
    public DisciplinaryNote(String id, String studentId, LocalDate date, String teacherId, String description, String parentEmail) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.teacherId = teacherId;
        this.description = description;
        this.parentEmail = parentEmail;
    }

    // Getters
    public String getId() {
        return id;
    }

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

    public String getParentEmail() {
        return parentEmail;
    }

    // Setters (often excluded for entities to enforce immutability post-creation,
    // but included here based on common practice or potential future needs)
    public void setId(String id) {
        this.id = id;
    }

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

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
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

    @Override
    public String toString() {
        return "DisciplinaryNote{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", date=" + date +
               ", teacherId='" + teacherId + '\'' +
               ", description='" + description + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               '}';
    }
}