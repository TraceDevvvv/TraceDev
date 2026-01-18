package com.example.dtos;

import com.example.domain.Role;
import java.util.List;

/**
 * Data transfer object for role details.
 */
public class RoleDetails {
    private Role role;
    private List<String> permissions;

    public RoleDetails(Role role, List<String> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public Role getRole() {
        return role;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}