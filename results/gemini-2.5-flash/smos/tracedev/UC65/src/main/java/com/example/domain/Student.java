package com.example.domain;

/**
 * Domain model for a Student.
 */
public class Student {
    public String studentId;
    public String name;

    /**
     * Constructs a Student object.
     * @param studentId The unique ID of the student.
     * @param name The name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }
}