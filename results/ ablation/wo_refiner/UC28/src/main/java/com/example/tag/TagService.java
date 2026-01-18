package com.example.tag;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service orchestrating tag management operations following business logic.
 */
public class TagService {
    private DeleteTagUseCase deleteTagUseCase;
    private TagRepository tagRepository;

    public TagService(DeleteTagUseCase deleteTagUseCase, TagRepository tagRepository) {
        this.deleteTagUseCase = deleteTagUseCase;
        this.tagRepository = tagRepository;
    }

    /**
     * Retrieves all tags as DTOs.
     * @return list of TagDto
     */
    public List<TagDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return convertToDto(tags);
    }

    /**
     * Deletes tags by IDs and returns a notification.
     * @param tagIds list of tag IDs
     * @return Notification object
     */
    public DeleteTagNotification deleteTags(List<Long> tagIds) {
        try {
            deleteTagUseCase.deleteTags(tagIds);
            return new DeleteTagNotification("Tags deleted successfully", true);
        } catch (IllegalArgumentException e) {
            return new DeleteTagNotification(e.getMessage(), false);
        } catch (Exception e) {
            return new DeleteTagNotification("Error deleting tags: " + e.getMessage(), false);
        }
    }

    /**
     * Searches tags by criteria and returns DTOs.
     * Added to satisfy requirement REQ-007
     * @param criteria search string
     * @return list of TagDto
     */
    public List<TagDto> searchTags(String criteria) {
        List<Tag> tags = tagRepository.searchTags(criteria);
        return convertToDto(tags);
    }

    /**
     * Converts Tag entities to TagDto objects.
     * Added to satisfy requirement REQ-006, REQ-007
     * @param tags list of Tag entities
     * @return list of TagDto
     */
    public List<TagDto> convertToDto(List<Tag> tags) {
        return tags.stream()
                .map(TagDto::new)
                .collect(Collectors.toList());
    }
}