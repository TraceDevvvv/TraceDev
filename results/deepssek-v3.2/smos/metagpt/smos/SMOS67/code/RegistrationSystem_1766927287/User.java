import java.util.Objects;

/**
 * User class representing a student with registration details.
 * This class encapsulates all the user information required for registration
 * as specified in the RegistrationAtSite use case.
 */
public class User {
    // User attributes as per the use case requirements
    private String name;
    private String surname;
    private String mobilePhone;
    private String email;
    private String username;
    private String password;
    private String confirmationPassword; // For verification during registration
    
    /**
     * Default constructor
     */
    public User() {
        // Initialize with empty values
    }
    
    /**
     * Constructor with all user details
     * 
     * @param name the user's first name
     * @param surname the user's last name
     * @param mobilePhone the user's mobile phone number
     * @param email the user's email address
     * @param username the chosen username
     * @param password the chosen password
     * @param confirmationPassword password confirmation for verification
     */
    public User(String name, String surname, String mobilePhone, String email, 
                String username, String password, String confirmationPassword) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }
    
    // Getters and Setters for all fields
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getMobilePhone() {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
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
    
    public String getConfirmationPassword() {
        return confirmationPassword;
    }
    
    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }
    
    /**
     * Checks if the password and confirmation password match
     * 
     * @return true if passwords match, false otherwise
     */
    public boolean doPasswordsMatch() {
        if (password == null || confirmationPassword == null) {
            return false;
        }
        return password.equals(confirmationPassword);
    }
    
    /**
     * Returns a formatted string containing user information
     * (excluding passwords for security)
     * 
     * @return formatted user information
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
    
    /**
     * Compares two User objects for equality based on email and username
     * 
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && 
               Objects.equals(username, user.username);
    }
    
    /**
     * Generates hash code based on email and username
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }
    
    /**
     * Validates that all required fields are not empty or null
     * 
     * @return true if all required fields have values, false otherwise
     */
    public boolean hasRequiredFields() {
        return name != null && !name.trim().isEmpty() &&
               surname != null && !surname.trim().isEmpty() &&
               mobilePhone != null && !mobilePhone.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty() &&
               confirmationPassword != null && !confirmationPassword.trim().isEmpty();
    }
    
    /**
     * Creates a copy of this user object (shallow copy)
     * 
     * @return a new User object with the same values
     */
    public User copy() {
        return new User(name, surname, mobilePhone, email, username, password, confirmationPassword);
    }
}