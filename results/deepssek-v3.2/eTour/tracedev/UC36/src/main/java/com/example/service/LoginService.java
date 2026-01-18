package com.example.service;

import com.example.command.ICommand;
import com.example.data.AuthDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for authentication.
 */
public class LoginService {
    private AuthDAO authDAO;
    private List<ICommand> commandHistory;

    /**
     * Constructs a LoginService with an AuthDAO.
     * @param dao the data access object
     */
    public LoginService(AuthDAO dao) {
        this.authDAO = dao;
        this.commandHistory = new ArrayList<>();
    }

    /**
     * Authenticates a user.
     * @param username the username
     * @param password the password
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(String username, String password) {
        // Delegates to AuthDAO for validation.
        boolean isValid = authDAO.validateCredentials(username, password);
        // Save login attempt (optional, not in sequence).
        authDAO.saveLoginAttempt(username, isValid);
        return isValid;
    }

    /**
     * Executes a command and adds it to history.
     * @param cmd the command to execute
     */
    public void executeCommand(ICommand cmd) {
        if (cmd != null) {
            cmd.execute();
            commandHistory.add(cmd);
        }
    }

    /**
     * Undoes the last command in history.
     */
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            ICommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
            lastCommand.undo();
        }
    }
}