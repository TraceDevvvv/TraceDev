package com.example.menu;

/**
 * Interface for the Command design pattern.
 * Commands encapsulate a request as an object, thereby letting you parameterize clients with different requests,
 * queue or log requests, and support undoable operations.
 */
public interface Command {
    /**
     * Executes the command.
     * Implementations should define the specific action to be performed.
     *
     * @throws NetworkConnectionException if a network connection issue occurs during command execution.
     */
    void execute() throws NetworkConnectionException;
}