package com.example.application.dto;

/**
 * Data Transfer Object for Class.
 */
public class ClassDTO {
    private String id;
    private String name;
    private boolean archived;

    public ClassDTO(String id, String name, boolean archived) {
        this.id = id;
        this.name = name;
        this.archived = archived;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isArchived() {
        return archived;
    }
}