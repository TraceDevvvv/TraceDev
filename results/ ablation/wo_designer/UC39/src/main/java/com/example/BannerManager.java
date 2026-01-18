import java.io.*;
import java.util.*;

public class BannerManager {
    private List<Banner> banners;

    public BannerManager() {
        // Initialize with some dummy banners
        banners = new ArrayList<>();
        banners.add(new Banner(1, "Summer Sale", new File("summer.jpg")));
        banners.add(new Banner(2, "Winter Special", new File("winter.jpg")));
        banners.add(new Banner(3, "New Menu", new File("menu.jpg")));
    }

    public List<Banner> getBanners() {
        return new ArrayList<>(banners);
    }

    public void updateBannerImage(int bannerId, File newImage) throws IOException {
        for (Banner banner : banners) {
            if (banner.getId() == bannerId) {
                // Simulate saving the image (in reality, copy to a specific directory)
                if (!newImage.exists()) {
                    throw new IOException("Image file does not exist.");
                }
                banner.setImage(newImage);
                return;
            }
        }
        throw new IllegalArgumentException("Banner not found.");
    }
}