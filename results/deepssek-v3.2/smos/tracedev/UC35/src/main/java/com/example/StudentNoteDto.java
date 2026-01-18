package com.example;

import java.util.Date;

/**
 * Data transfer object for student notes.
 * Mirrors the database schema.
 */
public class StudentNoteDto {
    private String noteId;
    private String studentId;
    private String noteContent;
    private Date creationDate;
    private String schoolYear;

    /**
     * Gets the note ID.
     *
     * @return the note ID
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * Sets the note ID.
     *
     * @param noteId the note ID
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     * Gets the student ID.
     *
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID.
     *
     * @param studentId the student ID
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the note content.
     *
     * @return the note content
     */
    public String getNoteContent() {
        return noteContent;
    }

    /**
     * Sets the note content.
     *
     * @param noteContent the note content
     */
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the school year.
     *
     * @return the school year
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the school year.
     *
     * @param schoolYear the school year
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}