package com.example;

/**
 * Service for checking user permissions.
 */
public class PermissionService {
    public boolean hasEditingAccess(AgencyOperator user) {
        return user != null && user.hasPermission("edit_banner");
    }
}

=== File: src