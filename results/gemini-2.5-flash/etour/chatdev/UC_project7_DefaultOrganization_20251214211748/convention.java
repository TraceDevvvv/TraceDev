'''
Represents a Convention object.
This class holds data related to a convention request, including its ID, name,
associated refreshment point, and its current activation status.
'''
/**
 * Represents a Convention object.
 * This class holds data related to a convention request, including its ID, name,
 * associated refreshment point, and its current activation status.
 */
public class Convention {
    private String id;
    private String name;
    private String refreshmentPointId;
    private boolean isActive;
    private String status; // e.g., "PENDING_ACTIVATION", "ACTIVE", "INACTIVE"
    /**
     * Constructs a new Convention object.
     *
     * @param id The unique identifier for the convention.
     * @param name The name of the convention.
     * @param refreshmentPointId The ID of the refreshment point associated with this convention.
     * @param isActive The initial activation status of the convention.
     * @param status The initial status string of the convention (e.g., "PENDING_ACTIVATION").
     */
    public Convention(String id, String name, String refreshmentPointId, boolean isActive, String status) {
        this.id = id;
        this.name = name;
        this.refreshmentPointId = refreshmentPointId;
        this.isActive = isActive;
        this.status = status;
    }
    /**
     * Gets the ID of the convention.
     *
     * @return The convention ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the convention.
     *
     * @param id The new convention ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the name of the convention.
     *
     * @return The convention name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the convention.
     *
     * @param name The new convention name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the refreshment point ID associated with the convention.
     *
     * @return The refreshment point ID.
     */
    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }
    /**
     * Sets the refreshment point ID associated with the convention.
     *
     * @param refreshmentPointId The new refreshment point ID.
     */
    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }
    /**
     * Checks if the convention is active.
     *
     * @return True if the convention is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }
    /**
     * Sets the active status of the convention.
     *
     * @param active The new active status.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
    /**
     * Gets the status string of the convention.
     *
     * @return The convention status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status string of the convention.
     *
     * @param status The new convention status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Convention{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", refreshmentPointId='" + refreshmentPointId + '\'' +
               ", isActive=" + isActive +
               ", status='" + status + '\'' +
               '}';
    }
}