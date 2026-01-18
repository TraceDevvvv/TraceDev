package com.example.dto;

/**
 * Data Transfer Object for Class.
 */
public class ClassDTO {
    private String classId;
    private String className;
    private String academicYear;

    public ClassDTO() {}

    public ClassDTO(String classId, String className, String academicYear) {
        this.classId = classId;
        this.className = className;
        this.academicYear = academicYear;
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
}