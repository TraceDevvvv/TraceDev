package com.system;

import java.util.List;

/**
 * Controller for the Display User List use case.
 * Implements retry logic for reliable display (Quality Requirement).
 */
public class DisplayUserListController {
    private IUserRepository userRepository;
    private UserCache userCache;

    public DisplayUserListController() {
        this.userRepository = new UserRepository();
        this.userCache = new UserCache();
    }

    /**
     * Main execution method called by the boundary.
     * Sequence diagram interaction: UI -> Controller : execute()
     */
    public List<User> execute() {
        // Authenticate admin (Entry Condition)
        if (!authenticateAdmin()) {
            System.err.println("Admin authentication failed.");
            return null;
        }

        // Quality requirement: retry logic
        return retryFindAll(3);
    }

    /**
     * Authenticates the administrator.
     * Sequence diagram alt condition: Administrator is authenticated
     */
    boolean authenticateAdmin() {
        // In a real system, this would check session/security context.
        // For simplicity, assume there is a global/singleton admin instance.
        Administrator admin = Administrator.getCurrentAdmin();
        return admin != null && admin.isAuthenticated();
    }

    /**
     * Retry logic for reliable display.
     * Sequence diagram: Controller -> Controller : retryFindAll(3)
     */
    List<User> retryFindAll(int maxRetries) {
        List<User> users = null;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("Attempt " + attempt + " to fetch users...");
            users = userRepository.findAll();
            if (users != null && !users.isEmpty()) {
                // Success: update cache and return
                userCache.updateUsers(users);
                return users;
            }
            // If failure, wait before retry (simulated)
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // After all retries, try cache as fallback
        if (userCache != null && !userCache.isExpired()) {
            users = userCache.getUsers();
            if (users != null && !users.isEmpty()) {
                System.out.println("Returning cached users.");
                return users;
            }
        }
        return null;
    }
}