package com.example.tagsystem.application;

import com.example.tagsystem.domain.Tag;
import com.example.tagsystem.domain.TagRepository;
import com.example.tagsystem.errorhandling.ErrorHandlingService;
import com.example.tagsystem.errorhandling.InvalidTagDataException;
import com.example.tagsystem.errorhandling.TagAlreadyExistsException;

/**
 * Application service responsible for inserting new tags.
 * It orchestrates domain logic and interacts with the repository.
 */
public class InsertTagService {
    // Dependency: TagRepository, for data persistence operations
    public TagRepository tagRepository;
    // Dependency: ErrorHandlingService for specific error use cases
    private ErrorHandlingService errorHandlingService;

    /**
     * Constructor for InsertTagService.
     * @param tagRepository The repository for Tag entities.
     * @param errorHandlingService The service for activating error use cases.
     */
    public InsertTagService(TagRepository tagRepository, ErrorHandlingService errorHandlingService) {
        this.tagRepository = tagRepository;
        this.errorHandlingService = errorHandlingService;
    }

    /**
     * Inserts a new tag into the system.
     * This method implements the core logic of the "Insert new tag" use case.
     * @param tagName The name of the tag to be inserted.
     * @return The newly created Tag object.
     * @throws InvalidTagDataException if the tag name is invalid.
     * @throws TagAlreadyExistsException if a tag with the same name already exists.
     */
    public Tag insertTag(String tagName) throws InvalidTagDataException, TagAlreadyExistsException {
        System.out.println("[InsertTagService] Attempting to insert tag: '" + tagName + "'");

        // 1. Validate tag data
        try {
            validateTagData(tagName);
        } catch (InvalidTagDataException e) {
            // Activate the generic errored use case for invalid data
            errorHandlingService.activateErroredUseCase("Invalid data provided: " + e.getMessage());
            throw e; // Re-throw the exception to be handled by the controller
        }

        // 2. Check for tag uniqueness
        Tag existingTag = tagRepository.findByTagName(tagName);
        if (existingTag != null) {
            // Activate the specific "Tag already exists" error use case
            errorHandlingService.activateErroreTagEsistenteUseCase("Tag '" + tagName + "' already exists.");
            throw new TagAlreadyExistsException("A tag with name '" + tagName + "' already exists.");
        }

        // 3. Create new Tag object
        Tag newTag = new Tag(tagName);
        System.out.println("[InsertTagService] Created new Tag object: " + newTag.getName());

        // 4. Save the new tag
        Tag savedTag = tagRepository.save(newTag);
        System.out.println("[InsertTagService] Tag saved successfully: " + savedTag.getName() + " (ID: " + savedTag.getId() + ")");
        return savedTag;
    }

    /**
     * Validates the provided tag name.
     * @param tagName The name of the tag to validate.
     * @throws InvalidTagDataException if the tag name is null, empty, or fails other validation rules.
     */
    private void validateTagData(String tagName) throws InvalidTagDataException {
        System.out.println("[InsertTagService] Validating tag data for: '" + tagName + "'");
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new InvalidTagDataException("Tag name cannot be null or empty.");
        }
        // Additional validation rules could be added here (e.g., length, characters allowed)
        System.out.println("[InsertTagService] Tag data validation successful.");
    }
}