package com.school.domain;

import java.util.Date;

/**
 * Domain entity representing a disciplinary note.
 */
public class DisciplinaryNote {
    private String noteId;
    private Student student;
    private Date date;
    private Teacher teacher;
    private String description;
    private Date creationDate;

    /**
     * Constructor as per class diagram.
     * noteId and creationDate are generated internally.
     */
    public DisciplinaryNote(Student student, Date date, Teacher teacher, String description) {
        this.student = student;
        this.date = date;
        this.teacher = teacher;
        this.description = description;
        this.noteId = generateNoteId();
        this.creationDate = new Date();
    }

    public String getNoteId() {
        return noteId;
    }

    public Student getStudent() {
        return student;
    }

    public Date getDate() {
        return date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Validates the disciplinary note.
     * Throws an exception if validation fails.
     */
    public void validate() {
        if (student == null) {
            throw new IllegalStateException("Student cannot be null");
        }
        if (date == null) {
            throw new IllegalStateException("Date cannot be null");
        }
        if (teacher == null) {
            throw new IllegalStateException("Teacher cannot be null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalStateException("Description cannot be empty");
        }
    }

    private String generateNoteId() {
        return "NOTE-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
}