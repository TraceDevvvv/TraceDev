package com.example.interfaces.controller;

import com.example.application.usecase.DeleteClassUseCase;
import com.example.application.dto.ClassDTO;
import com.example.infrastructure.session.SessionManager;
import com.example.application.presenter.ClassListPresenter;
import com.example.application.usecase.DeleteClassCommand;
import java.util.List;

/**
 * Controller for handling delete class requests.
 * Depends on 'viewdettagliSlasse' use case for traceability.
 */
public class DeleteClassController {
    private DeleteClassUseCase deleteClassUseCase;
    private SessionManager sessionManager;
    private ClassListPresenter presenter;

    public DeleteClassController(DeleteClassUseCase deleteClassUseCase,
                                 SessionManager sessionManager,
                                 ClassListPresenter presenter) {
        this.deleteClassUseCase = deleteClassUseCase;
        this.sessionManager = sessionManager;
        this.presenter = presenter;
    }

    /**
     * Handles a delete request for a class.
     * Validates user session before proceeding.
     */
    public List<ClassDTO> handleDeleteRequest(String classId) {
        // Check if user is logged in (simplified: using a dummy userId)
        String userId = "admin";
        if (!sessionManager.isUserLoggedIn(userId)) {
            throw new SecurityException("User not logged in");
        }

        DeleteClassCommand command = new DeleteClassCommand(classId);
        List<ClassDTO> result = deleteClassUseCase.execute(command);

        // Present the updated list
        presenter.displayClassList(result);

        return result;
    }
}