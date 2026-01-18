package com.example.command;

import com.example.service.AuthenticationService;
import com.example.model.Session;

/**
 * Command to recover the previous session state.
 */
public class RecoverStateCommand extends NotificationCommand {
    private AuthenticationService authenticationService;

    public RecoverStateCommand(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void execute() {
        // Execute the command by recovering the previous state.
        Session previousState = authenticationService.getPreviousState();
        if (previousState != null) {
            authenticationService.recoverToState(previousState);
        }
    }

    @Override
    public void undo() {
        // Undo operation: In a real scenario, we might revert the state recovery.
        // For simplicity, we just print a message.
        System.out.println("Undo RecoverStateCommand: previous state was recovered.");
    }
}