package com.example.domain;

import java.util.List;

/**
 * Represents a student entity in the domain.
 */
public class Student {
    private String id;
    private String name;
    
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Gets the list of reports for this student.
     * In a real implementation, this would fetch from a repository.
     */
    public List<Report> getReports() {
        // Placeholder: actual implementation would fetch from repository
        return null;
    }
}