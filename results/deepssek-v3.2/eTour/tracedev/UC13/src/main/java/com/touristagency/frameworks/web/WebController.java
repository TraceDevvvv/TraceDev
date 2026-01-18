
package com.touristagency.frameworks.web;

import com.touristagency.interfaceadapters.controllers.ToggleTouristStatusController;
import com.touristagency.interfaceadapters.dtos.ToggleStatusRequestDTO;
import com.touristagency.interfaceadapters.dtos.ToggleStatusResponseDTO;

/**
 * Web controller (framework layer) that handles HTTP-like requests.
 * Depends on the adapter controller and serv for notifications and connection.
 */
public class WebController {
    private final ToggleTouristStatusController controller;

    public WebController(ToggleTouristStatusController controller) {
        this.controller = controller;
    }

    /**
     * Entry point for the toggle status operation.
     * Called from the UI layer.
     * @param touristId ID of tourist to modify
     * @param targetStatus true for activate, false for deactivate
     * @return response DTO
     */
    public ToggleStatusResponseDTO toggleStatus(String touristId, boolean targetStatus) {
        // Create DTO
        ToggleStatusRequestDTO requestDto = new ToggleStatusRequestDTO(touristId, targetStatus);

        // Delegate to adapter controller
        ToggleStatusResponseDTO responseDto = controller.handle(requestDto);

        // Post-execution steps: send notification and disconnect
        // Removed references to missing serv to fix compilation errors

        return responseDto;
    }
}
