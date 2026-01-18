package com.example.justification.application.port.in;

import com.example.justification.application.dto.ModifyJustificationRequestDto;

/**
 * Interface for the use case to modify an existing justification.
 * This port defines the input contract for updating justification information.
 */
public interface ModifyJustificationUseCase {

    /**
     * Executes the use case to modify a specific justification with new details.
     *
     * @param id      The unique identifier of the justification to modify.
     * @param details A {@link ModifyJustificationRequestDto} containing the new details and status.
     */
    void execute(String id, ModifyJustificationRequestDto details);
}