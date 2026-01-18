import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
// import java.text.ParseException; // Import for date parsing if needed for actual date validation, no longer directly used for validation here
/**
 * `NewsFormPanel` is a user interface component (JPanel) that facilitates the
 * insertion of a new news article into the system. It handles user input,
 * client-side validation, confirmation dialogs, and interacts with the `NewsService`
 * for business logic and data persistence.
 */
public class NewsFormPanel extends JPanel {
    private JTextField titleField;
    private JTextArea contentArea;
    private JTextField authorField;
    private JTextField publicationDateField;
    private JButton submitButton;
    private JButton cancelButton;
    private NewsService newsService; // Instance of the service layer for news operations
    /**
     * Constructs a new `NewsFormPanel`.
     * This involves initializing all GUI components (labels, text fields, buttons),
     * setting up the layout, and attaching event listeners to interactive elements.
     */
    public NewsFormPanel() {
        this.newsService = new NewsService(); // Initialize the service responsible for saving news
        // Set up the main panel's layout and padding.
        setLayout(new BorderLayout(10, 10)); // Outer layout with some spacing
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add internal padding
        // Header label for the form.
        JLabel headerLabel = new JLabel("Insert a New News Article", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);
        // Panel to hold input fields using GridBagLayout for flexible arrangement.
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding for each component in the grid
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components will fill their display area horizontally
        // Title Field
        gbc.gridx = 0; gbc.gridy = 0; // Position: row 0, column 0
        gbc.weightx = 0.0; // Label takes minimal horizontal space
        formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; // Position: row 0, column 1
        gbc.weightx = 1.0; // Text field takes all remaining horizontal space
        titleField = new JTextField(30); // Preffered width for the text field
        formPanel.add(titleField, gbc);
        // Author Field
        gbc.gridx = 0; gbc.gridy = 1; // Position: row 1, column 0
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; // Position: row 1, column 1
        gbc.weightx = 1.0;
        authorField = new JTextField(30);
        formPanel.add(authorField, gbc);
        // Publication Date Field (pre-filled with current date)
        gbc.gridx = 0; gbc.gridy = 2; // Position: row 2, column 0
        gbc.weightx = 0.0;
        formPanel.add(new JLabel("Pub. Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; // Position: row 2, column 1
        gbc.weightx = 1.0;
        publicationDateField = new JTextField(30);
        // Set current date as default, using "yyyy-MM-dd" format for consistency with validation.
        publicationDateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        formPanel.add(publicationDateField, gbc);
        // Content Area (spanning two columns)
        gbc.gridx = 0; gbc.gridy = 3; // Position: row 3, column 0
        gbc.gridwidth = 2; // Spans across label and text field columns
        gbc.weightx = 1.0; gbc.weighty = 0.0; // Label mostly horizontal, no vertical growth
        formPanel.add(new JLabel("Content:"), gbc);
        gbc.gridy = 4; // Position: row 4
        gbc.weighty = 1.0; // Allow the text area to expand vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        contentArea = new JTextArea(10, 30); // 10 rows, 30 columns preferred size
        contentArea.setLineWrap(true); // Enable line wrapping
        contentArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(contentArea); // Add scroll capability for long content
        formPanel.add(scrollPane, gbc);
        add(formPanel, BorderLayout.CENTER); // Add the form panel to the center of the main panel
        // Panel for action buttons (Submit, Cancel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0)); // Right-aligned flow layout
        submitButton = new JButton("Submit News");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the main panel
        // Attach action listeners to buttons.
        submitButton.addActionListener(e -> handleSubmit());
        cancelButton.addActionListener(e -> handleCancel());
        // Step 1: Activate the feature to insert a news (represented by this panel being displayed).
        // Step 2: Displays the corresponding form (layout defined above).
    }
    /**
     * Handles the submission of the news form.
     * This method retrieves user input, performs client-side validation,
     * prompts for confirmation, and then attempts to store the news
     * via the `NewsService`. It also handles various success and error scenarios.
     */
    private void handleSubmit() {
        // Step 3: User fills out the form and clicks "Submit News".
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        String author = authorField.getText().trim();
        String publicationDate = publicationDateField.getText().trim();
        // Step 4: Verify the data entered. Client-side validation.
        // If data is invalid or insufficient, the "Errored" use case is activated.
        if (title.isEmpty() || content.isEmpty() || author.isEmpty() || publicationDate.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please ensure all fields (Title, Content, Author, Publication Date) are filled out.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Stop processing if validation fails.
        }
        // Basic validation for publication date format (YYYY-MM-DD).
        // Actual date validity (e.g., Feb 30) will be handled by the server-side validation in NewsService
        if (!publicationDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Publication Date format. Please use YYYY-MM-DD (e.g., 2023-10-27).",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return; // Stop processing if date format is incorrect.
        }
        // Create a News object with the collected data.
        News newNews = new News(title, content, author, publicationDate);
        // Ask for confirmation of the transaction.
        int confirmResult = JOptionPane.showConfirmDialog(this,
                "Please review the news details before insertion:\n\n" + newNews.toString() +
                "\n\nConfirm you want to insert this news?",
                "Confirm News Insertion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // Step 5: User confirms or cancels the operation.
        if (confirmResult == JOptionPane.YES_OPTION) {
            try {
                // Step 6: Attempt to store the data of the new news via the service.
                newsService.saveNews(newNews);
                // Exit condition: System notifies proper placement of the news.
                JOptionPane.showMessageDialog(this,
                        "News '" + title + "' successfully inserted into the system!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearForm(); // Clear the form fields after successful submission.
            } catch (ValidationException e) {
                // Catches validation failures from the NewsService (e.g., server-side complex validation including date validity).
                // This activates the "Errored" use case.
                JOptionPane.showMessageDialog(this,
                        "Data validation failed during storage: " + e.getMessage(),
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException e) {
                // Catches simulated connection interruptions (ETOUR scenario).
                // This addresses the "Interruption of the connection to the server ETOUR" exit condition.
                JOptionPane.showMessageDialog(this,
                        e.getMessage() + "\nPlease verify your network connection and try again.",
                        "Connection Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Catches any other unexpected errors during the saving process.
                JOptionPane.showMessageDialog(this,
                        "An unexpected error occurred during news insertion: " + e.getMessage(),
                        "System Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace(); // Print stack trace for debugging purposes.
            }
        } else {
            // Exit condition: The Operator Agency cancels the operation.
            JOptionPane.showMessageDialog(this,
                    "News insertion operation cancelled by the operator.",
                    "Operation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Handles the cancellation of the news insertion operation.
     * Prompts the user for confirmation before clearing the form.
     */
    private void handleCancel() {
        // Exit condition: The Operator Agency cancels the operation.
        int confirmCancel = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel the current news insertion and clear the form?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmCancel == JOptionPane.YES_OPTION) {
            clearForm(); // Clear all input fields.
            JOptionPane.showMessageDialog(this,
                    "News insertion operation successfully cancelled.",
                    "Operation Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Clears all input fields in the form and resets the publication date to the current date.
     * This prepares the form for a new entry.
     */
    private void clearForm() {
        titleField.setText("");
        contentArea.setText("");
        authorField.setText("");
        // Reset publication date field to the current date after clearing.
        publicationDateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
}