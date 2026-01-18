
package com.example.interfaceadapters.controllers;

import com.example.application.DeleteBannerResult;
import com.example.domain.exceptions.ETOURConnectionException;

/**
 * Global exception handler to map exceptions to appropriate HTTP responses.
 */
public class GlobalExceptionHandler {
    
    public Object handleETOURConnectionException(ETOURConnectionException ex) {
        // Handle ETOUR connection exception as per sequence diagram interruption flow
        DeleteBannerResult result = new DeleteBannerResult(false, "Server error: " + ex.getMessage(), null);
        return result;
    }
    
    public Object handleGenericException(Exception ex) {
        DeleteBannerResult result = new DeleteBannerResult(false, "Deletion failed: " + ex.getMessage(), null);
        return result;
    }
}
