/**
 * DeleteBannerFrame.java
 *
 * This class represents the main window of the application.
 * It contains the GUI components to simulate the DeleteBanner use case.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DeleteBannerFrame extends JFrame {
    private JComboBox<String> refreshmentPointComboBox;
    private JList<String> bannerList;
    private DefaultListModel<String> bannerListModel;
    private JTextArea messageArea;
    private JButton deleteButton;
    private JButton confirmButton;
    // Simulated data: refreshment points and their associated banners
    private String[] refreshmentPoints = {"Point A", "Point B", "Point C"};
    private String[][] banners = {
            {"Banner 1 at Point A", "Banner 2 at Point A"},
            {"Banner 1 at Point B", "Banner 2 at Point B", "Banner 3 at Point B"},
            {"Banner 1 at Point C"}
    };
    public DeleteBannerFrame() {
        // Set layout and border for the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Create a title label
        JLabel titleLabel = new JLabel("Delete Banner - Agency Operator Console");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create a control panel for refreshment point selection and banner list
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Label and ComboBox for refreshment points (Step 1)
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Select Refreshment Point:"), gbc);
        refreshmentPointComboBox = new JComboBox<>(refreshmentPoints);
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
        listScrollPane.setPreferredSize(new Dimension(300, 150));
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
    }
    /**
     * Updates the banner list based on the currently selected refreshment point.
     * This simulates Step 1 and Step 2 of the use case.
     */
    private void updateBannerList() {
        int selectedIndex = refreshmentPointComboBox.getSelectedIndex();
        bannerListModel.clear();
        if (selectedIndex >= 0 && selectedIndex < banners.length) {
            for (String banner : banners[selectedIndex]) {
                bannerListModel.addElement(banner);
            }
        }
        messageArea.setText("Banner list updated for " + refreshmentPointComboBox.getSelectedItem() + ".\n");
    }
    /**
     * Handles the deletion process for the selected banner.
     * This simulates Steps 3, 4, 5, and 6 of the use case.
     */
    private void deleteSelectedBanner() {
        int selectedPointIndex = refreshmentPointComboBox.getSelectedIndex();
        int selectedBannerIndex = bannerList.getSelectedIndex();
        // Check if a banner is selected
        if (selectedBannerIndex == -1) {
            messageArea.append("Please select a banner to delete.\n");
            return;
        }
        // Step 4: Display a confirmation message
        String bannerToDelete = bannerListModel.get(selectedBannerIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the banner:\n" + bannerToDelete + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        // Step 5: Confirm the operation
        if (confirm == JOptionPane.YES_OPTION) {
            // Step 6: Remove the banner from the simulated data
            // In a real application, this would involve a database or server call.
            // Here we simulate by removing from the array and updating the list.
            String[] pointBanners = banners[selectedPointIndex];
            String[] newBanners = new String[pointBanners.length - 1];
            for (int i = 0, j = 0; i < pointBanners.length; i++) {
                if (i != selectedBannerIndex) {
                    newBanners[j++] = pointBanners[i];
                }
            }
            banners[selectedPointIndex] = newBanners;
            updateBannerList(); // Refresh the list to reflect deletion
            // Exit condition: Notify successful elimination
            messageArea.append("Successfully deleted banner: " + bannerToDelete + "\n");
            JOptionPane.showMessageDialog(this,
                    "Banner deleted successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            messageArea.append("Deletion cancelled.\n");
        }
    }
}