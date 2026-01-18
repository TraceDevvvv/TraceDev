package com.example.controller;

import com.example.model.Tag;
import com.example.repository.TagRepository;
import com.example.service.ETOURConnectionService;
import com.example.service.NotificationService;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for removing tags.
 * Manages interactions between the user, repository, and serv.
 */
public class RemoveTagController {
    private TagRepository tagRepository;
    private NotificationService notificationService;
    private ETOURConnectionService etourConnectionService;

    // Constructor with dependency injection (for flexibility)
    public RemoveTagController() {
        this.tagRepository = new TagRepository();
        this.notificationService = new NotificationService();
        this.etourConnectionService = new ETOURConnectionService();
    }

    // Constructor for testing or custom wiring
    public RemoveTagController(TagRepository tagRepository, 
                               NotificationService notificationService, 
                               ETOURConnectionService etourConnectionService) {
        this.tagRepository = tagRepository;
        this.notificationService = notificationService;
        this.etourConnectionService = etourConnectionService;
    }

    /**
     * Retrieves all tags.
     * @return list of all tags.
     */
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    /**
     * Deletes tags based on provided IDs.
     * Implements the sequence: validate, loop deletion, notify, disconnect.
     * @param tagIds list of tag IDs to delete.
     */
    public void deleteTags(List<String> tagIds) {
        // Validate tag IDs (opt block in sequence diagram)
        validateTagIds(tagIds);

        // Loop for each tagId and delete (loop in sequence diagram)
        for (String tagId : tagIds) {
            tagRepository.delete(tagId);
        }

        // Notify successful deletion
        notificationService.notifyDeletionSuccess();

        // Disconnect from ETOUR
        etourConnectionService.disconnect();
    }

    /**
     * Validates the list of tag IDs.
     * For simplicity, just checks for null/empty list.
     * @param tagIds the list of tag IDs to validate.
     */
    private void validateTagIds(List<String> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) {
            throw new IllegalArgumentException("Tag IDs list cannot be null or empty.");
        }
        // Additional validation could be added (e.g., format, existence)
    }
}