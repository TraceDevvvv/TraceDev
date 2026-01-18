'''
Note model class representing a student note
'''
package studentnotessystem;
import java.time.LocalDate;
public class Note {
    private String noteId;
    private Student student;
    private LocalDate date;
    private String teacher;
    private String subject;
    private String content;
    private NoteType type;
    public enum NoteType {
        ACADEMIC, BEHAVIORAL, ATTENDANCE, GENERAL
    }
    public Note(String noteId, Student student, LocalDate date, String teacher, 
                String subject, String content, NoteType type) {
        this.noteId = noteId;
        this.student = student;
        this.date = date;
        this.teacher = teacher;
        this.subject = subject;
        this.content = content;
        this.type = type;
    }
    // Getters
    public String getNoteId() { return noteId; }
    public Student getStudent() { return student; }
    public LocalDate getDate() { return date; }
    public String getTeacher() { return teacher; }
    public String getSubject() { return subject; }
    public String getContent() { return content; }
    public NoteType getType() { return type; }
    public String getTypeString() {
        return type.toString();
    }
    @Override
    public String toString() {
        return "Note [ID: " + noteId + ", Student: " + student.getFirstName() + 
               ", Date: " + date + ", Subject: " + subject + "]";
    }
}