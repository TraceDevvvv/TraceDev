'''
This is the main application class for the ELIMINANEWS system.
It provides a Graphical User Interface (GUI) for an Agency Operator to view,
select, and delete news items. The application handles user interaction,
confirmation dialogs, and delegates news management to the NewsService.
It also notifies the user of the outcome of deletion operations, including
successful deletion, cancellation, or simulated server connection errors.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
class NewsDeletionApp extends JFrame {
    // Service layer to manage news data
    private NewsService newsService;
    // GUI Components
    private JList<News> newsJList; // Displays the list of news
    private DefaultListModel<News> newsListModel; // Data model for the JList
    private JLabel statusLabel; // Displays status messages to the user
    private JButton deleteButton; // Button to initiate news deletion
    private JButton refreshButton; // Button to refresh the news list
    /**
     * Constructor for the NewsDeletionApp.
     * Initializes the GUI components, sets up the layout, and adds event listeners.
     * It also performs the initial loading of news items.
     *
     * Entry condition: "The agency has logged." is implicitly handled by launching the application.
     */
    public NewsDeletionApp() {
        super("ELIMINANEWS - News Deletion System"); // Set the frame title
        // Initialize the NewsService to handle data operations
        newsService = new NewsService();
        // --- Frame setup ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(900, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        // --- Component Initialization ---
        newsListModel = new DefaultListModel<>();
        newsJList = new JList<>(newsListModel);
        newsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one news item to be selected at a time
        newsJList.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Improve font readability
        JScrollPane scrollPane = new JScrollPane(newsJList); // Add scrollbar to the list if content overflows
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available News Items"));
        deleteButton = new JButton("Delete Selected News");
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        deleteButton.setBackground(new Color(220, 50, 50)); // Red background for delete button
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false); // Remove focus border
        refreshButton = new JButton("Refresh News List");
        refreshButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        refreshButton.setBackground(new Color(50, 150, 220)); // Blue background for refresh button
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        statusLabel = new JLabel("Welcome to ELIMINANEWS. Please select a news item to delete.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding for status label
        // --- Layout Setup ---
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout with gaps
        // Header for the application
        JLabel header = new JLabel("ELIMINANEWS MANAGEMENT", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        add(header, BorderLayout.NORTH); // Add header to the top
        // Panel for action buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for better button positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(deleteButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(refreshButton, gbc);
        // Add an empty panel for spacing below buttons
        gbc.gridy = 2;
        gbc.weighty = 1.0; // Allow this component to take up extra vertical space
        buttonPanel.add(new JPanel(), gbc);
        add(scrollPane, BorderLayout.CENTER); // News list in the center
        add(buttonPanel, BorderLayout.EAST); // Buttons on the right side
        add(statusLabel, BorderLayout.SOUTH); // Status bar at the bottom
        // --- Event Listeners ---
        // 1. Activate the function of elimination of a news.
        // This is activated by the delete button's action listener.
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedNews();
            }
        });
        // Event listener for the refresh button (using a lambda expression)
        refreshButton.addActionListener(e -> loadNews());
        // --- Initial Data Load ---
        // 2. View all news in a form.
        loadNews(); // Load news when the application starts
    }
    /**
     * Loads all news from the NewsService and updates the JList display.
     * This method is called upon application start and when the refresh button is pressed.
     */
    private void loadNews() {
        newsListModel.clear(); // Clear existing items in the list model
        List<News> allNews = newsService.getAllNews(); // Retrieve news from the service
        if (allNews.isEmpty()) {
            statusLabel.setText("No news items available to display.");
            deleteButton.setEnabled(false); // Disable delete if no news to delete
        } else {
            for (News news : allNews) {
                newsListModel.addElement(news); // Add each news item to the list model
            }
            statusLabel.setText(String.format("%d news items loaded. Select one to delete.", allNews.size()));
            deleteButton.setEnabled(true); // Enable delete button as there are news items
        }
    }
    /**
     * Handles the process of deleting a selected news item, following the use case flow.
     * This includes selection validation, user confirmation, deletion attempt,
     * and displaying the outcome.
     */
    private void deleteSelectedNews() {
        News selectedNews = newsJList.getSelectedValue(); // Get the currently selected news item
        // 3. Select a news from the list and submit the form.
        // (This is implicitly done by the user clicking the delete button after selection)
        if (selectedNews == null) {
            // Edge case: No news item selected
            JOptionPane.showMessageDialog(this,
                    "Please select a news item from the list to delete.",
                    "No News Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 4. Asks for confirmation of the transaction.
        // Modified to include News ID for clearer user interaction.
        int confirmation = JOptionPane.showConfirmDialog(this,
                String.format("Are you sure you want to delete News ID: '%s' titled '%s'?", selectedNews.getId(), selectedNews.getTitle()),
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            // 5. Confirm the deletion of the news. (User clicked 'Yes')
            // 6. Delete the data news.
            String newsIdToDelete = selectedNews.getId();
            // Attempt to delete via NewsService and get the detailed result
            NewsService.DeletionResult result = newsService.deleteNews(newsIdToDelete);
            switch (result) {
                case SUCCESS:
                    // Exit condition: The system shall notify the successful elimination of the news.
                    loadNews(); // Refresh the GUI list to reflect the deletion
                    // Modified to include News ID in status message and dialog for clarity.
                    statusLabel.setText(String.format("News ID: '%s' titled '%s' successfully deleted.", selectedNews.getId(), selectedNews.getTitle()));
                    JOptionPane.showMessageDialog(this,
                            String.format("News ID: '%s' titled '%s' has been successfully deleted.", selectedNews.getId(), selectedNews.getTitle()),
                            "Deletion Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case CONNECTION_INTERRUPTED:
                    // Exit condition: Interruption of the connection to the server ETOUR.
                    statusLabel.setText("Failed to delete news. Server connection interrupted (ETOUR).");
                    JOptionPane.showMessageDialog(this,
                            "Failed to delete news due to a server connection interruption (ETOUR). Please try again.",
                            "Deletion Failed: Connection Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                case NEWS_NOT_FOUND:
                    // Case where news was not found (e.g., already deleted by another operator)
                    // Modified to include News ID for clarity.
                    statusLabel.setText(String.format("Failed to delete News ID: '%s' titled '%s'. It might have been deleted already.", selectedNews.getId(), selectedNews.getTitle()));
                    JOptionPane.showMessageDialog(this,
                            String.format("Failed to delete news. The selected news item (ID: '%s') was not found, possibly already deleted by another operator.", selectedNews.getId()),
                            "Deletion Failed: News Not Found",
                            JOptionPane.WARNING_MESSAGE);
                    loadNews(); // Refresh to reflect potential external deletion or state discrepancy
                    break;
            }
        } else {
            // Exit condition: The Operator Agency cancels the operation.
            statusLabel.setText("Deletion cancelled by operator.");
            JOptionPane.showMessageDialog(this,
                    "News deletion cancelled.",
                    "Operation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Main method to launch the ELIMINANEWS application.
     * Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     */
    public static void main(String[] args) {
        // Ensures that all GUI updates happen on the Event Dispatch Thread (EDT)
        // This is a crucial requirement for Swing applications to maintain thread safety and responsiveness.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewsDeletionApp().setVisible(true); // Create and show the main application window
            }
        });
    }
}