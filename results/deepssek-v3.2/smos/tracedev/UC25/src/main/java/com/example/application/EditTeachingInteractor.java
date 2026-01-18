package com.example.application;

import com.example.domain.*;
import com.example.interfaceadapters.controllers.ListViewController;
import com.example.interfaceadapters.gateways.TeachingRepository;
import com.example.interfaceadapters.validators.TeachingValidator;

import java.util.List;

/**
 * Interactor implementing the EditTeachingUseCase.
 * Orchestrates the edit teaching flow: validation, fetching, updating, saving, and notifying the view.
 */
public class EditTeachingInteractor implements EditTeachingUseCase {
    private final TeachingRepository teachingRepository;
    private final TeachingValidator validator;
    private final ListViewController listViewController;
    private final ErrorHandlerUseCase errorHandler;

    public EditTeachingInteractor(TeachingRepository teachingRepository,
                                  TeachingValidator validator,
                                  ListViewController listViewController,
                                  ErrorHandlerUseCase errorHandler) {
        this.teachingRepository = teachingRepository;
        this.validator = validator;
        this.listViewController = listViewController;
        this.errorHandler = errorHandler;
    }

    @Override
    public EditTeachingResult execute(EditTeachingCommand command) {
        // Validate command data
        boolean validationResult = validator.validate(command);
        if (!validationResult) {
            errorHandler.execute("Data validation failed");
            return new EditTeachingResult(false, "Data validation failed", null);
        }

        // Fetch existing teaching
        Teaching teaching = teachingRepository.findById(command.getTeachingId());
        if (teaching == null) {
            // Assuming null indicates a connection error (as per sequence diagram)
            errorHandler.execute("Connection interrupted");
            return new EditTeachingResult(false, "Connection interrupted", null);
        }

        // Update teaching with new data
        teaching.setTitle(command.getTitle());
        teaching.setDescription(command.getDescription());
        teaching.setSchedule(command.getSchedule());

        // Save updated teaching
        try {
            teachingRepository.save(teaching);
        } catch (Exception e) {
            errorHandler.execute("Save failed: Connection lost");
            return new EditTeachingResult(false, "Save failed: Connection lost", null);
        }

        // Fetch all teachings and update the list view
        List<Teaching> teachingList = teachingRepository.findAll();
        listViewController.displayUpdatedTeachingList(teachingList);

        // Return success result
        return new EditTeachingResult(true, null, teaching);
    }

    /**
     * Cancels the ongoing operation and rolls back any pending changes.
     * In this simplified version, we just return a cancelled result.
     */
    public CancelledResult cancel() {
        // Rollback any pending changes (here just a placeholder)
        // In a real implementation, you might revert uncommitted changes.
        return new CancelledResult(true);
    }
}