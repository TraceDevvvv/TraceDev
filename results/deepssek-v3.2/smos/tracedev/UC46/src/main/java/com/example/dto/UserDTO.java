package com.example.dto;

/**
 * Data Transfer Object for User information.
 * Added to satisfy Entry Conditions: logged in state.
 */
public class UserDTO {
    public int id;
    public String username;
    public String role;

    public UserDTO() {}

    public UserDTO(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    /**
     * Checks if the user is authenticated.
     * @return true if the user has a valid id and role.
     */
    public boolean isAuthenticated() {
        return id > 0 && role != null && !role.isEmpty();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}