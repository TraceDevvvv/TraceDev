package com.example.tagsystem.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Tag entity in the domain model.
 * A tag has a unique ID and a name.
 */
public class Tag {
    private String id;
    private String name;

    /**
     * Constructor for creating a new Tag.
     * A new ID is generated upon creation.
     * @param name The name of the tag.
     */
    public Tag(String name) {
        // Generate a unique ID for the tag upon creation
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * Constructor for loading an existing Tag from persistence.
     * @param id The unique ID of the tag.
     * @param name The name of the tag.
     */
    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the unique ID of the tag.
     * @return The tag's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the name of the tag.
     * @return The tag's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides the equals method for Tag objects.
     * Two tags are considered equal if they have the same ID.
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tag tag = (Tag) obj;
        return Objects.equals(id, tag.id);
    }

    /**
     * Overrides the hashCode method for Tag objects.
     * Consistent with equals; generates hash based on ID.
     * @return The hash code for this Tag.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Provides a string representation of the Tag object.
     * @return A string containing the tag's ID and name.
     */
    @Override
    public String toString() {
        return "Tag{id='" + id + "', name='" + name + "'}";
    }
}