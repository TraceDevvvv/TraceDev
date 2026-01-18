package com.newsagency.model;

import java.util.Map;

/**
 * Represents an Agency Operator actor who interacts with the system.
 * This class satisfies the requirement for an actor with login capability.
 */
public class AgencyOperator {
    private String operatorId;
    private String name;
    private boolean loggedIn = false;
    
    public AgencyOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }
    
    public String getOperatorId() {
        return operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Logs in the agency operator by authenticating against the Operator system.
     * @return true if login successful, false otherwise
     */
    public boolean login() {
        // In a real implementation, credentials would be collected from UI
        // For simplicity, we assume login is successful if operatorId is not empty
        if (operatorId != null && !operatorId.trim().isEmpty()) {
            // Create credentials map as expected by Operator.authenticate()
            java.util.Map<String, String> credentials = new java.util.HashMap<>();
            credentials.put("operatorId", operatorId);
            
            // Authenticate through Operator system
            Operator operator = new Operator();
            boolean authResult = operator.authenticate(credentials);
            
            loggedIn = authResult;
            return authResult;
        }
        return false;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    /**
     * Fills form data as part of the sequence diagram interaction.
     * This method simulates the operator providing data to the form.
     */
    public Map<String, Object> fillForm() {
        java.util.Map<String, Object> formData = new java.util.HashMap<>();
        formData.put("title", "Breaking News");
        formData.put("content", "This is the content of the news article.");
        formData.put("author", "John Reporter");
        formData.put("category", "Politics");
        return formData;
    }
    
    /**
     * Simulates operator confirming an operation in the confirmation dialog.
     */
    public boolean confirmOperation() {
        return true; // Operator confirms
    }
    
    /**
     * Simulates operator cancelling an operation.
     */
    public boolean cancelOperation() {
        return true; // Operator cancels
    }
}