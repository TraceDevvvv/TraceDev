'''
Represents a banner ad associated with a refreshment point.
Each banner has a unique ID, a reference to its associated refreshment point,
and a description.
'''
package model;
public class Banner {
    private int id;
    private int refreshmentPointId;
    private String description;
    /**
     * Constructs a new Banner.
     *
     * @param id                 The unique identifier for the banner.
     * @param refreshmentPointId The ID of the refreshment point this banner belongs to.
     * @param description        A textual description of the banner content.
     */
    public Banner(int id, int refreshmentPointId, String description) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.description = description;
    }
    /**
     * Gets the ID of the banner.
     *
     * @return The banner's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the ID of the refreshment point this banner is associated with.
     *
     * @return The associated refreshment point's ID.
     */
    public int getRefreshmentPointId() {
        return refreshmentPointId;
    }
    /**
     * Gets the description of the banner.
     *
     * @return The banner's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns a string representation of the Banner,
     * which is its description. This is useful for displaying objects
     * directly in Swing components like JComboBox.
     *
     * @return The description of the banner.
     */
    @Override
    public String toString() {
        return description;
    }
}