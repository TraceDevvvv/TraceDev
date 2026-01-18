package com.example.service;

import com.example.dto.RefreshmentDTO;
import com.example.domain.Refreshment;
import com.example.data.RefreshmentRepository;
import com.example.infrastructure.ETOURServerConnection;

/**
 * Service layer containing business logic for refreshment operations.
 */
public class RefreshmentService {
    private RefreshmentRepository refreshmentRepository;
    private ETOURServerConnection connection;

    public RefreshmentService(RefreshmentRepository refreshmentRepository, ETOURServerConnection connection) {
        this.refreshmentRepository = refreshmentRepository;
        this.connection = connection;
    }

    /**
     * Validates the refreshment data according to business rules.
     * @param refreshmentDTO the DTO to validate
     * @return a ValidationResult indicating validity and error messages
     */
    public ValidationResult validateRefreshmentData(RefreshmentDTO refreshmentDTO) {
        ValidationResult result = new ValidationResult();
        if (refreshmentDTO.getName() == null || refreshmentDTO.getName().trim().isEmpty()) {
            result.addErrorMessage("Name is required.");
        }
        if (refreshmentDTO.getPrice() == null || refreshmentDTO.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            result.addErrorMessage("Price must be greater than zero.");
        }
        // Additional validation rules can be added here.
        return result;
    }

    /**
     * Updates a refreshment entity based on the DTO.
     * @param refreshmentDTO the DTO with updated data
     * @return true if update succeeded, false otherwise
     */
    public Boolean updateRefreshment(RefreshmentDTO refreshmentDTO) {
        Refreshment refreshment = refreshmentRepository.findById(refreshmentDTO.getId());
        if (refreshment == null) {
            return false;
        }
        refreshment.updateFromDTO(refreshmentDTO); // Added to satisfy requirement REQ-FLOW-009
        
        // Check connection before saving
        if (!connection.checkConnection()) {
            return false;
        }
        
        refreshmentRepository.save(refreshment);
        return true;
    }

    /**
     * Validate data rules (from sequence diagram).
     * @param refreshmentDTO the DTO to validate
     * @return true if valid, false otherwise
     */
    public Boolean validateDataRules(RefreshmentDTO refreshmentDTO) {
        ValidationResult result = validateRefreshmentData(refreshmentDTO);
        return result.isValid();
    }

    /**
     * Map DTO to Domain object (from sequence diagram).
     * @param refreshmentDTO the DTO
     * @return the domain object
     */
    public Refreshment mapDTOToDomainObject(RefreshmentDTO refreshmentDTO) {
        Refreshment refreshment = new Refreshment();
        refreshment.setId(refreshmentDTO.getId());
        refreshment.setName(refreshmentDTO.getName());
        refreshment.setDescription(refreshmentDTO.getDescription());
        refreshment.setPrice(refreshmentDTO.getPrice());
        refreshment.setAvailable(refreshmentDTO.isAvailable());
        return refreshment;
    }
}