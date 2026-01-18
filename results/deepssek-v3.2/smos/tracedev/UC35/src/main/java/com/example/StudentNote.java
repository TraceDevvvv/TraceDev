package com.example;

import java.util.Date;

/**
 * Domain entity representing a student note.
 */
public class StudentNote {
    private String id;
    private String studentId;
    private String content;
    private Date dateRecorded;
    private String schoolYear;

    /**
     * Constructs a StudentNote with all fields.
     *
     * @param id the note ID
     * @param studentId the student ID
     * @param content the note content
     * @param dateRecorded the date the note was recorded
     * @param schoolYear the school year
     */
    public StudentNote(String id, String studentId, String content, Date dateRecorded, String schoolYear) {
        this.id = id;
        this.studentId = studentId;
        this.content = content;
        this.dateRecorded = dateRecorded;
        this.schoolYear = schoolYear;
    }

    /**
     * Gets the note ID.
     *
     * @return the note ID
     */
    public String getId() {
        return id;
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
     * Gets the note content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the date recorded.
     *
     * @return the date recorded
     */
    public Date getDateRecorded() {
        return dateRecorded;
    }

    /**
     * Gets the school year.
     *
     * @return the school year
     */
    public String getSchoolYear() {
        return schoolYear;
    }
}