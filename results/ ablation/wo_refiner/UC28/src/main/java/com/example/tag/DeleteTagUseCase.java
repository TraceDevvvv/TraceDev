package com.example.tag;

import com.example.utils.Logger;
import java.util.List;

/**
 * Use case for deleting tags.
 */
public class DeleteTagUseCase {
    private TagRepository tagRepository;

    public DeleteTagUseCase(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Deletes multiple tags by their IDs. REQ-008, REQ-009
     * @param tagIds list of tag IDs to delete
     */
    public void deleteTags(List<Long> tagIds) {
        // Validate all tags exist before deletion
        for (Long tagId : tagIds) {
            if (!tagRepository.existsById(tagId)) {
                throw new IllegalArgumentException("Tag with id " + tagId + " not found");
            }
        }
        // Delete each tag
        for (Long tagId : tagIds) {
            tagRepository.deleteById(tagId);
        }
        // Log success
        Logger.getInstance().logDeletionSuccess(tagIds);
    }

    /**
     * Search tags by criteria. Added to satisfy requirement REQ-007
     * @param criteria search string
     * @return list of matching tags
     */
    public List<Tag> searchTags(String criteria) {
        return tagRepository.searchTags(criteria);
    }
}