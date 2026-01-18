package com.absenceapp;

/**
 * Represents a Student entity.
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

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Retrieves the parent's email address.
     *
     * @return The parent's email address.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                '}';
    }
}