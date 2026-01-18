import java.util.Objects;

/**
 * Role class representing a role in the system.
 * Each role has an ID and a name.
 */
public class Role {
    private String roleId;
    private String roleName;
    
    /**
     * Default constructor.
     */
    public Role() {
        this.roleId = "";
        this.roleName = "";
    }
    
    /**
     * Constructor with parameters.
     * 
     * @param roleId Unique identifier for the role
     * @param roleName Name of the role
     */
    public Role(String roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    
    /**
     * Get the role ID.
     * 
     * @return Role ID
     */
    public String getRoleId() {
        return roleId;
    }
    
    /**
     * Set the role ID.
     * 
     * @param roleId New role ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    /**
     * Get the role name.
     * 
     * @return Role name
     */
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * Set the role name.
     * 
     * @param roleName New role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    /**
     * Compare roles for equality based on role ID.
     * 
     * @param obj Object to compare
     * @return true if roles have the same ID, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role = (Role) obj;
        return Objects.equals(roleId, role.roleId);
    }
    
    /**
     * Generate hash code based on role ID.
     * 
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
    
    /**
     * String representation of the role.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "Role{" +
                "roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}