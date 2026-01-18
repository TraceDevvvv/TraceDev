'''
Main application window for the VISUALIZZASCHEDABENECULTURALE use case.
This class provides a graphical user interface for an Agency Operator to
view a list of cultural goods and then display the detailed card for
a selected item. It interacts with the ETOURService to fetch data.
'''
package com.chatdev.culturalviewer.gui;
import com.chatdev.culturalviewer.model.BeneCulturale;
import com.chatdev.culturalviewer.service.ETOURConnectionException;
import com.chatdev.culturalviewer.service.ETOURService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * '''
 * Main application window for the VISUALIZZASCHEDABENECULTURALE use case.
 * This class provides a graphical user interface for an Agency Operator to
 * view a list of cultural goods and then display the detailed card for
 * a selected item. It interacts with the ETOURService to fetch data.
 * '''
 */
public class CulturalGoodViewerApp extends JFrame {
    private JList<BeneCulturale> culturalGoodsList;
    private DefaultListModel<BeneCulturale> listModel;
    private JButton viewDetailsButton;
    private JButton simulateErrorButton; // Button to toggle error simulation
    private CulturalGoodDetailPanel detailPanel;
    private JLabel statusLabel;
    private ETOURService etourService;
    /**
     * '''
     * Constructs the main application frame, initializes all GUI components,
     * and sets up the event listeners. It also initializes the ETOURService.
     * '''
     */
    public CulturalGoodViewerApp() {
        super("VISUALIZZASCHEDABENECULTURALE - Cultural Goods Viewer");
        etourService = new ETOURService(); // Initialize the mock service
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // --- Left Panel: Cultural Goods List and Actions ---
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listModel = new DefaultListModel<>();
        culturalGoodsList = new JList<>(listModel);
        culturalGoodsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one item can be selected
        culturalGoodsList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane listScrollPane = new JScrollPane(culturalGoodsList);
        listScrollPane.setPreferredSize(new Dimension(300, 0)); // Set preferred width for the list
        leftPanel.add(new JLabel("Available Cultural Goods (RicercaBeneCulturale Result):", SwingConstants.CENTER), BorderLayout.NORTH);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        // Buttons Panel for "View Details" and "Simulate Error"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        viewDetailsButton = new JButton("View Details of Selected Cultural Good");
        simulateErrorButton = new JButton("Toggle ETOUR Server Connection Error (OFF)"); // Initial state is OFF
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(simulateErrorButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        // --- Right Panel: Details View ---
        detailPanel = new CulturalGoodDetailPanel();
        // Add a general status label at the bottom for messages/errors
        statusLabel = new JLabel("Ready: Select a cultural good and click 'View Details'.", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE); // Default informative color
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // Add panels to the main frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, detailPanel);
        splitPane.setResizeWeight(0.3); // Proportion of the split pane
        add(splitPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        // --- Event Listeners ---
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedCulturalGoodDetails();
            }
        });
        simulateErrorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the current simulation state from the service
                boolean currentSimulatingFailure = etourService.isSimulatingConnectionFailure();
                // Toggle the simulation state
                etourService.setSimulateConnectionFailure(!currentSimulatingFailure);
                // Update the button text to reflect the new state
                simulateErrorButton.setText("Toggle ETOUR Server Connection Error (" + (!currentSimulatingFailure ? "ON" : "OFF") + ")");
                // Update the status label
                setStatus("ETOUR server error simulation is now " + (!currentSimulatingFailure ? "ON" : "OFF") + ".", Color.ORANGE);
            }
        });
        // Initial load of cultural goods list when the application starts
        loadCulturalGoodsList();
    }
    /**
     * '''
     * Loads the list of cultural goods from the ETOURService and populates
     * the JList in the GUI. Handles potential connection errors during loading.
     * '''
     */
    private void loadCulturalGoodsList() {
        listModel.clear();
        setStatus("Loading cultural goods list...", Color.BLUE);
        // Use a SwingWorker or separate thread for network operations to keep UI responsive
        new SwingWorker<List<BeneCulturale>, Void>() {
            @Override
            protected List<BeneCulturale> doInBackground() throws ETOURConnectionException {
                return etourService.getAllCulturalGoods();
            }
            @Override
            protected void done() {
                try {
                    List<BeneCulturale> culturalGoods = get();
                    for (BeneCulturale culturalGood : culturalGoods) {
                        listModel.addElement(culturalGood);
                    }
                    setStatus("Cultural goods list loaded successfully. " + listModel.size() + " items found.", Color.BLACK);
                } catch (Exception ex) {
                    Throwable cause = ex.getCause();
                    if (cause instanceof ETOURConnectionException) {
                        setStatus("Error loading cultural goods: " + cause.getMessage(), Color.RED);
                    } else {
                        setStatus("An unexpected error occurred: " + ex.getMessage(), Color.RED);
                        ex.printStackTrace();
                    }
                }
            }
        }.execute();
    }
    /**
     * '''
     * Handles the action of viewing details for the selected cultural good.
     * It retrieves the selected item from the list, fetches its details
     * using ETOURService, and displays them in the detail panel.
     * It also handles connection interruptions.
     * '''
     */
    private void viewSelectedCulturalGoodDetails() {
        BeneCulturale selectedGood = culturalGoodsList.getSelectedValue();
        if (selectedGood == null) {
            setStatus("Please select a cultural good from the list first.", Color.ORANGE);
            detailPanel.clearDetails();
            return;
        }
        setStatus("Loading details for " + selectedGood.getName() + " (ID: " + selectedGood.getId() + ")...", Color.BLUE);
        detailPanel.clearDetails(); // Clear previous details while loading
        // Use a SwingWorker for network operations
        new SwingWorker<BeneCulturale, Void>() {
            @Override
            protected BeneCulturale doInBackground() throws ETOURConnectionException {
                // Simulate some processing time for a smoother user experience
                // (already handled by Thread.sleep in ETOURService, but good to remember for real async tasks)
                return etourService.getCulturalGoodDetails(selectedGood.getId());
            }
            @Override
            protected void done() {
                try {
                    BeneCulturale details = get(); // Get the result from doInBackground
                    detailPanel.displayCulturalGood(details);
                    setStatus("Details for '" + details.getName() + "' displayed successfully.", Color.BLACK);
                } catch (Exception ex) {
                    Throwable cause = ex.getCause();
                    if (cause instanceof ETOURConnectionException) {
                        // Exit condition: Interruption of the connection to the server ETOUR
                        setStatus("ETOUR Connection Error: " + cause.getMessage(), Color.RED);
                    } else {
                        setStatus("An unexpected error occurred: " + ex.getMessage(), Color.RED);
                        ex.printStackTrace();
                    }
                    detailPanel.clearDetails(); // Clear details on error
                }
            }
        }.execute(); // Execute the SwingWorker
    }
    /**
     * '''
     * Updates the status label in the GUI with the given message and color.
     * This method ensures thread-safe updates to the Swing UI.
     *
     * @param message The message to display.
     * @param color The color of the message text.
     * '''
     */
    private void setStatus(String message, Color color) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setForeground(color);
            statusLabel.setText(message);
        });
    }
    /**
     * '''
     * The main entry point for the CulturalGoodViewerApp.
     * Creates and displays the application frame on the Event Dispatch Thread.
     *
     * @param args Command line arguments (not used).
     * '''
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CulturalGoodViewerApp().setVisible(true);
            }
        });
    }
}