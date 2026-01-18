/**
 * Main class to launch the Insert Banner application.
 * This program implements a GUI for inserting a banner associated with a refreshment point.
 * It follows the use case specification, handling image selection, validation, and database operations.
 * Includes robust handling of database connection interruptions.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class InsertBannerApp {
    private JFrame frame;
    private JComboBox<String> refreshmentPointComboBox;
    private JLabel imageLabel;
    private String selectedImagePath;
    private JButton insertButton, cancelButton, selectImageButton;
    private static final int MAX_BANNERS = 5; // Maximum banners allowed per refreshment point
    private Connection connection;
    private JLabel statusLabel; // Status label for operation state
    private static final int CONNECTION_VALIDATION_TIMEOUT = 5; // Timeout in seconds for connection validation
    public InsertBannerApp() {
        initializeDatabase(); // Initialize database connection
        initializeGUI(); // Setup the GUI
        loadRefreshmentPoints(); // Load points from database
    }
    /**
     * Initializes the database connection with H2 embedded database.
     * Uses file-based storage; falls back to in-memory if needed.
     */
    private void initializeDatabase() {
        try {
            // Use H2 embedded database (pure Java, no external driver needed)
            Class.forName("org.h2.Driver");
            // File-based database for persistence; change to "jdbc:h2:mem:banners" for in-memory
            connection = DriverManager.getConnection("jdbc:h2:./banners_db;AUTO_SERVER=TRUE", "sa", "");
            createTables();
            updateStatus("Connected to H2 embedded database.");
        } catch (ClassNotFoundException e) {
            // Should not happen with H2 as it's included, but handle gracefully
            JOptionPane.showMessageDialog(frame,
                "H2 database driver not found. This is unexpected.\n" +
                "Using in-memory database for this session.",
                "Database Warning", JOptionPane.WARNING_MESSAGE);
            try {
                connection = DriverManager.getConnection("jdbc:h2:mem:banners;DB_CLOSE_DELAY=-1", "sa", "");
                createTables();
                addSampleData(); // Add sample data to in-memory DB
                updateStatus("Using in-memory database.");
            } catch (SQLException ex) {
                handleFatalDatabaseError(ex);
            }
        } catch (SQLException e) {
            handleFatalDatabaseError(e);
        }
    }
    /**
     * Checks if the database connection is active and valid.
     * @return true if connection is valid, false otherwise
     */
    private boolean isDatabaseConnected() {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(CONNECTION_VALIDATION_TIMEOUT);
        } catch (SQLException e) {
            return false;
        }
    }
    /**
     * Handles connection interruption errors during database operations.
     * Displays appropriate error message and attempts reconnection.
     * @param operation The operation that was being performed when connection was lost
     */
    private void handleConnectionError(String operation) {
        updateStatus("Connection to server interrupted");
        JOptionPane.showMessageDialog(frame,
            "Connection to server ETOUR interrupted during " + operation + ".\n" +
            "Please check your connection and try again.",
            "Connection Error", JOptionPane.ERROR_MESSAGE);
        // Attempt reconnection
        updateStatus("Attempting reconnection...");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            // Re-initialize database connection
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./banners_db;AUTO_SERVER=TRUE", "sa", "");
            updateStatus("Reconnected to database successfully");
        } catch (Exception e) {
            updateStatus("Reconnection failed");
            JOptionPane.showMessageDialog(frame,
                "Failed to reconnect to database: " + e.getMessage() + "\n" +
                "Some features may not be available.",
                "Reconnection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Handles fatal database errors by displaying a message and exiting.
     * @param e Exception that occurred
     */
    private void handleFatalDatabaseError(Exception e) {
        JOptionPane.showMessageDialog(frame,
            "Critical database error: " + e.getMessage() + "\nApplication will exit.",
            "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
    /**
     * Creates necessary tables if they don't exist with proper transaction handling.
     */
    private void createTables() {
        // Check connection before creating tables
        if (!isDatabaseConnected()) {
            handleConnectionError("table creation");
            // Try to proceed anyway - the table might already exist
        }
        String createRefreshmentPointsTable = "CREATE TABLE IF NOT EXISTS refreshment_points (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL)";
        String createBannersTable = "CREATE TABLE IF NOT EXISTS banners (" +
                "id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "refreshment_point_id INTEGER, " +
                "image_path VARCHAR(1024) NOT NULL, " +
                "FOREIGN KEY (refreshment_point_id) REFERENCES refreshment_points(id))";
        try {
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createRefreshmentPointsTable);
                stmt.execute(createBannersTable);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                "Failed to create database tables: " + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Database initialization failed", e);
        }
    }
    /**
     * Initializes the GUI components and layout.
     */
    private void initializeGUI() {
        frame = new JFrame("Insert Banner - Agency Operator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        // Top panel for refreshment point selection
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Refreshment Point:"));
        refreshmentPointComboBox = new JComboBox<>();
        topPanel.add(refreshmentPointComboBox);
        frame.add(topPanel, BorderLayout.NORTH);
        // Center panel for image selection and display
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel imagePanel = new JPanel();
        imageLabel = new JLabel("No image selected", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 200));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanel.add(imageLabel);
        centerPanel.add(imagePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(e -> selectImage());
        buttonPanel.add(selectImageButton);
        insertButton = new JButton("Insert Banner");
        insertButton.addActionListener(e -> insertBanner());
        insertButton.setEnabled(false);
        buttonPanel.add(insertButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancelOperation());
        buttonPanel.add(cancelButton);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        // South panel for status messages
        JPanel southPanel = new JPanel();
        statusLabel = new JLabel("Status: Ready");
        southPanel.add(statusLabel);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    /**
     * Updates the status label with the given message.
     * @param message Status message to display
     */
    private void updateStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText("Status: " + message);
        }
    }
    /**
     * Loads refreshment points from the database into the combo box.
     */
    private void loadRefreshmentPoints() {
        // Check database connection before loading points
        if (!isDatabaseConnected()) {
            handleConnectionError("loading refreshment points");
            if (!isDatabaseConnected()) {
                // Still not connected after reconnection attempt
                updateStatus("Cannot load points - no database connection");
                return;
            }
        }
        updateStatus("Loading refreshment points...");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM refreshment_points")) {
            refreshmentPointComboBox.removeAllItems(); // Clear existing items
            while (rs.next()) {
                refreshmentPointComboBox.addItem(rs.getString("name") + " (ID: " + rs.getInt("id") + ")");
            }
            if (refreshmentPointComboBox.getItemCount() == 0) {
                // If no points exist, add some sample data for demonstration
                addSampleData();
                loadRefreshmentPoints(); // Reload after adding sample
            }
            updateStatus("Ready");
        } catch (SQLException e) {
            updateStatus("Error loading points: " + e.getMessage());
            JOptionPane.showMessageDialog(frame,
                "Failed to load refreshment points: " + e.getMessage(),
                "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Adds sample refreshment points to the database for demonstration.
     */
    private void addSampleData() {
        // Check database connection before adding sample data
        if (!isDatabaseConnected()) {
            handleConnectionError("adding sample data");
            return;
        }
        String[] samplePoints = {"Restaurant A", "Cafe B", "Snack Bar C"};
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO refreshment_points (name) VALUES (?)")) {
            for (String point : samplePoints) {
                pstmt.setString(1, point);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                "Failed to add sample data: " + e.getMessage(),
                "Sample Data Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Opens a file chooser to select an image file.
     * Updates the GUI to show the selected image.
     */
    private void selectImage() {
        updateStatus("Selecting image...");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImagePath = fileChooser.getSelectedFile().getPath();
            // Display image (scaled to fit label)
            ImageIcon icon = new ImageIcon(selectedImagePath);
            Image scaledImage = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText("");
            insertButton.setEnabled(true); // Enable insert button after image selection
            updateStatus("Image selected: " + fileChooser.getSelectedFile().getName());
        } else {
            updateStatus("Image selection canceled");
        }
    }
    /**
     * Inserts the selected banner after validation.
     * Checks for maximum banner count and validates image.
     * Handles database connection interruptions as per the use case.
     */
    private void insertBanner() {
        updateStatus("Validating input...");
        // Validate refreshment point selection
        if (refreshmentPointComboBox.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a refreshment point.",
                    "Selection Error", JOptionPane.WARNING_MESSAGE);
            updateStatus("Please select a refreshment point");
            return;
        }
        // Validate image selection
        if (selectedImagePath == null || selectedImagePath.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an image.",
                    "Image Error", JOptionPane.WARNING_MESSAGE);
            updateStatus("Please select an image");
            return;
        }
        // Extract refreshment point ID from combo box selection with validation
        String selected = (String) refreshmentPointComboBox.getSelectedItem();
        int pointId;
        try {
            pointId = Integer.parseInt(selected.split("\\(ID: ")[1].replace(")", ""));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid refreshment point selection.",
                    "Selection Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Invalid refreshment point");
            return;
        }
        // Step 4: Validate image
        updateStatus("Validating image...");
        if (!isImageValid(selectedImagePath)) {
            JOptionPane.showMessageDialog(frame, "The selected image is not valid. Please choose a valid image.",
                    "Image Validation Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Image validation failed");
            return; // Enable Errored use case (represented by error message)
        }
        // Check database connection before banner count check
        updateStatus("Checking database connection...");
        if (!isDatabaseConnected()) {
            handleConnectionError("banner count check");
            if (!isDatabaseConnected()) {
                // Still not connected after reconnection attempt
                JOptionPane.showMessageDialog(frame,
                    "Cannot proceed with banner insertion - no database connection.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Insertion failed - no database connection");
                return; // Exit condition: connection interruption
            }
        }
        // Check banner count
        updateStatus("Checking banner count...");
        int bannerCount;
        try {
            bannerCount = getBannerCount(pointId);
        } catch (SQLException e) {
            // Handle database error during count retrieval
            handleConnectionError("banner count retrieval");
            JOptionPane.showMessageDialog(frame,
                "Failed to check banner count due to database error.",
                "Database Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Banner count check failed");
            return;
        }
        if (bannerCount >= MAX_BANNERS) {
            JOptionPane.showMessageDialog(frame, "This refreshment point has reached the maximum number of banners (" + MAX_BANNERS + ").",
                    "Maximum Banners Exceeded", JOptionPane.ERROR_MESSAGE);
            updateStatus("Maximum banners exceeded");
            return; // Exit condition: maximum banners reached
        }
        // Ask for confirmation (Step 4 continuation)
        updateStatus("Awaiting confirmation...");
        int confirm = JOptionPane.showConfirmDialog(frame,
                "Confirm insertion of banner for " + selected + "?",
                "Confirm Insertion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            updateStatus("Insertion canceled by user");
            return; // Operator canceled
        }
        // Check connection again before insertion (connection could have been lost during confirmation)
        if (!isDatabaseConnected()) {
            handleConnectionError("banner insertion");
            if (!isDatabaseConnected()) {
                JOptionPane.showMessageDialog(frame,
                    "Cannot insert banner - database connection lost.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
                updateStatus("Insertion failed - connection lost");
                return; // Exit condition: connection interruption
            }
        }
        // Insert banner into database (Step 5 and 6)
        updateStatus("Inserting banner...");
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO banners (refreshment_point_id, image_path) VALUES (?, ?)")) {
            pstmt.setInt(1, pointId);
            pstmt.setString(2, selectedImagePath);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Banner inserted successfully for " + selected + ".",
                    "Insertion Successful", JOptionPane.INFORMATION_MESSAGE);
            updateStatus("Banner inserted successfully");
            // Reset GUI for next operation
            resetForm();
        } catch (SQLException e) {
            // Handle database error during insertion
            handleConnectionError("banner insertion");
            JOptionPane.showMessageDialog(frame, "Failed to insert banner: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            updateStatus("Insertion failed: " + e.getMessage());
        }
    }
    /**
     * Validates the selected image file.
     * In a real application, this might check file format, size, etc.
     * @param imagePath path to the image file
     * @return true if valid, false otherwise
     */
    private boolean isImageValid(String imagePath) {
        // Simple validation: check if file exists and has a valid image extension
        String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif"};
        for (String ext : validExtensions) {
            if (imagePath.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Gets the current number of banners for a refreshment point.
     * @param pointId the ID of the refreshment point
     * @return the count of banners
     * @throws SQLException if there's a database error
     */
    private int getBannerCount(int pointId) throws SQLException {
        // Check connection before query
        if (!isDatabaseConnected()) {
            throw new SQLException("Database connection not available");
        }
        try (PreparedStatement pstmt = connection.prepareStatement(
                "SELECT COUNT(*) AS count FROM banners WHERE refreshment_point_id = ?")) {
            pstmt.setInt(1, pointId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            updateStatus("Error checking banner count: " + e.getMessage());
            throw e; // Re-throw to let caller handle
        }
        return 0;
    }
    /**
     * Cancels the current operation and resets the form.
     */
    private void cancelOperation() {
        updateStatus("Canceling operation...");
        int confirm = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to cancel?",
                "Cancel Operation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            resetForm();
            JOptionPane.showMessageDialog(frame, "Operation canceled.",
                    "Canceled", JOptionPane.INFORMATION_MESSAGE);
            updateStatus("Operation canceled");
        } else {
            updateStatus("Operation cancellation aborted");
        }
    }
    /**
     * Resets the form to its initial state.
     */
    private void resetForm() {
        if (refreshmentPointComboBox.getItemCount() > 0) {
            refreshmentPointComboBox.setSelectedIndex(0);
        }
        imageLabel.setIcon(null);
        imageLabel.setText("No image selected");
        selectedImagePath = null;
        insertButton.setEnabled(false);
        updateStatus("Ready");
    }
    /**
     * Closes the database connection when the application exits.
     */
    private void closeDatabase() {
        updateStatus("Closing database connection...");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                updateStatus("Database connection closed");
            }
        } catch (SQLException e) {
            updateStatus("Error closing database connection");
        }
    }
    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertBannerApp app = new InsertBannerApp();
            // Add shutdown hook to close database
            Runtime.getRuntime().addShutdownHook(new Thread(app::closeDatabase));
        });
    }
}