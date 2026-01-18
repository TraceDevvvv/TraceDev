package com.example.login;

/**
 * Application Layer (Use Case Controller): Orchestrates the login process.
 * Contains the core business logic for authenticating users.
 */
public class LoginService {
    private CredentialValidator credentialValidator;
    private UserRepository userRepository;
    private UserSessionManager sessionManager;

    /**
     * Constructs a new LoginService with its dependencies.
     * @param validator The CredentialValidator for validating username and password formats.
     * @param repo The UserRepository for accessing user data.
     * @param session The UserSessionManager for managing user sessions.
     */
    public LoginService(CredentialValidator validator, UserRepository repo, UserSessionManager session) {
        this.credentialValidator = validator;
        this.userRepository = repo;
        this.sessionManager = session;
    }

    /**
     * Authenticates a user based on the provided username and password.
     * // Modified to satisfy requirement R8, R9, R10, R13, R14
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return The authenticated User object.
     * @throws InvalidCredentialsException If username/password format is invalid or credentials do not match.
     * @throws SystemErrorException If a system-level error occurs (e.g., database connection issue).
     */
    public User authenticate(String username, String password) throws InvalidCredentialsException, SystemErrorException {
        System.out.println("LoginService: Attempting to authenticate user: " + username);

        // 1. System checks that the username has a length greater than or equal to 5.
        if (!credentialValidator.isValidUsername(username)) {
            System.out.println("LoginService: Log \"Invalid username format\" for user: " + username);
            throw new InvalidCredentialsException("Invalid username or password."); // R8, R9, R10
        }

        // 2. System checks that the password has a length greater than or equal to 5.
        if (!credentialValidator.isValidPassword(password)) {
            System.out.println("LoginService: Log \"Invalid password format\" for user: " + username);
            throw new InvalidCredentialsException("Invalid username or password."); // R8, R9, R10
        }

        // For security, password should be hashed before querying or storing.
        String passwordHash = hashPassword(password);
        System.out.println("LoginService: Password hashed for user: " + username);

        User user = null;
        try {
            // 3. System searches in the archive for the entered username and password among loggable users.
            user = userRepository.findByUsernameAndPasswordHash(username, passwordHash);
        } catch (ConnectionException e) {
            // R13, R14: Handle connection interruption from repository
            System.err.println("LoginService: Connection to UserRepository lost: " + e.getMessage());
            throw new SystemErrorException("Connection to SMOS server lost."); // R13
        }

        if (user == null) {
            System.out.println("LoginService: Log \"Authentication failed for username: " + username + "\"");
            throw new InvalidCredentialsException("Invalid username or password."); // R8, R9, R10
        }

        // 4. System logs in the user to the system.
        sessionManager.login(user);
        System.out.println("LoginService: User " + user.getUsername() + " successfully logged into session.");

        return user;
    }

    /**
     * A placeholder method to simulate password hashing.
     * In a real application, this would use a strong hashing algorithm like bcrypt.
     * @param password The plain text password.
     * @return A simple hash representation of the password.
     */
    private String hashPassword(String password) {
        // Using a simple hashCode for demonstration.
        // DO NOT USE THIS IN PRODUCTION.
        return String.valueOf(password.hashCode());
    }
}