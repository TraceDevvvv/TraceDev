package application;

/**
 * Data Transfer Object (DTO) for responding to banner insertion requests.
 * Carries information about the success or failure of the operation,
 * a message, and whether further confirmation is required.
 */
public class BannerInsertionResponseDTO {
    private boolean success;
    private String message;
    private String bannerId;
    private boolean confirmationRequired;

    /**
     * Constructs a new BannerInsertionResponseDTO.
     *
     * @param success True if the operation was successful, false otherwise.
     * @param message A descriptive message about the operation's outcome.
     * @param bannerId The ID of the banner involved in the operation (can be null if not applicable).
     * @param confirmationRequired True if the user needs to confirm the action, false otherwise.
     */
    public BannerInsertionResponseDTO(boolean success, String message, String bannerId, boolean confirmationRequired) {
        this.success = success;
        this.message = message;
        this.bannerId = bannerId;
        this.confirmationRequired = confirmationRequired;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getBannerId() {
        return bannerId;
    }

    public boolean isConfirmationRequired() {
        return confirmationRequired;
    }

    @Override
    public String toString() {
        return "BannerInsertionResponseDTO{" +
               "success=" + success +
               ", message='" + message + '\'' +
               ", bannerId='" + bannerId + '\'' +
               ", confirmationRequired=" + confirmationRequired +
               '}';
    }
}