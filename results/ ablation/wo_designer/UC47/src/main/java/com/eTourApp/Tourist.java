/**
 * Model class representing a Tourist.
 * Contains all account information that can be updated.
 */
public class Tourist {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;

    /**
     * Constructor for creating a Tourist with all fields.
     * @param username Unique identifier.
     * @param password Authentication password.
     * @param fullName Full name of the tourist.
     * @param email Email address.
     * @param phoneNumber Phone number.
     */
    public Tourist(String username, String password, String fullName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Tourist [username=" + username + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }
}