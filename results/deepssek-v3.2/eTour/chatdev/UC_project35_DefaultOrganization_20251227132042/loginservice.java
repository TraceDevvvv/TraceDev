'''
Service class for handling login authentication logic.
Separates business logic from presentation layer.
'''
import java.util.Map;
import java.util.regex.Pattern;
public class LoginService {
    private static LoginService instance;
    // Regular expressions for input validation
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{6,}$");
    /**
     * Private constructor for singleton pattern.
     */
    private LoginService() {}
    /**
     * Gets the singleton instance of LoginService.
     * @return The LoginService instance.
     */
    public static synchronized LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }
    /**
     * Authenticates a user with provided credentials.
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @param userDatabase The database of registered users.
     * @return The authenticated User object if successful.
     * @throws LoginException If authentication fails with specific error type.
     */
    public User authenticate(String username, String password, Map<String, User> userDatabase) throws LoginException {
        // Check if userDatabase is null
        if (userDatabase == null) {
            throw new LoginException(LoginErrorType.SERVER_CONNECTION_FAILED,
                "User database not available.");
        }
        // Check for empty fields first
        if (username.isEmpty() || password.isEmpty()) {
            throw new LoginException(LoginErrorType.EMPTY_FIELDS, 
                "Username and password cannot be empty.");
        }
        // Validate input format
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new LoginException(LoginErrorType.INVALID_INPUT_FORMAT,
                "Username must be 3-20 characters long and contain only letters, numbers, and underscores.");
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new LoginException(LoginErrorType.INVALID_INPUT_FORMAT,
                "Password must be at least 6 characters long and contain at least one letter and one number.");
        }
        // Check server connection
        if (!checkServerConnection()) {
            throw new LoginException(LoginErrorType.SERVER_CONNECTION_FAILED,
                "Connection to server interrupted (ETOUR).");
        }
        // Check if user exists
        if (!userDatabase.containsKey(username)) {
            throw new LoginException(LoginErrorType.INVALID_CREDENTIALS,
                "Invalid username or password.");
        }
        User user = userDatabase.get(username);
        // Check if account is locked
        if (user.isLocked()) {
            throw new LoginException(LoginErrorType.ACCOUNT_LOCKED,
                "Account is locked. Please contact administrator.");
        }
        // Verify password
        try {
            if (!user.verifyPassword(password)) {
                throw new LoginException(LoginErrorType.INVALID_CREDENTIALS,
                    "Invalid username or password.");
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            // This should not happen in normal circumstances
            throw new LoginException(LoginErrorType.INVALID_CREDENTIALS,
                "System error: Unable to verify password.");
        }
        // Authentication successful
        return user;
    }
    /**
     * Simulates server connection check.
     * In a real application, this would perform actual network connectivity tests.
     * @return true if server is reachable, false otherwise.
     */
    private synchronized boolean checkServerConnection() {
        // For demo purposes, simulate 95% success rate
        // Using synchronization to make it thread-safe
        return Math.random() > 0.05;
    }
}