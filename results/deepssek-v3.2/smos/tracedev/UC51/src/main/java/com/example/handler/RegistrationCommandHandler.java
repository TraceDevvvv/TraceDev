package com.example.handler;

import com.example.command.RejectRegistrationCommand;

/**
 * Interface for handling registration commands.
 */
public interface RegistrationCommandHandler {
    /**
     * Handles a reject registration command.
     * @param command the command to handle
     */
    void handle(RejectRegistrationCommand command);
}