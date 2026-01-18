package com.example.school;

import java.util.Date;

/**
 * Represents a Disciplinary Note record for a student in a class.
 */
public class DisciplinaryNote {
    private String noteId;
    private String studentId;
    private String classId;
    private Date date;
    private String description;

    /**
     * Constructs a new DisciplinaryNote record.
     * @param noteId The unique identifier for the disciplinary note.
     * @param studentId The ID of the student.
     * @param classId The ID of the class where the note was issued.
     * @param date The date the note was issued.
     * @param description A description of the disciplinary incident.
     */
    public DisciplinaryNote(String noteId, String studentId, String classId, Date date, String description) {
        this.noteId = noteId;
        this.studentId = studentId;
        this.classId = classId;
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the unique identifier of the disciplinary note.
     * @return The note ID.
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Gets the ID of the student.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the ID of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the date the note was issued.
     * @return The date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the description of the disciplinary incident.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }
}