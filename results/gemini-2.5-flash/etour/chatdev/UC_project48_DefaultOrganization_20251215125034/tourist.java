'''
Represents a Tourist entity with generic personal preferences.
This class acts as a data model for storing and retrieving user-specific settings.
'''
public class Tourist {
    private String id;
    private String username;
    private String favoriteDestination;
    private String preferredActivities;
    private boolean receiveNotifications;
    /**
     * Constructor for the Tourist class.
     * @param id A unique identifier for the tourist.
     * @param username The username of the tourist.
     * @param favoriteDestination The tourist's preferred travel destination.
     * @param preferredActivities A description of the tourist's preferred activities.
     * @param receiveNotifications A boolean indicating if the tourist wishes to receive notifications.
     */
    public Tourist(String id, String username, String favoriteDestination, String preferredActivities, boolean receiveNotifications) {
        this.id = id;
        this.username = username;
        this.favoriteDestination = favoriteDestination;
        this.preferredActivities = preferredActivities;
        this.receiveNotifications = receiveNotifications;
    }
    // --- Getters ---
    /**
     * Returns the unique identifier of the tourist.
     * @return The tourist's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the username of the tourist.
     * @return The tourist's username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Returns the tourist's favorite travel destination.
     * @return The favorite destination.
     */
    public String getFavoriteDestination() {
        return favoriteDestination;
    }
    /**
     * Returns the description of the tourist's preferred activities.
     * @return The preferred activities.
     */
    public String getPreferredActivities() {
        return preferredActivities;
    }
    /**
     * Returns true if the tourist wishes to receive notifications, false otherwise.
     * @return Notification preference.
     */
    public boolean wantsReceiveNotifications() {
        return receiveNotifications;
    }
    // --- Setters ---
    /**
     * Sets the unique identifier of the tourist.
     * @param id The new tourist ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Sets the username of the tourist.
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Sets the tourist's favorite travel destination.
     * @param favoriteDestination The new favorite destination.
     */
    public void setFavoriteDestination(String favoriteDestination) {
        this.favoriteDestination = favoriteDestination;
    }
    /**
     * Sets the description of the tourist's preferred activities.
     * @param preferredActivities The new preferred activities.
     */
    public void setPreferredActivities(String preferredActivities) {
        this.preferredActivities = preferredActivities;
    }
    /**
     * Sets whether the tourist wishes to receive notifications.
     * @param receiveNotifications The new notification preference.
     */
    public void setReceiveNotifications(boolean receiveNotifications) {
        this.receiveNotifications = receiveNotifications;
    }
    /**
     * Provides a string representation of the Tourist object.
     * @return A string containing tourist details.
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", favoriteDestination='" + favoriteDestination + '\'' +
               ", preferredActivities='" + preferredActivities + '\'' +
               ", receiveNotifications=" + receiveNotifications +
               '}';
    }
}