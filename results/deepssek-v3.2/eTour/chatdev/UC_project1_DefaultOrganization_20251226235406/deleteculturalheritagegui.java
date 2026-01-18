'''
DeleteCulturalHeritageGUI provides the graphical user interface for the delete cultural heritage use case.
Implements the complete flow: view list, select item, ask confirmation, and delete.
'''
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class DeleteCulturalHeritageGUI extends JFrame {
    private DeleteCulturalHeritageController controller;
    private JTextField searchField;
    private JTable culturalHeritageTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JButton confirmButton;
    private JButton loginButton;
    private JPanel loginPanel;
    private JPanel mainPanel;
    private JLabel statusLabel;
    private int selectedCulturalHeritageId;
    private boolean isLoggedIn;
    public DeleteCulturalHeritageGUI() {
        controller = new DeleteCulturalHeritageController();
        isLoggedIn = false;
        selectedCulturalHeritageId = -1;
        setTitle("Cultural Heritage Management System - Delete Cultural Heritage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        createLoginPanel();
        createMainPanel();
        // Start with login panel
        getContentPane().add(loginPanel);
        setVisible(true);
    }
    private void createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel titleLabel = new JLabel("Agency Operator Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(titleLabel, gbc);
        gbc.insets = new Insets(20, 5, 5, 5);
        JLabel userLabel = new JLabel("Username:");
        loginPanel.add(userLabel, gbc);
        JTextField usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);
        JLabel passLabel = new JLabel("Password:");
        loginPanel.add(passLabel, gbc);
        JPasswordField passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);
        gbc.insets = new Insets(20, 5, 5, 5);
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (controller.loginAgencyOperator(username, password)) {
                    isLoggedIn = true;
                    statusLabel.setText("Logged in as Agency Operator");
                    switchToMainPanel();
                } else {
                    JOptionPane.showMessageDialog(DeleteCulturalHeritageGUI.this,
                        "Invalid credentials. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(loginButton, gbc);
    }
    private void createMainPanel() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create top panel with search
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JLabel searchLabel = new JLabel("Search Cultural Heritage:");
        topPanel.add(searchLabel, BorderLayout.WEST);
        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchCulturalHeritage();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                searchCulturalHeritage();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                searchCulturalHeritage();
            }
        });
        topPanel.add(searchField, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Create table for displaying cultural heritage
        String[] columnNames = {"ID", "Name", "Type", "Location", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        culturalHeritageTable = new JTable(tableModel);
        culturalHeritageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        culturalHeritageTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = culturalHeritageTable.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedCulturalHeritageId = (int) tableModel.getValueAt(selectedRow, 0);
                    deleteButton.setEnabled(true);
                } else {
                    deleteButton.setEnabled(false);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(culturalHeritageTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Create bottom panel with buttons and status
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Select a cultural heritage item to delete");
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        deleteButton = new JButton("Delete Selected Item");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(e -> initiateDeletion());
        confirmButton = new JButton("Confirm Deletion");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(e -> confirmDeletion());
        cancelButton = new JButton("Cancel Operation");
        cancelButton.setEnabled(false);
        cancelButton.addActionListener(e -> cancelDeletion());
        buttonPanel.add(deleteButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        // Load initial data
        loadCulturalHeritageData();
    }
    private void switchToMainPanel() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }
    private void loadCulturalHeritageData() {
        List<CulturalHeritage> items = controller.getAllCulturalHeritage();
        updateTable(items);
    }
    private void searchCulturalHeritage() {
        String query = searchField.getText();
        List<CulturalHeritage> results = controller.searchCulturalHeritage(query);
        updateTable(results);
    }
    private void updateTable(List<CulturalHeritage> items) {
        tableModel.setRowCount(0);
        for (CulturalHeritage item : items) {
            tableModel.addRow(new Object[]{
                item.getId(),
                item.getName(),
                item.getType(),
                item.getLocation(),
                item.getDescription()
            });
        }
    }
    private void initiateDeletion() {
        if (selectedCulturalHeritageId == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select a cultural heritage item to delete.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Ask for confirmation (Step 2 in use case)
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the selected cultural heritage item?\n" +
            "This action cannot be undone.",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // Block input controls to prevent multiple submissions
            deleteButton.setEnabled(false);
            searchField.setEnabled(false);
            culturalHeritageTable.setEnabled(false);
            confirmButton.setEnabled(true);
            cancelButton.setEnabled(true);
            if (controller.initiateDeletion(selectedCulturalHeritageId)) {
                statusLabel.setText("Deletion initiated. Please confirm the operation.");
            } else {
                resetUI();
                statusLabel.setText("Cannot initiate deletion. Operation may already be in progress.");
            }
        }
    }
    private void confirmDeletion() {
        String result = controller.confirmDeletion(selectedCulturalHeritageId);
        // Show result in dialog and status
        if (result.startsWith("SUCCESS")) {
            JOptionPane.showMessageDialog(this,
                result,
                "Deletion Successful",
                JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Cultural heritage item successfully deleted.");
        } else {
            JOptionPane.showMessageDialog(this,
                result,
                "Deletion Failed",
                JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Deletion failed: " + result);
        }
        resetUI();
        searchCulturalHeritage(); // Refresh the list
    }
    private void cancelDeletion() {
        controller.cancelDeletion();
        JOptionPane.showMessageDialog(this,
            "Deletion operation has been cancelled.",
            "Operation Cancelled",
            JOptionPane.INFORMATION_MESSAGE);
        statusLabel.setText("Deletion operation cancelled.");
        resetUI();
    }
    private void resetUI() {
        deleteButton.setEnabled(true);
        searchField.setEnabled(true);
        culturalHeritageTable.setEnabled(true);
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
        culturalHeritageTable.clearSelection();
        selectedCulturalHeritageId = -1;
        searchCulturalHeritage(); // Refresh search results
    }
}