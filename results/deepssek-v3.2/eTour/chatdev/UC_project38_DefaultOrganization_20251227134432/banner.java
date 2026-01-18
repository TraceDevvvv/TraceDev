/**
 * Model class representing a banner
 */
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
public class Banner {
    private int id;
    private BufferedImage image;
    private LocalDateTime createdDate;
    private String imagePath;
    public Banner(BufferedImage image, String imagePath) {
        this.image = image;
        this.imagePath = imagePath;
        this.createdDate = LocalDateTime.now();
    }
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}