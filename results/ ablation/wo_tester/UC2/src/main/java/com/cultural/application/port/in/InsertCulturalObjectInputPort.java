package com.cultural.application.port.in;

import com.cultural.application.dto.InsertCulturalObjectRequest;
import com.cultural.application.dto.InsertCulturalObjectResponse;

/**
 * Input port interface for the Insert Cultural Object use case.
 * Defines the contract for the primary use case operations.
 */
public interface InsertCulturalObjectInputPort {
    /**
     * Executes the insertion use case.
     */
    InsertCulturalObjectResponse execute(InsertCulturalObjectRequest request);

    /**
     * Confirms a pending operation.
     * Added to satisfy requirement Flow: 8.
     */
    InsertCulturalObjectResponse confirmOperation(String requestId);

    /**
     * Cancels a pending operation.
     * Added to satisfy requirement Flow: 11.
     */
    InsertCulturalObjectResponse cancelOperation(String requestId);
}