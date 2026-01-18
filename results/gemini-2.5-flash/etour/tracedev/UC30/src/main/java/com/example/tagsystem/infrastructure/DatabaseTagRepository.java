package com.example.tagsystem.infrastructure;

import com.example.tagsystem.domain.Tag;
import com.example.tagsystem.domain.TagRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Concrete implementation of TagRepository that simulates interaction with a database.
 * Uses an in-memory HashMap for demonstration purposes.
 */
public class DatabaseTagRepository implements TagRepository {
    // Simulates a database table for tags using a HashMap
    // Key: Tag ID, Value: Tag object
    private final Map<String, Tag> tagStore = new HashMap<>();
    // Secondary index for quick lookup by name (case-insensitive for this example)
    // Key: Tag Name (lowercase), Value: Tag object
    private final Map<String, Tag> tagStoreByName = new HashMap<>();

    public DatabaseTagRepository() {
        // Pre-populate with some data for testing
        save(new Tag("existing-tag-123", "Java"));
        save(new Tag("existing-tag-456", "Spring Boot"));
    }

    /**
     * Finds a Tag by its name in the simulated database.
     * @param tagName The name of the tag to find.
     * @return The Tag object if found, null otherwise.
     */
    @Override
    public Tag findByTagName(String tagName) {
        System.out.println("[DatabaseTagRepository] Searching for tag by name: '" + tagName + "' in DB.");
        // Simulate database query: SELECT * FROM Tags WHERE name = ?
        Tag foundTag = tagStoreByName.get(tagName.toLowerCase());
        if (foundTag != null) {
            System.out.println("[DatabaseTagRepository] Found tag: " + foundTag.getName());
        } else {
            System.out.println("[DatabaseTagRepository] Tag '" + tagName + "' not found in DB.");
        }
        return foundTag;
    }

    /**
     * Saves a Tag to the simulated database.
     * If the tag is new (no ID or ID not present in store), it's inserted.
     * If an ID exists, it's treated as an update (though this simple implementation
     * doesn't explicitly handle updates beyond replacing the object).
     * @param tag The Tag object to save.
     * @return The saved Tag object.
     */
    @Override
    public Tag save(Tag tag) {
        System.out.println("[DatabaseTagRepository] Saving tag: '" + tag.getName() + "' to DB.");
        // Simulate database insert/update: INSERT INTO Tags (name) VALUES (?)
        // Or UPDATE Tags SET name=? WHERE id=?

        // If it's a new tag, make sure it has an ID
        if (tag.getId() == null || tag.getId().isEmpty()) {
            // This should ideally not happen if Tag constructor already assigns UUID,
            // but as a safeguard.
            tag = new Tag(tag.getName()); // Creates a new Tag with a new ID
        }

        tagStore.put(tag.getId(), tag);
        tagStoreByName.put(tag.getName().toLowerCase(), tag); // Update secondary index
        System.out.println("[DatabaseTagRepository] Tag saved. Current tags in DB: " + tagStore.size());
        return tag;
    }
}