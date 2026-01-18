package com.example.application.commands;

/**
 * Command object containing data needed to update a banner image.
 */
public class UpdateBannerImageCommand {
    private final String bannerId;
    private final String operatorId;
    private final byte[] newImageData;
    private final String contentType;

    public UpdateBannerImageCommand(String bannerId, String operatorId, byte[] newImageData, String contentType) {
        this.bannerId = bannerId;
        this.operatorId = operatorId;
        this.newImageData = newImageData;
        this.contentType = contentType;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public byte[] getNewImageData() {
        return newImageData;
    }

    public String getContentType() {
        return contentType;
    }
}