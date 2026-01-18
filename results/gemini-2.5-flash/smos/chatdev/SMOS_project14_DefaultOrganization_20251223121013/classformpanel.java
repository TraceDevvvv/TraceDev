'''
GUI panel for inserting new class data.
It provides input fields for class name, address, and academic year.
It includes validation logic and interacts with the ClassArchive to save new classes.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ClassFormPanel extends JPanel {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField academicYearField;
    private JButton saveButton;
    private ClassArchive classArchive; // Reference to the data archive
    /**
     * Constructs a new ClassFormPanel.
     * @param classArchive The ClassArchive instance to which new classes will be added.
     */
    public ClassFormPanel(ClassArchive classArchive) {
        this.classArchive = classArchive;
        setLayout(new GridBagLayout()); // Using GridBagLayout for flexible component placement
        setBorder(BorderFactory.createTitledBorder("New Class Information")); // Title for the panel
        // Set up GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontal space
        // 1. Class Name
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        add(new JLabel("Class Name:"), gbc);
        gbc.gridx = 1; // Column 1
        nameField = new JTextField(20); // 20 preferred columns wide
        add(nameField, gbc);
        // 2. Address
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1
        add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; // Column 1
        addressField = new JTextField(20);
        add(addressField, gbc);
        // 3. Academic Year
        gbc.gridx = 0; // Column 0
        gbc.gridy = 2; // Row 2
        add(new JLabel("Academic Year (e.g., 2023):"), gbc);
        gbc.gridx = 1; // Column 1
        academicYearField = new JTextField(20);
        add(academicYearField, gbc);
        // 4. Save Button
        gbc.gridx = 0; // Column 0
        gbc.gridy = 3; // Row 3
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        saveButton = new JButton("Save Class");
        add(saveButton, gbc);
        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveClass(); // Call the method to handle saving the class
            }
        });
    }
    /**
     * Handles the saving of class data when the "Save" button is clicked.
     * Performs input validation and interacts with the ClassArchive.
     */
    private void saveClass() {
        String name = nameField.getText().trim();        // Get and trim input values
        String address = addressField.getText().trim();
        String academicYearText = academicYearField.getText().trim();
        // Data validation logic
        if (name.isEmpty()) {
            // Activate "Errodati" use case: show error message
            JOptionPane.showMessageDialog(this, "Class Name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (address.isEmpty()) {
            // Activate "Errodati" use case: show error message
            JOptionPane.showMessageDialog(this, "Address cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int academicYear;
        try {
            academicYear = Integer.parseInt(academicYearText);
            if (academicYear < 1900 || academicYear > 2100) { // Simple range check for academic year
                JOptionPane.showMessageDialog(this, "Academic Year must be a realistic year (e.g., between 1900 and 2100).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            // Activate "Errodati" use case: show error message for invalid year format
            JOptionPane.showMessageDialog(this, "Academic Year must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // If all validation passes, create and insert the new class
        SchoolClass newSchoolClass = new SchoolClass(name, address, academicYear);
        boolean success = classArchive.insertClass(newSchoolClass);
        if (success) {
            // Notify user of success (Postcondition: user has entered a class in the system and is notified)
            JOptionPane.showMessageDialog(this, "Class '" + name + "' inserted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm(); // Clear the form fields after successful insertion
            // For demonstration/debugging, you might print all classes in the archive
            // classArchive.printAllClasses();
        } else {
            // This path would be hit if classArchive.insertClass had more robust error handling
            // For now, it's mostly for simulated failures.
            JOptionPane.showMessageDialog(this, "Failed to insert class. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        nameField.setText("");
        addressField.setText("");
        academicYearField.setText("");
    }
}