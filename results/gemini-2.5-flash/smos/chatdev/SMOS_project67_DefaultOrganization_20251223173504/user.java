'''
    User.java
    A plain old Java object (POJO) to represent a user with their registration details.
    This class holds the data collected from the registration form after the password
    has been processed (i.e., hashed). It represents the conceptual 'stored' user.
'''
public class User {
    private final String name;
    private final String surname;
    private final String mobilePhone;
    private final String email;
    private final String username;
    private final String hashedPassword; // Renamed to clearly indicate it's not plain text
    /**
     * Constructs a new User object with the provided registration details where the
     * password has already been hashed.
     *
     * @param name        The user's first name.
     * @param surname     The user's last name.
     * @param mobilePhone The user's mobile phone number.
     * @param email       The user's email address.
     * @param username    The chosen username for the system.
     * @param hashedPassword The *hashed* chosen password for the system.
     */
    public User(String name, String surname, String mobilePhone, String email, String username, String hashedPassword) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword; // Assign the hashed password
    }
    /**
     * Retrieves the user's first name.
     *
     * @return The first name.
     */
    public String getName() {
        return name;
    }
    /**
     * Retrieves the user's last name.
     *
     * @return The last name.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Retrieves the user's mobile phone number.
     *
     * @return The mobile phone number.
     */
    public String getMobilePhone() {
        return mobilePhone;
    }
    /**
     * Retrieves the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Retrieves the chosen username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Retrieves the hashed password.
     * IMPORTANT: In a real application, this hashed password should *never* be
     * revealed or used for direct comparison. It's solely for storage and verification
     * against a newly hashed input password using a dedicated secure comparison function.
     *
     * @return The hashed password.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }
    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", mobilePhone='" + mobilePhone + '\'' +
               ", email='" + email + '\'' +
               ", username='" + username + '\'' +
               // For security, never include the (hashed) password directly in toString()
               // unless specifically for debugging needs where it's handled securely.
               '}';
    }
}