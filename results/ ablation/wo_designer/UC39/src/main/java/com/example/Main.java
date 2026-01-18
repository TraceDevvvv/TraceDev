import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        BannerManager manager = new BannerManager();
        Scanner scanner = new Scanner(System.in);

        // Simulate authentication (in a real system, this would involve credentials)
        System.out.println("Authentication successful.");

        // Step 1: Operator selects editing functionality
        System.out.println("Enter 'edit' to start banner editing: ");
        String input = scanner.nextLine();
        if (!input.equalsIgnoreCase("edit")) {
            System.out.println("Operation cancelled.");
            return;
        }

        // Step 2 & 3: View list and select banner
        List<Banner> banners = manager.getBanners();
        System.out.println("Available banners:");
        for (int i = 0; i < banners.size(); i++) {
            System.out.println((i + 1) + ". " + banners.get(i));
        }
        System.out.println("Select a banner number: ");
        int bannerIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline
        if (bannerIndex < 1 || bannerIndex > banners.size()) {
            System.out.println("Invalid selection. Operation cancelled.");
            return;
        }
        Banner selectedBanner = banners.get(bannerIndex - 1);

        // Step 4: Enter editing
        System.out.println("Editing banner: " + selectedBanner);

        // Step 5 & 6: Display form and select picture
        System.out.println("Enter the path of the new image: ");
        String imagePath = scanner.nextLine();
        File imageFile = new File(imagePath);

        // Step 7 & 8: Check image characteristics
        ImageValidator validator = new ImageValidator();
        if (!validator.isValid(imageFile)) {
            System.out.println("Invalid image. Operation cancelled.");
            return;
        }

        // Step 9 & 11 & 12: Ask for confirmation
        System.out.println("Confirm change? (yes/no): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Operation cancelled.");
            return;
        }

        // Step 13: Update banner image
        try {
            manager.updateBannerImage(selectedBanner.getId(), imageFile);
            System.out.println("Banner updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating banner: " + e.getMessage());
        }
    }
}