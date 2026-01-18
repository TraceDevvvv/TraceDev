package com.touristagency.application.usecase;

import com.touristagency.application.ToggleStatusRequest;
import com.touristagency.application.ToggleStatusResponse;

/**
 * Use case interface for toggling tourist status.
 * Part of the use case layer (application business rules).
 */
public interface ToggleTouristStatusUseCase {
    /**
     * Executes the tourist status toggle operation.
     * @param request contains touristId and targetStatus
     * @return response indicating success/failure and message
     */
    ToggleStatusResponse execute(ToggleStatusRequest request);
}