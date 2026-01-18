/**
 * Panel for editing news data (Steps 4-5).
 * Loads existing news data into editable fields and validates user input.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
public class EditNewsPanel extends JPanel {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField dateField;
    private JTextArea contentArea;
    private JButton submitButton;
    private JButton cancelButton;
    private NewsItem originalNews;
    private boolean dataLoaded = false;
    /**
     * Constructor initializes the editing form.
     */
    public EditNewsPanel() {
        initializeUI();
    }
    /**
     * Sets up the form with labels and input fields.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        JLabel titleLabel = new JLabel("Edit News Item", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("News Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        // Title field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Title*:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        titleField = new JTextField(35);
        formPanel.add(titleField, gbc);
        // Author field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Author*:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        authorField = new JTextField(35);
        formPanel.add(authorField, gbc);
        // Date field with format hint
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Date* (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dateField = new JTextField(35);
        formPanel.add(dateField, gbc);
        // Content area
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        formPanel.add(new JLabel("Content*:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        contentArea = new JTextArea(12, 35);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentArea);
        formPanel.add(scrollPane, gbc);
        add(formPanel, BorderLayout.CENTER);
        // Instructions
        JLabel instruction = new JLabel("* Required fields", SwingConstants.CENTER);
        instruction.setFont(new Font("Arial", Font.ITALIC, 12));
        instruction.setForeground(Color.DARK_GRAY);
        add(instruction, BorderLayout.SOUTH);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        submitButton = new JButton("Submit Changes");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(180, 40));
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads news data into the form for editing.
     */
    public void loadNewsData(NewsItem news) {
        this.originalNews = news;
        titleField.setText(news.getTitle());
        authorField.setText(news.getAuthor());
        dateField.setText(news.getDate());
        contentArea.setText(news.getContent());
        dataLoaded = true;
    }
    /**
     * Validates and returns modified news, or null if invalid.
     */
    public NewsItem getModifiedNews() {
        if (!dataLoaded) {
            return null;
        }
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String date = dateField.getText().trim();
        String content = contentArea.getText().trim();
        // Basic validation (mimics Step 6 checking)
        if (title.isEmpty() || author.isEmpty() || date.isEmpty() || content.isEmpty()) {
            return null;
        }
        // Additional date format validation
        if (!isValidDate(date)) {
            return null;
        }
        return new NewsItem(originalNews.getId(), title, author, date, content);
    }
    /**
     * Simple date format validation.
     */
    private boolean isValidDate(String dateStr) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    /**
     * Sets listener for submit button.
     */
    public void setSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
    /**
     * Sets listener for cancel button.
     */
    public void setCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}