package com.example.command;

/**
 * Interface for the Command pattern.
 */
public interface ICommand {
    void execute();
    void undo();
}