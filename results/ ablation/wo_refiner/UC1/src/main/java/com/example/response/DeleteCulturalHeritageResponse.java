package com.example.response;

/**
 * Response object for delete cultural heritage operation.
 * Added errorCode and errorType for REQ-010 and REQ-013.
 */
public class DeleteCulturalHeritageResponse {
    private boolean success;
    private String message;
    private int errorCode;
    private String errorType;

    private DeleteCulturalHeritageResponse(boolean success, String message, int errorCode, String errorType) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    public static DeleteCulturalHeritageResponse success(String message) {
        return new DeleteCulturalHeritageResponse(true, message, 0, null);
    }

    public static DeleteCulturalHeritageResponse failureInvalidToken() {
        return new DeleteCulturalHeritageResponse(false, "Invalid or expired confirmation", 400, "VALIDATION");
    }

    public static DeleteCulturalHeritageResponse failureMultipleSubmissions() {
        return new DeleteCulturalHeritageResponse(false, "Multiple submissions blocked", 429, "THROTTLING");
    }

    public static DeleteCulturalHeritageResponse failureConnection(int errorCode, String errorMessage) {
        return new DeleteCulturalHeritageResponse(false, errorMessage, errorCode, "CONNECTION");
    }

    public static DeleteCulturalHeritageResponse failureNotFound() {
        return new DeleteCulturalHeritageResponse(false, "Cultural heritage not found", 404, "NOT_FOUND");
    }

    public static DeleteCulturalHeritageResponse failureDelete() {
        return new DeleteCulturalHeritageResponse(false, "Delete operation failed", 500, "SERVER");
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorType() {
        return errorType;
    }
}