package com.example.etour;

/**
 * JPA implementation of UserRepository.
 */
public class JpaUserRepository implements UserRepository {
    private Server server; // Connects to server as per class diagram.

    public JpaUserRepository() {
        this.server = new Server();
        // Assumption: server connection is established.
        server.connectionStatus = true;
    }

    @Override
    public UserEntity findByUsername(String username) {
        // Simulate database lookup.
        if ("john_doe".equals(username)) {
            UserEntity user = new UserEntity();
            user.setUserId("user123");
            user.setUsername(username);
            user.setPasswordHash("hashed_password");
            user.setEmail("john@example.com");
            user.setRole("USER");
            return user;
        }
        // Simulate connection loss
        if ("connection_lost_user".equals(username)) {
            throw new RuntimeException("ConnectionException");
        }
        return null;
    }

    @Override
    public UserEntity save(UserEntity user) {
        // Simulate save operation.
        return user;
    }
}