/**
 * UserAccount class to hold registration data and perform validation.
 */
public class UserAccount {
    private String username;
    private String password;
    private String email;
    private String phone;
    public UserAccount(String username, String password, String email, String phone) {
        this.username = username == null ? "" : username.trim();
        this.password = password == null ? "" : password.trim();
        this.email = email == null ? "" : email.trim();
        this.phone = phone == null ? "" : phone.trim();
    }
    /**
     * Validates the user-entered data.
     * Returns true if data is valid, false otherwise.
     * Implements step 4 of the use case: Verify the data entered
     */
    public boolean validate() {
        // Username validation
        if (username.isEmpty()) {
            return false;
        }
        // Password validation - at least 6 characters
        if (password.length() < 6) {
            return false;
        }
        // Email validation using regex pattern
        if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return false;
        }
        // Phone is optional; if provided, it should have at least 10 digits and only numbers
        if (!phone.isEmpty() && !phone.matches("^\\d{10,}$")) {
            return false;
        }
        return true;
    }
    // Getters for account information
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}