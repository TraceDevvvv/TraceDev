package com.example.infrastructure.persistence;

import com.example.domain.Role;
import com.example.ports.RoleRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of RoleRepository (stub for JPA).
 */
public class JpaRoleRepository implements RoleRepository {
    private Map<String, Role> roles = new HashMap<>();

    @Override
    public Role findById(String id) {
        return roles.get(id);
    }

    // Helper method for testing
    public void addRole(Role role) {
        roles.put(role.getId(), role);
    }
}