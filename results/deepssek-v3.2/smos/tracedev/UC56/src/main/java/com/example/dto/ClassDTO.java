package com.example.dto;

/**
 * Data Transfer Object for Class.
 */
public class ClassDTO {
    private String id;
    private String name;
    private String professorName;

    public ClassDTO() {
    }

    public ClassDTO(String id, String name, String professorName) {
        this.id = id;
        this.name = name;
        this.professorName = professorName;
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

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
}