package com.example.model;

/**
 * Represents a Teaching entity in the system.
 */
public class Teaching {
    private String id;
    private String name;
    private String description;

    /**
     * Constructs a new Teaching entity.
     * @param id The unique identifier for the teaching.
     * @param name The name of the teaching.
     * @param description A detailed description of the teaching.
     */
    public Teaching(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Updates the details of the teaching using data from a TeachingDTO.
     * The ID is assumed to be immutable and is not updated.
     * @param editedTeachingDTO The DTO containing the updated name and description.
     */
    public void updateDetails(TeachingDTO editedTeachingDTO) {
        if (editedTeachingDTO != null) {
            this.name = editedTeachingDTO.name;
            this.description = editedTeachingDTO.description;
        }
    }

    /**
     * Validates if the current Teaching entity has valid data.
     * For simplicity, checks if name and description are not null or empty.
     * @return true if valid, false otherwise.
     */
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               description != null && !description.trim().isEmpty();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Teaching{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}