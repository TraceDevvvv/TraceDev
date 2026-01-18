package com.example.domain;

import java.util.Date;

/**
 * Domain entity representing a Note.
 */
public class Note {
    private String id;
    private Student student;
    private String description;
    private Teacher teacher;
    private Date date;

    public Note(String id, Student student, String description, Teacher teacher, Date date) {
        this.id = id;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public String getDescription() {
        return description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Date getDate() {
        return date;
    }
}