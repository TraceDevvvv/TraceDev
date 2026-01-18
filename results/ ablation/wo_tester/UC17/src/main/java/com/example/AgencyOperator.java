package com.example;

import java.util.List;

/**
 * Represents an agency operator with permissions.
 */
public class AgencyOperator {
    private String id;
    private String name;
    private List<String> permissions;

    public AgencyOperator(String id, String name, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}