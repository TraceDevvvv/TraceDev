'''
Main GUI application for deleting banner ads associated with refreshment bars.
This application allows an agency operator to select a refreshment point,
view its associated banners, and then delete a selected banner.
'''
package gui;
import model.Banner;
import model.RefreshmentPoint;
import service.ETOURServer;
import exception.ServerConnectionException;
import exception.BannerNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.SwingWorker; // Required for running long tasks in the background
public class EliminaBannerApp extends JFrame {
    // Service layer to interact with the simulated ETOUR server
    private ETOURServer eTourServer;
    // GUI Components
    private JComboBox<RefreshmentPoint> refreshmentPointComboBox;
    private JComboBox<Banner> bannerComboBox;
    private JButton deleteButton;
    private JLabel statusLabel;
    private JCheckBox simulateConnectionInterruption;
    /**
     * Constructs the EliminaBannerApp GUI.
     * Sets up the main frame, initializes components, and loads initial data.
     */
    public EliminaBannerApp() {
        // Initialize the simulated ETOUR server
        eTourServer = new ETOURServer();
        // Set up the main frame
        setTitle("ELIMINABANNER - Delete Banner Ad");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Initialize GUI components
        refreshmentPointComboBox = new JComboBox<>();
        bannerComboBox = new JComboBox<>();
        deleteButton = new JButton("Delete Selected Banner");
        statusLabel = new JLabel("Status: Ready", SwingConstants.CENTER);
        simulateConnectionInterruption = new JCheckBox("Simulate Server Connection Interruption");
        // Set up layout using GridBagLayout for better control
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("1. Select Refreshment Point:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(refreshmentPointComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(new JLabel("2. Select Banner to Delete:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(bannerComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(deleteButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(statusLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(simulateConnectionInterruption, gbc);
        // Add action listeners
        refreshmentPointComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // This will trigger a SwingWorker
                    loadBannersForSelectedRefreshmentPoint();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This will trigger a SwingWorker after confirmation
                deleteSelectedBanner();
            }
        });
        // Add action listener for the simulation checkbox
        simulateConnectionInterruption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggling interruption is a fast operation, no SwingWorker needed here.
                eTourServer.simulateConnectionInterruption(simulateConnectionInterruption.isSelected());
                // Reload data to reflect potential new connection status immediately.
                // This call will initiate a SwingWorker.
                loadRefreshmentPoints();
            }
        });
        // Initial data load when the application starts
        // This will launch the first SwingWorker
        loadRefreshmentPoints();
        // Make the frame visible
        setVisible(true);
    }
    /**
     * Helper method to enable/disable primary interactive components.
     * The simulation checkbox remains enabled so the user can always toggle connection.
     * @param enabled true to enable, false to disable.
     */
    private void toggleInteractionComponents(boolean enabled) {
        refreshmentPointComboBox.setEnabled(enabled);
        bannerComboBox.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
        // simulateConnectionInterruption remains enabled to allow toggling it
        // even if the main UI is disabled due to a background task or error.
    }
    /**
     * Loads the list of refreshment points from the ETOUR server and populates
     * the refreshmentPointComboBox. Handles server connection errors.
     * This operation runs in a SwingWorker to prevent GUI freezing.
     * Corresponds to Use Case Step 1.
     */
    private void loadRefreshmentPoints() {
        statusLabel.setText("Status: Loading refreshment points...");
        refreshmentPointComboBox.removeAllItems(); // Clear existing items
        bannerComboBox.removeAllItems(); // Clear banners as well
        toggleInteractionComponents(false); // Disable interaction during loading
        new SwingWorker<java.util.List<RefreshmentPoint>, Void>() {
            private ServerConnectionException connectionException = null;
            @Override
            protected java.util.List<RefreshmentPoint> doInBackground() {
                // This code runs in a background thread
                try {
                    return eTourServer.getRefreshmentPoints();
                } catch (ServerConnectionException e) {
                    connectionException = e;
                    return null; // On exception, return null, details handled in done()
                }
            }
            @Override
            protected void done() {
                // Ensure simulateConnectionInterruption is always enabled regardless of outcome initially.
                simulateConnectionInterruption.setEnabled(true);
                try {
                    if (connectionException != null) {
                        throw connectionException; // Re-throw the stored exception
                    }
                    java.util.List<RefreshmentPoint> points = get(); // Get the result from doInBackground
                    if (points == null || points.isEmpty()) { // Check for null points list as well
                        statusLabel.setText("Status: No refreshment points found.");
                        // If no points, disable main interaction components
                        toggleInteractionComponents(false);
                    } else {
                        refreshmentPointComboBox.removeAllItems(); // Clear to ensure clean state before adding
                        for (RefreshmentPoint rp : points) {
                            refreshmentPointComboBox.addItem(rp);
                        }
                        statusLabel.setText("Status: Refreshment points loaded.");
                        // Upon successful load, enable the primary combobox.
                        refreshmentPointComboBox.setEnabled(true);
                        // Other components' state (bannerComboBox, deleteButton) will be handled by the subsequent
                        // call to loadBannersForSelectedRefreshmentPoint().
                        // Automatically trigger loading banners for the first point if available.
                        // This will launch its own SwingWorker.
                        if (refreshmentPointComboBox.getSelectedItem() != null) {
                            loadBannersForSelectedRefreshmentPoint();
                        } else {
                            // If somehow no item is selected despite points existing (unlikely)
                            toggleInteractionComponents(false); // Disable everything.
                        }
                    }
                } catch (ServerConnectionException ex) {
                    statusLabel.setText("Status: Error - " + ex.getMessage());
                    JOptionPane.showMessageDialog(EliminaBannerApp.this,
                            "Failed to connect to ETOUR server: " + ex.getMessage(),
                            "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    // On connection error, disable ALL main interaction components
                    toggleInteractionComponents(false);
                } catch (Exception ex) { // Catch other potential exceptions from get() or unexpected issues
                    statusLabel.setText("Status: An unexpected error occurred: " + ex.getMessage());
                    JOptionPane.showMessageDialog(EliminaBannerApp.this,
                            "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    // On unexpected errors, disable ALL main interaction components
                    toggleInteractionComponents(false);
                }
            }
        }.execute(); // Start the SwingWorker
    }
    /**
     * Loads the list of banners associated with the currently selected refreshment point
     * and populates the bannerComboBox. Handles server connection errors.
     * This operation runs in a SwingWorker to prevent GUI freezing.
     * Corresponds to Use Case Step 2.
     */
    private void loadBannersForSelectedRefreshmentPoint() {
        RefreshmentPoint selectedPoint = (RefreshmentPoint) refreshmentPointComboBox.getSelectedItem();
        bannerComboBox.removeAllItems(); // Clear existing banners
        if (selectedPoint == null) {
            statusLabel.setText("Status: Select a refreshment point first.");
            updateDeleteButtonState();
            return;
        }
        statusLabel.setText("Status: Loading banners for " + selectedPoint.getName() + "...");
        // Disable banner selection and delete button during load to prevent interaction.
        bannerComboBox.setEnabled(false);
        deleteButton.setEnabled(false);
        new SwingWorker<java.util.List<Banner>, Void>() {
            private ServerConnectionException connectionException = null;
            // Capture ID for background thread to avoid issues if selection changes during async call
            private final int pointId = selectedPoint.getId();
            @Override
            protected java.util.List<Banner> doInBackground() {
                try {
                    return eTourServer.getBannersForRefreshmentPoint(pointId);
                } catch (ServerConnectionException e) {
                    connectionException = e;
                    return null;
                }
            }
            @Override
            protected void done() {
                // Ensure simulateConnectionInterruption is always enabled regardless of outcome initially.
                simulateConnectionInterruption.setEnabled(true);
                // Re-enable bannerComboBox initially as it was disabled before doInBackground().
                bannerComboBox.setEnabled(true);
                try {
                    if (connectionException != null) {
                        throw connectionException;
                    }
                    java.util.List<Banner> banners = get();
                    if (banners == null || banners.isEmpty()) {
                        statusLabel.setText("Status: No banners associated with " + selectedPoint.getName() + ".");
                    } else {
                        for (Banner banner : banners) {
                            bannerComboBox.addItem(banner);
                        }
                        statusLabel.setText("Status: Banners loaded for " + selectedPoint.getName() + ".");
                    }
                    // After successful loading (even if empty), ensure refreshmentPointComboBox is enabled
                    refreshmentPointComboBox.setEnabled(true);
                } catch (ServerConnectionException ex) {
                    statusLabel.setText("Status: Error - " + ex.getMessage());
                    JOptionPane.showMessageDialog(EliminaBannerApp.this,
                            "Failed to retrieve banners: " + ex.getMessage(),
                            "Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    // On connection error, disable ALL main interaction components
                    toggleInteractionComponents(false);
                } catch (Exception ex) { // Catch other potential exceptions from get() or unexpected issues
                    statusLabel.setText("Status: An unexpected error occurred: " + ex.getMessage());
                    JOptionPane.showMessageDialog(EliminaBannerApp.this,
                            "An unexpected error occurred: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    // On unexpected errors, disable ALL main interaction components
                    toggleInteractionComponents(false);
                } finally {
                    // Always update delete button state regardless of success/failure
                    updateDeleteButtonState();
                }
            }
        }.execute();
    }
    /**
     * Handles the deletion of the currently selected banner.
     * Prompts for confirmation and then calls the ETOUR server to perform
     * the deletion in a SwingWorker, preventing GUI freezing.
     * Updates UI status and reloads banners on success.
     * Corresponds to Use Case Steps 3, 4, 5, 6, and Exit Condition.
     */
    private void deleteSelectedBanner() {
        Banner selectedBanner = (Banner) bannerComboBox.getSelectedItem();
        if (selectedBanner == null) {
            statusLabel.setText("Status: No banner selected for deletion.");
            return;
        }
        // Use Case Step 4: Displays a message confirming the deletion.
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the banner:\n\"" + selectedBanner.getDescription() + "\"?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        // Use Case Step 5: Confirm the operation.
        if (confirm == JOptionPane.YES_OPTION) {
            statusLabel.setText("Status: Deleting banner ID " + selectedBanner.getId() + "...");
            toggleInteractionComponents(false); // Disable interaction during deletion
            new SwingWorker<Boolean, Void>() {
                private ServerConnectionException serverEx = null;
                private BannerNotFoundException notFoundEx = null;
                // Capture ID for background thread
                private final int bannerIdToDelete = selectedBanner.getId();
                @Override
                protected Boolean doInBackground() {
                    try {
                        return eTourServer.deleteBanner(bannerIdToDelete);
                    } catch (ServerConnectionException e) {
                        serverEx = e;
                        return false; // Indicate failure to delete due to server issue
                    } catch (BannerNotFoundException e) {
                        notFoundEx = e;
                        return false; // Indicate failure to delete as banner wasn't found
                    }
                }
                @Override
                protected void done() {
                    // Ensure simulateConnectionInterruption is always enabled regardless of outcome initially.
                    simulateConnectionInterruption.setEnabled(true);
                    // Re-enable refreshmentPointComboBox here, as it might have been disabled during the delete operation.
                    refreshmentPointComboBox.setEnabled(true);
                    try {
                        if (serverEx != null) {
                            throw serverEx;
                        }
                        if (notFoundEx != null) {
                            throw notFoundEx;
                        }
                        boolean success = get(); // Will be true if banner was found and removed
                        if (success) {
                            statusLabel.setText("Status: Successfully deleted banner: " + selectedBanner.getDescription());
                            JOptionPane.showMessageDialog(EliminaBannerApp.this,
                                    "Banner '" + selectedBanner.getDescription() + "' successfully deleted!",
                                    "Deletion Successful",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // This case generally shouldn't happen if exceptions are handled,
                            // but as a fallback, if for some reason deleteBanner returns false but no exception.
                            statusLabel.setText("Status: Deletion failed for unknown reason for banner ID " + bannerIdToDelete);
                            JOptionPane.showMessageDialog(EliminaBannerApp.this,
                                    "Deletion failed for unknown reason.",
                                    "Deletion Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        // Reload banners for the current refreshment point to reflect the change.
                        // This will trigger its own SwingWorker and manage bannerComboBox/deleteButton state.
                        loadBannersForSelectedRefreshmentPoint();
                    } catch (ServerConnectionException ex) {
                        // Exit condition: Interruption of the connection to the server ETOUR.
                        statusLabel.setText("Status: Error - " + ex.getMessage());
                        JOptionPane.showMessageDialog(EliminaBannerApp.this,
                                "Failed to delete banner due to server connection error: " + ex.getMessage(),
                                "Connection Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Disable main components on error.
                        toggleInteractionComponents(false);
                    } catch (BannerNotFoundException ex) {
                        // This typically means the banner was already deleted or doesn't exist.
                        statusLabel.setText("Status: Error - " + ex.getMessage());
                        JOptionPane.showMessageDialog(EliminaBannerApp.this,
                                "Deletion failed: " + ex.getMessage() + ". It might have already been deleted.",
                                "Deletion Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Try reloading banners to sync the UI with server state
                        loadBannersForSelectedRefreshmentPoint(); // This will enable/disable components as appropriate
                    } catch (Exception ex) { // Catch other potential exceptions from get() or unexpected issues
                        statusLabel.setText("Status: An unexpected error occurred: " + ex.getMessage());
                        JOptionPane.showMessageDialog(EliminaBannerApp.this,
                                "An unexpected error occurred: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                        // Disable main components on error.
                        toggleInteractionComponents(false);
                    }
                    // updateDeleteButtonState is called by loadBannersForSelectedRefreshmentPoint()'s done method
                }
            }.execute();
        } else {
            statusLabel.setText("Status: Banner deletion cancelled.");
            // Re-enable primary interaction components if user cancelled.
            // Note: toggleInteractionComponents(false) was called before the SwingWorker,
            // so we must re-enable if operation cancelled *before* background task started.
            // If it was cancelled while a task was running, the task's done() would handle it.
            // Since this block is only entered if the dialog returns NO_OPTION,
            // the SwingWorker for deletion wasn't launched, so we need to re-enable here.
            toggleInteractionComponents(true); 
            updateDeleteButtonState(); // Ensure button state is correct after cancellation
        }
    }
    /**
     * Updates the enabled/disabled state of the delete button based on
     * whether a banner is selected and a refreshment point is active.
     */
    private void updateDeleteButtonState() {
        deleteButton.setEnabled(
                refreshmentPointComboBox.getSelectedItem() != null &&
                bannerComboBox.getSelectedItem() != null
        );
    }
}