package com.example.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages tag validation and duplicates.
 */
public class TagManager {
    private List<String> existingTags;

    public TagManager() {
        existingTags = new ArrayList<>();
        // Initialize with some sample tags for demonstration
        existingTags.add("existing_tag");
        existingTags.add("sample_tag");
        existingTags.add("test_tag");
    }

    /**
     * Validates if a tag is acceptable (not duplicate).
     * @param tag the tag to validate
     * @return true if valid, false if duplicate
     */
    public boolean validateTag(String tag) {
        if (isDuplicate(tag)) {
            System.out.println("TagManager: Tag '" + tag + "' is duplicate.");
            return false;
        }
        System.out.println("TagManager: Tag '" + tag + "' is valid.");
        return true;
    }

    /**
     * Adds a new tag to the list.
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        existingTags.add(tag);
        System.out.println("TagManager: Added tag - " + tag);
    }

    /**
     * Checks if a tag is duplicate.
     * @param tag the tag to check
     * @return true if duplicate, false otherwise
     */
    public boolean isDuplicate(String tag) {
        return existingTags.contains(tag);
    }
}