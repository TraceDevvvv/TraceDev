/**
 * A JPanel responsible for displaying a list of tourists and allowing selection.
 * It simulates the 'RicercaTurista' use case by fetching and displaying a list of tourists.
 * The panel includes a button to view the details of a selected tourist.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException; // Required for SwingWorker.get() exception handling
public class TouristListPanel extends JPanel {
    private JList<Tourist> touristJList;
    private DefaultListModel<Tourist> listModel;
    private JButton viewDetailsButton;
    private JButton refreshListButton; // Added for explicit refresh
    private ETOURService etourService;
    private TouristApp parentApp; // Reference to the main application frame
    /**
     * Constructs a new TouristListPanel.
     * @param etourService The ETOURService instance to fetch tourist data.
     * @param parentApp The main application frame to communicate back with.
     */
    public TouristListPanel(ETOURService etourService, TouristApp parentApp) {
        this.etourService = etourService;
        this.parentApp = parentApp;
        setLayout(new BorderLayout(10, 10)); // Add some spacing
        initComponents();
        // loadTouristList() should ideally be called after this panel is visible/activated,
        // rather than in the constructor, to avoid blocking UI during app startup.
        // The TouristApp will call it when switching to this card.
    }
    /**
     * Initializes and lays out the GUI components of the tourist list panel.
     */
    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Tourist List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        // Tourist list
        listModel = new DefaultListModel<>();
        touristJList = new JList<>(listModel);
        touristJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        touristJList.setFixedCellHeight(30); // Make items easier to click
        touristJList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(touristJList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Select a Tourist"));
        add(scrollPane, BorderLayout.CENTER);
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        viewDetailsButton = new JButton("View Tourist Details");
        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedTouristDetails();
            }
        });
        viewDetailsButton.setEnabled(false); // Initially disabled until a selection is made
        buttonPanel.add(viewDetailsButton);
        refreshListButton = new JButton("Refresh List");
        refreshListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTouristList();
            }
        });
        buttonPanel.add(refreshListButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Enable/disable viewDetailsButton based on selection
        touristJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensure event fires once selection is stable
                // Only enable if there's an actual tourist selected, not a placeholder
                Tourist selected = touristJList.getSelectedValue();
                viewDetailsButton.setEnabled(selected != null && !selected.getId().isEmpty());
            }
        });
    }
    /**
     * Loads the list of tourists from the ETOURService and updates the JList.
     * Handles potential connection errors.
     * This simulates the "RicercaTurista" use case.
     * This method is executed on the Event Dispatch Thread (EDT) but offloads the
     * network operation to a background thread using SwingWorker to maintain UI responsiveness.
     */
    public void loadTouristList() {
        listModel.clear(); // Clear previous list immediately on EDT
        listModel.addElement(new Tourist("", "Loading tourists...", "", "", "", "")); // Add a temporary loading indicator
        viewDetailsButton.setEnabled(false); // Disable button while loading
        refreshListButton.setEnabled(false); // Disable refresh button during loading
        // Use SwingWorker to perform the potentially long-running operation in a background thread
        new SwingWorker<List<Tourist>, Void>() {
            private ETOURConnectionException connectionException = null; // To store potential connection errors
            /**
             * Performs the actual data fetching in a separate thread.
             * @return A list of Tourist objects, or null if a connection error occurred.
             */
            @Override
            protected List<Tourist> doInBackground() {
                try {
                    return etourService.getTouristList();
                } catch (ETOURConnectionException e) {
                    connectionException = e; // Store the exception to handle on the EDT
                    return null; // Return null to indicate failure or no result
                }
            }
            /**
             * This method is executed on the Event Dispatch Thread after doInBackground() completes.
             * It updates the GUI with the results or error messages.
             */
            @Override
            protected void done() {
                listModel.clear(); // Clear the "Loading tourists..." indicator
                try {
                    // If a connection error was caught in doInBackground, re-throw it here
                    if (connectionException != null) {
                        throw connectionException;
                    }
                    List<Tourist> tourists = get(); // Retrieve the result from doInBackground()
                    if (tourists == null || tourists.isEmpty()) {
                        listModel.addElement(new Tourist("", "No tourists found.", "", "", "", ""));
                        viewDetailsButton.setEnabled(false); // No tourists, so view details is not possible
                    } else {
                        for (Tourist tourist : tourists) {
                            listModel.addElement(tourist);
                        }
                        // The selection listener will handle enabling viewDetailsButton if a tourist is selected
                    }
                } catch (ETOURConnectionException e) {
                    // Handle and display connection errors
                    JOptionPane.showMessageDialog(TouristListPanel.this,
                            "<html><b>Error:</b> " + e.getMessage() + "<br>Please check server connection.</html>",
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                    listModel.addElement(new Tourist("", "Failed to load tourists.", "", "", "", ""));
                    viewDetailsButton.setEnabled(false); // On error, disable button
                } catch (InterruptedException | ExecutionException e) {
                    // Handle other exceptions that might occur during SwingWorker execution
                    JOptionPane.showMessageDialog(TouristListPanel.this,
                            "<html>An unexpected error occurred while loading tourist list: " + e.getMessage() + "</html>",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    listModel.addElement(new Tourist("", "Failed to load tourists due to internal error.", "", "", "", ""));
                    viewDetailsButton.setEnabled(false); // On error, disable button
                } finally {
                    refreshListButton.setEnabled(true); // Re-enable refresh button
                    // The viewDetailsButton state is managed by the ListSelectionListener
                    // but if the list ends up empty, explicit disable is needed.
                    if (listModel.isEmpty() || (listModel.size() == 1 && listModel.getElementAt(0).getId().isEmpty())) {
                         viewDetailsButton.setEnabled(false);
                    } else if (touristJList.getSelectedIndex() != -1) {
                         // Re-enable if there's a selection and the list isn't empty/error placeholder
                         if(!touristJList.getSelectedValue().getId().isEmpty()) {
                            viewDetailsButton.setEnabled(true);
                         }
                    }
                }
            }
        }.execute(); // Start the SwingWorker
    }
    /**
     * Initiates the 'VISUALIZZASCHEDATURISTA' use case by fetching and displaying
     * the details of the currently selected tourist.
     */
    private void viewSelectedTouristDetails() {
        Tourist selectedTourist = touristJList.getSelectedValue();
        if (selectedTourist != null && !selectedTourist.getId().isEmpty()) {
            parentApp.showTouristDetails(selectedTourist.getId()); // Delegate to main app to show details
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid tourist from the list.", "No Tourist Selected", JOptionPane.WARNING_MESSAGE);
        }
    }
}