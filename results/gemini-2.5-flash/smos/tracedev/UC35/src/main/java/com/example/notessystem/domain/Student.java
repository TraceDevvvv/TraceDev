package com.example.notessystem.domain;

import java.util.Objects;

/**
 * Represents a core business entity: a Student.
 * Added to satisfy requirement R2, R4, R5 from the class diagram notes.
 */
public class Student {
    private String studentId;
    private String name;
    // ... other student attributes

    /**
     * Constructs a new Student object.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // --- Getters ---
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    // --- Setters (if mutation is allowed) ---
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
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