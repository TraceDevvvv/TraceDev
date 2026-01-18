'''
User model class representing a user in the system.
Contains basic user information.
'''
public class User {
    private String id;
    private String username;
    private String email;
    private String role;
    private String createdDate;
    public User(String id, String username, String email, String role, String createdDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
    }
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    @Override
    public String toString() {
        return username + " (" + email + ")";
    }
}