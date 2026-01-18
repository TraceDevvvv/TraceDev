package com.system.entities;

/**
 * AgencyOperator is a specialized User (generalization relationship).
 */
public class AgencyOperator extends User {
    private String employeeId;

    public AgencyOperator(String id, String username, String role, String employeeId) {
        super(id, username, role);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getId() {
        return super.getId();
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getRole() {
        return super.getRole();
    }
}