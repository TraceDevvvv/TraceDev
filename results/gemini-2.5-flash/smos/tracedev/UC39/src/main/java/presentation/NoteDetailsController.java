package presentation;

import common.DataAccessFailureException;
import domain.NoteDetailDTO;
import security.AuthenticationService; // Dependency from Class Diagram
import service.NoteService;

/**
 * Presentation Layer component acting as a controller for note details.
 * It handles user requests to view note details, interacts with the service layer,
 * and updates the view.
 */
public class NoteDetailsController {
    // Private fields as specified in the Class Diagram (Modified to satisfy REQ-001)
    private NoteService noteService;
    private NoteDetailsView noteDetailsView;
    // Dependency on AuthenticationService as per class diagram REQ-003,
    // though not explicitly used in the provided sequence diagram.
    private AuthenticationService authenticationService;

    /**
     * Constructs a NoteDetailsController with dependencies (REQ-001).
     * @param service The NoteService instance to interact with the application layer.
     * @param view The NoteDetailsView instance to update the UI.
     * @param authenticationService The AuthenticationService instance for security checks.
     */
    public NoteDetailsController(NoteService service, NoteDetailsView view, AuthenticationService authenticationService) {
        this.noteService = service;
        this.noteDetailsView = view;
        this.authenticationService = authenticationService; // Initialize the dependency
    }

    /**
     * Handles the request to view details for a specific note.
     * This is the entry point from the NoteListView as per the sequence diagram.
     * It orchestrates the retrieval of note details and their display or error handling.
     *
     * @param noteId The ID of the note to display details for.
     */
    public void viewNoteDetails(String noteId) {
        System.out.println("[NoteDetailsController] Received request to view details for noteId: " + noteId);

        // Class diagram shows dependency to AuthenticationService,
        // but the sequence diagram does not detail its use in this flow.
        // Assuming authentication is a precondition or handled elsewhere for this specific sequence.
        // if (!authenticationService.isAuthenticated()) {
        //     noteDetailsView.showErrorMessage("User not authenticated. Please log in.");
        //     return;
        // }

        try {
            // Call the service layer to get note details
            NoteDetailDTO noteDetailDTO = noteService.getNoteDetails(noteId);

            // If successful, display the details
            noteDetailsView.displayNoteDetails(noteDetailDTO);

        } catch (DataAccessFailureException e) {
            // Handle data access failure as per sequence diagram's error path (REQ-012, REQ-013)
            handleException(e);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("[NoteDetailsController] An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            displayError("An unexpected error occurred while retrieving note details.");
        }
    }

    /**
     * Displays an error message through the view.
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.out.println("[NoteDetailsController] Displaying error: " + message);
        noteDetailsView.showErrorMessage(message); // Delegating to the view's showErrorMessage method
    }

    /**
     * Handles specific DataAccessFailureExceptions (REQ-012).
     * This private method encapsulates the logic for preparing an error message
     * and passing it to the view.
     * @param e The DataAccessFailureException that occurred.
     */
    private void handleException(DataAccessFailureException e) {
        System.err.println("[NoteDetailsController] Caught DataAccessFailureException: " + e.getMessage());
        // As per sequence diagram, construct a user-friendly error message.
        String errorMessage = "Failed to retrieve note details due to server issues. Please try again.";
        displayError(errorMessage); // Uses the public displayError method which delegates to the view.
    }
}