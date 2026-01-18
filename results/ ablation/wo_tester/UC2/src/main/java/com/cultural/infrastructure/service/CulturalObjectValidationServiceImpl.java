package com.cultural.infrastructure.service;

import com.cultural.application.port.out.CulturalObjectRepository;
import com.cultural.application.service.CulturalObjectValidationService;
import com.cultural.application.dto.InsertCulturalObjectRequest;
import com.cultural.domain.model.CulturalObject;
import java.util.*;

/**
 * Validation service implementation.
 * Handles duplicate prevention as per quality requirement.
 */
public class CulturalObjectValidationServiceImpl implements CulturalObjectValidationService {
    private CulturalObjectRepository culturalObjectRepository;

    public CulturalObjectValidationServiceImpl(CulturalObjectRepository repository) {
        this.culturalObjectRepository = repository;
    }

    @Override
    public boolean isDuplicate(CulturalObject culturalObject) {
        List<CulturalObject> existing = culturalObjectRepository.findByNameAndLocation(
                culturalObject.getName(),
                culturalObject.getLocation()
        );
        return !existing.isEmpty();
    }

    @Override
    public boolean isValidData(InsertCulturalObjectRequest request) {
        // Simple validation: all fields non-empty.
        return request.getName() != null && !request.getName().trim().isEmpty() &&
                request.getDescription() != null && !request.getDescription().trim().isEmpty() &&
                request.getType() != null && !request.getType().trim().isEmpty() &&
                request.getLocation() != null && !request.getLocation().trim().isEmpty();
    }

    @Override
    public Map<String, String> validateRequiredFields(InsertCulturalObjectRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            errors.put("name", "Name is required");
        }
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            errors.put("description", "Description is required");
        }
        if (request.getType() == null || request.getType().trim().isEmpty()) {
            errors.put("type", "Type is required");
        }
        if (request.getLocation() == null || request.getLocation().trim().isEmpty()) {
            errors.put("location", "Location is required");
        }
        return errors;
    }

    @Override
    public Map<String, String> validateDataTypes(InsertCulturalObjectRequest request) {
        // For simplicity, assume all fields are strings, no type mismatch.
        // In a real scenario, we might check that type belongs to a predefined set, etc.
        Map<String, String> errors = new HashMap<>();
        // Example: type must be one of the allowed values
        Set<String> allowedTypes = Set.of("Monument", "Artifact", "Site", "Tradition");
        if (request.getType() != null && !allowedTypes.contains(request.getType())) {
            errors.put("type", "Type must be one of: Monument, Artifact, Site, Tradition");
        }
        return errors;
    }
}