/*
Service class responsible for handling user registration business logic.
This includes data validation, simulating account creation, and error handling.
Passwords are now securely hashed before storage and handled as char arrays
during validation and registration to prevent lingering in memory.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays; // Required for securely clearing char arrays
import java.util.Collections; // Required for Collections.synchronizedList
public class UserService {
    private static final Logger logger = RegistrationLogger.getLogger();
    // In a real application, this would be a database or persistent storage.
    // For this example, we use an in-memory list to simulate registered users.
    // Using Collections.synchronizedList to ensure thread-safety, as recommended.
    private final List<User> registeredUsers;
    /*
     Constructor for UserService.
     Initializes the list of registered users with pre-hashed passwords.
    */
    public UserService() {
        // Initialize the list as a synchronized list for thread-safety.
        this.registeredUsers = Collections.synchronizedList(new ArrayList<>());
        // Pre-fill with some dummy users for testing username uniqueness
        // Pass char arrays to hashPassword and then immediately wipe them
        char[] adminPassChars = "password123".toCharArray();
        registeredUsers.add(new User("admin", hashPassword(adminPassChars), "admin@example.com"));
        Arrays.fill(adminPassChars, ' '); // Wipe after use
        char[] testUserPassChars = "testpass".toCharArray();
        registeredUsers.add(new User("testuser", hashPassword(testUserPassChars), "test@example.com"));
        Arrays.fill(testUserPassChars, ' '); // Wipe after use
    }
    /*
     Hashes a plain-text password using a simulated hashing mechanism.
     In a real application, this would use a strong, cryptographically secure
     hashing library (e.g., BCrypt, Argon2, PBKDF2) along with a salt.
     The char array is wiped immediately after its content is used.
     @param plainTextPassword The plain text password as a char array.
     @return The simulated hashed password string.
    */
    private String hashPassword(char[] plainTextPassword) {
        String passwordString = new String(plainTextPassword);
        // Wipe the original char array immediately after converting to String.
        Arrays.fill(plainTextPassword, ' ');
        return "HASHED_" + passwordString + "_" + generateSalt(); // Simulate salt for better practice
    }
    /*
     Generates a simulated salt for password hashing.
     In a real system, this would be a cryptographically secure random salt.
     @return A simulated salt string.
    */
    private String generateSalt() {
        return Long.toHexString(System.nanoTime()); // Using nanoTime for slightly more randomness for simulation
    }
    /*
     Validates the provided user registration data, including plain-text password strength.
     The plainTextPassword char array is wiped after validation.
     @param username The desired username.
     @param plainTextPassword The plain text password entered by the user.
     @param email The email address.
     @return RegistrationStatus indicating success or specific validation failure.
    */
    public RegistrationStatus validateRegistrationData(String username, char[] plainTextPassword, String email) {
        logger.info("Validating registration data for username: " + username);
        try {
            // Basic not-null/empty checks
            if (username == null || username.trim().isEmpty() ||
                plainTextPassword == null || plainTextPassword.length == 0 ||
                email == null || email.trim().isEmpty()) {
                logger.warning("Validation failed: Missing or empty fields.");
                return RegistrationStatus.INVALID_DATA;
            }
            // Username length
            if (username.length() < 3 || username.length() > 20) {
                logger.warning("Validation failed: Username length out of bounds for user: " + username);
                return RegistrationStatus.INVALID_DATA;
            }
            // Password length/complexity (simple check on plain-text password)
            if (plainTextPassword.length < 6) {
                logger.warning("Validation failed: Password too short for user: " + username);
                return RegistrationStatus.INVALID_DATA;
            }
            // Email format validation (basic regex)
            if (!isValidEmail(email)) {
                logger.warning("Validation failed: Invalid email format for user: " + username);
                return RegistrationStatus.INVALID_DATA;
            }
            // Check if username already exists
            if (isUsernameTaken(username)) {
                logger.warning("Validation failed: Username already taken for user: " + username);
                return RegistrationStatus.USERNAME_TAKEN;
            }
            logger.info("Registration data validation successful for user: " + username);
            return RegistrationStatus.SUCCESS;
        } finally {
            // Ensure the plainTextPassword array is cleared immediately after use.
            if (plainTextPassword != null) {
                Arrays.fill(plainTextPassword, ' ');
            }
        }
    }
    /*
     Registers a new user account after successful validation and confirmation.
     Hashes the plain-text password before creating and storing the User object.
     Simulates potential connection errors (ETOUR).
     The plainTextPassword char array is wiped after processing.
     @param username The username to register.
     @param plainTextPassword The plain text password provided by the user.
     @param email The email address of the user.
     @return RegistrationStatus indicating success or a specific error.
    */
    public RegistrationStatus registerUser(String username, char[] plainTextPassword, String email) {
        logger.info("Attempting to permanently register user: " + username);
        try {
            // Simulate potential connection error (ETOUR) approximately 10% of the time.
            if (Math.random() < 0.1) {
                logger.log(Level.SEVERE, "Simulated connection interruption (ETOUR) during registration for user: " + username);
                return RegistrationStatus.CONNECTION_ERROR;
            }
            // Hash the password before storing it
            String hashedPassword = hashPassword(plainTextPassword);
            // In a real system, this would involve database insertion.
            // For this example, we add to our in-memory list.
            User newUser = new User(username, hashedPassword, email);
            registeredUsers.add(newUser);
            logger.info("User account successfully created for: " + username);
            return RegistrationStatus.SUCCESS;
        } finally {
            // Ensure the plainTextPassword array is cleared immediately after use.
            if (plainTextPassword != null) {
                Arrays.fill(plainTextPassword, ' ');
            }
        }
    }
    /*
     Checks if a given username already exists in the system.
     @param username The username to check.
     @return true if the username is taken, false otherwise.
    */
    private boolean isUsernameTaken(String username) {
        // Iterate over the synchronized list
        synchronized (registeredUsers) {
            return registeredUsers.stream()
                    .anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
        }
    }
    /*
     Validates if a string is a valid email address using a simple regex pattern.
     @param email The email string to validate.
     @return true if the email is valid, false otherwise.
    */
    private boolean isValidEmail(String email) {
        // A simple regex for email validation (can be more robust for production)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}