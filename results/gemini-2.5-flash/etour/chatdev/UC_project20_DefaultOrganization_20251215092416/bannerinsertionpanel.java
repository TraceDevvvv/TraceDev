'''
JPanel for inserting a new banner associated with a selected Point of Rest.
It provides controls for selecting an image, and sending the insertion request
to the service layer. Handles confirmation and error messages.
'''
package com.chatdev.bannerapp.gui;
import com.chatdev.bannerapp.model.PointOfRest;
import com.chatdev.bannerapp.service.BannerInsertionResult; // Import the new enum
import com.chatdev.bannerapp.service.BannerService;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Supplier; // Using Supplier for returning to parent view
public class BannerInsertionPanel extends JPanel {
    private BannerService bannerService;
    private PointOfRest selectedPoint;
    private JLabel pointNameLabel;
    private JTextField imagePathField;
    private JButton browseImageButton;
    private JButton confirmButton;
    private JButton cancelButton;
    // Callbacks to communicate with the parent frame (AgencyOperatorApp)
    private Supplier<Void> onOperationComplete; // Callback to return to previous panel
    private BiConsumer<String, String> errorHandler; // For displaying service errors
    /**
     * Constructs a new BannerInsertionPanel.
     *
     * @param bannerService The service layer for banner operations.
     * @param selectedPoint The PointOfRest for which the banner is being inserted.
     * @param onOperationComplete A callback function to be called when the insertion operation
     *                            is completed (success, cancel, or handled error) to return to the previous view.
     * @param errorHandler A callback function to handle errors from the service layer.
     */
    public BannerInsertionPanel(BannerService bannerService, PointOfRest selectedPoint,
                                Supplier<Void> onOperationComplete, BiConsumer<String, String> errorHandler) {
        this.bannerService = bannerService;
        this.selectedPoint = selectedPoint;
        this.onOperationComplete = onOperationComplete;
        this.errorHandler = errorHandler;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
        displayPointOfRestInfo();
    }
    /**
     * Initializes the graphical components of the panel.
     */
    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        JLabel titleLabel = new JLabel("Insert New Banner", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(titleLabel, gbc);
        // Point of Rest Name
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Selected Point of Rest:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        pointNameLabel = new JLabel();
        pointNameLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        add(pointNameLabel, gbc);
        // Image Selection Row
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Image Path:"), gbc);
        gbc.gridx = 1;
        imagePathField = new JTextField(30);
        imagePathField.setEditable(false); // User cannot type directly
        add(imagePathField, gbc);
        gbc.gridx = 2;
        browseImageButton = new JButton("Browse Image");
        browseImageButton.addActionListener(e -> selectImageFile());
        add(browseImageButton, gbc);
        // Action Buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE; // Reset fill for buttons
        gbc.anchor = GridBagConstraints.EAST; // Anchor buttons to the right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel for buttons
        confirmButton = new JButton("Confirm Insertion");
        confirmButton.addActionListener(e -> attemptBannerInsertion());
        confirmButton.setEnabled(false); // Initially disabled until image is selected
        buttonPanel.add(confirmButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancelOperation());
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);
    }
    /**
     * Displays the name and current banner count of the selected Point of Rest.
     */
    private void displayPointOfRestInfo() {
        if (selectedPoint != null) {
            pointNameLabel.setText(selectedPoint.getName() + " (ID: " + selectedPoint.getId() + ") " +
                                  "Banners: " + selectedPoint.getBanners().size() + "/" + selectedPoint.getMaxBanners());
        }
    }
    /**
     * Opens a JFileChooser to allow the user to select an image file.
     */
    private void selectImageFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Banner Image");
        // Filter for common image file types
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePathField.setText(selectedFile.getAbsolutePath());
            confirmButton.setEnabled(true); // Enable confirm button once an image is selected
        } else {
            imagePathField.setText("");
            confirmButton.setEnabled(false);
        }
    }
    /**
     * Attempts to insert the banner by calling the BannerService.
     * Handles success, maximum banner limits, invalid image errors, and service connection errors.
     */
    private void attemptBannerInsertion() {
        String imagePath = imagePathField.getText();
        if (imagePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an image file first.",
                    "Missing Image", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Ask for confirmation before insertion
        int confirmResult = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to insert this banner for " + selectedPoint.getName() + "?\n" +
                "Image: " + imagePath,
                "Confirm Banner Insertion", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            try {
                // Call the service and receive the enum result
                BannerInsertionResult serviceResult = bannerService.insertBanner(selectedPoint, imagePath);
                switch (serviceResult) {
                    case SUCCESS:
                        JOptionPane.showMessageDialog(this,
                                "Banner inserted successfully for " + selectedPoint.getName() + ".",
                                "Insertion Successful", JOptionPane.INFORMATION_MESSAGE);
                        onOperationComplete.get(); // Notify parent to go back to selection panel
                        break;
                    case INVALID_IMAGE:
                        // Specific error for image validation (use case Errored)
                        errorHandler.accept("Image Validation Error",
                                "The selected image is not valid or has unacceptable characteristics (e.g., too large). Please select a different image.");
                        // Stay on this panel to allow selecting a different image
                        break;
                    case MAX_BANNERS_REACHED:
                        JOptionPane.showMessageDialog(this,
                                "Maximum number of banners (" + selectedPoint.getMaxBanners() + ") reached for " + selectedPoint.getName() + ". Cannot add more banners.",
                                "Insertion Failed", JOptionPane.WARNING_MESSAGE);
                        onOperationComplete.get(); // Go back to selection panel, as max banners reached.
                        break;
                    case NO_POINT_SELECTED:
                        JOptionPane.showMessageDialog(this,
                                "Error: No Point of Rest was selected. This should not happen at this stage.",
                                "Internal Error", JOptionPane.ERROR_MESSAGE);
                        onOperationComplete.get(); // Go back as something went wrong with the selection.
                        break;
                    case UNKNOWN_ERROR:
                    default:
                        JOptionPane.showMessageDialog(this,
                                "An unexpected error occurred during banner insertion for " + selectedPoint.getName() + ".",
                                "Insertion Failed", JOptionPane.ERROR_MESSAGE);
                        onOperationComplete.get(); // Go back on unexpected error.
                        break;
                }
            } catch (RuntimeException ex) {
                // Catch ETOUR or other unhandled service exceptions
                errorHandler.accept("Service Error", ex.getMessage());
                onOperationComplete.get(); // Return to selection panel after handling error
            }
        }
    }
    /**
     * Handles the cancellation of the banner insertion operation.
     * Notifies the parent panel that the operation is cancelled.
     */
    private void cancelOperation() {
        // The Operator Agency cancels the operation.
        JOptionPane.showMessageDialog(this, "Banner insertion cancelled by operator.",
                "Operation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        onOperationComplete.get(); // Notify parent to go back
    }
}