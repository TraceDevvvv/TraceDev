'''
Provides methods for validating user input data.
This service encapsulates all validation rules for user registration fields.
It includes checks for empty fields, email format, cell number format,
password matching, and delegates login uniqueness checking to UserManager.
'''
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class UserValidationService {
    // Regex for basic email validation (can be more robust depending on requirements)
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    // Regex for basic cell number validation (e.g., 10 digits, can be adjusted)
    private static final String CELL_REGEX = "^[0-9]{10,15}$"; // Assuming 10-15 digits
    private static final Pattern CELL_PATTERN = Pattern.compile(CELL_REGEX);
    private final UserManager userManager; // Dependency to check login uniqueness
    /**
     * Constructs a new UserValidationService.
     *
     * @param userManager An instance of UserManager to check for login uniqueness.
     */
    public UserValidationService(UserManager userManager) {
        this.userManager = userManager;
    }
    /**
     * Validates all fields required for creating a new user.
     *
     * @param name The user's first name.
     * @param surname The user's last name.
     * @param email The user's email address.
     * @param cell The user's cell phone number.
     * @param login The unique login ID for the user.
     * @param password The user's password (plaintext for validation).
     * @param confirmPassword The re-entered password for confirmation (plaintext for validation).
     * @return A String containing an error message if validation fails, or null if all data is valid.
     */
    public String validate(String name, String surname, String email, String cell,
                           String login, String password, String confirmPassword) {
        // 1. Check for empty fields
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        if (surname == null || surname.trim().isEmpty()) {
            return "Surname cannot be empty.";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }
        if (cell == null || cell.trim().isEmpty()) {
            return "Cell number cannot be empty.";
        }
        if (login == null || login.trim().isEmpty()) {
            return "Login cannot be empty.";
        }
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }
        if (confirmPassword == null || confirmPassword.isEmpty()) {
            return "Confirm Password cannot be empty.";
        }
        // 2. Validate Email format
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email.trim());
        if (!emailMatcher.matches()) {
            return "Invalid email address format.";
        }
        // 3. Validate Cell number format
        Matcher cellMatcher = CELL_PATTERN.matcher(cell.trim());
        if (!cellMatcher.matches()) {
            return "Invalid cell number format. Must be 10-15 digits.";
        }
        // 4. Validate Password match
        if (!password.equals(confirmPassword)) {
            return "Password and Confirm Password do not match.";
        }
        // 5. Validate Password strength (simple length check)
        if (password.length() < 6) {
            return "Password must be at least 6 characters long.";
        }
        // 6. Check for Login uniqueness (delegated to UserManager)
        if (userManager.isLoginTaken(login.trim())) {
            return "Login ID '" + login + "' is already taken. Please choose another.";
        }
        // If all checks pass
        return null;
    }
}