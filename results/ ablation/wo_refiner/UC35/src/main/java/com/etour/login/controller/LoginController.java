package com.etour.login.controller;

import com.etour.login.dto.LoginRequestDTO;
import com.etour.login.dto.LoginResponseDTO;
import com.etour.login.exception.ETOURConnectionException;
import com.etour.login.service.LoginService;

/**
 * Controller for handling login requests.
 * Acts as a mediator between the UI and service layer.
 */
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Handles login request from the UI.
     * Main flow method for requirement REQ-005.
     *
     * @param request the login request DTO
     * @return login response DTO
     */
    public LoginResponseDTO handleLoginRequest(LoginRequestDTO request) {
        try {
            return loginService.authenticate(request);
        } catch (ETOURConnectionException e) {
            // Handle connection exception (Req 012)
            handleException(e);
            // Return error response
            return new LoginResponseDTO(false, null, "Connection error");
        }
    }

    /**
     * Handles exceptions that occur during login processing.
     * Added to satisfy requirement REQ-012.
     *
     * @param ex the exception to handle
     */
    public void handleException(Exception ex) {
        // Log the exception, send to monitoring, etc.
        System.err.println("Exception handled in LoginController: " + ex.getMessage());
        // In a real application, you might want to do more here
    }
}