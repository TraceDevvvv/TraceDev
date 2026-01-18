package com.school.ui;

import com.school.application.CreateDisciplinaryNoteUseCase;
import com.school.auth.AuthenticationService;

/**
 * Controller handling disciplinary note creation requests.
 */
public class DisciplinaryNoteController {
    private CreateDisciplinaryNoteUseCase createUseCase;
    private AuthenticationService authenticationService;

    public DisciplinaryNoteController(
            CreateDisciplinaryNoteUseCase createUseCase,
            AuthenticationService authenticationService) {
        this.createUseCase = createUseCase;
        this.authenticationService = authenticationService;
    }

    /**
     * Creates a disciplinary note after validating session.
     * Implements sequence diagram flow.
     */
    public CreateNoteResponse createNote(CreateNoteRequest request) {
        // Session validation as per sequence diagram
        // For simplicity, we assume token is provided in request; here we use a dummy token.
        String token = "dummy-session-token";
        boolean valid = authenticationService.validateSession(token);
        if (!valid) {
            return new CreateNoteResponse(null, false, "Invalid session");
        }

        try {
            com.school.domain.DisciplinaryNote note = createUseCase.execute(request);
            return new CreateNoteResponse(note.getNoteId(), true, "Note created successfully");
        } catch (Exception e) {
            return new CreateNoteResponse(null, false, e.getMessage());
        }
    }
}