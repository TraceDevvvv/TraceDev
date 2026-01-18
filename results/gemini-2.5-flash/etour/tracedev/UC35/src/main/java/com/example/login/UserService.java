package com.example.login;

/**
 * Application service for user-related operations, primarily authentication.
 * It orchestrates interactions between the LoginController and UserRepository.
 */
public class UserService {
    private UserRepository userRepository;

    /**
     * Constructs a UserService with a given UserRepository.
     * @param userRepository The repository for accessing user data.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user based on provided credentials.
     *
     * @param credentials The login credentials (username and password).
     * @return true if authentication is successful, false otherwise.
     * @throws ConnectionException if a connection error occurs during user data retrieval.
     */
    public boolean authenticateUser(LoginCredentials credentials) {
        System.out.println("[UserService] Authenticating user: " + credentials.getUsername());

        try {
            User user = userRepository.findByUsername(credentials.getUsername());

            if (user != null) {
                // Check if the provided password matches the user's stored password
                if (user.isValidPassword(credentials.getPassword())) {
                    System.out.println("[UserService] Authentication successful for " + credentials.getUsername());
                    return true;
                } else {
                    System.out.println("[UserService] Invalid password for " + credentials.getUsername());
                    return false;
                }
            } else {
                System.out.println("[UserService] User not found: " + credentials.getUsername());
                return false;
            }
        } catch (ConnectionException e) {
            // Re-throw ConnectionException as per sequence diagram to be handled by LoginController
            System.err.println("[UserService] Connection error during authentication: " + e.getMessage());
            throw e;
        }
    }
}