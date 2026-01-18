package com.system;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Cache for User entities to improve performance.
 */
public class UserCache {
    private List<User> cachedUsers;
    private LocalDateTime lastUpdated;

    public UserCache() {
        this.cachedUsers = new ArrayList<>();
        this.lastUpdated = LocalDateTime.now().minusHours(1); // initially expired
    }

    /**
     * Returns cached users if not expired.
     */
    public List<User> getUsers() {
        if (isExpired()) {
            return new ArrayList<>();
        }
        return cachedUsers;
    }

    /**
     * Updates the cache with new user list.
     * Sequence diagram: Controller -> Cache : updateUsers(List<User>)
     */
    public void updateUsers(List<User> users) {
        this.cachedUsers = users != null ? new ArrayList<>(users) : new ArrayList<>();
        this.lastUpdated = LocalDateTime.now();
    }

    /**
     * Checks if cache is expired (older than 5 minutes).
     */
    public boolean isExpired() {
        return lastUpdated.isBefore(LocalDateTime.now().minusMinutes(5));
    }
}