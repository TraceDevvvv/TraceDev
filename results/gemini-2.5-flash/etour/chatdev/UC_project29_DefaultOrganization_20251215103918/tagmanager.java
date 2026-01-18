'''
TagManager.java
This class simulates a backend system responsible for managing a collection of tags.
It provides functionality to check if a tag already exists and to add new tags.
This is a core component for the TagApplication to determine if a tag input by the user
corresponds to an existing tag, triggering the ERRORETAGESISTENTE use case.
'''
import java.util.HashSet;
import java.util.Set;
/**
 * Manages a collection of tags existing in the system.
 * Simulates the backend logic for checking and adding tags.
 */
public class TagManager {
    // A Set is used to store tags for efficient lookup (O(1) average time complexity)
    // and to naturally enforce uniqueness of tags.
    private final Set<String> existingTags;
    /**
     * Constructs a new TagManager and initializes it with some predefined tags.
     * These initial tags simulate data already present in the system.
     */
    public TagManager() {
        existingTags = new HashSet<>();
        // Add some initial tags to simulate existing data for testing the error case
        existingTags.add("java");
        existingTags.add("programming");
        existingTags.add("software");
        existingTags.add("chatdev");
    }
    /**
     * Checks if a given tag already exists in the system.
     * This method is central to the "ERRORETAGESISTENTE" use case.
     *
     * @param tag The tag string to check.
     * @return true if the tag exists (case-insensitively), false otherwise.
     */
    public boolean tagExists(String tag) {
        // Handle null or empty tags as they cannot meaningfully "exist"
        if (tag == null || tag.trim().isEmpty()) {
            return false;
        }
        // Perform a case-insensitive check by converting the input tag to lowercase.
        return existingTags.contains(tag.toLowerCase());
    }
    /**
     * Adds a new tag to the system.
     * This method is included for completeness of a tag management system,
     * but the primary focus of the ERRORETAGESISTENTE use case is on {@link #tagExists(String)}.
     *
     * @param tag The tag string to add.
     * @return true if the tag was successfully added (i.e., it did not exist before), false otherwise.
     */
    public boolean addTag(String tag) {
        // Refuse to add null or empty tags.
        if (tag == null || tag.trim().isEmpty()) {
            return false;
        }
        // Add the tag in lowercase to maintain case-insensitivity within the system.
        return existingTags.add(tag.toLowerCase());
    }
    /**
     * Retrieves all tags currently stored in the system.
     *
     * @return A new {@code HashSet} containing all existing tags (lowercase).
     */
    public Set<String> getAllTags() {
        // Return a copy to prevent external modification of the internal set.
        return new HashSet<>(existingTags);
    }
}