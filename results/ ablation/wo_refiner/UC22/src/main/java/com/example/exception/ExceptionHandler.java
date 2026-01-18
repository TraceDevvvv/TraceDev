package com.example.exception;

import com.example.dto.ErrorDTO;

/**
 * Handles exceptions and converts them to ErrorDTO.
 * Added to address quality requirement REQ-015 for Robustness and Maintainability.
 */
public class ExceptionHandler {
    public ErrorDTO handleException(Exception exception) {
        // Map different exception types to appropriate error codes
        String errorCode;
        if (exception.getMessage() != null && exception.getMessage().contains("DatabaseException")) {
            errorCode = "DB_CONNECTION_ERROR";
        } else if (exception.getMessage() != null && exception.getMessage().contains("ServiceException")) {
            errorCode = "SERVICE_ERROR";
        } else {
            errorCode = "UNKNOWN_ERROR";
        }
        
        String errorMessage = "Error: " + exception.getMessage();
        return new ErrorDTO(errorCode, errorMessage);
    }
}