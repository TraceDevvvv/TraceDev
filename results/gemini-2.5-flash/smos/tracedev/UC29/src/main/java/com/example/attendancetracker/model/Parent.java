package com.example.attendancetracker.model;

/**
 * Represents a parent in the system.
 */
public class Parent {
    private String parentId;
    public String parentName;
    public String email;

    public Parent(String parentId, String parentName, String email) {
        this.parentId = parentId;
        this.parentName = parentName;
        this.email = email;
    }

    // Getters for private fields
    public String getParentId() {
        return parentId;
    }

    public String getEmail() {
        return email;
    }
}