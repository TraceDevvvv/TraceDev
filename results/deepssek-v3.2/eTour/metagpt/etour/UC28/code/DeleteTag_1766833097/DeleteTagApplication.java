import java.util.*;
import java.util.stream.Collectors;

/**
 * Main application for the DeleteTag use case.
 * This program simulates an agency operator deleting one or more search tags.
 * It includes user interaction via console, displays existing tags,
 * allows selection of multiple tags for deletion, performs deletion,
 * and notifies success. Handles edge cases and simulates server connection interruption (ETOUR).
 */
public class DeleteTagApplication {
    
    // Simulated database of existing tags
    private static final List<String> existingTags = new ArrayList<>(Arrays.asList(
        "Adventure", "Beach", "Cultural", "Family", "Luxury",
        "Budget", "Honeymoon", "Solo", "Group", "Eco-Tourism"
    ));
    
    // Simulated service for tag operations
    private static TagService tagService = new TagService();
    
    public static void main(String[] args) {
        System.out.println("=== DeleteTag Application ===");
        System.out.println("Agency Operator logged in.");
        
        // Step 1: Access functionality to delete a tag
        System.out.println("\n1. Accessing tag deletion functionality...");
        
        // Step 2: Research existing tags and display them
        System.out.println("2. Researching existing tags...");
        displayTags();
        
        // Step 3: Select one or more tags for deletion
        List<String> tagsToDelete = selectTagsForDeletion();
        if (tagsToDelete.isEmpty()) {
            System.out.println("No tags selected. Exiting.");
            return;
        }
        
        // Step 4: Delete the selected search tags
        System.out.println("4. Deleting selected tags...");
        try {
            boolean success = deleteTags(tagsToDelete);
            if (success) {
                // Exit condition: Notify successful elimination
                System.out.println("\n=== SUCCESS ===");
                System.out.println("Successfully deleted the following tags:");
                for (String tag : tagsToDelete) {
                    System.out.println("  - " + tag);
                }
            } else {
                System.out.println("Deletion failed. Some tags may not exist.");
            }
        } catch (ETOURConnectionException e) {
            // Handle interruption of connection to server ETOUR
            System.out.println("\n=== ERROR ===");
            System.out.println("Connection to server ETOUR interrupted: " + e.getMessage());
            System.out.println("Please check your network connection and try again.");
        }
        
        System.out.println("\nApplication terminated.");
    }
    
    /**
     * Displays all existing tags in a formatted list.
     * This simulates displaying tags in a form as per the use case.
     */
    private static void displayTags() {
        System.out.println("\nExisting Tags:");
        System.out.println("--------------");
        for (int i = 0; i < existingTags.size(); i++) {
            System.out.println((i + 1) + ". " + existingTags.get(i));
        }
        System.out.println("--------------");
    }
    
    /**
     * Allows user to select one or more tags for deletion.
     * Handles invalid input and edge cases.
     * 
     * @return List of selected tag names to delete
     */
    private static List<String> selectTagsForDeletion() {
        Scanner scanner = new Scanner(System.in);
        List<String> selectedTags = new ArrayList<>();
        
        System.out.println("\n3. Select tags to delete (enter numbers separated by commas, e.g., 1,3,5):");
        System.out.print("Your selection: ");
        
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return selectedTags; // Empty list if no input
            }
            
            // Parse comma-separated numbers
            String[] parts = input.split(",");
            for (String part : parts) {
                try {
                    int index = Integer.parseInt(part.trim()) - 1; // Convert to 0-based index
                    if (index >= 0 && index < existingTags.size()) {
                        String tagName = existingTags.get(index);
                        if (!selectedTags.contains(tagName)) {
                            selectedTags.add(tagName);
                        } else {
                            System.out.println("Warning: Tag '" + tagName + "' already selected.");
                        }
                    } else {
                        System.out.println("Warning: Invalid number '" + (index + 1) + "'. Skipping.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid input '" + part + "'. Skipping.");
                }
            }
        } finally {
            // Note: In a real application, we'd manage the scanner properly
            // For simplicity, we don't close System.in scanner
        }
        
        return selectedTags;
    }
    
    /**
     * Deletes the selected tags from the system.
     * Simulates potential server connection interruption.
     * 
     * @param tagsToDelete List of tag names to delete
     * @return true if all deletions were successful, false otherwise
     * @throws ETOURConnectionException if connection to server ETOUR is interrupted
     */
    private static boolean deleteTags(List<String> tagsToDelete) throws ETOURConnectionException {
        // Simulate random server connection interruption (10% chance)
        if (Math.random() < 0.1) {
            throw new ETOURConnectionException("Server ETOUR is unreachable.");
        }
        
        boolean allSuccessful = true;
        for (String tag : tagsToDelete) {
            boolean deleted = tagService.deleteTag(tag);
            if (deleted) {
                System.out.println("  Deleted: " + tag);
            } else {
                System.out.println("  Failed to delete: " + tag + " (tag not found)");
                allSuccessful = false;
            }
        }
        
        return allSuccessful;
    }
}

/**
 * Service class for tag operations.
 * Simulates business logic for tag deletion.
 */
class TagService {
    // Simulated in-memory storage of tags
    private Set<String> tags = new HashSet<>(DeleteTagApplication.existingTags);
    
    /**
     * Deletes a tag from the system.
     * 
     * @param tagName Name of the tag to delete
     * @return true if tag was found and deleted, false otherwise
     */
    public boolean deleteTag(String tagName) {
        if (tags.contains(tagName)) {
            tags.remove(tagName);
            // Also update the static list in main application for consistency
            DeleteTagApplication.existingTags.remove(tagName);
            return true;
        }
        return false;
    }
    
    /**
     * Gets all current tags in the system.
     * 
     * @return List of all tag names
     */
    public List<String> getAllTags() {
        return new ArrayList<>(tags);
    }
}

/**
 * Custom exception for server ETOUR connection interruptions.
 * This handles the quality requirement about connection interruption.
 */
class ETOURConnectionException extends Exception {
    public ETOURConnectionException(String message) {
        super(message);
    }
}