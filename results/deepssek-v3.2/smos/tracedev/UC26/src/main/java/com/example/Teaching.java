package com.example;

/**
 * Represents a Teaching entity with basic information and archive status.
 * As per the class diagram.
 */
public class Teaching {
    private String id;
    private String title;
    private String description;
    private boolean archived;

    public Teaching(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.archived = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return archived;
    }

    /**
     * Archives the teaching, marking it as archived.
     */
    public void archive() {
        this.archived = true;
    }

    /**
     * Deletes the teaching (as per class diagram).
     */
    public void delete() {
        // Implementation for delete method.
        // Since Teaching is a JPA entity, deletion is typically handled by the repository.
        // This method could mark the entity as deleted or perform any cleanup.
        // For now, we mark it as archived (soft delete).
        this.archived = true;
    }
}