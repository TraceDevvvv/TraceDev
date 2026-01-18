package com.example.model;

/**
 * Represents a Class entity with its attributes.
 */
public class Class {
    private String classId;
    private String className;
    private String academicYear;
    private String teacherId;

    public Class(String classId, String className, String academicYear, String teacherId) {
        this.classId = classId;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}