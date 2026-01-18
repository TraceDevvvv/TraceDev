/**
 * JPanel responsible for displaying the form to insert justification details.
 * It allows the administrator to enter a justification date and save it.
 * It communicates with the MainApp for displaying status messages and
 * returning to the registry screen.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
// Add this import as per comment
import javax.swing.SwingWorker;
/**
 * JPanel responsible for displaying the form to insert justification details.
 * It allows the administrator to enter a justification date and save it.
 * It communicates with the MainApp for displaying status messages and
 * returning to the registry screen.
 */
public class JustificationFormPanel extends JPanel {
    private JLabel absenceInfoLabel; // Displays information about the absence being justified
    private JTextField justificationDateField; // Input field for the justification date
    private JButton saveButton;
    private JButton cancelButton;
    private Absence absenceToJustify; // The absence object currently being justified
    private JustificationService justificationService; // Service to handle saving justification
    // Callbacks to communicate with the parent MainApp
    // Callback for when justification saving is complete (success or failure)
    private BiConsumer<Boolean, String> onJustificationComplete;
    // Callback for when the operation is cancelled or needs to revert to registry screen
    private Consumer<Void> onOperationCancelled;
    /**
     * Constructor for JustificationFormPanel.
     * @param absence The Absence object that is being justified.
     * @param onJustificationComplete Callback to inform parent about save result (boolean success, String absenceId).
     * @param onOperationCancelled Callback to inform parent about cancellation or completion and return to registry.
     */
    public JustificationFormPanel(Absence absence, BiConsumer<Boolean, String> onJustificationComplete, Consumer<Void> onOperationCancelled) {
        this.absenceToJustify = absence;
        this.justificationService = new JustificationService();
        this.onJustificationComplete = onJustificationComplete;
        this.onOperationCancelled = onOperationCancelled;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        initComponents(); // Initialize GUI components
        setupLayout();    // Arrange components
        addListeners();   // Add event listeners
    }
    /**
     * Initializes the graphical components of the form.
     */
    private void initComponents() {
        absenceInfoLabel = new JLabel("Justify Absence for Employee: " + absenceToJustify.getEmployeeName() + " on " + absenceToJustify.getDate(), SwingConstants.CENTER);
        absenceInfoLabel.setFont(new Font("Serif", Font.BOLD, 18)); // Make it stand out
        justificationDateField = new JTextField(LocalDate.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE), 15); // Pre-fill with current date
        justificationDateField.setToolTipText("Enter justification date (YYYY-MM-DD)");
        saveButton = new JButton("Save Justification");
        cancelButton = new JButton("Cancel");
    }
    /**
     * Lays out the components on the panel.
     */
    private void setupLayout() {
        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Add absence info label
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        inputPanel.add(absenceInfoLabel, gbc);
        // Add "Justification Date" label
        gbc.gridy++;
        gbc.gridwidth = 1; // Reset to 1 column
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Justification Date (YYYY-MM-DD):"), gbc);
        // Add justification date text field
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(justificationDateField, gbc);
        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        // Add input and button panels to the main panel
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Adds action listeners to the buttons.
     * This handles the "Save" and "Cancel" actions.
     */
    private void addListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Event sequence step 3: User clicks on "Save"
                saveJustification();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Postcondition: The administrator interrupts the operation
                System.out.println("Administrator interrupted justification operation for absence: " + absenceToJustify.getId());
                JOptionPane.showMessageDialog(JustificationFormPanel.this, "Operation cancelled by administrator.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                onOperationCancelled.accept(null); // Return to registry screen
            }
        });
    }
    /**
     * Handles the logic for saving the justification.
     * This includes input validation, creating a Justification object,
     * and calling the JustificationService on a background thread using SwingWorker.
     */
    private void saveJustification() {
        String dateString = justificationDateField.getText().trim();
        LocalDate justificationLocalDate;
        // Input validation for date format (this part remains on EDT)
        if (dateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Justification date cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            justificationLocalDate = LocalDate.parse(dateString, java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Precondition check for SMOS server connection (immediate check, can be on EDT)
        if (!JustificationService.isSmosServerConnected()) {
            // Postcondition: Connection to the SMOS server interrupted
            JOptionPane.showMessageDialog(this, "SMOS server connection interrupted. Cannot save justification.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            onJustificationComplete.accept(false, absenceToJustify.getId());
            return;
        }
        // Disable buttons and show busy cursor while processing to prevent UI freezing
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final Justification newJustification = new Justification(absenceToJustify.getId(), justificationLocalDate);
        // Use SwingWorker for background processing to avoid blocking the Event Dispatch Thread (EDT)
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Event sequence step 4: Save the justification
                // This runs on a separate worker thread
                return justificationService.saveJustification(newJustification);
            }
            @Override
            protected void done() {
                // This runs on the Event Dispatch Thread (EDT) after doInBackground() completes
                try {
                    boolean success = get(); // Retrieve the result from doInBackground()
                    if (success) {
                        // Postcondition: The justification data has been entered into the system.
                        System.out.println("Justification successfully saved for absence: " + absenceToJustify.getId());
                        onJustificationComplete.accept(true, absenceToJustify.getId()); // Inform parent of success
                    } else {
                        // The error message for individual save failure (e.g., random error)
                        JOptionPane.showMessageDialog(JustificationFormPanel.this, "Failed to save justification. Please try again.", "Save Error", JOptionPane.ERROR_MESSAGE);
                        onJustificationComplete.accept(false, absenceToJustify.getId()); // Inform parent of failure
                    }
                } catch (Exception ex) {
                    // Handle any exceptions that occurred in doInBackground() (e.g., InterruptedException, other runtime exceptions)
                    JOptionPane.showMessageDialog(JustificationFormPanel.this, "An unexpected error occurred during saving: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Error saving justification: " + ex.getMessage());
                    onJustificationComplete.accept(false, absenceToJustify.getId());
                } finally {
                    // Always re-enable buttons and restore default cursor, regardless of success or failure
                    saveButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        }.execute(); // Start the SwingWorker, which executes doInBackground() on a new thread
    }
}