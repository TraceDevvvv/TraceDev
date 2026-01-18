'''
Represents a user in the system with a unique ID, username, and a set of roles.
Provides methods to manage the user's roles.
'''
package model;
import java.util.HashSet;
import java.util.Set;
public class User {
    private String userId;
    private String username;
    private Set<String> roles; // Stores roles as strings
    /**
     * Constructs a new User with a specified ID and username.
     * Roles are initialized as an empty set.
     * @param userId The unique identifier for the user.
     * @param username The username of the user.
     */
    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.roles = new HashSet<>();
    }
    /**
     * Returns the unique ID of the user.
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Returns the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Returns a copy of the set of roles assigned to this user.
     * @return A Set of role strings.
     */
    public Set<String> getRoles() {
        return new HashSet<>(roles); // Return a copy to prevent external modification
    }
    /**
     * Assigns a role to the user.
     * @param role The role to be added.
     * @return true if the role was added, false if the user already had the role.
     */
    public boolean addRole(String role) {
        return roles.add(role);
    }
    /**
     * Removes a role from the user.
     * @param role The role to be removed.
     * @return true if the role was removed, false if the user did not have the role.
     */
    public boolean removeRole(String role) {
        return roles.remove(role);
    }
    /**
     * Checks if the user has a specific role.
     * @param role The role to check.
     * @return true if the user has the role, false otherwise.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
    /**
     * Provides a string representation of the User object.
     * @return A string containing user ID and username.
     */
    @Override
    public String toString() {
        return username + " (" + userId + ")";
    }
    /**
     * Compares this User to another object for equality.
     * Users are considered equal if their user IDs are the same.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId);
    }
    /**
     * Generates a hash code for this User based on its user ID.
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}