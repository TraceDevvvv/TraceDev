/**
 * Main Dashboard GUI for the Report Statistics System
 * Provides interface for agency operators to select locations and view statistical reports
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
public class ReportDashboard extends JFrame {
    private JComboBox<String> locationComboBox;
    private JButton viewReportButton;
    private JLabel statusLabel;
    private JPanel mainPanel;
    private ReportService reportService;
    public ReportDashboard() {
        reportService = new ReportService();
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Report Statistics Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Statistical Report System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        // Control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Location selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("Select Location:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loadLocations(); // Load locations into combo box
        controlPanel.add(locationComboBox, gbc);
        // View Report button
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        viewReportButton = new JButton("View Statistical Report");
        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStatisticalReport();
            }
        });
        controlPanel.add(viewReportButton, gbc);
        // Status label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        statusLabel = new JLabel("Ready to generate report");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(statusLabel, gbc);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    private void loadLocations() {
        try {
            // Step 2: Upload the list of places in the system
            List<Location> locations = reportService.getAllLocations();
            String[] locationNames = new String[locations.size() + 1];
            locationNames[0] = "-- Select a Location --";
            for (int i = 0; i < locations.size(); i++) {
                locationNames[i + 1] = locations.get(i).getName();
            }
            locationComboBox = new JComboBox<>(locationNames);
            locationComboBox.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading locations: " + e.getMessage(),
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
            String[] defaultLocations = {"-- Select a Location --", "Default Location"};
            locationComboBox = new JComboBox<>(defaultLocations);
        }
    }
    private void viewStatisticalReport() {
        // Step 3: Select a location and submit the form
        String selectedLocation = (String) locationComboBox.getSelectedItem();
        if (selectedLocation == null || selectedLocation.equals("-- Select a Location --")) {
            JOptionPane.showMessageDialog(this, 
                "Please select a location first",
                "Selection Required",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            statusLabel.setText("Generating report for: " + selectedLocation + "...");
            viewReportButton.setEnabled(false);
            // Step 4: Upload site feedback obtained by activating the SearchSite use case
            ReportData reportData = reportService.generateStatisticalReport(selectedLocation);
            // Display the report
            StatisticalReport reportWindow = new StatisticalReport(
                reportData.getLocationName(),
                reportData.getStatistics(),
                reportData.getFeedbackData()
            );
            reportWindow.setVisible(true);
            reportWindow.setLocationRelativeTo(this);
            statusLabel.setText("Report generated successfully for: " + selectedLocation);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error generating report: " + e.getMessage() + "\nPlease check your connection and try again.",
                "Report Generation Failed",
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Report generation failed: " + e.getMessage());
        } finally {
            viewReportButton.setEnabled(true);
        }
    }
}