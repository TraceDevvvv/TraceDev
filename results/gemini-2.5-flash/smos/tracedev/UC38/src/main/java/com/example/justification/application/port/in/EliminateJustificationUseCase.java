package com.example.justification.application.port.in;

/**
 * Interface for the use case to eliminate (delete) a justification.
 * This port defines the input contract for removing a justification.
 */
public interface EliminateJustificationUseCase {

    /**
     * Executes the use case to eliminate a specific justification.
     *
     * @param id The unique identifier of the justification to eliminate.
     */
    void execute(String id);
}