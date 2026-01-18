package com.example.controller;

import com.example.dto.ModifyRefreshmentPointRequestDto;
import com.example.dto.ResultDto;
import com.example.security.AuthenticationService;
import com.example.usecase.ModifyRefreshmentPointUseCase;
import com.example.validation.ValidationResult;

/**
 * Controller coordinating the modification flow.
 */
public class RefreshmentPointController {
    
    private final ModifyRefreshmentPointUseCase modifyRefreshmentPointUseCase;
    private final AuthenticationService authenticationService;
    private final ErrorHandler errorHandler;

    public RefreshmentPointController(ModifyRefreshmentPointUseCase modifyRefreshmentPointUseCase,
                                      AuthenticationService authenticationService,
                                      ErrorHandler errorHandler) {
        this.modifyRefreshmentPointUseCase = modifyRefreshmentPointUseCase;
        this.authenticationService = authenticationService;
        this.errorHandler = errorHandler;
    }

    /**
     * Step 1: Load existing data and return DTO for form display.
     */
    public ModifyRefreshmentPointRequestDto showModifyForm(String pointId) {
        if (!authenticationService.isUserAuthenticated("currentUser")) {
            // In real application, throw security exception or redirect.
            System.err.println("User not authenticated.");
            return null;
        }
        return modifyRefreshmentPointUseCase.loadDataForModification(pointId);
    }

    /**
     * Step 3 & 4: Submit modification for validation.
     * Returns a result indicating whether validation passed.
     */
    public ResultDto submitModification(ModifyRefreshmentPointRequestDto request) {
        try {
            ValidationResult validationResult = modifyRefreshmentPointUseCase.validateModificationData(request);
            if (validationResult.isValid()) {
                return new ResultDto(true, "Please confirm");
            } else {
                // Activate error flow via ErroredUseCase (as per sequence diagram).
                // Note: The diagram shows ErroredUseCase activation; we assume it's done via a method call.
                // For simplicity, we'll just log and return error.
                System.err.println("Validation failed: " + validationResult.getErrors());
                return new ResultDto(false, "Validation failed: " + String.join(", ", validationResult.getErrors()));
            }
        } catch (Exception e) {
            errorHandler.handleError(e);
            return new ResultDto(false, errorHandler.getErrorMessage(e));
        }
    }

    /**
     * Step 5 & 6: Confirm and execute the modification.
     */
    public ResultDto confirmModification(ModifyRefreshmentPointRequestDto request) {
        try {
            return modifyRefreshmentPointUseCase.executeModification(request);
        } catch (Exception e) {
            errorHandler.handleError(e);
            return new ResultDto(false, "Failed to execute modification: " + e.getMessage());
        }
    }
}