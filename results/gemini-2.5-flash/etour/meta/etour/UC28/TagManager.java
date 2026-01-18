package DeleteTag_1765892478;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Manages a collection of Tag objects, providing functionalities to add, retrieve, and delete tags.
 * This class ensures thread-safe operations for managing tags.
 */
public class TagManager {
    // Using ConcurrentHashMap for thread-safe access to tags by their ID.
    private final Map<String, Tag> tags;

    /**
     * Constructs a new TagManager with an empty collection of tags.
     */
    public TagManager() {
        this.tags = new ConcurrentHashMap<>();
    }

    /**
     * Adds a new tag to the manager.
     * If a tag with the same ID already exists, it will not be added.
     *
     * @param tag The Tag object to add. Must not be null.
     * @return true if the tag was added successfully, false if a tag with the same ID already exists.
     * @throws IllegalArgumentException if the provided tag is null.
     */
    public boolean addTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag to add cannot be null.");
        }
        // putIfAbsent ensures that if the key is already associated with a value,
        // the existing value is returned and the new value is not put.
        return tags.putIfAbsent(tag.getId(), tag) == null;
    }

    /**
     * Retrieves a tag by its unique identifier.
     *
     * @param tagId The ID of the tag to retrieve. Must not be null or empty.
     * @return The Tag object if found, or null if no tag with the given ID exists.
     * @throws IllegalArgumentException if tagId is null or empty.
     */
    public Tag getTagById(String tagId) {
        if (tagId == null || tagId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag ID cannot be null or empty.");
        }
        return tags.get(tagId);
    }

    /**
     * Retrieves an unmodifiable list of all tags currently managed.
     *
     * @return A List of all Tag objects. The list is unmodifiable to prevent external modification
     *         of the manager's internal state.
     */
    public List<Tag> getAllTags() {
        return Collections.unmodifiableList(new ArrayList<>(tags.values()));
    }

    /**
     * Deletes one or more tags identified by their IDs.
     *
     * @param tagIds A list of tag IDs to be deleted. Must not be null.
     * @return A list of Tag objects that were successfully deleted.
     * @throws IllegalArgumentException if the provided list of tag IDs is null.
     */
    public List<Tag> deleteTags(List<String> tagIds) {
        if (tagIds == null) {
            throw new IllegalArgumentException("List of tag IDs to delete cannot be null.");
        }

        List<Tag> deletedTags = new ArrayList<>();
        for (String tagId : tagIds) {
            if (tagId != null && !tagId.trim().isEmpty()) {
                // remove returns the value associated with the key, or null if the key was not found.
                Tag removedTag = tags.remove(tagId);
                if (removedTag != null) {
                    deletedTags.add(removedTag);
                }
            }
        }
        return deletedTags;
    }

    /**
     * Searches for tags whose names contain the given search term (case-insensitive).
     *
     * @param searchTerm The term to search for within tag names. If null or empty, returns all tags.
     * @return A list of tags matching the search term.
     */
    public List<Tag> searchTags(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllTags(); // If no search term, return all tags
        }
        final String lowerCaseSearchTerm = searchTerm.toLowerCase();
        return tags.values().stream()
                .filter(tag -> tag.getName().toLowerCase().contains(lowerCaseSearchTerm))
                .collect(Collectors.toUnmodifiableList());
    }
}