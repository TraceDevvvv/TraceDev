import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

// Represents an Image with a unique ID and a URL.
class Image {
    private String id;
    private String url;
    private long sizeBytes; // Size of the image in bytes
    private String format; // Image format, e.g., "JPEG", "PNG"

    public Image(String url, long sizeBytes, String format) {
        this.id = UUID.randomUUID().toString();
        this.url = url;
        this.sizeBytes = sizeBytes;
        this.format = format;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public String getFormat() {
        return format;
    }

    // Validates if the image characteristics are acceptable.
    // For demonstration, let's say an image is valid if its size is between 1KB and 5MB
    // and its format is either JPEG or PNG.
    public boolean isValid() {
        return sizeBytes >= 1024 && sizeBytes <= 5 * 1024 * 1024 &&
               ("JPEG".equalsIgnoreCase(format) || "PNG".equalsIgnoreCase(format));
    }

    @Override
    public String toString() {
        return "Image ID: " + id + ", URL: " + url + ", Size: " + sizeBytes + " bytes, Format: " + format;
    }
}

// Represents a Banner with a unique ID, a name, and an associated Image.
class Banner {
    private String id;
    private String name;
    private Image image;

    public Banner(String name, Image image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Banner ID: " + id + ", Name: " + name + ", Current Image: [" + image.getUrl() + "]";
    }
}

// Represents a "Refreshment Point" or an advertising slot where banners can be displayed.
class RefreshmentPoint {
    private String id;
    private String name;
    private List<Banner> associatedBanners;

    public RefreshmentPoint(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.associatedBanners = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Banner> getAssociatedBanners() {
        return associatedBanners;
    }

    public void addBanner(Banner banner) {
        this.associatedBanners.add(banner);
    }

    @Override
    public String toString() {
        return "Refreshment Point ID: " + id + ", Name: " + name + ", Banners: " + associatedBanners.size();
    }
}

// Simulates a database or data store for banners, images, and refreshment points.
class DataStore {
    private List<RefreshmentPoint> refreshmentPoints;
    private List<Image> availableImages;

    public DataStore() {
        this.refreshmentPoints = new ArrayList<>();
        this.availableImages = new ArrayList<>();
        // Initialize with some dummy data
        initializeData();
    }

    private void initializeData() {
        // Create some images
        Image img1 = new Image("http://example.com/img1.jpg", 200 * 1024, "JPEG");
        Image img2 = new Image("http://example.com/img2.png", 500 * 1024, "PNG");
        Image img3 = new Image("http://example.com/img3.gif", 10 * 1024, "GIF"); // Invalid format
        Image img4 = new Image("http://example.com/img4.jpg", 5 * 1024 * 1024 + 1, "JPEG"); // Too large
        Image img5 = new Image("http://example.com/img5.png", 50 * 1024, "PNG");

        availableImages.add(img1);
        availableImages.add(img2);
        availableImages.add(img3);
        availableImages.add(img4);
        availableImages.add(img5);

        // Create some banners
        Banner bannerA = new Banner("Summer Sale Banner", img1);
        Banner bannerB = new Banner("New Arrivals Banner", img2);
        Banner bannerC = new Banner("Clearance Banner", img5);

        // Create refreshment points and associate banners
        RefreshmentPoint rp1 = new RefreshmentPoint("Homepage Top Banner Slot");
        rp1.addBanner(bannerA);
        rp1.addBanner(bannerB);

        RefreshmentPoint rp2 = new RefreshmentPoint("Category Page Sidebar Ad");
        rp2.addBanner(bannerC);

        refreshmentPoints.add(rp1);
        refreshmentPoints.add(rp2);
    }

    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        return new ArrayList<>(refreshmentPoints);
    }

    public Optional<RefreshmentPoint> getRefreshmentPointById(String id) {
        return refreshmentPoints.stream()
                .filter(rp -> rp.getId().equals(id))
                .findFirst();
    }

    public Optional<Banner> getBannerById(String rpId, String bannerId) {
        return getRefreshmentPointById(rpId)
                .flatMap(rp -> rp.getAssociatedBanners().stream()
                        .filter(banner -> banner.getId().equals(bannerId))
                        .findFirst());
    }

