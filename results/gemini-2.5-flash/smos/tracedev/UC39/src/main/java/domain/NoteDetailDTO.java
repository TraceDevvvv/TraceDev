package domain;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for displaying detailed information about a note.
 * Aggregates data from Note, Student, and Teacher entities.
 */
public class NoteDetailDTO {
    // Attributes as specified in the Class Diagram
    public String studentName;
    public String noteDescription;
    public String teacherName;
    public Date noteDate;

    /**
     * Constructs a new NoteDetailDTO.
     * @param studentName The name of the student.
     * @param noteDescription The description content of the note.
     * @param teacherName The name of the teacher.
     * @param noteDate The date of the note.
     */
    public NoteDetailDTO(String studentName, String noteDescription, String teacherName, Date noteDate) {
        this.studentName = studentName;
        this.noteDescription = noteDescription;
        this.teacherName = teacherName;
        this.noteDate = noteDate;
    }

    // Getters for the attributes
    public String getStudentName() {
        return studentName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    @Override
    public String toString() {
        return "Note Details:\n" +
               "  Student: " + studentName + "\n" +
               "  Teacher: " + teacherName + "\n" +
               "  Date:    " + noteDate + "\n" +
               "  Note:    " + noteDescription;
    }
}