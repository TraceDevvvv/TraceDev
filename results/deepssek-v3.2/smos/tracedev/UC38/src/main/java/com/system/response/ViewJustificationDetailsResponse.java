package com.system.response;

import java.util.Map;

/**
 * Response object for the View Justification Details use case.
 */
public class ViewJustificationDetailsResponse {
    private Map<String, Object> justificationDetails;
    private boolean canModify;
    private boolean canDelete;
    private boolean success;
    private String errorMessage;

    public ViewJustificationDetailsResponse() {
        // Default constructor
    }

    public ViewJustificationDetailsResponse(Map<String, Object> justificationDetails, boolean canModify, boolean canDelete, boolean success, String errorMessage) {
        this.justificationDetails = justificationDetails;
        this.canModify = canModify;
        this.canDelete = canDelete;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> getJustificationDetails() {
        return justificationDetails;
    }

    public void setJustificationDetails(Map<String, Object> justificationDetails) {
        this.justificationDetails = justificationDetails;
    }

    public boolean isCanModify() {
        return canModify;
    }

    public void setCanModify(boolean canModify) {
        this.canModify = canModify;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ViewJustificationDetailsResponse{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                ", canModify=" + canModify +
                ", canDelete=" + canDelete +
                ", justificationDetails=" + justificationDetails +
                '}';
    }
}