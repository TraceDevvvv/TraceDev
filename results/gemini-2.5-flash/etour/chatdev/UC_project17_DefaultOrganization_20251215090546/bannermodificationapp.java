'''
Main GUI application for modifying banner ads.
This class provides the user interface for selecting a Point of Interest,
viewing its associated banners, selecting a banner, choosing a new image,
validating the image, and confirming the change.
It interacts with BannerManager and PoIManager for data operations
and ImageValidator for image checks.
'''
package gui;
import models.Banner;
import models.PointOfInterest;
import managers.BannerManager;
import managers.PoIManager;
import utils.ImageValidator;
import exceptions.EtourConnectionException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
/**
 * Main GUI application for modifying banner ads.
 * This class provides the user interface for selecting a Point of Interest,
 * viewing its associated banners, selecting a banner, choosing a new image,
 * validating the image, and confirming the change.
 * It interacts with BannerManager and PoIManager for data operations
 * and ImageValidator for image checks.
 */
public class BannerModificationApp extends JFrame {
    // --- Backend Managers and Utilities ---
    private PoIManager poiManager;        // Manages Point of Interest data
    private BannerManager bannerManager;  // Manages Banner data
    private ImageValidator imageValidator; // Validates selected image files
    // --- UI Components ---
    private JComboBox<PointOfInterest> poiComboBox;        // Dropdown for selecting Points of Interest
    private JList<Banner> bannerList;                      // List for displaying and selecting banners
    private DefaultListModel<Banner> bannerListModel;      // Model for the banner JList
    private JButton selectImageButton;                     // Button to open file chooser
    private JLabel selectedImagePathLabel;                 // Displays the path of the selected image file
    private JLabel imagePreviewLabel;                      // Displays a preview of the selected image
    private JButton changeBannerButton;                    // Button to initiate the banner image change
    private JLabel statusLabel;                           // Displays application status and feedback
    private String currentSelectedImagePath; // Stores the absolute path of the image selected by the user
    /**
     * Constructor for the BannerModificationApp.
     * Initializes all managers and utility classes, then sets up the graphical user interface.
     */
    public BannerModificationApp() {
        // Initialize backend managers and utility classes
        poiManager = new PoIManager();
        bannerManager = new BannerManager();
        imageValidator = new ImageValidator();
        // Initialize and lay out the GUI components
        initComponents();
        // Load initial data into the PoI dropdown
        loadPointsOfInterest();
    }
    /**
     * Initializes all UI components, sets up their properties, and arranges them
     * using various layout managers. This forms the visual structure of the application.
     */
    private void initComponents() {
        setTitle("Banner Modification System"); // Set the window title
        setSize(800, 600); // Set initial window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define close operation
        setLocationRelativeTo(null); // Center the window on the screen
        // Main panel using BorderLayout for overall structure
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // 10px gaps
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        // --- Top Panel: Point of Interest Selection ---
        JPanel poiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout for simple horizontal arrangement
        poiPanel.setBorder(BorderFactory.createTitledBorder("1. Select Point of Interest"));
        poiComboBox = new JComboBox<>();
        poiComboBox.setPreferredSize(new Dimension(300, 30)); // Fixed size for aesthetics
        // Action listener to load banners when a new POI is selected
        poiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBannersForSelectedPoi();
            }
        });
        poiPanel.add(new JLabel("Point of Interest: "));
        poiPanel.add(poiComboBox);
        mainPanel.add(poiPanel, BorderLayout.NORTH);
        // --- Center Panel: Banner List and Image Selection/Update ---
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Two columns, 10px horizontal gap
        // Left sub-panel: Banner List
        JPanel bannerListPanel = new JPanel(new BorderLayout());
        bannerListPanel.setBorder(BorderFactory.createTitledBorder("2. Select Banner for Modification"));
        bannerListModel = new DefaultListModel<>(); // Model to hold Banner objects for JList
        bannerList = new JList<>(bannerListModel);
        bannerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one banner can be selected
        // Listener for banner selection changes
        bannerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Ensure this event fires only once per selection and when an item is actually selected
                if (!e.getValueIsAdjusting() && bannerList.getSelectedValue() != null) {
                    // Reset image selection when a new banner is chosen
                    currentSelectedImagePath = null;
                    selectedImagePathLabel.setText("Selected Image Path: No file chosen.");
                    imagePreviewLabel.setIcon(null); // Clear image preview on new banner selection
                    statusLabel.setText("Status: Banner '" + bannerList.getSelectedValue().getName() + "' selected.");
                }
            }
        });
        JScrollPane bannerListScrollPane = new JScrollPane(bannerList); // Make list scrollable
        bannerListPanel.add(bannerListScrollPane, BorderLayout.CENTER);
        centerPanel.add(bannerListPanel);
        // Right sub-panel: Image Selection and Actions
        JPanel imageActionPanel = new JPanel(new GridBagLayout()); // GridBagLayout for flexible component placement
        imageActionPanel.setBorder(BorderFactory.createTitledBorder("3. Select New Image and Confirm Change"));
        GridBagConstraints gbc = new GridBagConstraints(); // Constraints for GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components will fill their display area horizontally
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.gridwidth = 2; // Span two columns
        selectedImagePathLabel = new JLabel("Selected Image Path: No file chosen.");
        imageActionPanel.add(selectedImagePathLabel, gbc);
        gbc.gridy = 1; // Position the preview below the path label
        imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(250, 150)); // Set a preferred size for the preview area
        imagePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add a border for visibility
        imageActionPanel.add(imagePreviewLabel, gbc);
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Adjust row for buttons
        gbc.gridwidth = 1; // Span one column
        selectImageButton = new JButton("Select Image File...");
        // Action listener to open file chooser
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        imageActionPanel.add(selectImageButton, gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 2; // Row 2
        changeBannerButton = new JButton("Change Banner Image");
        // Action listener to confirm and apply the image change
        changeBannerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmImageChange();
            }
        });
        imageActionPanel.add(changeBannerButton, gbc);
        centerPanel.add(imageActionPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // --- Bottom Panel: Status Bar ---
        statusLabel = new JLabel("Status: Ready."); // Status bar at the bottom
        statusLabel.setBorder(BorderFactory.createEtchedBorder()); // Aesthetic border for status bar
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        add(mainPanel); // Add the main panel to the JFrame
    }
    /**
     * Loads all available Points of Interest into the PoI JComboBox.
     * This corresponds to use case step 1.
     */
    private void loadPointsOfInterest() {
        List<PointOfInterest> pois = poiManager.getAllPointsOfInterest();
        if (pois.isEmpty()) {
            // Handle case where no PoIs are found
            poiComboBox.addItem(new PointOfInterest(-1, "No Points of Interest Found"));
            poiComboBox.setEnabled(false); // Disable combo box if no PoIs
            statusLabel.setText("Status: No Points of Interest available.");
        } else {
            for (PointOfInterest poi : pois) {
                poiComboBox.addItem(poi);
            }
            poiComboBox.setSelectedIndex(0); // Select the first PoI by default
            poiComboBox.setEnabled(true);
            loadBannersForSelectedPoi(); // Automatically load banners for the initial selection
        }
    }
    /**
     * Loads banners associated with the currently selected Point of Interest.
     * Displays them in the banner JList. Handles potential ETOUR server connection errors.
     * This corresponds to use case step 2.
     */
    private void loadBannersForSelectedPoi() {
        PointOfInterest selectedPoi = (PointOfInterest) poiComboBox.getSelectedItem();
        // Handle cases where no PoI is selected or a placeholder is present
        if (selectedPoi == null || selectedPoi.getId() == -1) {
            bannerListModel.clear(); // Clear banner list
            statusLabel.setText("Status: Please select a valid Point of Interest.");
            return;
        }
        bannerListModel.clear(); // Clear existing banners before loading new ones
        try {
            List<Banner> banners = bannerManager.getBannersForPoi(selectedPoi.getId());
            if (banners.isEmpty()) {
                statusLabel.setText("Status: No banners found for " + selectedPoi.getName() + ".");
            } else {
                for (Banner banner : banners) {
                    bannerListModel.addElement(banner); // Add each banner to the list model
                }
                statusLabel.setText("Status: Banners loaded for " + selectedPoi.getName() + ".");
            }
        } catch (EtourConnectionException e) {
            // "Interruption of the connection to the server ETOUR." exit condition
            JOptionPane.showMessageDialog(this,
                    "Connection Error: " + e.getMessage() + "\nCannot retrieve banners. Please try again.",
                    "ETOUR Server Error",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Status: ETOUR connection interrupted while loading banners.");
        }
    }
    /**
     * Opens a file chooser dialog, allowing the user to select an image file.
     * Updates the UI to show the selected file's path and a preview of the image.
     * This corresponds to use case step 4 and 5 (displaying form and selecting picture).
     */
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        // Set the initial directory for the file chooser.
        // For demonstration, it points to the current working directory.
        // For production, you might set it to a specific upload folder.
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Select New Banner Image");
        // Apply a file filter to show only common image file types
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                // Accept directories and files with specific image extensions
                String name = f.getName().toLowerCase();
                return f.isDirectory() || name.endsWith(".jpg") ||
                       name.endsWith(".jpeg") || name.endsWith(".png") ||
                       name.endsWith(".gif");
            }
            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        int result = fileChooser.showOpenDialog(this); // Show the dialog
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            currentSelectedImagePath = selectedFile.getAbsolutePath(); // Store the absolute path
            selectedImagePathLabel.setText("Selected Image Path: " + selectedFile.getName()); // Display only file name
            statusLabel.setText("Status: Image selected: " + selectedFile.getName());
            // Load and display image preview
            try {
                ImageIcon originalIcon = new ImageIcon(currentSelectedImagePath);
                // Check if the image loaded successfully (width/height > 0)
                if (originalIcon.getImage() != null && originalIcon.getIconWidth() > 0 && originalIcon.getIconHeight() > 0) {
                    int previewWidth = imagePreviewLabel.getPreferredSize().width;
                    int previewHeight = imagePreviewLabel.getPreferredSize().height;
                    int imgWidth = originalIcon.getIconWidth();
                    int imgHeight = originalIcon.getIconHeight();
                    // Calculate scale factors
                    double scaleX = (double) previewWidth / imgWidth;
                    double scaleY = (double) previewHeight / imgHeight;
                    // Use the smaller scale factor to fit the image entirely within the preview area while maintaining aspect ratio
                    double scale = Math.min(scaleX, scaleY);
                    int scaledWidth = (int) (imgWidth * scale);
                    int scaledHeight = (int) (imgHeight * scale);
                    Image scaledImage = originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                    imagePreviewLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    imagePreviewLabel.setIcon(null); // Clear if not a valid image or load failed
                    statusLabel.setText("Status: Selected file is not a valid image or corrupted, or could not be loaded.");
                }
            } catch (Exception ex) {
                System.err.println("Error loading image preview: " + ex.getMessage());
                imagePreviewLabel.setIcon(null);
                statusLabel.setText("Status: Error loading image preview.");
            }
        } else {
            // User cancelled file selection
            currentSelectedImagePath = null;
            selectedImagePathLabel.setText("Selected Image Path: No file chosen.");
            imagePreviewLabel.setIcon(null); // Clear preview when cancelled
            statusLabel.setText("Status: Image selection cancelled.");
        }
    }
    /**
     * Handles the process of confirming and applying the image change to the selected banner.
     * This involves checking prerequisites, image validation, user confirmation, and calling
     * the BannerManager to perform the update.
     * This method covers use case steps 6, 7, and 8, and the "Errored" use case.
     */
    private void confirmImageChange() {
        PointOfInterest selectedPoi = (PointOfInterest) poiComboBox.getSelectedItem();
        Banner selectedBanner = bannerList.getSelectedValue();
        // 1. Check if a Point of Interest is selected
        if (selectedPoi == null || selectedPoi.getId() == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Point of Interest first.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            statusLabel.setText("Status: Please select a Point of Interest.");
            return;
        }
        // 2. Check if a banner is selected from the list (Use case step 3)
        if (selectedBanner == null) {
            JOptionPane.showMessageDialog(this, "Please select a banner from the list to modify.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            statusLabel.setText("Status: Please select a banner.");
            return;
        }
        // 3. Check if an image file has been selected (Use case step 5)
        if (currentSelectedImagePath == null || currentSelectedImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a new image file first.", "Image Selection Required", JOptionPane.WARNING_MESSAGE);
            statusLabel.setText("Status: Please select an image file.");
            return;
        }
        // 4. Validate the selected image characteristics (Use case step 6)
        statusLabel.setText("Status: Validating new image characteristics...");
        if (!imageValidator.isValidImage(currentSelectedImagePath)) {
            // If the image is not valid, enable the use case Errored.
            JOptionPane.showMessageDialog(this,
                    "The selected image '" + new File(currentSelectedImagePath).getName() + "' is not valid.\n" +
                    "Please select a valid image file (e.g., JPEG, PNG, GIF, max 5MB).",
                    "Image Validation Error (Errored Use Case)",
                    JOptionPane.ERROR_MESSAGE);
            imagePreviewLabel.setIcon(null); // Clear preview on validation failure
            statusLabel.setText("Status: Image validation failed (Errored).");
            return;
        }
        // 5. Ask for confirmation of the change (Use case step 6)
        int confirmResult = JOptionPane.showConfirmDialog(this,
                "You are about to change the image for banner '" + selectedBanner.getName() + "'\n" +
                "to '" + new File(currentSelectedImagePath).getName() + "'. Are you sure?",
                "Confirm Banner Image Change",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmResult == JOptionPane.YES_OPTION) {
            statusLabel.setText("Status: Sending request to update banner image...");
            try {
                // 6. Bookmark this new image for the selected banner (Use case step 8)
                boolean success = bannerManager.updateBannerImage(selectedBanner.getId(), currentSelectedImagePath);
                if (success) {
                    // "The system shall notify the successful modification of the banner." exit condition
                    JOptionPane.showMessageDialog(this,
                            "Banner '" + selectedBanner.getName() + "' image successfully updated!",
                            "Modification Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    statusLabel.setText("Status: Banner modification successful.");
                    // Refresh the banner list to reflect the updated image path.
                    // This involves reloading all banners for the current PoI.
                    int selectedBannerIndex = bannerList.getSelectedIndex(); // Remember selected index
                    loadBannersForSelectedPoi();
                    // Attempt to re-select the banner if it still exists in the list
                    if (selectedBannerIndex >= 0 && selectedBannerIndex < bannerListModel.size()) {
                        bannerList.setSelectedIndex(selectedBannerIndex);
                    }
                    // Clear the current image path and preview after successful update
                    currentSelectedImagePath = null;
                    selectedImagePathLabel.setText("Selected Image Path: No file chosen.");
                    imagePreviewLabel.setIcon(null);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Failed to update banner '" + selectedBanner.getName() + "'. Banner not found or update failed.",
                            "Modification Failed",
                            JOptionPane.ERROR_MESSAGE);
                    statusLabel.setText("Status: Banner modification failed.");
                }
            } catch (EtourConnectionException e) {
                // "Interruption of the connection to the server ETOUR." exit condition
                JOptionPane.showMessageDialog(this,
                        "ETOUR Server Connection Error: " + e.getMessage() + "\nFailed to update banner. Please try again.",
                        "ETOUR Server Error",
                        JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("Status: ETOUR connection interrupted during update.");
            }
        } else {
            statusLabel.setText("Status: Banner image change cancelled by user.");
        }
    }
    /**
     * Main method to run the application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing. It also provides an initial setup check for the 'images' directory.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an 'images' directory in the project root if it doesn't exist.
        // This is necessary for the sample banner image paths to be valid initially.
        File imagesDir = new File("images");
        if (!imagesDir.exists()) {
            boolean created = imagesDir.mkdirs();
            if (created) {
                System.out.println("Created 'images' directory in the project root.");
                System.out.println("Please place some dummy image files (e.g., .jpg, .png, .gif) into this 'images' directory.");
                System.out.println("For example, create files named banner1_italian.jpg, banner2_italian.png, etc., to match initial data.");
            } else {
                System.err.println("Failed to create 'images' directory. Please create it manually if needed for sample data.");
            }
        }
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            new BannerModificationApp().setVisible(true); // Create and display the main application window
        });
    }
}