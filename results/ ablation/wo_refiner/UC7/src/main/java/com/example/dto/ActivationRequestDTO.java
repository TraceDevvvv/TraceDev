package com.example.dto;

/**
 * Data Transfer Object for activation requests.
 */
public class ActivationRequestDTO {
    private String requestId;
    private String operatorId;
    
    public ActivationRequestDTO() {}
    
    public ActivationRequestDTO(String requestId, String operatorId) {
        this.requestId = requestId;
        this.operatorId = operatorId;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public String getOperatorId() {
        return operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}