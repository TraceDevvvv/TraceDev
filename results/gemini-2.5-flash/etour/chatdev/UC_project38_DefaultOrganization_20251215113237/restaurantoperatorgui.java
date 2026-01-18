/*
 * The main GUI for the Restaurant Operator.
 * It provides an interface for the operator to interact with banner management features.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class RestaurantOperatorGUI extends JFrame {
    private BannerService bannerService;
    private JLabel statusLabel;
    private JTextArea bannerListArea;
    /**
     * Constructs the main GUI for the Restaurant Operator.
     * @param bannerService The BannerService instance to handle banner operations.
     */
    public RestaurantOperatorGUI(BannerService bannerService) {
        this.bannerService = bannerService;
        initializeGUI();
        updateBannerListDisplay(); // Display initial banners
    }
    /**
     * Initializes the JFrame and its components.
     */
    private void initializeGUI() {
        setTitle("Restaurant Banner Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Authenticated message (Entry Condition: Operator successfully authenticated)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel authLabel = new JLabel("<html><b>Welcome, Authenticated Restaurant Operator!</b><bR>Manage your restaurant banners below.</html>");
        topPanel.add(authLabel);
        add(topPanel, BorderLayout.NORTH);
        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Panel for actions (like inserting banners)
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton insertBannerButton = new JButton("1. Insert New Banner");
        insertBannerButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        insertBannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertBannerAction(); // Trigger the banner insertion flow
            }
        });
        actionPanel.add(insertBannerButton);
        mainPanel.add(actionPanel, BorderLayout.NORTH);
        // Panel for displaying current banners
        JPanel displayPanel = new JPanel(new BorderLayout());
        // Encapsulation fix: Use method from BannerService to get restaurant name
        displayPanel.setBorder(BorderFactory.createTitledBorder("Current Banners for " + bannerService.getRestaurantName()));
        bannerListArea = new JTextArea();
        bannerListArea.setEditable(false);
        bannerListArea.setLineWrap(true);
        bannerListArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(bannerListArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(displayPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        // Status bar at the bottom
        statusLabel = new JLabel("Ready.");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Handles the action of inserting a new banner, following the use case flow.
     */
    private void insertBannerAction() {
        statusLabel.setText("2. Displaying form for image selection...");
        // Step 2 & 3: Display form for selection, select image and send request
        ImageSelectionDialog dialog = new ImageSelectionDialog(this);
        String selectedImagePath = dialog.showDialog(); // This blocks until dialog is closed
        if (selectedImagePath == null) {
            statusLabel.setText("Operation cancelled: No image selected.");
            JOptionPane.showMessageDialog(this, "Banner insertion cancelled by user.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            return; // Exit condition: Operator cancels
        }
        statusLabel.setText("3. Image selected: " + selectedImagePath + ". Processing request...");
        // Step 4-6: Check characteristics, confirm, remember banner
        BannerService.BannerInsertionResult result = bannerService.insertBanner(selectedImagePath);
        // Handle the result of the banner insertion operation
        switch (result) {
            case SUCCESS:
                statusLabel.setText("SUCCESS: New banner inserted! (" + selectedImagePath + ")");
                JOptionPane.showMessageDialog(this, "Successfully inserted new banner!", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateBannerListDisplay(); // Update display for step 6
                break;
            case CANCELLED_BY_USER:
                statusLabel.setText("Operation cancelled by user.");
                JOptionPane.showMessageDialog(this, "Banner insertion cancelled by user.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                break;
            case INVALID_IMAGE_CHARACTERISTICS:
                statusLabel.setText("ERROR: Invalid image characteristics. " + selectedImagePath);
                JOptionPane.showMessageDialog(this, "The selected image is not valid (e.g., wrong file type, corrupted, incorrect dimensions, or too large).\nUse case 'Errored' enabled.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case MAX_BANNERS_EXCEEDED:
                // Encapsulation fix: Use method from BannerService to get max banners
                statusLabel.setText("ERROR: Maximum number of banners reached for this restaurant (" + bannerService.getMaxRestaurantBanners() + ").");
                // Encapsulation fix: Use method from BannerService to get max banners
                JOptionPane.showMessageDialog(this, "Cannot add more banners. Maximum allowed banners (" + bannerService.getMaxRestaurantBanners() + ") already reached.", "Error: Limit Exceeded", JOptionPane.WARNING_MESSAGE);
                break;
            case CONNECTION_ERROR:
                statusLabel.setText("ERROR: Connection to server interrupted (ETOUR). Please try again.");
                JOptionPane.showMessageDialog(this, "Connection interrupted (ETOUR). Please check your network and try again.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                break;
            case UNKNOWN_ERROR:
            default:
                statusLabel.setText("ERROR: An unknown error occurred during banner insertion.");
                JOptionPane.showMessageDialog(this, "An unexpected error occurred. Please contact support.", "Unknown Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    /**
     * Updates the JTextArea to display the current list of banners associated with the restaurant.
     */
    private void updateBannerListDisplay() {
        java.util.List<Banner> banners = bannerService.getRestaurantBanners();
        if (banners.isEmpty()) {
            // Encapsulation fix: Use method from BannerService to get restaurant name
            bannerListArea.setText("No banners currently associated with " + bannerService.getRestaurantName() + ".");
        } else {
            StringBuilder sb = new StringBuilder();
            // Encapsulation fix: Use methods from BannerService for restaurant name, banner count, and max banners
            sb.append("Banners for ").append(bannerService.getRestaurantName()).append(" (")
              .append(bannerService.getRestaurantBannerCount()).append("/").append(bannerService.getMaxRestaurantBanners()).append("):\n");
            for (int i = 0; i < banners.size(); i++) {
                Banner banner = banners.get(i);
                sb.append((i + 1)).append(". ID: ").append(banner.getId().substring(0, 8)) // Shorten ID for display
                  .append(", Path: ").append(banner.getImagePath()).append("\n");
            }
            bannerListArea.setText(sb.toString());
        }
    }
}