package com.example.application;

import com.example.domain.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation of AuthenticationService.
 * Added to satisfy requirement Entry Conditions.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    // In-memory map for demonstration. In real application, use session store.
    private Map<String, User> loggedInUsers = new HashMap<>();

    public void addLoggedInUser(User user) {
        loggedInUsers.put(user.getId(), user);
    }

    public void removeLoggedInUser(String userId) {
        loggedInUsers.remove(userId);
    }

    @Override
    public boolean isUserLoggedIn(String userId) {
        User user = loggedInUsers.get(userId);
        return user != null && user.isLoggedIn();
    }
}