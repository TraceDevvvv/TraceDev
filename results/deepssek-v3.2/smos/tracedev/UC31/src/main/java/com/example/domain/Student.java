package com.example.domain;

/**
 * Represents a student entity.
 */
public class Student {
    private String id;
    private String name;
    private String parentEmail;

    public Student(String id, String name, String parentEmail) {
        this.id = id;
        this.name = name;
        this.parentEmail = parentEmail;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}