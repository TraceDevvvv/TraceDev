package com.banner.handler;

import com.banner.dto.InsertBannerResponse;

/**
 * Handles different error scenarios as per quality requirements.
 */
public class ErrorHandler {
    public ErrorHandler() {}

    public InsertBannerResponse handleImageValidationError() {
        return new InsertBannerResponse(false, null, "Image validation failed.");
    }

    public InsertBannerResponse handleMaxBannersExceededError() {
        return new InsertBannerResponse(false, null, "Maximum number of banners exceeded for this restaurant.");
    }

    public InsertBannerResponse handleServerConnectionError() {
        return new InsertBannerResponse(false, null, "Server connection error.");
    }

    public InsertBannerResponse handleCancelledOperation() {
        return new InsertBannerResponse(false, null, "Operation cancelled by user.");
    }

    public InsertBannerResponse handleAuthenticationError() {
        return new InsertBannerResponse(false, null, "Authentication failed.");
    }

    public InsertBannerResponse handleGenericError(Exception exception) {
        return new InsertBannerResponse(false, null, "Error: " + exception.getMessage());
    }

    // New method to handle sequence diagram return message m26, m30, m41
    public InsertBannerResponse errorResponse() {
        return new InsertBannerResponse(false, null, "Generic error response from handler.");
    }
}