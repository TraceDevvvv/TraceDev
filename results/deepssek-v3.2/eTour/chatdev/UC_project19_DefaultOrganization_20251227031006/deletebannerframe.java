/*
 * DeleteBannerFrame.java
 * 
 * This class represents the main window for the DeleteBanner use case.
 * It provides a GUI for agency operators to delete banner ads associated
 * with refreshment points. The application simulates server connection
 * to ETOUR and handles connection interruptions as specified in the use case.
 * 
 * Key features:
 * - Selection of refreshment points and associated banners
 * - Deletion confirmation dialog
 * - Simulated server connection with retry mechanism
 * - Handling of ETOUR server connection interruptions
 * - Validation and edge case handling
 * - Data persistence using a service layer with file-based storage
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class DeleteBannerFrame extends JFrame {
    private JComboBox<String> refreshmentPointComboBox;
    private JList<String> bannerList;
    private DefaultListModel<String> bannerListModel;
    private JTextArea messageArea;
    private JButton deleteButton;
    private JLabel connectionStatusLabel;
    // Use BannerService instead of in-memory arrays for data persistence
    private BannerService bannerService;
    public DeleteBannerFrame() {
        // Initialize the banner service (handles data persistence and server simulation)
        bannerService = new BannerService();
        // Set up the JFrame
        setTitle("Delete Banner - Agency Operator Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 550));
        // Set layout and border for the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Create a title label
        JLabel titleLabel = new JLabel("Delete Banner - Agency Operator Console");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create connection status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        connectionStatusLabel = new JLabel("Server: Connected");
        connectionStatusLabel.setForeground(Color.GREEN);
        statusPanel.add(connectionStatusLabel);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        // Create a control panel for refreshment point selection and banner list
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Label and ComboBox for refreshment points (Step 1)
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Select Refreshment Point:"), gbc);
        // Get refreshment points from the service
        refreshmentPointComboBox = new JComboBox<>(bannerService.getRefreshmentPoints());
        refreshmentPointComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBannerList();
            }
        });
        gbc.gridx = 1;
        controlPanel.add(refreshmentPointComboBox, gbc);
        // Label and List for banners (Step 2)
        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Associated Banners:"), gbc);
        bannerListModel = new DefaultListModel<>();
        bannerList = new JList<>(bannerListModel);
        bannerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(bannerList);
        listScrollPane.setPreferredSize(new Dimension(350, 150));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        controlPanel.add(listScrollPane, gbc);
        // Button to trigger deletion (Step 3)
        deleteButton = new JButton("Delete Selected Banner");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedBanner();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        controlPanel.add(deleteButton, gbc);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        // Create a message area for displaying status (Step 4, 5, 6, exit condition)
        messageArea = new JTextArea(5, 30);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setBorder(BorderFactory.createTitledBorder("Messages"));
        mainPanel.add(messageScrollPane, BorderLayout.SOUTH);
        // Initialize banner list with the first point
        updateBannerList();
        // Add main panel to frame
        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Center the window
    }
    /**
     * Updates the banner list based on the currently selected refreshment point.
     * This simulates Step 1 and Step 2 of the use case.
     * Includes server connection validation as per the interruption requirement.
     */
    private void updateBannerList() {
        // Check server connection before updating data
        if (!bannerService.checkServerConnection()) {
            messageArea.setText("Warning: Server connection lost. Data may be outdated.\n");
            connectionStatusLabel.setForeground(Color.ORANGE);
            connectionStatusLabel.setText("Server: Connection Issues");
        } else {
            messageArea.setText("");
            connectionStatusLabel.setForeground(Color.GREEN);
            connectionStatusLabel.setText("Server: Connected");
        }
        String selectedPoint = (String) refreshmentPointComboBox.getSelectedItem();
        bannerListModel.clear();
        // Validate selected point
        if (selectedPoint != null) {
            List<String> selectedBanners = bannerService.getBannersForPoint(selectedPoint);
            // Handle empty banner list
            if (selectedBanners.isEmpty()) {
                bannerListModel.addElement("No banners available for this point");
                deleteButton.setEnabled(false);
            } else {
                for (String banner : selectedBanners) {
                    bannerListModel.addElement(banner);
                }
                deleteButton.setEnabled(true);
            }
            if (bannerService.isServerConnected()) {
                messageArea.append("Banner list updated for " + selectedPoint + ".\n");
            }
        } else {
            messageArea.append("Error: No refreshment point selected.\n");
            bannerListModel.addElement("No data available");
            deleteButton.setEnabled(false);
        }
    }
    /**
     * Handles the deletion process for the selected banner.
     * This simulates Steps 3, 4, 5, and 6 of the use case.
     * Includes server connection checking as per the ETOUR interruption requirement.
     */
    private void deleteSelectedBanner() {
        // Check server connection before proceeding (handles ETOUR interruption requirement)
        if (!bannerService.connectToServer(3)) {
            messageArea.append("Error: Cannot connect to ETOUR server. Please check your connection.\n");
            JOptionPane.showMessageDialog(this,
                "Connection to ETOUR server failed. Operation aborted.",
                "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedPoint = (String) refreshmentPointComboBox.getSelectedItem();
        int selectedBannerIndex = bannerList.getSelectedIndex();
        // Validate selected refreshment point
        if (selectedPoint == null) {
            messageArea.append("Error: Invalid refreshment point selection.\n");
            JOptionPane.showMessageDialog(this,
                "Invalid refreshment point selection.",
                "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if a banner is selected
        if (selectedBannerIndex == -1) {
            messageArea.append("Please select a banner to delete.\n");
            JOptionPane.showMessageDialog(this,
                "Please select a banner from the list to delete.",
                "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get the actual banner text
        String bannerToDelete = bannerListModel.get(selectedBannerIndex);
        // Check if this is the "no banners" message
        if (bannerToDelete.equals("No banners available for this point") || 
            bannerToDelete.equals("No data available")) {
            messageArea.append("Error: Cannot delete placeholder text.\n");
            JOptionPane.showMessageDialog(this,
                "Invalid banner selection. Please try again.",
                "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Step 4: Display a confirmation message
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the banner:\n" + bannerToDelete + "?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        // Step 5: Confirm the operation
        if (confirm == JOptionPane.YES_OPTION) {
            // Step 6: Remove the banner using the service
            boolean success = bannerService.deleteBanner(selectedPoint, bannerToDelete);
            if (success) {
                updateBannerList(); // Refresh the list to reflect deletion
                // Exit condition: Notify successful elimination
                messageArea.append("Successfully deleted banner: " + bannerToDelete + "\n");
                JOptionPane.showMessageDialog(this,
                    "Banner deleted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                messageArea.append("Failed to delete banner: " + bannerToDelete + "\n");
                JOptionPane.showMessageDialog(this,
                    "Failed to delete banner. Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            messageArea.append("Deletion cancelled.\n");
        }
    }
    /**
     * Main method to launch the application.
     * This is the entry point for the Java program.
     */
    public static void main(String[] args) {
        // Ensure GUI creation is thread-safe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set system look and feel for better UI integration
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DeleteBannerFrame frame = new DeleteBannerFrame();
                frame.setVisible(true);
            }
        });
    }
}