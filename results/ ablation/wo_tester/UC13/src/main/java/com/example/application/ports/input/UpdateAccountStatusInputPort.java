package com.example.application.ports.input;

import com.example.application.dtos.UpdateAccountStatusRequest;
import com.example.application.dtos.UpdateAccountStatusResponse;

/**
 * Input port (use case interface) for the Update Account Status use case.
 * Follows hexagonal architecture pattern.
 */
public interface UpdateAccountStatusInputPort {
    UpdateAccountStatusResponse execute(UpdateAccountStatusRequest request);
}