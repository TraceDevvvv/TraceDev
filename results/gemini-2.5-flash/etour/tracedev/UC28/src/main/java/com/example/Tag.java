package com.example;

import java.util.Objects;

/**
 * Represents a Tag entity with an ID and a name.
 * This class models the domain object for a tag.
 */
public class Tag {
    private String id;
    private String name;

    /**
     * Constructs a new Tag with the specified ID and name.
     * @param id The unique identifier for the tag.
     * @param name The name of the tag.
     */
    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique ID of the tag.
     * @return The tag's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the tag.
     * @return The tag's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Overrides the toString method for better representation of Tag objects.
     * @return A string representation of the Tag.
     */
    @Override
    public String toString() {
        return "Tag{id='" + id + "', name='" + name + "'}";
    }

    /**
     * Overrides the equals method to compare Tag objects based on their id.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     * @return The hash code for this Tag object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}