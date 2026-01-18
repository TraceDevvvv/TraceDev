package com.example.infrastructure.presentation;

import com.example.application.ports.output.UpdateAccountStatusOutputPort;
import com.example.application.dtos.UpdateAccountStatusResponse;

/**
 * Concrete presenter implementation for the Update Account Status use case.
 * Transforms use case response into presentation format.
 */
public class UpdateAccountStatusPresenter implements UpdateAccountStatusOutputPort {
    
    @Override
    public void present(UpdateAccountStatusResponse response) {
        // Prepare notification message as per sequence diagram requirement
        String notificationMessage = prepareNotificationMessage(response);
        System.out.println("[PRESENTER] " + notificationMessage);
    }
    
    public String prepareNotificationMessage(UpdateAccountStatusResponse response) {
        if (response.isSuccess()) {
            return "Operation successful: " + response.getMessage() + 
                   ". Account status changed to: " + response.getUpdatedStatus();
        } else {
            return "Operation failed: " + response.getMessage();
        }
    }
}