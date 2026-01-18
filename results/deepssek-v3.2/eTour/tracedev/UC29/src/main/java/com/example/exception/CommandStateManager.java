package com.example.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Command-based state manager that keeps a history of commands.
 */
public class CommandStateManager implements StateManager {
    private List<Command> commandHistory;

    public CommandStateManager() {
        this.commandHistory = new ArrayList<>();
    }

    @Override
    public void restorePreviousState(State previousState) {
        System.out.println("CommandStateManager restoring previous state.");
        Command command = new RestoreStateCommand(previousState);
        executeCommand(command);
    }

    /**
     * Executes a command and adds it to history.
     * @param command The command to execute.
     */
    public void executeCommand(Command command) {
        command.execute();
        commandHistory.add(command);
        System.out.println("Command executed and added to history.");
    }

    /**
     * Undoes the last command in history.
     */
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
            System.out.println("Last command undone.");
        } else {
            System.out.println("No commands to undo.");
        }
    }
}