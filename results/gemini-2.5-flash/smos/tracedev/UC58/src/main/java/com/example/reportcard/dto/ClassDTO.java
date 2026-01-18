package com.example.reportcard.dto;

/**
 * DTO for transferring Class information.
 * Used for displaying classes to the user.
 */
public class ClassDTO {
    private String id;
    private String name;

    public ClassDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}