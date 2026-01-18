/**
 * Form for inserting justification details
 * Shows a form with fields for justification date and details
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
public class InsertJustificationForm extends JDialog {
    private JTextField dateField;
    private JTextArea detailsArea;
    private int absenceId;
    private RegistryScreen parent;
    public InsertJustificationForm(RegistryScreen parent, int absenceId) {
        super(parent, "Insert Justification", true);
        this.parent = parent;
        this.absenceId = absenceId;
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Insert Justification for Absence ID: " + absenceId, 
                                       SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form fields
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        // Date field
        JPanel datePanel = new JPanel(new BorderLayout(5, 5));
        datePanel.add(new JLabel("Justification Date (YYYY-MM-DD):"), BorderLayout.WEST);
        dateField = new JTextField(LocalDate.now().toString());
        datePanel.add(dateField, BorderLayout.CENTER);
        // Details area
        JPanel detailsPanel = new JPanel(new BorderLayout(5, 5));
        detailsPanel.add(new JLabel("Justification Details:"), BorderLayout.NORTH);
        detailsArea = new JTextArea(8, 30);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);
        // Helper text
        JLabel helperLabel = new JLabel("<html><i>Note: Please provide detailed justification for the absence.</i></html>");
        formPanel.add(datePanel);
        formPanel.add(detailsPanel);
        formPanel.add(helperLabel);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJustification();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void saveJustification() {
        // Validate inputs
        String dateStr = dateField.getText().trim();
        String details = detailsArea.getText().trim();
        if (dateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a justification date.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            dateField.requestFocus();
            return;
        }
        if (details.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter justification details.",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            detailsArea.requestFocus();
            return;
        }
        try {
            // Validate and parse date using strict resolver
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                .withResolverStyle(ResolverStyle.STRICT);
            LocalDate justificationDate = LocalDate.parse(dateStr, formatter);
            // Validate that date is not in the future
            if (justificationDate.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(this,
                    "Justification date cannot be in the future.",
                    "Date Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Create justification object
            Justification justification = new Justification(absenceId, justificationDate, details);
            // Save to database
            boolean success = DatabaseSimulator.getInstance().saveJustification(justification);
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Justification saved successfully! Returning to registry screen...",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                // Postcondition: Return to registry screen
                dispose();
                parent.loadAbsences(); // Refresh the registry screen
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to save justification. Connection to server may be interrupted.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                "Invalid date format. Please use YYYY-MM-DD format (e.g., 2024-01-15).",
                "Date Error",
                JOptionPane.ERROR_MESSAGE);
            dateField.requestFocus();
            dateField.selectAll();
        } catch (Exception e) {
            // Handle SMOS server interruption or other errors
            String message = e.getMessage();
            if (message != null && message.contains("SMOS server")) {
                message = "Connection to the SMOS server was interrupted. Please try again.";
            }
            JOptionPane.showMessageDialog(this,
                "Error: " + message + "\nPlease try again.",
                "System Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cancel() {
        // Administrator interrupts the operation
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel? Any unsaved changes will be lost.",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}