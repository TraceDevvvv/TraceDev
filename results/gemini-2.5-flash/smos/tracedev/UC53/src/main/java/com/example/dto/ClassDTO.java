package com.example.dto;

/**
 * Data Transfer Object (DTO) for Class information.
 * Used to transfer class data between layers, often representing a simplified or
 * aggregated view of a ClassEntity.
 */
public class ClassDTO {
    public String id;
    public String name;
    public String description;
    public String accessKey;

    /**
     * Default constructor.
     */
    public ClassDTO() {
    }

    /**
     * Constructs a new ClassDTO with specified details.
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param description A brief description of the class.
     * @param accessKey The access key for the class.
     */
    public ClassDTO(String id, String name, String description, String accessKey) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.accessKey = accessKey;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", accessKey='" + accessKey + '\'' +
               '}';
    }
}