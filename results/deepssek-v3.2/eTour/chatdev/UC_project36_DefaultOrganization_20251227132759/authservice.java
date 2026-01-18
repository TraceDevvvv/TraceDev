/**
 * Simple authentication service that validates credentials.
 * For demonstration purposes, uses hardcoded valid credentials.
 * In a real application, this would connect to a database or external service.
 */
public class AuthService {
    // Hardcoded valid credentials for demonstration
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password123";
    /**
     * Authenticates the provided credentials.
     * 
     * @param username The username to validate
     * @param password The password to validate
     * @return true if credentials are valid, false otherwise
     */
    public boolean authenticate(String username, String password) {
        // Simulate some processing time (like network/database access)
        try {
            Thread.sleep(100); // Small delay to simulate processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Basic validation - check against hardcoded credentials
        if (username == null || password == null) {
            return false;
        }
        // Trim and compare (in real application, this would involve hashing)
        return username.trim().equals(VALID_USERNAME) && 
               password.trim().equals(VALID_PASSWORD);
    }
    /**
     * Additional validation methods can be added here
     * For example, password strength validation, username format validation, etc.
     */
    public boolean isUsernameValidFormat(String username) {
        // Basic username format validation
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        // Username should be 3-20 characters, alphanumeric with underscores
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
    public boolean isPasswordValidFormat(String password) {
        // Basic password format validation
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        // Password should be at least 8 characters
        return password.length() >= 8;
    }
}