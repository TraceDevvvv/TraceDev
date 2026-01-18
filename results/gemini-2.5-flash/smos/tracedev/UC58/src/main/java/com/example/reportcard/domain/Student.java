package com.example.reportcard.domain;

/**
 * Domain entity representing a student.
 */
public class Student {
    private String id;
    private String name;
    private String classId;

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

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}