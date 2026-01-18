package com.example.entity;

import java.util.List;

/**
 * Represents a Class entity.
 */
public class Class {
    private String id;
    private String name;
    private String academicYear;

    public Class() {}

    public Class(String id, String name, String academicYear) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
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

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    /**
     * Returns the list of teachings offered by this class.
     * In a real implementation, this would likely be fetched from a repository.
     * @return a list of Teaching objects
     */
    public List<Teaching> getTeachings() {
        // Simulated implementation
        return List.of();
    }
}