    public List<Image> getAvailableImages() {
        return new ArrayList<>(availableImages);
    }

    // Simulates updating a banner in the data store.
    public boolean updateBannerImage(String bannerId, Image newImage) {
        for (RefreshmentPoint rp : refreshmentPoints) {
            for (Banner banner : rp.getAssociatedBanners()) {
                if (banner.getId().equals(bannerId)) {
                    banner.setImage(newImage);
                    return true;
                }
            }
        }
        return false;
    }
}

// Represents the Agency Operator who interacts with the system.
class AgencyOperator {
    private String username;
    private boolean loggedIn;

    public AgencyOperator(String username) {
        this.username = username;
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        System.out.println(username + " is logging in...");
        this.loggedIn = true;
        System.out.println(username + " logged in successfully.");
    }

    public void logout() {
        System.out.println(username + " is logging out...");
        this.loggedIn = false;
        System.out.println(username + " logged out.");
    }
}

// Main system class that orchestrates the ModifyBanner use case.
class AdManagementSystem {
    private DataStore dataStore;
    private Scanner scanner;

    public AdManagementSystem(DataStore dataStore) {
        this.dataStore = dataStore;
        this.scanner = new Scanner(System.in);
    }

    // Simulates the "SearchRefreshmentPoint" use case to get a list of points.
    public List<RefreshmentPoint> searchRefreshmentPoints() {
        System.out.println("\n--- Searching for Refreshment Points ---");
        List<RefreshmentPoint> points = dataStore.getAllRefreshmentPoints();
        if (points.isEmpty()) {
            System.out.println("No refreshment points found.");
        } else {
            System.out.println("Available Refreshment Points:");
            for (int i = 0; i < points.size(); i++) {
                System.out.println((i + 1) + ". " + points.get(i).getName() + " (ID: " + points.get(i).getId() + ")");
            }
        }
        return points;
    }

    // Displays banners associated with a selected refreshment point.
    public List<Banner> viewBannersForRefreshmentPoint(RefreshmentPoint selectedRp) {
        System.out.println("\n--- Banners for Refreshment Point: " + selectedRp.getName() + " ---");
        List<Banner> banners = selectedRp.getAssociatedBanners();
        if (banners.isEmpty()) {
            System.out.println("No banners associated with this refreshment point.");
        } else {
            for (int i = 0; i < banners.size(); i++) {
                System.out.println((i + 1) + ". " + banners.get(i).getName() + " (ID: " + banners.get(i).getId() + ")");
                System.out.println("   Current Image: " + banners.get(i).getImage().getUrl());
            }
        }
        return banners;
    }

    // Displays a form for image selection.
    public List<Image> displayImageSelectionForm() {
        System.out.println("\n--- Available Images for Selection ---");
        List<Image> images = dataStore.getAvailableImages();
        if (images.isEmpty()) {
            System.out.println("No images available in the system.");
        } else {
            for (int i = 0; i < images.size(); i++) {
                System.out.println((i + 1) + ". " + images.get(i));
            }
        }
        return images;
    }

    // Simulates the "Errored" use case.
    public void handleErroredUseCase(String errorMessage) {
        System.err.println("\n--- ERROR: " + errorMessage + " ---");
        System.err.println("The operation could not be completed due to an invalid input or system issue.");
        // In a real system, this might log the error, notify an administrator, etc.
    }

