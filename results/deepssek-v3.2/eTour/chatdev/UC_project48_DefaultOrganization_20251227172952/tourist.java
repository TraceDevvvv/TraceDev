/**
 * Represents an authenticated tourist user.
 * In a real system, this would be populated after successful authentication.
 */
package modifygenericpreference;
public class Tourist {
    private String username;
    private String fullName;
    private String email;
    public Tourist(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
}