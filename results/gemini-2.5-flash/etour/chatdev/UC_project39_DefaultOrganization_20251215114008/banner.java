'''
Represents a Banner Ad in the system.
This class holds the data for a banner, including its ID, name, and the path to its image.
'''
public class Banner {
    private String id;
    private String name;
    private String imagePath; // Path or URL to the banner image
    /**
     * Constructs a new Banner object.
     * @param id The unique identifier for the banner.
     * @param name The name or title of the banner.
     * @param imagePath The file path or URL to the banner's image.
     */
    public Banner(String id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }
    /**
     * Returns the unique ID of the banner.
     * @return The banner ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the unique ID of the banner.
     * @param id The new banner ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the name of the banner.
     * @return The banner name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the banner.
     * @param name The new banner name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the path to the banner's image.
     * @return The image path.
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Sets the path to the banner's image.
     * @param imagePath The new image path.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    /**
     * Provides a string representation of the Banner, useful for display in UI components.
     * @return A string combining the banner ID and name.
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
}