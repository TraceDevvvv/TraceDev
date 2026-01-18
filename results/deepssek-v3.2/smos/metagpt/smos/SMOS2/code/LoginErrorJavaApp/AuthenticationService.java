import java.util.HashMap;
import java.util.Map;

/**
 * AuthenticationService class that validates user credentials.
 * This class simulates authentication logic with a simple in-memory user database.
 * It validates username and password combinations and handles the LoginError use case
 * by returning false (or throwing LoginErrorException in more advanced implementations)
 * when incorrect authentication data is provided.
 * 
 * The class demonstrates:
 * 1. Simple credential validation against a mock user database
 * 2. Handling of incorrect authentication data (LoginError scenario)
 * 3. Basic security pract (like not storing plain text passwords in real applications)
 * 4. Edge case handling (null inputs, empty strings, etc.)
 */
public class AuthenticationService {
    
    /**
     * Simple in-memory user database for demonstration purposes.
     * In a real application, this would connect to a proper database or external service.
     * Keys: usernames, Values: password hashes (simulated with simple checks)
     * 
     * Note: In a production system, passwords should NEVER be stored in plain text.
     * They should be hashed using strong cryptographic algorithms like bcrypt or Argon2.
     */
    private final Map<String, UserCredentials> userDatabase;
    
    /**
     * Inner class to represent user credentials with additional metadata.
     * This allows for more realistic user data storage including status flags.
     */
    private static class UserCredentials {
        private final String passwordHash;
        private final boolean accountEnabled;
        private final boolean accountLocked;
        private int failedAttempts;
        
        UserCredentials(String password, boolean accountEnabled, boolean accountLocked) {
            // In a real system, we would use proper password hashing
            // For this simulation, we'll use a simple string comparison
            this.passwordHash = simulatePasswordHash(password);
            this.accountEnabled = accountEnabled;
            this.accountLocked = accountLocked;
            this.failedAttempts = 0;
        }
        
        private String simulatePasswordHash(String password) {
            // Simple simulation - in real system use proper hashing like BCrypt
            return "hash_" + password; // NEVER do this in production!
        }
        
        boolean matchesPassword(String inputPassword) {
            return passwordHash.equals(simulatePasswordHash(inputPassword));
        }
        
        void incrementFailedAttempts() {
            failedAttempts++;
        }
        
        void resetFailedAttempts() {
            failedAttempts = 0;
        }
        
        int getFailedAttempts() {
            return failedAttempts;
        }
        
        boolean isAccountEnabled() {
            return accountEnabled;
        }
        
        boolean isAccountLocked() {
            return accountLocked || failedAttempts >= 5; // Lock after 5 failed attempts
        }
    }
    
    /**
     * Constructor initializes the authentication service with a mock user database.
     * Creates sample users for demonstration purposes.
     */
    public AuthenticationService() {
        userDatabase = new HashMap<>();
        initializeMockUsers();
    }
    
    /**
     * Initializes the mock user database with sample users.
     * In a real application, this would load users from a database or external service.
     */
    private void initializeMockUsers() {
        // Add sample users for demonstration
        userDatabase.put("admin", new UserCredentials("password123", true, false));
        userDatabase.put("user1", new UserCredentials("Welcome123", true, false));
        userDatabase.put("user2", new UserCredentials("Test@456", true, false));
        userDatabase.put("disabled_user", new UserCredentials("Pass123", false, false)); // Disabled account
        userDatabase.put("locked_user", new UserCredentials("Locked456", true, true)); // Manually locked
        
        System.out.println("Authentication Service initialized with " + userDatabase.size() + " sample users.");
        System.out.println("Available usernames for testing: admin, user1, user2, disabled_user, locked_user");
        System.out.println("Corresponding passwords: password123, Welcome123, Test@456, Pass123, Locked456");
    }
    
    /**
     * Authenticates a user with the given username and password.
     * This is the main authentication method that implements the business logic
     * for the LoginError use case.
     * 
     * @param username the username to authenticate
     * @param password the password to verify
     * @return true if authentication is successful, false otherwise
     * @throws LoginErrorException if authentication fails with specific error details
     * @throws IllegalArgumentException if username or password is null or empty
     */
    public boolean authenticate(String username, String password) throws LoginErrorException {
        // Validate input parameters
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        username = username.trim();
        password = password.trim();
        
        // Check if user exists in the database
        UserCredentials credentials = userDatabase.get(username);
        
        if (credentials == null) {
            // User doesn't exist - this is part of the LoginError use case
            throw LoginErrorException.invalidCredentials(username);
        }
        
        // Check if account is locked
        if (credentials.isAccountLocked()) {
            throw new LoginErrorException(LoginErrorException.ErrorCode.ACCOUNT_LOCKED, username,
                    new SecurityException("Account locked due to security policy"));
        }
        
        // Check if account is enabled
        if (!credentials.isAccountEnabled()) {
            throw new LoginErrorException(LoginErrorException.ErrorCode.ACCOUNT_DISABLED, username);
        }
        
        // Verify password
        if (credentials.matchesPassword(password)) {
            // Successful authentication - reset failed attempts
            credentials.resetFailedAttempts();
            logAuthenticationSuccess(username);
            return true;
        } else {
            // Failed authentication - increment failed attempts
            credentials.incrementFailedAttempts();
            
            // Log the failed attempt
            logAuthenticationFailure(username, credentials.getFailedAttempts());
            
            // Check if account should be locked after this failed attempt
            if (credentials.isAccountLocked()) {
                throw new LoginErrorException(LoginErrorException.ErrorCode.ACCOUNT_LOCKED, username);
            }
            
            // Throw LoginErrorException for incorrect password
            // This is the core of the LoginError use case
            throw LoginErrorException.invalidCredentials(username);
        }
    }
    
