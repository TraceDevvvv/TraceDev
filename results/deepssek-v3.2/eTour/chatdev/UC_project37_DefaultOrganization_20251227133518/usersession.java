'''
UserSession class manages the current user session state.
It tracks whether a user is logged in and stores user information.
Singleton pattern ensures a single session instance across the application.
Thread-safe eager initialization is used to prevent multiple instances in multi-threaded environments.
'''
public class UserSession {
    // Thread-safe eager initialization of the singleton instance
    private static final UserSession instance = new UserSession();
    private boolean isLoggedIn;
    private String username;
    // Private constructor for singleton pattern
    private UserSession() {
        this.isLoggedIn = false;
        this.username = null;
    }
    /**
     * Gets the singleton instance of UserSession.
     * @return UserSession instance
     */
    public static UserSession getInstance() {
        return instance;
    }
    /**
     * Logs in a user by setting session state.
     * @param username The username of the logged in user
     */
    public void login(String username) {
        this.isLoggedIn = true;
        this.username = username;
    }
    /**
     * Logs out the current user by clearing session state.
     */
    public void logout() {
        this.isLoggedIn = false;
        this.username = null;
    }
    /**
     * Checks if a user is currently logged in.
     * @return true if user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    /**
     * Gets the username of the currently logged in user.
     * @return username or null if no user is logged in
     */
    public String getUsername() {
        return username;
    }
}