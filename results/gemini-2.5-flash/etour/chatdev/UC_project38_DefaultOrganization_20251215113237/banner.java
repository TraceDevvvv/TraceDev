/*
 * Represents a banner object with an ID and the path to its image.
 */
import java.util.UUID;
public class Banner {
    private String id;
    private String imagePath;
    /**
     * Constructs a new Banner with a unique ID and the specified image path.
     * @param imagePath The file path to the banner image.
     */
    public Banner(String imagePath) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the banner
        this.imagePath = imagePath;
    }
    /**
     * Gets the unique ID of this banner.
     * @return The banner's ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Gets the file path of the image associated with this banner.
     * @return The image file path.
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Returns a string representation of the Banner object.
     * @return A string containing the banner's ID and image path.
     */
    @Override
    public String toString() {
        return "Banner{" +
               "id='" + id + '\'' +
               ", imagePath='" + imagePath + '\'' +
               '}';
    }
}