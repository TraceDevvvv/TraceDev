package com.example.service;

import com.example.domain.CulturalGood;
import com.example.dto.CulturalGoodDTO;
import com.example.repository.CulturalGoodRepository;
import java.util.List;

/**
 * Service layer for cultural good operations.
 * Contains business logic and orchestrates repository calls.
 */
public class CulturalGoodService {
    private CulturalGoodRepository culturalGoodRepository;

    /**
     * Constructor with dependency injection.
     * @param repository the CulturalGoodRepository implementation
     */
    public CulturalGoodService(CulturalGoodRepository repository) {
        this.culturalGoodRepository = repository;
    }

    /**
     * Validates a CulturalGood entity.
     * Currently relies on domain validation, but can be extended.
     * @param culturalGood the entity to validate
     */
    private void validateCulturalGood(CulturalGood culturalGood) {
        // Validation is already performed in CulturalGood constructor.
        // This method can be extended for additional business rules.
        if (culturalGood == null) {
            throw new IllegalArgumentException("CulturalGood cannot be null");
        }
    }

    /**
     * Checks for duplicate cultural goods by name.
     * Throws DuplicateErrorException if a duplicate is found.
     * @param culturalGood the entity to check
     */
    private void checkForDuplicates(CulturalGood culturalGood) {
        if (culturalGoodRepository.existsByName(culturalGood.getName())) {
            throw new DuplicateErrorException("A cultural good with name '" + culturalGood.getName() + "' already exists.");
        }
    }

    /**
     * Inserts a new cultural good after validation and duplicate check.
     * Requests confirmation from controller before actual insertion.
     * @param culturalGoodDTO the DTO containing data for the new cultural good
     */
    public void insertCulturalGood(CulturalGoodDTO culturalGoodDTO) {
        // Create domain object from DTO
        CulturalGood culturalGood = CulturalGood.createFromDTO(culturalGoodDTO);
        // Validate
        validateCulturalGood(culturalGood);
        // Check for duplicates
        checkForDuplicates(culturalGood);
        // If no duplicates, the controller will request confirmation.
        // The actual save will happen in insertCulturalGoodConfirmed.
    }

    /**
     * Called after user confirmation to perform the actual insertion.
     * @param culturalGood the validated cultural good to insert
     */
    public void insertCulturalGoodConfirmed(CulturalGood culturalGood) {
        culturalGoodRepository.save(culturalGood);
    }

    /**
     * Retrieves all cultural goods.
     * @return a list of all CulturalGoods
     */
    public List<CulturalGood> getAllCulturalGoods() {
        return culturalGoodRepository.findAll();
    }

    /**
     * Custom exception for duplicate cultural goods.
     */
    public static class DuplicateErrorException extends RuntimeException {
        public DuplicateErrorException(String message) {
            super(message);
        }
    }
}