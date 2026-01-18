/**
 * Provides serv for managing Tag objects.
 * This class simulates interaction with a data store (in this case, an in-memory set).
 * It handles validation and checks for tag existence.
 */
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class TagService {
    // In-memory data store for tags for simplicity.
    // In a real application, this would interact with a database.
    private final Set<Tag> tags;
    /**
     * Constructs a new TagService instance, initializing the tag storage.
     */
    public TagService() {
        this.tags = new HashSet<>();
        // Add some initial data for demonstration purposes
        try {
            tags.add(new Tag("Java", "Programming language"));
            tags.add(new Tag("Python", "Scripting language"));
            tags.add(new Tag("Spring", "Java framework"));
        } catch (InvalidTagDataException e) {
            // This should ideally not happen with hardcoded valid data
            System.err.println("Error initializing default tags: " + e.getMessage());
        }
    }
    /**
     * Validates the provided tag data.
     * @param name The name of the tag.
     * @param description The description of the tag.
     * @throws InvalidTagDataException If the name is null or empty.
     */
    private void validateTagData(String name, String description) throws InvalidTagDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidTagDataException("Tag name cannot be empty.");
        }
        // Additional validation rules could go here (e.g., max length, allowed characters)
    }
    /**
     * Checks if a tag with the given name already exists in the system.
     * @param tagName The name of the tag to check.
     * @return true if a tag with the given name exists (case-insensitive), false otherwise.
     */
    public boolean isTagPresent(String tagName) {
        if (tagName == null || tagName.trim().isEmpty()) {
            return false; // An empty name cannot be present
        }
        // Check for existence, case-insensitive
        return tags.stream().anyMatch(tag -> tag.getName().equalsIgnoreCase(tagName.trim()));
    }
    /**
     * Adds a new tag to the system.
     * @param name The name of the tag to add.
     * @param description The description of the tag.
     * @throws InvalidTagDataException If the provided tag data is invalid or insufficient.
     * @throws TagAlreadyExistsException If a tag with the same name already exists in the system.
     * @throws ServerConnectionException For connection errors (simulating ETOUR).
     */
    public void addTag(String name, String description)
            throws InvalidTagDataException, TagAlreadyExistsException, ServerConnectionException {
        // Step 4: Verify the data entered and check if the tag is already present.
        // Simulate a random connection error for ETOUR condition
        if (Math.random() < 0.05) { // 5% chance of connection error
            throw new ServerConnectionException("Connection to server interrupted (ETOUR simulation).");
        }
        // Validate data entered
        validateTagData(name, description);
        // Check if the tag is already present in the system
        if (isTagPresent(name)) {
            throw new TagAlreadyExistsException("Tag '" + name + "' already exists in the system.");
        }
        // If data is valid and tag is not present, create and add the new tag.
        Tag newTag = new Tag(name, description); // Tag constructor now throws InvalidTagDataException
        tags.add(newTag);
        System.out.println("Tag added: " + newTag); // Log for verification
    }
    /**
     * Retrieves an immutable set of all tags currently in the system.
     * @return A Set of Tag objects.
     */
    public Set<Tag> getAllTags() {
        return Collections.unmodifiableSet(new HashSet<>(tags));
    }
}