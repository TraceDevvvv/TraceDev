package com.example.dto;

/**
 * Data Transfer Object for banner insertion requests.
 */
public class BannerInsertionRequest {
    private String pointId;
    private byte[] imageData;
    private String operatorId;

    public BannerInsertionRequest(String pointId, byte[] imageData, String operatorId) {
        this.pointId = pointId;
        this.imageData = imageData;
        this.operatorId = operatorId;
    }

    public String getPointId() {
        return pointId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getOperatorId() {
        return operatorId;
    }
}