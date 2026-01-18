package controller;

import application.*;
import domain.Tag;
import presentation.*;
import java.util.Arrays;

/**
 * Controller for tag search insertion, orchestrating the flow.
 */
public class TagSearchController {
    private TagService tagService;
    private ExistingErrorTagUseCase existingErrorTagUseCase;
    private ErroredUseCase erroredUseCase;

    public TagSearchController(TagService tagService,
                               ExistingErrorTagUseCase existingErrorTagUseCase,
                               ErroredUseCase erroredUseCase) {
        this.tagService = tagService;
        this.existingErrorTagUseCase = existingErrorTagUseCase;
        this.erroredUseCase = erroredUseCase;
    }

    /**
     * Main method called from UI to insert a tag search.
     * Follows the sequence diagram logic.
     */
    public InsertTagResultDTO insertTagSearch(TagFormDTO tagData) {
        // Step 5: Validate data
        ValidationResult validationResult = tagService.validateTagData(tagData);
        if (!validationResult.isValid()) {
            return new InsertTagResultDTO(false,
                    "Validation failed: " + String.join(", ", validationResult.getErrors()),
                    null,
                    "VALIDATION_ERROR");
        }

        // Step 6: Check if tag exists
        boolean exists = tagService.checkTagExists(tagData.getName());
        if (exists) {
            // Step 7: Activate ExistingErrorTagUseCase
            ErrorDetailsDTO errorDetails = existingErrorTagUseCase.execute(tagData.getName());
            // Step 8: Activate ErroredUseCase
            erroredUseCase.execute(errorDetails);
            return new InsertTagResultDTO(false,
                    "Tag with name '" + tagData.getName() + "' already exists.",
                    null,
                    "DUPLICATE_TAG");
        }

        // Normal flow: create and save tag
        Tag savedTag = tagService.createTag(tagData);
        return new InsertTagResultDTO(true,
                "Tag created successfully.",
                savedTag.getId(),
                null);
    }
}