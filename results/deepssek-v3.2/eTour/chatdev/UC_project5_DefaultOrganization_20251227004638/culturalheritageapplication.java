"""
Main application entry point for Cultural Heritage Management System.
Implements the ViewCulturalHeritageCard use case.
Handles login simulation, search functionality, detailed card viewing,
and server connection interruption scenarios.
"""
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.List;
/**
 * Complete Cultural Heritage Management System
 * Implements the ViewCulturalHeritageCard use case
 */
public class CulturalHeritageApplication {
    public static void main(String[] args) {
        // Simulate agency operator login (Entry Operator conditions)
        System.out.println("Agency Operator logged in successfully.");
        // Create server connection
        ETOURServerConnection serverConnection = new ETOURServerConnection();
        try {
            // Step 1: View list from SearchCulturalHeritage use case
            SearchCulturalHeritagePanel searchPanel = new SearchCulturalHeritagePanel(serverConnection);
            CulturalHeritage selectedHeritage = searchPanel.displayAndSelect();
            // Step 2: Load data and display card (Exit conditions)
            if (selectedHeritage != null) {
                HeritageCardDialog cardDialog = new HeritageCardDialog(null, selectedHeritage);
                cardDialog.setVisible(true);
            }
        } catch (ServerConnectionException e) {
            // Handle interruption of the connection to the server ETOUR
            System.err.println("Error: Connection to ETOUR server interrupted. " + e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Connection to ETOUR server lost. Please try again later.",
                "Server Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
/**
 * Represents a cultural heritage item with all relevant properties.
 */
class CulturalHeritage {
    private String id;
    private String name;
    private String type;
    private String location;
    private String period;
    private String status;
    private List<String> images;
    private String description;
    private String historicalSignificance;
    private String conservationStatus;
    public CulturalHeritage(String id, String name, String type, String location, 
                           String period, String status, List<String> images,
                           String description, String historicalSignificance, 
                           String conservationStatus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.period = period;
        this.status = status;
        this.images = images;
        this.description = description;
        this.historicalSignificance = historicalSignificance;
        this.conservationStatus = conservationStatus;
    }
    // Getter methods
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getPeriod() { return period; }
    public String getStatus() { return status; }
    public List<String> getImages() { return images; }
    public String getDescription() { return description; }
    public String getHistoricalSignificance() { return historicalSignificance; }
    public String getConservationStatus() { return conservationStatus; }
}
/**
 * Panel for searching and selecting cultural heritage items.
 */
class SearchCulturalHeritagePanel {
    private ETOURServerConnection serverConnection;
    public SearchCulturalHeritagePanel(ETOURServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }
    /**
     * Displays search results and allows user to select an item.
     * @return Selected CulturalHeritage object, or null if none selected
     * @throws ServerConnectionException if connection is interrupted
     */
    public CulturalHeritage displayAndSelect() throws ServerConnectionException {
        // Simulate loading cultural goods from server
        List<CulturalHeritage> heritageList = serverConnection.searchCulturalHeritage();
        if (heritageList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No cultural heritage items found.",
                "Search Results", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        // Display list in a dialog for selection
        return showSelectionDialog(heritageList);
    }
    /**
     * Shows dialog with cultural heritage items for user selection.
     */
    private CulturalHeritage showSelectionDialog(List<CulturalHeritage> items) {
        // Create array of display names for the list
        String[] itemNames = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getName() + " (" + items.get(i).getType() + ")";
        }
        // Show selection dialog
        String selection = (String) JOptionPane.showInputDialog(null,
            "Select a cultural heritage item to view details:",
            "Cultural Heritage List",
            JOptionPane.QUESTION_MESSAGE,
            null,
            itemNames,
            itemNames[0]);
        // Find and return selected item
        if (selection != null) {
            for (CulturalHeritage item : items) {
                if ((item.getName() + " (" + item.getType() + ")").equals(selection)) {
                    return item;
                }
            }
        }
        return null;
    }
}
/**
 * Simulates connection to ETOUR server with interruption handling.
 */
class ETOURServerConnection {
    private boolean connected = true;
    /**
     * Searches for cultural heritage items on the server.
     * @return List of cultural heritage items
     * @throws ServerConnectionException if connection is interrupted
     */
    public List<CulturalHeritage> searchCulturalHeritage() throws ServerConnectionException {
        checkConnection();  // Verify server connectivity
        // Simulate server delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Return mock data for demonstration
        return createMockData();
    }
    /**
     * Checks if connection to server is active.
     * Simulates random connection interruptions.
     */
    private void checkConnection() throws ServerConnectionException {
        // Simulate random connection failure (15% chance for demonstration)
        if (Math.random() < 0.15) {
            connected = false;
            throw new ServerConnectionException("ETOUR server connection lost");
        }
        connected = true;
    }
    /**
     * Creates mock cultural heritage data for demonstration.
     */
    private List<CulturalHeritage> createMockData() {
        List<CulturalHeritage> mockData = new ArrayList<>();
        // Sample data 1
        List<String> images1 = new ArrayList<>();
        images1.add("GreatWall_1.jpg");
        images1.add("GreatWall_2.jpg");
        mockData.add(new CulturalHeritage(
            "CH001",
            "Great Wall of China",
            "Historical Monument",
            "Northern China",
            "7th century BC",
            "Protected",
            images1,
            "Series of fortifications made of stone, brick, tamped earth, wood...",
            "One of the most impressive architectural feats in history...",
            "Well preserved with ongoing conservation efforts"
        ));
        // Sample data 2
        List<String> images2 = new ArrayList<>();
        images2.add("Pyramids_1.jpg");
        mockData.add(new CulturalHeritage(
            "CH002",
            "Pyramids of Giza",
            "Ancient Monument",
            "Giza, Egypt",
            "2580–2560 BC",
            "UNESCO World Heritage",
            images2,
            "Ancient pyramid complex including the Great Pyramid...",
            "Last surviving wonder of the ancient world...",
            "Stable with tourism management"
        ));
        return mockData;
    }
}
/**
 * Custom exception for ETOUR server connection errors.
 */
class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
/**
 * Dialog for displaying detailed cultural heritage card information.
 */
class HeritageCardDialog extends JDialog {
    private CulturalHeritage heritage;
    /**
     * Constructor for the heritage card dialog.
     * @param parent The parent frame
     * @param heritage The cultural heritage item to display
     */
    public HeritageCardDialog(Frame parent, CulturalHeritage heritage) {
        super(parent, "Cultural Heritage Card", true);
        this.heritage = heritage;
        initComponents();
        layoutComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(parent);
    }
    /**
     * Initialize dialog components.
     */
    private void initComponents() {
        // Components will be created and populated in layoutComponents
    }
    /**
     * Layout components within the dialog.
     */
    private void layoutComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        // Main panel with card layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Title section
        JLabel titleLabel = new JLabel(heritage.getName(), JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel);
        // Details section
        mainPanel.add(createDetailsSection());
        mainPanel.add(Box.createVerticalStrut(10));
        // Images section
        mainPanel.add(createImagesSection());
        mainPanel.add(Box.createVerticalStrut(10));
        // Additional information section
        mainPanel.add(createAdditionalInfoSection());
        // Add main panel to content pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        // Close button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Create the details section panel.
     * @return Panel containing cultural heritage details
     */
    private JPanel createDetailsSection() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Row 0: ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getId()), gbc);
        // Row 1: Type
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getType()), gbc);
        // Row 2: Location
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getLocation()), gbc);
        // Row 3: Period
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Period:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getPeriod()), gbc);
        // Row 4: Status
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(heritage.getStatus()), gbc);
        return panel;
    }
    /**
     * Create the images section panel.
     * @return Panel containing image information
     */
    private JPanel createImagesSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Images"));
        List<String> images = heritage.getImages();
        if (images.isEmpty()) {
            panel.add(new JLabel("No images available", JLabel.CENTER), BorderLayout.CENTER);
        } else {
            JPanel imageListPanel = new JPanel();
            imageListPanel.setLayout(new BoxLayout(imageListPanel, BoxLayout.Y_AXIS));
            for (String image : images) {
                JLabel imageLabel = new JLabel("• " + image);
                imageLabel.setBorder(new EmptyBorder(2, 10, 2, 10));
                imageListPanel.add(imageLabel);
            }
            panel.add(new JScrollPane(imageListPanel), BorderLayout.CENTER);
        }
        return panel;
    }
    /**
     * Create the additional information section panel.
     * @return Panel containing additional heritage information
     */
    private JPanel createAdditionalInfoSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Additional Information"));
        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        StringBuilder infoText = new StringBuilder();
        infoText.append("Description:\n");
        infoText.append(heritage.getDescription());
        infoText.append("\n\n");
        infoText.append("Historical Significance:\n");
        infoText.append(heritage.getHistoricalSignificance());
        infoText.append("\n\n");
        infoText.append("Conservation Status:\n");
        infoText.append(heritage.getConservationStatus());
        infoArea.setText(infoText.toString());
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }
}