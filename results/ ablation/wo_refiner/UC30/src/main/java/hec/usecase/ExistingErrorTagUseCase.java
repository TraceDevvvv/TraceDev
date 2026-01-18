package hec.usecase;

/**
 * Handles duplicate tag errors.
 * Simulates displaying an error to the user.
 */
public class ExistingErrorTagUseCase {
    /**
     * Handles the duplicate tag scenario.
     *
     * @param tagName the duplicate tag name
     */
    public void handleDuplicateTag(String tagName) {
        // Simulate displaying duplicate tag error.
        System.out.println("Error: Tag with name \"" + tagName + "\" already exists.");
    }
}