    // Main flow for modifying a banner.
    public void modifyBanner(AgencyOperator operator) {
        if (!operator.isLoggedIn()) {
            System.out.println("Error: Agency Operator must be logged in to modify banners.");
            return;
        }

        System.out.println("\n--- Starting Modify Banner Use Case ---");

        // Step 1: Receives a list of turning points of the rest use case SearchRefreshmentPoint and select one from accessing the function of editing a banner.
        List<RefreshmentPoint> refreshmentPoints = searchRefreshmentPoints();
        if (refreshmentPoints.isEmpty()) {
            System.out.println("Cannot proceed: No refreshment points available.");
            return;
        }

        RefreshmentPoint selectedRp = null;
        while (selectedRp == null) {
            System.out.print("Select a Refreshment Point by number to edit its banners: ");
            try {
                int rpChoice = Integer.parseInt(scanner.nextLine());
                if (rpChoice > 0 && rpChoice <= refreshmentPoints.size()) {
                    selectedRp = refreshmentPoints.get(rpChoice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number within the range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println("Selected Refreshment Point: " + selectedRp.getName());

        // Step 2: View the list of banner associated with the point of rest.
        List<Banner> banners = viewBannersForRefreshmentPoint(selectedRp);
        if (banners.isEmpty()) {
            System.out.println("Cannot proceed: No banners associated with this refreshment point.");
            return;
        }

        // Step 3: Select a banner from the list and enter the editing functionality.
        Banner selectedBanner = null;
        while (selectedBanner == null) {
            System.out.print("Select a Banner by number to modify: ");
            try {
                int bannerChoice = Integer.parseInt(scanner.nextLine());
                if (bannerChoice > 0 && bannerChoice <= banners.size()) {
                    selectedBanner = banners.get(bannerChoice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number within the range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println("Selected Banner for modification: " + selectedBanner.getName());

        // Step 4: Displays a form for the selection of an image.
        List<Image> availableImages = displayImageSelectionForm();
        if (availableImages.isEmpty()) {
            System.out.println("Cannot proceed: No images available for selection.");
            return;
        }

        // Step 5: Select a picture and send the request to change the system.
        Image selectedImage = null;
        while (selectedImage == null) {
            System.out.print("Select a new Image by number for the banner: ");
            try {
                int imageChoice = Integer.parseInt(scanner.nextLine());
                if (imageChoice > 0 && imageChoice <= availableImages.size()) {
                    selectedImage = availableImages.get(imageChoice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number within the range.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println("Selected new Image: " + selectedImage.getUrl());

        // Step 6: Check the characteristics of the inserted and asks for confirmation of the change of the banner.
        // In the event that the inserted image is not valid, enable the use case Errored.
        if (!selectedImage.isValid()) {
            handleErroredUseCase("Selected image is not valid. Details: " + selectedImage);
            return; // Exit the use case if image is invalid
        }

        System.out.println("\n--- Confirmation Required ---");
        System.out.println("You are about to change the image for Banner: " + selectedBanner.getName());
        System.out.println("From: " + selectedBanner.getImage().getUrl());
        System.out.println("To: " + selectedImage.getUrl());
        System.out.print("Confirm change? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (!"yes".equalsIgnoreCase(confirmation)) {
            System.out.println("Banner modification cancelled by user.");
            return;
        }

        // Step 7: Confirmation of the transaction change.
        // Step 8: Bookmark this new image for the selected banner.
        // This is handled by the updateBannerImage method in DataStore.
        try {
            // Simulate potential server interruption (e.g., ETOUR)
            if (Math.random() < 0.1) { // 10% chance of interruption
                throw new RuntimeException("Interruption of the connection to the server ETOUR.");
            }

            boolean success = dataStore.updateBannerImage(selectedBanner.getId(), selectedImage);

            if (success) {
                System.out.println("\n--- SUCCESS ---");
                System.out.println("Banner '" + selectedBanner.getName() + "' successfully modified.");
                System.out.println("New image for banner: " + selectedBanner.getImage().getUrl());
                // Exit conditions: The system shall notify the successful modification of the banner.
            } else {
                handleErroredUseCase("Failed to update banner image in the data store. Banner ID: " + selectedBanner.getId());
            }
        } catch (RuntimeException e) {
            // Interruption of the connection to the server ETOUR.
            handleErroredUseCase("Server connection interrupted: " + e.getMessage());
        }
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

public class ModifyBanner {
    public static void main(String[] args) {
        // Initialize the data store with some dummy data
        DataStore dataStore = new DataStore();
        // Create an agency operator
        AgencyOperator agencyOperator = new AgencyOperator("AgencyUser1");
        // Create the ad management system
        AdManagementSystem system = new AdManagementSystem(dataStore);

        try {
            // Entry Operator conditions: The agency has logged.
            agencyOperator.login();

            // Execute the ModifyBanner use case
            system.modifyBanner(agencyOperator);

        } finally {
            // Ensure scanner is closed
            system.closeScanner();
            // Log out the operator
            agencyOperator.logout();
        }
    }
}