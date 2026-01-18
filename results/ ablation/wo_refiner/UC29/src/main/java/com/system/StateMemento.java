package com.system;

/**
 * StateMemento interface for memento pattern.
 * Methods: getState, restore.
 */
public interface StateMemento {
    SystemState getState();
    void restore(SystemState state);
}