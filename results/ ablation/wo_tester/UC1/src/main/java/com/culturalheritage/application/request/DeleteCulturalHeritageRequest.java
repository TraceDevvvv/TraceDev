package com.culturalheritage.application.request;

/**
 * Request object for deleting a cultural heritage.
 * Contains culturalHeritageId, confirmationToken, operatorId and cancellation state.
 */
public class DeleteCulturalHeritageRequest {
    private String culturalHeritageId;
    private String confirmationToken;
    private String operatorId;
    private boolean isCancelled;

    public DeleteCulturalHeritageRequest(String culturalHeritageId, String confirmationToken, String operatorId) {
        this.culturalHeritageId = culturalHeritageId;
        this.confirmationToken = confirmationToken;
        this.operatorId = operatorId;
        this.isCancelled = false;
    }

    public String getCulturalHeritageId() {
        return culturalHeritageId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}