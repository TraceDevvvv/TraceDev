'''
Statistics display frame that shows data for the statistics associated with refreshment point.
Implements step 2 of the use case: "Displays a form that shows data for the statistics associated refreshment point."
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class StatisticsFrame extends JFrame {
    private RestaurantOperator operator;
    private StatisticsService statsService;
    private JTextArea statsTextArea;
    private JButton refreshButton;
    private JButton backButton;
    private JLabel statusLabel;
    public StatisticsFrame(RestaurantOperator operator) {
        this.operator = operator;
        this.statsService = new StatisticsService();
        setTitle("Point of Restaurant - Personal Statistics");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadStatistics(); // Load initial statistics
    }
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Personal Statistics for " + operator.getRestaurantName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titlePanel.add(titleLabel);
        // Statistics Display Panel
        JPanel statsPanel = new JPanel(new BorderLayout(10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statsTextArea = new JTextArea(15, 40);
        statsTextArea.setEditable(false);
        statsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statsTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(statsTextArea);
        statsPanel.add(scrollPane, BorderLayout.CENTER);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        refreshButton = new JButton("Refresh Statistics");
        backButton = new JButton("Back to Menu");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStatistics();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        // Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready");
        statusPanel.add(statusLabel);
        // Add panels to frame
        add(titlePanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusPanel, BorderLayout.AFTER_LAST_LINE);
    }
    /**
     * Loads and displays statistics data.
     * Handles server connection interruptions as specified in exit conditions.
     */
    private void loadStatistics() {
        statusLabel.setText("Loading statistics...");
        refreshButton.setEnabled(false);
        // Use SwingWorker to prevent UI freezing during data loading
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            private StatisticsData statsData = null;
            private String errorMessage = null;
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    // Simulate network delay
                    Thread.sleep(1000);
                    // Fetch statistics data
                    statsData = statsService.fetchStatisticsData(operator.getUsername());
                } catch (StatisticsService.ServerConnectionException e) {
                    errorMessage = e.getMessage();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    errorMessage = "Operation interrupted";
                }
                return null;
            }
            @Override
            protected void done() {
                refreshButton.setEnabled(true);
                if (errorMessage != null) {
                    // Handle server connection interruption
                    statusLabel.setText("Connection Error: " + errorMessage);
                    statsTextArea.setText("ERROR: " + errorMessage + "\n\n" +
                                        "Please check your internet connection and try again.");
                    // Offer reconnection option
                    int choice = JOptionPane.showConfirmDialog(StatisticsFrame.this,
                        "Server connection was interrupted. Would you like to try reconnecting?",
                        "Connection Error",
                        JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        if (statsService.reconnectToServer()) {
                            JOptionPane.showMessageDialog(StatisticsFrame.this,
                                "Reconnected successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                            loadStatistics();
                        } else {
                            JOptionPane.showMessageDialog(StatisticsFrame.this,
                                "Failed to reconnect. Please try again later.",
                                "Reconnection Failed",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (statsData != null) {
                    // Display statistics data
                    statusLabel.setText("Statistics loaded successfully");
                    displayStatistics(statsData);
                }
            }
        };
        worker.execute();
    }
    /**
     * Displays formatted statistics data in the text area.
     * 
     * @param data The statistics data to display
     */
    private void displayStatistics(StatisticsData data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder statsText = new StringBuilder();
        statsText.append("=".repeat(60)).append("\n");
        statsText.append("PERSONAL STATISTICS REPORT\n");
        statsText.append("=".repeat(60)).append("\n\n");
        statsText.append(String.format("%-25s: %s\n", "Report Generated", dateFormat.format(data.getReportDate())));
        statsText.append(String.format("%-25s: %s\n", "Restaurant", operator.getRestaurantName()));
        statsText.append(String.format("%-25s: %s\n", "Operator", operator.getUsername()));
        statsText.append("\n");
        statsText.append("PERFORMANCE METRICS\n");
        statsText.append("-".repeat(60)).append("\n");
        statsText.append(String.format("%-25s: %d orders\n", "Total Orders", data.getTotalOrders()));
        statsText.append(String.format("%-25s: $%.2f\n", "Total Revenue", data.getTotalRevenue()));
        statsText.append(String.format("%-25s: $%.2f\n", "Average Order Value", data.getAverageOrderValue()));
        statsText.append(String.format("%-25s: %d:00 (24h format)\n", "Peak Business Hour", data.getPeakHour()));
        statsText.append(String.format("%-25s: %s (ID: %d)\n", "Most Popular Item", 
                                      data.getMostPopularItemName(), data.getMostPopularItemId()));
        statsText.append("\n");
        statsText.append("QUALITY METRICS\n");
        statsText.append("-".repeat(60)).append("\n");
        int satisfaction = data.getCustomerSatisfactionScore();
        String satisfactionLevel;
        if (satisfaction >= 90) {
            satisfactionLevel = "Excellent";
        } else if (satisfaction >= 75) {
            satisfactionLevel = "Good";
        } else if (satisfaction >= 60) {
            satisfactionLevel = "Fair";
        } else {
            satisfactionLevel = "Needs Improvement";
        }
        statsText.append(String.format("%-25s: %d/100 (%s)\n", "Customer Satisfaction", 
                                      satisfaction, satisfactionLevel));
        statsText.append("\n");
        statsText.append("SUMMARY\n");
        statsText.append("-".repeat(60)).append("\n");
        if (data.getTotalOrders() > 200) {
            statsText.append("• Very busy period - consider increasing staff\n");
        } else if (data.getTotalOrders() > 100) {
            statsText.append("• Standard business level\n");
        } else {
            statsText.append("• Consider promotional activities\n");
        }
        if (satisfaction >= 90) {
            statsText.append("• Excellent customer satisfaction - keep up the great work!\n");
        } else if (satisfaction < 70) {
            statsText.append("• Customer satisfaction needs attention\n");
        }
        if (data.getPeakHour() >= 12 && data.getPeakHour() <= 14) {
            statsText.append("• Peak during lunch hours\n");
        } else if (data.getPeakHour() >= 17 && data.getPeakHour() <= 19) {
            statsText.append("• Peak during dinner hours\n");
        }
        statsText.append("\n");
        statsText.append("=".repeat(60)).append("\n");
        statsText.append("Report ends. Last updated: ").append(dateFormat.format(new Date())).append("\n");
        statsText.append("=".repeat(60));
        statsTextArea.setText(statsText.toString());
        statsTextArea.setForeground(Color.BLACK);
        // The exit condition is satisfied: "The system displays the data on the screen."
    }
}