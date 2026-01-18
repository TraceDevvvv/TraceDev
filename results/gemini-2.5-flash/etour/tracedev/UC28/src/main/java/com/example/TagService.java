package com.example;

import java.util.List;

/**
 * Concrete implementation of ITagService.
 * This class orchestrates business logic and interacts with the ITagRepository.
 * It handles the flow of getting and deleting tags, propagating any connection exceptions.
 */
public class TagService implements ITagService {

    private final ITagRepository tagRepository;

    /**
     * Constructs a TagService with a specific ITagRepository dependency.
     * @param tagRepository The repository used for tag data access.
     */
    public TagService(ITagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Retrieves a list of all existing tags by delegating to the repository.
     *
     * @return A list of Tag objects.
     * @throws ConnectionException if the repository operation fails due to a connection issue.
     */
    @Override
    public List<Tag> getExistingTags() throws ConnectionException {
        System.out.println("TagService: Requesting existing tags from repository.");
        // Step 2: System researches tags (delegated to repository)
        List<Tag> tags = tagRepository.findAll();
        System.out.println("TagService: Received " + tags.size() + " tags from repository.");
        return tags;
    }

    /**
     * Deletes a list of tags by delegating to the repository.
     *
     * @param tagIds A list of String IDs of the tags to be deleted.
     * @throws ConnectionException if the repository operation fails due to a connection issue.
     */
    @Override
    public void deleteTags(List<String> tagIds) throws ConnectionException {
        System.out.println("TagService: Requesting deletion of tags with IDs: " + tagIds);
        // Step 6: System deletes selected tags (delegated to repository)
        // Quality Req: Ensures data integrity (handled by repository's simulated deletion)
        tagRepository.deleteByIds(tagIds);
        System.out.println("TagService: Tags deleted successfully.");
    }
}