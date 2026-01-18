'''
A modal JDialog for the administrator to select an academic year.
It presents a dropdown list of typical academic years.
'''
import javax.swing.*;
import java.awt.*;
import java.time.Year;
public class AcademicYearSelectionDialog extends JDialog {
    private JComboBox<Integer> yearComboBox;
    private Integer selectedYear = null; // Stores the selected year
    /**
     * Constructs the AcademicYearSelectionDialog.
     *
     * @param owner The parent Frame of this dialog (usually the main application frame).
     */
    public AcademicYearSelectionDialog(Window owner) {
        super(owner, "Select Academic Year", ModalityType.APPLICATION_MODAL); // Set as a modal dialog
        setSize(300, 150); // Set dialog size
        setLocationRelativeTo(owner); // Center dialog relative to its owner
        setResizable(false); // Make dialog non-resizable
        initUI(); // Initialize UI components
    }
    /**
     * Initializes all UI components for the dialog.
     */
    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Use FlowLayout for simple arrangement
        JLabel label = new JLabel("Choose Academic Year:");
        add(label);
        // Populate JComboBox with a range of academic years
        yearComboBox = new JComboBox<>();
        int currentYear = Year.now().getValue();
        for (int i = currentYear - 5; i <= currentYear + 1; i++) { // From 5 years ago to next year
            yearComboBox.addItem(i);
        }
        // Set default selection to the current year if available
        yearComboBox.setSelectedItem(currentYear);
        add(yearComboBox);
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            selectedYear = (Integer) yearComboBox.getSelectedItem(); // Get selected year
            dispose(); // Close the dialog
        });
        buttonPanel.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            selectedYear = null; // No year selected
            dispose(); // Close the dialog
        });
        buttonPanel.add(cancelButton);
        add(buttonPanel);
    }
    /**
     * Returns the academic year selected by the user.
     *
     * @return The selected academic year as an Integer, or null if cancelled.
     */
    public Integer getSelectedYear() {
        return selectedYear;
    }
}