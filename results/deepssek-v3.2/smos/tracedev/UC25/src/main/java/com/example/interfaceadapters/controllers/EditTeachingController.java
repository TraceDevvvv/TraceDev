
package com.example.interfaceadapters.controllers;

import com.example.application.DisplayTeachingDetailsUseCase;
import com.example.application.EditTeachingUseCase;
import com.example.domain.EditTeachingCommand;
import com.example.domain.EditTeachingResult;
import com.example.domain.Teaching;

/**
 * Controller for the edit teaching use case.
 * Handles requests from the UI (Administrator) and coordinates use cases.
 */
public class EditTeachingController {
    private final EditTeachingUseCase editTeachingUseCase;
    private final Object administratorSession;
    private final DisplayTeachingDetailsUseCase displayTeachingDetailsUseCase;

    public EditTeachingController(EditTeachingUseCase useCase,
                                  Object session,
                                  DisplayTeachingDetailsUseCase detailsUseCase) {
        this.editTeachingUseCase = useCase;
        this.administratorSession = session;
        this.displayTeachingDetailsUseCase = detailsUseCase;
    }

    /**
     * Saves the teaching with new data.
     */
    public EditTeachingResult saveTeaching(String teachingId, String title, String description, java.time.LocalDateTime schedule) {
        EditTeachingCommand command = new EditTeachingCommand(teachingId, title, description, schedule);
        return editTeachingUseCase.execute(command);
    }

    /**
     * Cancels the ongoing operation.
     */
    public void cancelOperation() {
        // In this simplified version, we just call cancel on the interactor.
        // Since the interactor is not directly accessible, we assume the use case can be cancelled.
        // For the purpose of this implementation, we'll just log or ignore.
        // In a real scenario, you might have a reference to the interactor to call cancel().
    }

    /**
     * Loads teaching details for a given ID.
     */
    public Teaching loadTeachingDetails(String teachingId) {
        return displayTeachingDetailsUseCase.execute(teachingId);
    }
}
