package com.school.domain;

/**
 * Domain entity representing a Student.
 */
public class Student {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the parent email address.
     * Assumed to be derived from student email or separate field.
     * For simplicity, we return the student email with a prefix.
     */
    public String getParentEmail() {
        return "parent." + email;
    }
}