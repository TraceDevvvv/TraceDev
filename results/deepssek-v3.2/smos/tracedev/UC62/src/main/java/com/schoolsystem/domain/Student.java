package com.schoolsystem.domain;

import java.util.Objects;

/**
 * Represents a Student in the system.
 */
public class Student {
    private String studentId;
    private String name;
    private String gradeLevel;

    public Student(String studentId, String name, String gradeLevel) {
        this.studentId = studentId;
        this.name = name;
        this.gradeLevel = gradeLevel;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentId);
    }
}