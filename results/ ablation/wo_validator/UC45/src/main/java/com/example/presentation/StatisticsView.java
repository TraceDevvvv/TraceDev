package com.example.presentation;

import com.example.dto.StatisticsReportDTO;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * A simple Swing view for displaying statistics.
 * In a real application, this could be a web view or another UI framework.
 */
public class StatisticsView {
    private StatisticsController controller;

    public StatisticsView(StatisticsController controller) {
        this.controller = controller;
    }

    /**
     * Shows the display form (simulated with a simple dialog).
     */
    public void showDisplayForm() {
        JFrame frame = new JFrame("Statistics Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel label = new JLabel("Enter Point ID:");
        JTextField pointIdField = new JTextField();
        JButton fetchButton = new JButton("Fetch Statistics");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        fetchButton.addActionListener(e -> {
            String pointId = pointIdField.getText();
            if (pointId.isEmpty()) {
                showError("Point ID cannot be empty");
                return;
            }
            try {
                StatisticsReportDTO report = controller.displayStatistics(pointId);
                renderStatistics(report);
            } catch (Exception ex) {
                showError("Error fetching statistics: " + ex.getMessage());
            }
        });

        panel.add(label);
        panel.add(pointIdField);
        panel.add(fetchButton);
        panel.add(new JLabel("Results:"));
        panel.add(new JScrollPane(resultArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * Renders the statistics report (simulated to console and a dialog).
     */
    public void renderStatistics(StatisticsReportDTO report) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("Point Name: ").append(report.getPointName()).append("\n");
        sb.append("Period: ").append(sdf.format(report.getPeriodStart()))
          .append(" to ").append(sdf.format(report.getPeriodEnd())).append("\n");
        sb.append("Total Sales: $").append(String.format("%.2f", report.getTotalSales())).append("\n");
        sb.append("Average Rating: ").append(String.format("%.1f", report.getAverageRating())).append("\n");
        sb.append("Popular Items: ").append(String.join(", ", report.getPopularItems())).append("\n");

        // Display in a dialog (or could update UI components)
        JOptionPane.showMessageDialog(null, sb.toString(), "Statistics", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(sb.toString());
    }

    /**
     * Shows an error message.
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        System.err.println("Error: " + message);
    }
}