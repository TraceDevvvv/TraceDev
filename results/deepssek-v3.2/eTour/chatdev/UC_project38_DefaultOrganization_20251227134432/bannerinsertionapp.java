/**
 * Main GUI application for banner insertion
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image; // Import for Image class used in scaling
public class BannerInsertionApp extends JFrame {
    private RestaurantPoint restaurantPoint;
    private BannerService bannerService;
    private JLabel statusLabel;
    private JLabel bannerCountLabel;
    private JLabel imagePreviewLabel;
    private JButton selectImageButton;
    private JButton insertButton;
    private JButton authenticateButton;
    private JButton simulateErrorButton;
    private File selectedImageFile;
    public BannerInsertionApp() {
        // Initialize restaurant point and service
        restaurantPoint = new RestaurantPoint(1, "Main Restaurant Point");
        bannerService = new BannerService(restaurantPoint);
        // Setup window
        setTitle("Banner Insertion System - Restaurant Point Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());
        // Create components
        createGUI();
        // Center window on screen
        setLocationRelativeTo(null);
    }
    private void createGUI() {
        // North panel - Title and authentication
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Banner Insertion System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel authPanel = new JPanel(new FlowLayout());
        JLabel authLabel = new JLabel("Authentication Status: ");
        JLabel authStatusLabel = new JLabel("Not Authenticated");
        authStatusLabel.setForeground(Color.RED);
        authenticateButton = new JButton("Authenticate");
        authenticateButton.addActionListener(e -> authenticateOperator());
        authPanel.add(authLabel);
        authPanel.add(authStatusLabel);
        authPanel.add(authenticateButton);
        northPanel.add(authPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.NORTH);
        // Center panel - Image selection and preview
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Banner Image Selection"));
        JPanel imageSelectionPanel = new JPanel(new FlowLayout());
        selectImageButton = new JButton("Select Image File");
        selectImageButton.addActionListener(e -> selectImageFile());
        imageSelectionPanel.add(selectImageButton);
        imagePreviewLabel = new JLabel("No image selected", JLabel.CENTER);
        imagePreviewLabel.setPreferredSize(new Dimension(300, 200));
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(imageSelectionPanel, BorderLayout.NORTH);
        centerPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        // South panel - Status, controls, and banner count
        JPanel southPanel = new JPanel(new BorderLayout());
        // Banner count panel
        JPanel countPanel = new JPanel();
        bannerCountLabel = new JLabel("Banners: 0/" + restaurantPoint.getMaxBanners());
        countPanel.add(bannerCountLabel);
        // Control buttons panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        insertButton = new JButton("Insert Banner");
        insertButton.setEnabled(false);
        insertButton.addActionListener(e -> insertBanner());
        simulateErrorButton = new JButton("Simulate Server Disconnect");
        simulateErrorButton.addActionListener(e -> toggleServerConnection());
        controlPanel.add(insertButton);
        controlPanel.add(simulateErrorButton);
        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Ready to begin banner insertion process");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        southPanel.add(countPanel, BorderLayout.NORTH);
        southPanel.add(controlPanel, BorderLayout.CENTER);
        southPanel.add(statusPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
        // Update UI based on authentication status
        updateAuthenticationStatus();
    }
    private void authenticateOperator() {
        // Show authentication dialog
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPanel authPanel = new JPanel(new GridLayout(2, 2));
        authPanel.add(new JLabel("Username:"));
        authPanel.add(usernameField);
        authPanel.add(new JLabel("Password:"));
        authPanel.add(passwordField);
        int result = JOptionPane.showConfirmDialog(
            this, authPanel, "Operator Authentication", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (restaurantPoint.authenticate(username, password)) {
                JOptionPane.showMessageDialog(this, 
                    "Authentication successful!\nThe Point Of Restaurant Operator has successfully authenticated to the system.", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText("Authentication successful. Please select an image.");
                // Step 1 completed: Operator has authenticated. Next step is image selection.
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid credentials. Please try again.", 
                    "Authentication Failed", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Authentication failed. Please try again.");
            }
            updateAuthenticationStatus();
        }
    }
    private void updateAuthenticationStatus() {
        // Update UI based on authentication status
        boolean authenticated = restaurantPoint.isAuthenticated();
        insertButton.setEnabled(authenticated && selectedImageFile != null);
        updateBannerCount();
    }
    private void selectImageFile() {
        // Step 2: Displays a form for the selection of an image.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Banner Image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Set file filter for images
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || 
                       name.endsWith(".png") || name.endsWith(".gif");
            }
            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            // Step 3: Select an image and sends the request for entering the system.
            // Validate image immediately (part of Step 4)
            ImageValidator.ValidationResult validation = 
                ImageValidator.validateImage(selectedImageFile);
            if (validation.isValid()) {
                // Display image preview
                BufferedImage originalImage = validation.getImage();
                if (originalImage != null) {
                    // Scale image for preview
                    Image scaledImage = originalImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    imagePreviewLabel.setIcon(icon);
                    imagePreviewLabel.setText("");
                    statusLabel.setText("Image selected: " + selectedImageFile.getName());
                    insertButton.setEnabled(restaurantPoint.isAuthenticated());
                } else {
                    // Handle case where image is null despite validation passing
                    selectedImageFile = null;
                    imagePreviewLabel.setIcon(null);
                    imagePreviewLabel.setText("Invalid Image");
                    statusLabel.setText("Image could not be loaded");
                    JOptionPane.showMessageDialog(this, 
                        "Image could not be loaded", 
                        "Image Loading Error", JOptionPane.ERROR_MESSAGE);
                    insertButton.setEnabled(false);
                }
            } else {
                selectedImageFile = null;
                imagePreviewLabel.setIcon(null);
                imagePreviewLabel.setText("Invalid Image");
                statusLabel.setText("Invalid image: " + validation.getMessage());
                JOptionPane.showMessageDialog(this, 
                    "Invalid image: " + validation.getMessage(), 
                    "Image Validation Error", JOptionPane.ERROR_MESSAGE);
                insertButton.setEnabled(false);
            }
        }
    }
    private void insertBanner() {
        if (selectedImageFile == null) {
            JOptionPane.showMessageDialog(this, 
                "Please select an image first", 
                "No Image Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Process banner insertion through service
        // Steps 4-6 are handled in BannerService.insertBanner()
        BannerService.InsertionResult result = bannerService.insertBanner(selectedImageFile);
        // Display result (notification about the insertion of new banner)
        if (result.isSuccess()) {
            JOptionPane.showMessageDialog(this, result.getMessage(), 
                "Banner Insertion Successful", JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Banner inserted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, result.getMessage(), 
                "Insertion Failed", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Insertion failed: " + result.getMessage());
        }
        // Reset selection
        selectedImageFile = null;
        imagePreviewLabel.setIcon(null);
        imagePreviewLabel.setText("No image selected");
        insertButton.setEnabled(false);
        // Update banner count
        updateBannerCount();
    }
    private void updateBannerCount() {
        int current = restaurantPoint.getBannerCount();
        int max = restaurantPoint.getMaxBanners();
        bannerCountLabel.setText("Banners: " + current + "/" + max);
        // Update color based on count
        if (current >= max) {
            bannerCountLabel.setForeground(Color.RED);
            statusLabel.setText("Maximum banners reached (" + max + ")");
        } else if (current >= max * 0.8) {
            bannerCountLabel.setForeground(Color.ORANGE);
        } else {
            bannerCountLabel.setForeground(Color.BLACK);
        }
    }
    private void toggleServerConnection() {
        // Simulate interruption of the connection to the server ETOUR
        boolean currentlyConnected = bannerService.isServerConnected();
        bannerService.setServerConnected(!currentlyConnected);
        if (currentlyConnected) {
            simulateErrorButton.setText("Reconnect Server");
            statusLabel.setText("Server connection interrupted (ETOUR server disconnected)");
            JOptionPane.showMessageDialog(this, 
                "Simulated server connection interruption to ETOUR", 
                "Server Disconnected", JOptionPane.WARNING_MESSAGE);
        } else {
            simulateErrorButton.setText("Simulate Server Disconnect");
            statusLabel.setText("Server connection restored");
            JOptionPane.showMessageDialog(this, 
                "Server connection to ETOUR restored", 
                "Server Reconnected", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}