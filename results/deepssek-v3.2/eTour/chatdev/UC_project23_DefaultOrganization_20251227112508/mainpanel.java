/**
 * Main panel controlling the application flow via CardLayout.
 * Manages navigation between news list, editing, and confirmation views.
 * Handles data model interactions and simulates connection interruptions.
 */
import javax.swing.*;
import java.awt.*;
public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    // Step-specific panels
    private NewsListPanel newsListPanel;
    private EditNewsPanel editNewsPanel;
    private ConfirmationPanel confirmationPanel;
    // Data and state
    private AgencyNewsModel newsModel;
    private NewsItem selectedNews; // Current news being edited
    /**
     * Constructor initializes the model and UI.
     */
    public MainPanel() {
        // Simulate pre-condition: agency has logged
        newsModel = new AgencyNewsModel();
        initializeUI();
    }
    /**
     * Sets up the CardLayout and all child panels.
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Header
        JLabel headerLabel = new JLabel("Agency News Management System", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        add(headerLabel, BorderLayout.NORTH);
        // Card layout for step navigation
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);
        // Initialize panels
        newsListPanel = new NewsListPanel(newsModel);
        editNewsPanel = new EditNewsPanel();
        confirmationPanel = new ConfirmationPanel();
        // Add to card layout
        cardPanel.add(newsListPanel, "LIST");
        cardPanel.add(editNewsPanel, "EDIT");
        cardPanel.add(confirmationPanel, "CONFIRM");
        // Start with news list (Step 2)
        cardLayout.show(cardPanel, "LIST");
        // Set up event handlers for panel transitions
        setupEventHandlers();
    }
    /**
     * Connects actions between panels to follow use case flow.
     */
    private void setupEventHandlers() {
        // Step 3: Select news from list -> edit
        newsListPanel.setEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedNews = newsListPanel.getSelectedNews();
                if (selectedNews != null) {
                    // Step 4: Load data into edit form
                    editNewsPanel.loadNewsData(selectedNews);
                    cardLayout.show(cardPanel, "EDIT");
                } else {
                    showWarning("No Selection", "Please select a news item to edit.");
                }
            }
        });
        // Step 5: Submit edited data -> validation
        editNewsPanel.setSubmitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewsItem modifiedNews = editNewsPanel.getModifiedNews();
                // Step 6: Validate data
                if (modifiedNews != null && modifiedNews.isValid()) {
                    confirmationPanel.setNewsDetails(modifiedNews);
                    cardLayout.show(cardPanel, "CONFIRM");
                } else {
                    // Trigger error handling as per use case
                    showError("Data Error", "Invalid or insufficient data. Please correct all fields.");
                    // Note: In a full system, would activate "Errored" use case here
                }
            }
        });
        // Step 7: Confirm changes -> storage
        confirmationPanel.setConfirmButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewsItem modifiedNews = confirmationPanel.getNews();
                if (modifiedNews != null) {
                    // Disable button during update to prevent multiple submissions
                    confirmationPanel.setConfirmButtonEnabled(false);
                    // Use SwingWorker to perform update off the EDT
                    SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                        @Override
                        protected Boolean doInBackground() throws Exception {
                            // Step 8: Store modified news (off EDT)
                            return newsModel.updateNews(modifiedNews);
                        }
                        @Override
                        protected void done() {
                            try {
                                boolean success = get();
                                if (success) {
                                    // Exit condition: notify success
                                    showInfo("Success", "News amended successfully.");
                                    // Refresh list and return to start
                                    newsListPanel.refreshNewsList();
                                    cardLayout.show(cardPanel, "LIST");
                                } else {
                                    // Simulate ETOUR connection interruption
                                    showError("Connection Error", 
                                              "Failed to update news. Server connection interrupted (ETOUR).");
                                }
                            } catch (Exception ex) {
                                showError("Update Error", "An error occurred during update: " + ex.getMessage());
                            } finally {
                                confirmationPanel.setConfirmButtonEnabled(true);
                            }
                        }
                    };
                    worker.execute();
                }
            }
        });
        // Cancel from confirmation
        confirmationPanel.setCancelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Operator cancels operation
                cardLayout.show(cardPanel, "LIST");
            }
        });
        // Cancel from edit
        editNewsPanel.setCancelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "LIST");
            }
        });
    }
    // Helper methods for standardized messages
    private void showWarning(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
    }
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    private void showInfo(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}