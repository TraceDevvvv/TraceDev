package com.example.command;

/**
 * Generic command interface for the Command pattern.
 */
public interface Command {
    boolean execute();
    void undo();
}