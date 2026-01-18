package com.example.service;

import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointType;
import com.example.domain.SearchCriteria;
import com.example.dto.RefreshmentPointDTO;
import com.example.exception.ServiceException;
import com.example.repository.IRefreshmentPointRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for searching refreshment points.
 * Implements main and alternative flows from sequence diagram.
 */
public class RefreshmentPointSearchService {
    private IRefreshmentPointRepository refreshmentPointRepository;

    // Constructor with dependency injection
    public RefreshmentPointSearchService(IRefreshmentPointRepository refreshmentPointRepository) {
        this.refreshmentPointRepository = refreshmentPointRepository;
    }

    /**
     * Searches refreshment points based on criteria.
     * @param criteria domain search criteria
     * @return list of refreshment points
     * @throws ServiceException if search fails (e.g., repository throws DataAccessException)
     */
    public List<RefreshmentPoint> searchRefreshmentPoints(SearchCriteria criteria) throws ServiceException {
        // Validate criteria (simplified validation)
        if (!isCriteriaValid(criteria)) {
            // Alternative flow: criteria invalid
            return handleInvalidCriteria();
        }

        // Main flow: criteria valid
        try {
            return refreshmentPointRepository.findByCriteria(criteria);
        } catch (Exception e) {
            // Alternative flow: connection interrupted or other data access error
            throw new ServiceException("Error searching refreshment points", e);
        }
    }

    /**
     * Handles invalid criteria by returning an empty list.
     * Called in alternative flow when criteria is invalid.
     */
    public List<RefreshmentPoint> handleInvalidCriteria() {
        // Return empty list as per sequence diagram
        return new ArrayList<>();
    }

    /**
     * Converts a list of domain objects to DTOs for presentation layer.
     * This method is called in the sequence diagram (convertToDTOs).
     */
    public List<RefreshmentPointDTO> convertToDTOs(List<RefreshmentPoint> domainList) {
        List<RefreshmentPointDTO> dtos = new ArrayList<>();
        for (RefreshmentPoint point : domainList) {
            RefreshmentPointDTO dto = new RefreshmentPointDTO();
            dto.setId(point.getId());
            dto.setName(point.getName());
            dto.setLocation(point.getLocation());
            dto.setType(point.getType().toString());
            dto.setAmenities(point.getAmenities());
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Simple validation of criteria.
     * For demonstration, we consider criteria valid if maxDistance >= 0.
     */
    private boolean isCriteriaValid(SearchCriteria criteria) {
        return criteria != null && criteria.getMaxDistance() >= 0;
    }

    /**
     * Helper method to map DTO to domain criteria.
     * Used by controller to convert SearchCriteriaDTO to SearchCriteria.
     */
    public SearchCriteria mapToDomainCriteria(com.example.dto.SearchCriteriaDTO dto) {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setLocationFilter(dto.getLocationFilter());
        // Map typeFilter string to enum
        RefreshmentPointType type = null;
        try {
            type = RefreshmentPointType.valueOf(dto.getTypeFilter().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            // If type string is invalid, leave as null (no filter)
        }
        criteria.setTypeFilter(type);
        criteria.setMaxDistance(dto.getMaxDistance());
        return criteria;
    }
}