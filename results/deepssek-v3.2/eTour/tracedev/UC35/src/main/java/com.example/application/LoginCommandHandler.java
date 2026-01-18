package com.example.application;

import com.example.interfaces.IAuthenticatorService;
import com.example.dtos.AuthToken;
import com.example.dtos.CredentialsDTO;

/**
 * Handles the login command (CQRS command side).
 */
public class LoginCommandHandler {
    private IAuthenticatorService authenticatorService;

    public LoginCommandHandler(IAuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    /**
     * Processes the login command.
     *
     * @param command the login command
     * @return a command result containing either an auth token or an error
     */
    public CommandResult<AuthToken> handle(LoginCommand command) {
        try {
            CredentialsDTO credentials = command.toCredentialsDTO();
            AuthToken token = authenticatorService.authenticate(credentials);
            return new CommandResult<>(true, token, null);
        } catch (Exception e) {
            return new CommandResult<>(false, null, e.getMessage());
        }
    }

    /**
     * Simple generic result container for command execution.
     *
     * @param <T> type of result value
     */
    public static class CommandResult<T> {
        private final boolean success;
        private final T value;
        private final String error;

        public CommandResult(boolean success, T value, String error) {
            this.success = success;
            this.value = value;
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public T getValue() {
            return value;
        }

        public String getError() {
            return error;
        }
    }
}