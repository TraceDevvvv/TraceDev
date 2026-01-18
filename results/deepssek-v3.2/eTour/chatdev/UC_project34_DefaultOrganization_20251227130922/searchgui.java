'''
SearchGUI.java
Main GUI window for the Cultural Heritage Search Application.
Provides the main interface and coordinates between form and results.
'''
package culturalheritage.search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class SearchGUI extends JFrame {
    private SearchForm searchForm;
    private SearchService searchService;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    public SearchGUI() {
        super("Cultural Heritage Search System");
        initializeComponents();
        setupLayout();
    }
    private void initializeComponents() {
        // Initialize serv
        searchService = new SearchService();
        // Create search form
        searchForm = new SearchForm();
        // Create results panel
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        // Create scroll pane for results
        scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setVisible(false);
        // Add submit listener
        searchForm.setSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });
    }
    private void setupLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        // Create main container with BorderLayout
        Container container = getContentPane();
        container.setLayout(new BorderLayout(10, 10));
        // Add form at the top
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.add(searchForm, BorderLayout.CENTER);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(formPanel, BorderLayout.NORTH);
        // Add results in the center
        JPanel resultsContainer = new JPanel(new BorderLayout());
        resultsContainer.setBorder(BorderFactory.createTitledBorder("Search Results"));
        resultsContainer.add(scrollPane, BorderLayout.CENTER);
        container.add(resultsContainer, BorderLayout.CENTER);
        // Add status bar at the bottom
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("Ready - Guest User logged on");
        statusPanel.add(statusLabel);
        container.add(statusPanel, BorderLayout.SOUTH);
    }
    private void handleSearch() {
        try {
            // Clear previous results
            resultsPanel.removeAll();
            scrollPane.setVisible(false);
            // Get search criteria from form
            String keyword = searchForm.getKeyword();
            String category = searchForm.getCategory();
            String location = searchForm.getLocation();
            // Validate keyword
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Keyword cannot be empty. Please enter a search term.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Show loading message
            JLabel loadingLabel = new JLabel("Searching... Please wait...");
            loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultsPanel.add(loadingLabel);
            scrollPane.setVisible(true);
            revalidate();
            // Execute search
            List<Site> sites = searchService.searchSites(keyword, category, location);
            // Clear loading message
            resultsPanel.removeAll();
            if (sites.isEmpty()) {
                JLabel noResults = new JLabel("No cultural heritage sites found matching your criteria.");
                noResults.setHorizontalAlignment(SwingConstants.CENTER);
                resultsPanel.add(noResults);
            } else {
                for (Site site : sites) {
                    resultsPanel.add(createSitePanel(site));
                    resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
            scrollPane.setVisible(true);
            revalidate();
            repaint();
        } catch (ServerConnectionException e) {
            // Specific handling for server connection interruptions
            JOptionPane.showMessageDialog(this,
                "Connection to ETOUR server was interrupted. Please check your internet connection and try again.",
                "Server Connection Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Specific handling for input validation errors
            JOptionPane.showMessageDialog(this,
                e.getMessage(),
                "Input Error",
                JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            // Generic fallback for unexpected errors
            JOptionPane.showMessageDialog(this,
                "An unexpected error occurred during the search. Please try again later.",
                "System Error",
                JOptionPane.ERROR_MESSAGE);
            // Log the actual error for debugging
            System.err.println("Search error: " + e.getMessage());
        } finally {
            // Ensure UI is in consistent state
            revalidate();
            repaint();
        }
    }
    private JPanel createSitePanel(Site site) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.WHITE);
        // Title and category
        JLabel titleLabel = new JLabel("<html><b>" + site.getName() + "</b> - " + site.getCategory() + "</html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        // Description
        JTextArea descArea = new JTextArea(site.getDescription());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setBackground(Color.WHITE);
        // Location and rating
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(new JLabel("📍 " + site.getLocation()));
        infoPanel.add(new JLabel(" | ⭐ " + site.getRating()));
        infoPanel.add(new JLabel(" | 👥 " + site.getVisitors() + " visitors"));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(descArea), BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(700, 120));
        return panel;
    }
}