// Service layer for tag search operations
import java.util.ArrayList;
import java.util.List;

public class TagSearchService {
    private List<TagSearch> tagSearches = new ArrayList<>();
    private ETourServerConnection serverConnection = new ETourServerConnection();

    // Adds a new tag search if valid and not duplicate
    public String insertTagSearch(String tagName) {
        // Step 5: Verify data entered
        if (!verifyData(tagName)) {
            // Step 8: Activate Errored use case
            activateErrored();
            return "Errored: Invalid or insufficient data.";
        }

        // Step 6: Check if tag already present
        if (isTagPresent(tagName)) {
            // Step 7: Activate ExistingErrorTag use case
            activateExistingErrorTag();
            return "ExistingErrorTag: Tag is already present.";
        }

        // Check server connection before proceeding
        if (!serverConnection.isConnected()) {
            return "Error: Connection to the server ETOUR is interrupted.";
        }

        // Insert new tag
        int newId = tagSearches.size() + 1;
        TagSearch newTag = new TagSearch(newId, tagName);
        tagSearches.add(newTag);
        System.out.println("Notification about the inclusion of the tag is shown.");

        // Simulate possible server interruption after operation (Exit Condition)
        if (Math.random() < 0.3) { // 30% chance to interrupt
            serverConnection.interruptConnection();
        }

        return "Success: Tag '" + tagName + "' inserted successfully.";
    }

    private boolean verifyData(String tagName) {
        // Validate that tag is not null, not empty, and length is reasonable
        return tagName != null && !tagName.trim().isEmpty() && tagName.length() <= 100;
    }

    private boolean isTagPresent(String tagName) {
        return tagSearches.stream().anyMatch(tag -> tag.getName().equalsIgnoreCase(tagName));
    }

    private void activateErrored() {
        System.out.println("Activating use case Errored: Data is invalid or insufficient.");
    }

    private void activateExistingErrorTag() {
        System.out.println("Activating use case ExistingErrorTag: Tag already exists.");
    }

    // For testing
    public List<TagSearch> getAllTags() {
        return new ArrayList<>(tagSearches);
    }

    public ETourServerConnection getServerConnection() {
        return serverConnection;
    }
}