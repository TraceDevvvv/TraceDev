/**
 * Confirmation panel for reviewing changes before final submission (Step 6-7).
 * Displays all modified fields read-only for operator verification.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class ConfirmationPanel extends JPanel {
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel dateLabel;
    private JTextArea contentArea;
    private JButton confirmButton;
    private JButton cancelButton;
    private NewsItem newsToConfirm;
    /**
     * Constructor sets up the confirmation view.
     */
    public ConfirmationPanel() {
        initializeUI();
    }
    /**
     * Creates the confirmation interface.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(0, 15));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        JLabel title = new JLabel("Confirm News Changes", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);
        // Main details panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Review Updated News"));
        // Basic info panel
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        infoPanel.add(new JLabel("Title:", Font.BOLD, 12), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(titleLabel, gbc);
        // Author
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        infoPanel.add(new JLabel("Author:", Font.BOLD, 12), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        authorLabel = new JLabel();
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(authorLabel, gbc);
        // Date
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        infoPanel.add(new JLabel("Date:", Font.BOLD, 12), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(dateLabel, gbc);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        // Content area
        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.setBorder(BorderFactory.createTitledBorder("Content"));
        contentArea = new JTextArea(15, 50);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(contentArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        // Warning message
        JLabel warning = new JLabel("Please verify all changes before confirming.", SwingConstants.CENTER);
        warning.setFont(new Font("Arial", Font.ITALIC, 12));
        warning.setForeground(Color.RED);
        add(warning, BorderLayout.SOUTH);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        confirmButton = new JButton("Confirm Changes");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(34, 139, 34));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setPreferredSize(new Dimension(180, 40));
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
        cancelButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the panel with news details for confirmation.
     */
    public void setNewsDetails(NewsItem news) {
        this.newsToConfirm = news;
        titleLabel.setText(news.getTitle());
        authorLabel.setText(news.getAuthor());
        dateLabel.setText(news.getDate());
        contentArea.setText(news.getContent());
        // Scroll to top
        contentArea.setCaretPosition(0);
    }
    /**
     * Returns the news item to be confirmed.
     */
    public NewsItem getNews() {
        return newsToConfirm;
    }
    /**
     * Enables or disables the confirm button.
     */
    public void setConfirmButtonEnabled(boolean enabled) {
        confirmButton.setEnabled(enabled);
    }
    /**
     * Sets listener for confirm button.
     */
    public void setConfirmButtonListener(ActionListener listener) {
        confirmButton.addActionListener(listener);
    }
    /**
     * Sets listener for cancel button.
     */
    public void setCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}