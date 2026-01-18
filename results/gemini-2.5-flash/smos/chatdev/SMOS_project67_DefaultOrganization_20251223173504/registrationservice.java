'''
    RegistrationService.java
    This class handles the business logic for user registration, including data validation
    and simulating the insertion of the registration request into the system.
    It now handles password hashing internally before creating a User object for storage.
'''
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64; // For Base64 encoding of hash and salt
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class RegistrationService {
    // Simulates a database of existing usernames to check for uniqueness.
    // In a real application, this would query a persistent data store.
    private static final Set<String> existingUsernames = new HashSet<>();
    /**
     * Initializes the RegistrationService.
     * Adds some dummy usernames to simulate pre-existing accounts.
     */
    public RegistrationService() {
        existingUsernames.add("admin");
        existingUsernames.add("tester");
    }
    /**
     * Attempts to register a new user in the system.
     * Performs various validations on the provided user data and plain-text passwords.
     * The password will be hashed internally before it's conceptually stored.
     *
     * @param name              The user's first name.
     * @param surname           The user's last name.
     * @param mobilePhone       The user's mobile phone number.
     * @param email             The user's email address.
     * @param username          The chosen username for the system.
     * @param plainTextPassword The chosen password for the system (in plain text, for validation).
     * @param confirmedPassword The confirmation password entered by the user (in plain text, for validation).
     * @return true if the user is successfully registered after all validations, false otherwise.
     * @throws IllegalArgumentException If any validation fails, providing a descriptive error message.
     */
    public boolean registerUser(String name, String surname, String mobilePhone, String email,
                                String username, String plainTextPassword, String confirmedPassword) throws IllegalArgumentException {
        // 1. Validate all fields are not empty
        if (name.trim().isEmpty() || surname.trim().isEmpty() ||
            mobilePhone.trim().isEmpty() || email.trim().isEmpty() ||
            username.trim().isEmpty() || plainTextPassword.trim().isEmpty() ||
            confirmedPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled out.");
        }
        // 2. Validate password match (using plain text passwords from user input)
        if (!plainTextPassword.equals(confirmedPassword)) {
            throw new IllegalArgumentException("Password and confirmation password do not match.");
        }
        // 3. Validate email format
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        // 4. Validate mobile phone format
        if (!isValidMobile(mobilePhone)) {
            throw new IllegalArgumentException("Invalid mobile phone number. Must be 10-15 digits.");
        }
        // 5. Validate password strength (using plain text password)
        if (!isStrongPassword(plainTextPassword)) {
            // Expanded message to guide the user on password requirements.
            throw new IllegalArgumentException("Password must be at least 8 characters long, include at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#$%^&+=_).");
        }
        // 6. Validate username uniqueness (simulated check against existing usernames)
        if (existingUsernames.contains(username)) {
            throw new IllegalArgumentException("Username '" + username + "' already exists. Please choose a different one.");
        }
        // --- All validations passed. Proceed to process and "store" the user. ---
        // Hash the password *before* creating the User object that will be conceptually stored.
        String hashedPassword = hashPassword(plainTextPassword);
        // Create a User object with the collected data and the hashed password.
        // This 'newUserForStorage' object represents the user data as it would be
        // stored in a database, containing the hashed password.
        User newUserForStorage = new User(name, surname, mobilePhone, email, username, hashedPassword);
        // Simulate the registration process:
        // In a real application, this would involve storing newUserForStorage in a database.
        System.out.println("----------------------------------------------");
        System.out.println("SIMULATING REGISTRATION REQUEST INSERTION:");
        System.out.println("New User Registered:");
        System.out.println("  Name: " + newUserForStorage.getName());
        System.out.println("  Surname: " + newUserForStorage.getSurname());
        System.out.println("  Mobile: " + newUserForStorage.getMobilePhone());
        System.out.println("  Email: " + newUserForStorage.getEmail());
        System.out.println("  Username: " + newUserForStorage.getUsername());
        // For security, never log or display actual passwords or even their hashes directly in production.
        // Here, we indicate that the password is handled securely.
        System.out.println("  Password: [Hashed, HIDDEN]");
        System.out.println("Request inserted into the system.");
        System.out.println("----------------------------------------------");
        // Add the new username to the set of existing usernames to simulate persistence
        existingUsernames.add(newUserForStorage.getUsername());
        return true;
    }
    /**
     * Generates a secure hash of the plain-text password using SHA-256 with a unique salt.
     * This uses standard Java cryptographic APIs for demonstration.
     * For real-world production systems, dedicated password hashing functions like BCrypt or Argon2 are
     * strongly recommended as they inherently include key stretching (a technique to make brute-force
     * attacks harder) which is crucial for password security.
     *
     * @param plainTextPassword The user's plain-text password.
     * @return A string containing the Base64 encoded salt and hash, separated by a colon.
     * @throws IllegalStateException if the SHA-256 algorithm is not available.
     */
    private String hashPassword(String plainTextPassword) {
        try {
            // Generate a random, unique salt for each password
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16]; // 16-byte salt is a good practice (128 bits)
            random.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            // Initialize SHA-256 message digest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add salt to the digest
            md.update(salt);
            // Hash the password combined with the salt
            byte[] hashedPasswordBytes = md.digest(plainTextPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            String encodedHashedPassword = Base64.getEncoder().encodeToString(hashedPasswordBytes);
            // Return the salt and the hash, typically stored together.
            // In a real system, you might store salt and hash in separate database columns.
            return encodedSalt + ":" + encodedHashedPassword;
        } catch (NoSuchAlgorithmException e) {
            // This exception should not happen in a standard Java environment
            throw new IllegalStateException("Error: SHA-256 algorithm not found. " +
                                            "Cannot perform secure password hashing.", e);
        }
    }
    /**
     * Validates the format of an email address using a regular expression.
     *
     * @param email The email string to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        // Simple regex for email validation (can be more complex for stricter adherence)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Validates the format of a mobile phone number.
     * Assumes a simple format of 10 to 15 digits.
     *
     * @param mobile The mobile phone string to validate.
     * @return true if the mobile number format is valid, false otherwise.
     */
    private boolean isValidMobile(String mobile) {
        // Simple regex: 10 to 15 digits
        String mobileRegex = "^[0-9]{10,15}$";
        Pattern pattern = Pattern.compile(mobileRegex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
    /**
     * Validates the strength of a password using a regular expression.
     * Requires at least 8 characters, one digit, one lowercase, one uppercase, and one special character.
     *
     * @param password The password string to validate.
     * @return true if the password meets the strength requirements, false otherwise.
     */
    private boolean isStrongPassword(String password) {
        // Regex for strong password:
        // ^                 # start of line
        // (?=.*[0-9])       # must contain at least one digit
        // (?=.*[a-z])       # must contain at least one lowercase letter
        // (?=.*[A-Z])       # must contain at least one uppercase letter
        // (?=.*[!@#$%^&+=_]) # must contain at least one special character
        // (?=\S+$)          # no whitespace allowed
        // .{8,}             # at least 8 characters long
        // $                 # end of line
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=_])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}