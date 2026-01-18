/**
 * Main GUI for the DeleteTag functionality
 * Implements the flow of events: access functionality, display tags, select tags, delete
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DeleteTagFrame extends JFrame {
    private JTable tagTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private JButton refreshButton;
    private TagService tagService;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    public DeleteTagFrame() {
        setTitle("Delete Search Tags - Agency System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        tagService = new TagService();
        initComponents();
        loadTags(); // Load tags on initialization
    }
    private void initComponents() {
        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Delete Search Tags");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        // Center panel with table
        JPanel centerPanel = new JPanel(new BorderLayout());
        // Create table with columns
        String[] columns = {"Select", "Tag ID", "Tag Name", "Description", "Created Date"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Checkbox column
                }
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Only checkbox column is editable
            }
        };
        tagTable = new JTable(tableModel);
        tagTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        tagTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        tagTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(tagTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        // Add table header
        JLabel tableHeader = new JLabel("Existing Search Tags:");
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(tableHeader, BorderLayout.NORTH);
        // Progress bar and status panel
        JPanel progressPanel = new JPanel(new BorderLayout(5, 5));
        progressPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        statusLabel = new JLabel("Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        progressPanel.add(statusLabel, BorderLayout.SOUTH);
        centerPanel.add(progressPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        refreshButton = new JButton("Refresh Tags");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTags();
            }
        });
        buttonPanel.add(refreshButton);
        deleteButton = new JButton("Delete Selected Tags");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTags();
            }
        });
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Load tags from the system and display them in the table
     * Implements step 2: Research in the existing system, the tags and displays them in a form
     */
    private void loadTags() {
        try {
            // Clear existing rows
            tableModel.setRowCount(0);
            statusLabel.setText("Loading tags...");
            // Get tags from service (simulating database fetch)
            List<Tag> tags = tagService.getAllTags();
            // Add tags to table
            for (Tag tag : tags) {
                Object[] row = {
                    false, // Checkbox unchecked by default
                    tag.getId(),
                    tag.getName(),
                    tag.getDescription(),
                    tag.getCreatedDate()
                };
                tableModel.addRow(row);
            }
            statusLabel.setText("Loaded " + tags.size() + " tag(s)");
            if (tags.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tags found in the system.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            // Handle network interruption (server ETOUR connection)
            statusLabel.setText("Error loading tags");
            JOptionPane.showMessageDialog(this,
                "Error connecting to server: " + e.getMessage(),
                "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Delete selected tags from the system
     * Implements steps 3-4: Select tags and send deletion request
     */
    private void deleteSelectedTags() {
        // Count selected tags
        int selectedCount = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
            if (isSelected != null && isSelected) {
                selectedCount++;
            }
        }
        if (selectedCount == 0) {
            JOptionPane.showMessageDialog(this,
                "Please select at least one tag to delete.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + selectedCount + " tag(s)?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        // Disable buttons during deletion
        deleteButton.setEnabled(false);
        refreshButton.setEnabled(false);
        progressBar.setVisible(true);
        progressBar.setMaximum(selectedCount);
        progressBar.setValue(0);
        statusLabel.setText("Deleting tags...");
        // Use SwingWorker for background processing to avoid blocking EDT
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            private int successCount = 0;
            private Exception error = null;
            @Override
            protected Void doInBackground() throws Exception {
                // Collect ind of rows to delete
                List<Integer> rowsToDelete = new ArrayList<>();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
                    if (isSelected != null && isSelected) {
                        String tagId = (String) tableModel.getValueAt(i, 1);
                        try {
                            if (tagService.deleteTag(tagId)) {
                                successCount++;
                                rowsToDelete.add(i);
                                // Safe progress update via publish
                                publish(successCount);
                            }
                        } catch (Exception e) {
                            error = e;
                            break;
                        }
                    }
                }
                // Remove rows in reverse order to maintain ind, done on EDT
                Collections.reverse(rowsToDelete);
                final List<Integer> finalRows = rowsToDelete;
                SwingUtilities.invokeLater(() -> {
                    for (int rowIndex : finalRows) {
                        tableModel.removeRow(rowIndex);
                    }
                });
                return null;
            }
            @Override
            protected void process(List<Integer> chunks) {
                // Update progress bar safely on EDT
                if (!chunks.isEmpty()) {
                    progressBar.setValue(chunks.get(chunks.size() - 1));
                }
            }
            @Override
            protected void done() {
                // Re-enable buttons and hide progress bar
                deleteButton.setEnabled(true);
                refreshButton.setEnabled(true);
                progressBar.setVisible(false);
                if (error != null) {
                    statusLabel.setText("Deletion failed");
                    JOptionPane.showMessageDialog(DeleteTagFrame.this,
                        "Error during deletion: " + error.getMessage(),
                        "Deletion Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    statusLabel.setText("Deletion completed");
                    // Show success notification (exit condition)
                    JOptionPane.showMessageDialog(DeleteTagFrame.this,
                        "Successfully deleted " + successCount + " tag(s).",
                        "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        worker.execute();
    }
}