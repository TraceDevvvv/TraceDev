/**
 * Tourist class representing an authenticated user with search preferences
 * This class models a tourist in the eTour system who can modify their search preferences
 */
public class Tourist {
    private String userId;
    private String username;
    private boolean authenticated;
    private SearchPreferences searchPreferences;
    
    /**
     * Constructor for creating a Tourist with authentication status
     * @param userId unique user identifier
     * @param username display name of the user
     * @param authenticated whether the user is currently authenticated
     */
    public Tourist(String userId, String username, boolean authenticated) {
        this.userId = userId;
        this.username = username;
        this.authenticated = authenticated;
        this.searchPreferences = null; // Initialize as null, will be set later
    }
    
    /**
     * Authenticates the user with the system
     * @param password the password to authenticate with
     * @return true if authentication is successful, false otherwise
     */
    public boolean authenticate(String password) {
        // In a real system, this would validate against a database
        // For simulation purposes, we'll use a simple check
        if (password != null && !password.trim().isEmpty()) {
            this.authenticated = true;
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the user is authenticated
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    /**
     * Sets the authentication status of the user
     * @param authenticated the new authentication status
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    
    /**
     * Gets the user ID
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Sets the user ID
     * @param userId the new user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * Gets the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Gets the current search preferences
     * @return the search preferences object, could be null if not set
     */
    public SearchPreferences getSearchPreferences() {
        return searchPreferences;
    }
    
    /**
     * Sets the search preferences for this user
     * @param searchPreferences the new search preferences
     */
    public void setSearchPreferences(SearchPreferences searchPreferences) {
        this.searchPreferences = searchPreferences;
    }
    
    /**
     * Checks if the user has existing search preferences
     * @return true if preferences exist, false otherwise
     */
    public boolean hasSearchPreferences() {
        return searchPreferences != null;
    }
    
    /**
     * Returns a string representation of the tourist
     * @return string representation
     */
    @Override
    public String toString() {
        return "Tourist [userId=" + userId + ", username=" + username + 
               ", authenticated=" + authenticated + ", hasPreferences=" + 
               hasSearchPreferences() + "]";
    }
}