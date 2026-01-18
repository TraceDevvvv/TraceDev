package com.example.service;

import com.example.dto.PositionDTO;
import com.example.dto.SearchDTO;
import com.example.repository.PositionRepository;
import java.time.LocalDateTime;

/**
 * Service that orchestrates the system initialization use case.
 * Coordinates the flow and may use async/await for GPS calls.
 */
public class SystemInitializationService {
    private PositionRepository positionRepository;

    /**
     * Constructor with dependency injection.
     * @param positionRepository the repository to use for position retrieval
     */
    public SystemInitializationService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    /**
     * Initializes the system and retrieves the current position.
     * This method orchestrates the sequence as shown in the diagram.
     * @param searchData the search data (used for context, not directly for position)
     * @return PositionDTO if successful, null if position not detectable
     */
    public PositionDTO initializeSystem(SearchDTO searchData) {
        // Step 1: request current position via repository (could be async in real scenario)
        PositionDTO position = requestCurrentPosition();
        // Step 2: create a new PositionDTO (as shown in sequence diagram)
        // The repository already returns a fully formed DTO, but we can still create a new one if needed.
        // According to sequence diagram, we create a new DTO here. We'll copy values for clarity.
        if (position != null) {
            // Create a new instance (simulating the step "InitService -> PosDTO : new PositionDTO(...)")
            return new PositionDTO(
                position.getLatitude(),
                position.getLongitude(),
                position.getAccuracy(),
                LocalDateTime.now() // use current timestamp
            );
        } else {
            return null; // Position not detectable
        }
    }

    /**
     * Protected method to request current position from the repository.
     * This abstracts the call and allows potential async implementation.
     * @return PositionDTO from repository, or null if not available
     */
    protected PositionDTO requestCurrentPosition() {
        // In a real async scenario, this could be an asynchronous call.
        // For simplicity, we call synchronously.
        return positionRepository.getCurrentPosition();
    }
}