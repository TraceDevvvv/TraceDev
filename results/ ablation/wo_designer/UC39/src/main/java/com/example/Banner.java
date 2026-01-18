
import java.io.File;

public class Banner {
    private int id;
    private String name;
    private File image;

    public Banner(int id, String name, File image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Banner ID: " + id + ", Name: " + name + ", Image: " + (image != null ? image.getName() : "None");
    }
}
