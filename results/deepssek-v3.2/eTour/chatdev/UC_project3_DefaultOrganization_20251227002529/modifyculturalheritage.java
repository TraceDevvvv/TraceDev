'''
Main class for the Modify Cultural Heritage application.
This GUI application allows an agency operator to modify cultural heritage data.
It simulates a system where an operator logs in, searches for cultural items,
selects one to modify, edits its data, and submits the changes with validation.
Includes proper form blocking to prevent multiple submissions and simulates
server connection interruptions as per the use case requirements.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
public class ModifyCulturalHeritage {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField searchField;
    private JList<String> heritageList;
    private DefaultListModel<String> listModel;
    private HashMap<Integer, CulturalHeritage> heritageData;
    private AtomicBoolean connectionActive; // To simulate server connection status
    private Agency currentAgency; // Track the currently logged-in agency
    // Form fields for editing
    private JTextField nameField;
    private JTextField typeField;
    private JTextField locationField;
    private JTextField yearField;
    private JButton submitButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private int selectedId; // ID of the currently selected cultural heritage item
    /**
     * Constructor - initializes the data and GUI.
     */
    public ModifyCulturalHeritage() {
        // Simulate initial data (in a real application, this would come from a database or server)
        heritageData = new HashMap<>();
        heritageData.put(1, new CulturalHeritage(1, "Ancient Vase", "Artifact", "Athens", "500 BC"));
        heritageData.put(2, new CulturalHeritage(2, "Medieval Manuscript", "Document", "Florence", "1300 AD"));
        heritageData.put(3, new CulturalHeritage(3, "Historic Fort", "Monument", "Delhi", "1600 AD"));
        connectionActive = new AtomicBoolean(true); // Assume connection is active at start
        currentAgency = null; // No agency logged in initially
        initializeGUI();
    }
    /**
     * Initializes the GUI components and layout.
     */
    private void initializeGUI() {
        frame = new JFrame("Cultural Heritage Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        // Panel with CardLayout to manage different views
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Create the different panels (screens)
        JPanel loginPanel = createLoginPanel();
        JPanel searchPanel = createSearchPanel();
        JPanel editPanel = createEditPanel();
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(editPanel, "Edit");
        frame.add(mainPanel, BorderLayout.CENTER);
        // Initially show the login panel
        cardLayout.show(mainPanel, "Login");
        frame.setVisible(true);
    }
    /**
     * Creates the login panel.
     * @return JPanel for login.
     */
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel titleLabel = new JLabel("Agency Operator Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);
        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userField, gbc);
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);
        JPasswordField passField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passField, gbc);
        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                // Simulate agency authentication
                Agency authenticatedAgency = Agency.authenticate(username, password);
                if (authenticatedAgency != null) {
                    currentAgency = authenticatedAgency;
                    JOptionPane.showMessageDialog(frame, 
                        "Login successful! Agency: " + currentAgency.getName(),
                        "Authentication Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "Search");
                    loadAllHeritageData();
                } else {
                    JOptionPane.showMessageDialog(frame, 
                        "Invalid agency credentials. Please try again.\n\n" +
                        "Available demo credentials:\n" +
                        "Username: agency1, Password: pass123\n" +
                        "Username: admin, Password: admin123",
                        "Authentication Failed", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(loginButton, gbc);
        return panel;
    }
    /**
     * Creates the search panel where the user can view and select a cultural heritage item.
     * @return JPanel for search.
     */
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Top panel with search bar and agency info
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchBarPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        searchBarPanel.add(new JLabel("Search: "));
        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);
        // Agency info panel
        JPanel agencyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel agencyLabel = new JLabel("Agency: Not logged in");
        agencyPanel.add(agencyLabel);
        // Update agency label when panel is shown
        JPanel finalPanel = panel;
        final JLabel finalAgencyLabel = agencyLabel;
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                if (currentAgency != null) {
                    finalAgencyLabel.setText("Agency: " + currentAgency.getName() + " (" + currentAgency.getId() + ")");
                } else {
                    finalAgencyLabel.setText("Agency: Not logged in");
                }
                finalPanel.revalidate();
                finalPanel.repaint();
            }
        });
        topPanel.add(searchBarPanel, BorderLayout.CENTER);
        topPanel.add(agencyPanel, BorderLayout.EAST);
        // List for displaying cultural heritage items
        listModel = new DefaultListModel<>();
        heritageList = new JList<>(listModel);
        heritageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(heritageList);
        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton editButton = new JButton("Edit Selected");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectedItem();
            }
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
        bottomPanel.add(editButton);
        bottomPanel.add(logoutButton);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }
    /**
     * Handles logout action.
     */
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(frame,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            currentAgency = null;
            connectionActive.set(true); // Reset connection status
            listModel.clear();
            searchField.setText("");
            cardLayout.show(mainPanel, "Login");
        }
    }
    /**
     * Performs a search for cultural heritage items based on user query.
     * Simulates the SearchCulturalHeritage use case functionality.
     */
    private void performSearch() {
        // Check if agency is logged in
        if (currentAgency == null) {
            JOptionPane.showMessageDialog(frame,
                "No agency logged in. Please login first.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE);
            cardLayout.show(mainPanel, "Login");
            return;
        }
        // Check server connection before performing search
        if (!connectionActive.get()) {
            JOptionPane.showMessageDialog(frame, 
                "Connection to server ETOUR interrupted. Cannot perform search.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        String query = searchField.getText().toLowerCase().trim();
        listModel.clear();
        if (query.isEmpty()) {
            // Show all items if search field is empty
            loadAllHeritageData();
        } else {
            // Perform search simulation
            boolean foundResults = false;
            for (CulturalHeritage ch : heritageData.values()) {
                if (ch.getName().toLowerCase().contains(query) || 
                    ch.getType().toLowerCase().contains(query) ||
                    ch.getLocation().toLowerCase().contains(query) ||
                    ch.getYear().toLowerCase().contains(query)) {
                    listModel.addElement(ch.getId() + ": " + ch.getName() + " - " + ch.getLocation());
                    foundResults = true;
                }
            }
            if (!foundResults) {
                listModel.addElement("No results found for: \"" + query + "\"");
            }
        }
    }
    /**
     * Loads all cultural heritage items into the list.
     * This simulates the initial state of the SearchCulturalHeritage use case.
     */
    private void loadAllHeritageData() {
        listModel.clear();
        for (CulturalHeritage ch : heritageData.values()) {
            listModel.addElement(ch.getId() + ": " + ch.getName() + " - " + ch.getLocation());
        }
        // Clear search field when showing all items
        searchField.setText("");
    }
    /**
     * Handles editing of the selected cultural heritage item.
     */
    private void editSelectedItem() {
        // Check if agency is logged in
        if (currentAgency == null) {
            JOptionPane.showMessageDialog(frame,
                "No agency logged in. Please login first.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE);
            cardLayout.show(mainPanel, "Login");
            return;
        }
        int selectedIndex = heritageList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an item to edit.");
            return;
        }
        String selectedValue = listModel.get(selectedIndex);
        // Check if this is a "no results" message
        if (selectedValue.startsWith("No results found")) {
            JOptionPane.showMessageDialog(frame, "Please select a valid cultural heritage item to edit.");
            return;
        }
        // Parse ID from the list item (format: "ID: Name - Location")
        try {
            selectedId = Integer.parseInt(selectedValue.split(":")[0]);
            // Check if item exists in our data (simulating server validation)
            if (!heritageData.containsKey(selectedId)) {
                JOptionPane.showMessageDialog(frame, "Selected item no longer exists in the system.");
                return;
            }
            loadHeritageData(selectedId);
            cardLayout.show(mainPanel, "Edit");
            // Reset form state when entering edit mode
            setFormEnabled(true);
            submitButton.setEnabled(true);
            confirmButton.setEnabled(false);
            connectionActive.set(true);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(frame, "Error parsing item ID. Please select a valid item.");
        }
    }
    /**
     * Creates the edit panel where the user can modify the selected cultural heritage item.
     * @return JPanel for editing.
     */
    private JPanel createEditPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel titleLabel = new JLabel("Edit Cultural Heritage Data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        // Agency info label
        JLabel agencyInfoLabel = new JLabel();
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        agencyInfoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        panel.add(agencyInfoLabel, gbc);
        // Update agency info when panel is shown
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                if (currentAgency != null) {
                    agencyInfoLabel.setText("Editing as: " + currentAgency.getName());
                } else {
                    agencyInfoLabel.setText("Not logged in");
                }
                panel.revalidate();
                panel.repaint();
            }
        });
        // Name field
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(new JLabel("Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(nameField, gbc);
        // Type field
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Type:"), gbc);
        typeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(typeField, gbc);
        // Location field
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Location:"), gbc);
        locationField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(locationField, gbc);
        // Year field
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Year:"), gbc);
        yearField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(yearField, gbc);
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        submitButton = new JButton("Submit Changes");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        confirmButton = new JButton("Confirm Save");
        confirmButton.setEnabled(false); // Initially disabled
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConfirm();
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancel();
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        panel.add(buttonPanel, gbc);
        return panel;
    }
    /**
     * Handles the submit action for form changes.
     */
    private void handleSubmit() {
        // Check if agency is logged in (double-check for security)
        if (currentAgency == null) {
            JOptionPane.showMessageDialog(frame,
                "Session expired. Please login again.",
                "Authentication Required",
                JOptionPane.WARNING_MESSAGE);
            cardLayout.show(mainPanel, "Login");
            return;
        }
        if (!validateForm()) {
            // Data invalid, activate "Errored" use case (simulated by a message)
            JOptionPane.showMessageDialog(frame, 
                "Invalid or insufficient data. Please correct errors.\n\n" +
                "All fields must be filled in properly.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to save these changes?\n\n" +
            "Agency: " + currentAgency.getName() + "\n" +
            "Item ID: " + selectedId,
            "Confirm Changes", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Disable form immediately to prevent multiple submissions
            setFormEnabled(false);
            submitButton.setEnabled(false);
            // Simulate server operation with delay and connection check
            new Thread(() -> {
                try {
                    // Simulate server processing time
                    Thread.sleep(2000);
                    // Randomly simulate connection interruption (10% chance)
                    if (Math.random() < 0.1) {
                        connectionActive.set(false);
                    }
                    SwingUtilities.invokeLater(() -> {
                        if (!connectionActive.get()) {
                            JOptionPane.showMessageDialog(frame, 
                                "Connection to server ETOUR interrupted. Operation cancelled.", 
                                "Connection Error", 
                                JOptionPane.ERROR_MESSAGE);
                            setFormEnabled(true);
                            submitButton.setEnabled(true);
                            connectionActive.set(true); // Reset connection
                        } else {
                            // Enable confirm button only after successful server response
                            confirmButton.setEnabled(true);
                        }
                    });
                } catch (InterruptedException ex) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(frame, 
                            "Operation interrupted. Please try again.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        setFormEnabled(true);
                        submitButton.setEnabled(true);
                    });
                }
            }).start();
        }
    }
    /**
     * Handles the confirmation action to save changes.
     */
    private void handleConfirm() {
        // Check agency status
        if (currentAgency == null) {
            JOptionPane.showMessageDialog(frame,
                "Session expired. Changes cannot be saved.",
                "Authentication Error",
                JOptionPane.ERROR_MESSAGE);
            resetFormState();
            cardLayout.show(mainPanel, "Login");
            return;
        }
        // Re-check connection status
        if (!connectionActive.get()) {
            JOptionPane.showMessageDialog(frame, 
                "Connection to server lost. Operation cancelled.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            resetFormState();
            return;
        }
        // Save data only after successful confirmation
        if (heritageData.containsKey(selectedId)) {
            heritageData.get(selectedId).setName(nameField.getText().trim());
            heritageData.get(selectedId).setType(typeField.getText().trim());
            heritageData.get(selectedId).setLocation(locationField.getText().trim());
            heritageData.get(selectedId).setYear(yearField.getText().trim());
            JOptionPane.showMessageDialog(frame, 
                "Cultural heritage data modified successfully!\n\n" +
                "Modified by: " + currentAgency.getName() + "\n" +
                "Item ID: " + selectedId + "\n" +
                "Item Name: " + nameField.getText().trim(),
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            // Return to search panel and refresh the list
            cardLayout.show(mainPanel, "Search");
            loadAllHeritageData();
        }
        resetFormState();
    }
    /**
     * Handles the cancel action.
     */
    private void handleCancel() {
        // Ask for confirmation if changes were made
        if (dataChanged()) {
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "You have unsaved changes. Cancel anyway?\n\n" +
                "Agency: " + (currentAgency != null ? currentAgency.getName() : "Not logged in"),
                "Confirm Cancel", 
                JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }
        // Return to search panel without saving
        cardLayout.show(mainPanel, "Search");
        resetFormState();
    }
    /**
     * Loads the data of the selected cultural heritage item into the form.
     * @param id The ID of the item to load.
     */
    private void loadHeritageData(int id) {
        if (heritageData.containsKey(id)) {
            CulturalHeritage ch = heritageData.get(id);
            nameField.setText(ch.getName());
            typeField.setText(ch.getType());
            locationField.setText(ch.getLocation());
            yearField.setText(ch.getYear());
        }
    }
    /**
     * Validates the form data.
     * @return true if data is valid, false otherwise.
     */
    private boolean validateForm() {
        // Basic validation: no empty fields
        if (nameField.getText().trim().isEmpty() ||
            typeField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() ||
            yearField.getText().trim().isEmpty()) {
            return false;
        }
        // Additional validation could be added here (e.g., year format)
        return true;
    }
    /**
     * Enables or disables the form fields and buttons.
     * This is used to block input after submission until operation completes.
     * @param enabled true to enable, false to disable.
     */
    private void setFormEnabled(boolean enabled) {
        nameField.setEnabled(enabled);
        typeField.setEnabled(enabled);
        locationField.setEnabled(enabled);
        yearField.setEnabled(enabled);
        cancelButton.setEnabled(enabled);
    }
    /**
     * Resets the form to its initial enabled state.
     */
    private void resetFormState() {
        setFormEnabled(true);
        submitButton.setEnabled(true);
        confirmButton.setEnabled(false);
        connectionActive.set(true); // Reset connection status
    }
    /**
     * Checks if the form data has changed from the originally loaded data.
     * @return true if changed, false otherwise.
     */
    private boolean dataChanged() {
        if (!heritageData.containsKey(selectedId)) return false;
        CulturalHeritage original = heritageData.get(selectedId);
        return !nameField.getText().equals(original.getName()) ||
               !typeField.getText().equals(original.getType()) ||
               !locationField.getText().equals(original.getLocation()) ||
               !yearField.getText().equals(original.getYear());
    }
    /**
     * Inner class representing a Cultural Heritage item.
     */
    class CulturalHeritage {
        private int id;
        private String name;
        private String type;
        private String location;
        private String year;
        public CulturalHeritage(int id, String name, String type, String location, String year) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.location = location;
            this.year = year;
        }
        // Getters and setters
        public int getId() { return id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getLocation() { return location; }
        public void setLocation(String location) { this.location = location; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
    }
    /**
     * Class representing an Agency with authentication capabilities.
     */
    static class Agency {
        private String id;
        private String name;
        private String username;
        private String password;
        // Simulated agency database
        private static final Map<String, Agency> AGENCIES = new HashMap<>();
        static {
            AGENCIES.put("agency1", new Agency("AG001", "Cultural Heritage Agency", "agency1", "pass123"));
            AGENCIES.put("admin", new Agency("ADMIN", "System Administrator", "admin", "admin123"));
        }
        public Agency(String id, String name, String username, String password) {
            this.id = id;
            this.name = name;
            this.username = username;
            this.password = password;
        }
        /**
         * Authenticates an agency based on username and password.
         * @param username The username to authenticate
         * @param password The password to verify
         * @return Authenticated Agency object or null if authentication fails
         */
        public static Agency authenticate(String username, String password) {
            Agency agency = AGENCIES.get(username);
            if (agency != null && agency.password.equals(password)) {
                return agency;
            }
            return null;
        }
        public String getId() { return id; }
        public String getName() { return name; }
        public String getUsername() { return username; }
    }
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ModifyCulturalHeritage();
            }
        });
    }
}