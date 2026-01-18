/**
 * Main entry point for the Tourist Account Deletion application.
 * This program provides a GUI for an agency operator to search for and delete tourist accounts.
 * It simulates a connection to a server and handles deletion with confirmation.
 * The search functionality is implemented as a separate use case as specified.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DeleteTouristAccount {
    // Logger for logging events and errors
    private static final Logger logger = Logger.getLogger(DeleteTouristAccount.class.getName());
    // Simulated data: list of tourists
    private List<Tourist> tourists = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> touristList;
    private JFrame frame;
    // Constructor: initializes the application
    public DeleteTouristAccount() {
        initializeData();
        createAndShowGUI();
    }
    /**
     * Initializes the tourist data with sample entries.
     */
    private void initializeData() {
        tourists.add(new Tourist("T001", "John Doe", "john.doe@example.com"));
        tourists.add(new Tourist("T002", "Jane Smith", "jane.smith@example.com"));
        tourists.add(new Tourist("T003", "Alice Johnson", "alice.johnson@example.com"));
        tourists.add(new Tourist("T004", "Bob Brown", "bob.brown@example.com"));
        updateListModel();
    }
    /**
     * Updates the list model with current tourist names.
     */
    private void updateListModel() {
        listModel.clear();
        for (Tourist tourist : tourists) {
            listModel.addElement(tourist.getName() + " (" + tourist.getId() + ")");
        }
    }
    /**
     * Creates and displays the GUI.
     */
    private void createAndShowGUI() {
        // Set up the main frame
        frame = new JFrame("Tourist Account Deletion System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        // Top panel: search functionality (simulated search)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel searchLabel = new JLabel("Search Tourist:");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().trim();
                if (!query.isEmpty()) {
                    // Step 1: Activate the SearchTourist use case as specified in requirements
                    searchTourists(query);
                } else {
                    updateListModel(); // Reset to all tourists if search is empty
                }
            }
        });
        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        // Center panel: list of tourists
        touristList = new JList<>(listModel);
        touristList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(touristList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Select Tourist to Delete", 
            TitledBorder.CENTER, TitledBorder.TOP));
        // Bottom panel: delete button
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected Account");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTourist(); // Corresponds to step 1 and 2 in use case
            }
        });
        bottomPanel.add(deleteButton);
        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        // Center and display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Simulates searching for tourists by activating the SearchTourist use case.
     * Step 1 in use case: "Tourists from the list obtained by activating the use case SearchTourist"
     * @param query The search string.
     */
    private void searchTourists(String query) {
        if (query == null || query.trim().isEmpty()) {
            updateListModel();
            return;
        }
        // Create and activate the SearchTourist use case
        SearchTourist searchUseCase = new SearchTourist();
        List<SearchTourist.Tourist> filtered = searchUseCase.search(
            convertToSearchTouristList(tourists), query);
        // Update list model with filtered results
        listModel.clear();
        for (SearchTourist.Tourist tourist : filtered) {
            listModel.addElement(tourist.getName() + " (" + tourist.getId() + ")");
        }
        if (filtered.isEmpty()) {
            listModel.addElement("No tourists found.");
        }
    }
    /**
     * Converts internal Tourist list to SearchTourist.Tourist list.
     * @param internalList The internal list of tourists
     * @return List of SearchTourist.Tourist objects
     */
    private List<SearchTourist.Tourist> convertToSearchTouristList(List<Tourist> internalList) {
        List<SearchTourist.Tourist> result = new ArrayList<>();
        for (Tourist t : internalList) {
            result.add(new SearchTourist.Tourist(t.getId(), t.getName(), t.getEmail()));
        }
        return result;
    }
    /**
     * Handles deletion of the selected tourist account.
     * Steps:
     * 1. Tourist selected from list (simulated by JList selection).
     * 2. Asks for confirmation (Step 2 in use case).
     * 3. Confirm operation (Step 3 in use case).
     * 4. Delete selected data (Step 4 in use case).
     */
    private void deleteSelectedTourist() {
        int selectedIndex = touristList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, 
                "Please select a tourist from the list.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        String selectedItem = listModel.getElementAt(selectedIndex);
        // Handle case when "No tourists found." is displayed
        if (selectedItem.equals("No tourists found.")) {
            JOptionPane.showMessageDialog(frame, 
                "No tourist selected. Please search and select a valid tourist.", 
                "Invalid Selection", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Extract tourist ID from the display string
        String touristId = "";
        try {
            touristId = selectedItem.substring(selectedItem.indexOf("(") + 1, selectedItem.indexOf(")"));
        } catch (StringIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Error parsing tourist ID from: " + selectedItem, e);
            JOptionPane.showMessageDialog(frame, 
                "Error processing selected tourist. Please try again.", 
                "Processing Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Step 2: Ask for confirmation
        int confirmation = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to delete tourist account: " + touristId + "?",
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Step 3: Confirm operation (proceed with deletion)
            Tourist toRemove = null;
            for (Tourist tourist : tourists) {
                if (tourist.getId().equals(touristId)) {
                    toRemove = tourist;
                    break;
                }
            }
            if (toRemove != null) {
                try {
                    // Step 4: Delete the selected data
                    tourists.remove(toRemove);
                    updateListModel();
                    logger.log(Level.INFO, "Tourist account deleted: " + toRemove.getId());
                    // Simulate connection interruption to server ETOUR (quality requirement)
                    simulateConnectionInterruption();
                    // Step: Notification of elimination (exit condition)
                    JOptionPane.showMessageDialog(frame,
                            "Tourist account " + touristId + " has been successfully deleted.",
                            "Deletion Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error deleting tourist account: " + touristId, e);
                    JOptionPane.showMessageDialog(frame, 
                        "Error deleting tourist account. Please try again.", 
                        "Deletion Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                logger.log(Level.WARNING, "Attempted to delete non-existent tourist: " + touristId);
                JOptionPane.showMessageDialog(frame, 
                    "Tourist not found in data.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // User cancelled the operation
            JOptionPane.showMessageDialog(frame, 
                "Deletion cancelled.", 
                "Cancelled", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Simulates interruption of the connection to the server ETOUR.
     * Logs a message to simulate the event.
     */
    private void simulateConnectionInterruption() {
        // Simulate a network interruption by logging a warning
        logger.log(Level.WARNING, "Connection to server ETOUR interrupted (simulated).");
        // In a real application, this would trigger network exception handling
    }
    /**
     * Inner class representing a Tourist.
     * Encapsulates tourist data.
     */
    private static class Tourist {
        private String id;
        private String name;
        private String email;
        public Tourist(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
    }
    /**
     * Main method to launch the application.
     * Ensures GUI creation is thread-safe using Event Dispatch Thread.
     */
    public static void main(String[] args) {
        // Configure logger to show messages in console
        logger.setLevel(Level.ALL);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new DeleteTouristAccount();
                    logger.log(Level.INFO, "Tourist Account Deletion System started successfully.");
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Failed to start Tourist Account Deletion System", e);
                    JOptionPane.showMessageDialog(null,
                        "Failed to start application: " + e.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}