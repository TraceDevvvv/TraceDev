package com.example.application.requestresponse;

import com.example.domain.Convention;
import java.util.List;

/**
 * Response DTO for the view convention history use case.
 */
public class ViewConventionHistoryResponse {

    private final List<Convention> conventions;
    private final boolean success;
    private final String errorMessage;

    public ViewConventionHistoryResponse(List<Convention> conventions, boolean success, String errorMessage) {
        this.conventions = conventions;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public List<Convention> getConventions() {
        return conventions;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}