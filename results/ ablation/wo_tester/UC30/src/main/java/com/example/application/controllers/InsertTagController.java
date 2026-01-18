package com.example.application.controllers;

import com.example.application.dto.InsertTagRequest;
import com.example.application.dto.InsertTagResponse;
import com.example.application.interfaces.EtourServerAdapter;
import com.example.application.interfaces.TagRepository;
import com.example.application.serv.TagValidationService;
import com.example.application.serv.ValidationResult;
import com.example.domain.entities.Tag;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller for handling insert tag use case.
 */
public class InsertTagController {
    private TagRepository tagRepository;
    private TagValidationService validationService;
    private EtourServerAdapter etourServerAdapter;
    private ExistingErrorTagController existingErrorTagController;
    private ErroredController erroredController;

    // Constructor with dependency injection
    public InsertTagController(TagRepository tagRepository,
                               TagValidationService validationService,
                               EtourServerAdapter etourServerAdapter,
                               ExistingErrorTagController existingErrorTagController,
                               ErroredController erroredController) {
        this.tagRepository = tagRepository;
        this.validationService = validationService;
        this.etourServerAdapter = etourServerAdapter;
        this.existingErrorTagController = existingErrorTagController;
        this.erroredController = erroredController;
    }

    /**
     * Main method to insert a tag as per sequence diagram.
     */
    public InsertTagResponse insertTag(InsertTagRequest request) {
        // Step: Validate request
        ValidationResult validationResult = validationService.validate(request, tagRepository);

        if (validationResult.isValid()) {
            // Step: Check if tag already exists (even though validation already did, we double-check for clarity)
            Optional<Tag> existingTag = tagRepository.findByName(request.getTagName());
            if (existingTag.isPresent()) {
                // Tag already exists -> activate ExistingErrorTag use case
                existingErrorTagController.handle(request.getTagName());
                return new InsertTagResponse(false, "Tag already exists");
            } else {
                // Tag does not exist -> create and save
                Tag newTag = createTag(request.getTagName(), request.getDescription());
                tagRepository.save(newTag);

                // Notify ETOUR server
                try {
                    etourServerAdapter.notifyTagCreated(newTag);
                } catch (Exception e) {
                    // Handled in adapter, but we could log here if needed.
                }

                return new InsertTagResponse(true, "Tag created");
            }
        } else {
            // Data invalid -> activate Errored use case
            erroredController.handle(validationResult.getErrors());
            return new InsertTagResponse(false, "Invalid data");
        }
    }

    /**
     * Helper method to create a Tag instance.
     * Added to satisfy consistency with sequence diagram.
     */
    public Tag createTag(String name, String description) {
        String id = UUID.randomUUID().toString();
        Date createdAt = new Date();
        boolean isNotified = false; // Initially false, set true after successful notification
        return new Tag(id, name, description, createdAt, isNotified);
    }
}