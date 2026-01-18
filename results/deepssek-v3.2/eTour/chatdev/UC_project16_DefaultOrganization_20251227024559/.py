/**
 * Main entry point for the Tourist Account Deletion application.
 * This program provides a GUI for an agency operator to search for and delete tourist accounts.
 * It simulates a connection to a server and handles deletion with confirmation.
 */
import javax.swing.*;
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
                    searchTourists(query); // Simulates use case SearchTourist
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Select Tourist to Delete"));
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
     * Simulates searching for tourists (Step 1 in use case: Tourist list from SearchTourist).
     * Filters the list based on the query string.
     * @param query The search string.
     */
    private void searchTourists(String query) {
        List<Tourist> filtered = new ArrayList<>();
        for (Tourist tourist : tourists) {
            if (tourist.getName().toLowerCase().contains(query.toLowerCase()) ||
                    tourist.getId().toLowerCase().contains(query.toLowerCase()) ||
                    tourist.getEmail().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(tourist);
            }
        }
        // Update list model with filtered results
        listModel.clear();
        for (Tourist tourist : filtered) {
            listModel.addElement(tourist.getName() + " (" + tourist.getId() + ")");
        }
        if (filtered.isEmpty()) {
            listModel.addElement("No tourists found.");
        }
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
            JOptionPane.showMessageDialog(frame, "Please select a tourist from the list.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Step 2: Ask for confirmation
        int confirmation = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to delete the selected tourist account?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Step 3: Confirm operation (proceed with deletion)
            // Map list index to tourist in the listModel
            String selectedItem = listModel.getElementAt(selectedIndex);
            String touristId = selectedItem.substring(selectedItem.indexOf("(") + 1, selectedItem.indexOf(")"));
            // Step 4: Delete the selected data
            Tourist toRemove = null;
            for (Tourist tourist : tourists) {
                if (tourist.getId().equals(touristId)) {
                    toRemove = tourist;
                    break;
                }
            }
            if (toRemove != null) {
                tourists.remove(toRemove);
                updateListModel();
                logger.log(Level.INFO, "Tourist account deleted: " + toRemove.getId());
                // Simulate connection interruption to server ETOUR (quality requirement)
                simulateConnectionInterruption();
                // Step: Notification of elimination (exit condition)
                JOptionPane.showMessageDialog(frame,
                        "Tourist account " + touristId + " has been successfully deleted.",
                        "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Tourist not found in data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Deletion cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Simulates interruption of the connection to the server ETOUR.
     * Logs a message to simulate the event.
     */
    private void simulateConnectionInterruption() {
        // Simulate a network interruption (e.g., for a few seconds)
        logger.log(Level.WARNING, "Connection to server ETOUR interrupted (simulated).");
        // In a real application, you might close sockets or handle network exceptions.
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
     */
    public static void main(String[] args) {
        // Ensure GUI creation is thread-safe using Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeleteTouristAccount();
                logger.log(Level.INFO, "Tourist Account Deletion System started.");
            }
        });
    }
}