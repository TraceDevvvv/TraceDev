package com.example.model;

/**
 * Represents a class entity in the system.
 */
public class ClassEntity {
    private String id;
    private String name;
    private String description;
    private String accessKey; // Unique key for accessing this class

    /**
     * Default constructor.
     */
    public ClassEntity() {
    }

    /**
     * Constructs a new ClassEntity with specified details.
     * @param id The unique identifier of the class.
     * @param name The name of the class.
     * @param description A brief description of the class.
     * @param accessKey The access key for the class.
     */
    public ClassEntity(String id, String name, String description, String accessKey) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.accessKey = accessKey;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAccessKey() {
        return accessKey;
    }

    // --- Setters ---
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", accessKey='" + accessKey + '\'' +
               '}';
    }
}