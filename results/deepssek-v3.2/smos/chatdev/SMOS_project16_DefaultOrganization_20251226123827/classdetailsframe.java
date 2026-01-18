'''
Displays detailed information of a class as per precondition:
"the user displays the detailed information of a class"
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ClassDetailsFrame extends JFrame {
    private DatabaseSimulator db;
    private int classId;
    private Class currentClass;
    private JLabel idLabel, nameLabel, descLabel, teacherLabel, roomLabel, maxStudentsLabel;
    private JButton deleteButton, backButton;
    public ClassDetailsFrame(DatabaseSimulator db, int classId) {
        this.db = db;
        this.classId = classId;
        this.currentClass = db.getClassById(classId);
        if (currentClass == null) {
            JOptionPane.showMessageDialog(null, 
                "Class not found with ID: " + classId + "\nReturning to class list.",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            // Return to class list instead of exiting
            new ClassListFrame(db).setVisible(true);
            return; // Exit constructor early
        }
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Class Details - ID: " + classId);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Class Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Details panel
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        detailsPanel.add(new JLabel("Class ID:", SwingConstants.RIGHT));
        idLabel = new JLabel(String.valueOf(currentClass.getId()));
        detailsPanel.add(idLabel);
        detailsPanel.add(new JLabel("Class Name:", SwingConstants.RIGHT));
        nameLabel = new JLabel(currentClass.getName());
        detailsPanel.add(nameLabel);
        detailsPanel.add(new JLabel("Description:", SwingConstants.RIGHT));
        descLabel = new JLabel(currentClass.getDescription());
        detailsPanel.add(descLabel);
        detailsPanel.add(new JLabel("Teacher:", SwingConstants.RIGHT));
        teacherLabel = new JLabel(currentClass.getTeacher());
        detailsPanel.add(teacherLabel);
        detailsPanel.add(new JLabel("Room:", SwingConstants.RIGHT));
        roomLabel = new JLabel(currentClass.getRoom());
        detailsPanel.add(roomLabel);
        detailsPanel.add(new JLabel("Max Students:", SwingConstants.RIGHT));
        maxStudentsLabel = new JLabel(String.valueOf(currentClass.getMaxStudents()));
        detailsPanel.add(maxStudentsLabel);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        deleteButton = new JButton("Delete Class");
        deleteButton.setBackground(new Color(220, 100, 100));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton = new JButton("Back to List");
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add action listeners
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete button click (precondition: "the user click on the 'Delete' button")
                deleteClass();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to class list
                showClassList();
            }
        });
        add(mainPanel);
    }
    /**
     * Deletes the current class and shows updated list.
     * Implements Events sequence:
     * 1. Delete the class from the archive
     * 2. Displays the list of updated classes
     */
    private void deleteClass() {
        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete class: " + currentClass.getName() + "?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // Step 1: Delete the class from the archive
            boolean success = db.deleteClass(classId);
            if (success) {
                JOptionPane.showMessageDialog(this,
                    "Class '" + currentClass.getName() + "' deleted successfully!",
                    "Deletion Complete",
                    JOptionPane.INFORMATION_MESSAGE);
                // Step 2: Displays the list of updated classes
                dispose(); // Close current window
                showClassList();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Failed to delete class. It may have been already deleted.",
                    "Deletion Failed",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Shows the updated class list after deletion.
     */
    private void showClassList() {
        ClassListFrame listFrame = new ClassListFrame(db);
        listFrame.setVisible(true);
    }
}