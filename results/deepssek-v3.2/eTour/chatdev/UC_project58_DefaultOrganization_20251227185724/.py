/*
 * SiteCardViewer.java
 * Main program for the ViewSiteCard use case.
 * This application provides a GUI for tourists to view details of a site.
 * It simulates fetching site details from a database (ETOUR server).
 * Includes error handling for server connection interruptions.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
public class SiteCardViewer {
    // Main method: entry point of the application
    public static void main(String[] args) {
        // Use Swing event dispatch thread for thread-safe GUI operations
        SwingUtilities.invokeLater(() -> {
            // Create and display the main application window
            AppWindow window = new AppWindow();
            window.setVisible(true);
        });
    }
}
// Main application window
class AppWindow extends JFrame {
    private JComboBox<String> siteComboBox;
    private JTextArea detailsTextArea;
    private JButton viewButton;
    private SiteDatabase database;
    private JLabel statusLabel;
    public AppWindow() {
        // Initialize window properties
        setTitle("Tourist Site Viewer - ETOUR System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        // Initialize components
        initComponents();
        // Initialize mock database
        database = new SiteDatabase();
        // Setup layout
        layoutComponents();
        // Attach event listener
        attachListeners();
    }
    private void initComponents() {
        // Dropdown for selecting a site
        siteComboBox = new JComboBox<>(new String[]{
            "Select a site...",
            "Eiffel Tower",
            "Colosseum",
            "Great Wall",
            "Machu Picchu",
            "Taj Mahal"
        });
        // Text area to display site details
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Button to trigger viewing site card
        viewButton = new JButton("View Site Card");
        viewButton.setEnabled(false); // Disabled until a site is selected
        // Label for status messages
        statusLabel = new JLabel("Ready. Please select a site.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    private void layoutComponents() {
        // Use BorderLayout for main layout
        setLayout(new BorderLayout());
        // Top panel: site selection and button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Choose Site:"));
        topPanel.add(siteComboBox);
        topPanel.add(viewButton);
        add(topPanel, BorderLayout.NORTH);
        // Center panel: details display
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Site Details"));
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel: status label
        add(statusLabel, BorderLayout.SOUTH);
    }
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
        // Button listener: fetch and display site details
        viewButton.addActionListener(e -> {
            // Clear previous details
            detailsTextArea.setText("");
            statusLabel.setText("Fetching site details...");
            // Get selected site
            String siteName = (String) siteComboBox.getSelectedItem();
            // Use ExecutorService to simulate asynchronous database fetch
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(() -> database.fetchSiteDetails(siteName));
            // Handle the result (or interruption) of the fetch operation
            try {
                // Simulate a delay for network operation
                String details = future.get(5, TimeUnit.SECONDS); // Timeout after 5 seconds
                detailsTextArea.setText(details);
                statusLabel.setText("Details loaded for: " + siteName);
            } catch (TimeoutException ex) {
                // Handle server connection interruption (ETOUR server timeout)
                future.cancel(true);
                detailsTextArea.setText("Error: Connection to ETOUR server timed out.\nPlease check your network connection and try again.");
                statusLabel.setText("Error: Server connection interrupted.");
            } catch (InterruptedException | ExecutionException ex) {
                // Handle other execution errors
                detailsTextArea.setText("Error: Could not fetch site details.\n" + ex.getMessage());
                statusLabel.setText("Error: Fetch failed.");
            } finally {
                executor.shutdownNow();
            }
        });
    }
}
// Mock database class simulating interaction with ETOUR server
class SiteDatabase {
    // Simulates fetching site details from a remote database
    // In real implementation, this would connect to an actual database server
    public String fetchSiteDetails(String siteName) throws InterruptedException {
        // Simulate network delay
        Thread.sleep(1000);
        // Return mock details based on site name
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