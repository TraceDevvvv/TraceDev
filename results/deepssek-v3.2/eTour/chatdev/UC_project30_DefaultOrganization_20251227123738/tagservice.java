/**
 * Service class for handling tag-related business logic.
 * This class encapsulates the operations for tag validation, duplicate checking, and insertion.
 * It can be extended to integrate with a real database in the future.
 */
import java.util.HashSet;
import java.util.Set;
public class TagService {
    // Simulate a persistent tag storage (in production, replace with database DAO)
    private Set<String> tagRepository;
    public TagService() {
        tagRepository = new HashSet<>();
        // Initialize with some existing tags for demonstration
        tagRepository.add("java");
        tagRepository.add("programming");
        tagRepository.add("database");
    }
    /**
     * Validates the tag according to business rules.
     * @param tagName The tag name to validate
     * @return true if the tag is valid, false otherwise
     */
    public boolean validateTag(String tagName) {
        if (tagName == null || tagName.trim().isEmpty()) {
            return false;
        }
        String trimmedTag = tagName.trim();
        // Business rule: tag must be 1-100 characters and alphanumeric + underscore
        if (trimmedTag.length() > 100) {
            return false;
        }
        return trimmedTag.matches("^[a-zA-Z0-9_]+$");
    }
    /**
     * Checks if a tag already exists in the system.
     * @param tagName The tag name to check
     * @return true if the tag exists, false otherwise
     */
    public boolean tagExists(String tagName) {
        return tagRepository.contains(tagName.toLowerCase()); // Case-insensitive check
    }
    /**
     * Inserts a new tag into the system.
     * Precondition: tag must be validated and not already exist.
     * @param tagName The tag name to insert
     * @return true if insertion was successful, false otherwise
     * @throws Exception if server connection is interrupted (simulated ETOUR)
     */
    public boolean insertTag(String tagName) throws Exception {
        // Simulate potential server interruption
        if (simulateServerInterruption()) {
            throw new Exception("Server connection interrupted (ETOUR) during insertion.");
        }
        return tagRepository.add(tagName.toLowerCase()); // Store in lowercase for consistency
    }
    /**
     * Simulates a random server interruption for demonstration purposes.
     * In a real system, this would be replaced with actual connection health checks.
     * @return true if interruption occurs, false otherwise
     */
    private boolean simulateServerInterruption() {
        // 15% chance of simulated server interruption
        return Math.random() < 0.15;
    }
    /**
     * Gets all current tags (for debugging or display purposes).
     * @return A copy of the current tag repository
     */
    public Set<String> getAllTags() {
        return new HashSet<>(tagRepository);
    }
}