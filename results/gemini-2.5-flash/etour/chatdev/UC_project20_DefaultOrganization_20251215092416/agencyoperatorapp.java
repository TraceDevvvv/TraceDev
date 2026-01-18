'''
Main JFrame for the Agency Operator application.
It acts as the primary container for different panels, managing the navigation flow
between selecting a point of rest and inserting a banner.
'''
package com.chatdev.bannerapp.gui;
import com.chatdev.bannerapp.model.PointOfRest;
import com.chatdev.bannerapp.service.BannerService;
import javax.swing.*;
import java.awt.*;
public class AgencyOperatorApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private BannerService bannerService;
    private PointOfRestSelectionPanel pointOfRestSelectionPanel;
    private BannerInsertionPanel bannerInsertionPanel;
    /**
     * Constructs the main application window for the Agency Operator.
     * Initializes the service layer and sets up the CardLayout to switch between different views.
     */
    public AgencyOperatorApp() {
        setTitle("Agency Operator - Banner Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        bannerService = new BannerService();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Initialize panels
        pointOfRestSelectionPanel = new PointOfRestSelectionPanel(bannerService, this::showBannerInsertionPanel, this::handleServiceError);
        mainPanel.add(pointOfRestSelectionPanel, "PointOfRestSelection");
        // The bannerInsertionPanel will be created dynamically when a point of rest is selected
        // This is to ensure it is initialized with the correct selected PointOfRest object
        add(mainPanel);
        // Initial view
        cardLayout.show(mainPanel, "PointOfRestSelection");
    }
    /**
     * Callback method to display the Banner Insertion Panel for a selected Point of Rest.
     *
     * @param selectedPoint The PointOfRest object for which banners are to be inserted.
     */
    private void showBannerInsertionPanel(PointOfRest selectedPoint) {
        // Remove old panel if it exists and create a new one
        if (bannerInsertionPanel != null) {
            mainPanel.remove(bannerInsertionPanel);
        }
        bannerInsertionPanel = new BannerInsertionPanel(bannerService, selectedPoint, this::showPointOfRestSelectionPanel, this::handleServiceError);
        mainPanel.add(bannerInsertionPanel, "BannerInsertion");
        cardLayout.show(mainPanel, "BannerInsertion");
    }
    /**
     * Callback method to return to the Point of Rest Selection Panel.
     * This is typically called after a banner insertion attempt or cancellation.
     */
    private void showPointOfRestSelectionPanel() {
        // Refresh the pointOfRestSelectionPanel data in case banners were added
        pointOfRestSelectionPanel.refreshPointsOfRest();
        cardLayout.show(mainPanel, "PointOfRestSelection");
    }
    /**
     * Handles service-level errors (e.g., connection interruptions).
     * Displays an error dialog to the user.
     * @param errorTitle The title for the error dialog, providing context.
     * @param errorMessage The description of the error.
     */
    private void handleServiceError(String errorTitle, String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
        // Depending on the severity of the error, you might want to exit or reset.
        // For ETOUR, returning to selection panel might be appropriate, or even restart flow.
        showPointOfRestSelectionPanel(); // Return to selection after error for a cleaner state
    }
}