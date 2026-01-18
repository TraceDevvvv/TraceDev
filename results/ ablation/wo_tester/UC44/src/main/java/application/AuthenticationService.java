package application;

import domain.User;

/**
 * Service for authentication operations.
 * Added for requirement EC-001.
 */
public interface AuthenticationService {
    boolean validateToken(String token);
    User getCurrentUser();
}