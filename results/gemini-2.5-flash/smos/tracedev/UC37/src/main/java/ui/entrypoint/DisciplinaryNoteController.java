package ui.entrypoint;

import application.DisciplinaryNoteService;
import domain.DisciplinaryNote;

import java.time.LocalDate;

/**
 * UI/Entrypoint Layer controller for handling requests related to Disciplinary Notes.
 * This class acts as an application adapter, translating external requests
 * into calls to the application service and transforming service results
 * back into external responses.
 */
public class DisciplinaryNoteController {
    private DisciplinaryNoteService disciplinaryNoteService;

    /**
     * Constructs a DisciplinaryNoteController with a dependency on DisciplinaryNoteService.
     *
     * @param disciplinaryNoteService The application service handling disciplinary note logic.
     */
    public DisciplinaryNoteController(DisciplinaryNoteService disciplinaryNoteService) {
        this.disciplinaryNoteService = disciplinaryNoteService;
        System.out.println("DisciplinaryNoteController: Initialized.");
        // R3: Assumes Administrator is logged in. This check would typically happen
        // in an authentication/authorization layer before reaching the controller.
    }

    /**
     * Displays a new disciplinary note form.
     * This method typically prepares an empty form for the UI.
     *
     * @return A NewNoteForm DTO ready for user input.
     * Pre-conditions: Admin executed "SviewTetTingloregister" (R4) and "ViewElonconote" (R5).
     * These pre-conditions imply certain UI navigation or setup steps that are assumed
     * to have occurred before this method is called.
     */
    public NewNoteForm displayNewNoteForm() {
        System.out.println("Controller: displayNewNoteForm() called. Preparing new note form.");
        // In a real web application, this might fetch dropdown data (e.g., list of students, teachers)
        // to populate the form fields. For this example, it's a simple DTO.
        return new NewNoteForm();
    }

    /**
     * Creates a new disciplinary note based on the provided request data.
     * This method handles the incoming request, delegates to the application service,
     * and constructs the appropriate response.
     *
     * @param request The CreateNoteRequest DTO containing all necessary data.
     * @return A DisciplinaryNoteResponse DTO indicating the outcome of the creation.
     * R14: Returns to registry screen upon success. This is handled by the UI consuming this response.
     */
    public DisciplinaryNoteResponse createNote(CreateNoteRequest request) {
        System.out.println("Controller: createNote() called with request: " + request);

        // Basic input validation at the entrypoint layer (can be more extensive with frameworks)
        if (request.getStudentId() == null || request.getStudentId().isEmpty() ||
            request.getDate() == null ||
            request.getTeacherId() == null || request.getTeacherId().isEmpty() ||
            request.getDescription() == null || request.getDescription().isEmpty()) {
            System.err.println("Controller: Invalid input in CreateNoteRequest.");
            return new DisciplinaryNoteResponse(null, "Error: All fields are required.");
        }

        try {
            // Delegate to the application service for business logic execution
            DisciplinaryNote createdNote = disciplinaryNoteService.createDisciplinaryNote(
                    request.getStudentId(),
                    request.getDate(),
                    request.getTeacherId(),
                    request.getDescription()
            );

            System.out.println("Controller: Disciplinary note created successfully with ID: " + createdNote.getId());
            // R14: Returns to registry screen upon success.
            // The response DTO containing the note ID and a message helps the UI
            // to confirm success and potentially redirect or update the view.
            return new DisciplinaryNoteResponse(createdNote.getId(), "Disciplinary note created and notification initiated successfully.");

        } catch (IllegalArgumentException e) {
            System.err.println("Controller: Validation error during note creation: " + e.getMessage());
            return new DisciplinaryNoteResponse(null, "Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Controller: An unexpected error occurred during note creation: " + e.getMessage());
            // Log the full exception stack trace in a real application
            return new DisciplinaryNoteResponse(null, "An unexpected error occurred: " + e.getMessage());
        }
    }
}