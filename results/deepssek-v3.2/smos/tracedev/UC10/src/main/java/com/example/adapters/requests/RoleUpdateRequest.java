package com.example.adapters.requests;

import java.util.List;

/**
 * Request object for role updates.
 */
public class RoleUpdateRequest {
    private String userId;
    private List<String> rolesToAssign;
    private List<String> rolesToRemove;

    public RoleUpdateRequest() {}

    public RoleUpdateRequest(String userId, List<String> rolesToAssign, List<String> rolesToRemove) {
        this.userId = userId;
        this.rolesToAssign = rolesToAssign;
        this.rolesToRemove = rolesToRemove;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRolesToAssign() {
        return rolesToAssign;
    }

    public void setRolesToAssign(List<String> rolesToAssign) {
        this.rolesToAssign = rolesToAssign;
    }

    public List<String> getRolesToRemove() {
        return rolesToRemove;
    }

    public void setRolesToRemove(List<String> rolesToRemove) {
        this.rolesToRemove = rolesToRemove;
    }
}