/*
A simple Plain Old Java Object (POJO) representing a User's registration data.
This class now stores the hashed password for security.
*/
public class User {
    private final String username;
    private final String hashedPassword; // Stores the hashed password
    private final String email;
    /*
     Constructor for the User object.
     @param username The username for the account.
     @param hashedPassword The securely hashed password.
     @param email The email address for the account.
    */
    public User(String username, String hashedPassword, String email) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
    }
    // --- Getters ---
    public String getUsername() {
        return username;
    }
    public String getHashedPassword() { // Getter for the hashed password
        return hashedPassword;
    }
    public String getEmail() {
        return email;
    }
}