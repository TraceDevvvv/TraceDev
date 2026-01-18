package com.example.usecase;

import com.example.dto.ModifyRefreshmentPointRequestDto;
import com.example.dto.ResultDto;
import com.example.validation.ValidationResult;

/**
 * Use case interface for modifying a refreshment point.
 */
public interface ModifyRefreshmentPointUseCase {
    ModifyRefreshmentPointRequestDto loadDataForModification(String pointId);
    ValidationResult validateModificationData(ModifyRefreshmentPointRequestDto request);
    ResultDto executeModification(ModifyRefreshmentPointRequestDto request);
}