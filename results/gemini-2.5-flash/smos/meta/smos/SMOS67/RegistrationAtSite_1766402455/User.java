package RegistrationAtSite_1766402455;

/**
 * Represents a user with registration details.
 * This class holds all the necessary information a user provides during registration.
 */
public class User {
    private String name;
    private String surname;
    private String mobilePhone;
    private String email;
    private String username;
    private String password;

    /**
     * Constructs a new User object with the provided registration details.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param mobilePhone The mobile phone number of the user.
     * @param email The email address of the user.
     * @param username The chosen username for the user.
     * @param password The chosen password for the user.
     */
    public User(String name, String surname, String mobilePhone, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the first name of the user.
     * @return The user's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the user.
     * @param name The new first name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the last name of the user.
     * @return The user's last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the user.
     * @param surname The new last name.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the mobile phone number of the user.
     * @return The user's mobile phone number.
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Sets the mobile phone number of the user.
     * @param mobilePhone The new mobile phone number.
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * Returns the email address of the user.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the username of the user.
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     * Note: In a real application, passwords should never be stored or returned directly.
     * They should be hashed and salted. For this simulation, we return the plain password.
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * Note: In a real application, passwords should never be stored or returned directly.
     * They should be hashed and salted. For this simulation, we set the plain password.
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Provides a string representation of the User object,
     * useful for logging and debugging.
     * @return A string containing the user's details (excluding password for security).
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
}