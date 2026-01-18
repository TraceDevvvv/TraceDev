package com.example.dto;

import com.example.model.Convention;
import java.util.List;

/**
 * Response DTO for viewing convention history.
 * Updated to satisfy requirement REQ-008.
 */
public class ViewConventionHistoryResponse {
    private List<Convention> conventions;
    private boolean success;
    private String errorMessage;

    public ViewConventionHistoryResponse(boolean success, List<Convention> conventions) {
        this.success = success;
        this.conventions = conventions;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public void setConventions(List<Convention> conventions) {
        this.conventions = conventions;
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
}