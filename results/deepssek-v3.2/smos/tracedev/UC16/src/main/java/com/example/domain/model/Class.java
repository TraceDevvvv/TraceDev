package com.example.domain.model;

/**
 * Represents a Class entity.
 */
public class Class {
    private String id;
    private String name;
    private boolean archived;

    public Class(String id, String name, boolean archived) {
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

    /**
     * Marks the class for deletion (sets archived to true).
     * Updated from delete() to clarify state change.
     */
    public void markForDeletion() {
        this.archived = true;
    }

    public void delete() {
        this.archived = true;
    }
}