package com.example.entity;

import java.util.List;

/**
 * Represents a Teacher entity.
 */
public class Teacher {
    private String id;
    private String name;

    public Teacher() {}

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
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

    /**
     * Simulates retrieving teachings for a given year.
     * In a real implementation, this would likely involve a repository call.
     * For simplicity, this returns an empty list.
     * @param year the academic year
     * @return a list of TeachingAssignment objects
     */
    public List<TeachingAssignment> getTeachingsForYear(String year) {
        // In a real implementation, this would query the TeachingAssignmentRepository
        return List.of();
    }
}