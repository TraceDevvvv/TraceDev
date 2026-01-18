package com.example;

/**
 * Controller class for handling administrator actions related to notes.
 * It orchestrates the interaction between the UI (AdminNoteScreen),
 * authentication, and the use case for deleting notes.
 */
public class AdminNoteController {

    private DeleteNoteUseCase deleteNoteUseCase;
    private AdminNoteScreen adminNoteScreen;
    private AuthenticationService authService; // Added to satisfy REQ-003

    /**
     * Constructs an AdminNoteController.
     *
     * @param deleteNoteUseCase The use case for deleting notes.
     * @param adminNoteScreen   The boundary object for displaying UI information.
     * @param authService       The authentication service to check login status.
     */
    public AdminNoteController(DeleteNoteUseCase deleteNoteUseCase,
                               AdminNoteScreen adminNoteScreen,
                               AuthenticationService authService) {
        this.deleteNoteUseCase = deleteNoteUseCase;
        this.adminNoteScreen = adminNoteScreen;
        this.authService = authService;
    }

    /**
     * Handles the request from the AdminNoteScreen to delete a note.
     * This method orchestrates the deletion process, including authentication and error handling.
     *
     * @param noteId The ID of the note to be deleted.
     */
    public void handleDeleteNote(String noteId) {
        System.out.println("\nAdminNoteController: Received request to delete note with ID: " + noteId);

        // Pre-condition: Administrator is logged in (REQ-003)
        if (!authService.isLoggedIn()) {
            adminNoteScreen.notifyUser("Administrator not logged in. Please log in to delete notes.");
            return;
        }

        System.out.println("AdminNoteController: Administrator is logged in. Proceeding with deletion.");
        try {
            deleteNoteUseCase.execute(noteId);
            adminNoteScreen.notifyUser("Note '" + noteId + "' deleted successfully!");
            adminNoteScreen.displayRegistryScreen(); // Display updated registry
        } catch (NotificationFailedException e) {
            // Specific handling for notification failures during deletion
            adminNoteScreen.notifyUser("Failed to send notification to parent while deleting note '" + noteId + "'. Deletion rolled back. " + e.getMessage());
        } catch (NoteDeletionFailedException e) {
            // General handling for note deletion failures (e.g., note not found, database error)
            adminNoteScreen.notifyUser("Failed to delete note '" + noteId + "'. " + e.getMessage());
        } catch (Exception e) {
            // Catch any unexpected exceptions
            adminNoteScreen.notifyUser("An unexpected error occurred while deleting note '" + noteId + "': " + e.getMessage());
        }
    }
}