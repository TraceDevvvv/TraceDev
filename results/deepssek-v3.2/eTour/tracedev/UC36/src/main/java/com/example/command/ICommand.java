package com.example.command;

/**
 * Command pattern interface.
 */
public interface ICommand {
    /**
     * Executes the command.
     */
    void execute();

    /**
     * Undoes the command.
     */
    void undo();
}