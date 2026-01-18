'''
Handles administrator session management.
Verifies if the current user is logged in as an administrator.
'''
public class AdminSession {
    private static boolean isAdminLoggedIn = false;
    /**
     * Simulates admin login.
     */
    public static boolean loginAsAdmin(String username, String password) {
        // Simple simulation - in real app, check against database
        if ("admin".equals(username) && "admin123".equals(password)) {
            isAdminLoggedIn = true;
            return true;
        }
        return false;
    }
    /**
     * Checks if administrator is logged in (precondition).
     */
    public static boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }
    /**
     * Logs out the administrator.
     */
    public static void logout() {
        isAdminLoggedIn = false;
    }
}