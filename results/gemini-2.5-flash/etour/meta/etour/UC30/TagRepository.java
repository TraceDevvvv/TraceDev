package com.example.inserttag;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Simulates a repository for Tag objects.
 * This class manages a collection of tags, providing methods to save and find tags.
 * In a real application, this would interact with a database.
 */
public class TagRepository {
    // Using a Set to store tags to ensure uniqueness based on the Tag's equals and hashCode methods (which rely on name).
    private final Set<Tag> tags;

    /**
     * Constructs a new TagRepository.
     * Initializes the internal storage for tags.
     */
    public TagRepository() {
        this.tags = new HashSet<>();
        // Optionally add some initial tags for testing purposes
        tags.add(new Tag("Java", "Programming language"));
        tags.add(new Tag("Python", "Scripting language"));
    }

    /**
     * Saves a new tag to the repository.
     * If a tag with the same name already exists (case-insensitive), it will not be added again.
     *
     * @param tag The Tag object to save.
     * @return true if the tag was added successfully, false if a tag with the same name already exists.
     */
    public boolean save(Tag tag) {
        // The add method of HashSet uses the equals and hashCode methods of Tag
        // to determine if an object is already present.
        return tags.add(tag);
    }

    /**
     * Finds a tag by its name (case-insensitive).
     *
     * @param name The name of the tag to find.
     * @return An Optional containing the Tag if found, or an empty Optional if not found.
     */
    public Optional<Tag> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.empty();
        }
        String searchName = name.trim();
        for (Tag tag : tags) {
            if (tag.getName().equalsIgnoreCase(searchName)) {
                return Optional.of(tag);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all tags currently stored in the repository.
     *
     * @return A Set of all tags.
     */
    public Set<Tag> findAll() {
        return new HashSet<>(tags); // Return a copy to prevent external modification
    }

    /**
     * Clears all tags from the repository.
     * This method is primarily for testing or specific administrative functions.
     */
    public void clear() {
        tags.clear();
    }
}