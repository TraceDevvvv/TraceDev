'''
Represents a banner ad associated with a specific Point of Interest.
It includes information about the banner's identity, its name,
the ID of the Point of Interest it belongs to, and the path to its image.
'''
package models;
/**
 * Represents a banner ad associated with a specific Point of Interest.
 * It includes information about the banner's identity, its name,
 * the ID of the Point of Interest it belongs to, and the path to its image.
 */
public class Banner {
    private int id;          // Unique identifier for the banner
    private String name;     // Name or description of the banner
    private int poiId;       // Foreign key linking to a PointOfInterest
    private String imagePath; // File path to the image displayed by the banner
    /**
     * Constructs a new Banner object.
     *
     * @param id        The unique ID of the banner.
     * @param name      The name of the banner.
     * @param poiId     The ID of the Point of Interest this banner belongs to.
     * @param imagePath The initial file path to the banner's image.
     */
    public Banner(int id, String name, int poiId, String imagePath) {
        this.id = id;
        this.name = name;
        this.poiId = poiId;
        this.imagePath = imagePath;
    }
    /**
     * Returns the ID of the banner.
     *
     * @return The integer ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the name of the banner.
     *
     * @return The string name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the Point of Interest ID this banner is associated with.
     *
     * @return The integer PoI ID.
     */
    public int getPoiId() {
        return poiId;
    }
    /**
     * Returns the current image path of the banner.
     *
     * @return The string image path.
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Sets a new image path for the banner. This method is used
     * when the banner's image is modified.
     *
     * @param imagePath The new file path to the banner's image.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    /**
     * Overrides the toString method to provide a user-friendly representation
     * for display in GUI components like JList. It includes the banner's name
     * and a snippet of its current image path.
     *
     * @return A string representation of the banner.
     */
    @Override
    public String toString() {
        String fileName = (imagePath != null && !imagePath.isEmpty())
                ? imagePath.substring(Math.max(imagePath.lastIndexOf('/'), imagePath.lastIndexOf('\\')) + 1)
                : "None";
        return name + " (Current: " + fileName + ")";
    }
}