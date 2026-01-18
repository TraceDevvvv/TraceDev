/**
 * Service layer for tag operations
 * Handles business logic and data access for tags
 */
import java.util.ArrayList;
import java.util.List;
public class TagService {
    // Simulated database connection
    private DatabaseConnection dbConnection;
    public TagService() {
        dbConnection = new DatabaseConnection();
    }
    /**
     * Get all tags from the system
     * @return List of all tags
     */
    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<>();
        try {
            // In a real application, this would query the database
            // For this example, we'll create some sample data
            // Check if connection is available (simulate server interruption)
            if (!dbConnection.isConnected()) {
                throw new RuntimeException("Connection to server ETOUR interrupted");
            }
            // Sample tags
            tags.add(new Tag("TAG001", "Java", "Java programming language", "2024-01-15"));
            tags.add(new Tag("TAG002", "Python", "Python programming language", "2024-01-20"));
            tags.add(new Tag("TAG003", "Database", "Database management systems", "2024-02-01"));
            tags.add(new Tag("TAG004", "Web", "Web development technologies", "2024-02-10"));
            tags.add(new Tag("TAG005", "Mobile", "Mobile application development", "2024-02-15"));
            tags.add(new Tag("TAG006", "Cloud", "Cloud computing serv", "2024-03-01"));
            tags.add(new Tag("TAG007", "Security", "Cybersecurity and encryption", "2024-03-05"));
            tags.add(new Tag("TAG008", "AI", "Artificial Intelligence", "2024-03-10"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve tags: " + e.getMessage(), e);
        }
        return tags;
    }
    /**
     * Delete a tag by its ID
     * @param tagId The ID of the tag to delete
     * @return true if deletion was successful
     */
    public boolean deleteTag(String tagId) {
        try {
            // Check if connection is available
            if (!dbConnection.isConnected()) {
                throw new RuntimeException("Connection to server ETOUR interrupted");
            }
            // In a real application, this would execute a DELETE SQL statement
            // For this example, we'll simulate successful deletion
            System.out.println("Deleting tag with ID: " + tagId);
            // Simulate processing time (this method can be called from background thread)
            // Using a minimal delay that won't impact performance significantly
            // Thread.sleep(100); // REMOVED to prevent blocking EDT
            return true;
        } catch (Exception e) {
            System.err.println("Failed to delete tag " + tagId + ": " + e.getMessage());
            return false;
        }
    }
    /**
     * Delete multiple tags at once
     * @param tagIds List of tag IDs to delete
     * @return Number of successfully deleted tags
     */
    public int deleteMultipleTags(List<String> tagIds) {
        int successCount = 0;
        for (String tagId : tagIds) {
            if (deleteTag(tagId)) {
                successCount++;
            }
        }
        return successCount;
    }
}