package com.example.dtos;

/**
 * Data Transfer Object for registry summary.
 */
public class RegistrySummaryDTO {
    private String className;
    private String teacherName;
    private int studentCount;

    public RegistrySummaryDTO(String className, String teacherName, int studentCount) {
        this.className = className;
        this.teacherName = teacherName;
        this.studentCount = studentCount;
    }

    public String getClassName() {
        return className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getStudentCount() {
        return studentCount;
    }
}