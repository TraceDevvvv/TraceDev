package com.example.dto;

/**
 * Result object returned by position operations.
 * Contains success flag, position data (if successful) and error message (if failed).
 */
public class PositionResult {
    private boolean isSuccess;
    private PositionData positionData;
    private String errorMessage;

    public PositionResult(boolean isSuccess, PositionData positionData, String errorMessage) {
        this.isSuccess = isSuccess;
        this.positionData = positionData;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public PositionData getPositionData() {
        return positionData;
    }

    public void setPositionData(PositionData positionData) {
        this.positionData = positionData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "PositionResult{isSuccess=" + isSuccess + ", positionData=" + positionData + ", errorMessage='" + errorMessage + "'}";
    }
}