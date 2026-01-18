package com.example.service;

import com.example.dto.ModifyRefreshmentPointRequestDto;
import com.example.dto.ResultDto;
import com.example.entity.RefreshmentPoint;
import com.example.repository.RefreshmentPointRepository;
import com.example.usecase.ModifyRefreshmentPointUseCase;
import com.example.controller.ErroredUseCase;
import com.example.validation.RefreshmentPointValidator;
import com.example.validation.ValidationResult;

/**
 * Service implementing the modification use case.
 * Marked as Service and Transactional (stereotype implied).
 */
public class ModifyRefreshmentPointService implements ModifyRefreshmentPointUseCase {
    
    private final RefreshmentPointRepository refreshmentPointRepository;
    private final RefreshmentPointValidator dataValidator;
    private final ErroredUseCase erroredUseCase;

    public ModifyRefreshmentPointService(RefreshmentPointRepository refreshmentPointRepository, 
                                         RefreshmentPointValidator dataValidator,
                                         ErroredUseCase erroredUseCase) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.dataValidator = dataValidator;
        this.erroredUseCase = erroredUseCase;
    }

    @Override
    public ModifyRefreshmentPointRequestDto loadDataForModification(String pointId) {
        RefreshmentPoint point = refreshmentPointRepository.findById(pointId);
        if (point == null) {
            // In a real scenario, throw an exception or return null.
            return null;
        }
        // Map entity to DTO
        return new ModifyRefreshmentPointRequestDto(
            point.getId(), point.getName(), point.getLocation(),
            point.getOperatingHours(), point.getContactInfo()
        );
    }

    @Override
    public ValidationResult validateModificationData(ModifyRefreshmentPointRequestDto request) {
        return dataValidator.validateRequest(request);
    }

    @Override
    public ResultDto executeModification(ModifyRefreshmentPointRequestDto request) {
        // Step 1: retrieve the entity
        RefreshmentPoint point = refreshmentPointRepository.findById(request.getId());
        if (point == null) {
            return new ResultDto(false, "Refreshment point not found.");
        }
        // Step 2: update entity fields from DTO
        point.setLocation(request.getLocation());
        point.setOperatingHours(request.getOperatingHours());
        point.setContactInfo(request.getContactInfo());
        // Step 3: save
        RefreshmentPoint saved = refreshmentPointRepository.save(point);
        if (saved != null) {
            return new ResultDto(true, "Modification successful.");
        } else {
            return new ResultDto(false, "Failed to save changes.");
        }
    }
}