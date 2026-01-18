package com.example.service;

import com.example.model.Convention;
import com.example.model.ActivationResult;

/**
 * Handler interface for activation operations.
 */
public interface ActivationHandler {
    ActivationResult handleActivation(Convention convention);
    void logConnectionError();
}