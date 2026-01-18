
package com.example.bookmark;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface component for bookmark operations.
 * It interacts with the BookmarkController and displays information to the user.
 *
 * Relationship: BookmarkView ..> BookmarkController (uses)
 * Relationship: BookmarkView ..> BookmarkRemovalConfirmationDTO (displays)
 */
public class BookmarkView {

    private final BookmarkController bookmarkController;
    private BookmarkRemovalConfirmationDTO lastConfirmationDTO; // Store last DTO for confirmation/cancellation

    /**
     * Constructs a BookmarkView with a dependency on BookmarkController.
     * Uses constructor injection for the controller.
     *
     * @param bookmarkController The controller handling UI actions.
     */
    public BookmarkView(BookmarkController bookmarkController) {
        this.bookmarkController = bookmarkController;
        // The controller needs to know its view to update it.
        // This creates a circular dependency, often handled by interfaces or event bus.
        // For simplicity, direct setter injection.
        this.bookmarkController.setBookmarkView(this);
        System.out.println("BookmarkView initialized.");
    }

    /**
     * Displays a list of bookmarks to the user.
     * (Currently, this method is not directly invoked in the provided sequence diagram,
     * but is part of the class diagram).
     *
     * @param bookmarks A list of Bookmark objects to display.
     */
    public void displayBookmarks(List<Bookmark> bookmarks) {
        System.out.println("--- Current Bookmarks ---");
        if (bookmarks == null || bookmarks.isEmpty()) {
            System.out.println("No bookmarks available.");
        } else {
            bookmarks.forEach(System.out::println);
        }
        System.out.println("-------------------------");
    }

    /**
     * Shows a confirmation prompt to the user for bookmark removal.
     * Stores the confirmation DTO for future actions.
     *
     * @param confirmation The BookmarkRemovalConfirmationDTO containing details.
     */
    public void showConfirmationPrompt(BookmarkRemovalConfirmationDTO confirmation) { // Harmonized message name
        this.lastConfirmationDTO = confirmation; // Store the DTO for future reference
        System.out.println("\n--- REMOVAL CONFIRMATION ---");
        System.out.println("Message: " + confirmation.message);
        System.out.println("Details: " + confirmation.bookmarkDetails);
        System.out.println("Confirmation ID: " + confirmation.confirmationId);
        System.out.println("Enter 'Y' to confirm, 'N' to cancel:");
    }

    /**
     * Shows a success message after a bookmark has been successfully removed.
     */
    public void showRemovalSuccess() { // Harmonized message name
        String siteId = (lastConfirmationDTO != null) ? lastConfirmationDTO.siteId : "Unknown Site";
        System.out.println("\n--- REMOVAL SUCCESS ---");
        System.out.println("Site '" + siteId + "' removed successfully."); // Harmonized message name
        this.lastConfirmationDTO = null; // Clear pending confirmation
    }

    /**
     * Shows a failure message if bookmark removal could not be completed.
     *
     * @param reason The reason for the failure.
     */
    public void showRemovalFailure(String reason) { // Harmonized message name
        String siteId = (lastConfirmationDTO != null) ? lastConfirmationDTO.siteId : "Unknown Site";
        System.err.println("\n--- REMOVAL FAILED ---");
        System.err.println("Removal of site '" + siteId + "' failed: " + reason); // Harmonized message name
        System.err.println("System error, please try again."); // Harmonized message name
        this.lastConfirmationDTO = null; // Clear pending confirmation
    }

    /**
     * Shows a confirmation message that the removal operation was canceled. (REQ-002)
     */
    public void showCancellationConfirmation() { // Harmonized message name
        System.out.println("\n--- CANCELLATION CONFIRMATION ---");
        System.out.println("Removal cancelled."); // Harmonized message name
        this.lastConfirmationDTO = null; // Clear pending confirmation
    }

    /**
     * Simulates a user clicking a "Remove" button for a specific site.
     *
     * @param siteId The ID of the site to be removed.
     */
    public void onRemoveButtonClicked(String siteId) {
        System.out.println("\nTourist -> BookmarkView: onRemoveButtonClicked(" + siteId + ")");
        bookmarkController.requestRemoveBookmark(siteId);
    }

    /**
     * Simulates a user confirming the removal action.
     * Uses the stored confirmation DTO to get the confirmation ID.
     */
    public void onConfirmationConfirmed() {
        if (lastConfirmationDTO != null && lastConfirmationDTO.confirmationId != null && !lastConfirmationDTO.confirmationId.isEmpty()) {
            System.out.println("\nTourist -> BookmarkView: onConfirmationConfirmed(" + lastConfirmationDTO.confirmationId + ")");
            bookmarkController.confirmRemoveBookmark(lastConfirmationDTO.confirmationId);
        } else {
            System.err.println("View: No pending confirmation to confirm.");
        }
    }

