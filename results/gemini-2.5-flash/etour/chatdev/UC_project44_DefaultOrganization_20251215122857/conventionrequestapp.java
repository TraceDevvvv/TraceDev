/*
 * DOCSTRING:
 * This is the main application class for the RICHIESTACONVENZIONE use case.
 * It provides a graphical user interface (GUI) for the Point Of Restaurant Operator
 * to request a convention with an agency.
 * It handles the flow of events: enabling functionality, displaying a form,
 * collecting data, validating, confirming, and simulating sending the request.
 *
 * This application uses Java Swing for the GUI.
 */
import javax.swing.*;           // For GUI components
import java.awt.*;              // For layout managers and basic GUI classes
import java.awt.event.ActionEvent; // For handling action events (button clicks)
import java.awt.event.ActionListener; // For listening to action events
import javax.swing.SwingWorker; // For performing background tasks
public class ConventionRequestApp extends JFrame implements ActionListener {
    // GUI Components
    private JPanel mainPanel; // Main panel holding all other components
    private JButton requestConventionButton; // Button to initiate the convention request
    private JPanel conventionFormPanel; // Panel for the convention data entry form
    // Form fields
    private JTextField conventionNameField;
    private JTextField agencyNameField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea termsArea;
    private JButton submitButton; // Button to submit the form data
    private JButton cancelButton; // Button to cancel the form entry
    private JLabel statusLabel; // Label to show processing status
    // Serv
    private ConventionService conventionService;
    /**
     * Constructor for the ConventionRequestApp.
     * Initializes the JFrame and sets up the user interface.
     */
    public ConventionRequestApp() {
        super("RICHIESTACONVENZIONE - Convention Request System"); // Set window title
        this.conventionService = new ConventionService(); // Initialize the service layer
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(800, 600); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        createUI(); // Call method to set up all GUI components
    }
    /**
     * Initializes and lays out all GUI components.
     * This method covers steps 1 and 2 of the "Flow of events" regarding initial display.
     */
    private void createUI() {
        mainPanel = new JPanel(new CardLayout()); // Use CardLayout to switch between views
        // --- Initial View: Request Convention Button ---
        JPanel initialPanel = new JPanel(new GridBagLayout()); // Using GridBagLayout for centering
        requestConventionButton = new JButton("Request New Convention");
        requestConventionButton.setFont(new Font("Arial", Font.BOLD, 24)); // Make button prominent
        requestConventionButton.addActionListener(this); // Register action listener
        // Entry condition: "The Point Of Restaurant Operator has successfully authenticated to the system."
        // For this example, we assume authentication is successful, so the button is immediately enabled.
        requestConventionButton.setEnabled(true); // Step 1: Enable the functionality
        initialPanel.add(requestConventionButton); // Add button to the initial panel
        mainPanel.add(initialPanel, "INITIAL_VIEW"); // Add initial panel to main panel
        // --- Convention Form View ---
        conventionFormPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for form layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding
        // Set up labels and text fields for the form
        // Convention Name
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        conventionFormPanel.add(new JLabel("Convention Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
        conventionNameField = new JTextField(30);
        conventionFormPanel.add(conventionNameField, gbc);
        // Agency Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        conventionFormPanel.add(new JLabel("Agency Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
        agencyNameField = new JTextField(30);
        conventionFormPanel.add(agencyNameField, gbc);
        // Start Date
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        conventionFormPanel.add(new JLabel("Start Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
        startDateField = new JTextField(15);
        conventionFormPanel.add(startDateField, gbc);
        // End Date
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        conventionFormPanel.add(new JLabel("End Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTALLY;
        endDateField = new JTextField(15);
        conventionFormPanel.add(endDateField, gbc);
        // Terms and Conditions
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        conventionFormPanel.add(new JLabel("Terms & Conditions:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0;
        termsArea = new JTextArea(8, 30); // 8 rows, 30 columns
        termsArea.setLineWrap(true); // Wraps lines
        termsArea.setWrapStyleWord(true); // Wraps at word boundaries
        JScrollPane scrollPane = new JScrollPane(termsArea);
        conventionFormPanel.add(scrollPane, gbc);
        // Add a status label for showing progress
        statusLabel = new JLabel(" "); // Initialize with empty text
        statusLabel.setForeground(Color.BLUE); // Optional: style the label
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        statusLabel.setVisible(false); // Initially hidden
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        conventionFormPanel.add(statusLabel, gbc);
        // Buttons for form submission/cancellation
        submitButton = new JButton("Submit Convention Request");
        submitButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        gbc.gridx = 1; gbc.gridy = 7; gbc.gridwidth = 2; gbc.gridheight = 1; // Adjusted gridy to be after statusLabel
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.EAST; gbc.weighty = 0;
        conventionFormPanel.add(buttonPanel, gbc);
        mainPanel.add(conventionFormPanel, "FORM_VIEW"); // Add form panel to main panel
        add(mainPanel); // Add the main panel to the JFrame
    }
    /**
     * Handles action events from GUI components (button clicks).
     * This method orchestrates the different steps of the use case.
     * @param e The ActionEvent generated by a component.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == requestConventionButton) {
            // Step 2: Displays a form for entering data of the Convention.
            displayConventionForm();
        } else if (e.getSource() == submitButton) {
            // Step 3: Inserts the data in the form of the agreement and submit.
            // This is primarily handled by the user interacting with the text fields.
            // When submit is clicked, we move to verification and sending.
            submitConventionRequest();
        } else if (e.getSource() == cancelButton) {
            // Exit condition: "Restaurant Point Of Operator cancels the operation."
            // Clear form and return to initial view.
            clearForm();
            showInitialView();
            JOptionPane.showMessageDialog(this, "Convention request cancelled.", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Switches the view to display the convention request form.
     * Corresponds to "2 Displays a form for entering data of the Convention."
     */
    private void displayConventionForm() {
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "FORM_VIEW"); // Show the form panel
        setTitle("RICHIESTACONVENZIONE - Enter Convention Details");
    }
    /**
     * Processes the submitted convention data.
     * This method encapsulates Step 4 (Verify data and confirm) and Step 6 (Send request).
     * It uses a SwingWorker to send the request in a background thread to keep the UI responsive.
     */
    private void submitConventionRequest() {
        // Step 3: Data is inserted via JTextFields/JTextAreas.
        String conventionName = conventionNameField.getText();
        String agencyName = agencyNameField.getText();
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();
        String terms = termsArea.getText();
        ConventionData data = new ConventionData(conventionName, agencyName, startDate, endDate, terms);
        // Step 4: Verify the data entered.
        if (!data.isValid()) {
            // Where the data is invalid or insufficient, the system activates the use case Errored.
            // For this example, "Errored" is represented by an error message dialog.
            handleError("Invalid or insufficient data provided.\n" +
                        "Please ensure all fields are filled, and dates are in YYYY-MM-DD format with a valid range (start date not after end date).");
            return;
        }
        // Ask for confirmation of the request (Step 4 continued, Step 5).
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Please confirm the convention details:\n\n" + data.toString(),
                "Confirm Convention Request",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Confirmation given (Step 5 completed).
            // Disable buttons to prevent further actions while the request is being sent.
            // This provides visual feedback that an operation is in progress.
            submitButton.setEnabled(false);
            cancelButton.setEnabled(false);
            // Show status message to the user
            statusLabel.setText("Sending convention request... Please wait.");
            statusLabel.setVisible(true); // Make the label visible
            // Ensure UI updates immediately
            conventionFormPanel.revalidate();
            conventionFormPanel.repaint();
            // Use SwingWorker to perform the potentially long-running operation
            // in a background thread, keeping the UI responsive.
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    // This code runs in a separate background thread, not the EDT.
                    // Step 6: Send the request to the Convention.
                    return conventionService.sendConventionRequest(data);
                }
                @Override
                protected void done() {
                    // This code runs back on the Event Dispatch Thread (EDT) after doInBackground completes.
                    // Hide loading indicator regardless of outcome
                    statusLabel.setVisible(false);
                    statusLabel.setText(" "); // Clear text
                    try {
                        boolean success = get(); // Retrieve the result from doInBackground()
                        if (success) {
                            // Exit condition: "The notification about the call for the Convention to the Agency."
                            JOptionPane.showMessageDialog(ConventionRequestApp.this,
                                    "Convention request successfully sent to the agency!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            clearForm();        // Clear the form fields
                            showInitialView();  // Return to the initial view
                        } else {
                            // This branch handles simulated "Interruption of the connection to the server ETOUR"
                            // or other service-level errors.
                            handleError("Failed to send convention request. Please try again later.");
                        }
                    } catch (Exception ex) {
                        // Handle any exceptions that occurred during the background task.
                        handleError("An unexpected error occurred: " + ex.getMessage());
                    } finally {
                        // Re-enable the buttons after the operation is complete,
                        // allowing the user to interact with the application again.
                        submitButton.setEnabled(true);
                        cancelButton.setEnabled(true);
                        // If showInitialView() was called, the requestConventionButton for the initial
                        // view is implicitly available/enabled.
                    }
                }
            }.execute(); // Start the SwingWorker, which begins execution of doInBackground()
        } else {
            // User cancelled confirmation (effectively "Restaurant Point Of Operator cancels the operation.")
            JOptionPane.showMessageDialog(this,
                    "Convention request not sent. Operation cancelled by user.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            // No need for a status message here as the operation wasn't initiated
        }
    }
    /**
     * Simulates the "Errored" use case by displaying an error message.
     * @param message The error message to display.
     */
    private void handleError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        conventionNameField.setText("");
        agencyNameField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        termsArea.setText("");
    }
    /**
     * Switches the view back to the initial panel with the "Request New Convention" button.
     */
    private void showInitialView() {
        CardLayout cl = (CardLayout)(mainPanel.getLayout());
        cl.show(mainPanel, "INITIAL_VIEW");
        setTitle("RICHIESTACONVENZIONE - Convention Request System");
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            ConventionRequestApp app = new ConventionRequestApp();
            app.setVisible(true); // Make the JFrame visible
        });
    }
}