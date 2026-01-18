package com.example.banner.application.command;

/**
 * Command object for deleting a banner.
 */
public class DeleteBannerCommand {
    private String bannerId;
    private String pointOfRestId;
    private String operatorId;

    public DeleteBannerCommand(String bannerId, String pointOfRestId, String operatorId) {
        this.bannerId = bannerId;
        this.pointOfRestId = pointOfRestId;
        this.operatorId = operatorId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public String getOperatorId() {
        return operatorId;
    }
}