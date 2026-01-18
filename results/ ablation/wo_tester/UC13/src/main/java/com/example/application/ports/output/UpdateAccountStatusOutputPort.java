package com.example.application.ports.output;

import com.example.application.dtos.UpdateAccountStatusResponse;

/**
 * Output port for presenting the result of the Update Account Status use case.
 */
public interface UpdateAccountStatusOutputPort {
    void present(UpdateAccountStatusResponse response);
}