package com.restaurantstats;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A simple Swing form to display statistics for a refreshment point.
 */
public class StatisticsForm extends JFrame {

    private StatisticsService service;
    private String refreshmentPointId;
    private JTextArea displayArea;
    private JLabel statusLabel;

    public StatisticsForm(String refreshmentPointId) {
        this.service = new StatisticsService();
        this.refreshmentPointId = refreshmentPointId;
        initializeUI();
        loadStatistics();
    }

    /**
     * Sets up the UI components.
     */
    private void initializeUI() {
        setTitle("Personal Statistics - Point of Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Header with point ID
        JLabel header = new JLabel("Statistics for Refreshment Point: " + refreshmentPointId, SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        add(header, BorderLayout.NORTH);

        // Central display area for statistics
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Status label at the bottom
        statusLabel = new JLabel("Ready", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Refresh button
        JButton refreshButton = new JButton("Refresh Statistics");
        refreshButton.addActionListener(e -> loadStatistics());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.EAST);
    }

    /**
     * Loads statistics from the service and updates the display.
     */
    private void loadStatistics() {
        // Validate point ID before fetching
        if (!service.isValidPoint(refreshmentPointId)) {
            displayArea.setText("Error: Invalid refreshment point ID.");
            statusLabel.setText("Invalid ID");
            return;
        }

        // Update status to loading
        statusLabel.setText("Loading statistics...");
        displayArea.setText("Fetching data...\n");

        // Use a separate thread to keep UI responsive and meet the 3‑second requirement
        SwingWorker<List<StatisticsService.StatisticItem>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<StatisticsService.StatisticItem> doInBackground() {
                return service.fetchStatistics(refreshmentPointId);
            }

            @Override
            protected void done() {
                try {
                    List<StatisticsService.StatisticItem> stats = get();
                    displayStatistics(stats);
                    statusLabel.setText("Data loaded successfully.");
                } catch (Exception ex) {
                    displayArea.setText("Error fetching statistics: " + ex.getMessage());
                    statusLabel.setText("Connection interrupted or error occurred.");
                }
            }
        };
        worker.execute();
    }

    /**
     * Formats and displays the statistics list in the text area.
     *
     * @param stats the list of StatisticItem to display
     */
    private void displayStatistics(List<StatisticsService.StatisticItem> stats) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Personal Statistics ===\n\n");
        for (StatisticsService.StatisticItem item : stats) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("\n--- End of Report ---");
        displayArea.setText(sb.toString());
    }

    /**
     * Main method to launch the form (for demonstration).
     * In a real system, the Point Of Restaurants Operator would be authenticated first.
     */
    public static void main(String[] args) {
        // Simulate authenticated user with a refreshment point ID.
        // This ID would come from the session/authentication in a real app.
        String pointId = "RP-2024-001";

        SwingUtilities.invokeLater(() -> {
            StatisticsForm form = new StatisticsForm(pointId);
            form.setVisible(true);
        });
    }
}