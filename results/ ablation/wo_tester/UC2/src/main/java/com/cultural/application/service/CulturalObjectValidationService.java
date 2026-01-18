package com.cultural.application.service;

import com.cultural.application.dto.InsertCulturalObjectRequest;
import com.cultural.domain.model.CulturalObject;
import java.util.Map;

/**
 * Validation service interface for cultural objects.
 */
public interface CulturalObjectValidationService {
    boolean isDuplicate(CulturalObject culturalObject);
    boolean isValidData(InsertCulturalObjectRequest request);
    Map<String, String> validateRequiredFields(InsertCulturalObjectRequest request);
    Map<String, String> validateDataTypes(InsertCulturalObjectRequest request);
}