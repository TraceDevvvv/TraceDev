/**
 * Statistical Report Display Window
 * Displays comprehensive statistics for the selected location
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
public class StatisticalReport extends JFrame {
    private JTextArea reportTextArea;
    private JTable dataTable;
    private JLabel titleLabel;
    public StatisticalReport(String locationName, Map<String, Object> statistics, List<SiteFeedback> feedbackData) {
        initializeUI(locationName, statistics, feedbackData);
    }
    private void initializeUI(String locationName, Map<String, Object> statistics, List<SiteFeedback> feedbackData) {
        setTitle("Statistical Report - " + locationName);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        titleLabel = new JLabel("Statistical Report for: " + locationName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create tabbed pane for different report views
        JTabbedPane tabbedPane = new JTabbedPane();
        // Tab 1: Summary Statistics
        JPanel summaryPanel = createSummaryPanel(statistics);
        tabbedPane.addTab("Summary", summaryPanel);
        // Tab 2: Detailed Feedback Data
        JPanel detailsPanel = createDetailsPanel(feedbackData);
        tabbedPane.addTab("Detailed Data", detailsPanel);
        // Tab 3: Raw Report
        JPanel rawPanel = createRawReportPanel(locationName, statistics, feedbackData);
        tabbedPane.addTab("Full Report", rawPanel);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        // Close button
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close Report");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private JPanel createSummaryPanel(Map<String, Object> statistics) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Display key statistics
        for (Map.Entry<String, Object> entry : statistics.entrySet()) {
            JLabel keyLabel = new JLabel(entry.getKey() + ":");
            keyLabel.setFont(new Font("Arial", Font.BOLD, 12));
            JLabel valueLabel = new JLabel(entry.getValue().toString());
            valueLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            panel.add(keyLabel);
            panel.add(valueLabel);
        }
        return panel;
    }
    private JPanel createDetailsPanel(List<SiteFeedback> feedbackData) {
        JPanel panel = new JPanel(new BorderLayout());
        // Create table model
        String[] columns = {"Date", "Rating", "Comments", "Visitor Count"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        // Populate table with feedback data
        for (SiteFeedback feedback : feedbackData) {
            Object[] row = {
                feedback.getDate(),
                feedback.getRating(),
                feedback.getComments(),
                feedback.getVisitorCount()
            };
            model.addRow(row);
        }
        dataTable = new JTable(model);
        dataTable.setAutoCreateRowSorter(true); // Enable sorting
        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        // Add statistics label
        JLabel statsLabel = new JLabel("Total Feedback Entries: " + feedbackData.size());
        statsLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(statsLabel, BorderLayout.SOUTH);
        return panel;
    }
    private JPanel createRawReportPanel(String locationName, Map<String, Object> statistics, List<SiteFeedback> feedbackData) {
        JPanel panel = new JPanel(new BorderLayout());
        reportTextArea = new JTextArea();
        reportTextArea.setEditable(false);
        reportTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        // Build comprehensive report text
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("=".repeat(60)).append("\n");
        reportBuilder.append("STATISTICAL REPORT FOR: ").append(locationName).append("\n");
        reportBuilder.append("=".repeat(60)).append("\n\n");
        reportBuilder.append("SUMMARY STATISTICS:\n");
        reportBuilder.append("-".repeat(40)).append("\n");
        for (Map.Entry<String, Object> entry : statistics.entrySet()) {
            reportBuilder.append(String.format("%-30s: %s%n", entry.getKey(), entry.getValue()));
        }
        reportBuilder.append("\n\nDETAILED FEEDBACK DATA:\n");
        reportBuilder.append("-".repeat(40)).append("\n");
        reportBuilder.append(String.format("%-12s %-8s %-10s %-20s%n", 
            "Date", "Rating", "Visitors", "Comments"));
        reportBuilder.append("-".repeat(60)).append("\n");
        for (SiteFeedback feedback : feedbackData) {
            String truncatedComments = feedback.getComments();
            if (truncatedComments.length() > 20) {
                truncatedComments = truncatedComments.substring(0, 17) + "...";
            }
            reportBuilder.append(String.format("%-12s %-8.1f %-10d %-20s%n",
                feedback.getDate(),
                feedback.getRating(),
                feedback.getVisitorCount(),
                truncatedComments));
        }
        reportBuilder.append("\n").append("=".repeat(60)).append("\n");
        reportBuilder.append("Report generated on: ").append(java.time.LocalDate.now()).append("\n");
        reportBuilder.append("=".repeat(60));
        reportTextArea.setText(reportBuilder.toString());
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}