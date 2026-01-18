/**
 * Represents a Banner ad with its properties.
 */
public class Banner {
    private String name;
    private String imageUrl;
    private String status;
    public Banner(String name, String imageUrl, String status) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return name + " (" + status + ")";
    }
}