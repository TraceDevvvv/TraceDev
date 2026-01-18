/*
Represents a 'Tourist' actor who can modify personal search preferences.
For simplicity, this class currently only holds a user ID.
In a real application, it would contain more user-related information and
authentication status.
*/
public class Tourist {
    private String userId;
    /**
     * Constructs a new Tourist with the specified user ID.
     *
     * @param userId The unique identifier for the tourist.
     */
    public Tourist(String userId) {
        this.userId = userId;
    }
    /**
     * Gets the user ID of the tourist.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * Sets the user ID of the tourist.
     *
     * @param userId The new user ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * Returns a string representation of the Tourist object.
     *
     * @return A string containing the user ID.
     */
    @Override
    public String toString() {
        return "Tourist{" +
               "userId='" + userId + '\'' +
               '}';
    }
}