package hec.adapters;

/**
 * Simulated session manager for authentication.
 * For simplicity, we assume a session is always valid.
 */
public class SessionManager {
    /**
     * Checks if the given user ID has a valid session.
     *
     * @param userId the user ID
     * @return true if valid (simulated)
     */
    public boolean isValid(String userId) {
        return userId != null && !userId.isEmpty();
    }

    /**
     * Gets the current user ID from session.
     *
     * @return a simulated user ID
     */
    public String getCurrentUserId() {
        return "user123"; // Simulated user ID.
    }
}