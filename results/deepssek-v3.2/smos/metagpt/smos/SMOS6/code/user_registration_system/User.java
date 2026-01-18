import java.util.regex.Pattern;

/**
 * User class representing a user in the system.
 * Contains all fields required for user registration and validation logic.
 */
public class User {
    // User fields as specified in requirements
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;
    
    // Regular expression patterns for validation
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern CELL_PATTERN = 
        Pattern.compile("^\\+?[0-9]{10,15}$"); // Allows optional + and 10-15 digits
    private static final Pattern LOGIN_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{4,20}$"); // Alphanumeric and underscore, 4-20 chars
    
    /**
     * Default constructor
     */
    public User() {
    }
    
    /**
     * Parameterized constructor for creating a user with all fields
     */
    public User(String name, String surname, String email, String cell, 
                String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }
    
    // Getters and setters for all fields
    
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCell() {
        return cell;
    }
    
    public void setCell(String cell) {
        this.cell = cell;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Validates all user fields according to business rules
     * @return true if all fields are valid, false otherwise
     */
    public boolean validate() {
        return isValidName() && isValidSurname() && isValidEmail() && 
               isValidCell() && isValidLogin() && isValidPassword();
    }
    
    /**
     * Validates the name field
     * @return true if name is not null/empty and contains only letters and spaces
     */
    public boolean isValidName() {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // Name should contain only letters and spaces
        return name.matches("^[a-zA-Z\\s]+$");
    }
    
    /**
     * Validates the surname field
     * @return true if surname is not null/empty and contains only letters and spaces
     */
    public boolean isValidSurname() {
        if (surname == null || surname.trim().isEmpty()) {
            return false;
        }
        // Surname should contain only letters and spaces
        return surname.matches("^[a-zA-Z\\s]+$");
    }
    
    /**
     * Validates the email field using regex pattern
     * @return true if email matches the email pattern
     */
    public boolean isValidEmail() {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validates the cell phone number field
     * @return true if cell number matches the pattern (optional +, 10-15 digits)
     */
    public boolean isValidCell() {
        if (cell == null || cell.trim().isEmpty()) {
            return false;
        }
        return CELL_PATTERN.matcher(cell).matches();
    }
    
    /**
     * Validates the login field
     * @return true if login is 4-20 characters, alphanumeric with underscores
     */
    public boolean isValidLogin() {
        if (login == null || login.trim().isEmpty()) {
            return false;
        }
        return LOGIN_PATTERN.matcher(login).matches();
    }
    
    /**
     * Validates the password field
     * @return true if password is at least 8 characters long
     */
    public boolean isValidPassword() {
        if (password == null || password.isEmpty()) {
            return false;
        }
        // Password should be at least 8 characters for security
        return password.length() >= 8;
    }
    
    /**
     * Validates if the provided password matches the user's password
     * @param confirmPassword the password to confirm
     * @return true if passwords match
     */
    public boolean confirmPassword(String confirmPassword) {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }
    
    /**
     * Returns a string representation of the user (excluding password for security)
     * @return formatted string with user details
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cell='" + cell + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
    
    /**
     * Returns detailed validation error messages for debugging
     * @return string containing all validation errors
     */
    public String getValidationErrors() {
        StringBuilder errors = new StringBuilder();
        
        if (!isValidName()) {
            errors.append("Name is invalid. Must contain only letters and spaces.\n");
        }
        if (!isValidSurname()) {
            errors.append("Surname is invalid. Must contain only letters and spaces.\n");
        }
        if (!isValidEmail()) {
            errors.append("Email is invalid. Must be in format: user@domain.com\n");
        }
        if (!isValidCell()) {
            errors.append("Cell phone is invalid. Must be 10-15 digits, optionally starting with +.\n");
        }
        if (!isValidLogin()) {
            errors.append("Login is invalid. Must be 4-20 alphanumeric characters with underscores.\n");
        }
        if (!isValidPassword()) {
            errors.append("Password is invalid. Must be at least 8 characters long.\n");
        }
        
        return errors.toString();
    }
}