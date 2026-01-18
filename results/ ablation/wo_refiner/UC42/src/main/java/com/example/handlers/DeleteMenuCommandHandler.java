package com.example.handlers;

import com.example.commands.DeleteMenuCommand;
import com.example.repositories.IMenuRepository;
import com.example.entities.Menu;
import com.example.exceptions.ConnectionException;
import java.util.logging.Logger;

/**
 * Handles the delete menu command, performing validation and coordinating with repository.
 */
public class DeleteMenuCommandHandler {
    private static final Logger LOGGER = Logger.getLogger(DeleteMenuCommandHandler.class.getName());
    private IMenuRepository menuRepository;

    public DeleteMenuCommandHandler(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Main handling method as per sequence diagram.
     */
    public void handle(DeleteMenuCommand command) {
        if (!validate(command)) {
            LOGGER.warning("Validation failed for command: " + command.day);
            throw new IllegalArgumentException("Command validation failed.");
        }

        try {
            // Find menu
            Menu menu = menuRepository.findByDay(command.day);
            if (menu == null) {
                LOGGER.warning("Menu not found for day: " + command.day);
                return;
            }

            // Begin transaction
            menuRepository.getUnitOfWork().beginTransaction();

            // Delete menu
            menuRepository.delete(menu);

            // Commit transaction
            menuRepository.getUnitOfWork().commit();
            LOGGER.info("Menu for " + command.day + " deleted successfully.");

        } catch (ConnectionException e) {
            LOGGER.severe("ConnectionException: " + e.getErrorDetails());
            menuRepository.getUnitOfWork().rollback();
            throw new RuntimeException("Delete failed due to connection issue.", e);
        } catch (Exception e) {
            menuRepository.getUnitOfWork().rollback();
            throw new RuntimeException("Delete failed.", e);
        }
    }

    /**
     * Validates the command.
     */
    private boolean validate(DeleteMenuCommand command) {
        return command != null && command.day != null;
    }

    /**
     * Cancels the command and rolls back transaction.
     */
    public void cancel(DeleteMenuCommand command) {
        LOGGER.info("Cancelling command for " + command.day);
        menuRepository.getUnitOfWork().rollback();
        command.cancel();
    }
}