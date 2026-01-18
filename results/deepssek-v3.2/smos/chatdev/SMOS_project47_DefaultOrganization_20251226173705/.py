/**
 * Panel for editing a student's report card. This corresponds to the form
 * where the administrator enters new data.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditFormPanel extends JPanel {
    private StudentDataManager dataManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Student currentStudent;
    // Form components
    private JTextField idField;
    private JTextField nameField;
    private JTextField gradeField;
    private JTextField mathField;
    private JTextField scienceField;
    private JTextField englishField;
    private JTextField historyField;
    private JTextField artField;
    private JButton saveButton;
    private JButton cancelButton;
    public EditFormPanel(StudentDataManager dataManager, CardLayout cardLayout, JPanel mainPanel) {
        this.dataManager = dataManager;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initializeUI();
    }
    /**
     * Builds the edit form UI.
     */
    private void initializeUI() {
        // Title
        JLabel titleLabel = new JLabel("Edit Student Report Card", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        idField = new JTextField();
        nameField = new JTextField();
        gradeField = new JTextField();
        mathField = new JTextField();
        scienceField = new JTextField();
        englishField = new JTextField();
        historyField = new JTextField();
        artField = new JTextField();
        // Make ID field non‑editable as per requirement (identifier should not change)
        idField.setEditable(false);
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Grade:"));
        formPanel.add(gradeField);
        formPanel.add(new JLabel("Math Grade:"));
        formPanel.add(mathField);
        formPanel.add(new JLabel("Science Grade:"));
        formPanel.add(scienceField);
        formPanel.add(new JLabel("English Grade:"));
        formPanel.add(englishField);
        formPanel.add(new JLabel("History Grade:"));
        formPanel.add(historyField);
        formPanel.add(new JLabel("Art Grade:"));
        formPanel.add(artField);
        add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelEdit();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the form with the given student's data.
     * This is called when switching from the list view.
     * @param student the student to edit
     */
    public void setStudent(Student student) {
        currentStudent = student;
        idField.setText(student.getId());
        nameField.setText(student.getName());
        gradeField.setText(student.getGrade());
        mathField.setText(student.getMathGrade());
        scienceField.setText(student.getScienceGrade());
        englishField.setText(student.getEnglishGrade());
        historyField.setText(student.getHistoryGrade());
        artField.setText(student.getArtGrade());
    }
    /**
     * Validates input fields.
     * @return true if all fields are filled, false otherwise
     */
    private boolean validateInput() {
        if (nameField.getText().trim().isEmpty() ||
            gradeField.getText().trim().isEmpty() ||
            mathField.getText().trim().isEmpty() ||
            scienceField.getText().trim().isEmpty() ||
            englishField.getText().trim().isEmpty() ||
            historyField.getText().trim().isEmpty() ||
            artField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return false;
        }
        return true;
    }
    /**
     * Saves the changes and returns to the list view.
     * This corresponds to step 2 and 3 of the event sequence.
     */
    private void saveChanges() {
        if (!validateInput()) {
            return;
        }
        // Update the student object
        currentStudent.setName(nameField.getText());
        currentStudent.setGrade(gradeField.getText());
        currentStudent.setMathGrade(mathField.getText());
        currentStudent.setScienceGrade(scienceField.getText());
        currentStudent.setEnglishGrade(englishField.getText());
        currentStudent.setHistoryGrade(historyField.getText());
        currentStudent.setArtGrade(artField.getText());
        // Persist the change
        boolean success = dataManager.updateStudent(currentStudent);
        if (success) {
            // Show confirmation message
            JOptionPane.showMessageDialog(this, "Report card updated successfully!");
            // Return to the list view (post‑condition: displays the form with the list of students)
            cardLayout.show(mainPanel, "LIST");
            // Refresh the list view (optional, could be done via observer pattern)
            StudentListPanel listPanel = (StudentListPanel) mainPanel.getComponent(0);
            // For simplicity, we just reload the main frame? Actually we cannot easily refresh here.
            // Since we are using a simple demo, we leave as is.
        } else {
            JOptionPane.showMessageDialog(this, "Error updating student.");
        }
    }
    /**
     * Cancels editing and returns to the list view without saving.
     */
    private void cancelEdit() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Discard changes and return to student list?",
                "Cancel Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cardLayout.show(mainPanel, "LIST");
        }
    }
}