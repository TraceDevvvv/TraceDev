'''
Represents a user in the system with basic profile information.
Encapsulates user data and provides methods to access it.
'''
public class User {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String hashedPassword; // Storing the hashed password for security.
    /**
     * Constructs a new User object.
     *
     * @param name The user's first name.
     * @param surname The user's last name.
     * @param email The user's email address.
     * @param cell The user's cell phone number.
     * @param login The unique login ID for the user.
     * @param hashedPassword The user's password, already hashed.
     */
    public User(String name, String surname, String email, String cell, String login, String hashedPassword) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.hashedPassword = hashedPassword;
    }
    // --- Getters for user properties ---
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getCell() {
        return cell;
    }
    public String getLogin() {
        return login;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", login='" + login + '\'' +
               '}';
    }
}