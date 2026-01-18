package com.example.model;

/**
 * Data Transfer Object (DTO) for Teaching entities.
 * Used for transferring data between layers, particularly UI and service layer.
 */
public class TeachingDTO {
    public String id;
    public String name;
    public String description;

    /**
     * Constructs a new TeachingDTO.
     * @param id The unique identifier of the teaching.
     * @param name The name of the teaching.
     * @param description The description of the teaching.
     */
    public TeachingDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TeachingDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}