    /**
     * Simulates a user canceling the removal action.
     * Uses the stored confirmation DTO to get the confirmation ID.
     */
    public void onConfirmationCanceled() {
        if (lastConfirmationDTO != null && lastConfirmationDTO.confirmationId != null && !lastConfirmationDTO.confirmationId.isEmpty()) {
            System.out.println("\nTourist -> BookmarkView: onConfirmationCanceled(" + lastConfirmationDTO.confirmationId + ")");
            bookmarkController.cancelRemoveBookmark(lastConfirmationDTO.confirmationId);
        } else {
            System.err.println("View: No pending confirmation to cancel.");
        }
    }

    /**
     * Helper to get the last confirmation DTO for external simulation of user input.
     * @return The last BookmarkRemovalConfirmationDTO or null if none.
     */
    public BookmarkRemovalConfirmationDTO getLastConfirmationDTO() {
        return lastConfirmationDTO;
    }

    /**
     * Main method to demonstrate the interaction flow.
     */
    public static void main(String[] args) {
        // Setup the dependency injection manually for this example
        BookmarkRepositoryImpl repository = new BookmarkRepositoryImpl();
        BookmarkRemovalService service = new BookmarkRemovalService(repository);
        BookmarkController controller = new BookmarkController(service);
        BookmarkView view = new BookmarkView(controller);

        Scanner scanner = new Scanner(System.in);

        // Simulate initial bookmarks
        System.out.println("\n--- Initial Bookmarks (simulated in DB) ---");
        System.out.println(repository.findById("s123").orElse(null));
        System.out.println(repository.findById("s456").orElse(null));
        System.out.println(repository.findById("s789").orElse(null));
        System.out.println("-------------------------------------------");


        // --- Scenario 1: Successful removal ---
        System.out.println("\n--- Scenario 1: Initiate and Confirm Removal (Expected Success) ---");
        view.onRemoveButtonClicked("s123"); // Tourist wants to remove bookmark s123

        if (view.getLastConfirmationDTO() != null) {
            System.out.print("User input (Y/N): ");
            String input = scanner.nextLine();
            if ("Y".equalsIgnoreCase(input)) {
                view.onConfirmationConfirmed(); // Tourist confirms
            } else {
                view.onConfirmationCanceled(); // Tourist cancels
            }
        }
        System.out.println("\n--- Remaining Bookmarks ---");
        System.out.println(repository.findById("s123").orElse(null)); // Should be null
        System.out.println("---------------------------");

        // --- Scenario 2: Initiate and Cancel Removal ---
        System.out.println("\n--- Scenario 2: Initiate and Cancel Removal ---");
        view.onRemoveButtonClicked("s456"); // Tourist wants to remove bookmark s456

        if (view.getLastConfirmationDTO() != null) {
            System.out.print("User input (Y/N): ");
            String input = scanner.nextLine();
            if ("Y".equalsIgnoreCase(input)) {
                view.onConfirmationConfirmed(); // Tourist confirms
            } else {
                view.onConfirmationCanceled(); // Tourist cancels
            }
        }
        System.out.println("\n--- Remaining Bookmarks ---");
        System.out.println(repository.findById("s456").orElse(null)); // Should still be present if cancelled
        System.out.println("---------------------------");


        // --- Scenario 3: Removal with simulated connection error ---
        // This might take a few tries due to random nature of the ConnectionException in BookmarkRepositoryImpl
        System.out.println("\n--- Scenario 3: Removal with potential Connection Error (May need multiple runs) ---");
        System.out.println("Note: BookmarkRepositoryImpl simulates a 20% chance of ConnectionException on 'remove'.");
        System.out.println("      You may need to run this a few times to see the error scenario.");
        view.onRemoveButtonClicked("s789"); // Tourist wants to remove bookmark s789

        if (view.getLastConfirmationDTO() != null) {
            System.out.print("User input (Y/N): ");
            String input = scanner.nextLine();
            if ("Y".equalsIgnoreCase(input)) {
                view.onConfirmationConfirmed(); // Tourist confirms (might hit ConnectionException)
            } else {
                view.onConfirmationCanceled(); // Tourist cancels
            }
        }
        System.out.println("\n--- Remaining Bookmarks ---");
        System.out.println(repository.findById("s789").orElse(null)); // Could be null or present depending on success/failure
        System.out.println("---------------------------");


        // --- Scenario 4: Attempt to remove non-existent bookmark ---
        System.out.println("\n--- Scenario 4: Attempt to remove non-existent bookmark ---");
        view.onRemoveButtonClicked("nonExistentId"); // Tourist tries to remove a non-existent bookmark
        // No confirmation prompt should appear, should directly show failure

        scanner.close();
    }
}
