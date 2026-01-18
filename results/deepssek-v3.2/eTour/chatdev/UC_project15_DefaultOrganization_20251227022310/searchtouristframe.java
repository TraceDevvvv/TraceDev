'''
GUI frame for searching and selecting tourists (Use Case: SearchTourist).
Implements Step 1 of the use case flow.
'''
package com.etour.agency;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class SearchTouristFrame extends JFrame {
    private TouristService touristService;
    private JTable touristTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton selectButton;
    private JButton refreshButton;
    private JButton exitButton;
    public SearchTouristFrame(TouristService touristService) {
        this.touristService = touristService;
        initializeUI();
        loadTourists();
    }
    /**
     * Initialize the GUI components
     */
    private void initializeUI() {
        setTitle("Search Tourist - Agency Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Create search panel
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(new JLabel("Search Tourists:"), BorderLayout.WEST);
        searchField = new JTextField(30);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { performSearch(); }
            @Override
            public void removeUpdate(DocumentEvent e) { performSearch(); }
            @Override
            public void changedUpdate(DocumentEvent e) { performSearch(); }
        });
        searchPanel.add(searchField, BorderLayout.CENTER);
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadTourists());
        searchPanel.add(refreshButton, BorderLayout.EAST);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        // Create table for displaying tourists
        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Phone", "Nationality", "Passport"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        touristTable = new JTable(tableModel);
        touristTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        touristTable.getSelectionModel().addListSelectionListener(e -> {
            selectButton.setEnabled(touristTable.getSelectedRow() != -1);
        });
        JScrollPane scrollPane = new JScrollPane(touristTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> MainApp.exitApplication());
        buttonPanel.add(exitButton);
        selectButton = new JButton("Select Tourist to Modify");
        selectButton.setEnabled(false);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = touristTable.getSelectedRow();
                if (selectedRow != -1) {
                    String touristId = (String) tableModel.getValueAt(selectedRow, 0);
                    Tourist selectedTourist = touristService.getTouristById(touristId);
                    if (selectedTourist != null) {
                        // Navigate to modify tourist frame (Step 2 of use case)
                        MainApp.showModifyTouristFrame(selectedTourist);
                    }
                }
            }
        });
        buttonPanel.add(selectButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Load all tourists into the table
     */
    private void loadTourists() {
        List<Tourist> tourists = touristService.getAllTourists();
        updateTable(tourists);
    }
    /**
     * Perform search based on search field text
     */
    private void performSearch() {
        String query = searchField.getText().trim();
        List<Tourist> results;
        if (query.isEmpty()) {
            results = touristService.getAllTourists();
        } else {
            results = touristService.searchTourists(query);
        }
        updateTable(results);
    }
    /**
     * Update the table with tourist data
     * @param tourists List of tourists to display
     */
    private void updateTable(List<Tourist> tourists) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Tourist tourist : tourists) {
            Object[] rowData = {
                tourist.getId(),
                tourist.getFirstName(),
                tourist.getLastName(),
                tourist.getEmail(),
                tourist.getPhoneNumber(),
                tourist.getNationality(),
                tourist.getPassportNumber()
            };
            tableModel.addRow(rowData);
        }
    }
}