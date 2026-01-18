package com.etour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AuthenticationService {
    // In-memory user database (in real application, this would be a database)
    private Map<String, User> userDatabase;
    private Random random;
    
    public AuthenticationService() {
        this.userDatabase = new HashMap<>();
        this.random = new Random();
        // Initialize with some sample users
        initializeSampleUsers();
    }
    
    private void initializeSampleUsers() {
        userDatabase.put("john_doe", new User("john_doe", "password123", "REGISTERED_USER", 2));
        userDatabase.put("jane_smith", new User("jane_smith", "pass456", "PREMIUM_USER", 3));
        userDatabase.put("admin", new User("admin", "admin123", "ADMIN", 5));
    }
    
    /**
     * Authenticates a user with given credentials
     * @param username The username
     * @param password The password
     * @return User object if authentication successful
     * @throws AuthenticationException if credentials are invalid
     * @throws ServerConnectionException if connection to server is interrupted
     */
    public User authenticate(String username, String password) 
            throws AuthenticationException, ServerConnectionException {
        
        // Simulate potential server connection interruption (5% chance)
        if (random.nextInt(100) < 5) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted");
        }
        
        // Check if user exists
        if (!userDatabase.containsKey(username)) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        User user = userDatabase.get(username);
        
        // Verify password (in real application, compare hashed passwords)
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        return user;
    }
    
    /**
     * Adds a new user to the system (for testing purposes)
     * @param user The user to add
     */
    public void addUser(User user) {
        userDatabase.put(user.getUsername(), user);
    }
}