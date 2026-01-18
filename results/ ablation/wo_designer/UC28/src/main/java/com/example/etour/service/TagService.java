package com.example.etour.service;

import com.example.etour.model.Tag;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Service layer for Tag operations.
 */
public class TagService {
    // In-memory storage simulating a database.
    private List<Tag> tagStorage;

    public TagService() {
        // Initialize with some sample tags.
        tagStorage = new ArrayList<>();
        tagStorage.add(new Tag(1, "Adventure"));
        tagStorage.add(new Tag(2, "Cultural"));
        tagStorage.add(new Tag(3, "Beach"));
        tagStorage.add(new Tag(4, "Mountain"));
        tagStorage.add(new Tag(5, "City"));
    }

    /**
     * Retrieves all tags in the system.
     * @return List of all tags.
     */
    public List<Tag> getAllTags() {
        return new ArrayList<>(tagStorage); // Return a copy to avoid external modification.
    }

    /**
     * Deletes tags by their IDs.
     * @param tagIds List of tag IDs to delete.
     * @return List of tags that were successfully deleted.
     * @throws RuntimeException if connection to server is interrupted (simulated).
     */
    public List<Tag> deleteTags(List<Integer> tagIds) {
        // Simulate connection interruption with a random chance.
        if (Math.random() < 0.1) { // 10% chance to simulate interruption.
            throw new RuntimeException("Connection to the server ETOUR is interrupted.");
        }

        List<Tag> deleted = new ArrayList<>();
        List<Tag> remaining = new ArrayList<>();
        for (Tag tag : tagStorage) {
            if (tagIds.contains(tag.getId())) {
                deleted.add(tag);
            } else {
                remaining.add(tag);
            }
        }
        tagStorage = remaining;
        return deleted;
    }
}