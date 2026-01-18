package com.example.service;

import com.example.dto.CulturalObjectDTO;
import com.example.dto.SearchFormDTO;
import com.example.domain.CulturalObject;
import com.example.etour.ETOURService;
import com.example.repository.CulturalObjectRepository;
import com.example.repository.CulturalObjectRepositoryImpl;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of CulturalObjectService.
 * Contains business logic for searching cultural objects.
 */
public class CulturalObjectServiceImpl implements CulturalObjectService {
    private CulturalObjectRepository culturalObjectRepository;
    private ETOURService etourService;

    public CulturalObjectServiceImpl() {
        this.culturalObjectRepository = new CulturalObjectRepositoryImpl();
        this.etourService = new ETOURServiceImpl();
    }

    @Override
    public List<CulturalObjectDTO> searchObjects(SearchFormDTO searchCriteria) {
        long startTime = System.currentTimeMillis();

        // Validate search criteria (Flow of Events 5)
        if (!validateSearchCriteria(searchCriteria)) {
            throw new IllegalArgumentException("Invalid search criteria.");
        }

        // Check ETOUR connection
        if (!etourService.checkConnection()) {
            etourService.handleConnectionLoss();
            throw new RuntimeException("Connection to ETOUR server interrupted.");
        }

        // Delegate to repository
        List<CulturalObject> objects = culturalObjectRepository.findByCriteria(
            searchCriteria.getCriteria(),
            searchCriteria.getFilterType(),
            searchCriteria.getDateRange()
        );

        // Convert entities to DTOs
        List<CulturalObjectDTO> dtos = convertToDTOs(objects);

        long endTime = System.currentTimeMillis();
        // Quality Requirement: Ensure completion within 2 seconds.
        if ((endTime - startTime) > 2000) {
            System.out.println("Warning: Search took longer than 2 seconds.");
        }
        return dtos;
    }

    /**
     * Validates the search criteria.
     * Flow of Events 5.
     */
    public boolean validateSearchCriteria(SearchFormDTO searchCriteria) {
        // Simple validation: criteria should not be null or empty.
        return searchCriteria != null &&
               searchCriteria.getCriteria() != null &&
               !searchCriteria.getCriteria().trim().isEmpty();
    }

    /**
     * Converts a list of CulturalObject entities to CulturalObjectDTOs.
     */
    public List<CulturalObjectDTO> convertToDTOs(List<CulturalObject> objects) {
        return objects.stream()
                      .map(CulturalObjectDTO::new)
                      .collect(Collectors.toList());
    }
}