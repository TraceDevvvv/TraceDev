package com.example.command;

/**
 * Command object for inserting a banner.
 */
public class InsertBannerCommand {
    private String restPointId;
    private byte[] imageData;
    private String imageType;

    public InsertBannerCommand(String restPointId, byte[] imageData, String imageType) {
        this.restPointId = restPointId;
        this.imageData = imageData;
        this.imageType = imageType;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageType() {
        return imageType;
    }

    /**
     * Validates the command parameters (REQ-009).
     * Sequence diagram step 12.
     */
    public CommandValidationResult validate() {
        if (restPointId == null || restPointId.isEmpty()) {
            return new CommandValidationResult(false, "Rest point ID is required.");
        }
        if (imageData == null || imageData.length == 0) {
            return new CommandValidationResult(false, "Image data is required.");
        }
        if (imageType == null || imageType.isEmpty()) {
            return new CommandValidationResult(false, "Image type is required.");
        }
        return new CommandValidationResult(true, "Command is valid.");
    }
}