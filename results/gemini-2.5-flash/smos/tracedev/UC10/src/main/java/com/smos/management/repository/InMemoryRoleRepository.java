package com.smos.management.repository;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An in-memory implementation of IRoleRepository for testing and demonstration purposes.
 * Simulates data storage using a HashMap.
 */
public class InMemoryRoleRepository implements IRoleRepository {
    private final Map<String, Role> roles = new HashMap<>();
    private boolean simulateConnectionError = false;

    public InMemoryRoleRepository() {
        // Populate with some initial roles
        roles.put("R1", new Role("R1", "ROLE_ADMIN"));
        roles.put("R2", new Role("R2", "ROLE_EDITOR"));
        roles.put("R3", new Role("R3", "ROLE_MODERATOR"));
        roles.put("R4", new Role("R4", "ROLE_VIEWER"));
    }

    /**
     * Configures the repository to simulate a connection error for testing purposes.
     *
     * @param simulateConnectionError true to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    @Override
    public List<Role> findAll() throws SMOSConnectionException {
        if (simulateConnectionError) {
            throw new SMOSConnectionException("Simulated connection error during findAll roles.");
        }
        System.out.println("RoleRepo: findAll() called, returning " + roles.size() + " roles.");
        return new ArrayList<>(roles.values());
    }

    @Override
    public Role findById(String roleId) throws SMOSConnectionException {
        if (simulateConnectionError) {
            throw new SMOSConnectionException("Simulated connection error during findById role: " + roleId);
        }
        Role foundRole = roles.get(roleId);
        System.out.println("RoleRepo: findById(" + roleId + ") called, found: " + (foundRole != null));
        return foundRole;
    }
}