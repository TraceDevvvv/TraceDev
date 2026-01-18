package dto;

/**
 * Response DTO for delete banner operation.
 * Includes status, message, and cancellation flag (REQ-013).
 */
public class DeleteBannerResponse {
    private boolean isSuccessful;
    private String message;
    private boolean operationCancelled;

    public DeleteBannerResponse(boolean isSuccessful, String message, boolean operationCancelled) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.operationCancelled = operationCancelled;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOperationCancelled() {
        return operationCancelled;
    }
}