import java.time.LocalDateTime;

/**
 * User class representing a Guest User in the registration system.
 * This class contains user attributes like username, email, password, etc.
 */
public class User {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private LocalDateTime registrationDate;
    private boolean isGuest;
    
    /**
     * Constructor for creating a new User instance.
     * 
     * @param username the username for the user
     * @param email the email address
     * @param password the password (should be encrypted in production)
     * @param fullName the full name of the user
     */
    public User(String username, String email, String password, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.registrationDate = LocalDateTime.now();
        this.isGuest = true; // Initially all users are guests until registered
    }
    
    /**
     * Default constructor for Guest User.
     */
    public User() {
        this.username = "Guest";
        this.email = "";
        this.password = "";
        this.fullName = "Guest User";
        this.registrationDate = LocalDateTime.now();
        this.isGuest = true;
    }
    
    /**
     * Get the username of the user.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Set the username of the user.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Get the email address of the user.
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Set the email address of the user.
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Get the password of the user.
     * Note: In production, passwords should not be retrievable in plain text.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Set the password of the user.
     * Note: In production, passwords should be hashed before storing.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Get the full name of the user.
     * 
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Set the full name of the user.
     * 
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    /**
     * Get the registration date and time.
     * 
     * @return the registration date
     */
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    /**
     * Check if the user is a guest.
     * 
     * @return true if the user is a guest, false otherwise
     */
    public boolean isGuest() {
        return isGuest;
    }
    
    /**
     * Set the guest status of the user.
     * After successful registration, the user should no longer be a guest.
     * 
     * @param isGuest true if the user is a guest, false otherwise
     */
    public void setGuest(boolean isGuest) {
        this.isGuest = isGuest;
    }
    
    /**
     * Convert the user to a string representation.
     * Excludes password for security reasons.
     * 
     * @return string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", registrationDate=" + registrationDate +
                ", isGuest=" + isGuest +
                '}';
    }
}