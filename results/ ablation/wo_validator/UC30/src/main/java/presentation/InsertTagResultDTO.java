package presentation;

/**
 * Result DTO for tag insertion.
 */
public class InsertTagResultDTO {
    private boolean success;
    private String message;
    private String tagId;
    private String errorCode;

    public InsertTagResultDTO() {}

    public InsertTagResultDTO(boolean success, String message, String tagId, String errorCode) {
        this.success = success;
        this.message = message;
        this.tagId = tagId;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}