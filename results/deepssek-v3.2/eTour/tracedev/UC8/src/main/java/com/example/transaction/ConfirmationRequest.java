package com.example.transaction;

import com.example.dto.RefreshmentPointUpdateDTO;
import java.time.LocalDateTime;

/**
 * Represents a pending confirmation request for an update.
 */
public class ConfirmationRequest {
    private String confirmationId;
    private RefreshmentPointUpdateDTO updateDTO;
    private LocalDateTime requestedAt;

    public ConfirmationRequest(String confirmationId, RefreshmentPointUpdateDTO updateDTO) {
        this.confirmationId = confirmationId;
        this.updateDTO = updateDTO;
        this.requestedAt = LocalDateTime.now();
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public RefreshmentPointUpdateDTO getUpdateDTO() {
        return updateDTO;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    /**
     * Checks if the request is expired (e.g., older than 5 minutes).
     */
    public boolean isExpired() {
        return requestedAt.plusMinutes(5).isBefore(LocalDateTime.now());
    }
}