package com.example.reportcard.domain;

/**
 * Represents a student in the domain model.
 */
public class Student {
    public String id;
    public String name;
    public String classId; // Foreign key to Class

    /**
     * Constructs a new Student.
     *
     * @param id The unique identifier for the student.
     * @param name The name of the student.
     * @param classId The ID of the class this student belongs to.
     */
    public Student(String id, String name, String classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }

    @Override
    public String toString() {
        return "Student{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}