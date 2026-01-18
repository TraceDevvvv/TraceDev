package com.example.system;

import java.util.Stack;

/**
 * Maintains history of executed commands for undo functionality.
 */
public class CommandHistory {
    private Stack<Command> commandStack;

    public CommandHistory() {
        commandStack = new Stack<>();
    }

    /**
     * Pushes a command onto the history stack.
     * @param command the command to push
     */
    public void push(Command command) {
        commandStack.push(command);
        System.out.println("CommandHistory: Command pushed to history.");
    }

    /**
     * Pops the most recent command from the stack.
     * @return the popped command
     */
    public Command pop() {
        Command command = commandStack.pop();
        System.out.println("CommandHistory: Command popped from history.");
        return command;
    }

    /**
     * Checks if command history is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return commandStack.isEmpty();
    }
}