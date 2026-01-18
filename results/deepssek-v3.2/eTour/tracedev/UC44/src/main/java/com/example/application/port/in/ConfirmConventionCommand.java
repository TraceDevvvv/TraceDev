package com.example.application.port.in;

/**
 * Input port for confirming a convention request.
 */
public interface ConfirmConventionCommand {
    void execute(String requestId);
}