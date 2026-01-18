package com.example.smos.model;

/**
 * Represents a teaching entry in the SMOS archive.
 * Corresponds to the 'Teaching' class in the UML Class Diagram.
 */
public class Teaching {
    private String id;
    private String title;
    private String description;
    private String author;

    /**
     * Constructs a new Teaching object.
     * @param id Unique identifier for the teaching.
     * @param title Title of the teaching.
     * @param description Detailed description of the teaching.
     * @param author Author of the teaching.
     */
    public Teaching(String id, String title, String description, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    // Setter for ID (if needed, though typically IDs are immutable)
    public void setId(String id) {
        this.id = id;
    }

    // Setters for other attributes
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Teaching{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", author='" + author + '\'' +
               '}';
    }

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
}