package com.example.usecase;

import com.example.request.ModifyJusticeRequest;
import com.example.response.ModifyJusticeResponse;

/**
 * Use case interface for modifying justice.
 */
public interface ModifyJusticeUseCase {
    /**
     * Executes the modify justice use case.
     */
    ModifyJusticeResponse execute(ModifyJusticeRequest request);
}