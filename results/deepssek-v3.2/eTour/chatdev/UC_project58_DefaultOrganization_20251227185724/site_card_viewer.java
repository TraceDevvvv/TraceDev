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
    /**
     * Constructs the main application window.
     * Initializes components, sets up the layout, and attaches event listeners.
     */
    public AppWindow() {
        // Initialize window properties
        setTitle("Tourist Site Viewer - ETOUR System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
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
        siteComboBox = new JComboBox<>(new String[]{
            "Select a site...",
            "Eiffel Tower",
            "Colosseum",
            "Great Wall",
            "Machu Picchu",
            "Taj Mahal"
        });
        // Text area to display site details (simulates the site card display)
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Button to trigger viewing site card (step 1 of use case flow)
        viewButton = new JButton("View Site Card");
        viewButton.setEnabled(false); // Disabled until a valid site is selected
        // Label for status messages
        statusLabel = new JLabel("Ready. Please select a site.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    /**
     * Sets up the layout of the GUI components.
     */
    private void layoutComponents() {
        // Use BorderLayout for main layout
        setLayout(new BorderLayout());
        // Top panel: site selection and button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Choose Site:"));
        topPanel.add(siteComboBox);
        topPanel.add(viewButton);
        add(topPanel, BorderLayout.NORTH);
        // Center panel: details display (simulates the site card)
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Site Details"));
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel: status label
        add(statusLabel, BorderLayout.SOUTH);
    }
    /**
     * Attaches event listeners to handle user interactions.
     */
    private void attachListeners() {
        // ComboBox listener: enable button when a valid site is selected
        siteComboBox.addActionListener(e -> {
            String selected = (String) siteComboBox.getSelectedItem();
            viewButton.setEnabled(selected != null && !selected.equals("Select a site..."));
            if (viewButton.isEnabled()) {
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
                        statusLabel.setText("Details loaded for: " + siteName);
                    } catch (InterruptedException | ExecutionException ex) {
                        // Handle execution errors
                        detailsTextArea.setText("Error: Could not fetch site details.\n" + 
                                               ex.getCause().getMessage());
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
    /**
     * Simulates fetching site details from a remote database (ETOUR server).
     * Handles simulated network delays without throwing InterruptedException
     * to avoid conflicts with SwingWorker.
     * 
     * @param siteName the name of the site to fetch details for
     * @return a formatted string containing site details, or an error message
     */
    public String fetchSiteDetails(String siteName) {
        // Simulate network delay (step 2: upload data from database)
        try {
            Thread.sleep(1000); // Simulate 1-second network delay
        } catch (InterruptedException e) {
            // Restore interrupted status and return error message
            // This handles the "Interruption of the connection to the server ETOUR" quality requirement
            Thread.currentThread().interrupt();
            return "Error: Database operation interrupted due to server connection issue.";
        }
        // Return mock details based on site name
        // In a real implementation, this would query a database
        switch (siteName) {
            case "Eiffel Tower":
                return "Name: Eiffel Tower\n" +
                       "Location: Paris, France\n" +
                       "Built: 1889\n" +
                       "Architect: Gustave Eiffel\n" +
                       "Height: 330 meters\n" +
                       "Description: An iron lattice tower on the Champ de Mars in Paris.";
            case "Colosseum":
                return "Name: Colosseum\n" +
                       "Location: Rome, Italy\n" +
                       "Built: 80 AD\n" +
                       "Capacity: 50,000 - 80,000 spectators\n" +
                       "Type: Amphitheater\n" +
                       "Description: The largest ancient amphitheater ever built.";
            case "Great Wall":
                return "Name: Great Wall of China\n" +
                       "Location: Northern China\n" +
                       "Length: 21,196 km\n" +
                       "Built: 7th century BC - 1644 AD\n" +
                       "Purpose: Fortification\n" +
                       "Description: A series of fortifications made of stone, brick, tamped earth, wood, and other materials.";
            case "Machu Picchu":
                return "Name: Machu Picchu\n" +
                       "Location: Andes Mountains, Peru\n" +
                       "Built: 1450 AD\n" +
                       "Altitude: 2,430 meters\n" +
                       "Culture: Inca\n" +
                       "Description: A 15th-century Inca citadel.";
            case "Taj Mahal":
                return "Name: Taj Mahal\n" +
                       "Location: Agra, India\n" +
                       "Built: 1643 AD\n" +
                       "Architectural Style: Mughal\n" +
                       "Material: White marble\n" +
                       "Description: An ivory-white marble mausoleum.";
            default:
                return "Error: Site details not found in database.";
        }
    }
}