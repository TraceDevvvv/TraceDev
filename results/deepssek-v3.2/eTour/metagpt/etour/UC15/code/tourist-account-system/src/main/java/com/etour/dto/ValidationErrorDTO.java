package com.etour.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object for validation errors.
 * Used to provide detailed validation error information in API responses.
 * Standardizes error response format across the application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorDTO {
    
    /** Timestamp when the error occurred */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    /** HTTP status code */
    private int status;
    
    /** Error type/name */
    private String error;
    
    /** Detailed error message */
    private String message;
    
    /** Request path that caused the error */
    private String path;
    
    /** List of field-specific validation errors */
    private List<FieldError> fieldErrors;
    
    /** Global validation errors (not tied to a specific field) */
    private List<String> globalErrors;
    
    /** Additional error details (context-specific) */
    private Map<String, Object> details;
    
    /**
     * Inner class representing a field-specific validation error.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldError {
        /** Name of the field with the error */
        private String field;
        
        /** Rejected value (if available) */
        private Object rejectedValue;
        
        /** Error message */
        private String message;
        
        /** Error code/category */
        private String code;
        
        /**
         * Creates a formatted string representation of the field error.
         * 
         * @return Formatted error string
         */
        public String getFormattedError() {
            String valueStr = rejectedValue != null ? rejectedValue.toString() : "null";
            return String.format("Field '%s': %s (value: %s, code: %s)", 
                    field, message, valueStr, code);
        }
    }
    
    /**
     * Creates a ValidationErrorDTO from field errors.
     * 
     * @param fieldErrors List of field-specific errors
     * @param path The request path
     * @return ValidationErrorDTO instance
     */
    public static ValidationErrorDTO fromFieldErrors(List<FieldError> fieldErrors, String path) {
        return ValidationErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Validation Failed")
                .message("One or more validation errors occurred")
                .path(path)
                .fieldErrors(fieldErrors)
                .globalErrors(new ArrayList<>())
                .build();
    }
    
    /**
     * Creates a ValidationErrorDTO for a single field error.
     * 
     * @param field The field with error
     * @param rejectedValue The rejected value
     * @param message Error message
     * @param path The request path
     * @return ValidationErrorDTO instance
     */
    public static ValidationErrorDTO createFieldError(String field, Object rejectedValue, 
                                                      String message, String path) {
        FieldError fieldError = FieldError.builder()
                .field(field)
                .rejectedValue(rejectedValue)
                .message(message)
                .code("VALIDATION_ERROR")
                .build();
        
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(fieldError);
        
        return fromFieldErrors(fieldErrors, path);
    }
    
    /**
     * Creates a ValidationErrorDTO for global errors.
     * 
     * @param globalErrors List of global error messages
     * @param path The request path
     * @return ValidationErrorDTO instance
     */
    public static ValidationErrorDTO fromGlobalErrors(List<String> globalErrors, String path) {
        return ValidationErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Validation Failed")
                .message("One or more validation errors occurred")
                .path(path)
                .fieldErrors(new ArrayList<>())
                .globalErrors(globalErrors)
                .build();
    }
    
    /**
     * Adds a field error to this DTO.
     * 
     * @param fieldError The field error to add
     */
    public void addFieldError(FieldError fieldError) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(fieldError);
    }
    
    /**
     * Adds a simple field error.
     * 
     * @param field Field name
     * @param rejectedValue Rejected value
     * @param message Error message
     */
    public void addFieldError(String field, Object rejectedValue, String message) {
        FieldError fieldError = FieldError.builder()
                .field(field)
                .rejectedValue(rejectedValue)
                .message(message)
                .code("VALIDATION_ERROR")
                .build();
        addFieldError(fieldError);
    }
    
    /**
     * Adds a global error message.
     * 
     * @param errorMessage Global error message
     */
    public void addGlobalError(String errorMessage) {
        if (globalErrors == null) {
            globalErrors = new ArrayList<>();
        }
        globalErrors.add(errorMessage);
    }
    
    /**
     * Checks if there are any validation errors.
     * 
     * @return true if there are errors, false otherwise
     */
    public boolean hasErrors() {
        boolean hasFieldErrors = fieldErrors != null && !fieldErrors.isEmpty();
        boolean hasGlobalErrors = globalErrors != null && !globalErrors.isEmpty();
        return hasFieldErrors || hasGlobalErrors;
    }
    
    /**
     * Gets the total number of validation errors.
     * 
     * @return Total error count
     */
    public int getErrorCount() {
        int fieldErrorCount = fieldErrors != null ? fieldErrors.size() : 0;
        int globalErrorCount = globalErrors != null ? globalErrors.size() : 0;
        return fieldErrorCount + globalErrorCount;
    }
    
    /**
     * Creates a formatted summary of all validation errors.
     * 
     * @return Summary string
     */
    public String getErrorSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Validation failed with ").append(getErrorCount()).append(" error(s):\n");
        
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            summary.append("Field errors:\n");
            for (int i = 0; i < fieldErrors.size(); i++) {
                FieldError error = fieldErrors.get(i);
                summary.append(String.format("  %d. %s\n", i + 1, error.getFormattedError()));
            }
        }
        
        if (globalErrors != null && !globalErrors.isEmpty()) {
            summary.append("Global errors:\n");
            for (int i = 0; i < globalErrors.size(); i++) {
                String error = globalErrors.get(i);
                summary.append(String.format("  %d. %s\n", i + 1, error));
            }
        }
        
        return summary.toString();
    }
    
    /**
     * Converts this DTO to a TouristResponse for API consistency.
     * 
     * @return TouristResponse with validation error details
     */
    public TouristResponse toTouristResponse() {
        return TouristResponse.validationError(this);
    }
}