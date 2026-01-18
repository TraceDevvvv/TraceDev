'''
Manages users and their roles within the system.
Simulates a backend for user management operations.
'''
package model;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class UserManager {
    private Map<String, User> users; // Stores users by their userId
    private Set<String> availableRoles; // Stores all roles that can be assigned
    /**
     * Constructs a new UserManager and initializes internal data structures.
     */
    public UserManager() {
        this.users = new HashMap<>();
        this.availableRoles = new HashSet<>();
    }
    /**
     * Adds an available role to the system.
     * @param roleName The name of the role to add.
     */
    public void addAvailableRole(String roleName) {
        availableRoles.add(roleName);
    }
    /**
     * Returns an unmodifiable set of all available roles in the system.
     * @return A Set of available role names.
     */
    public Set<String> getAvailableRoles() {
        return Collections.unmodifiableSet(availableRoles);
    }
    /**
     * Gets a user by their userId.
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUser(String userId) {
        return users.get(userId);
    }
    /**
     * Creates a new user or returns an existing one if the userId already exists.
     * @param userId The unique ID for the user.
     * @param username The username for the user.
     * @return The newly created User object or an existing one.
     */
    public User getOrCreateUser(String userId, String username) {
        if (!users.containsKey(userId)) {
            users.put(userId, new User(userId, username));
        }
        return users.get(userId);
    }
    /**
     * Returns an unmodifiable collection of all users in the system.
     * @return A Collection of User objects.
     */
    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(users.values());
    }
    /**
     * Assigns a role to a specified user.
     * This operation will only succeed if the user exists and the role is an available role.
     * @param userId The ID of the user.
     * @param role The role to assign.
     * @return true if the role was successfully assigned, false otherwise (e.g., user not found, role not available).
     */
    public boolean assignRole(String userId, String role) {
        User user = users.get(userId);
        if (user != null && availableRoles.contains(role)) {
            return user.addRole(role);
        }
        return false;
    }
    /**
     * Removes a role from a specified user.
     * This operation will only succeed if the user exists.
     * @param userId The ID of the user.
     * @param role The role to remove.
     * @return true if the role was successfully removed, false otherwise (e.g., user not found).
     */
    public boolean removeRole(String userId, String role) {
        User user = users.get(userId);
        if (user != null) {
            return user.removeRole(role);
        }
        return false;
    }
}