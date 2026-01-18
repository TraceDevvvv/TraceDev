package com.example.application;

import com.example.application.command.CommandHandler;
import com.example.application.command.DeleteNoteCommand;
import com.example.application.command.DeleteNoteCommandHandler;
import com.example.ui.RegistryScreen;

public class DeleteNoteService {
    private CommandHandler<DeleteNoteCommand> commandHandler;
    private DeleteNoteCommandHandler deleteNoteCommandHandler; // Added for direct access

    // Constructor that accepts a command handler
    public DeleteNoteService(CommandHandler<DeleteNoteCommand> commandHandler) {
        this.commandHandler = commandHandler;
        if (commandHandler instanceof DeleteNoteCommandHandler) {
            this.deleteNoteCommandHandler = (DeleteNoteCommandHandler) commandHandler;
        }
    }

    // Execute deletion of a note
    public String executeDelete(DeleteNoteCommand command) {
        System.out.println("DeleteNoteService executing delete for note ID: " + command.getNoteId());
        try {
            commandHandler.handle(command);
            // After deletion, return to registry screen (Flow of Events Step 3)
            returnToRegistryScreen();
            return "Deletion completed successfully.";
        } catch (RuntimeException e) {
            // This catches the exception thrown by the handler when SMOS connection fails
            rollbackTransaction();
            return "Error: Deletion failed due to SMOS connection issue";
        }
    }

    // Handle interruption from administrator
    public String handleInterruption() {
        System.out.println("DeleteNoteService handling interruption.");
        if (deleteNoteCommandHandler != null) {
            deleteNoteCommandHandler.handleInterruption();
            return "Operation interrupted and state saved";
        } else {
            return "Cannot handle interruption: command handler not set.";
        }
    }

    // Rollback transaction in case of SMOS interruption
    public void rollbackTransaction() {
        System.out.println("Rolling back transaction due to SMOS connection issue.");
        // In a real scenario, this would revert any changes (e.g., unsend notification, restore note)
        System.out.println("Transaction rolled back.");
    }

    // Return to registry screen after operation
    private void returnToRegistryScreen() {
        RegistryScreen screen = new RegistryScreen("main-screen");
        screen.returnToView();
    }
}