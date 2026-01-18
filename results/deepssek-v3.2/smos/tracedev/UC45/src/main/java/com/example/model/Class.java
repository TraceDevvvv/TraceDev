package com.example.model;

import java.util.List;

/**
 * Entity representing a class.
 */
public class Class {
    private String classId;
    private String className;
    private String academicYear;
    private List<Student> students;

    public Class() {}

    public Class(String classId, String className, String academicYear, List<Student> students) {
        this.classId = classId;
        this.className = className;
        this.academicYear = academicYear;
        this.students = students;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}