    /**
     * Alternative authentication method that returns boolean instead of throwing exceptions.
     * This might be used in different contexts where exception handling is not desired.
     * 
     * @param username the username to authenticate
     * @param password the password to verify
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticateSilently(String username, String password) {
        try {
            return authenticate(username, password);
        } catch (LoginErrorException e) {
            // Just return false for any login error
            return false;
        } catch (IllegalArgumentException e) {
            // Return false for invalid input
            return false;
        }
    }
    
    /**
     * Checks if a user exists in the system without attempting authentication.
     * 
     * @param username the username to check
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return userDatabase.containsKey(username.trim());
    }
    
    /**
     * Gets the number of failed login attempts for a specific user.
     * 
     * @param username the username to check
     * @return number of failed attempts, or 0 if user doesn't exist
     */
    public int getFailedAttempts(String username) {
        if (username == null || username.trim().isEmpty()) {
            return 0;
        }
        
        UserCredentials credentials = userDatabase.get(username.trim());
        if (credentials != null) {
            return credentials.getFailedAttempts();
        }
        return 0;
    }
    
    /**
     * Resets the failed login attempts counter for a specific user.
     * This could be used by an administrator or after successful password reset.
     * 
     * @param username the username to reset
     * @return true if reset was successful, false if user doesn't exist
     */
    public boolean resetFailedAttempts(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        UserCredentials credentials = userDatabase.get(username.trim());
        if (credentials != null) {
            credentials.resetFailedAttempts();
            return true;
        }
        return false;
    }
    
    /**
     * Simulates unlocking a user account.
     * In a real system, this would involve more complex logic and security checks.
     * 
     * @param username the username to unlock
     * @return true if unlock was successful, false if user doesn't exist
     */
    public boolean unlockAccount(String username) {
        // In a real implementation, this would update the account status in a database
        // For this simulation, we just reset the failed attempts
        return resetFailedAttempts(username);
    }
    
    /**
     * Logs a successful authentication attempt.
     * In a real system, this would write to an audit log.
     * 
     * @param username the username that was successfully authenticated
     */
    private void logAuthenticationSuccess(String username) {
        // In a production system, this would write to a proper audit log
        // For demonstration, we just print to console
        System.out.println("[AUTH LOG] Successful login for user: " + username);
    }
    
    /**
     * Logs a failed authentication attempt.
     * In a real system, this would write to a security audit log.
     * 
     * @param username the username that failed authentication
     * @param attemptNumber the number of consecutive failed attempts
     */
    private void logAuthenticationFailure(String username, int attemptNumber) {
        // In a production system, this would write to a security audit log
        // For demonstration, we just print to console
        System.out.println("[AUTH LOG] Failed login attempt #" + attemptNumber + " for user: " + username);
        
        // Security warning for multiple failed attempts
        if (attemptNumber >= 3) {
            System.out.println("[SECURITY WARNING] Multiple failed login attempts for user: " + username);
        }
    }
    
    /**
     * Demonstrates the LoginError use case by showing how authentication fails
     * with incorrect credentials. This is a helper method for demonstration purposes.
     */
    public void demonstrateLoginError() {
        System.out.println("\n=== Demonstrating LoginError Use Case ===");
        System.out.println("Scenario 1: Correct credentials");
        try {
            boolean result = authenticate("admin", "password123");
            System.out.println("Authentication result: " + result + " (expected: true)");
        } catch (LoginErrorException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
        
        System.out.println("\nScenario 2: Incorrect password (LoginError use case)");
        try {
            boolean result = authenticate("admin", "wrongpassword");
            System.out.println("Authentication result: " + result + " (should not reach here)");
        } catch (LoginErrorException e) {
            System.out.println("LoginErrorException caught: " + e.getUserFriendlyMessage());
            System.out.println("This demonstrates the LoginError use case - incorrect authentication data");
        }
        
        System.out.println("\nScenario 3: Non-existent user");
        try {
            boolean result = authenticate("nonexistent", "password");
            System.out.println("Authentication result: " + result + " (should not reach here)");
        } catch (LoginErrorException e) {
            System.out.println("LoginErrorException caught: " + e.getUserFriendlyMessage());
        }
        
        System.out.println("\n=== End of LoginError Demonstration ===");
    }
    
    /**
     * Returns the number of users in the mock database.
     * 
     * @return the count of users
     */
    public int getUserCount() {
        return userDatabase.size();
    }
    
    /**
     * Main method for standalone testing of the AuthenticationService.
     * Not used in the main application, but useful for debugging and testing.
     */
    public static void main(String[] args) {
        System.out.println("=== AuthenticationService Self-Test ===");
        AuthenticationService authService = new AuthenticationService();
        
        // Test various scenarios
        authService.demonstrateLoginError();
        
        // Test edge cases
        System.out.println("\n=== Testing Edge Cases ===");
        try {
            authService.authenticate(null, "password");
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception for null username: " + e.getMessage());
        }
        
        try {
            authService.authenticate("admin", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected exception for null password: " + e.getMessage());
        }
        
        System.out.println("\n=== Self-Test Complete ===");
    }
}