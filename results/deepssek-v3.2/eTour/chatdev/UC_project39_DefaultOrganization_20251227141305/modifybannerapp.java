'''
Main class to run the Modify Banner application.
This class sets up the GUI and initializes the core components.
Handles banner image modification with server communication simulation.
Implements all use case steps including connection interruption handling.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
// Server simulation class to handle banner image saving with connection management
class BannerServer {
    private boolean serverAvailable = true;
    private boolean simulateConnectionError = false;
    /**
     * Checks if server is available.
     * @return true if server is available, false otherwise
     */
    public boolean isServerAvailable() {
        return serverAvailable;
    }
    /**
     * Sets server availability status.
     * @param available true to make server available, false to make it unavailable
     */
    public void setServerAvailable(boolean available) {
        this.serverAvailable = available;
    }
    /**
     * Simulates connection errors for testing purposes.
     * @param simulate true to simulate connection errors, false for normal operation
     */
    public void simulateConnectionError(boolean simulate) {
        this.simulateConnectionError = simulate;
    }
    /**
     * Returns whether connection error simulation is currently active.
     * @return true if connection error simulation is active, false otherwise
     */
    public boolean isConnectionErrorSimulated() {
        return simulateConnectionError;
    }
    /**
     * Saves banner image to server with simulated delay.
     * @param bannerId The ID of the banner to update
     * @param imageFile The image file to save
     * @return true if save succeeded, false otherwise
     * @throws IOException if server is unavailable or connection is interrupted
     */
    public boolean saveBannerImage(String bannerId, File imageFile) throws IOException {
        // Check server availability
        if (!serverAvailable) {
            throw new IOException("Server is unavailable");
        }
        // Simulate connection interruption if enabled
        if (simulateConnectionError) {
            // Immediately disable simulation after triggering the error
            simulateConnectionError = false;
            throw new IOException("Connection interrupted to server ETOUR");
        }
        // Simulate server processing delay
        try {
            Thread.sleep(1000); // 1 second delay to simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Server processing interrupted");
        }
        // In a real implementation, this would upload the file to a server
        System.out.println("Server: Saving image " + imageFile.getName() + " for banner " + bannerId);
        return true; // Success
    }
}
/**
 * Main application class for modifying banner images.
 * Implements the complete ModifyBanner use case with GUI interface.
 */
public class ModifyBannerApp {
    private JFrame mainFrame;
    private JList<String> bannerList;
    private DefaultListModel<String> listModel;
    private JLabel imagePreviewLabel;
    private File selectedImageFile;
    private String selectedBannerId;
    private BannerServer bannerServer;
    // Simulate authenticated operator and banner data
    private boolean isAuthenticated = true; // Assuming authentication is successful
    private java.util.List<String> bannerIds = Arrays.asList("Banner1", "Banner2", "Banner3");
    /**
     * Constructor to set up the GUI and initialize components.
     * Checks authentication entry condition first.
     */
    public ModifyBannerApp() {
        // Initialize server connection
        bannerServer = new BannerServer();
        // Check entry condition: authentication
        if (!isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Authentication failed. Exit.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        // Step 1: Select editing functionality (implied by launching this app)
        mainFrame = new JFrame("Modify Banner - Restaurant Point Operator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(new BorderLayout());
        // Top panel with title
        JLabel titleLabel = new JLabel("Banner Editing System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainFrame.add(titleLabel, BorderLayout.NORTH);
        // Center panel with banner list and image preview
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        // Left panel: banner list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Banners for Point of Restaurant"));
        listModel = new DefaultListModel<>();
        for (String id : bannerIds) {
            listModel.addElement(id);
        }
        bannerList = new JList<>(listModel);
        bannerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        leftPanel.add(new JScrollPane(bannerList), BorderLayout.CENTER);
        JButton editButton = new JButton("Edit Selected Banner");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 3: Select a banner and enter editing
                selectedBannerId = bannerList.getSelectedValue();
                if (selectedBannerId == null) {
                    JOptionPane.showMessageDialog(mainFrame, "Please select a banner first.", "No Selection", JOptionPane.WARNING_MESSAGE);
                } else {
                    openImageSelectionForm();
                }
            }
        });
        leftPanel.add(editButton, BorderLayout.SOUTH);
        // Right panel: image preview placeholder
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Image Preview"));
        imagePreviewLabel = new JLabel("No image selected", SwingConstants.CENTER);
        imagePreviewLabel.setPreferredSize(new Dimension(250, 200));
        rightPanel.add(imagePreviewLabel, BorderLayout.CENTER);
        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);
        mainFrame.add(centerPanel, BorderLayout.CENTER);
        // Add connection test button for demonstration
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton testConnectionButton = new JButton("Test Connection Interruption");
        testConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateConnectionInterruption();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Connection interruption simulation enabled.\nNext save operation will fail with a one-time error.", 
                    "Connection Test", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        bottomPanel.add(testConnectionButton);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }
    /**
     * Opens a form for image selection (Step 4).
     * Displays file browser and preview for selected image.
     */
    private void openImageSelectionForm() {
        JFrame imageFrame = new JFrame("Select Image for " + selectedBannerId);
        imageFrame.setSize(400, 350);
        imageFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JLabel instruction = new JLabel("Choose an image file (JPG, PNG, GIF):", SwingConstants.CENTER);
        panel.add(instruction, BorderLayout.NORTH);
        JButton browseButton = new JButton("Browse...");
        JLabel selectedFileLabel = new JLabel("No file selected", SwingConstants.CENTER);
        panel.add(selectedFileLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(browseButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 5: Select picture
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        String name = f.getName().toLowerCase();
                        return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                               name.endsWith(".png") || name.endsWith(".gif") ||
                               f.isDirectory();
                    }
                    @Override
                    public String getDescription() {
                        return "Image files (*.jpg, *.jpeg, *.png, *.gif)";
                    }
                });
                int result = fileChooser.showOpenDialog(imageFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedImageFile = fileChooser.getSelectedFile();
                    selectedFileLabel.setText(selectedImageFile.getName());
                    // Update preview
                    ImageIcon icon = new ImageIcon(selectedImageFile.getAbsolutePath());
                    Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    imagePreviewLabel.setIcon(new ImageIcon(scaled));
                    imagePreviewLabel.setText("");
                }
            }
        });
        JButton submitButton = new JButton("Submit for Validation");
        JButton cancelButton = new JButton("Cancel");
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.add(cancelButton);
        actionPanel.add(submitButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle operation cancellation (exit condition)
                imageFrame.dispose();
                JOptionPane.showMessageDialog(mainFrame, "Operation cancelled by operator.", 
                    "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedImageFile == null) {
                    JOptionPane.showMessageDialog(imageFrame, "Please select an image first.", 
                        "No Image", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Step 6: Validate image
                    if (validateImage(selectedImageFile)) {
                        // Ask for confirmation
                        int confirm = JOptionPane.showConfirmDialog(imageFrame,
                                "Are you sure you want to change the banner image?\nFile: " + selectedImageFile.getName(),
                                "Confirm Change", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            // Step 7 & 8: Confirm and bookmark with progress indicator
                            showProgressAndBookmark(imageFrame);
                        } else {
                            JOptionPane.showMessageDialog(imageFrame, "Change cancelled.", 
                                "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        // Enable use case Errored (simulated by error message)
                        JOptionPane.showMessageDialog(imageFrame, 
                            "Invalid image. Please select a valid image file (JPG, PNG, GIF, max 5MB).", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        imageFrame.add(panel, BorderLayout.CENTER);
        imageFrame.add(actionPanel, BorderLayout.SOUTH);
        imageFrame.setVisible(true);
    }
    /**
     * Shows progress indicator while bookmarking image and handles the save operation.
     * @param parentFrame The parent frame to position the progress dialog
     */
    private void showProgressAndBookmark(JFrame parentFrame) {
        // Create and show progress dialog
        JDialog progressDialog = new JDialog(parentFrame, "Saving Banner", true);
        progressDialog.setSize(300, 100);
        progressDialog.setLayout(new BorderLayout());
        progressDialog.setLocationRelativeTo(parentFrame);
        JLabel progressLabel = new JLabel("Saving banner image to server...", SwingConstants.CENTER);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Use indeterminate progress for network operation
        progressDialog.add(progressLabel, BorderLayout.CENTER);
        progressDialog.add(progressBar, BorderLayout.SOUTH);
        // Use SwingWorker to perform network operation in background
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Perform the bookmark operation in background thread
                return performBookmarkOperation();
            }
            @Override
            protected void done() {
                progressDialog.dispose();
                try {
                    Boolean success = get();
                    if (success) {
                        // Success notification (exit condition)
                        parentFrame.dispose();
                        JOptionPane.showMessageDialog(mainFrame, 
                            "Banner modified successfully!", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    // Handle exceptions from doInBackground
                    if (e.getCause() instanceof IOException) {
                        // Connection interruption handling
                        JOptionPane.showMessageDialog(mainFrame, 
                            "Connection error: " + e.getCause().getMessage() + 
                            "\nPlease try again later or check your connection.", 
                            "Server Connection Error", 
                            JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, 
                            "Unexpected error: " + e.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        worker.execute();
        progressDialog.setVisible(true);
    }
    /**
     * Performs the actual bookmark operation with server communication.
     * @return true if operation succeeded, false otherwise
     * @throws IOException if connection fails
     */
    private boolean performBookmarkOperation() throws IOException {
        // Step 8: Bookmark the new image for the selected banner
        return bookmarkImage(selectedBannerId, selectedImageFile);
    }
    /**
     * Validates the selected image file (Step 6).
     * @param file The image file to validate.
     * @return true if valid, false otherwise.
     */
    private boolean validateImage(File file) {
        // Check if file exists
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        // Check file size (max 5MB)
        long maxSize = 5 * 1024 * 1024; // 5MB
        if (file.length() > maxSize) {
            return false;
        }
        // Check file extension
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
               name.endsWith(".png") || name.endsWith(".gif");
    }
    /**
     * Bookmarks the new image for the selected banner (Step 8).
     * Saves to server with proper error handling for connection interruptions.
     * @param bannerId The ID of the banner to update.
     * @param imageFile The new image file.
     * @return true if bookmarking succeeded, false otherwise
     * @throws IOException if server communication fails
     */
    private boolean bookmarkImage(String bannerId, File imageFile) throws IOException {
        try {
            // Attempt to save to server with timeout simulation
            boolean success = bannerServer.saveBannerImage(bannerId, imageFile);
            if (success) {
                System.out.println("Successfully bookmarked image for banner " + bannerId);
                // In a real implementation, you would update local state or database here
                return true;
            }
            return false;
        } catch (IOException e) {
            // Re-throw the exception to be handled by the calling method
            throw e;
        }
    }
    /**
     * Simulates connection interruption for testing purposes.
     * This method enables the connection error simulation in the BannerServer for one-time use.
     */
    private void simulateConnectionInterruption() {
        bannerServer.simulateConnectionError(true);
        System.out.println("Connection interruption simulation enabled for next save attempt only");
    }
    /**
     * Main method to launch the application.
     * Uses SwingUtilities to ensure thread-safe GUI creation.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModifyBannerApp();
            }
        });
    }
}