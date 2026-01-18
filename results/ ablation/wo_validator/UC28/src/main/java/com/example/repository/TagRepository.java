package com.example.repository;

import com.example.model.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for managing Tag entities.
 * Simulates a data store (e.g., database, in-memory list).
 */
public class TagRepository {
    // In-memory storage for demonstration
    private List<Tag> tagStorage = new ArrayList<>();

    /**
     * Retrieves all tags.
     * @return a list of all tags.
     */
    public List<Tag> findAll() {
        return new ArrayList<>(tagStorage); // Return a copy to avoid external modification
    }

    /**
     * Deletes a specific tag by its ID.
     * @param tag the Tag to delete.
     */
    public void delete(Tag tag) {
        // Find the tag by ID and remove it
        tagStorage.removeIf(t -> t.getId().equals(tag.getId()));
        tag.delete(); // Call delete method on the tag
    }

    /**
     * Deletes all tags in the provided list.
     * @param tags list of tags to delete.
     */
    public void deleteAll(List<Tag> tags) {
        for (Tag tag : tags) {
            delete(tag);
        }
    }

    /**
     * Saves a tag (adds if new, updates if existing).
     * For simplicity, assumes tag has a unique ID.
     * @param tag the tag to save.
     * @return the saved tag.
     */
    public Tag save(Tag tag) {
        // Remove existing tag with same ID if present
        tagStorage.removeIf(t -> t.getId().equals(tag.getId()));
        tagStorage.add(tag);
        return tag;
    }

    /**
     * Helper method to find a tag by ID (used in delete operation).
     * @param tagId the ID of the tag to find.
     * @return the Tag if found, null otherwise.
     */
    public Tag findById(String tagId) {
        return tagStorage.stream()
                .filter(tag -> tag.getId().equals(tagId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Deletes a tag by its ID (called from controller).
     * @param tagId the ID of the tag to delete.
     */
    public void delete(String tagId) {
        Tag tag = findById(tagId);
        if (tag != null) {
            delete(tag);
        }
    }
}