package com.example.command;

import com.example.service.LoginService;

/**
 * Concrete command for login operation.
 */
public class LoginCommand implements ICommand {
    private LoginService loginService;
    private String username;
    private String password;
    private Object previousState; // Could be used for undo, but not implemented in this flow

    /**
     * Creates a login command.
     * @param service the login service
     * @param user the username
     * @param pass the password
     */
    public LoginCommand(LoginService service, String user, String pass) {
        this.loginService = service;
        this.username = user;
        this.password = pass;
        this.previousState = null;
    }

    /**
     * Executes authentication.
     */
    @Override
    public void execute() {
        // In this sequence, authentication is performed directly by service,
        // so execute might do nothing or call service.authenticate.
        // For simplicity, we leave it empty as per the sequence diagram.
    }

    /**
     * Undoes the login command (not used in the given sequence).
     */
    @Override
    public void undo() {
        // Implementation not required for the provided sequence.
    }
}