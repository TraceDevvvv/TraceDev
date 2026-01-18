/**
 * User class representing a user in the system.
 * This class holds user attributes such as username, email, role, and admin status.
 */
public class User {
    private String username;
    private String email;
    private String role;
    private boolean isAdmin;
    public User(String username, String email, String role, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.isAdmin = isAdmin;
    }
    // Getters
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    @Override
    public String toString() {
        return String.format("User{username='%s', email='%s', role='%s', isAdmin=%s}", 
                           username, email, role, isAdmin);
    }
}