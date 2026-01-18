package com.example.tagmanagement;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages storage and retrieval of Tag objects.
 * This acts as a data access layer for Tags.
 */
public class TagRepository {
    // In-memory store for demonstration purposes
    private final Map<String, Tag> tags = new HashMap<>();

    public TagRepository() {
        // Populate with some dummy data for simulation
        tags.put("Java", new Tag("1", "Java"));
        tags.put("UML", new Tag("2", "UML"));
        tags.put("PlantUML", new Tag("3", "PlantUML"));
    }

    /**
     * Finds a Tag by its name.
     *
     * @param name The name of the tag to find.
     * @return The Tag object if found, otherwise null.
     */
    public Tag findByName(String name) {
        // Simulate database lookup
        System.out.println("[TagRepository] Searching for tag with name: " + name);
        return tags.get(name);
    }
}