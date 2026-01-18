/**
 * A simple console-based user interface for the tag insertion form.
 * This class handles the user interaction flow as per the use case.
 */
import java.util.Scanner;
public class TagFormUI {
    private TagService tagService;
    private Scanner scanner;
    public TagFormUI(TagService tagService) {
        this.tagService = tagService;
        this.scanner = new Scanner(System.in);
    }
    /**
     * Displays the tag insertion form and handles the complete workflow.
     */
    public void showForm() {
        System.out.println("\n=== Tag Insertion Form ===");
        // Get tag input from user
        System.out.print("Enter new tag: ");
        String tagName = scanner.nextLine().trim();
        // Validate input
        if (!tagService.validateTag(tagName)) {
            System.out.println("ERROR: Invalid tag. Please enter a tag with 1-100 alphanumeric characters or underscores.");
            return;
        }
        // Check for existing tag
        if (tagService.tagExists(tagName)) {
            System.out.println("ERROR: Tag '" + tagName + "' already exists in the system.");
            return;
        }
        // Attempt to insert the tag
        try {
            boolean success = tagService.insertTag(tagName);
            if (success) {
                System.out.println("SUCCESS: Tag '" + tagName + "' has been successfully added!");
            } else {
                System.out.println("ERROR: Failed to insert tag.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    /**
     * Closes the scanner resource.
     */
    public void close() {
        scanner.close();
    }
}