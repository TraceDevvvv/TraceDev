package com.example.model;

/**
 * Represents an operator in the system.
 */
public class Operator {
    private String operatorId;
    private String name;
    private String role;
    
    /**
     * Default constructor.
     */
    public Operator() {
    }
    
    /**
     * Constructor with all fields.
     * @param operatorId The operator ID.
     * @param name The operator name.
     * @param role The operator role.
     */
    public Operator(String operatorId, String name, String role) {
        this.operatorId = operatorId;
        this.name = name;
        this.role = role;
    }
    
    /**
     * Gets the operator ID.
     * @return The operator ID.
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * Gets the operator name.
     * @return The operator name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the operator role.
     * @return The operator role.
     */
    public String getRole() {
        return role;
    }
}