package com.example.controller;

import com.example.dto.RefreshmentPointFormDTO;
import com.example.model.*;
import com.example.service.RefreshmentPointService;
import com.example.service.AuthenticationService;

/**
 * Controller for modifying a refreshment point.
 */
public class ModifyRefreshmentPointController {
    private RefreshmentPointService service;
    private AuthenticationService authService;

    public ModifyRefreshmentPointController(RefreshmentPointService service, AuthenticationService authService) {
        this.service = service;
        this.authService = authService;
    }

    public Result handleModification(ModifyRefreshmentPointCommand command) {
        // Check session validity (implied from sequence diagram)
        if (!authService.validateOperatorSession(command.getOperatorId())) {
            return Result.failure("Invalid operator session");
        }

        // Step 1: Validate input
        ValidationResult inputValidation = validateInput(command.getFormData());
        if (!inputValidation.isValid()) {
            return Result.failure("Input validation failed: " + inputValidation.getErrors());
        }

        // Step 2: Check permissions
        if (!checkPermissions(command.getOperatorId())) {
            return Result.failure("Insufficient permissions");
        }

        // Step 3: Business validation via service
        Result serviceResult = service.modifyRefreshmentPoint(command);
        if (!serviceResult.isSuccess()) {
            return serviceResult;
        }

        // At this point the service returns a confirmation request
        return serviceResult;
    }

    ValidationResult validateInput(RefreshmentPointFormDTO formData) {
        // Delegate to DTO validation
        return formData.validate();
    }

    boolean checkPermissions(String operatorId) {
        // In a real system, check operator's permissions
        // For now, assume all authenticated operators can modify
        return operatorId != null && !operatorId.trim().isEmpty();
    }

    public RefreshmentPointFormDTO mapToFormDTO(RefreshmentPoint point) {
        // Map entity to DTO
        // Assuming we have a data mapper, but for simplicity do direct mapping
        LocationDTO locDto = null;
        if (point.getLocation() != null) {
            locDto = new LocationDTO(
                    point.getLocation().getLatitude(),
                    point.getLocation().getLongitude(),
                    point.getLocation().getAddress()
            );
        }
        return new RefreshmentPointFormDTO(
                point.getName(),
                locDto,
                point.getAttributes(),
                "Operator notes placeholder"
        );
    }

    public Result confirmModification(String confirmationToken) {
        // Confirm modification with token
        if (!service.verifyConfirmationToken(confirmationToken)) {
            return Result.failure("Invalid confirmation token");
        }

        // For sequence diagram, we assume the token is valid and proceed.
        // The actual modification would be done elsewhere.
        return Result.success("Modification confirmed", null);
    }
}