package com.example.culturalgoods.service;

import com.example.culturalgoods.dto.CulturalGoodDTO;
import com.example.culturalgoods.exception.ConnectionException;
import com.example.culturalgoods.exception.DuplicateCulturalGoodException;
import com.example.culturalgoods.exception.InvalidDataException;
import com.example.culturalgoods.exception.SystemUnavailableException;
import com.example.culturalgoods.model.CulturalGood;
import com.example.culturalgoods.repository.ICulturalGoodRepository;

import java.util.UUID;

/**
 * Application Service for managing CulturalGood related business logic.
 * This class orchestrates operations related to cultural goods,
 * acting as an intermediary between the controller and the repository.
 */
public class CulturalGoodApplicationService {
    private final ICulturalGoodRepository culturalGoodRepository;

    /**
     * Constructs a CulturalGoodApplicationService with a given repository.
     *
     * @param culturalGoodRepository The repository for CulturalGood persistence.
     */
    public CulturalGoodApplicationService(ICulturalGoodRepository culturalGoodRepository) {
        this.culturalGoodRepository = culturalGoodRepository;
    }

    /**
     * Previews a cultural good by validating its data and checking for duplicates.
     * This method implements Flow of Events step 4 and checks for Quality Requirements.
     *
     * @param dto The CulturalGoodDTO containing data for the cultural good.
     * @return The validated CulturalGoodDTO if valid and unique.
     * @throws InvalidDataException If the DTO contains invalid or insufficient data.
     * @throws DuplicateCulturalGoodException If a cultural good with the same name already exists.
     */
    public CulturalGoodDTO previewCulturalGood(CulturalGoodDTO dto) throws InvalidDataException, DuplicateCulturalGoodException {
        System.out.println("[ApplicationService] Step 4: System verifies data for: " + dto.getName());

        // Validate Cultural Good Data
        validateCulturalGoodData(dto);

        // Check for duplicates (Quality Requirement)
        if (culturalGoodRepository.existsByName(dto.name)) {
            System.out.println("[ApplicationService] Duplicate cultural good found for name: " + dto.name);
            // Sequence diagram: CulturalGoodApplicationService --> CulturalGoodController: throw DuplicateCulturalGoodException
            throw new DuplicateCulturalGoodException("A cultural good with the name '" + dto.name + "' already exists.");
        }

        System.out.println("[ApplicationService] Data is valid and unique for: " + dto.name);
        // Sequence diagram: CulturalGoodApplicationService --> CulturalGoodController: return dto
        return dto;
    }

    /**
     * Inserts a new cultural good into the system after confirmation.
     * This method implements Flow of Events step 6 and handles persistence.
     *
     * @param dto The CulturalGoodDTO containing the data to be inserted.
     * @return The CulturalGoodDTO representing the newly inserted cultural good.
     * @throws SystemUnavailableException If there's an issue with the underlying persistence system (e.g., connection).
     */
    public CulturalGoodDTO insertCulturalGood(CulturalGoodDTO dto) throws SystemUnavailableException {
        System.out.println("[ApplicationService] Step 6: System memorizes the new cultural good: " + dto.getName());

        // Create CulturalGood entity from DTO and generate unique ID
        CulturalGood culturalGoodEntity = createCulturalGoodEntity(dto);

        try {
            // Save the cultural good to the repository
            culturalGoodRepository.save(culturalGoodEntity);
            System.out.println("[ApplicationService] CulturalGood inserted successfully: " + culturalGoodEntity.getName());
            // Sequence diagram: CulturalGoodApplicationService --> CulturalGoodController: return createdDto
            return dto; // Returning the original DTO as confirmation
        } catch (ConnectionException e) {
            // Sequence diagram: ICulturalGoodRepository --x CulturalGoodApplicationService: throw ConnectionException
            // CulturalGoodApplicationService --x CulturalGoodController: throw SystemUnavailableException
            System.err.println("[ApplicationService] Connection to ETOUR server interrupted: " + e.getMessage());
            throw new SystemUnavailableException("System is currently unavailable due to connection issues.", e);
        }
    }

    /**
     * Validates the data in the CulturalGoodDTO.
     * Private helper method as specified in the class diagram.
     *
     * @param dto The DTO to validate.
     * @throws InvalidDataException If any required field is null or empty.
     */
    private void validateCulturalGoodData(CulturalGoodDTO dto) throws InvalidDataException {
        if (dto.name == null || dto.name.trim().isEmpty()) {
            throw new InvalidDataException("Cultural good name cannot be empty.");
        }
        if (dto.description == null || dto.description.trim().isEmpty()) {
            throw new InvalidDataException("Cultural good description cannot be empty.");
        }
        if (dto.acquisitionDate == null) {
            throw new InvalidDataException("Cultural good acquisition date cannot be empty.");
        }
        // Add more complex validation rules if necessary
        System.out.println("[ApplicationService] CulturalGoodDTO data validated successfully.");
    }

    /**
     * Creates a CulturalGood entity from a CulturalGoodDTO.
     * This method also generates a unique ID for the new entity.
     * Private helper method as specified in the class diagram.
     *
     * @param dto The DTO to convert.
     * @return A new CulturalGood entity.
     */
    private CulturalGood createCulturalGoodEntity(CulturalGoodDTO dto) {
        String newId = UUID.randomUUID().toString(); // Generate a unique ID
        System.out.println("[ApplicationService] Generated new ID: " + newId + " for " + dto.getName());
        return new CulturalGood(newId, dto.name, dto.description, dto.acquisitionDate);
    }
}