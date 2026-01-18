package com.example.ports;

import com.example.domain.Role;

/**
 * Port for role repository operations.
 */
public interface RoleRepository {
    Role findById(String id);
}