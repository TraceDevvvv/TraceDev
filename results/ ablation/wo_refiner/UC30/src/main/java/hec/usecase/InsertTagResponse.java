package hec.usecase;

/**
 * Response DTO for insert tag operation.
 * Indicates success, includes tag ID and a message.
 */
public class InsertTagResponse {
    private final boolean success;
    private final String tagId;
    private final String message;

    /**
     * Constructor.
     *
     * @param success true if operation succeeded
     * @param tagId   the ID of the created tag (null if failed)
     * @param message a descriptive message
     */
    public InsertTagResponse(boolean success, String tagId, String message) {
        this.success = success;
        this.tagId = tagId;
        this.message = message;
    }

    /**
     * Checks if the operation succeeded.
     *
     * @return true if success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the tag ID.
     *
     * @return the tag ID
     */
    public String getTagId() {
        return tagId;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}