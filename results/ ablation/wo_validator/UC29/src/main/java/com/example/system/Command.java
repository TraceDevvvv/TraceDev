package com.example.system;

/**
 * Command interface following the Command pattern.
 */
public interface Command {
    void execute();
    void undo();
}