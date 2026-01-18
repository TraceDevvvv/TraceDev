package com.example.tagmanagement;

/**
 * Represents a Tag entity in the system.
 * This class holds basic information about a tag.
 */
public class Tag {
    private String id;   // Unique identifier for the tag
    private String name; // The name of the tag

    /**
     * Constructs a new Tag instance.
     *
     * @param id The unique ID of the tag.
     * @param name The name of the tag.
     */
    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique ID of the tag.
     *
     * @return The tag's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the tag.
     *
     * @return The tag's name.
     */
    public String getName() {
        return name;
    }

    // No setters provided as tags are assumed to be immutable once created for this context.
}