package infrastructure;

import application.AuthenticationService;
import domain.User;

import java.util.UUID;

/**
 * Implementation of AuthenticationService.
 * Added for requirement EC-001.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    // TokenValidator omitted; using simulation.

    public AuthenticationServiceImpl() {
        // In real app, would inject TokenValidator
    }

    @Override
    public boolean validateToken(String token) {
        // Simulate token validation: for demonstration, any non‑null token is valid.
        return token != null && !token.trim().isEmpty();
    }

    @Override
    public User getCurrentUser() {
        // Simulate fetching current user; in a real app, this would come from security context.
        return new User(UUID.randomUUID().toString(), "operator", "OPERATOR");
    }
}