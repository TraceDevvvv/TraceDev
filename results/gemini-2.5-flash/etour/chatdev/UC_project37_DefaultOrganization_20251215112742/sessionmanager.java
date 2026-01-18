/*
Manages the current user session state, including login and logout functionalities.
*/
package com.chatdev.session;
import com.chatdev.model.User;
import com.chatdev.store.UserStore;
/**
 * Manages the current user session state, including login and logout functionalities.
 * This class ensures only one user can be logged in at a time.
 */
public class SessionManager {
    // The currently logged-in user. Null if no user is logged in.
    private static User currentUser = null;
    /**
     * Attempts to log in a user with the given username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return A LoginResult enum indicating the outcome of the login attempt.
     */
    public static LoginResult login(String username, String password) {
        if (isLoggedIn()) {
            // Already logged in, returning appropriate status without printing to console.
            return LoginResult.ALREADY_LOGGED_IN;
        }
        User user = UserStore.findUser(username, password);
        if (user != null) {
            currentUser = user;
            // Login successful, returning success status without printing to console.
            return LoginResult.SUCCESS;
        } else {
            // Login failed due to invalid credentials, returning appropriate status.
            return LoginResult.INVALID_CREDENTIALS;
        }
    }
    /**
     * Logs out the current user, ending their session.
     * The currentUser is set to null.
     */
    public static void logout() {
        if (isLoggedIn()) {
            // Logic to disconnect the user, actual notification handled by the view/controller.
            currentUser = null;
        } else {
            // No user logged in, nothing to do.
        }
    }
    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    /**
     * Gets the currently logged-in user.
     *
     * @return The User object if logged in, otherwise null.
     */
    public static User getCurrentUser() {
        return currentUser;
    }
}