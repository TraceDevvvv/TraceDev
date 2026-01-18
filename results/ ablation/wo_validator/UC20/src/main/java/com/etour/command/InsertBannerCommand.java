package com.etour.command;

import java.io.File;

/**
 * InsertBannerCommand - command object containing data for inserting a banner.
 */
public class InsertBannerCommand {
    private String restPointId;
    private File imageFile;
    private String operatorId;

    public InsertBannerCommand(String restPointId, File imageFile, String operatorId) {
        this.restPointId = restPointId;
        this.imageFile = imageFile;
        this.operatorId = operatorId;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public File getImageFile() {
        return imageFile;
    }

    public String getOperatorId() {
        return operatorId;
    }
}