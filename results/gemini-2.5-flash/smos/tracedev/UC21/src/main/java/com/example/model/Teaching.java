package com.example.model;

/**
 * Represents a teaching or lesson that can be associated with an address.
 */
public class Teaching {
    private String id;
    private String title;
    private String description;

    /**
     * Constructs a new Teaching instance.
     *
     * @param id          Unique identifier for the teaching.
     * @param title       Title of the teaching.
     * @param description Detailed description of the teaching.
     */
    public Teaching(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Standard equals and hashCode for object comparison, especially useful when managing lists
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return id.equals(teaching.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Teaching{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               '}';
    }
}