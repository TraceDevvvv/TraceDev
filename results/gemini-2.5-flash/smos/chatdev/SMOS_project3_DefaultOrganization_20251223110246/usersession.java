/*
 * Represents the current user session, managing login and logout status.
 * This class uses a static approach to simulate a global session state for simplicity.
 */
public class UserSession {
    // A static boolean flag to track if a user is currently logged in.
    private static boolean isLoggedIn = false;
    // A placeholder for the logged-in username.
    private static String currentUsername = null;
    /**
     * Simulates a user logging into the system.
     * Sets the session's login status to true and stores a dummy username.
     *
     * @param username The username of the user logging in.
     */
    public static void login(String username) {
        // In a real application, this would involve authenticating credentials.
        // For this simulation, we assume authentication is successful.
        isLoggedIn = true;
        currentUsername = username;
        System.out.println("User " + username + " logged in successfully.");
    }
    /**
     * Simulates a user logging out of the system.
     * Sets the session's login status to false and clears the username.
     */
    public static void logout() {
        // In a real application, this might invalidate tokens or clear session data.
        String user = currentUsername; // Store username temporarily for logout message
        isLoggedIn = false;
        currentUsername = null;
        System.out.println("User " + user + " logged out successfully.");
    }
    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
    /**
     * Retrieves the username of the currently logged-in user.
     *
     * @return The username if logged in, otherwise null.
     */
    public static String getUsername() {
        return currentUsername;
    }
}