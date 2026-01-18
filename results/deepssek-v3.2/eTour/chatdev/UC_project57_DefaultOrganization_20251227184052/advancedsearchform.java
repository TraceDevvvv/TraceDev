/**
 * AdvancedSearchForm provides the advanced search interface for tourists.
 * Flow of events: 
 * 1. Tourist enables advanced search from personal area (simplified as direct access)
 * 2. Views the advanced search form
 * 3. Fills the form and submits
 * 4. Gets position based on tourist's location and processes request
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
class AdvancedSearchForm extends JFrame {
    private JTextField keywordField;
    private JComboBox<String> categoryComboBox;
    private JTextField locationField;
    private JFormattedTextField startDateField;
    private JFormattedTextField endDateField;
    private JTextArea statusArea;
    // Search parameters
    private static final String[] CATEGORIES = {
        "All Categories", "Historical Sites", "Museums", "Parks", 
        "Restaurants", "Hotels", "Shopping", "Entertainment"
    };
    public AdvancedSearchForm() {
        super("ETOUR - Advanced Search");
        initializeUI();
    }
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Advanced Search", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel with GridBagLayout for flexible form layout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Keyword
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        formPanel.add(new JLabel("Keyword:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        keywordField = new JTextField(20);
        formPanel.add(keywordField, gbc);
        // Category
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        categoryComboBox = new JComboBox<>(CATEGORIES);
        formPanel.add(categoryComboBox, gbc);
        // Location
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        formPanel.add(new JLabel("Location (City):"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        locationField = new JTextField(20);
        locationField.setText("Current Location: Paris, France"); // Simulated location
        formPanel.add(locationField, gbc);
        // Date Range
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        formPanel.add(new JLabel("Start Date:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        startDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        startDateField.setValue(new Date());
        formPanel.add(startDateField, gbc);
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.3;
        formPanel.add(new JLabel("End Date:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7;
        endDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7); // Default: 7 days from now
        endDateField.setValue(cal.getTime());
        formPanel.add(endDateField, gbc);
        // Status area
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 0.3;
        statusArea = new JTextArea(3, 40);
        statusArea.setEditable(false);
        statusArea.setBorder(BorderFactory.createTitledBorder("Status"));
        statusArea.setText("Ready for search. Please fill in the form and click Search.");
        formPanel.add(new JScrollPane(statusArea), gbc);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        JButton logoutButton = new JButton("Logout");
        // Search button action - simulates server call with timeout
        searchButton.addActionListener(e -> performSearch());
        // Clear button
        clearButton.addActionListener(e -> {
            keywordField.setText("");
            categoryComboBox.setSelectedIndex(0);
            startDateField.setValue(new Date());
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DAY_OF_MONTH, 7);
            endDateField.setValue(cal2.getTime());
            statusArea.setText("Form cleared. Ready for new search.");
        });
        // Logout button
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginWindow().setVisible(true);
        });
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
    /**
     * Performs the advanced search with simulated server communication.
     * Implements timeout handling to meet quality requirement of ≤15 seconds.
     * Simulates location processing based on tourist's current location.
     */
    private void performSearch() {
        String keyword = keywordField.getText().trim();
        String category = (String) categoryComboBox.getSelectedItem();
        String location = locationField.getText();
        // Basic validation
        if (keyword.isEmpty() && category.equals("All Categories")) {
            statusArea.setText("Please enter a keyword or select a specific category.");
            return;
        }
        statusArea.setText("Searching... Please wait (timeout: 15 seconds)");
        // Simulate server communication with timeout handling
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<SearchResult>> future = executor.submit(() -> {
            // Simulate server processing delay (2-10 seconds)
            int delay = 2000 + (int)(Math.random() * 8000);
            Thread.sleep(delay);
            // Simulate location-based processing
            String processedLocation = processLocation(location);
            // Generate mock results based on search parameters
            return generateMockResults(keyword, category, processedLocation);
        });
        try {
            // Wait for results with 15-second timeout as per quality requirement
            List<SearchResult> results = future.get(15, TimeUnit.SECONDS);
            executor.shutdown();
            // Display results window
            dispose();
            new ResultsWindow(results).setVisible(true);
        } catch (TimeoutException e) {
            future.cancel(true);
            executor.shutdown();
            statusArea.setText("Error: Search timeout (exceeded 15 seconds). Please try again.");
            JOptionPane.showMessageDialog(this,
                "Connection to ETOUR server timed out. Please check your connection and try again.",
                "Server Timeout", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            executor.shutdown();
            statusArea.setText("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                "Search failed: " + e.getMessage(),
                "Search Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Processes location information from the tourist's input.
     * In a real system, this would use GPS or other location serv.
     */
    private String processLocation(String location) {
        if (location.contains("Current Location:")) {
            // Extract and process current location
            return location.substring(location.indexOf(":") + 1).trim();
        }
        return location;
    }
    /**
     * Generates mock search results for demonstration.
     * In a real system, this would query a database or external API.
     */
    private List<SearchResult> generateMockResults(String keyword, String category, String location) {
        List<SearchResult> results = new ArrayList<>();
        // Sample mock data
        String[] siteNames = {
            "Eiffel Tower", "Louvre Museum", "Notre-Dame Cathedral",
            "Montmartre", "Champs-Élysées", "Palace of Versailles",
            "Latin Quarter", "Seine River Cruise", "Musée d'Orsay"
        };
        String[] categories = {
            "Historical Sites", "Museums", "Historical Sites",
            "Parks", "Shopping", "Historical Sites",
            "Entertainment", "Entertainment", "Museums"
        };
        String[] addresses = {
            "Champ de Mars, 5 Avenue Anatole France, 75007 Paris",
            "Rue de Rivoli, 75001 Paris",
            "6 Parvis Notre-Dame - Pl. Jean-Paul II, 75004 Paris",
            "Montmartre, 75018 Paris",
            "Avenue des Champs-Élysées, 75008 Paris",
            "Place d'Armes, 78000 Versailles",
            "5th arrondissement, Paris",
            "Port de la Bourdonnais, 75007 Paris",
            "1 Rue de la Légion d'Honneur, 75007 Paris"
        };
        // Filter and create results based on search criteria
        for (int i = 0; i < siteNames.length; i++) {
            boolean matchesKeyword = keyword.isEmpty() || 
                siteNames[i].toLowerCase().contains(keyword.toLowerCase());
            boolean matchesCategory = category.equals("All Categories") || 
                categories[i].equals(category);
            boolean matchesLocation = location.isEmpty() || 
                addresses[i].toLowerCase().contains(location.toLowerCase());
            if (matchesKeyword && matchesCategory && matchesLocation) {
                double rating = 4.0 + (Math.random() * 1.5); // 4.0-5.5 rating
                int reviews = 1000 + (int)(Math.random() * 5000);
                results.add(new SearchResult(siteNames[i], categories[i], 
                    addresses[i], rating, reviews));
            }
        }
        // If no results match exact criteria, add some general results
        if (results.isEmpty()) {
            results.add(new SearchResult("Paris City Tour", "Entertainment",
                "Various locations in " + location, 4.5, 2500));
            results.add(new SearchResult("Local Cuisine Experience", "Restaurants",
                "Traditional restaurants in " + location, 4.7, 1800));
        }
        return results;
    }
}