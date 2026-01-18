package com.cultural.application.usecase;

import com.cultural.domain.model.CulturalObject;
import com.cultural.application.dto.OperationResult;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles errors and erroneous conditions.
 * Added to satisfy requirement Flow: 7.
 */
public class ErrorHandler {
    public OperationResult handleInvalidData(Map<String, String> errorDetails) {
        return new OperationResult(false, "Invalid data provided", "ERR_INVALID_DATA", errorDetails);
    }

    public OperationResult handleDuplicateObject(CulturalObject objectDetails) {
        Map<String, String> details = new HashMap<>();
        details.put("name", objectDetails.getName());
        details.put("location", objectDetails.getLocation());
        return new OperationResult(false, "Duplicate cultural object detected", "ERR_DUPLICATE", details);
    }

    public OperationResult handleConnectionError(String service) {
        Map<String, String> details = new HashMap<>();
        details.put("service", service);
        return new OperationResult(false, "Connection error with " + service, "ERR_CONNECTION", details);
    }
}