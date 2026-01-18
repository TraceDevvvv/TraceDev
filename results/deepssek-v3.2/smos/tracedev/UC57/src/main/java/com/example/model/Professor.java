package com.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a professor.
 */
public class Professor {
    private String professorId;
    private String name;

    public Professor(String professorId, String name) {
        this.professorId = professorId;
        this.name = name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean login(String username, String password) {
        // Implementation should check credentials, e.g., against a database
        // For now, a simple placeholder logic
        return username != null && password != null && username.length() > 0 && password.length() > 0;
    }
}