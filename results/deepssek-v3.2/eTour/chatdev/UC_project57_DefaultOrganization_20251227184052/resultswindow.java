/**
 * ResultsWindow displays the search results in a user-friendly interface.
 * Exit condition: The system displays a list of results.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
class ResultsWindow extends JFrame {
    public ResultsWindow(List<SearchResult> results) {
        super("Search Results - ETOUR System");
        initializeUI(results);
    }
    private void initializeUI(List<SearchResult> results) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Title
        JLabel titleLabel = new JLabel("Search Results:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Results display
        if (results.isEmpty()) {
            JLabel noResultsLabel = new JLabel(
                "<html><div style='text-align: center;'>No results found.<br>" +
                "Try different search criteria.</div></html>", 
                SwingConstants.CENTER);
            noResultsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            mainPanel.add(noResultsLabel, BorderLayout.CENTER);
        } else {
            // Create a panel with vertical box layout for better results display
            JPanel resultsPanel = new JPanel();
            resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
            // Header
            JPanel headerPanel = new JPanel(new GridLayout(1, 3));
            headerPanel.add(new JLabel("Site Name", SwingConstants.CENTER));
            headerPanel.add(new JLabel("Category", SwingConstants.CENTER));
            headerPanel.add(new JLabel("Rating/Reviews", SwingConstants.CENTER));
            headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
            headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            resultsPanel.add(headerPanel);
            // Add each result as a panel
            for (SearchResult result : results) {
                JPanel resultPanel = new JPanel(new GridLayout(1, 3));
                resultPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                // Name with tooltip showing full name
                JLabel nameLabel = new JLabel(
                    result.getName().length() > 28 ? 
                    result.getName().substring(0, 25) + "..." : result.getName());
                nameLabel.setToolTipText(result.getName());
                JLabel categoryLabel = new JLabel(result.getCategory());
                JLabel ratingLabel = new JLabel(
                    String.format("%.1f/5.0 (%d reviews)", result.getRating(), result.getReviewCount()));
                // Center align all labels
                nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
                ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                resultPanel.add(nameLabel);
                resultPanel.add(categoryLabel);
                resultPanel.add(ratingLabel);
                // Add mouse listener for detailed view
                resultPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showResultDetails(result);
                    }
                });
                resultsPanel.add(resultPanel);
                resultsPanel.add(new JSeparator());
            }
            JScrollPane scrollPane = new JScrollPane(resultsPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            // Results count
            JLabel countLabel = new JLabel(
                String.format("Found %d result(s)", results.size()), 
                SwingConstants.CENTER);
            mainPanel.add(countLabel, BorderLayout.SOUTH);
        }
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton newSearchButton = new JButton("New Advanced Search");
        JButton logoutButton = new JButton("Logout");
        newSearchButton.addActionListener(e -> {
            dispose();
            new AdvancedSearchForm().setVisible(true);
        });
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginWindow().setVisible(true);
        });
        buttonPanel.add(newSearchButton);
        buttonPanel.add(logoutButton);
        // Create a separate panel for buttons
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
    /**
     * Displays detailed information about a selected search result.
     */
    private void showResultDetails(SearchResult result) {
        JDialog detailsDialog = new JDialog(this, "Site Details", true);
        detailsDialog.setSize(500, 300);
        detailsDialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel(result.getName(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);
        // Details in a text area
        JTextArea detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setText(String.format(
            "Category: %s\n\n" +
            "Address: %s\n\n" +
            "Rating: %.1f out of 5.0\n" +
            "Based on %d reviews\n\n" +
            "Description: A popular tourist attraction with excellent reviews. " +
            "Recommended for visitors interested in %s.",
            result.getCategory(), result.getAddress(), 
            result.getRating(), result.getReviewCount(), 
            result.getCategory().toLowerCase()));
        panel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> detailsDialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        detailsDialog.setContentPane(panel);
        detailsDialog.setVisible(true);
    }
}