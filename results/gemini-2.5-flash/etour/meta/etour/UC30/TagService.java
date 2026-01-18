package com.example.inserttag;

import java.util.Optional;

/**
 * Service layer for handling business logic related to Tag operations.
 * This class orchestrates the validation, persistence, and error handling
 * for inserting new tags, adhering to the 'InsertTag' use case.
 */
public class TagService {

    private final TagRepository tagRepository;

    /**
     * Constructs a new TagService with a given TagRepository.
     *
     * @param tagRepository The repository responsible for tag data persistence.
     */
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Inserts a new tag into the system.
     * This method encapsulates the core logic of the 'InsertTag' use case:
     * 1. Validates the provided tag data.
     * 2. Checks if a tag with the same name already exists.
     * 3. Persists the tag if valid and unique.
     *
     * @param tagName The name of the tag to insert.
     * @param tagDescription An optional description for the tag.
     * @return The newly created and inserted Tag object.
     * @throws InvalidTagDataException If the tag name is null, empty, or otherwise invalid (activates Errored use case).
     * @throws TagAlreadyExistsException If a tag with the same name already exists (activates ExistingErrorTag use case).
     * @throws ServerConnectionException If there's an interruption in connection to the server (simulated ETOUR).
     */
    public Tag insertTag(String tagName, String tagDescription)
            throws InvalidTagDataException, TagAlreadyExistsException, ServerConnectionException {

        // Simulate potential server connection interruption (ETOUR)
        // This could be a random chance or based on external factors in a real system.
        if (Math.random() < 0.05) { // 5% chance of connection interruption
            throw new ServerConnectionException("Connection to the server interrupted (ETOUR). Please try again.");
        }

        // 4. Verify the data entered
        // Check for null or empty tag name, which is a fundamental requirement for a Tag object.
        if (tagName == null || tagName.trim().isEmpty()) {
            // Data is invalid or insufficient, activate Errored use case.
            throw new InvalidTagDataException("Tag name cannot be null or empty.");
        }

        // Create a Tag object for validation and potential storage.
        // The Tag constructor itself performs basic validation on the name.
        Tag newTag;
        try {
            newTag = new Tag(tagName, tagDescription);
        } catch (IllegalArgumentException e) {
            // Catching IllegalArgumentException from Tag constructor for invalid name
            throw new InvalidTagDataException("Invalid tag data provided: " + e.getMessage(), e);
        }

        // 4. Check if the tag is already present in the system.
        Optional<Tag> existingTag = tagRepository.findByName(newTag.getName());
        if (existingTag.isPresent()) {
            // Tag is already present, activate ExistingErrorTag use case.
            throw new TagAlreadyExistsException("Tag with name '" + newTag.getName() + "' already exists.");
        }

        // If validation passes and tag is unique, save the tag.
        // The repository's save method might return false if a race condition occurred
        // and another thread added the same tag, but our findByName check mitigates this
        // for this single-threaded simulation.
        boolean saved = tagRepository.save(newTag);
        if (!saved) {
            // This case should ideally be caught by findByName, but as a safeguard.
            throw new TagAlreadyExistsException("Failed to save tag, possibly due to a concurrent insertion of '" + newTag.getName() + "'.");
        }

        // Exit condition: Notification about the inclusion of the tag.
        // The returned Tag object serves as this notification.
        return newTag;
    }
}