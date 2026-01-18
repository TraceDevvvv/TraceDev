package com.example.entity;

/**
 * Represents a TeachingAssignment entity.
 */
public class TeachingAssignment {
    private String id;
    private Teacher teacher;
    private Teaching teaching;
    private String academicYear;

    public TeachingAssignment() {}

    public TeachingAssignment(String id, Teacher teacher, Teaching teaching, String academicYear) {
        this.id = id;
        this.teacher = teacher;
        this.teaching = teaching;
        this.academicYear = academicYear;
    }

    public TeachingAssignment(Teacher teacher, Teaching teaching, String academicYear) {
        this.teacher = teacher;
        this.teaching = teaching;
        this.academicYear = academicYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teaching getTeaching() {
        return teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}