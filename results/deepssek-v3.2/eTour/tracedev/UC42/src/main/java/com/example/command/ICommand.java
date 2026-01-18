package com.example.command;

/**
 * Command interface following the Command pattern.
 */
public interface ICommand {
    void execute();
    void undo();
}