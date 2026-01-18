package com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Role with a set of permissions.
 */
public class Role {
    public String id;
    public String name;
    public List<String> permissions;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
        this.permissions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return new ArrayList<>(permissions);
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission.name());
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission.name());
    }
}