package com.smos.management.model;

import java.util.Objects;

/**
 * Represents a Role in the system.
 * This class models the 'Role' entity from the Class Diagram.
 */
public class Role {
    private String roleId;
    private String roleName;

    /**
     * Constructs a new Role with the specified ID and name.
     *
     * @param roleId the unique identifier for the role.
     * @param roleName the name of the role.
     */
    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * Returns the unique identifier of the role.
     *
     * @return the role ID.
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Returns the name of the role.
     *
     * @return the role name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Provides a string representation of the Role object.
     *
     * @return a string containing the role ID and name.
     */
    @Override
    public String toString() {
        return "Role{id='" + roleId + "', name='" + roleName + "'}";
    }

    /**
     * Compares this Role object with another object for equality.
     * Two roles are considered equal if they have the same roleId.
     *
     * @param o the object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the roleId.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}