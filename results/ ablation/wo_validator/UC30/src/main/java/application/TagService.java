
package application;

import domain.Tag;
import domain.TagRepository;
import presentation.TagFormDTO;
import presentation.ValidationResult;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service for tag-related business logic.
 */
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Validates the tag form data.
     */
    public ValidationResult validateTagData(TagFormDTO tagData) {
        List<String> errors = new ArrayList<>();
        if (tagData.getName() == null || tagData.getName().trim().isEmpty()) {
            errors.add("Tag name is required.");
        }
        if (tagData.getDescription() == null || tagData.getDescription().trim().isEmpty()) {
            errors.add("Tag description is required.");
        }
        return new ValidationResult(errors.isEmpty(), errors);
    }

    /**
     * Checks if a tag with the given name already exists.
     */
    public boolean checkTagExists(String name) {
        return tagRepository.existsByName(name);
    }

    /**
     * Creates a new tag from DTO and saves it.
     */
    public Tag createTag(TagFormDTO tagData) {
        Tag newTag = new Tag(tagData.getName(),
                            tagData.getDescription());
        return tagRepository.save(newTag);
    }
}
