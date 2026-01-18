package com.example.tagsystem.domain;

/**
 * Interface for Tag data access operations.
 * Defines the contract for repositories that manage Tag entities.
 */
public interface TagRepository {

    /**
     * Finds a Tag by its name.
     * @param tagName The name of the tag to find.
     * @return The Tag object if found, null otherwise.
     */
    Tag findByTagName(String tagName);

    /**
     * Saves a Tag (either new or existing) to the persistence store.
     * @param tag The Tag object to save.
     * @return The saved Tag object (potentially with updated fields like ID if it was new).
     */
    Tag save(Tag tag);
}