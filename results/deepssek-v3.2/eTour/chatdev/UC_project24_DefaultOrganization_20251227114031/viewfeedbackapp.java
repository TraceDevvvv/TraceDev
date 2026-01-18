/**
 * Main GUI application window for the ViewFeedback use case.
 * Displays a list of sites and allows viewing feedback for a selected site.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
public class ViewFeedbackApp extends JFrame {
    private JTable siteTable;
    private DefaultTableModel siteTableModel;
    private JTextArea feedbackArea;
    private JButton viewFeedbackButton;
    private JButton logoutButton;
    private JLabel statusLabel;
    private DatabaseSimulator dbSimulator;
    public ViewFeedbackApp() {
        super("View Feedback Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        dbSimulator = new DatabaseSimulator();
        // Initialize components
        initComponents();
        // Load initial site list (simulating result from SearchSite use case)
        loadSites();
    }
    private void initComponents() {
        // North panel for status
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Status: Logged in as Agency Operator");
        northPanel.add(statusLabel);
        add(northPanel, BorderLayout.NORTH);
        // Center panel for site list and feedback
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Site list panel (left)
        JPanel sitePanel = new JPanel(new BorderLayout());
        sitePanel.setBorder(BorderFactory.createTitledBorder("Sites (from SearchSite)"));
        siteTableModel = new DefaultTableModel(new Object[]{"ID", "Site Name"}, 0);
        siteTable = new JTable(siteTableModel);
        siteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sitePanel.add(new JScrollPane(siteTable), BorderLayout.CENTER);
        centerPanel.add(sitePanel);
        // Feedback panel (right)
        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.setBorder(BorderFactory.createTitledBorder("Feedback for Selected Site"));
        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);
        feedbackPanel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);
        centerPanel.add(feedbackPanel);
        add(centerPanel, BorderLayout.CENTER);
        // South panel for buttons
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        viewFeedbackButton = new JButton("View Feedback");
        logoutButton = new JButton("Logout");
        // Add action listeners
        viewFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFeedbackForSelectedSite();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        southPanel.add(viewFeedbackButton);
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads sites from the database simulator (simulating SearchSite use case result).
     */
    private void loadSites() {
        try {
            List<Site> sites = dbSimulator.getAllSites();
            siteTableModel.setRowCount(0); // Clear existing rows
            for (Site site : sites) {
                siteTableModel.addRow(new Object[]{site.getId(), site.getName()});
            }
            statusLabel.setText("Status: Sites loaded successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading sites: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Status: Error loading sites.");
        }
    }
    /**
     * Fetches and displays feedback for the site selected in the table.
     * Implements step 2 of the use case flow.
     */
    private void viewFeedbackForSelectedSite() {
        int selectedRow = siteTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a site first.", "No Site Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int siteId = (int) siteTableModel.getValueAt(selectedRow, 0);
        String siteName = (String) siteTableModel.getValueAt(selectedRow, 1);
        try {
            // The connection interruption simulation is handled in DatabaseSimulator
            List<Feedback> feedbackList = dbSimulator.getFeedbackForSite(siteId);
            feedbackArea.setText(""); // Clear previous feedback
            if (feedbackList.isEmpty()) {
                feedbackArea.append("No feedback available for site: " + siteName + " (ID: " + siteId + ")\n");
            } else {
                feedbackArea.append("Feedback for site: " + siteName + " (ID: " + siteId + ")\n");
                feedbackArea.append("========================================\n");
                for (Feedback fb : feedbackList) {
                    feedbackArea.append("Rating: " + fb.getRating() + "/5\n");
                    feedbackArea.append("Comment: " + fb.getComment() + "\n");
                    feedbackArea.append("----------------------------------------\n");
                }
            }
            statusLabel.setText("Status: Feedback loaded for site: " + siteName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading feedback: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Status: Error loading feedback.");
        }
    }
    /**
     * Logs out the agency operator (simulated).
     */
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logged out successfully.");
            System.exit(0);
        }
    }
}