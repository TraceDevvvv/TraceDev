package com.example.controllers;

import com.example.usecases.HandlePasswordModificationUseCase;
import com.example.commands.ErrorNotificationCommand;
import java.util.Map;

/**
 * Controller for password modification requests.
 */
public class PasswordModificationController extends FrontController {
    
    private HandlePasswordModificationUseCase handleUseCase;
    
    public PasswordModificationController(HandlePasswordModificationUseCase handleUseCase) {
        this.handleUseCase = handleUseCase;
    }
    
    /**
     * Handles requests with password parameters as per REQ-004 and REQ-009.
     * @param request the request type
     * @param providedPassword the provided password
     * @param confirmedPassword the confirmed password
     */
    public void handleRequest(String request, String providedPassword, String confirmedPassword) {
        if ("modify_password".equals(request)) {
            processModification(providedPassword, confirmedPassword);
        }
    }
    
    @Override
    public void handleRequest(String request) {
        // Override for compatibility, but the main method is the one with password parameters
        throw new UnsupportedOperationException("Use handleRequest(String, String, String) instead");
    }
    
    /**
     * Processes the password modification.
     * @param providedPassword the provided password
     * @param confirmedPassword the confirmed password
     */
    protected void processModification(String providedPassword, String confirmedPassword) {
        handleUseCase.handleModificationRequest(providedPassword, confirmedPassword);
    }
    
    /**
     * Executes an error notification command.
     * @param cmd the command to execute
     */
    public void executeCommand(ErrorNotificationCommand cmd) {
        cmd.execute();
    }
}