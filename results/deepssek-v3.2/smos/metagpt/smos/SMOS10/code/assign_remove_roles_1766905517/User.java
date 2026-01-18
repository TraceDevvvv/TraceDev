import java.util.HashSet;
import java.util.Set;

/**
 * User class representing a user in the system.
 * Each user has an ID, username, and a set of roles assigned to them.
 */
public class User {
    private String userId;
    private String username;
    private Set<Role> roles;
    
    /**
     * Default constructor.
     */
    public User() {
        this.userId = "";
        this.username = "";
        this.roles = new HashSet<>();
    }
    
    /**
     * Constructor with parameters.
     * 
     * @param userId Unique identifier for the user
     * @param username Username of the user
     */
    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.roles = new HashSet<>();
    }
    
    /**
     * Constructor with parameters including initial roles.
     * 
     * @param userId Unique identifier for the user
     * @param username Username of the user
     * @param roles Initial set of roles for the user
     */
    public User(String userId, String username, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.roles = new HashSet<>(roles);
    }
    
    /**
     * Get the user ID.
     * 
     * @return User ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Set the user ID.
     * 
     * @param userId New user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * Get the username.
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Set the username.
     * 
     * @param username New username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Get the set of roles assigned to the user.
     * Returns a copy to prevent external modification.
     * 
     * @return Set of roles assigned to the user
     */
    public Set<Role> getRoles() {
        return new HashSet<>(roles);
    }
    
    /**
     * Assign a role to the user.
     * If the role is already assigned, no action is taken.
     * 
     * @param role Role to assign
     * @return true if role was added (not already present), false otherwise
     */
    public boolean assignRole(Role role) {
        if (role == null) {
            System.err.println("Cannot assign null role to user: " + username);
            return false;
        }
        return roles.add(role);
    }
    
    /**
     * Remove a role from the user.
     * If the role is not assigned, no action is taken.
     * 
     * @param role Role to remove
     * @return true if role was removed (was present), false otherwise
     */
    public boolean removeRole(Role role) {
        if (role == null) {
            System.err.println("Cannot remove null role from user: " + username);
            return false;
        }
        return roles.remove(role);
    }
    
    /**
     * Check if the user has a specific role.
     * 
     * @param role Role to check
     * @return true if user has the role, false otherwise
     */
    public boolean hasRole(Role role) {
        if (role == null) {
            return false;
        }
        return roles.contains(role);
    }
    
    /**
     * Check if the user has a role with the given ID.
     * 
     * @param roleId Role ID to check
     * @return true if user has a role with the given ID, false otherwise
     */
    public boolean hasRoleById(String roleId) {
        if (roleId == null || roleId.trim().isEmpty()) {
            return false;
        }
        
        for (Role role : roles) {
            if (roleId.equals(role.getRoleId())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get the number of roles assigned to the user.
     * 
     * @return Number of roles
     */
    public int getRoleCount() {
        return roles.size();
    }
    
    /**
     * Clear all roles from the user.
     */
    public void clearAllRoles() {
        roles.clear();
    }
    
    /**
     * Assign multiple roles to the user.
     * 
     * @param rolesToAssign Set of roles to assign
     * @return Number of roles successfully added (excluding duplicates)
     */
    public int assignRoles(Set<Role> rolesToAssign) {
        if (rolesToAssign == null || rolesToAssign.isEmpty()) {
            return 0;
        }
        
        int addedCount = 0;
        for (Role role : rolesToAssign) {
            if (role != null && roles.add(role)) {
                addedCount++;
            }
        }
        return addedCount;
    }
    
    /**
     * Remove multiple roles from the user.
     * 
     * @param rolesToRemove Set of roles to remove
     * @return Number of roles successfully removed
     */
    public int removeRoles(Set<Role> rolesToRemove) {
        if (rolesToRemove == null || rolesToRemove.isEmpty()) {
            return 0;
        }
        
        int removedCount = 0;
        for (Role role : rolesToRemove) {
            if (role != null && roles.remove(role)) {
                removedCount++;
            }
        }
        return removedCount;
    }
    
    /**
     * String representation of the user.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
    
    /**
     * Compare users for equality based on user ID.
     * 
     * @param obj Object to compare
     * @return true if users have the same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId.equals(user.userId);
    }
    
    /**
     * Generate hash code based on user ID.
     * 
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}