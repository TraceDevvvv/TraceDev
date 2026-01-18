package com.example.presentation;

import com.example.application.NoteDetailsDTO;
import com.example.application.NoteDetailsUseCaseController;
import com.example.auth.AuthService;
import com.example.infrastructure.NoteRepository;
import com.example.infrastructure.NoteRepositoryImpl;
import com.example.validation.NoteDetailsValidator;

/**
 * Controller that mediates between the views and the use case controller.
 * Handles user actions and coordinates the flow.
 */
public class NoteDetailsController {
    private NoteDetailsUseCaseController useCaseController;
    private NoteDetailsValidator validator;
    private AuthService authService;

    public NoteDetailsController() {
        // In a real implementation, these would be injected (e.g., via DI)
        NoteRepository repository = new NoteRepositoryImpl();
        this.useCaseController = new NoteDetailsUseCaseController(repository);
        this.validator = new NoteDetailsValidator();
        this.authService = AuthService.getInstance();
    }

    /**
     * Handles the request to display the note list.
     * Not explicitly in sequence diagram but implied.
     */
    public void handleNoteListRequest() {
        // This method is called by NoteListView to get the list of notes.
        // The repository is accessed via the use case controller, but for simplicity,
        // we directly call repository here.
        NoteRepository repository = new NoteRepositoryImpl();
        java.util.List<com.example.domain.Note> notes = repository.findAll();
        // Normally we would pass notes back to the view; for this example we just print.
        System.out.println("Controller retrieved " + notes.size() + " notes.");
    }

    /**
     * Handles the selection of a note by ID.
     * @param noteId the ID of the selected note
     */
    public void handleNoteSelection(String noteId) {
        System.out.println("Controller: Note selection handled for " + noteId);
        // Additional logic could be added here (e.g., updating the UI state).
    }

    /**
     * Handles the click on the "Details" button.
     * Validates the note ID, executes the use case, and passes the DTO to the details view.
     * @param noteId the ID of the note for which details are requested
     */
    public void handleDetailsButtonClick(String noteId) {
        System.out.println("Controller: Handling details button click for note ID: " + noteId);
        if (!authService.isAdminLoggedIn()) {
            System.err.println("Admin not logged in.");
            return;
        }
        // Validate note ID (simple validation)
        if (noteId == null || noteId.trim().isEmpty()) {
            System.err.println("Invalid note ID.");
            return;
        }
        // Execute the use case
        NoteDetailsDTO dto = useCaseController.execute(noteId);
        if (dto == null) {
            System.err.println("Could not retrieve note details.");
            return;
        }
        // Validate the DTO
        if (!validator.validateNoteDetails(dto)) {
            System.err.println("Note details validation failed.");
            return;
        }
        // In a real MVC, we would have a reference to the details view.
        // For this example, we create a temporary view to render.
        NoteDetailsView detailsView = new NoteDetailsView(this);
        detailsView.renderNoteDetailsForm(dto);
    }

    /**
     * Notified when the administrator disconnects.
     * Cancels any pending operations and cleans up resources.
     */
    public void notifyDisconnect() {
        System.out.println("Controller: Notified of disconnection.");
        // Cancel pending operations (simulated).
        useCaseController.cancelPendingOperations();
        // Additional cleanup if needed.
    }
}