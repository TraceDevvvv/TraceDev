package com.example.justification.application.port.in;

import com.example.justification.application.dto.JustificationDetailsDto;

/**
 * Interface for the use case to view justification details.
 * This port defines the input contract for retrieving justification information.
 */
public interface ViewJustificationDetailsUseCase {

    /**
     * Executes the use case to retrieve details of a specific justification.
     *
     * @param id The unique identifier of the justification.
     * @return A {@link JustificationDetailsDto} containing the details of the justification.
     */
    JustificationDetailsDto execute(String id);
}