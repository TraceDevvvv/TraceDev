package com.example.serv;

import com.example.enums.DayOfWeek;
import com.example.commands.DeleteMenuCommand;
import com.example.handlers.DeleteMenuCommandHandler;
import com.example.repositories.IMenuRepository;
import com.example.ui.UIDeleteForm;
import java.util.logging.Logger;

/**
 * Service Layer coordinating the delete operation flow.
 */
public class MenuService {
    private static final Logger LOGGER = Logger.getLogger(MenuService.class.getName());
    private DeleteMenuCommandHandler commandHandler;
    private IMenuRepository menuRepository;
    private DeleteMenuCommand pendingCommand;

    public MenuService(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
        this.commandHandler = new DeleteMenuCommandHandler(menuRepository);
    }

    /**
     * Requests confirmation for deletion (triggers UI dialog).
     */
    public void requestDeletionConfirmation(DayOfWeek day) {
        LOGGER.info("Requesting deletion confirmation for " + day);
        // In a real scenario, this would trigger UI via callback
    }

    /**
     * Confirms deletion after user approval.
     */
    public void confirmDeletion(DayOfWeek day, boolean confirmed) {
        if (confirmed) {
            LOGGER.info("Deletion confirmed for " + day);
            pendingCommand = new DeleteMenuCommand(day);
            pendingCommand.confirmed = true;
            commandHandler.handle(pendingCommand);
        } else {
            LOGGER.info("Deletion not confirmed for " + day);
        }
    }

    /**
     * Cancels pending deletion and rolls back.
     */
    public void cancelPendingDeletion() {
        LOGGER.info("Cancelling pending deletion.");
        if (pendingCommand != null) {
            commandHandler.cancel(pendingCommand);
        }
        pendingCommand = null;
    }

    /**
     * Private helper for confirmation (as per class diagram).
     */
    private boolean confirmDeletion(DayOfWeek day) {
        // This is a private method used internally; assuming it checks some condition
        return true;
    }

    /**
     * Private helper for cancellation (as per class diagram).
     */
    private void cancelDeletion() {
        cancelPendingDeletion();
    }

    /**
     * Deletes menu for the given day (public method as per class diagram).
     */
    public void deleteMenu(DayOfWeek day) {
        LOGGER.info("deleteMenu called for " + day);
        // Create command and handle deletion
        DeleteMenuCommand command = new DeleteMenuCommand(day);
        commandHandler.handle(command);
    }
}