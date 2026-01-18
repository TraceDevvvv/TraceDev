package RegistrationAtSite_1766402455;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service class responsible for handling user registration logic.
 * This includes validating input data and simulating the insertion of a new user
 * into a system.
 */
public class RegistrationService {

    // A mock database to store registered users. In a real application, this would be a database.
    // Key: username, Value: User object
    private static final Map<String, User> MOCK_DATABASE = new HashMap<>();

    // Regex for basic email validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Regex for basic mobile phone validation (e.g., 10 digits, optional + prefix)
    private static final String MOBILE_PHONE_REGEX = "^\\+?[0-9]{10,15}$";
    private static final Pattern MOBILE_PHONE_PATTERN = Pattern.compile(MOBILE_PHONE_REGEX);

    /**
     * Registers a new user in the system after performing necessary validations.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param mobilePhone The mobile phone number of the user.
     * @param email The email address of the user.
     * @param username The chosen username for the user.
     * @param password The chosen password for the user.
     * @param confirmPassword The confirmation of the chosen password.
     * @return A User object if registration is successful, null otherwise.
     * @throws IllegalArgumentException if any validation fails.
     */
    public User registerUser(String name, String surname, String mobilePhone, String email,
                             String username, String password, String confirmPassword) throws IllegalArgumentException {

        // 1. Validate all fields are not empty
        if (isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (isNullOrEmpty(surname)) {
            throw new IllegalArgumentException("Surname cannot be empty.");
        }
        if (isNullOrEmpty(mobilePhone)) {
            throw new IllegalArgumentException("Mobile phone cannot be empty.");
        }
        if (isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (isNullOrEmpty(username)) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (isNullOrEmpty(password)) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (isNullOrEmpty(confirmPassword)) {
            throw new IllegalArgumentException("Confirmation password cannot be empty.");
        }

        // 2. Validate email format
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        // 3. Validate mobile phone format
        if (!isValidMobilePhone(mobilePhone)) {
            throw new IllegalArgumentException("Invalid mobile phone format. Must be 10-15 digits, optionally starting with '+'.");
        }

        // 4. Validate password and confirmation password match
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirmation password do not match.");
        }

        // 5. Validate username uniqueness (simulate database check)
        if (MOCK_DATABASE.containsKey(username)) {
            throw new IllegalArgumentException("Username '" + username + "' is already taken. Please choose another.");
        }

        // If all validations pass, create a new User object
        User newUser = new User(name, surname, mobilePhone, email, username, password);

        // 6. Simulate inserting the registration request into the system (mock database)
        MOCK_DATABASE.put(username, newUser);
        System.out.println("System: Registration request for user '" + username + "' inserted successfully.");

        // In a real system, you might return a success status or the user ID.
        // For this simulation, we return the User object.
        return newUser;
    }

    /**
     * Helper method to check if a string is null or empty.
     * @param str The string to check.
     * @return true if the string is null or empty, false otherwise.
     */
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Helper method to validate email format using a regular expression.
     * @param email The email string to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * Helper method to validate mobile phone format using a regular expression.
     * @param mobilePhone The mobile phone string to validate.
     * @return true if the mobile phone format is valid, false otherwise.
     */
    private boolean isValidMobilePhone(String mobilePhone) {
        Matcher matcher = MOBILE_PHONE_PATTERN.matcher(mobilePhone);
        return matcher.matches();
    }

    /**
     * For testing purposes: Clears the mock database.
     */
    public static void clearMockDatabase() {
        MOCK_DATABASE.clear();
    }

    /**
     * For testing purposes: Checks if a user exists in the mock database.
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */
    public static boolean userExists(String username) {
        return MOCK_DATABASE.containsKey(username);
    }
}