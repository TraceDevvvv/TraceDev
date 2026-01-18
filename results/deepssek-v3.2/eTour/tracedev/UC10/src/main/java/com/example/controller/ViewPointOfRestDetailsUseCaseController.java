package com.example.controller;

import com.example.domain.PointOfRest;
import com.example.dto.PointOfRestDto;
import com.example.repository.PointOfRestRepository;
import com.example.infrastructure.DataAccessException;

/**
 * Use case controller for viewing point of rest details.
 * Core Domain/Application Layer class.
 */
public class ViewPointOfRestDetailsUseCaseController {
    private PointOfRestRepository repository;

    public ViewPointOfRestDetailsUseCaseController(PointOfRestRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the use case: retrieves a PointOfRest by ID.
     * @param id the point of rest identifier.
     * @return the PointOfRest domain object.
     * @throws DataAccessException if data retrieval fails.
     */
    public PointOfRest execute(int id) throws DataAccessException {
        return repository.findById(id);
    }

    /**
     * Converts a PointOfRest domain object to a DTO.
     * @param domainObj the domain object.
     * @return the corresponding DTO.
     */
    public PointOfRestDto convertToDto(PointOfRest domainObj) {
        return new PointOfRestDto(
                domainObj.getId(),
                domainObj.getName(),
                domainObj.getLocation(),
                domainObj.getAmenities()
        );
    }

    /**
     * Converts a PointOfRest domain object to a DTO (alternate name, sequence diagram m17).
     * @param DomainObject the domain object.
     * @return the corresponding DTO.
     */
    public PointOfRestDto convertToDto2(PointOfRest DomainObject) {
        return convertToDto(DomainObject);
    }
}