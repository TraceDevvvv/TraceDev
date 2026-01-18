package com.touristagency.interfaceadapters.controllers;

import com.touristagency.application.ToggleStatusRequest;
import com.touristagency.application.ToggleStatusResponse;
import com.touristagency.application.usecase.ToggleTouristStatusUseCase;
import com.touristagency.interfaceadapters.dtos.ToggleStatusRequestDTO;
import com.touristagency.interfaceadapters.dtos.ToggleStatusResponseDTO;

/**
 * Controller that adapts the DTOs to use case requests.
 * Part of the interface adapters layer.
 */
public class ToggleTouristStatusController {
    private final ToggleTouristStatusUseCase useCase;

    public ToggleTouristStatusController(ToggleTouristStatusUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Handles the request from the web layer.
     * @param requestDto DTO from web controller
     * @return DTO to web controller
     */
    public ToggleStatusResponseDTO handle(ToggleStatusRequestDTO requestDto) {
        // Convert DTO to use case request
        ToggleStatusRequest request = new ToggleStatusRequest(
                requestDto.getTouristId(),
                requestDto.getTargetStatus()
        );

        // Execute use case
        ToggleStatusResponse response = useCase.execute(request);

        // Convert use case response to DTO
        return new ToggleStatusResponseDTO(
                response.isSuccess(),
                response.getMessage()
        );
    }
}