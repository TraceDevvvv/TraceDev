package com.example.model;

/**
 * Represents a teacher in the system.
 */
public class Teacher {
    private String id;
    private String name;
    private String employeeId;

    public Teacher(String id, String name, String employeeId) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    // Setters (if needed for updates, though not explicitly in diagram)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", employeeId='" + employeeId + '\'' +
               '}';
    }
}