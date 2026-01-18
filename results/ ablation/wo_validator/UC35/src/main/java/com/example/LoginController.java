package com.example;

/**
 * Controller that handles login requests, coordinates authentication,
 * and returns the result.
 */
public class LoginController {
    private UserRepository userRepository;
    private AuthenticationStrategy authStrategy;

    /**
     * Constructs a LoginController with dependencies.
     * @param userRepository the user repository
     * @param authStrategy the authentication strategy
     */
    public LoginController(UserRepository userRepository, AuthenticationStrategy authStrategy) {
        this.userRepository = userRepository;
        this.authStrategy = authStrategy;
    }

    /**
     * Handles a login request with given credentials.
     * @param username the username
     * @param password the password
     * @return the AuthenticationResult indicating success or failure
     */
    public AuthenticationResult handleLoginRequest(String username, String password) {
        System.out.println("LoginController: Handling login request for user '" + username + "'");

        // Step 1: Validate credentials (basic validation)
        if (!validateCredentials(username, password)) {
            return new AuthenticationResult(false, null, "Invalid input: username and password must not be empty.");
        }

        // Step 2: Authenticate
        return authenticate(username, password);
    }

    /**
     * Validates that username and password are not empty.
     * @param username the username
     * @param password the password
     * @return true if valid, false otherwise
     */
    private boolean validateCredentials(String username, String password) {
        System.out.println("LoginController: Validating credentials...");
        return username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty();
    }

    /**
     * Performs the actual authentication.
     * @param username the username
     * @param password the password
     * @return the AuthenticationResult
     */
    private AuthenticationResult authenticate(String username, String password) {
        System.out.println("LoginController: Authenticating...");

        // Find user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new AuthenticationResult(false, null, "User not found.");
        }

        // Verify password using strategy
        boolean verified = authStrategy.verify(password, user.getPasswordHash());
        if (!verified) {
            return new AuthenticationResult(false, null, "Incorrect password.");
        }

        // Success
        return new AuthenticationResult(true, user, null);
    }
}