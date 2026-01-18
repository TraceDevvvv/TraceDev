package com.example.application;

import com.example.domain.DayOfWeek;
import com.example.command.ICommand;
import com.example.command.DailyMenuCommandFactory;
import com.example.presentation.DeleteDailyMenuController;

/**
 * Interactor implementing the use case for deleting a daily menu.
 * Handles the business logic and coordinates command execution.
 */
public class DeleteDailyMenuInteractor implements IDeleteDailyMenuUseCase {
    private DailyMenuCommandFactory commandFactory;
    private DeleteDailyMenuController controller; // For callbacks

    public DeleteDailyMenuInteractor(DailyMenuCommandFactory commandFactory, DeleteDailyMenuController controller) {
        this.commandFactory = commandFactory;
        this.controller = controller;
    }

    @Override
    public void deleteDailyMenu(DayOfWeek day) {
        // Create the delete command
        ICommand deleteCommand = commandFactory.createDeleteCommand(day);
        // Request confirmation from the user (via controller and view)
        controller.requestConfirmation();
    }

    /**
     * Proceed with deletion after confirmation.
     */
    public void proceedWithDeletion(DayOfWeek day) {
        try {
            ICommand deleteCommand = commandFactory.createDeleteCommand(day);
            deleteCommand.execute();
            // Notify success
            controller.notifySuccess();
        } catch (Exception e) {
            controller.notifyError(e.getMessage());
        }
    }

    /**
     * Creates a new DeleteDailyMenuCommand with given day and repository.
     * Corresponds to message m10 in sequence diagram: new DeleteDailyMenuCommand(day, repository)
     */
    public void newDeleteDailyMenuCommand(DayOfWeek day, com.example.infrastructure.IDailyMenuRepository repository) {
        // This method is a placeholder for the message traceability.
        // The actual command creation is done in commandFactory.createDeleteCommand(day)
        // For exact traceability, we add this method to match the sequence diagram.
    }
}