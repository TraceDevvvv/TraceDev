package com.tourist.app.interfaces;

/**
 * Generic interface for command handlers (CQRS pattern).
 * @param <TCommand> the type of command
 */
public interface ICommandHandler<TCommand> {
    /**
     * Handles the given command.
     * @param command the command to handle
     */
    void Handle(TCommand command);
}