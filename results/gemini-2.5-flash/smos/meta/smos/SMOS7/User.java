/**
 * Represents a user with personal and login details.
 * This class stores information such as name, surname, email, cell number, login username, and password.
 * In a real-world application, passwords should always be hashed and never stored or transmitted in plain text.
 * For the purpose of this use case, which explicitly states "Password" should be displayed, it is included as a String.
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String cell; // Represents the cell phone number
    private String login; // Represents the username for login
    private String password; // Represents the user's password (plain text for this use case)

    /**
     * Constructs a new User object with the specified details.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell phone number of the user.
     * @param login The login username of the user.
     * @param password The password of the user.
     */
    public User(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
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
     * Returns the last name of the user.
     * @return The user's last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the email address of the user.
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the cell phone number of the user.
     * @return The user's cell phone number.
     */
    public String getCell() {
        return cell;
    }

    /**
     * Returns the login username of the user.
     * @return The user's login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns the password of the user.
     * WARNING: In a real application, this method should not expose the plain text password.
     * Passwords should be hashed and never directly retrievable.
     * @return The user's password (plain text for this specific use case).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a string representation of the User object, displaying all its details.
     * This method is useful for debugging and for fulfilling the use case requirement
     * to display detailed user information.
     *
     * @return A formatted string containing all user details.
     */
    @Override
    public String toString() {
        return "User Details:\n" +
               "  Name: " + name + "\n" +
               "  Surname: " + surname + "\n" +
               "  E-mail: " + email + "\n" +
               "  Cell: " + cell + "\n" +
               "  Login: " + login + "\n" +
               "  Password: " + password; // As per use case, password is displayed
    }
}