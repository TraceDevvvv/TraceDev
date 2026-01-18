package com.example.studentdelay.domain;

import java.util.Objects;

/**
 * Represents a Student in the domain model.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentContactInfo;

    public Student(String studentId, String name, String parentContactInfo) {
        this.studentId = studentId;
        this.name = name;
        this.parentContactInfo = parentContactInfo;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getParentContactInfo() {
        return parentContactInfo;
    }

    // Setters (not strictly required by diagram, but useful for instantiation)
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentContactInfo(String parentContactInfo) {
        this.parentContactInfo = parentContactInfo;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", parentContactInfo='" + parentContactInfo + '\'' +
               '}';
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
        return Objects.hash(studentId);
    }
}