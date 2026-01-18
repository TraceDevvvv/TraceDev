package com.example.school.domain;

/**
 * Represents a student in the school system.
 */
public class Student {
    public String id;
    public String name;
    public String classId;

    /**
     * Constructs a new Student.
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     * @param classId The ID of the class the student is enrolled in.
     */
    public Student(String id, String name, String classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }
}