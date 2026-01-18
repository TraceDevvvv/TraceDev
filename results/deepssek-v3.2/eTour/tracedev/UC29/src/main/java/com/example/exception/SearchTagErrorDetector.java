package com.example.exception;

/**
 * Concrete error detector that checks for tag existence.
 */
public class SearchTagErrorDetector implements ErrorDetector {
    private TagRepository tagRepository;

    public SearchTagErrorDetector(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public boolean isErrorCondition(String searchTags) {
        System.out.println("Checking error condition for tags: " + searchTags);
        boolean exists = tagRepository.exists(searchTags);
        // Error condition is defined as tag already existing.
        return exists;
    }
}