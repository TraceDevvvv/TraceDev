/**
 * AuthenticationManager.java
 * This class simulates user authentication. It checks if the current user
 * is logged in as an administrator, which is a precondition for the use case.
 * In a real application, this would integrate with a secure authentication system.
 */
public class AuthenticationManager {
    private static boolean isAdminLoggedIn = false;
    /**
     * Simulates logging in a user. Sets the admin login status.
     * In a real app, this would validate credentials against a database.
     *
     * @param username The username
     * @param password The password
     * @return true if login is successful as admin, false otherwise
     */
    public static boolean login(String username, String password) {
        // Simulate admin login: for demo, assume "admin" with any password works
        if (username.equals("admin")) {
            isAdminLoggedIn = true;
            return true;
        }
        return false;
    }
    /**
     * Checks if the current user is logged in as an administrator.
     * This is a precondition for the InsertNewTeaching use case.
     *
     * @return true if admin is logged in, false otherwise
     */
    public static boolean isAdminLoggedIn() {
        return isAdminLoggedIn;
    }
    /**
     * Logs out the current user.
     */
    public static void logout() {
        isAdminLoggedIn = false;
    }
}