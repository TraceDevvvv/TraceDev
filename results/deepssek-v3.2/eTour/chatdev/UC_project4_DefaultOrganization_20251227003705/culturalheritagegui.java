'''
GUI for Cultural Heritage Search System
Provides search form and results display
'''
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
public class CulturalHeritageGUI {
    private JFrame frame;
    private JTextField nameField;
    private JComboBox<String> typeComboBox;
    private JTextField locationField;
    private JTextField yearField;
    private JButton searchButton;
    private JButton clearButton;
    private JTable resultsTable;
    private JLabel statusLabel;
    public void createAndShowGUI() {
        frame = new JFrame("Cultural Heritage Search System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel searchPanel = createSearchPanel();
        JPanel resultsPanel = createResultsPanel();
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Ready to search cultural heritage objects");
        statusPanel.add(statusLabel);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        String[] types = {"Any", "Monument", "Artifact", "Building", "Site", "Tradition"};
        typeComboBox = new JComboBox<>(types);
        panel.add(typeComboBox, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationField = new JTextField(20);
        panel.add(locationField, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        yearField = new JTextField(10);
        panel.add(yearField, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        buttonPanel.add(searchButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        buttonPanel.add(clearButton);
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        return panel;
    }
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        String[] columnNames = {"ID", "Name", "Type", "Location", "Year", "Description"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable = new JTable(model);
        resultsTable.setRowHeight(25);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        resultsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        resultsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        resultsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        resultsTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        resultsTable.getColumnModel().getColumn(5).setPreferredWidth(350);
        panel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);
        return panel;
    }
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String type = (String) typeComboBox.getSelectedItem();
            String location = locationField.getText().trim();
            String year = yearField.getText().trim();
            if (!year.isEmpty()) {
                try {
                    Integer.parseInt(year);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please enter a valid year number or leave blank", 
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            statusLabel.setText("Searching...");
            searchButton.setEnabled(false);
            new Thread(() -> {
                try {
                    List<CulturalObject> results = performSearch(name, type, location, year);
                    SwingUtilities.invokeLater(() -> {
                        updateResultsTable(results);
                        statusLabel.setText("Search completed. Found " + results.size() + " result(s)");
                        searchButton.setEnabled(true);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        statusLabel.setText("Error: " + ex.getMessage());
                        searchButton.setEnabled(true);
                        JOptionPane.showMessageDialog(frame, 
                            "Search failed: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start();
        }
    }
    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            typeComboBox.setSelectedIndex(0);
            locationField.setText("");
            yearField.setText("");
            clearResultsTable();
            statusLabel.setText("Ready to search cultural heritage objects");
        }
    }
    private List<CulturalObject> performSearch(String name, String type, String location, String year) throws IOException {
        long startTime = System.currentTimeMillis();
        try (Socket socket = new Socket("localhost", 8080)) {
            socket.setSoTimeout(5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(name + "|" + type + "|" + location + "|" + year);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            long endTime = System.currentTimeMillis();
            if (endTime - startTime > 3000) {
                System.out.println("Warning: Search took " + (endTime - startTime) + "ms");
            }
            return parseResponse(response);
        } catch (IOException e) {
            System.err.println("Server connection failed, using local search: " + e.getMessage());
            return performLocalSearch(name, type, location, year);
        }
    }
    private List<CulturalObject> performLocalSearch(String name, String type, String location, String year) {
        SearchService searchService = new SearchService();
        return searchService.search(name, type, location, year);
    }
    private List<CulturalObject> parseResponse(String response) {
        List<CulturalObject> results = new ArrayList<>();
        if (response == null || response.isEmpty() || response.startsWith("ERROR")) {
            return results;
        }
        String[] objectStrings = response.split(";");
        for (String objStr : objectStrings) {
            if (objStr.trim().isEmpty()) continue;
            String[] parts = objStr.split("\\|", 6);
            if (parts.length == 6) {
                try {
                    CulturalObject obj = new CulturalObject(
                        parts[0], parts[1], parts[2], parts[3], 
                        Integer.parseInt(parts[4]), parts[5]
                    );
                    results.add(obj);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing year for object: " + parts[0]);
                }
            }
        }
        return results;
    }
    private void updateResultsTable(List<CulturalObject> results) {
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setRowCount(0);
        for (CulturalObject obj : results) {
            model.addRow(new Object[]{
                obj.getId(),
                obj.getName(),
                obj.getType(),
                obj.getLocation(),
                obj.getYear(),
                obj.getDescription()
            });
        }
    }
    private void clearResultsTable() {
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setRowCount(0);
    }
}