import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Main GUI frame for search tourist functionality
 * Implements steps 1-3 from the flow of events:
 * 1. Access the search functionality
 * 2. Show the form
 * 3. Fill out the form and submit
 */
public class SearchTouristFrame extends JFrame {
    private TouristService touristService;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField idField;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    public SearchTouristFrame(TouristService touristService) {
        this.touristService = touristService;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Search Tourist System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        // Create search panel (form)
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);
        // Create results panel
        JPanel resultsPanel = createResultsPanel();
        add(resultsPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center on screen
    }
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Criteria"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        // Email field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        // Tourist ID field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Tourist ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        panel.add(idField, gbc);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        // Clear button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
                clearResults();
            }
        });
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(buttonPanel, gbc);
        return panel;
    }
    private JPanel createResultsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search Results"));
        // Create table model with column names
        String[] columnNames = {"Tourist ID", "Name", "Email", "Phone", "Nationality"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    /**
     * Step 4: Process the search request
     */
    private void performSearch() {
        try {
            // Create search criteria from form
            SearchCriteria criteria = new SearchCriteria();
            criteria.setName(nameField.getText().trim());
            criteria.setEmail(emailField.getText().trim());
            criteria.setTouristId(idField.getText().trim());
            // Call service to search tourists
            List<Tourist> tourists = touristService.searchTourists(criteria);
            // Clear existing results
            clearResults();
            // Populate results in table
            for (Tourist tourist : tourists) {
                Object[] row = {
                    tourist.getId(),
                    tourist.getName(),
                    tourist.getEmail(),
                    tourist.getPhone(),
                    tourist.getNationality()
                };
                tableModel.addRow(row);
            }
            // Show message if no results found
            if (tourists.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No tourists found matching the search criteria.",
                    "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ConnectionInterruptedException e) {
            JOptionPane.showMessageDialog(this,
                "Connection to ETOUR server was interrupted: " + e.getMessage(),
                "Connection Error",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "An error occurred during search: " + e.getMessage(),
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        idField.setText("");
    }
    private void clearResults() {
        tableModel.setRowCount(0);
    }
}