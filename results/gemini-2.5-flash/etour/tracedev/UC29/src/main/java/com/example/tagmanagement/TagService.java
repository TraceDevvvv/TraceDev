package com.example.tagmanagement;

/**
 * Provides business logic related to Tag operations.
 * Uses TagRepository to interact with tag data.
 */
public class TagService {
    private final TagRepository tagRepository; // Dependency on TagRepository

    /**
     * Constructs a TagService with a given TagRepository.
     *
     * @param tagRepository The repository used to access tag data.
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Checks if a tag with the given name already exists in the system.
     *
     * @param tagName The name of the tag to check.
     * @return true if a tag with the name exists, false otherwise.
     */
    public boolean tagExists(String tagName) {
        System.out.println("[TagService] Checking if tag '" + tagName + "' exists.");
        // Delegates to the repository to find the tag
        return tagRepository.findByName(tagName) != null;
    }
}