package com.example.command;

import java.util.Stack;

/**
 * Maintains a history of commands to support undo functionality.
 */
public class CommandHistory {
    private Stack<Command> commandStack;

    public CommandHistory() {
        this.commandStack = new Stack<>();
    }

    /**
     * Pushes a command onto the history stack.
     * @param command The command to push
     */
    public void push(Command command) {
        commandStack.push(command);
    }

    /**
     * Pops the most recent command from the stack.
     * @return The command, or null if stack is empty
     */
    public Command pop() {
        if (commandStack.isEmpty()) {
            return null;
        }
        return commandStack.pop();
    }

    public boolean isEmpty() {
        return commandStack.isEmpty();
    }
}