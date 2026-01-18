'''
Main GUI interface for news deletion operations
Implements all user interface components and event handling
Requires agency operator to be logged in
'''
package com.chatdev.newsapp;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class NewsDeletionGUI extends JFrame {
    private NewsService newsService;
    private DatabaseManager dbManager;
    private JTable newsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JTextArea newsDetailsArea;
    private JButton deleteButton;
    private JButton refreshButton;
    private JButton cancelButton;
    private JButton logoutButton;
    private JLabel userLabel;
    public NewsDeletionGUI(DatabaseManager dbManager) {
        // Store shared database manager
        this.dbManager = dbManager;
        this.newsService = new NewsService(dbManager);
        // Verify user is logged in before proceeding
        verifyAuthentication();
        initializeUI();
    }
    /**
     * Verifies that agency operator is logged in
     */
    private void verifyAuthentication() {
        try {
            newsService.validateUserSession();
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null,
                "Access Denied: " + e.getMessage() + "\n\nPlease login first.",
                "Authentication Required",
                JOptionPane.ERROR_MESSAGE);
            // Return to login screen
            returnToLogin();
        }
    }
    /**
     * Initializes the user interface
     */
    private void initializeUI() {
        setTitle("News Deletion System - Agency Operator");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
        setSize(950, 700);
        setLocationRelativeTo(null); // Center on screen
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Add user info panel at the top
        JPanel userPanel = createUserPanel();
        mainPanel.add(userPanel, BorderLayout.NORTH);
        // Create center panel with table
        JPanel centerPanel = createNewsTablePanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // Create details panel
        JPanel detailsPanel = createDetailsPanel();
        mainPanel.add(detailsPanel, BorderLayout.EAST);
        // Create button panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Creates user information panel with logout option
     */
    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // Get current user from session
        SessionManager session = SessionManager.getInstance();
        String username = session.getUsername();
        userLabel = new JLabel("Logged in as: " + username + " (Agency Operator)");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(userLabel, BorderLayout.WEST);
        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogout();
            }
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        panel.add(logoutPanel, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            new EmptyBorder(5, 5, 5, 5)
        ));
        return panel;
    }
    /**
     * Creates the news table panel with scroll pane
     */
    private JPanel createNewsTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "All News Articles", 
            TitledBorder.LEFT, 
            TitledBorder.TOP
        ));
        // Create table model with columns
        String[] columns = {"ID", "Title", "Author", "Publish Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        // Create table with model
        newsTable = new JTable(tableModel);
        newsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        newsTable.setRowHeight(25);
        newsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        // Add selection listener to update details when row is selected
        newsTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && newsTable.getSelectedRow() >= 0) {
                int selectedRow = newsTable.getSelectedRow();
                int newsId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                updateNewsDetails(newsId);
                deleteButton.setEnabled(true);
            }
        });
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(newsTable);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        // Load initial data
        refreshNewsTable();
        return panel;
    }
    /**
     * Creates the news details panel
     */
    private JPanel createDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), 
            "News Details", 
            TitledBorder.LEFT, 
            TitledBorder.TOP
        ));
        panel.setPreferredSize(new Dimension(350, 400));
        // Create text area for details
        newsDetailsArea = new JTextArea();
        newsDetailsArea.setEditable(false);
        newsDetailsArea.setLineWrap(true);
        newsDetailsArea.setWrapStyleWord(true);
        newsDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        newsDetailsArea.setText("Select a news item from the list to view details.");
        JScrollPane detailsScroll = new JScrollPane(newsDetailsArea);
        panel.add(detailsScroll, BorderLayout.CENTER);
        // Add status label at bottom
        statusLabel = new JLabel("Ready. Please select a news item to delete.");
        statusLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            new EmptyBorder(5, 5, 5, 5)
        ));
        panel.add(statusLabel, BorderLayout.SOUTH);
        return panel;
    }
    /**
     * Creates the button panel with actions
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        // Delete button
        deleteButton = new JButton("Delete Selected News");
        deleteButton.setEnabled(false); // Disabled until news is selected
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedNews();
            }
        });
        // Refresh button
        refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshNewsTable();
            }
        });
        // Cancel button
        cancelButton = new JButton("Cancel Operation");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOperation();
            }
        });
        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(cancelButton);
        return panel;
    }
    /**
     * Refreshes the news table with current data
     */
    private void refreshNewsTable() {
        try {
            // Verify user is still logged in
            newsService.validateUserSession();
            // Clear existing data
            tableModel.setRowCount(0);
            // Get all news from service
            List<News> allNews = newsService.getAllNews();
            // Add news to table
            for (News news : allNews) {
                Object[] row = {
                    news.getId(),
                    news.getTitle(),
                    news.getAuthor(),
                    news.getPublishDate()
                };
                tableModel.addRow(row);
            }
            statusLabel.setText("Loaded " + allNews.size() + " news articles.");
            deleteButton.setEnabled(false);
            newsDetailsArea.setText("Select a news item from the list to view details.");
        } catch (SecurityException e) {
            handleSessionExpired();
        }
    }
    /**
     * Updates the news details area with selected news information
     * @param newsId The ID of the news to display
     */
    private void updateNewsDetails(int newsId) {
        News news = newsService.getNewsDetails(newsId);
        if (news != null) {
            StringBuilder details = new StringBuilder();
            details.append("ID: ").append(news.getId()).append("\n\n");
            details.append("TITLE: ").append(news.getTitle()).append("\n\n");
            details.append("AUTHOR: ").append(news.getAuthor()).append("\n\n");
            details.append("PUBLISH DATE: ").append(news.getPublishDate()).append("\n\n");
            details.append("CONTENT:\n").append(news.getContent()).append("\n");
            newsDetailsArea.setText(details.toString());
            statusLabel.setText("Selected: " + news.getTitle());
        }
    }
    /**
     * Handles the deletion of selected news with confirmation
     */
    private void deleteSelectedNews() {
        try {
            // Verify user is still logged in
            newsService.validateUserSession();
            int selectedRow = newsTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a news item to delete.", 
                    "No Selection", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            int newsId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String newsTitle = tableModel.getValueAt(selectedRow, 1).toString();
            // Step 4: Ask for confirmation
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the following news?\n\n" +
                "ID: " + newsId + "\n" +
                "Title: " + newsTitle + "\n\n" +
                "This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            // Step 5: If confirmed, proceed with deletion
            if (confirm == JOptionPane.YES_OPTION) {
                // Step 6: Delete the news data
                String result = newsService.deleteNews(newsId);
                // Display result
                if (result.startsWith("Success")) {
                    JOptionPane.showMessageDialog(this,
                        result + "\n\nDeleted successfully.",
                        "Deletion Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the table to reflect deletion
                    refreshNewsTable();
                } else {
                    JOptionPane.showMessageDialog(this,
                        result,
                        "Deletion Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
                statusLabel.setText(result);
            } else {
                statusLabel.setText("Deletion cancelled by operator.");
            }
        } catch (SecurityException e) {
            handleSessionExpired();
        }
    }
    /**
     * Handles operation cancellation
     */
    private void cancelOperation() {
        try {
            newsService.validateUserSession();
            newsTable.clearSelection();
            deleteButton.setEnabled(false);
            newsDetailsArea.setText("Select a news item from the list to view details.");
            statusLabel.setText("Operation cancelled. Ready for new operation.");
            JOptionPane.showMessageDialog(this,
                "Operation cancelled. No news were deleted.",
                "Cancellation",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (SecurityException e) {
            handleSessionExpired();
        }
    }
    /**
     * Performs logout operation
     */
    private void performLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?\n\n" +
            "Any unsaved changes will be lost.",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            returnToLogin();
        }
    }
    /**
     * Handles expired or invalid sessions
     */
    private void handleSessionExpired() {
        JOptionPane.showMessageDialog(this,
            "Your session has expired or is invalid.\n\n" +
            "Please login again to continue.",
            "Session Expired",
            JOptionPane.WARNING_MESSAGE);
        returnToLogin();
    }
    /**
     * Returns to login screen
     */
    private void returnToLogin() {
        this.dispose();
        LoginGUI loginGUI = new LoginGUI(dbManager);
        loginGUI.setVisible(true);
    }
    /**
     * Confirms exit when window is closing
     */
    private void confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to exit the News Deletion System?\n\n" +
            "Any unsaved changes will be lost.",
            "Confirm Exit",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // Logout before exiting
            SessionManager session = SessionManager.getInstance();
            session.logout();
            System.exit(0);
        }
    }
}