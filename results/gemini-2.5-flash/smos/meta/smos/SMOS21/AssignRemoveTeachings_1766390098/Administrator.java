// Administrator.java
package com.example.teachingsystem;

/**
 * Represents an Administrator user in the system.
 * Administrators have the necessary permissions to assign and remove teachings from addresses.
 */
public class Administrator {
    private String administratorId;
    private String username;

    /**
     * Constructs a new Administrator instance.
     *
     * @param administratorId A unique identifier for the administrator.
     * @param username The username of the administrator.
     */
    public Administrator(String administratorId, String username) {
        if (administratorId == null || administratorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator ID cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.administratorId = administratorId;
        this.username = username;
    }

    /**
     * Returns the unique identifier of the administrator.
     *
     * @return The administrator's ID.
     */
    public String getAdministratorId() {
        return administratorId;
    }

    /**
     * Returns the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username The new username for the administrator.
     */
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    /**
     * Provides a string representation of the Administrator object.
     *
     * @return A string containing the administrator's ID and username.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "administratorId='" + administratorId + '\'' +
               ", username='" + username + '\'' +
               '}';
    }

    /**
     * Checks if this Administrator object is equal to another object.
     * Equality is based on the administratorId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return administratorId.equals(that.administratorId);
    }

    /**
     * Returns a hash code value for the Administrator object.
     * The hash code is based on the administratorId.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return administratorId.hashCode();
    }
}