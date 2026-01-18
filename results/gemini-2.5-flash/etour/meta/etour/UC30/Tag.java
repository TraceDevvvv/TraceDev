package com.example.inserttag;

/**
 * Represents a Tag object with a name and an optional description.
 * This class is a simple data structure to hold tag information.
 */
public class Tag {
    private String name;
    private String description;

    /**
     * Constructs a new Tag with the specified name and description.
     *
     * @param name The name of the tag. Must not be null or empty.
     * @param description An optional description for the tag. Can be null or empty.
     */
    public Tag(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty.");
        }
        this.name = name.trim();
        this.description = (description == null) ? "" : description.trim();
    }

    /**
     * Returns the name of the tag.
     *
     * @return The tag's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tag.
     *
     * @param name The new name for the tag. Must not be null or empty.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be null or empty.");
        }
        this.name = name.trim();
    }

    /**
     * Returns the description of the tag.
     *
     * @return The tag's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the tag.
     *
     * @param description The new description for the tag. Can be null or empty.
     */
    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    /**
     * Provides a string representation of the Tag object.
     *
     * @return A string in the format "Tag{name='...', description='...'}".
     */
    @Override
    public String toString() {
        return "Tag{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }

    /**
     * Compares this Tag object to another object for equality.
     * Two tags are considered equal if their names are equal (case-insensitive).
     *
     * @param o The object to compare with.
     * @return true if the given object is a Tag and its name matches this tag's name, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return name.equalsIgnoreCase(tag.name);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the tag's name (case-insensitive).
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}