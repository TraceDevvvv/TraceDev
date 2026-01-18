/**
 * SiteCardViewer.java
 * Main program for the ViewSiteCard use case.
 * This application provides a GUI for tourists to view details of a site.
 * It simulates fetching site details from a database (ETOUR server).
 * Includes error handling for server connection interruptions.
 * The application assumes the tourist has authenticated and is in one of
 * the specified areas (Research Results, List of Sites Visited, or List of Favorites).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SiteCardViewer {
    /**
     * Main entry point of the application.
     * Launches the GUI on the Swing Event Dispatch Thread for thread safety.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Use Swing event dispatch thread for thread-safe GUI operations
        SwingUtilities.invokeLater(() -> {
            AppWindow window = new AppWindow();
            window.setVisible(true);
        });
    }
}
/**
 * Main application window that provides the GUI for viewing site cards.
 * Implements the ViewSiteCard use case flow.
 */
class AppWindow extends JFrame {
    private JComboBox<String> siteComboBox;
    private JTextArea detailsTextArea;
    private JButton viewButton;
    private SiteDatabase database;
    private JLabel statusLabel;
    private JCheckBox filterCheckBox;
    private List<String> favoriteSites;
    private List<String> visitedSites;
    /**
     * Constructs the main application window.
     * Initializes components, sets up the layout, and attaches event listeners.
     */
    public AppWindow() {
        // Initialize window properties
        setTitle("Tourist Site Viewer - ETOUR System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        // Initialize mock data
        favoriteSites = new ArrayList<>();
        favoriteSites.add("Eiffel Tower");
        favoriteSites.add("Taj Mahal");
        visitedSites = new ArrayList<>();
        visitedSites.add("Colosseum");
        visitedSites.add("Great Wall");
        // Initialize components
        initComponents();
        // Initialize mock database (simulates ETOUR server connection)
        database = new SiteDatabase();
        // Setup layout
        layoutComponents();
        // Attach event listeners
        attachListeners();
    }
    /**
     * Initializes all GUI components.
     */
    private void initComponents() {
        // Dropdown for selecting a site - simulates selection from Research Results,
        // List of Sites Visited, or List of Favorites
        siteComboBox = new JComboBox<>();
        updateSiteList("All"); // Initial load with all sites
        // Text area to display site details (simulates the site card display)
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Button to trigger viewing site card (step 1 of use case flow)
        viewButton = new JButton("View Site Card");
        viewButton.setEnabled(false); // Disabled until a valid site is selected
        // Filter checkbox for showing only favorites/visited
        filterCheckBox = new JCheckBox("Show Only Favorites");
        // Label for status messages
        statusLabel = new JLabel("Ready. Please select a site.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    /**
     * Updates the site list based on filter selection
     * @param filterType Type of filter to apply ("All", "Favorites", "Visited")
     */
    private void updateSiteList(String filterType) {
        siteComboBox.removeAllItems();
        siteComboBox.addItem("Select a site...");
        List<String> sitesToShow = new ArrayList<>();
        switch (filterType) {
            case "Favorites":
                sitesToShow.addAll(favoriteSites);
                break;
            case "Visited":
                sitesToShow.addAll(visitedSites);
                break;
            default: // "All"
                sitesToShow.add("Eiffel Tower");
                sitesToShow.add("Colosseum");
                sitesToShow.add("Great Wall");
                sitesToShow.add("Machu Picchu");
                sitesToShow.add("Taj Mahal");
        }
        for (String site : sitesToShow) {
            siteComboBox.addItem(site);
        }
        viewButton.setEnabled(false);
    }
    /**
     * Sets up the layout of the GUI components.
     */
    private void layoutComponents() {
        // Use BorderLayout for main layout
        setLayout(new BorderLayout());
        // Top panel: filter options
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter by:"));
        String[] filters = {"All", "Favorites", "Visited"};
        JComboBox<String> filterCombo = new JComboBox<>(filters);
        filterPanel.add(filterCombo);
        filterPanel.add(filterCheckBox);
        // Middle panel: site selection and button
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectionPanel.add(new JLabel("Choose Site:"));
        selectionPanel.add(siteComboBox);
        selectionPanel.add(viewButton);
        // Combined top panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(filterPanel);
        topPanel.add(selectionPanel);
        add(topPanel, BorderLayout.NORTH);
        // Center panel: details display (simulates the site card)
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Site Details Card"));
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel: status label
        add(statusLabel, BorderLayout.SOUTH);
        // Add filter combo listener
        filterCombo.addActionListener(e -> {
            String selectedFilter = (String) filterCombo.getSelectedItem();
            updateSiteList(selectedFilter);
            statusLabel.setText("Showing: " + selectedFilter + " sites");
        });
    }
    /**
     * Attaches event listeners to handle user interactions.
     */
    private void attachListeners() {
        // ComboBox listener: enable button when a valid site is selected
        siteComboBox.addActionListener(e -> {
            String selected = (String) siteComboBox.getSelectedItem();
            boolean isValidSelection = selected != null && !selected.equals("Select a site...");
            viewButton.setEnabled(isValidSelection);
            if (isValidSelection) {
                statusLabel.setText("Site selected: " + selected);
            } else {
                statusLabel.setText("Ready. Please select a site.");
            }
        });
        // Button listener: fetch and display site details using SwingWorker
        // Implements step 2 of use case flow (upload data from database)
        viewButton.addActionListener(e -> {
            detailsTextArea.setText("");
            statusLabel.setText("Fetching site details...");
            viewButton.setEnabled(false);
            siteComboBox.setEnabled(false);
            String siteName = (String) siteComboBox.getSelectedItem();
            // Use SwingWorker for background database operation to keep GUI responsive
            SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    // Step 2: Upload data from the database
                    return database.fetchSiteDetails(siteName);
                }
                @Override
                protected void done() {
                    try {
                        String details = get();
                        detailsTextArea.setText(details);
                        statusLabel.setText("Site card displayed for: " + siteName);
                    } catch (InterruptedException | ExecutionException ex) {
                        // Handle execution errors
                        Throwable cause = ex.getCause();
                        String errorMsg = "Error: Could not fetch site details.\n";
                        if (cause != null) {
                            errorMsg += cause.getMessage();
                        } else {
                            errorMsg += ex.getMessage();
                        }
                        detailsTextArea.setText(errorMsg);
                        statusLabel.setText("Error: Fetch failed.");
                    } catch (CancellationException ex) {
                        // Handle server connection interruption (ETOUR server timeout)
                        detailsTextArea.setText("Error: Connection to ETOUR server timed out.\n" +
                                               "Please check your network connection and try again.");
                        statusLabel.setText("Error: Server connection interrupted.");
                    } finally {
                        viewButton.setEnabled(true);
                        siteComboBox.setEnabled(true);
                    }
                }
            };
            // Execute the worker with a timeout to simulate server interruption
            worker.execute();
            // Timer to simulate connection timeout (quality requirement: interruption handling)
            Timer timer = new Timer(5000, evt -> {
                if (!worker.isDone()) {
                    worker.cancel(true); // Cancel the worker if it takes too long
                }
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
}
/**
 * Mock database class simulating interaction with ETOUR server.
 * In a real implementation, this would connect to an actual database server.
 */
class SiteDatabase {
    // Simulate database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/etour_db";
    private static final String USER = "tourist_user";
    private static final String PASS = "password";
    /**
     * Simulates fetching site details from a remote database (ETOUR server).
     * Handles simulated network delays and connection issues.
     * 
     * @param siteName the name of the site to fetch details for
     * @return a formatted string containing site details, or an error message
     * @throws Exception if database connection fails
     */
    public String fetchSiteDetails(String siteName) throws Exception {
        // Simulate a 50% chance of server interruption for testing
        if (Math.random() < 0.5) {
            Thread.sleep(6000); // Longer delay to trigger timeout
            throw new SQLException("ETOUR server connection interrupted");
        }
        // Simulate normal network delay
        Thread.sleep(1000); // Simulate 1-second network delay
        // Simulate database connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // In real implementation, this would be a prepared statement
            return getSiteDetailsFromDatabase(siteName);
        } catch (SQLException e) {
            // Handle database connection errors
            throw new Exception("Database error: " + e.getMessage(), e);
        }
    }
    /**
     * Mock method to simulate database query results
     */
    private String getSiteDetailsFromDatabase(String siteName) {
        // Return mock details based on site name
        // In a real implementation, this would query a database
        switch (siteName) {
            case "Eiffel Tower":
                return "Site Card: Eiffel Tower\n" +
                       "========================\n" +
                       "Location: Paris, France\n" +
                       "Coordinates: 48.8584° N, 2.2945° E\n" +
                       "Built: 1887-1889\n" +
                       "Architect: Gustave Eiffel\n" +
                       "Height: 330 meters (1,083 ft)\n" +
                       "Material: Wrought iron\n" +
                       "Visitors per year: ~7 million\n" +
                       "\nDescription:\n" +
                       "The Eiffel Tower is a wrought-iron lattice tower on the Champ de Mars in Paris, France.\n" +
                       "It is named after the engineer Gustave Eiffel, whose company designed and built the tower.\n" +
                       "It was constructed from 1887 to 1889 as the centerpiece of the 1889 World's Fair.";
            case "Colosseum":
                return "Site Card: Colosseum\n" +
                       "===================\n" +
                       "Location: Rome, Italy\n" +
                       "Coordinates: 41.8902° N, 12.4922° E\n" +
                       "Built: 72-80 AD\n" +
                       "Capacity: 50,000-80,000 spectators\n" +
                       "Type: Amphitheater\n" +
                       "Material: Travertine, tuff, brick-faced concrete\n" +
                       "\nDescription:\n" +
                       "The Colosseum is an oval amphitheatre in the centre of the city of Rome, Italy.\n" +
                       "It is the largest ancient amphitheatre ever built, and is still the largest standing\n" +
                       "amphitheatre in the world today, despite its age.";
            case "Great Wall":
                return "Site Card: Great Wall of China\n" +
                       "==============================\n" +
                       "Location: Northern China\n" +
                       "Coordinates: 40.4319° N, 116.5704° E\n" +
                       "Length: 21,196 km (13,171 mi)\n" +
                       "Built: 7th century BC - 1644 AD\n" +
                       "Purpose: Fortification, border control\n" +
                       "Material: Stone, brick, tamped earth, wood\n" +
                       "\nDescription:\n" +
                       "The Great Wall of China is a series of fortifications made of stone, brick,\n" +
                       "tamped earth, wood, and other materials, generally built along an east-to-west line\n" +
                       "across the historical northern borders of China to protect against invasions.";
            case "Machu Picchu":
                return "Site Card: Machu Picchu\n" +
                       "=======================\n" +
                       "Location: Andes Mountains, Peru\n" +
                       "Coordinates: 13.1631° S, 72.5450° W\n" +
                       "Built: c. 1450 AD\n" +
                       "Altitude: 2,430 meters (7,970 ft)\n" +
                       "Culture: Inca\n" +
                       "Discovery: 1911 by Hiram Bingham\n" +
                       "\nDescription:\n" +
                       "Machu Picchu is a 15th-century Inca citadel located on a mountain ridge 2,430 meters\n" +
                       "above sea level. It is located in the Cusco Region of Peru. Most archaeologists believe\n" +
                       "that Machu Picchu was built as an estate for the Inca emperor Pachacuti.";
            case "Taj Mahal":
                return "Site Card: Taj Mahal\n" +
                       "===================\n" +
                       "Location: Agra, India\n" +
                       "Coordinates: 27.1751° N, 78.0421° E\n" +
                       "Built: 1632-1643 AD\n" +
                       "Architectural Style: Mughal\n" +
                       "Material: White marble\n" +
                       "Cost: ~32 million rupees (equivalent to ~$1 billion today)\n" +
                       "\nDescription:\n" +
                       "The Taj Mahal is an ivory-white marble mausoleum on the southern bank of the Yamuna\n" +
                       "river in the Indian city of Agra. It was commissioned in 1632 by the Mughal emperor\n" +
                       "Shah Jahan to house the tomb of his favorite wife, Mumtaz Mahal.";
            default:
                return "Error: Site '" + siteName + "' not found in the ETOUR database.\n" +
                       "Please verify the site name and try again.";
        }
    }
}