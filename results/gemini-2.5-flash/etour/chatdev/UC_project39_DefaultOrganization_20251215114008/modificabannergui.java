'''
This class implements the GUI for the MODIFICABANNER use case.
It allows a Point Of Restaurant Operator to select a banner,
choose a new image for it, validate the image, and update the banner.
'''
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon; // For displaying images
public class ModificaBannerGUI extends JFrame {
    private BannerService bannerService; // Service to handle banner operations
    private JComboBox<Banner> bannerComboBox; // For selecting banners (Step 2, 3)
    private JLabel currentBannerImageLabel; // Displays the image of the selected banner (for context)
    private JLabel imageDetailsLabel; // Displays details of the selected image for upload (Step 6)
    private JButton selectImageButton; // Button to open file chooser (Step 4, 5)
    private JButton saveButton; // Button to confirm and save changes (Step 5, 7)
    private String selectedImagePath; // Stores the path of the newly selected image file
    private Banner selectedBanner; // Holds the currently selected banner object
    /**
     * Constructor for ModificaBannerGUI.
     * Initializes the GUI components and sets up event listeners.
     * @param bannerService An instance of BannerService to interact with banner data.
     */
    public ModificaBannerGUI(BannerService bannerService) {
        this.bannerService = bannerService;
        initializeUI(); // Set up the graphical user interface
        loadBanners(); // Load initial banner data into the combo box
    }
    /**
     * Initializes all the UI components of the application window.
     */
    private void initializeUI() {
        setTitle("Modifica Banner Ad"); // Use case name hint
        setSize(800, 600); // Set initial window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLocationRelativeTo(null); // Center the window on the screen
        // Use a BorderLayout for overall layout
        setLayout(new BorderLayout(10, 10)); // 10px gaps
        // --- North Panel: Banner Selection ---
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        northPanel.setBorder(BorderFactory.createTitledBorder("1. Select Banner"));
        northPanel.add(new JLabel("Select Banner:"));
        bannerComboBox = new JComboBox<>();
        bannerComboBox.setPreferredSize(new Dimension(250, 30));
        // Add an ActionListener to handle banner selection changes (Step 3)
        bannerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedBanner = (Banner) bannerComboBox.getSelectedItem();
                displayBannerDetails(selectedBanner);
            }
        });
        northPanel.add(bannerComboBox);
        add(northPanel, BorderLayout.NORTH);
        // --- Center Panel: Banner Image Display and Image Selection ---
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Two rows for current and new image sections
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Padding
        // Current Banner Section
        JPanel currentBannerPanel = new JPanel(new BorderLayout());
        currentBannerPanel.setBorder(BorderFactory.createTitledBorder("Current Banner Image"));
        currentBannerImageLabel = new JLabel("No banner selected", SwingConstants.CENTER);
        currentBannerImageLabel.setPreferredSize(new Dimension(700, 200)); // Fixed size for image display
        currentBannerImageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        currentBannerPanel.add(currentBannerImageLabel, BorderLayout.CENTER);
        centerPanel.add(currentBannerPanel);
        // New Image Selection Section
        JPanel newImagePanel = new JPanel(new BorderLayout());
        newImagePanel.setBorder(BorderFactory.createTitledBorder("2. Select New Image (PNG, JPG, GIF)"));
        JPanel newImageControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        selectImageButton = new JButton("Choose Image File..."); // Button for image selection (Step 4, 5)
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImageFile();
            }
        });
        newImageControls.add(selectImageButton);
        imageDetailsLabel = new JLabel("No image selected."); // To show path and details (Step 6)
        newImageControls.add(imageDetailsLabel);
        newImagePanel.add(newImageControls, BorderLayout.NORTH);
        centerPanel.add(newImagePanel);
        add(centerPanel, BorderLayout.CENTER);
        // --- South Panel: Action Buttons ---
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        saveButton = new JButton("Save Changes"); // Button to save (Step 5, 7)
        saveButton.setEnabled(false); // Disabled until an image is selected and a banner is chosen
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptImageUpdate();
            }
        });
        // Add a "Cancel" button for the exit condition 'Point Of Operator Restaurant cancels the operation'.
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simply close the window or reset the state
                JOptionPane.showMessageDialog(ModificaBannerGUI.this,
                                              "Operation cancelled by operator.",
                                              "Operation Cancelled",
                                              JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the application window
            }
        });
        southPanel.add(cancelButton);
        southPanel.add(saveButton);
        add(southPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads the list of banners from the BannerService and populates the JComboBox.
     * (Corresponds to Step 2: View the list of banners associated with the point of rest.)
     */
    private void loadBanners() {
        bannerComboBox.removeAllItems(); // Clear existing items
        java.util.List<Banner> banners = bannerService.getAllBanners(); // Fetch banners
        if (banners.isEmpty()) {
            bannerComboBox.addItem(new Banner("", "No Banners Available", ""));
            bannerComboBox.setEnabled(false);
            selectImageButton.setEnabled(false);
            saveButton.setEnabled(false);
        } else {
            for (Banner banner : banners) {
                bannerComboBox.addItem(banner);
            }
            bannerComboBox.setEnabled(true);
            selectImageButton.setEnabled(true);
            bannerComboBox.setSelectedIndex(0); // Select the first banner by default
        }
    }
    /**
     * Displays the details of the currently selected banner, including its image.
     * @param banner The Banner object whose details are to be displayed.
     * (Corresponds to Step 3, contextual display)
     */
    private void displayBannerDetails(Banner banner) {
        if (banner != null && !banner.getId().isEmpty()) {
            // Load and scale the image for display
            // Create an ImageValidator instance to check the current image path validity
            ImageValidator validator = new ImageValidator();
            // Display the current banner's image
            if (banner.getImagePath() != null && !banner.getImagePath().isEmpty() && validator.isValidImage(banner.getImagePath())) {
                ImageIcon imageIcon = new ImageIcon(banner.getImagePath());
                Image image = imageIcon.getImage();
                // Get current size of the label to scale the image
                int labelWidth = currentBannerImageLabel.getWidth();
                int labelHeight = currentBannerImageLabel.getHeight();
                // If label isn't yet rendered (width/height are 0), use preferred size or a default for scaling
                if (labelWidth <= 0 || labelHeight <= 0) { 
                    labelWidth = currentBannerImageLabel.getPreferredSize().width;
                    labelHeight = currentBannerImageLabel.getPreferredSize().height;
                    // Fallback if preferred size also isn't set properly
                    if (labelWidth <= 0 || labelHeight <= 0) {
                        labelWidth = 700; // Default size from setPreferredSize
                        labelHeight = 200;
                    }
                }
                if (image != null && image.getWidth(null) > 0 && image.getHeight(null) > 0) {
                    // Calculate scaled dimensions to maintain aspect ratio
                    int originalWidth = image.getWidth(null);
                    int originalHeight = image.getHeight(null);
                    double scaleX = (double) labelWidth / originalWidth;
                    double scaleY = (double) labelHeight / originalHeight;
                    double scale = Math.min(scaleX, scaleY); // Use the smaller scale factor
                    int scaledWidth = (int) (originalWidth * scale);
                    int scaledHeight = (int) (originalHeight * scale);
                    Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
                    currentBannerImageLabel.setIcon(new ImageIcon(scaledImage));
                    currentBannerImageLabel.setText(""); // Clear text if image is displayed
                } else {
                    currentBannerImageLabel.setIcon(null); // No valid image to scale
                    currentBannerImageLabel.setText("Failed to load or scale image for: " + banner.getImagePath());
                }
            } else {
                currentBannerImageLabel.setIcon(null); // No image
                currentBannerImageLabel.setText("No valid current image found for " + banner.getName() + " or path is invalid/corrupt: " + banner.getImagePath());
            }
            // Enable save button if a new image has also been selected
            saveButton.setEnabled(selectedImagePath != null && !selectedImagePath.isEmpty());
        } else {
            currentBannerImageLabel.setIcon(null);
            currentBannerImageLabel.setText("Please select a banner.");
            saveButton.setEnabled(false);
            imageDetailsLabel.setText("No image selected.");
            selectedImagePath = null; // Clear previous selection
        }
    }
    /**
     * Opens a file chooser dialog for the user to select an image file.
     * (Corresponds to Step 4: Displays a form for the selection of an image,
     * and Step 5: Select a picture.)
     */
    private void chooseImageFile() {
        JFileChooser fileChooser = new JFileChooser("."); // Start in current directory
        fileChooser.setDialogTitle("Select New Banner Image");
        // Filter for common image file types
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files (*.png, *.jpg, *.jpeg, *.gif)", "png", "jpg", "jpeg", "gif");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            String tempSelectedImagePath = fileToLoad.getAbsolutePath(); // Use a temporary variable for validation
            // Display characteristics of the newly selected image (Step 6)
            ImageValidator validator = new ImageValidator();
            if (validator.isValidImage(tempSelectedImagePath)) {
                selectedImagePath = tempSelectedImagePath; // Only set if valid
                imageDetailsLabel.setText("Selected: " + fileToLoad.getName() + " (" + validator.getImageDetails(selectedImagePath) + ")");
                saveButton.setEnabled(selectedBanner != null && !selectedBanner.getId().isEmpty());
            } else {
                imageDetailsLabel.setText("Invalid image selected: " + fileToLoad.getName() + ". Please choose a valid image.");
                selectedImagePath = null; // Invalidate selection
                saveButton.setEnabled(false); // Disable save if image is invalid
                JOptionPane.showMessageDialog(this,
                                              "The selected file is not a valid image or does not meet requirements. Please try again.",
                                              "Invalid Image",
                                              JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // User cancelled the file selection (Exit condition: Operator cancels the operation).
            // Do not reset selectedImagePath if they cancelled the file *dialog*.
            // The previously valid selection (if any) should persist until a new one is successfully chosen.
            System.out.println("Image selection cancelled by user.");
            // If selectedImagePath was already valid, keep it; if it was null, it remains null.
            // The save button state will be correct based on `selectedImagePath` and `selectedBanner`.
        }
    }
    /**
     * Attempts to update the banner image using the BannerService.
     * This involves confirming the change with the user and handling the service response.
     * (Corresponds to Step 5: send the request, Step 6: check characteristics & ask for confirmation,
     * Step 7: Confirmation of the transaction change, Step 8: Bookmark this new image.)
     */
    private void attemptImageUpdate() {
        if (selectedBanner == null || selectedImagePath == null || selectedImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select a banner and a valid new image first.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Step 6: Check characteristics (already done in chooseImageFile) and asks for confirmation.
        // Display characteristics again for the final confirmation dialog
        ImageValidator validator = new ImageValidator();
        String currentDetails = "N/A";
        // Check if the current banner's image path is valid before trying to get details
        if (selectedBanner.getImagePath() != null && !selectedBanner.getImagePath().isEmpty() && validator.isValidImage(selectedBanner.getImagePath())) {
            currentDetails = validator.getImageDetails(selectedBanner.getImagePath());
        } else {
            currentDetails = "Invalid or Not Found: " + selectedBanner.getImagePath();
        }
        String newDetails = validator.getImageDetails(selectedImagePath);
        int confirm = JOptionPane.showConfirmDialog(this,
                "<html><body><p>You are about to change the image for banner <b>" + selectedBanner.getName() + "</b>.</p>" +
                "<p><b>Current Image:</b></p>" +
                "<p>" + selectedBanner.getImagePath() + " (" + currentDetails + ")" + "</p>" +
                "<p><b>New Image:</b></p>" +
                "<p>" + selectedImagePath + " (" + newDetails + ")" + "</p>" +
                "<p>Do you confirm this change?</p></body></html>",
                "Confirm Banner Image Change",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // User confirmed the change (Step 7)
            // Send the request to change the system (Step 5 part 2)
            BannerService.BannerUpdateResult result = bannerService.updateBannerImage(selectedBanner.getId(), selectedImagePath);
            if (result.isSuccess()) {
                // Exit condition: The system shall notify the successful modification of the banner.
                JOptionPane.showMessageDialog(this,
                        "Successfully updated banner '" + selectedBanner.getName() + "'.\n" + result.getMessage(),
                        "Update Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                // Refresh the display to show the new image and details
                displayBannerDetails(selectedBanner);
                // Reset the new image path selection after successful update
                selectedImagePath = null;
                imageDetailsLabel.setText("No image selected.");
                saveButton.setEnabled(false); // Disable save button after successful update
            } else {
                // If update failed (e.g., due to simulated ETOUR connection interruption or deeper validation failure)
                // This covers the "Interruption of the connection to the server ETOUR" exit condition
                 JOptionPane.showMessageDialog(this,
                        "Failed to update banner '" + selectedBanner.getName() + "'.\n" + result.getMessage(),
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                // If validation failed within BannerService, it implies more than just client-side check.
                // The use case indicates "In the event that the inserted image is not valid, enable the use case Errored."
                // This means the error message itself acts as the trigger for the 'Errored' use case.
                // The selectedImagePath and imageDetailsLabel should remain as they were, allowing the user to try again.
            }
        } else {
            // User cancelled the confirmation (Exit condition: Point Of Operator Restaurant cancels the operation.)
            JOptionPane.showMessageDialog(this,
                    "Banner image update cancelled.",
                    "Operation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            // Retain the previously selected new image path and details.
            // The save button should remain enabled if a banner and valid image are still selected.
            // displayBannerDetails(selectedBanner); // Do not refresh, as it would revert the visual state.
        }
    }
}