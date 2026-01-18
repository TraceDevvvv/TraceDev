'''
Main class for the ModifyBanner application.
This application simulates the process of changing a banner image for a rest point.
It includes a GUI to select a rest point, choose a banner, and update the image.
The code is structured to handle the use case flow as described.
'''
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ModifyBannerApplication {
    // Simulated database of rest points and their banners
    private static Map<String, List<Banner>> restPoints = new HashMap<>();
    private static String selectedRestPoint = null;
    private static Banner selectedBanner = null;
    // GUI Components
    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static JLabel statusLabel;
    private static JComboBox<String> restPointComboBox;
    private static JComboBox<String> bannerComboBox;
    private static JButton selectImageButton;
    private static JButton confirmButton;
    private static JTextArea imageInfoTextArea;
    private static JLabel imagePreviewLabel;
    // Currently selected image file
    private static File selectedImageFile = null;
    // Simulate server connection state
    private static boolean serverConnected = true;
    /**
     * Main method to launch the application.
     * Initializes the simulated data and sets up the GUI.
     */
    public static void main(String[] args) {
        // Step 1: Simulate receiving a list of rest points (from SearchRefreshmentPoint use case)
        initializeSampleData();
        // Set up GUI
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    /**
     * Initialize sample data for rest points and banners.
     * This simulates the data that would be retrieved from a database or server.
     */
    private static void initializeSampleData() {
        List<Banner> banners1 = new ArrayList<>();
        banners1.add(new Banner("Banner1_RestPointA", "Initial Image 1", "/path/to/image1.jpg"));
        banners1.add(new Banner("Banner2_RestPointA", "Initial Image 2", "/path/to/image2.jpg"));
        restPoints.put("Rest Point A", banners1);
        List<Banner> banners2 = new ArrayList<>();
        banners2.add(new Banner("Banner1_RestPointB", "Initial Image 3", "/path/to/image3.jpg"));
        banners2.add(new Banner("Banner2_RestPointB", "Initial Image 4", "/path/to/image4.jpg"));
        restPoints.put("Rest Point B", banners2);
    }
    /**
     * Creates and displays the main GUI window.
     * The GUI follows the flow of events described in the use case.
     */
    private static void createAndShowGUI() {
        mainFrame = new JFrame("Modify Banner Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Top panel for rest point selection (Step 1 and 2)
        JPanel topPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        topPanel.add(new JLabel("Select Rest Point:"));
        restPointComboBox = new JComboBox<>(restPoints.keySet().toArray(new String[0]));
        restPointComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestPointSelection();
            }
        });
        topPanel.add(restPointComboBox);
        topPanel.add(new JLabel("Select Banner:"));
        bannerComboBox = new JComboBox<>();
        bannerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBannerSelection();
            }
        });
        topPanel.add(bannerComboBox);
        // Initially disable banner selection until a rest point is selected
        bannerComboBox.setEnabled(false);
        // Button to select an image (Step 4 and 5)
        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        topPanel.add(selectImageButton);
        // Placeholder for alignment
        topPanel.add(new JLabel());
        // Simulation of server disconnection (for exit condition handling)
        JButton simulateDisconnectButton = new JButton("Simulate Server Disconnection");
        simulateDisconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateServerDisconnection();
            }
        });
        topPanel.add(new JLabel("Simulate exit condition:"));
        topPanel.add(simulateDisconnectButton);
        // Button to simulate server reconnection
        JButton simulateReconnectButton = new JButton("Simulate Server Reconnection");
        simulateReconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateServerReconnection();
            }
        });
        topPanel.add(new JLabel("Recover from interruption:"));
        topPanel.add(simulateReconnectButton);
        // Image preview and info area (Step 4, 5, and 6)
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        imagePreviewLabel = new JLabel("No image selected", JLabel.CENTER);
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        imageInfoTextArea = new JTextArea("Image information will appear here.\n");
        imageInfoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(imageInfoTextArea);
        centerPanel.add(scrollPane, BorderLayout.SOUTH);
        // Bottom panel for confirmation and status (Step 6, 7, and 8)
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        confirmButton = new JButton("Confirm Change");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmChange();
            }
        });
        confirmButton.setEnabled(false);
        bottomPanel.add(confirmButton, BorderLayout.NORTH);
        statusLabel = new JLabel("Status: Ready | Server: Connected");
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);
        // Add all panels to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    /**
     * Handles the selection of a rest point.
     * Updates the list of banners associated with the selected rest point.
     * This corresponds to Step 2 of the use case.
     */
    private static void handleRestPointSelection() {
        if (!serverConnected) {
            showErrorDialog("Cannot select rest point: Server connection interrupted.");
            return;
        }
        selectedRestPoint = (String) restPointComboBox.getSelectedItem();
        if (selectedRestPoint != null) {
            // Clear and update banner combo box
            bannerComboBox.removeAllItems();
            List<Banner> banners = restPoints.get(selectedRestPoint);
            // Add null safety check
            if (banners != null) {
                for (Banner banner : banners) {
                    bannerComboBox.addItem(banner.getName());
                }
                bannerComboBox.setEnabled(true);
                bannerComboBox.setSelectedIndex(-1);
                statusLabel.setText("Status: Rest point selected. Choose a banner. | Server: Connected");
            } else {
                showErrorDialog("No banners found for selected rest point.");
                bannerComboBox.setEnabled(false);
            }
        }
    }
    /**
     * Handles the selection of a banner from the list.
     * This corresponds to Step 3 of the use case.
     */
    private static void handleBannerSelection() {
        if (!serverConnected) {
            showErrorDialog("Cannot select banner: Server connection interrupted.");
            return;
        }
        String bannerName = (String) bannerComboBox.getSelectedItem();
        if (bannerName != null && selectedRestPoint != null) {
            List<Banner> banners = restPoints.get(selectedRestPoint);
            // Add null safety check
            if (banners != null) {
                for (Banner banner : banners) {
                    if (banner.getName().equals(bannerName)) {
                        selectedBanner = banner;
                        break;
                    }
                }
                statusLabel.setText("Status: Banner selected. You can now select a new image. | Server: Connected");
            } else {
                showErrorDialog("No banners found for selected rest point.");
                selectedBanner = null;
            }
        }
    }
    /**
     * Opens a file chooser to select an image file.
     * Validates the selected file and updates the preview.
     * This corresponds to Steps 4 and 5 of the use case.
     */
    private static void selectImage() {
        if (!serverConnected) {
            showErrorDialog("Cannot select image: Server connection interrupted.");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(mainFrame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            // Step 6: Check the characteristics of the inserted image.
            if (validateImage(selectedImageFile)) {
                // Display image preview
                ImageIcon icon = new ImageIcon(selectedImageFile.getPath());
                Image image = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                imagePreviewLabel.setIcon(new ImageIcon(image));
                imagePreviewLabel.setText("");
                // Display image information
                imageInfoTextArea.setText("Selected Image Information:\n");
                imageInfoTextArea.append("Name: " + selectedImageFile.getName() + "\n");
                imageInfoTextArea.append("Path: " + selectedImageFile.getPath() + "\n");
                imageInfoTextArea.append("Size: " + selectedImageFile.length() + " bytes\n");
                imageInfoTextArea.append("\nPlease confirm the change to update the banner.");
                // Enable confirmation button
                confirmButton.setEnabled(true);
                statusLabel.setText("Status: Valid image selected. Ready to confirm change. | Server: Connected");
            } else {
                // Image is not valid, simulate the Errored use case.
                showErrorDialog("Invalid image file. Please select a valid image file (JPEG, PNG, GIF, BMP).");
                selectedImageFile = null;
                imagePreviewLabel.setIcon(null);
                imagePreviewLabel.setText("No image selected");
                imageInfoTextArea.setText("Image information will appear here.\n");
                confirmButton.setEnabled(false);
            }
        }
    }
    /**
     * Validates the selected image file.
     * Checks if the file exists, is readable, and has a supported extension.
     * This is part of Step 6 of the use case.
     * @param file The image file to validate.
     * @return true if the image is valid, false otherwise.
     */
    private static boolean validateImage(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return false;
        }
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
               name.endsWith(".png") || name.endsWith(".gif") ||
               name.endsWith(".bmp");
    }
    /**
     * Shows an error dialog with a given message.
     * This simulates the Errored use case.
     * @param message The error message to display.
     */
    private static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Handles the confirmation of the banner image change.
     * Simulates updating the banner with the new image and notifies success.
     * This corresponds to Steps 7 and 8 of the use case.
     */
    private static void confirmChange() {
        if (!serverConnected) {
            showErrorDialog("Cannot confirm change: Server connection interrupted.");
            return;
        }
        if (selectedBanner != null && selectedImageFile != null) {
            // Step 7: Confirmation of the transaction change.
            // Step 8: Bookmark this new image for the selected banner.
            selectedBanner.setImagePath(selectedImageFile.getPath());
            selectedBanner.setImageDescription("Updated to: " + selectedImageFile.getName());
            // Simulate successful modification.
            statusLabel.setText("Status: Banner successfully updated with new image! | Server: Connected");
            JOptionPane.showMessageDialog(mainFrame,
                    "Banner image has been updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            // Reset selection for next operation
            resetSelection();
        } else {
            showErrorDialog("No banner or image selected. Please try again.");
        }
    }
    /**
     * Resets the UI selections after a successful update.
     */
    private static void resetSelection() {
        selectedImageFile = null;
        imagePreviewLabel.setIcon(null);
        imagePreviewLabel.setText("No image selected");
        imageInfoTextArea.setText("Image information will appear here.\n");
        confirmButton.setEnabled(false);
        bannerComboBox.setSelectedIndex(-1);
        bannerComboBox.setEnabled(false);
        selectedBanner = null;
    }
    /**
     * Simulates server disconnection as mentioned in exit conditions.
     * Handles the "Interruption of the connection to the server ETOUR" condition.
     */
    private static void simulateServerDisconnection() {
        serverConnected = false;
        statusLabel.setText("Status: Server connection interrupted! | Server: Disconnected");
        // Disable all interactive components
        restPointComboBox.setEnabled(false);
        bannerComboBox.setEnabled(false);
        selectImageButton.setEnabled(false);
        confirmButton.setEnabled(false);
        JOptionPane.showMessageDialog(mainFrame,
                "Server connection interrupted (ETOUR). All operations are disabled.",
                "Server Disconnected",
                JOptionPane.WARNING_MESSAGE);
    }
    /**
     * Simulates server reconnection after interruption.
     * Restores the server connection state and re-enables all UI components.
     */
    private static void simulateServerReconnection() {
        serverConnected = true;
        statusLabel.setText("Status: Server reconnected. Ready to resume operations. | Server: Connected");
        // Re-enable all interactive components
        restPointComboBox.setEnabled(true);
        // Only enable banner combo box if a rest point is selected
        if (selectedRestPoint != null) {
            bannerComboBox.setEnabled(true);
        } else {
            bannerComboBox.setEnabled(false);
        }
        selectImageButton.setEnabled(true);
        // Only enable confirm button if an image is selected
        if (selectedImageFile != null) {
            confirmButton.setEnabled(true);
        } else {
            confirmButton.setEnabled(false);
        }
        JOptionPane.showMessageDialog(mainFrame,
                "Server connection reestablished. All operations are now enabled.",
                "Server Reconnected",
                JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Inner class representing a Banner.
     * Each banner has a name, description, and image path.
     * This is a simplified model for demonstration.
     */
    static class Banner {
        private String name;
        private String imageDescription;
        private String imagePath;
        public Banner(String name, String imageDescription, String imagePath) {
            this.name = name;
            this.imageDescription = imageDescription;
            this.imagePath = imagePath;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getImageDescription() {
            return imageDescription;
        }
        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }
        public String getImagePath() {
            return imagePath;
        }
        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
        @Override
        public String toString() {
            return "Banner: " + name + " | Description: " + imageDescription + " | Path: " + imagePath;
        }
    }
}