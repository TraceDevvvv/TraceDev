package hec.core;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a Tag in the domain.
 * Contains tag ID, name, description, and creation date.
 */
public class Tag {
    private final String tagId;
    private final String name;
    private final String description;
    private final LocalDateTime creationDate;

    /**
     * Constructor for Tag.
     * Generates a unique ID and sets creationDate to now.
     *
     * @param name        the name of the tag
     * @param description the description of the tag
     */
    public Tag(String name, String description) {
        this.tagId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Gets the tag's unique identifier.
     *
     * @return the tag ID
     */
    public String getId() {
        return tagId;
    }

    /**
     * Gets the tag's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the tag's description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the tag's creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Validates the tag's data.
     *
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        return name != null && !name.trim().isEmpty() &&
               description != null && !description.trim().isEmpty();
    }
}