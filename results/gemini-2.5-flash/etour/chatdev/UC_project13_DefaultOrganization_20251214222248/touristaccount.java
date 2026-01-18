/**
 * Represents a single tourist account with basic details and an active status.
 */
public class TouristAccount {
    private String touristId;
    private String name;
    private String email;
    private boolean isActive;
    /**
     * Constructs a new TouristAccount.
     *
     * @param touristId A unique identifier for the tourist.
     * @param name      The name of the tourist.
     * @param email     The email address of the tourist.
     * @param isActive  The initial status of the account (true for active, false for disabled).
     */
    public TouristAccount(String touristId, String name, String email, boolean isActive) {
        this.touristId = touristId;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
    }
    /**
     * Returns the unique ID of the tourist.
     * @return The tourist ID.
     */
    public String getTouristId() {
        return touristId;
    }
    /**
     * Returns the name of the tourist.
     * @return The tourist's full name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the email address of the tourist.
     * @return The tourist's email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Checks if the tourist account is active.
     * @return True if the account is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * Sets the active status of the tourist account.
     * @param active The new status (true for active, false for disabled).
     */
    public void setActive(boolean active) {
        isActive = active;
    }
    /**
     * Provides a string representation of the tourist account,
     * useful for display in UI components like JList.
     * @return A formatted string showing tourist name and ID.
     */
    @Override
    public String toString() {
        return name + " (ID: " + touristId + ")";
    }
}