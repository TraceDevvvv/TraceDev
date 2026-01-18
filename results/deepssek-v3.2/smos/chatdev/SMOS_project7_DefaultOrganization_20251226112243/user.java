/**
 * Represents a user with personal details.
 * Stores all information required by the use case specification.
 */
public class User {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;
    public User(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }
    // Getters for all fields
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }
    public String getCell() { return cell; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    @Override
    public String toString() {
        // Display user details in format: "Name Surname (Login)"
        return String.format("%s %s (%s)", name, surname, login);
    }
}