/**
 * Displays all news in a form for selection (Step 2).
 * Provides a table view with news preview and edit selection functionality.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
public class NewsListPanel extends JPanel {
    private JTable newsTable;
    private DefaultTableModel tableModel;
    private JButton editButton;
    private AgencyNewsModel newsModel;
    /**
     * Constructor sets up the panel with news data.
     */
    public NewsListPanel(AgencyNewsModel model) {
        this.newsModel = model;
        initializeUI();
        refreshNewsList();
    }
    /**
     * Creates the UI components for news listing.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        JLabel titleLabel = new JLabel("Available News Items", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        // Table setup
        String[] columnNames = {"ID", "Title", "Author", "Date", "Content Preview"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                return String.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Read-only
            }
        };
        newsTable = new JTable(tableModel);
        newsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        newsTable.setRowHeight(25);
        newsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(newsTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("News List"));
        add(scrollPane, BorderLayout.CENTER);
        // Instructions
        JLabel instructions = new JLabel("Select a news item and click 'Edit' to modify.", SwingConstants.CENTER);
        instructions.setFont(new Font("Arial", Font.ITALIC, 12));
        instructions.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        add(instructions, BorderLayout.SOUTH);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        editButton = new JButton("Edit Selected News");
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setPreferredSize(new Dimension(200, 35));
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Refreshes the news list from the model.
     */
    public void refreshNewsList() {
        tableModel.setRowCount(0);
        List<NewsItem> newsList = newsModel.getAllNews();
        for (NewsItem news : newsList) {
            String preview = news.getContent();
            if (preview.length() > 40) {
                preview = preview.substring(0, 40) + "...";
            }
            tableModel.addRow(new Object[]{
                news.getId(),
                news.getTitle(),
                news.getAuthor(),
                news.getDate(),
                preview
            });
        }
        if (tableModel.getRowCount() > 0) {
            newsTable.setRowSelectionInterval(0, 0);
        }
    }
    /**
     * Returns the selected news item or null if none selected.
     */
    public NewsItem getSelectedNews() {
        int selectedRow = newsTable.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            return newsModel.getNewsById(id);
        }
        return null;
    }
    /**
     * Sets the action listener for the edit button.
     */
    public void setEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }
}